package com.example.demo.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.Model.PhongBanModel;
import com.example.demo.entity.NhanVienEntity;
import com.example.demo.entity.PhongBanEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.sevice.NhanVienService;
import com.example.demo.sevice.PhongBanService;
import com.example.demo.sevice.TicketService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@SessionAttributes({"nhanvienlogin"})
public class ApiController {

	@Autowired
	NhanVienService nhanVienSerivce;
	
	@Autowired
	PhongBanService phongBanService;
	
	@Autowired
	TicketService ticketService;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

//	-------------------------------- REGISTER ---------------------------------------
	@RequestMapping(path = "api/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam String fullName, @RequestParam String email, @RequestParam String password,
			@RequestParam String rePassword) {

		if (fullName != "" && email != "" && password != "" && rePassword != "") {
			if (password.equals(rePassword)) {
				NhanVienEntity nhanVien = new NhanVienEntity();
				nhanVien.setFullName(fullName);
				nhanVien.setEmail(email);
				nhanVien.setPassword(password);
				nhanVien.setNgayDangKy(new Timestamp(System.currentTimeMillis()));
				boolean emailPattern = validateEmail(email);
				boolean emailExist = checkEmailExist(nhanVien);
				if (emailPattern == true && emailExist == true) {
					nhanVienSerivce.save(nhanVien);
					return "Đăng ký thành công !";
				} else {
					String message = "";
					if (emailPattern == false) {
						message = "Email chưa đúng định dạng !";
					}
					if (emailExist == false) {
						message = "Email đã tồn tại. Vui lòng đăng ký Email khác !";
					}
					return message;
				}

			} else {
				return "Mật khẩu chưa trùng nhau !";
			}
		} else {
			return "Các trường không được để trống !";
		}

	}

	public boolean checkEmailExist(NhanVienEntity nv) {
		boolean check = true;
		List<NhanVienEntity> listNhanVien = nhanVienSerivce.findAll();
		for (int i = 0; i < listNhanVien.size(); i++) {
			if (nv.getEmail().equals(listNhanVien.get(i).getEmail())) {
				check = false;
				break;
			}
		}
		return check;
	}

//	-------------------------------- LOGIN ---------------------------------------
	@RequestMapping(path = "api/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, HttpSession httpSession) {
		if (email != "" && password != "") {
			NhanVienEntity nhanVien = nhanVienSerivce.findByEmailAndPassword(email, password);
			if (nhanVien != null) {
				httpSession.setAttribute("nhanvienlogin", nhanVien);
				return "Đăng nhập thành công";
			} else {
				String message = "";
				List<NhanVienEntity> listNhanVien = nhanVienSerivce.findAll();
				boolean emailExist = checkEmailExist(email);
				if (emailExist == false) {
					message = "Email chưa được đăng ký !";
				} else {
					for (int i = 0; i < listNhanVien.size(); i++) {
						if (!(password.equals(listNhanVien.get(i).getPassword()))) {
							message = "Sai mật khẩu";
						}
					}
				}
				return message;
			}
		} else {
			return "Các trường không được để trống";
		}

	}

	public boolean checkEmailExist(String email) {
		boolean check = false;
		List<NhanVienEntity> listNhanVien = nhanVienSerivce.findAll();
		for (int i = 0; i < listNhanVien.size(); i++) {
			if (email.equals(listNhanVien.get(i).getEmail())) {
				check = true;
				break;
			}
		}
		return check;
	}
		
//	-------------------------------- FORGOT PASSWORD ---------------------------------------
	@RequestMapping(path = "api/laypass", method = RequestMethod.POST)
	public String layPass(@RequestParam String email) {		
		if(email != "") {			
			String message = "";
			boolean emailExist = checkEmailExist(email);
			if (emailExist == false) {
				message = "Email chưa được đăng ký !";
			} 
			else {
				NhanVienEntity nhanVien = nhanVienSerivce.findByEmail(email);
				String pass = nhanVien.getPassword();
				message = "MK của bạn: " + pass;
			}
			return message;
		}else {
			return "Các trường không được để trống";
		}
	}
	
	
//	-------------------------------- QUAN LY TICKET ---------------------------------------
	
//	THEM
	@RequestMapping(path = "api/ghinhankhieunai", method = RequestMethod.POST)
	@ResponseBody
	public String ghiNhanKhieuNai(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			TicketEntity ticketEntity = new TicketEntity();
			jsonObject = objectMapper.readTree(dataJson);
			
			PhongBanEntity phongBanEntity = new PhongBanEntity();
			phongBanEntity.setId(jsonObject.get("phongBan").asInt());
			phongBanEntity.setTenPhongBan(jsonObject.get("phongBan").asText());
			
			String sdtKhachHang = jsonObject.get("sdtKhachHang").asText();
			String yKienKhachHang = jsonObject.get("yKienKhachHang").asText();
			
			ticketEntity.setPhongBan(phongBanEntity);
			ticketEntity.setSdtKhachHang(sdtKhachHang);
			ticketEntity.setyKienKhachHang(yKienKhachHang);
			ticketEntity.setThoiGianTiepNhan(new Timestamp(System.currentTimeMillis()));
			
			ticketService.save(ticketEntity);
		} catch (Exception e) {
			
		}
		return "";
	}
	
//	XOA
	@RequestMapping(path = "api/xoaticket", method = RequestMethod.POST)
	@ResponseBody
	public String xoaTicket(@RequestParam int id) {
		ticketService.deleteById(id);
		return "";
	}
	
//	XU LY TICKET
	@RequestMapping(path = "api/xuly", method = RequestMethod.POST)
	@ResponseBody
	public String xuLy(@RequestParam String dataJson, HttpSession httpSession) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			TicketEntity ticketEntity = new TicketEntity();
			jsonObject = objectMapper.readTree(dataJson);
			
			PhongBanEntity phongBanEntity = new PhongBanEntity();
			phongBanEntity.setId(jsonObject.get("phongBan").asInt());
			
			int id = jsonObject.get("id").asInt();
			String sdtKhachHang = jsonObject.get("sdtKhachHang").asText();
			String yKienKhachHang = jsonObject.get("yKienKhachHang").asText();
			boolean trangThaiXuLy = true;
			String noiDungXuLy = jsonObject.get("noiDungXuLy").asText();
			String thoiGianTiepNhan = jsonObject.get("thoiGianTiepNhan").asText();
			Timestamp thoiGianXuLy = new Timestamp(System.currentTimeMillis());
			
			if( httpSession.getAttribute("nhanvienlogin") != null) {
				NhanVienEntity nv = (NhanVienEntity) httpSession.getAttribute("nhanvienlogin");
				ticketEntity.setNhanVien(nv);
			}
			ticketEntity.setPhongBan(phongBanEntity);
			ticketEntity.setId(id);
			ticketEntity.setSdtKhachHang(sdtKhachHang);
			ticketEntity.setyKienKhachHang(yKienKhachHang);
			ticketEntity.setTrangThaiXuLy(trangThaiXuLy);
			ticketEntity.setNoiDungXuLy(noiDungXuLy);
			ticketEntity.setThoiGianXuLy(thoiGianXuLy);
			ticketEntity.setThoiGianTiepNhan(Timestamp.valueOf(thoiGianTiepNhan));
			ticketService.save(ticketEntity);
			
		} catch (Exception e) {
			
		}
		return "";
	}
	
	
//	-------------------------------- QUAN LY PHONG BAN ---------------------------------------
	
//	THEM
	@RequestMapping(path = "api/them-phongban", method = RequestMethod.POST)
	@ResponseBody
	public String themPhongBan(@RequestParam String tenPhongBan) {	
		if(tenPhongBan != "") {
			boolean check = checkPhongBanExist(tenPhongBan);
			if(check == false) {
				PhongBanEntity phongBan = new PhongBanEntity();
				phongBan.setTenPhongBan(tenPhongBan);
				phongBan.setNgayTao(new Timestamp(System.currentTimeMillis()));
				phongBanService.save(phongBan);
				return "Thêm thành công";
			}else {
				return "Đã tồn tại phòng ban";
			}
			
		}else {
			return "Thất bại";
		}	
	}
	
	public boolean checkPhongBanExist(String tenPhongBan) {
		boolean check = false;
		List<PhongBanEntity> listPhongBan = phongBanService.findAll();
		for(int i = 0; i < listPhongBan.size(); i++) {
			if(tenPhongBan.equals(listPhongBan.get(i).getTenPhongBan())) {
				check = true;
			}
		}
		return check;
	}
	
//	XOA
	@RequestMapping(path = "api/xoa-phongban", method = RequestMethod.POST)
	@ResponseBody
	public String xoaPhongBan(@RequestParam int id) {
		phongBanService.deleteById(id);
		return "";
	}
	
//	LAY THONG TIN PHONG BAN
	/*
	 * @RequestMapping(path = "api/laythongtinphongban", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public PhongBanModel layThongTin(@RequestParam int id) {
	 * PhongBanModel phongBanModel = new PhongBanModel(); PhongBanEntity
	 * phongBanEntity = phongBanService.getById(id);
	 * 
	 * phongBanModel.setId(phongBanEntity.getId());
	 * phongBanModel.setTenPhongBan(phongBanEntity.getTenPhongBan());
	 * phongBanModel.setNgayTao(phongBanEntity.getNgayTao());
	 * 
	 * return phongBanModel; }
	 */
	
//	CAP NHAT PHONG BAN
	@RequestMapping(path = "api/capnhatphongban", method = RequestMethod.POST)
	public String capNhatPhongBan(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			PhongBanEntity phongBanEntity = new PhongBanEntity();
			jsonObject = objectMapper.readTree(dataJson);
			String tenPhongBan = jsonObject.get("tenPhongBan").asText();
			int id = jsonObject.get("id").asInt();
			phongBanEntity.setId(id);
			phongBanEntity.setTenPhongBan(tenPhongBan);
			phongBanEntity.setNgayTao(new Timestamp(System.currentTimeMillis()));
			phongBanService.save(phongBanEntity);
		} catch (Exception e) {
			
		}
		return "";
	}
	
	
	
}

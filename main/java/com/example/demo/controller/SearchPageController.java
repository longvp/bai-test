package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.NhanVienEntity;
import com.example.demo.entity.PhongBanEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.sevice.PhongBanService;
import com.example.demo.sevice.TicketService;

@Controller
public class SearchPageController {
	
	@Autowired
	PhongBanService phongBanService;
	
	@Autowired
	TicketService ticketService;
	
//	@RequestMapping(path = "/search-page", method = RequestMethod.GET)
//	public String Default1(Model model, HttpSession httpSession) {
//		try {
//			if( httpSession.getAttribute("nhanvienlogin") != null) {
//				NhanVienEntity nv = (NhanVienEntity) httpSession.getAttribute("nhanvienlogin");
//				model.addAttribute("nhanvien", nv);
//			}
//			return "SearchPage";
//		} catch (Exception e) {
//			return "";
//		}
//		
//	}
	
	
	 @RequestMapping(path = "/search-phongban", method = RequestMethod.GET) 
	 public String Default2(Model model, HttpSession httpSession) {
		 try {
				if( httpSession.getAttribute("nhanvienlogin") != null) {
					NhanVienEntity nv = (NhanVienEntity) httpSession.getAttribute("nhanvienlogin");
					model.addAttribute("nhanvien", nv);
				}
				 return "SearchPage"; 
			} catch (Exception e) {
				return "";
			}
	 }
	 
	@RequestMapping(path = "/search-phongban", method = RequestMethod.POST)
	public String timKiemPhongBan(@RequestParam String tenPhongBan, Model model) {
		try {
			List<PhongBanEntity> listPhongBanTheoTen = phongBanService.findByTenPhongBan(tenPhongBan);
			model.addAttribute("listPhongBanTheoTen", listPhongBanTheoTen);
			return "SearchPage";
		} catch (Exception e) {
			return "";
		}
	}
	
	@RequestMapping(path = "/search-khieunai", method = RequestMethod.POST)
	public String timKiemTicket(@RequestParam String tenPhongBan, Model model) {
		try {
			List<TicketEntity> listTicketTheoTenPhongBan = ticketService.findAllByTenPhongBan(tenPhongBan);
			model.addAttribute("listTicketTheoTenPhongBan", listTicketTheoTenPhongBan);
			return "SearchPage";
		} catch (Exception e) {
			return "";
		}
	}

}

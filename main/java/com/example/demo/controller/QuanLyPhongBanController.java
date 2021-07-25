package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.NhanVienEntity;
import com.example.demo.entity.PhongBanEntity;
import com.example.demo.sevice.PhongBanService;

@Controller
public class QuanLyPhongBanController {
	
	@Autowired
	PhongBanService phongBanService;
	
	@RequestMapping(path = "/quanly-phongban", method = RequestMethod.GET)
	public String Default(Model model, HttpSession httpSession) {
		try {
			if( httpSession.getAttribute("nhanvienlogin") != null) {
				NhanVienEntity nv = (NhanVienEntity) httpSession.getAttribute("nhanvienlogin");
				model.addAttribute("nhanvien", nv);
			}
			
			List<PhongBanEntity> listPhongBan = phongBanService.findAll();
			model.addAttribute("listPhongBan", listPhongBan);
			
			return "QuanLy-PhongBan";
		} catch (Exception e) {
			return null;
		}
	}

}

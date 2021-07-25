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
import com.example.demo.entity.TicketEntity;
import com.example.demo.sevice.PhongBanService;
import com.example.demo.sevice.TicketService;

@Controller
public class QuanLyKhieuNaiController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	PhongBanService phongBanSerivce;
	
	@RequestMapping(path = "/quanly-khieunai", method = RequestMethod.GET)
	public String Default(Model model, HttpSession httpSession) {
		try {
			if( httpSession.getAttribute("nhanvienlogin") != null) {
				NhanVienEntity nv = (NhanVienEntity) httpSession.getAttribute("nhanvienlogin");
				model.addAttribute("nhanvien", nv);
			}
			List<PhongBanEntity> listPhongBan = phongBanSerivce.findAll();
			model.addAttribute("listPhongBan", listPhongBan);
			
			List<TicketEntity> listTicket = ticketService.findAll();
			model.addAttribute("listTicket", listTicket);
			
			return "QuanLy-KhieuNai";
		} catch (Exception e) {
			return "";
		}
	}
	
	

}

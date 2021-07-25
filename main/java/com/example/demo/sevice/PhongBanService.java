package com.example.demo.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PhongBanEntity;
import com.example.demo.repository.PhongBanRepository;

@Service
public class PhongBanService {
	
	@Autowired
	PhongBanRepository phongBanRepository;
	
	public List<PhongBanEntity> findAll(){
		return phongBanRepository.findAll();
	}
	
	public PhongBanEntity getById(int id) {
		return phongBanRepository.getById(id);
	}
	
	public List<PhongBanEntity> findByTenPhongBan(String tenPhongBan) {
		return phongBanRepository.findAllByTenPhongBan(tenPhongBan);
	}

	public void save(PhongBanEntity phongBan) {
		phongBanRepository.save(phongBan);
	}
	
	public void deleteById(int id) {
		phongBanRepository.deleteById(id);
	}
	
}

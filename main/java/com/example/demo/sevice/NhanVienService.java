package com.example.demo.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NhanVienEntity;
import com.example.demo.repository.NhanVienRepository;

@Service
public class NhanVienService {
	
	@Autowired
	NhanVienRepository nhanVienRepository;
	
	public void save(NhanVienEntity nhanVien) {
		nhanVienRepository.save(nhanVien);
	}
	
	public List<NhanVienEntity> findAll(){
		return nhanVienRepository.findAll();
	}
	
	public NhanVienEntity findByEmailAndPassword(String email, String password) {
		return nhanVienRepository.findByEmaiAndPassword(email, password);
	}

	public NhanVienEntity findByEmail(String email) {
		return nhanVienRepository.findByEmail(email);
	}
	
}

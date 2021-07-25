package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.NhanVienEntity;

public interface NhanVienRepository extends JpaRepository<NhanVienEntity, Integer>{
	
	@Query(value = "select * from nhanvien where email = ?1 and password = ?2 ", nativeQuery = true)
	public NhanVienEntity findByEmaiAndPassword(String email, String password);
	
	@Query(value = "select * from nhanvien where email = ?1", nativeQuery = true)
	public NhanVienEntity findByEmail(String email);
}

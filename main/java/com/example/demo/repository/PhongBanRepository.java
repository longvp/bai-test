package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhongBanEntity;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBanEntity, Integer> {
	
	@Query(value = "select * from phongban where tenphongban like %?1%", nativeQuery = true)
	public List<PhongBanEntity> findAllByTenPhongBan(String tenPhongBan);

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
	
	@Query(value = "select * from  ticket where "
			+ "ticket.id_phongban = (select phongban.id from phongban where phongban.tenphongban = ?1) ",
			nativeQuery = true)
	public List<TicketEntity> findAllByTenPhongBan(String tenPhongBan);

}

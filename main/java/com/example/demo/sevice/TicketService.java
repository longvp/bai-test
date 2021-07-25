package com.example.demo.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;
	
	public List<TicketEntity> findAll(){
		return ticketRepository.findAll();
	}
	
	public List<TicketEntity> findAllByTenPhongBan(String tenPhongBan){
		return ticketRepository.findAllByTenPhongBan(tenPhongBan);
	}
	
	public TicketEntity getById(int id) {
		return ticketRepository.getById(id);
	}
	
	public void save(TicketEntity ticket) {
		ticketRepository.save(ticket);
	}
	
	public void deleteById(int id) {
		ticketRepository.deleteById(id);
	}
	
}

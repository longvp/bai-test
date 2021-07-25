package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "phongban")
public class PhongBanEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tenphongban")
	private String tenPhongBan;
	
	@Column(name = "ngaytao")
	private Timestamp ngayTao;
	
	@OneToMany(mappedBy = "phongBan", cascade = CascadeType.ALL)
	private List<TicketEntity> tickets;

//	@OneToOne
//	@JoinColumn(name = "id_nhanvien")
//	private NhanVien nhanVien;
//	
//	public NhanVien getNhanVien() {
//		return nhanVien;
//	}
//
//	public void setNhanVien(NhanVien nhanVien) {
//		this.nhanVien = nhanVien;
//	}

	public int getId() {
		return id;
	}

	public List<TicketEntity> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketEntity> tickets) {
		this.tickets = tickets;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenPhongBan() {
		return tenPhongBan;
	}

	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}

	public Timestamp getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Timestamp ngayTao) {
		this.ngayTao = ngayTao;
	}

}

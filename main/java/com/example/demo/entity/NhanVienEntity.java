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
@Table(name = "nhanvien")
public class NhanVienEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "ngaydangky")
	private Timestamp ngayDangKy;
	
	@OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
	private List<TicketEntity> tickets;
	
//	@OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
//	private List<PhongBan> phongBans;
//
//	public List<PhongBan> getPhongBans() {
//		return phongBans;
//	}
//
//	public void setPhongBans(List<PhongBan> phongBans) {
//		this.phongBans = phongBans;
//	}

	public List<TicketEntity> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketEntity> tickets) {
		this.tickets = tickets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Timestamp ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	
	
	

}

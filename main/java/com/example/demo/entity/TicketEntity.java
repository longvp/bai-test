package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class TicketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "sdtkhachhang")
	private String sdtKhachHang;
	
	@Column(name = "ykienkhachhang")
	private String yKienKhachHang;
	
	@Column(name = "noidungxuly")
	private String noiDungXuLy;
	
	@Column(name = "trangthaixuly")
	private boolean trangThaiXuLy;
	
	@Column(name = "thoigiantiepnhan")
	private Timestamp thoiGianTiepNhan;
	
	@Column(name = "thoigianxuly")
	private Timestamp thoiGianXuLy;
	
	@ManyToOne
	@JoinColumn(name = "id_phongban")
	private PhongBanEntity phongBan;
	
	@ManyToOne
	@JoinColumn(name = "id_nhanvien")
	private NhanVienEntity nhanVien;

	public PhongBanEntity getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBanEntity phongBan) {
		this.phongBan = phongBan;
	}

	public NhanVienEntity getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVienEntity nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSdtKhachHang() {
		return sdtKhachHang;
	}

	public void setSdtKhachHang(String sdtKhachHang) {
		this.sdtKhachHang = sdtKhachHang;
	}

	public String getyKienKhachHang() {
		return yKienKhachHang;
	}

	public void setyKienKhachHang(String yKienKhachHang) {
		this.yKienKhachHang = yKienKhachHang;
	}

	public String getNoiDungXuLy() {
		return noiDungXuLy;
	}

	public void setNoiDungXuLy(String noiDungXuLy) {
		this.noiDungXuLy = noiDungXuLy;
	}

	public boolean isTrangThaiXuLy() {
		return trangThaiXuLy;
	}

	public void setTrangThaiXuLy(boolean trangThaiXuLy) {
		this.trangThaiXuLy = trangThaiXuLy;
	}

	public Timestamp getThoiGianTiepNhan() {
		return thoiGianTiepNhan;
	}

	public void setThoiGianTiepNhan(Timestamp thoiGianTiepNhan) {
		this.thoiGianTiepNhan = thoiGianTiepNhan;
	}

	public Timestamp getThoiGianXuLy() {
		return thoiGianXuLy;
	}

	public void setThoiGianXuLy(Timestamp thoiGianXuLy) {
		this.thoiGianXuLy = thoiGianXuLy;
	}
	
	
	

}

package entity;

import java.io.Serializable;
import java.util.Objects;

public class LuongNhanVienHanhChinh implements Serializable {
	private String maLuong;
	private int thang;
	private int nam;
	private NhanVienHanhChinh nhanVien;
	private double luongThucLanh;
	private double giamTru;
	private double tamUng;
	
	
	//---constructors---
	public LuongNhanVienHanhChinh() {
		
	}

	
	public LuongNhanVienHanhChinh(String maLuong) {
		this.maLuong = maLuong;
	}


	public LuongNhanVienHanhChinh(String maLuong, int thang, int nam, NhanVienHanhChinh nhanVien, 
			double luongThucLanh, double giamTru, double tamUng) {
		this.maLuong = maLuong;
		this.thang = thang;
		this.nam = nam;
		this.nhanVien = nhanVien;
		this.luongThucLanh = luongThucLanh;
		this.giamTru = giamTru;
		this.tamUng = tamUng;
	}


	//---getters/setters---
	public String getMaLuong() {
		return maLuong;
	}


	public void setMaLuong(String maLuong) {
		this.maLuong = maLuong;
	}


	public int getThang() {
		return thang;
	}


	public void setThang(int thang) {
		this.thang = thang;
	}


	public int getNam() {
		return nam;
	}


	public void setNam(int nam) {
		this.nam = nam;
	}


	public NhanVienHanhChinh getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(NhanVienHanhChinh nhanVien) {
		this.nhanVien = nhanVien;
	}


	public double getLuongThucLanh() {
		return luongThucLanh;
	}


	public void setLuongThucLanh(double luongThucLanh) {
		this.luongThucLanh = luongThucLanh;
	}


	public double getGiamTru() {
		return giamTru;
	}


	public void setGiamTru(double giamTru) {
		this.giamTru = giamTru;
	}


	public double getTamUng() {
		return tamUng;
	}


	public void setTamUng(double tamUng) {
		this.tamUng = tamUng;
	}


	//---hashCode/equals---
	@Override
	public int hashCode() {
		return Objects.hash(maLuong);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongNhanVienHanhChinh other = (LuongNhanVienHanhChinh) obj;
		return Objects.equals(maLuong, other.maLuong);
	}
	
}

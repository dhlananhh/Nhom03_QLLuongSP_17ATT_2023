package entity;

import java.io.Serializable;
import java.util.Objects;

public class LuongNhanVienHanhChinh implements Serializable {
	private String maBangLuongHC;
	private int nam;
	private int thang;
	private boolean trangThai;
	private NhanVienHanhChinh nhanVien;
	private float heSoLuong;
	private float luongCoBan;
	private float phuCap;
	private float luongThucLanh;
	
	
	//---constructors---
	public LuongNhanVienHanhChinh() {
		
	}


	public LuongNhanVienHanhChinh (String maBangLuongHC) {
		this.maBangLuongHC = maBangLuongHC;
	}


	public LuongNhanVienHanhChinh (String maBangLuongHC, int nam, int thang) {
		this.maBangLuongHC = maBangLuongHC;
		this.nam = nam;
		this.thang = thang;
	}

	
	public LuongNhanVienHanhChinh (String maBangLuongHC, int nam, int thang, 
			boolean trangThai, NhanVienHanhChinh nhanVien, 
			float heSoLuong, float luongCoBan, float phuCap) {
		this.maBangLuongHC = maBangLuongHC;
		this.nam = nam;
		this.thang = thang;
		this.trangThai = trangThai;
		this.nhanVien = nhanVien;
		this.heSoLuong = heSoLuong;
		this.luongCoBan = luongCoBan;
		this.phuCap = phuCap;
	}


	//---getters/setters---
	public String getMaBangLuongHC() {
		return maBangLuongHC;
	}


	public void setMaBangLuongHC(String maBangLuongHC) {
		this.maBangLuongHC = maBangLuongHC;
	}


	public int getNam() {
		return nam;
	}


	public void setNam(int nam) {
		this.nam = nam;
	}


	public int getThang() {
		return thang;
	}


	public void setThang(int thang) {
		this.thang = thang;
	}


	public boolean isTrangThai() {
		return trangThai;
	}


	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}


	public NhanVienHanhChinh getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(NhanVienHanhChinh nhanVien) {
		this.nhanVien = nhanVien;
	}


	public float getHeSoLuong() {
		return heSoLuong;
	}


	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}


	public float getLuongCoBan() {
		return luongCoBan;
	}


	public void setLuongCoBan(float luongCoBan) {
		this.luongCoBan = luongCoBan;
	}


	public float getPhuCap() {
		return phuCap;
	}


	public void setPhuCap(float phuCap) {
		this.phuCap = phuCap;
	}

	
	public float getLuongThucLanh() {
		return luongThucLanh;
	}


	public void setLuongThucLanh(float luongThucLanh) {
		this.luongThucLanh = luongThucLanh;
	}


	//---hashCode/equals---
	@Override
	public int hashCode() {
		return Objects.hash(maBangLuongHC);
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
		return Objects.equals(maBangLuongHC, other.maBangLuongHC);
	}

	
	// hàm tính lương thực lãnh cho nhân viên hành chính
	public float tinhLuongThucLanh() {
		float luongThucLanh = 0;
		
		luongThucLanh = (luongCoBan * heSoLuong) + phuCap / 26 * 26;
		
		return luongThucLanh;
	}


	//---toString---
	@Override
	public String toString() {
		return "LuongNhanVienHanhChinh [maBangLuongHC=" + maBangLuongHC + ", nam=" + nam + ", thang=" + thang
				+ ", trangThai=" + trangThai + ", nhanVien=" + nhanVien + ", heSoLuong=" + heSoLuong + ", luongCoBan="
				+ luongCoBan + ", phuCap=" + phuCap + ", luongThucLanh=" + luongThucLanh + "]";
	}
	
}

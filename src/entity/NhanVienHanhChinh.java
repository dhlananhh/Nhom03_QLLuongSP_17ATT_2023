package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class NhanVienHanhChinh implements Serializable {
	private String maNV;
	private String hoTenNV;
	private boolean gioiTinh;
	private Date ngaySinh;
	private String diaChi;
	private String CCCD;
	private String SDT;
	private Date ngayVao;
	private PhongBan phongBan;
	private boolean trangThai;
	private String bangCap;
	private float luongCoBan;
	private float phuCap;
	private float heSoLuong;
	private TaiKhoan taiKhoan;
	private String email;
	
	
	//---constructors---
	public NhanVienHanhChinh() {
		
	}


	public NhanVienHanhChinh(String maNV, String hoTenNV) {
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}


	public NhanVienHanhChinh(String maNV, String hoTenNV, boolean gioiTinh, 
			Date ngaySinh, String diaChi, String CCCD, String BHXH, String MST, 
			Date ngayVao, PhongBan phongBan, String SDT, boolean trangThai, String bangCap, 
			float luongCoBan, float phuCap, float heSoLuong, 
			TaiKhoan tenTaiKhoan, String email) {
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.CCCD = CCCD;
		this.SDT = SDT;
		this.ngayVao = ngayVao;
		this.phongBan = phongBan;
		this.trangThai = trangThai;
		this.bangCap = bangCap;
		this.luongCoBan = luongCoBan;
		this.phuCap = phuCap;
		this.heSoLuong = heSoLuong;
		this.taiKhoan = tenTaiKhoan;
		this.email = email;
	}


	//---getters/setters
	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTenNV() {
		return hoTenNV;
	}


	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}


	public boolean isGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public Date getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public String getCCCD() {
		return CCCD;
	}


	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	
	public String getSDT() {
		return SDT;
	}


	public void setSDT(String sDT) {
		SDT = sDT;
	}


	public Date getNgayVao() {
		return ngayVao;
	}


	public void setNgayVao(Date ngayVao) {
		this.ngayVao = ngayVao;
	}


	public PhongBan getPhongBan() {
		return phongBan;
	}


	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}


	public boolean isTrangThai() {
		return trangThai;
	}


	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}


	public String getBangCap() {
		return bangCap;
	}


	public void setBangCap(String bangCap) {
		this.bangCap = bangCap;
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


	public float getHeSoLuong() {
		return heSoLuong;
	}


	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}


	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}


	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	//---hashCode/equals
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVienHanhChinh other = (NhanVienHanhChinh) obj;
		return Objects.equals(maNV, other.maNV);
	}


	//---toString---
	@Override
	public String toString() {
		return "NhanVienHanhChinh [maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", gioiTinh=" + gioiTinh + ", ngaySinh="
				+ ngaySinh + ", diaChi=" + diaChi + ", CCCD=" + CCCD + ", SDT=" + SDT + ", ngayVao=" + ngayVao
				+ ", phongBan=" + phongBan + ", trangThai=" + trangThai + ", bangCap=" + bangCap + ", luongCoBan="
				+ luongCoBan + ", phuCap=" + phuCap + ", heSoLuong=" + heSoLuong + ", taiKhoan=" + taiKhoan + ", email="
				+ email + "]";
	}

}

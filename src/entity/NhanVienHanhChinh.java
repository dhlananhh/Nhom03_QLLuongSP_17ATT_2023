package entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
	
	
	// tính hệ số lương
	public float tinhHeSoLuong (Date ngayVaoLam, String bangCap) {
		Instant instantNgayVaoLam = new java.util.Date(ngayVaoLam.getTime()).toInstant();
		LocalDate localDateNgayVaoLam = instantNgayVaoLam.atZone(ZoneId.systemDefault()).toLocalDate();
		
		LocalDate ngayHienTai = LocalDate.now();
		Period khoangThoiGian = Period.between(localDateNgayVaoLam, ngayHienTai);
		int soNamLamViec = khoangThoiGian.getYears();
		int soBacTangLuong = soNamLamViec / 3;
		
		float heSoTienTrienCaoDang = 0.31f;
		float heSoTienTrienDaiHoc = 0.20f;
		
		float heSoLuong = 0.0f;
		
		if ("Cao đẳng".equalsIgnoreCase(bangCap))
			heSoLuong = 2.1f;
		else if ("Đại học".equalsIgnoreCase(bangCap))
			heSoLuong = 2.34f;
		
		if ("Cao đẳng".equalsIgnoreCase(bangCap))
			heSoLuong += soBacTangLuong * heSoTienTrienCaoDang;
		else if ("Đại học".equalsIgnoreCase(bangCap))
			heSoLuong += soBacTangLuong * heSoTienTrienDaiHoc;
		
		return heSoLuong;
	}
	
	
	public float tinhHeSoLuong() {
		Instant instantNgayVaoLam = new java.util.Date(getNgayVao().getTime()).toInstant();
		LocalDate localDateNgayVaoLam = instantNgayVaoLam.atZone(ZoneId.systemDefault()).toLocalDate();
		
		LocalDate ngayHienTai = LocalDate.now();
		Period khoangThoiGian = Period.between(localDateNgayVaoLam, ngayHienTai);
		int soNamLamViec = khoangThoiGian.getYears();
		int soBacTangLuong = soNamLamViec / 3;
		
		float heSoTienTrienCaoDang = 0.31f;
		float heSoTienTrienDaiHoc = 0.20f;
		
		float heSoLuong = 0.0f;
		
		if ("Cao đẳng".equalsIgnoreCase(bangCap))
			heSoLuong = 2.1f;
		else if ("Đại học".equalsIgnoreCase(bangCap))
			heSoLuong = 2.34f;
		
		if ("Cao đẳng".equalsIgnoreCase(bangCap))
			heSoLuong += soBacTangLuong * heSoTienTrienCaoDang;
		else if ("Đại học".equalsIgnoreCase(bangCap))
			heSoLuong += soBacTangLuong * heSoTienTrienDaiHoc;
		
		return heSoLuong;
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

package entity;

import java.time.LocalDate;
import java.util.Objects;

public class CongNhanSanXuat {
	private String maCN;
	private String hoTenCN;
	private String gioiTinh;
	private LocalDate ngaySinh;
	private String diaChi;
	private String CCCD;
	private String BHXH;
	private String MST;
	private LocalDate ngayVao;
	private PhongBan phongBan;
	private int toSanXuat;
	private String trangThai;
	private String bangCap;
	private int tayNghe;
	private double luongSanPham;
	private double phuCap;
	
	
	//---constructors---
	public CongNhanSanXuat() {
		
	}


	public CongNhanSanXuat(String maCN) {
		this.maCN = maCN;
	}

	
	public CongNhanSanXuat(String maCN, String hoTenCN, String gioiTinh, LocalDate ngaySinh, String diaChi, 
			String CCCD, String BHXH, String MST, LocalDate ngayVao, PhongBan phongBan, 
			int toSanXuat, String trangThai, String bangCap, int tayNghe, double luongSanPham, double phuCap) {
		this.maCN = maCN;
		this.hoTenCN = hoTenCN;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.CCCD = CCCD;
		this.BHXH = BHXH;
		this.MST = MST;
		this.ngayVao = ngayVao;
		this.phongBan = phongBan;
		this.toSanXuat = toSanXuat;
		this.trangThai = trangThai;
		this.bangCap = bangCap;
		this.tayNghe = tayNghe;
		this.luongSanPham = luongSanPham;
		this.phuCap = phuCap;
	}


	//---getters/setters---
	public String getMaCN() {
		return maCN;
	}


	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}


	public String getHoTenCN() {
		return hoTenCN;
	}


	public void setHoTenCN(String hoTenCN) {
		this.hoTenCN = hoTenCN;
	}


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public LocalDate getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(LocalDate ngaySinh) {
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


	public String getBHXH() {
		return BHXH;
	}


	public void setBHXH(String bHXH) {
		BHXH = bHXH;
	}


	public String getMST() {
		return MST;
	}


	public void setMST(String mST) {
		MST = mST;
	}


	public LocalDate getNgayVao() {
		return ngayVao;
	}


	public void setNgayVao(LocalDate ngayVao) {
		this.ngayVao = ngayVao;
	}


	public int getTayNghe() {
		return tayNghe;
	}


	public void setTayNghe(int tayNghe) {
		this.tayNghe = tayNghe;
	}


	public int getToSanXuat() {
		return toSanXuat;
	}


	public void setToSanXuat(int toSanXuat) {
		this.toSanXuat = toSanXuat;
	}


	public String getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	public String getBangCap() {
		return bangCap;
	}


	public void setBangCap(String bangCap) {
		this.bangCap = bangCap;
	}

	
	public PhongBan getPhongBan() {
		return phongBan;
	}


	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}


	public double getLuongSanPham() {
		return luongSanPham;
	}


	public void setLuongSanPham(double luongSanPham) {
		this.luongSanPham = luongSanPham;
	}


	public double getPhuCap() {
		return phuCap;
	}


	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}


	//---hashCode/equals---
	@Override
	public int hashCode() {
		return Objects.hash(maCN);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhanSanXuat other = (CongNhanSanXuat) obj;
		return Objects.equals(maCN, other.maCN);
	}
	
}

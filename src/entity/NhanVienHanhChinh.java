package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVienHanhChinh implements Serializable {
	private String maNV;
	private String hoTenNV;
	private String gioiTinh;
	private LocalDate ngaySinh;
	private String diaChi;
	private String CCCD;
	private String BHXH;
	private String MST;
	private LocalDate ngayVao;
	private PhongBan phongBan;
	private ChucDanh chucDanh;
	private String trangThai;
	private String bangCap;
	
	
	//---constructors---
	public NhanVienHanhChinh() {
		
	}


	public NhanVienHanhChinh(String maNV, String hoTenNV) {
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}


	public NhanVienHanhChinh(String maNV, String hoTenNV, String gioiTinh, LocalDate ngaySinh, String diaChi,
			String CCCD, String BHXH, String MST, LocalDate ngayVao, PhongBan phongBan, ChucDanh chucDanh,
			String trangThai, String bangCap) {
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.CCCD = CCCD;
		this.BHXH = BHXH;
		this.MST = MST;
		this.ngayVao = ngayVao;
		this.phongBan = phongBan;
		this.chucDanh = chucDanh;
		this.trangThai = trangThai;
		this.bangCap = bangCap;
	}


	//---getters/setters---
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


	public PhongBan getPhongBan() {
		return phongBan;
	}


	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}


	public ChucDanh getChucDanh() {
		return chucDanh;
	}


	public void setChucDanh(ChucDanh chucDanh) {
		this.chucDanh = chucDanh;
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


	//---hashCode/equals---
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

}

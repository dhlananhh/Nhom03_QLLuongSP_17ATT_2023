package entity;


import java.util.Objects;
import java.sql.Date;


public class CongNhanSanXuat {
	private String maCN;
	private String hoTenCN;
	private boolean gioiTinh;
	private Date ngaySinh;
	private String diaChi;
	private String CCCD;
	private String SDT;
	private Date ngayVao;
	private ToSanXuat toSanXuat;
	private boolean trangThai;
	private String bangCap;
	private int tayNghe;
	private double luongSanPham;
	private double phuCap;
	
	
	//---constructors---
	public CongNhanSanXuat() {
		
	}


	public CongNhanSanXuat(String maCN, String hoTenCN) {
		this.maCN = maCN;
		this.hoTenCN = hoTenCN;
	}
	public CongNhanSanXuat(String maCN) {
		this.maCN = maCN;
	}


	public CongNhanSanXuat(String maCN, String hoTenCN, boolean gioiTinh, Date ngaySinh, String diaChi, String cCCD,
			String sDT, Date ngayVao, ToSanXuat toSanXuat, boolean trangThai, String bangCap, int tayNghe,
			double luongSanPham, double phuCap) {
		super();
		this.maCN = maCN;
		this.hoTenCN = hoTenCN;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		CCCD = cCCD;
		SDT = sDT;
		this.ngayVao = ngayVao;
		this.toSanXuat = toSanXuat;
		this.trangThai = trangThai;
		this.bangCap = bangCap;
		this.tayNghe = tayNghe;
		this.luongSanPham = luongSanPham;
		this.phuCap = phuCap;
	}


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


	public ToSanXuat getToSanXuat() {
		return toSanXuat;
	}


	public void setToSanXuat(ToSanXuat toSanXuat) {
		this.toSanXuat = toSanXuat;
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


	public int getTayNghe() {
		return tayNghe;
	}


	public void setTayNghe(int tayNghe) {
		this.tayNghe = tayNghe;
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


	@Override
	public String toString() {
		return "CongNhanSanXuat [maCN=" + maCN + ", hoTenCN=" + hoTenCN + ", gioiTinh=" + gioiTinh + ", ngaySinh="
				+ ngaySinh + ", diaChi=" + diaChi + ", CCCD=" + CCCD + ", SDT=" + SDT + ", ngayVao=" + ngayVao
				+ ", toSanXuat=" + toSanXuat + ", trangThai=" + trangThai + ", bangCap=" + bangCap + ", tayNghe="
				+ tayNghe + ", luongSanPham=" + luongSanPham + ", phuCap=" + phuCap + "]";
	}


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

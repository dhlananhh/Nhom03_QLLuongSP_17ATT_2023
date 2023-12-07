package entity;

import java.sql.Date;

public class ChamCong {
	private String  maCN, maCD;
	private Date ngayCham;
	private int chiTieu, soLuongHoanThanh;
	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public Date getNgayCham() {
		return ngayCham;
	}
	public void setNgayCham(Date ngayCham) {
		this.ngayCham = ngayCham;
	}
	public int getChiTieu() {
		return chiTieu;
	}
	public void setChiTieu(int chiTieu) {
		this.chiTieu = chiTieu;
	}
	public int getSoLuongHoanThanh() {
		return soLuongHoanThanh;
	}
	public void setSoLuongHoanThanh(int soLuongHoanThanh) {
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	public ChamCong(String maCN, Date ngayCham, int soLuongHoanThanh) {
		super();
		this.maCN = maCN;
		this.ngayCham = ngayCham;
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	
	public ChamCong(String maCN, String maCD, Date ngayCham, int chiTieu, int soLuongHoanThanh) {
		super();
		this.maCN = maCN;
		this.maCD = maCD;
		this.ngayCham = ngayCham;
		this.chiTieu = chiTieu;
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	public ChamCong(String maCN, String maCD, Date ngayCham, int chiTieu) {
		super();
		this.maCN = maCN;
		this.maCD = maCD;
		this.ngayCham = ngayCham;
		this.chiTieu = chiTieu;
	}
	public ChamCong(String maCN, Date ngayCham) {
		super();
		this.maCN = maCN;
		this.ngayCham = ngayCham;
	}
	
}

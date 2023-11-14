package entity;

import java.sql.Date;

public class DiemDanh {
	private String maNV;
	private Date ngayCham;
	private String trangThai;

	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public Date getNgayCham() {
		return ngayCham;
	}
	public void setNgayCham(Date ngayCham) {
		this.ngayCham = ngayCham;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public DiemDanh(String maNV, Date ngayCham, String trangThai) {
		super();
		this.maNV = maNV;
		this.ngayCham = ngayCham;
		this.trangThai = trangThai;
	}
	public DiemDanh() {
		super();
	}
	public DiemDanh(Date ngayCham) {
		super();
		this.ngayCham = ngayCham;
	}
	public DiemDanh(String maNV) {
		super();
		this.maNV = maNV;
	}
	
	
}

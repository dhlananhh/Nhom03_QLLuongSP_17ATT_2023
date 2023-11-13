package entity;

import java.sql.Date;

public class DiemDanh {
	private String maNV;
	private Date ngayCham;
	private boolean phep;
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
	public boolean isPhep() {
		return phep;
	}
	public void setPhep(boolean phep) {
		this.phep = phep;
	}
	public DiemDanh(String maNV, Date ngayCham, boolean phep) {
		super();
		this.maNV = maNV;
		this.ngayCham = ngayCham;
		this.phep = phep;
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

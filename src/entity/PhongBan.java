package entity;


import java.io.Serializable;
import java.util.Objects;


public class PhongBan implements Serializable {
	private String maPhongBan;
	private String tenPhongBan;
	private String ghiChu;
	
	
	//---constructors---
	public PhongBan() {
		
	}

	
	public PhongBan(String maPhongBan) {
		this.maPhongBan = maPhongBan;
	}

	
	public PhongBan(String maPhongBan, String tenPhongBan) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
	}


	public PhongBan(String maPhongBan, String tenPhongBan, String ghiChu) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
		this.ghiChu = ghiChu;
	}


	//---getters/setters---
	public String getMaPhongBan() {
		return maPhongBan;
	}


	public void setMaPhongBan(String maPhongBan) {
		this.maPhongBan = maPhongBan;
	}


	public String getTenPhongBan() {
		return tenPhongBan;
	}


	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	//---hashCode/equals---
	@Override
	public int hashCode() {
		return Objects.hash(maPhongBan);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return Objects.equals(maPhongBan, other.maPhongBan);
	}
	
}

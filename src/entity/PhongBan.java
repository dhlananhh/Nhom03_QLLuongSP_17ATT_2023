package entity;


import java.io.Serializable;
import java.util.Objects;


public class PhongBan implements Serializable {
	private String maPhongBan;
	private String tenPhongBan;
	private int soLuongNV;
	private String moTa;
	
	
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


	public PhongBan(String maPhongBan, String tenPhongBan, int soLuongNV, String moTa) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
		this.soLuongNV = soLuongNV;
		this.moTa = moTa;
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

	
	public int getSoLuongNV() {
		return soLuongNV;
	}


	public void setSoLuongNV(int soLuongNV) {
		this.soLuongNV = soLuongNV;
	}


	public String getMoTa() {
		return moTa;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
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


	//---toString---
	@Override
	public String toString() {
		return "PhongBan [maPhongBan=" + maPhongBan + ", tenPhongBan=" + tenPhongBan + ", soLuongNV=" + soLuongNV
				+ ", moTa=" + moTa + "]";
	}
	
}

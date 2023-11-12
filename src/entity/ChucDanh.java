package entity;


import java.io.Serializable;
import java.util.Objects;


public class ChucDanh implements Serializable {
	private String maChucDanh;
	private String tenChucDanh;
	private String ghiChu;
	
	
	//---constructors---
	public ChucDanh() {
		
	}
	
	
	public ChucDanh (String maChucDanh) {
		this.maChucDanh = maChucDanh;
	}
	
	
	public ChucDanh (String maChucDanh, String tenChucDanh) {
		this.maChucDanh = maChucDanh;
		this.tenChucDanh = tenChucDanh;
	}
	
	
	public ChucDanh (String maChucDanh, String tenChucDanh, String ghiChu) {
		this.maChucDanh = maChucDanh;
		this.tenChucDanh = tenChucDanh;
		this.ghiChu = ghiChu;
	}


	//---getters/setters---
	public String getMaChucDanh() {
		return maChucDanh;
	}


	public void setMaChucDanh(String maChucDanh) {
		this.maChucDanh = maChucDanh;
	}


	public String getTenChucDanh() {
		return tenChucDanh;
	}


	public void setTenChucDanh(String tenChucDanh) {
		this.tenChucDanh = tenChucDanh;
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
		return Objects.hash(maChucDanh);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChucDanh other = (ChucDanh) obj;
		return Objects.equals(maChucDanh, other.maChucDanh);
	}
	
}

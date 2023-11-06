package entity;


import java.io.Serializable;
import java.util.Objects;


public class ChucDanh implements Serializable {
	private String ID;
	private String tenChucDanh;
	private String ghiChu;
	
	
	//---constructors---
	public ChucDanh() {
		
	}
	
	
	public ChucDanh (String ID) {
		this.ID = ID;
	}
	
	
	public ChucDanh (String ID, String tenChucDanh) {
		this.ID = ID;
		this.tenChucDanh = tenChucDanh;
	}
	
	
	public ChucDanh (String ID, String tenChucDanh, String ghiChu) {
		this.ID = ID;
		this.tenChucDanh = tenChucDanh;
		this.ghiChu = ghiChu;
	}


	//---getters/setters---
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
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
		return Objects.hash(ID);
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
		return Objects.equals(ID, other.ID);
	}
	
}

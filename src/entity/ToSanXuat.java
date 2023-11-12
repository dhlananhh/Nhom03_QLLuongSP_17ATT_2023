package entity;

import java.util.Objects;

public class ToSanXuat {
	private int maToSX;
	private String moTa;
	private int soLuongCN;
	
	
	public ToSanXuat() {
		
	}

	
	public ToSanXuat(int maToSX) {
		this.maToSX = maToSX;
	}


	public ToSanXuat(int maToSX, String moTa, int soLuongCN) {
		this.maToSX = maToSX;
		this.moTa = moTa;
		this.soLuongCN = soLuongCN;
	}


	public int getMaToSX() {
		return maToSX;
	}


	public void setMaToSX(int maToSX) {
		this.maToSX = maToSX;
	}


	public String getMoTa() {
		return moTa;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public int getSoLuongCN() {
		return soLuongCN;
	}


	public void setSoLuongCN(int soLuongCN) {
		this.soLuongCN = soLuongCN;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maToSX);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToSanXuat other = (ToSanXuat) obj;
		return maToSX == other.maToSX;
	}

}

package entity;

import java.util.Objects;

public class CongNhanSanXuat {
	private String maCN;
	private String hoTenCN;
	private String phongBan;
	private int dinhMucSanPham;
	private int soSanPham;
	private double donGiaSanPham;
	private double luongSanPham;
	private double luongPhuCap;
	private double thuong;
	private double phat;
	private double khauTru;
	private double thueTNCN;
	private double tamUng;
	private double luongThucLanh;
	
	
	public CongNhanSanXuat() {
		
	}
	
	
	public CongNhanSanXuat (String maCN) {
		this.maCN = maCN;
	}
	
	
	public CongNhanSanXuat (String maCN, String hoTenCN) {
		this.maCN = maCN;
		this.hoTenCN = hoTenCN;
	}


	public CongNhanSanXuat(String maCN, String hoTenCN, String phongBan, int dinhMucSanPham, int soSanPham,
			double donGiaSanPham, double luongSanPham, double luongPhuCap, double thuong, double phat, double khauTru,
			double thueTNCN, double tamUng, double luongThucLanh) {
		super();
		this.maCN = maCN;
		this.hoTenCN = hoTenCN;
		this.phongBan = phongBan;
		this.dinhMucSanPham = dinhMucSanPham;
		this.soSanPham = soSanPham;
		this.donGiaSanPham = donGiaSanPham;
		this.luongSanPham = luongSanPham;
		this.luongPhuCap = luongPhuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.khauTru = khauTru;
		this.thueTNCN = thueTNCN;
		this.tamUng = tamUng;
		this.luongThucLanh = luongThucLanh;
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

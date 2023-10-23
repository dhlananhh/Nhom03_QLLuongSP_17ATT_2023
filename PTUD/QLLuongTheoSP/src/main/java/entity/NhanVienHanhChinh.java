package entity;

import java.util.Objects;

public class NhanVienHanhChinh {
	private String maNV;
	private String hoTenNV;
	private String phongBan;
	private String chucVu;
	private double luongCung;
	private int ngayCongQuyDinh;
	private int ngayCongThucTe;
	private double thuong;
	private double phat;
	private double luongPhuCap;
	private double khauTru;
	private double thueTNCN;
	private double tamUng;
	private double luongThucLanh;
	
	
	public NhanVienHanhChinh() {
		
	}
	
	
	public NhanVienHanhChinh (String maNV) {
		this.maNV = maNV;
	}
	
	
	public NhanVienHanhChinh (String maNV, String hoTenNV) {
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}

	
	public NhanVienHanhChinh(String maNV, String hoTenNV, String phongBan, String chucVu, double luongCung,
			int ngayCongQuyDinh, int ngayCongThucTe, double thuong, double phat, double luongPhuCap, double khauTru,
			double thueTNCN, double tamUng, double luongThucLanh) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.phongBan = phongBan;
		this.chucVu = chucVu;
		this.luongCung = luongCung;
		this.ngayCongQuyDinh = ngayCongQuyDinh;
		this.ngayCongThucTe = ngayCongThucTe;
		this.thuong = thuong;
		this.phat = phat;
		this.luongPhuCap = luongPhuCap;
		this.khauTru = khauTru;
		this.thueTNCN = thueTNCN;
		this.tamUng = tamUng;
		this.luongThucLanh = luongThucLanh;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVienHanhChinh other = (NhanVienHanhChinh) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
	
	
}

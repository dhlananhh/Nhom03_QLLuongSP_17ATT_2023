package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class LuongCongNhanSanXuat implements Serializable {
	private String maBangLuongCN;
	private Date ngayTinhLuong;
	private int thang;
	private int nam;
	private double luongSanPham;
	private double tamUng;
	private double baoHiemXaHoi;
	private double baoHiemYTe;
	private double baoHiemThatNghiep;
	private double thueTNCN;
	private double luongThucLanh;
	private CongNhanSanXuat congNhan;
	private ChamCong chamCong;
	private CongDoan congDoan;
	
	
	//---constructors---
	public LuongCongNhanSanXuat() {
		
	}
	public LuongCongNhanSanXuat(String maBangLuongCN) {
		this.maBangLuongCN = maBangLuongCN;
	}
	
	public LuongCongNhanSanXuat(String maBangLuongCN, double tamUng) {
		super();
		this.maBangLuongCN = maBangLuongCN;
		this.tamUng = tamUng;
	}
	public LuongCongNhanSanXuat(String maBangLuongCN, Date ngayTinhLuong, int thang, int nam, double luongSanPham,
			double tamUng, double baoHiemXaHoi, double baoHiemYTe, double baoHiemThatNghiep, double thueTNCN,
			double luongThucLanh, CongNhanSanXuat congNhan) {
		super();
		this.maBangLuongCN = maBangLuongCN;
		this.ngayTinhLuong = ngayTinhLuong;
		this.thang = thang;
		this.nam = nam;
		this.luongSanPham = luongSanPham;
		this.tamUng = tamUng;
		this.baoHiemXaHoi = baoHiemXaHoi;
		this.baoHiemYTe = baoHiemYTe;
		this.baoHiemThatNghiep = baoHiemThatNghiep;
		this.thueTNCN = thueTNCN;
		this.luongThucLanh = luongThucLanh;
		this.congNhan = congNhan;
	}
	
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setSanPham(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	public String getMaBangLuongCN() {
		return maBangLuongCN;
	}
	public void setMaBangLuongCN(String maBangLuongCN) {
		this.maBangLuongCN = maBangLuongCN;
	}
	public Date getNgayTinhLuong() {
		return ngayTinhLuong;
	}
	public void setNgayTinhLuong(Date ngayTinhLuong) {
		this.ngayTinhLuong = ngayTinhLuong;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public double getLuongSanPham() {
		return luongSanPham;
	}
	public void setLuongSanPham(double luongSanPham) {
		this.luongSanPham = getChamCong().getSoLuongHoanThanh()*getCongDoan().getLuongTheoSanPham();
	}
	public double getTamUng() {
		return tamUng;
	}
	public void setTamUng(double tamUng) {
		this.tamUng = tamUng;
	}
	public double getBaoHiemXaHoi() {
		return baoHiemXaHoi;
	}
	public void setBaoHiemXaHoi(double baoHiemXaHoi) {
		this.baoHiemXaHoi = getLuongSanPham()* 0.08;
	}
	public double getBaoHiemYTe() {
		return baoHiemYTe;
	}
	public void setBaoHiemYTe(double baoHiemYTe) {
		this.baoHiemYTe = getLuongSanPham()* 0.015;
	}
	public double getBaoHiemThatNghiep() {
		return baoHiemThatNghiep;
	}
	public void setBaoHiemThatNghiep(double baoHiemThatNghiep) {
		this.baoHiemThatNghiep = getLuongSanPham()* 0.01;
	}
	public double getThueTNCN() {
		return thueTNCN;
	}
	public double tinhThueSuat (double thuNhapTinhThue) {
		double thueSuat = 0;
		
		// bậc 1
		if (thuNhapTinhThue < 5000) 
			thueSuat = 0.05;
		// bậc 2
		else if (thuNhapTinhThue >= 5000 && thuNhapTinhThue <= 10000)
			thueSuat = 0.1;
		// bậc 3
		else if (thuNhapTinhThue >= 10000 && thuNhapTinhThue <= 18000)
			thueSuat = 0.15;
		// bậc 4
		else if (thuNhapTinhThue >= 18000 && thuNhapTinhThue <= 32000)
			thueSuat = 0.2;
		// bậc 5
		else if (thuNhapTinhThue >= 32000 && thuNhapTinhThue <= 52000)
			thueSuat = 0.25;
		// bậc 6
		else if (thuNhapTinhThue >= 52000 && thuNhapTinhThue <= 80000)
			thueSuat = 0.3;
		// bậc 7
		else if (thuNhapTinhThue >= 80000)
			thueSuat = 0.35;
		
		return thueSuat;
	}
	
	
	public int tinhBacThue (double thuNhapTinhThue) {
		int bacThue = 0;
		
		if (tinhThueSuat(thuNhapTinhThue) == 0.05)
			bacThue = 1;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.1)
			bacThue = 2;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.15)
			bacThue = 3;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.2)
			bacThue = 4;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.25)
			bacThue = 5;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.3)
			bacThue = 6;
		else if (tinhThueSuat(thuNhapTinhThue) == 0.35)
			bacThue = 7;
		
		return bacThue;
	}
	
	
	public double tinhThueTNCN() {
		// tính thuế thu nhập thực tế khi đã trừ tiền bảo hiểm bắt buộc
		double thuNhapThucTe = getLuongSanPham() - getBaoHiemXaHoi() - getBaoHiemYTe() - getBaoHiemThatNghiep();
		double tienGiamTruBanThan = 11000;
		double thuNhapTinhThue = thuNhapThucTe - tienGiamTruBanThan;
		double thueSuat = tinhThueSuat(thuNhapTinhThue);
		int bacThue = tinhBacThue(thuNhapTinhThue);
		double thuePhaiDong = 0;
		
		
		if (bacThue == 1)
			thuePhaiDong = 5000 * 0.05;
		else if (bacThue == 2)
			thuePhaiDong = thuNhapTinhThue * 0.1 - 250;
		else if (bacThue == 3)
			thuePhaiDong = thuNhapTinhThue * 0.15 - 750;
		else if (bacThue == 4)
			thuePhaiDong = thuNhapTinhThue * 0.2 - 1650;
		else if (bacThue == 5)
			thuePhaiDong = thuNhapTinhThue * 0.25 - 3250;
		else if (bacThue == 6)
			thuePhaiDong = thuNhapTinhThue * 0.3 - 5850;
		else if (bacThue == 7)
			thuePhaiDong = thuNhapTinhThue * 0.35 - 9850;
		
		return thuePhaiDong;
	}
	public void setThueTNCN(double thueTNCN) {
		this.thueTNCN = tinhThueTNCN();
	}
	public double getLuongThucLanh() {
		return luongThucLanh;
	}
	public void setLuongThucLanh(double luongThucLanh) {
		this.luongThucLanh = getLuongSanPham() - getTamUng() - getBaoHiemXaHoi() - getBaoHiemYTe() - getBaoHiemThatNghiep() - getThueTNCN();
	}
	public CongNhanSanXuat getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhanSanXuat congNhan) {
		this.congNhan = congNhan;
	}
	public ChamCong getChamCong() {
		return chamCong;
	}
	public void setChamCong(ChamCong chamCong) {
		this.chamCong = chamCong;
	}
	public double getCacKhoanTruVaoLuongNV() {
		double giamTru = 0;
		giamTru = getBaoHiemXaHoi() + getBaoHiemYTe() + getBaoHiemThatNghiep() + tinhThueTNCN();
		return giamTru;
	}
	@Override
	public String toString() {
		return "LuongCongNhanSanXuat [maBangLuongCN=" + maBangLuongCN + ", ngayTinhLuong=" + ngayTinhLuong + ", thang="
				+ thang + ", nam=" + nam + ", luongSanPham=" + luongSanPham + ", tamUng=" + tamUng + ", baoHiemXaHoi="
				+ baoHiemXaHoi + ", baoHiemYTe=" + baoHiemYTe + ", baoHiemThatNghiep=" + baoHiemThatNghiep
				+ ", thueTNCN=" + thueTNCN + ", luongThucLanh=" + luongThucLanh + ", congNhan=" + congNhan
				+ ", chamCong=" + chamCong + "]";
	}
	
}

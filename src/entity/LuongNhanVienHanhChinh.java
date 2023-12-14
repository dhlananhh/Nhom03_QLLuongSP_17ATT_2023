package entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Objects;

import dao.DAO_LuongNhanVienHanhChinh;

public class LuongNhanVienHanhChinh {
	private DAO_LuongNhanVienHanhChinh dao_luongNV = new DAO_LuongNhanVienHanhChinh();
	private int soNgayDiLam = dao_luongNV.laySoNgayDiLam(getMaBangLuongHC(), getNam(), getThang());
	
	private String maBangLuongHC;
	private Date ngayTinhLuong;
	private int nam;
	private int thang;
	// lương chính = (lương CB * hệ số lương) / 26 * số ngày đi làm
	private double luongChinh;
	private double tienTamUng;
	private double baoHiemXaHoi;
	private double baoHiemYTe;
	private double baoHiemThatNghiep;
	private double thueTNCN;
	// lương thực lãnh = lương chính - tiền tạm ứng - bhxh - bhyt - bhtn - thuế TNCN
	private double luongThucLanh;
	private NhanVienHanhChinh nhanVien;
	
	
	public LuongNhanVienHanhChinh() {
		
	}


	public LuongNhanVienHanhChinh(String maBangLuongHC) {
		this.maBangLuongHC = maBangLuongHC;
	}
	
	
	public LuongNhanVienHanhChinh (String maBangLuongHC, double tienTamUng) {
		this.maBangLuongHC = maBangLuongHC;
		this.tienTamUng = tienTamUng;
	}


	public LuongNhanVienHanhChinh (String maBangLuongHC, 
			Date ngayTinhLuong, int nam, int thang, double tienTamUng, 
			NhanVienHanhChinh nhanVien) {
		this.maBangLuongHC = maBangLuongHC;
		this.ngayTinhLuong = ngayTinhLuong;
		this.nam = nam;
		this.thang = thang;
		this.tienTamUng = tienTamUng;
		this.nhanVien = nhanVien;
		
		setLuongChinh();
		setBaoHiemXaHoi();
		setBaoHiemYTe();
		setBaoHiemThatNghiep();
		setThueTNCN();
		setLuongThucLanh();
	}
	

	public LuongNhanVienHanhChinh(String maBangLuongHC, Date ngayTinhLuong, int nam, int thang, 
			double luongChinh, double tienTamUng,
			double baoHiemXaHoi, double baoHiemYTe, double baoHiemThatNghiep, double thueTNCN, 
			double luongThucLanh, NhanVienHanhChinh nhanVien) {
		this.maBangLuongHC = maBangLuongHC;
		this.ngayTinhLuong = ngayTinhLuong;
		this.nam = nam;
		this.thang = thang;
		this.luongChinh = luongChinh;
		this.tienTamUng = tienTamUng;
		this.baoHiemXaHoi = baoHiemXaHoi;
		this.baoHiemYTe = baoHiemYTe;
		this.baoHiemThatNghiep = baoHiemThatNghiep;
		this.thueTNCN = thueTNCN;
		this.luongThucLanh = luongThucLanh;
		this.nhanVien = nhanVien;
	}


	public String getMaBangLuongHC() {
		return maBangLuongHC;
	}


	public void setMaBangLuongHC(String maBangLuongHC) {
		this.maBangLuongHC = maBangLuongHC;
	}

	
	public Date getNgayTinhLuong() {
		return ngayTinhLuong;
	}


	public void setNgayTinhLuong(Date ngayTinhLuong) {
		this.ngayTinhLuong = ngayTinhLuong;
	}


	public int getNam() {
		return nam;
	}


	public void setNam(int nam) {
		this.nam = nam;
	}


	public int getThang() {
		return thang;
	}


	public void setThang(int thang) {
		this.thang = thang;
	}



	public double getLuongChinh() {
		return luongChinh;
	}


	public void setLuongChinh(){
		this.luongChinh = (getNhanVien().getLuongCoBan() * getNhanVien().getHeSoLuong()) / 26 * soNgayDiLam;
	}


	public double getTienTamUng() {
		return tienTamUng;
	}


	public void setTienTamUng(double tienTamUng) {
		this.tienTamUng = tienTamUng;
	}


	public double getBaoHiemXaHoi() {
		return baoHiemXaHoi;
	}


	public void setBaoHiemXaHoi() {
		this.baoHiemXaHoi = getLuongChinh() * 0.08;
	}

	
	public double getBaoHiemYTe() {
		return baoHiemYTe;
	}


	public void setBaoHiemYTe() {
		this.baoHiemYTe = getLuongChinh() * 0.015;
	}


	public double getBaoHiemThatNghiep() {
		return baoHiemThatNghiep;
	}


	public void setBaoHiemThatNghiep() {
		this.baoHiemThatNghiep = getLuongChinh() * 0.01;
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
	
	
	// tính thuế TNCN
	public double tinhThueTNCN() {
		// tính thuế thu nhập thực tế khi đã trừ tiền bảo hiểm bắt buộc
		double thuNhapThucTe = getLuongChinh() - getBaoHiemXaHoi() - getBaoHiemYTe() - getBaoHiemThatNghiep();
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


	public void setThueTNCN() {
		this.thueTNCN = tinhThueTNCN();
	}
	
	
	public double getLuongThucLanh() {
		return luongThucLanh;
	}


	public void setLuongThucLanh() {
		this.luongThucLanh = getLuongChinh() - getTienTamUng() - getBaoHiemXaHoi() - getBaoHiemYTe() - getBaoHiemThatNghiep() - getThueTNCN();
	}


	public NhanVienHanhChinh getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(NhanVienHanhChinh nhanVien) throws Exception {
		if (nhanVien == null)
			throw new Exception("Nhân viên không được để trống");
		else
			this.nhanVien = nhanVien;
	}
	
	
	public double getCacKhoanTruVaoLuongNV() {
		double giamTru = 0;
		giamTru = getBaoHiemXaHoi() + getBaoHiemYTe() + getBaoHiemThatNghiep() + tinhThueTNCN();
		return giamTru;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maBangLuongHC);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongNhanVienHanhChinh other = (LuongNhanVienHanhChinh) obj;
		return Objects.equals(maBangLuongHC, other.maBangLuongHC);
	}


	@Override
	public String toString() {
		return "LuongNhanVienHanhChinh [maBangLuongHC=" + maBangLuongHC + ", ngayTinhLuong=" + ngayTinhLuong + ", nam="
				+ nam + ", thang=" + thang + ", luongChinh=" + luongChinh + ", tienTamUng=" + tienTamUng
				+ ", baoHiemXaHoi=" + baoHiemXaHoi + ", baoHiemYTe=" + baoHiemYTe + ", baoHiemThatNghiep="
				+ baoHiemThatNghiep + ", thueTNCN=" + thueTNCN + ", luongThucLanh=" + luongThucLanh + ", nhanVien="
				+ nhanVien + "]";
	}

}

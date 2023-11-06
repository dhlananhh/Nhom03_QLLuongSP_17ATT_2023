package entity;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int soLuongTon;
	private double giaThanh;
	private String trangThai;
	public SanPham() {
		super();
	}
	public SanPham(String maSP) {
		this.maSP = maSP;
	}
	public SanPham(String maSP, String tenSP, int soLuongTon, double giaThanh, String trangThai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuongTon = soLuongTon;
		this.giaThanh = giaThanh;
		this.trangThai = trangThai;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public double getGiaThanh() {
		return giaThanh;
	}
	public void setGiaThanh(double giaThanh) {
		this.giaThanh = giaThanh;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuongTon=" + soLuongTon + ", giaThanh=" + giaThanh
				+ ", trangThai=" + trangThai + "]";
	}
	
}

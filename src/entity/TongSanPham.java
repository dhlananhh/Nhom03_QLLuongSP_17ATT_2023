package entity;

public class TongSanPham {
	private String maSP;
	private String tenSP;
	private int tongSoLuong;
	public TongSanPham(String maSP, String tenSP, int tongSoLuong) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.tongSoLuong = tongSoLuong;
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
	public int getTongSoLuong() {
		return tongSoLuong;
	}
	public void setTongSoLuong(int tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}
	

}

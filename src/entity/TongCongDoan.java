package entity;

public class TongCongDoan {
	private int nam;
	private int thang;
	private String maCD;
	private int soLuongHoanThanh;
	
	public TongCongDoan(int nam, int thang, int soLuongHoanThanh) {
		this.nam = nam;
		this.thang = thang;
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	public TongCongDoan(int nam, int thang, String maCD, int soLuongHoanThanh) {
		this.nam = nam;
		this.thang = thang;
		this.maCD = maCD;
		this.soLuongHoanThanh = soLuongHoanThanh;
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
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public int getSoLuongHoanThanh() {
		return soLuongHoanThanh;
	}
	public void setSoLuongHoanThanh(int soLuongHoanThanh) {
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	

}

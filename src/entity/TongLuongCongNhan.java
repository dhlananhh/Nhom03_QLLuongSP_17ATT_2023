package entity;

public class TongLuongCongNhan {

    private String maCongNhan;
    private String hoTenCongNhan;
    private String maToSanXuat;
    private String moTaToSanXuat;
    private int nam;
    private double tongLuong;

    public TongLuongCongNhan(String maCongNhan, String hoTenCongNhan, String maToSanXuat, String moTaToSanXuat, int nam, double tongLuong) {
        this.maCongNhan = maCongNhan;
        this.hoTenCongNhan = hoTenCongNhan;
        this.maToSanXuat = maToSanXuat;
        this.moTaToSanXuat = moTaToSanXuat;
        this.nam = nam;
        this.tongLuong = tongLuong;
    }

	public String getMaCongNhan() {
		return maCongNhan;
	}

	public void setMaCongNhan(String maCongNhan) {
		this.maCongNhan = maCongNhan;
	}

	public String getHoTenCongNhan() {
		return hoTenCongNhan;
	}

	public void setHoTenCongNhan(String hoTenCongNhan) {
		this.hoTenCongNhan = hoTenCongNhan;
	}

	public String getMaToSanXuat() {
		return maToSanXuat;
	}

	public void setMaToSanXuat(String maToSanXuat) {
		this.maToSanXuat = maToSanXuat;
	}

	public String getMoTaToSanXuat() {
		return moTaToSanXuat;
	}

	public void setMoTaToSanXuat(String moTaToSanXuat) {
		this.moTaToSanXuat = moTaToSanXuat;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public double getTongLuong() {
		return tongLuong;
	}

	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}
    
}

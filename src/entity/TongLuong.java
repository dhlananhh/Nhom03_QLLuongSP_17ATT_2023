package entity;

public class TongLuong {
    private int nam;
    private int thang;
    private double tongLuong;

    public TongLuong(int nam, int thang, double tongLuong) {
        this.nam = nam;
        this.thang = thang;
        this.tongLuong = tongLuong;
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

	public double getTongLuong() {
		return tongLuong;
	}

	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}
    
}

package entity;

public class TongLuongNhanVien {
    private String maNhanVien;
    private String hoTenNhanVien;
    private String tenPhongBan;
    private double tongLuong;


    public TongLuongNhanVien(String maNhanVien, String hoTenNhanVien, String tenPhongBan, double tongLuong) {
        this.maNhanVien = maNhanVien;
        this.hoTenNhanVien = hoTenNhanVien;
        this.tenPhongBan = tenPhongBan;
        this.tongLuong = tongLuong;
    }


    public TongLuongNhanVien(double tongLuong) {
        this.tongLuong = tongLuong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }


    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(double tongLuong) {
        this.tongLuong = tongLuong;
    }
}

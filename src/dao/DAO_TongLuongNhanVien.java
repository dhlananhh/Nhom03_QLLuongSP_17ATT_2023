package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import connection.ConnectDB;
import entity.TongLuong;
import entity.TongLuongNhanVien;

public class DAO_TongLuongNhanVien {

    public ArrayList<TongLuongNhanVien> getTongLuongNhanVien(int nam) {
        ArrayList<TongLuongNhanVien> resultList = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String query = "SELECT NV.maNV AS MaNhanVien, NV.hoTenNV AS HoTenNhanVien, NV.maPhongBan AS MaPhongBan, " +
                "PB.tenPhongBan AS TenPhongBan, LCB.nam AS Nam, SUM(LCB.luongThucLanh) AS TongLuong " +
                "FROM dbo.NhanVienHanhChinh NV " +
                "JOIN dbo.LuongNhanVienHanhChinh LCB ON NV.maNV = LCB.maNV " +
                "JOIN dbo.PhongBan PB ON NV.maPhongBan = PB.maPhongBan " +
                "WHERE LCB.nam = ? " +
                "GROUP BY NV.maNV, NV.hoTenNV, NV.maPhongBan, PB.tenPhongBan, LCB.nam";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, nam);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String maNhanVien = resultSet.getString("MaNhanVien");
                    String hoTenNhanVien = resultSet.getString("HoTenNhanVien");
                    String tenPhongBan = resultSet.getString("TenPhongBan");
                    double tongLuong = resultSet.getDouble("TongLuong");

                    TongLuongNhanVien tongLuongNhanVien = new TongLuongNhanVien(maNhanVien, hoTenNhanVien,
                            tenPhongBan, tongLuong);
                    resultList.add(tongLuongNhanVien);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<TongLuong> getTongLuongCongTy(int nam) {
        ArrayList<TongLuong> resultList = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();

        String query = "SELECT LCB.nam AS Nam, LCB.thang AS Thang, SUM(LCB.luongThucLanh) AS TongLuongCongTy " +
                "FROM dbo.LuongNhanVienHanhChinh LCB " +
                "WHERE LCB.nam = ? " +
                "GROUP BY LCB.nam, LCB.thang " +
                "ORDER BY LCB.nam, LCB.thang";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, nam);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int thang = resultSet.getInt("Thang");
                    double tongLuongCongTy = resultSet.getDouble("TongLuongCongTy");

                    TongLuong tongLuongNhanVien = new TongLuong(nam, thang, tongLuongCongTy);
                    resultList.add(tongLuongNhanVien);
                }
            }
            for (int thang = 1; thang <= 12; thang++) {
                boolean found = false;
                for (TongLuong tongLuong : resultList) {
                    if (tongLuong.getNam() == nam && tongLuong.getThang() == thang) {
                    	resultList.add(tongLuong);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                	resultList.add(new TongLuong(nam, thang, 0));
                }
            }
            Collections.sort(resultList, new Comparator<TongLuong>() {
                @Override
                public int compare(TongLuong o1, TongLuong o2) {
                    return Integer.compare(o1.getThang(), o2.getThang());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}

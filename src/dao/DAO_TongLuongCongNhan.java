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
import entity.CongNhanSanXuat;
import entity.ToSanXuat;
import entity.TongLuong;
import entity.TongLuongCongNhan;

public class DAO_TongLuongCongNhan {
	
	public ArrayList<TongLuongCongNhan> getDanhSachTopCongNhan(int nam) {
		ArrayList<TongLuongCongNhan> resultList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String query = "SELECT CNSX.maCN AS MaCongNhan, CNSX.hoTenCN AS HoTenCongNhan, CNSX.maToSX AS MaToSanXuat, TSX.moTa AS MoTaToSanXuat, "+
						"LCB.nam AS Nam, SUM(LCB.luongThucLanh) AS TongLuong "+ 
						"FROM dbo.CongNhanSanXuat CNSX "+ 
						"JOIN dbo.LuongCongNhanSanXuat LCB ON CNSX.maCN = LCB.maCN "+ 
						"JOIN dbo.ToSanXuat TSX ON CNSX.maToSX = TSX.maToSX "+ 
						"WHERE LCB.nam = ? "+ 
						"GROUP BY CNSX.maCN, CNSX.hoTenCN, CNSX.maToSX, TSX.moTa, LCB.nam";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, nam);
            	ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String maCongNhan = rs.getString("MaCongNhan");
                    String hoTenCongNhan = rs.getString("HoTenCongNhan");
                    String maToSanXuat = rs.getString("MaToSanXuat");
                    String moTaToSanXuat = rs.getString("MoTaToSanXuat");
                    double tongLuong = rs.getDouble("TongLuong");

                    TongLuongCongNhan tongLuongCongNhan = new TongLuongCongNhan(maCongNhan, hoTenCongNhan, maToSanXuat, moTaToSanXuat, nam, tongLuong);
                    resultList.add(tongLuongCongNhan);
                }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
		
	}
	

    public ArrayList<TongLuong> getTongLuongCongNhan(int nam) {
        ArrayList<TongLuong> resultList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
        String query = "SELECT LCB.nam AS Nam, LCB.thang AS Thang, SUM(LCB.luongThucLanh) AS TongLuongCongTy " +
                       "FROM dbo.LuongCongNhanSanXuat LCB " +
                       "WHERE LCB.nam = ? " +
                       "GROUP BY LCB.nam, LCB.thang " +
                       "ORDER BY LCB.nam, LCB.thang";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, nam);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int thang = resultSet.getInt("Thang");
                    double tongLuongCongTy = resultSet.getDouble("TongLuongCongTy");

                    TongLuong tongLuong = new TongLuong(nam, thang, tongLuongCongTy);
                    resultList.add(tongLuong);	
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

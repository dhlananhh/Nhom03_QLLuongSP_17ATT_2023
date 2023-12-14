package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import connection.ConnectDB;
import entity.SanPham;
import entity.TongCongDoan;
import entity.TongLuong;
import entity.TongSanPham;

public class DAO_TongSanPham {
	
    public ArrayList<TongCongDoan> getSoLuongSanPhamHoanThanh(int nam) {
        ArrayList<TongCongDoan> finalList = new ArrayList<>();        
        for (int thang = 1; thang <= 12; thang++) {
        	finalList.add(new TongCongDoan(nam, thang, 0));
        }
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DAO_CongDoan dao_cd = new DAO_CongDoan();
		DAO_SanPham dao_sp = new DAO_SanPham();
		
        String query = "select year(NgayCham) as nam, month(NgayCham) as thang, maCD, sum(soLuongHoanThanh) as tongSoLuongHoanThanh from ChamCong "
        		+ "where year(NgayCham) = ? and soLuongHoanThanh > 0 group by maCD, soLuongHoanThanh, year(NgayCham),  month(NgayCham) "
        		+ "ORDER BY  year(NgayCham), month(NgayCham)";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, nam);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int thang = resultSet.getInt("thang");
                    String maCD = resultSet.getString("maCD");
                    int tongCongDoan = resultSet.getInt("tongSoLuongHoanThanh");
                    SanPham SP = dao_sp.getalltbSanPhamTheoMaCD(maCD).get(0);
                    String maSP = SP.getMaSP();
                    int slcd = dao_cd.getAllCongDoanTheoSP(maSP).size();
                    
                    if(dao_cd.getCongDoanTheoMa(maCD).getThuTu()==slcd) {
                        for (TongCongDoan tcd : finalList) {
    						if(tcd.getThang() == thang) {
    							tcd.setSoLuongHoanThanh(tcd.getSoLuongHoanThanh()+tongCongDoan);
    						}
    					}
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return finalList;
    }
    
    public ArrayList<TongSanPham> getTopSanPham(int nam) {
        ArrayList<TongSanPham> listSanPham = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DAO_CongDoan dao_cd = new DAO_CongDoan();
		DAO_SanPham dao_sp = new DAO_SanPham();
		
		String query = "SELECT maCD, SUM(soLuongHoanThanh) AS tongSoLuongHoanThanh " +
	               "FROM ChamCong " +
	               "WHERE YEAR(NgayCham) = ? AND soLuongHoanThanh > 0 " +
	               "GROUP BY maCD " +
	               "ORDER BY maCD";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, nam);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String maCD = resultSet.getString("maCD");
                    int tongCongDoan = resultSet.getInt("tongSoLuongHoanThanh");
                    SanPham SP = dao_sp.getalltbSanPhamTheoMaCD(maCD).get(0);
                    String maSP = SP.getMaSP();
                    int slcd = dao_cd.getAllCongDoanTheoSP(maSP).size();
                    boolean found = false;
                    if(dao_cd.getCongDoanTheoMa(maCD).getThuTu()==slcd) { 
                    	listSanPham.add(new TongSanPham(maSP, SP.getTenSP(), tongCongDoan));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSanPham;
    }

}

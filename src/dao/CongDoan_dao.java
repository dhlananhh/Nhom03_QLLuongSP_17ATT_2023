package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.CongDoan;
import entity.SanPham;

public class CongDoan_dao {
	public ArrayList<CongDoan> getalltbCongDoan(){
		ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getCon();
			String sql = "Select * from CongDoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maCD = rs.getString("maCD");
				String tenCD = rs.getString("tenCD");
				double luongTheoSP = rs.getDouble("luongTheoSanPham");
				SanPham sp = new SanPham(rs.getString("maSP"));
				int thuTu = rs.getInt("thuTu");
				CongDoan cd = new CongDoan(maCD, tenCD, luongTheoSP, sp, thuTu);
				dsCongDoan.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsCongDoan;
	}
}

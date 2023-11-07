package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean themCD(CongDoan cd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into CongDoan values(?, ?, ?, ?, ?)");
			statement.setString(1, cd.getMaCD());
			statement.setString(2, cd.getTenCD());
			statement.setDouble(3, cd.getLuongTheoSanPham());
			statement.setString(4, cd.getSp().getMaSP());
			statement.setInt(5, cd.getThuTu());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}

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

public class SanPham_dao {
	public ArrayList<SanPham> getalltbSanPham(){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getCon();
			String sql = "Select * from SanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				int soLuongTon = rs.getInt("soLuongTon");
				double giaThanh = rs.getDouble("giaThanh");
				String trangThai = rs.getString("trangThai");
				SanPham sp = new SanPham(maSP, tenSP, soLuongTon, giaThanh, trangThai);
				dsSanPham.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
	public boolean themSP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into SanPham values(?, ?, ?, ?, ?)");
			statement.setString(1, sp.getMaSP());
			statement.setString(2, sp.getTenSP());
			statement.setInt(3, sp.getSoLuongTon());
			statement.setDouble(4, sp.getGiaThanh());
			statement.setString(5, sp.getTrangThai());
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

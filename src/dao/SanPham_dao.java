package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
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
				boolean trangThai = rs.getBoolean("trangThai");
				SanPham sp = new SanPham(maSP, tenSP, soLuongTon, giaThanh, trangThai);
				dsSanPham.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
}

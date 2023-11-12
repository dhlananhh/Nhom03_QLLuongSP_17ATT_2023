package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;

public class DAO_LuongNhanVienHanhChinh {
	private static DAO_NhanVienHanhChinh instance = new DAO_NhanVienHanhChinh();
	
	
	public static DAO_NhanVienHanhChinh getInstance() {
		if (instance == null)
			instance = new DAO_NhanVienHanhChinh();
		return instance;
	}
	
	
	public List<LuongNhanVienHanhChinh> getDanhSachLuongNhanVien() {
		List<LuongNhanVienHanhChinh> dsLuongNhanVien = new ArrayList<LuongNhanVienHanhChinh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "select * from LuongNhanVienHanhChinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String ma = rs.getString(1);
				int nam = rs.getInt(2);
				int thang = rs.getInt(3);
				boolean trangThai = rs.getBoolean(4);
				Object nhanVien = rs.getObject(5);
				float heSoLuong = rs.getFloat(6);
				float luongCoBan = rs.getFloat(7);
				float phuCap = rs.getFloat(8);
				float giamTru = rs.getFloat(9);
				float tamUng = rs.getFloat(10);
				LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh(ma, nam, thang, trangThai, 
						new NhanVienHanhChinh(), heSoLuong, luongCoBan, phuCap, giamTru, tamUng);
				dsLuongNhanVien.add(luongNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNhanVien;
	}
}

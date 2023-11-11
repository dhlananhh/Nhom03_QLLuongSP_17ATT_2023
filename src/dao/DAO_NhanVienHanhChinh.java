package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.ChucDanh;
import entity.NhanVienHanhChinh;
import entity.PhongBan;
import entity.TaiKhoan;
import connection.*;


public class DAO_NhanVienHanhChinh {
//	private static DAO_NhanVienHanhChinh instance = new DAO_NhanVienHanhChinh();
//	
//	
//	public static DAO_NhanVienHanhChinh getInstance() {
//		if (instance == null)
//			instance = new DAO_NhanVienHanhChinh();
//		return instance;
//	}
	
	
	public List<NhanVienHanhChinh> getDanhSachNhanVien() {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		
		try {
			
			Connection con = ConnectDB.getInstance().getConnection();
			
			
			String sql = "select * from NhanVienHanhChinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String CCCD = rs.getString(6);
				String BHXH = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				String maPhongBan = rs.getString(9);
				String maChucDanh = rs.getString(10);
				String trangThai = rs.getString(11);
				String bangCap = rs.getString(12);
				float luongCoBan = rs.getFloat(13);
				float phuCap = rs.getFloat(14);
				float heSoLuong = rs.getFloat(15);
				String tenTaiKhoan = rs.getString(16);
				NhanVienHanhChinh nv = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, CCCD, BHXH, tenTaiKhoan, ngayVao, new PhongBan(maPhongBan), new ChucDanh(maChucDanh), trangThai, bangCap, luongCoBan, phuCap, heSoLuong, new TaiKhoan(tenTaiKhoan));
				dsNhanVien.add(nv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	private void close (PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

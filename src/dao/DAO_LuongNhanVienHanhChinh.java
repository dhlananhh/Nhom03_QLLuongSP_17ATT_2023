package dao;

import java.util.List;
import java.util.ArrayList;

import connection.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;

import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;


public class DAO_LuongNhanVienHanhChinh {
	public List<LuongNhanVienHanhChinh> getDanhSachLuongNhanVien() {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM LuongNhanVienHanhChinh";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				Date ngayTinhLuong = rs.getDate(2);
				int nam = rs.getInt(3);
				int thang = rs.getInt(4);
				double luongChinh = rs.getDouble(5);
				double tienTamUng = rs.getDouble(6);
				double baoHiemXaHoi = rs.getDouble(7);
				double baoHiemYTe = rs.getDouble(8);
				double baoHiemThatNghiep = rs.getDouble(9);
				double thueTNCN = rs.getDouble(10);
				double luongThucLanh = rs.getDouble(11);
				String maNhanVien = rs.getString(12);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, luongChinh, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
				
				dsLuongNV.add(luongNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNV;
	}
	
	
	public boolean createLuongNhanVien (LuongNhanVienHanhChinh luongNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = "INSERT INTO LuongNhanVienHanhChinh VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, luongNV.getMaBangLuongHC());
			stmt.setDate(2, luongNV.getNgayTinhLuong());
			stmt.setInt(3, luongNV.getNam());
			stmt.setInt(4, luongNV.getThang());
			stmt.setDouble(5, luongNV.getLuongChinh());
			stmt.setDouble(7, luongNV.getTienTamUng());
			stmt.setDouble(8, luongNV.getBaoHiemXaHoi());
			stmt.setDouble(9, luongNV.getBaoHiemYTe());
			stmt.setDouble(10, luongNV.getBaoHiemThatNghiep());
			stmt.setDouble(9, luongNV.getThueTNCN());
			stmt.setDouble(10, luongNV.getLuongThucLanh());
			stmt.setString(11, luongNV.getNhanVien().getMaNV());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n > 0;
	}
	
	
	public boolean updateLuongNhanVien (LuongNhanVienHanhChinh luongNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"UPDATE LuongNhanVienHanhChinh " +
							"SET tienTamUng = ?, luongThucLanh = ? " +
							"WHERE maBangLuongHC = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setDouble(1, luongNV.getTienTamUng());
			stmt.setDouble(2, luongNV.getLuongThucLanh());
			stmt.setString(3, luongNV.getMaBangLuongHC());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	/*
	public boolean deleteLuongNhanVien (String maLuongNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"DELETE FROM LuongNhanVienHanhChinh " +
							"WHERE maBangLuongHC = '" + maLuongNV + "'";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLuongNV);
							
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	*/
	
	
	public List<LuongNhanVienHanhChinh> timLuongTheoMaNV (String maNV) {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"WHERE maNV = '" + maNV + "'";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				Date ngayTinhLuong = rs.getDate(2);
				int nam = rs.getInt(3);
				int thang = rs.getInt(4);
				double luongChinh = rs.getDouble(5);
				double tienTamUng = rs.getDouble(6);
				double baoHiemXaHoi = rs.getDouble(7);
				double baoHiemYTe = rs.getDouble(8);
				double baoHiemThatNghiep = rs.getDouble(9);
				double thueTNCN = rs.getDouble(10);
				double luongThucLanh = rs.getDouble(11);
				String maNhanVien = rs.getString(12);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, luongChinh, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
				
				dsLuongNV.add(luongNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNV;
	}
	
	
	public List<LuongNhanVienHanhChinh> timLuongNVTheoNamThang (int year, int month) {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"WHERE nam = '" + year + "'" +
							" AND thang = '" + month + "'";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				Date ngayTinhLuong = rs.getDate(2);
				int nam = rs.getInt(3);
				int thang = rs.getInt(4);
				double luongChinh = rs.getDouble(5);
				double tienTamUng = rs.getDouble(6);
				double baoHiemXaHoi = rs.getDouble(7);
				double baoHiemYTe = rs.getDouble(8);
				double baoHiemThatNghiep = rs.getDouble(9);
				double thueTNCN = rs.getDouble(10);
				double luongThucLanh = rs.getDouble(11);
				String maNhanVien = rs.getString(12);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, luongChinh, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
				
				dsLuongNV.add(luongNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNV;
	}
	
	
	public List<LuongNhanVienHanhChinh> timLuongNVTheoNam (int year) {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"WHERE nam = '" + year + "'";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				Date ngayTinhLuong = rs.getDate(2);
				int nam = rs.getInt(3);
				int thang = rs.getInt(4);
				double luongChinh = rs.getDouble(5);
				double tienTamUng = rs.getDouble(6);
				double baoHiemXaHoi = rs.getDouble(7);
				double baoHiemYTe = rs.getDouble(8);
				double baoHiemThatNghiep = rs.getDouble(9);
				double thueTNCN = rs.getDouble(10);
				double luongThucLanh = rs.getDouble(11);
				String maNhanVien = rs.getString(12);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, luongChinh, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
				
				dsLuongNV.add(luongNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNV;
	}
	
	
	public List<LuongNhanVienHanhChinh> timLuongNVTheoThang (int month) {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"WHERE thang = '" + month + "'";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, month);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				Date ngayTinhLuong = rs.getDate(2);
				int nam = rs.getInt(3);
				int thang = rs.getInt(4);
				double luongChinh = rs.getDouble(5);
				double tienTamUng = rs.getDouble(6);
				double baoHiemXaHoi = rs.getDouble(7);
				double baoHiemYTe = rs.getDouble(8);
				double baoHiemThatNghiep = rs.getDouble(9);
				double thueTNCN = rs.getDouble(10);
				double luongThucLanh = rs.getDouble(11);
				String maNhanVien = rs.getString(12);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, luongChinh, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
				
				dsLuongNV.add(luongNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLuongNV;
	}
	
	
	
	public void close (PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

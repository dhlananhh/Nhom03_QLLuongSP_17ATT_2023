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
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghi = rs.getInt(6);
				int soNghiPhep = rs.getInt(7);
				double luongChinh = rs.getDouble(8);
				double tienPhuCapTrongThang = rs.getDouble(9);
				double tienTamUng = rs.getDouble(10);
				double baoHiemXaHoi = rs.getDouble(11);
				double baoHiemYTe = rs.getDouble(12);
				double baoHiemThatNghiep = rs.getDouble(13);
				double thueTNCN = rs.getDouble(14);
				double luongThucLanh = rs.getDouble(15);
				String maNhanVien = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNhanVien);
				LuongNhanVienHanhChinh luongNhanVien = new LuongNhanVienHanhChinh(maLuongNV, ngayTinhLuong, nam, thang, soNgayDiLam, soNgayNghi, soNghiPhep, luongChinh, tienPhuCapTrongThang, tienTamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, nhanVien);
						
				
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
			stmt.setInt(3, luongNV.getSoNgayDiLam());
			stmt.setInt(4, luongNV.getSoNgayNghi());
			stmt.setInt(5, luongNV.getSoNghiPhep());
			stmt.setDouble(5, luongNV.getLuongChinh());
			stmt.setDouble(6, luongNV.getTienPhuCapTrongThang());
			stmt.setDouble(7, luongNV.getTienTamUng());
			stmt.setDouble(8, luongNV.getBaoHiemXaHoi());
			stmt.setDouble(9, luongNV.getThueTNCN());
			stmt.setDouble(10, luongNV.getLuongThucLanh());
			stmt.setString(11, luongNV.getNhanVien().getMaNV());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n > 0;
	}
	
	/*
	public boolean updateLuongNhanVien (LuongNhanVienHanhChinh luongNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"UPDATE LuongNhanVienHanhChinh " +
							"SET ngayTinhLuong = ?, soNgayLam = ?, soNgayNghi = ?, " +
							"luongChinh = ?, tienPhuCap = ?, tienTamUng = ?, " +
							"baoHiemXaHoi = ?, thueTNCN = ?, luongThucLanh = ?," +
							"maNV = ? \r\n" +
							"WHERE maBangLuongHC = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setDate(1, luongNV.getNgayTinhLuong());
			stmt.setInt(2, luongNV.getSoNgayLam());
			stmt.setInt(3, luongNV.getSoNgayNghi());
			stmt.setDouble(4, luongNV.getLuongChinh());
			stmt.setDouble(5, luongNV.getTienPhuCap());
			stmt.setDouble(6, luongNV.getTienTamUng());
			stmt.setDouble(7, luongNV.getBaoHiemXaHoi());
			stmt.setDouble(8, luongNV.getThueTNCN());
			stmt.setDouble(9, luongNV.getLuongThucLanh());
			stmt.setString(10, luongNV.getNhanVien().getMaNV());
			
			stmt.setString(11, luongNV.getMaBangLuongHC());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	*/
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

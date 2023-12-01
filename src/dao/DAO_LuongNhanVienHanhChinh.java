package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;

public class DAO_LuongNhanVienHanhChinh {
	public List<LuongNhanVienHanhChinh> getDanhSachLuongNhanVien() {
		List<LuongNhanVienHanhChinh> dsLuongNhanVien = new ArrayList<LuongNhanVienHanhChinh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"INNER JOIN NhanVienHanhChinh ON LuongNhanVienHanhChinh.maNV = NhanVienHanhChinh.maNV \r\n";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh();
				luongNV.setMaBangLuongHC(rs.getString(1));
				luongNV.setNam(rs.getInt(2));
				luongNV.setThang(rs.getInt(3));
				luongNV.setTrangThai(rs.getBoolean(4));
				
				DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
				NhanVienHanhChinh nhanVien = dao_nv.layNhanVienTheoMa(rs.getString(5));
				luongNV.setNhanVien(nhanVien);
				
				luongNV.setHeSoLuong(rs.getFloat(6));
				luongNV.setLuongCoBan(rs.getFloat(7));
				luongNV.setPhuCap(rs.getFloat(8));
				luongNV.setLuongThucLanh(rs.getFloat(9));
				
				dsLuongNhanVien.add(luongNV);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsLuongNhanVien;
	}
	
	
	public boolean themMoiLuongNhanVien (LuongNhanVienHanhChinh luongNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"INSERT INTO LuongNhanVienHanhChinh (maBangLuongHC, nam, thang, trangThai, maNV, heSoLuong, luongCoBan, phuCap, luongThucLanh)" +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
		
			stmt.setString(1, luongNV.getMaBangLuongHC());
			stmt.setInt(2, luongNV.getNam());
			stmt.setInt(3, luongNV.getThang());
			stmt.setBoolean(4, luongNV.isTrangThai());
			stmt.setString(5, luongNV.getNhanVien().getMaNV());
			stmt.setFloat(6, luongNV.getHeSoLuong());
			stmt.setFloat(7, luongNV.getLuongCoBan());
			stmt.setFloat(8, luongNV.getPhuCap());
			stmt.setFloat(9, luongNV.getLuongThucLanh());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	public LuongNhanVienHanhChinh timKiemTheoMaNhanVien (String maNV) {
		LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"select * from LuongNhanVienHanhChinh \r\n" +
							"INNER JOIN NhanVienHanhChinh ON LuongNhanVienHanhChinh.maNV = NhanVienHanhChinh.maNV \r\n" +
							"where maNV = '" + maNV + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				luongNV.setMaBangLuongHC(rs.getString(1));
				luongNV.setNam(rs.getInt(2));
				luongNV.setThang(rs.getInt(3));
				luongNV.setTrangThai(rs.getBoolean(4));
				
				DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
				NhanVienHanhChinh nhanVien = dao_nv.layNhanVienTheoMa(rs.getString(5));
				luongNV.setNhanVien(nhanVien);
				
				luongNV.setHeSoLuong(rs.getFloat(6));
				luongNV.setLuongCoBan(rs.getFloat(7));
				luongNV.setPhuCap(rs.getFloat(8));
				luongNV.setLuongThucLanh(rs.getFloat(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return luongNV;
	}
	
	
	public LuongNhanVienHanhChinh timKiem (String maNV, int nam, int thang) {
		LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"select * from LuongNhanVienHanhChinh \r\n" +
							"INNER JOIN NhanVienHanhChinh ON LuongNhanVienHanhChinh.maNV = NhanVienHanhChinh.maNV \r\n" +
							"where maNV = '" + maNV + "'" +
							" and nam = '" + nam + "'" + " and thang = '" + thang + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				luongNV.setMaBangLuongHC(rs.getString(1));
				luongNV.setNam(rs.getInt(2));
				luongNV.setThang(rs.getInt(3));
				luongNV.setTrangThai(rs.getBoolean(4));
				
				DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
				NhanVienHanhChinh nhanVien = dao_nv.layNhanVienTheoMa(rs.getString(5));
				luongNV.setNhanVien(nhanVien);
				
				luongNV.setHeSoLuong(rs.getFloat(6));
				luongNV.setLuongCoBan(rs.getFloat(7));
				luongNV.setPhuCap(rs.getFloat(8));
				luongNV.setLuongThucLanh(rs.getFloat(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return luongNV;
	}
	
	
	public List<LuongNhanVienHanhChinh> timKiemTheoNamThang (int nam, int thang) {
		List<LuongNhanVienHanhChinh> dsLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT * FROM LuongNhanVienHanhChinh \r\n" +
							"INNER JOIN NhanVienHanhChinh ON LuongNhanVienHanhChinh.maNV = NhanVienHanhChinh.maNV \r\n" +
							" where nam = '" + nam + "'" + " and thang = '" + thang + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh();
				luongNV.setMaBangLuongHC(rs.getString(1));
				luongNV.setNam(rs.getInt(2));
				luongNV.setThang(rs.getInt(3));
				luongNV.setTrangThai(rs.getBoolean(4));
				
				DAO_NhanVienHanhChinh dao_nv = new DAO_NhanVienHanhChinh();
				NhanVienHanhChinh nhanVien = dao_nv.layNhanVienTheoMa(rs.getString(5));
				luongNV.setNhanVien(nhanVien);
				
				luongNV.setHeSoLuong(rs.getFloat(6));
				luongNV.setLuongCoBan(rs.getFloat(7));
				luongNV.setPhuCap(rs.getFloat(8));
				luongNV.setLuongThucLanh(rs.getFloat(9));
				
				dsLuongNV.add(luongNV);
			}
		} catch (Exception e) {
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

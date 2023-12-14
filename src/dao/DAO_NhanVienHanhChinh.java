package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.NhanVienHanhChinh;
import entity.PhongBan;
import entity.TaiKhoan;
import connection.*;


public class DAO_NhanVienHanhChinh {
	public List<NhanVienHanhChinh> getDanhSachNhanVien() {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM NhanVienHanhChinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	public boolean themMoiNhanVien (NhanVienHanhChinh nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"INSERT INTO NhanVienHanhChinh " +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTenNV());
			stmt.setBoolean(3, nv.isGioiTinh());
			stmt.setDate(4, (Date) nv.getNgaySinh());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getCCCD());
			stmt.setString(7, nv.getSDT());
			stmt.setDate(8, (Date) nv.getNgayVao());
			stmt.setString(9, nv.getPhongBan().getMaPhongBan());
			stmt.setBoolean(10, nv.isTrangThai());
			stmt.setString(11, nv.getBangCap());
			stmt.setDouble(12, nv.getLuongCoBan());
			stmt.setDouble(13, nv.getPhuCap());
			stmt.setDouble(14, nv.getHeSoLuong());
			stmt.setString(15, nv.getTaiKhoan().getTenTK());
			stmt.setString(16, nv.getEmail());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	public String getMaNhanVienMoiTao() throws SQLException {
		String maNhanVien = null;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT TOP 1 maNhanVien \r\n" + "from NhanVienHanhChinh \r\n" +
							"ORDER BY maNV DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		
			while (rs.next()) {
				maNhanVien = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maNhanVien;
	}
	
	
	public boolean capNhatThongTinNhanVien (NhanVienHanhChinh nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	
				"UPDATE NhanVienHanhChinh " +
					"SET hoTenNV = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, CCCD = ?, " +
					"SDT = ?, ngayVao = ?, maPhongBan = ?, trangThai = ?, bangCap = ?, " +
					"luongCoBan = ?, phuCap = ?, heSoLuong = ?, tenTK = ?, email = ? \r\n" +
				"WHERE maNV = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, nv.getHoTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setDate(3, (Date) nv.getNgaySinh());
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getCCCD());
			stmt.setString(6, nv.getSDT());
			stmt.setDate(7, (Date) nv.getNgayVao());
			stmt.setString(8, nv.getPhongBan().getMaPhongBan());
			stmt.setBoolean(9, nv.isTrangThai());
			stmt.setString(10, nv.getBangCap());
			stmt.setDouble(11, nv.getLuongCoBan());
			stmt.setDouble(12, nv.getPhuCap());
			stmt.setDouble(13, nv.getHeSoLuong());
			stmt.setString(14, nv.getTaiKhoan().getTenTK());
			stmt.setString(15, nv.getEmail());
			
			stmt.setString(16, nv.getMaNV());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	public NhanVienHanhChinh layNhanVienTheoMa (String maNhanVien) {
		NhanVienHanhChinh nhanVien = null;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT * FROM NhanVienHanhChinh \r\n" + 
							"WHERE maNV = '" + maNhanVien + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nhanVien;
	}
	
	
	public List<NhanVienHanhChinh> layNhanVienTheoTen (String tenNV) {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return null;
		
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM NhanVienHanhChinh " +
							"WHERE hoTenNV = '" + tenNV + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	// lấy DSNV theo giới tính
	public List<NhanVienHanhChinh> layNhanVienTheoGioiTinh (boolean gt) {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return null;
		
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM NhanVienHanhChinh " +
							"WHERE gioiTinh = '" + gt + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	// lấy DSNV theo trạng thái
	public List<NhanVienHanhChinh> layNhanVienTheoTrangThai (boolean tt) {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return null;
		
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM NhanVienHanhChinh " +
							"WHERE trangThai = '" + tt + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	// lấy DSNV theo trạng thái
	public List<NhanVienHanhChinh> layNhanVienTheoPhongBan (String maPB) {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return null;
		
		PreparedStatement stmt = null;
		
		try {
			String sql = 	"SELECT * FROM NhanVienHanhChinh " +
							"WHERE maPhongBan = '" + maPB + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				PhongBan phongBan = new PhongBan(rs.getString(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				double luongCoBan = rs.getDouble(12);
				double phuCap = rs.getDouble(13);
				double heSoLuong = rs.getDouble(14);
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString(15));
				String email = rs.getString(16);
				
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, phongBan, trangThai, bangCap, luongCoBan, phuCap, heSoLuong, taiKhoan, email);
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	// xóa nhân viên
	public boolean xoaNhanVien (String hoTenNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = "DELETE FROM NhanVienHanhChinh WHERE hoTenNV = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, hoTenNV);
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return n > 0;
	}
	
	
	// lấy hệ số lương
	public double layHeSoLuong (String maNhanVien) {
		double heSoLuong = 0;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT heSoLuong AS heSoLuong \r\n" +
							"FROM NhanVienHanhChinh WHERE maNV = '" + maNhanVien + "'" + "\r\n"; 
							
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				heSoLuong = Double.parseDouble(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return heSoLuong;
	}
	
	
	// lấy số tiền phụ cấp của nhân viên
	public double layTienPhuCap (String maNhanVien) {
		double tienPhuCap = 0;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT phuCap AS tienPhuCap \r\n" +
							"FROM NhanVienHanhChinh WHERE maNV = '" + maNhanVien + "'" + "\r\n"; 
							
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				tienPhuCap = Double.parseDouble(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tienPhuCap;
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

package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
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
			String sql = 	"SELECT * FROM NhanVienHanhChinh \r\n" + 
							"INNER JOIN PhongBan ON PhongBan.maPhongBan = NhanVienHanhChinh.maPhongBan \r\n" +
							"INNER JOIN TaiKhoan ON TaiKhoan.tenTK = NhanVienHanhChinh.tenTK \r\n";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				NhanVienHanhChinh nhanVien = new NhanVienHanhChinh();
				nhanVien.setMaNV(rs.getString(1));
				nhanVien.setHoTenNV(rs.getString(2));
				nhanVien.setGioiTinh(rs.getBoolean(3));
				nhanVien.setNgaySinh(rs.getDate(4));
				nhanVien.setDiaChi(rs.getString(5));
				nhanVien.setCCCD(rs.getString(6));
				nhanVien.setSDT(rs.getString(7));
				nhanVien.setNgayVao(rs.getDate(8));
				
				DAO_PhongBan dao_PB = new DAO_PhongBan();
				PhongBan phongBan = dao_PB.getPhongBanTheoMa(rs.getString(9));
				nhanVien.setPhongBan(phongBan);
				
				nhanVien.setTrangThai(rs.getBoolean(10));
				nhanVien.setBangCap(rs.getString(11));
				nhanVien.setLuongCoBan(rs.getFloat(12));
				nhanVien.setPhuCap(rs.getFloat(13));
				nhanVien.setHeSoLuong(rs.getFloat(14));
				
				DAO_TaiKhoan dao_TK = new DAO_TaiKhoan();
				TaiKhoan taiKhoan = dao_TK.layTKTheoTen(rs.getString(15));
				nhanVien.setTaiKhoan(taiKhoan);
				
				nhanVien.setEmail(rs.getString(16));
				
				dsNhanVien.add(nhanVien);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
	
	public boolean themMoiNhanVien (NhanVienHanhChinh nv) throws SQLException {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	"INSERT INTO NhanVienHanhChinh (maNV, hoTenNV, gioiTinh, ngaySinh, diaChi, " +
							"CCCD, SDT, ngayVao, maPhongBan, trangThai, bangCap, " + 
							"luongCoBan, phuCap, heSoLuong, tenTK, email)" +
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
			stmt.setFloat(12, nv.getLuongCoBan());
			stmt.setFloat(13, nv.getPhuCap());
			stmt.setFloat(14, nv.getHeSoLuong());
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
	
	
	public boolean capNhatThongTinNhanVien (NhanVienHanhChinh nv, PhongBan pb, TaiKhoan tk) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = 	
				"UPDATE NhanVienHanhChinh \r\n" +
					"SET hoTenNV = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, CCCD = ?, " +
					"SDT = ?, ngayVao = ?, trangThai = ?, bangCap = ?, " +
					"luongCoBan = ?, phuCap = ?, heSoLuong = ?, email = ? \r\n" +
				"WHERE maNV = ? \r\n" +
				"UPDATE PhongBan SET tenPhongBan = ?, soLuongNV = ?, moTa = ? WHERE maPhongBan = ? \r\n" +
				"UPDATE TaiKhoan SET matKhau = ? WHERE tenTK = ? \r\n";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, nv.getHoTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setDate(3, (Date) nv.getNgaySinh());
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getCCCD());
			stmt.setString(6, nv.getSDT());
			stmt.setDate(7, (Date) nv.getNgayVao());
			stmt.setBoolean(8, nv.isTrangThai());
			stmt.setString(9, nv.getBangCap());
			stmt.setFloat(10, nv.getLuongCoBan());
			stmt.setFloat(11, nv.getPhuCap());
			stmt.setFloat(12, nv.getHeSoLuong());
			stmt.setString(13, nv.getEmail());
			stmt.setString(14, nv.getMaNV());
			
			stmt.setString(15, pb.getTenPhongBan());
			stmt.setInt(16, pb.getSoLuongNV());
			stmt.setString(17, pb.getMoTa());
			stmt.setString(18, pb.getMaPhongBan());
			
			stmt.setString(19, tk.getMatKhau());
			stmt.setString(20, tk.getTenTK());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	public NhanVienHanhChinh getNhanVienTheoMa (String maNhanVien) {
		NhanVienHanhChinh nhanVien = new NhanVienHanhChinh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT * FROM NhanVienHanhChinh \r\n" + 
							"INNER JOIN PhongBan ON PhongBan.maPhongBan = NhanVienHanhChinh.maPhongBan \r\n" +
							"INNER JOIN TaiKhoan ON TaiKhoan.tenTK = NhanVienHanhChinh.tenTK \r\n" +
							"WHERE maNV = '" + maNhanVien + "'";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				nhanVien.setMaNV(resultSet.getString(1));
				nhanVien.setHoTenNV(resultSet.getString(2));
				nhanVien.setGioiTinh(resultSet.getBoolean(3));
				nhanVien.setNgaySinh(resultSet.getDate(4));
				nhanVien.setDiaChi(resultSet.getString(5));
				nhanVien.setCCCD(resultSet.getString(6));
				nhanVien.setSDT(resultSet.getString(7));
				nhanVien.setNgayVao(resultSet.getDate(8));
				
				DAO_PhongBan dao_PB = new DAO_PhongBan();
				PhongBan phongBan = dao_PB.getPhongBanTheoMa(resultSet.getString(9));
				nhanVien.setPhongBan(phongBan);
				
				nhanVien.setTrangThai(resultSet.getBoolean(10));
				nhanVien.setBangCap(resultSet.getString(11));
				nhanVien.setLuongCoBan(resultSet.getFloat(12));
				nhanVien.setPhuCap(resultSet.getFloat(13));
				nhanVien.setHeSoLuong(resultSet.getFloat(14));
				
				DAO_TaiKhoan dao_TK = new DAO_TaiKhoan();
				TaiKhoan taiKhoan = dao_TK.layTKTheoTen(resultSet.getString(15));
				
				nhanVien.setTaiKhoan(taiKhoan);
				nhanVien.setEmail(resultSet.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nhanVien;
	}
	
	
	public NhanVienHanhChinh getNhanVienTheoTen (String tenNhanVien) {
		NhanVienHanhChinh nhanVien = new NhanVienHanhChinh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT * FROM NhanVienHanhChinh \r\n" + 
							"INNER JOIN PhongBan ON PhongBan.maPhongBan = NhanVienHanhChinh.maPhongBan \r\n" +
							"INNER JOIN TaiKhoan ON TaiKhoan.tenTK = NhanVienHanhChinh.tenTK \r\n" +
							"WHERE hoTenNV = '" + tenNhanVien + "'";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				nhanVien.setMaNV(resultSet.getString(1));
				nhanVien.setHoTenNV(resultSet.getString(2));
				nhanVien.setGioiTinh(resultSet.getBoolean(3));
				nhanVien.setNgaySinh(resultSet.getDate(4));
				nhanVien.setDiaChi(resultSet.getString(5));
				nhanVien.setCCCD(resultSet.getString(6));
				nhanVien.setSDT(resultSet.getString(7));
				nhanVien.setNgayVao(resultSet.getDate(8));
				
				DAO_PhongBan dao_PB = new DAO_PhongBan();
				PhongBan phongBan = dao_PB.getPhongBanTheoMa(resultSet.getString(9));
				nhanVien.setPhongBan(phongBan);
				
				nhanVien.setTrangThai(resultSet.getBoolean(10));
				nhanVien.setBangCap(resultSet.getString(11));
				nhanVien.setLuongCoBan(resultSet.getFloat(12));
				nhanVien.setPhuCap(resultSet.getFloat(13));
				nhanVien.setHeSoLuong(resultSet.getFloat(14));
				
				DAO_TaiKhoan dao_TK = new DAO_TaiKhoan();
				TaiKhoan taiKhoan = dao_TK.layTKTheoTen(resultSet.getString(15));
				nhanVien.setTaiKhoan(taiKhoan);
				
				nhanVien.setEmail(resultSet.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nhanVien;
	}
	
	
	public boolean xoaNhanVien (String maNhanVien) {
		Connection con = ConnectDB.getInstance().getConnection();
		
		if (con == null)
			return false;
		
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = "DELETE FROM NhanVienHanhChinh WHERE maNV = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, maNhanVien);
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return n > 0;
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

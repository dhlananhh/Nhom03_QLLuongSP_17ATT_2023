package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.CongNhanSanXuat;
import entity.SanPham;
import entity.ToSanXuat;

public class DAO_CongNhan {
	// lấy dscn
	public List<CongNhanSanXuat> getDSCongNhan(){
		ArrayList<CongNhanSanXuat> dsCN = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from CongNhanSanXuat";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maCN = rs.getString(1);
				String tenCN = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				ToSanXuat tsx = new ToSanXuat(rs.getInt(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				int tayNghe = rs.getInt(12);
				double luongSP = rs.getDouble(13);
				double phuCap = rs.getDouble(14);
				CongNhanSanXuat cn = new CongNhanSanXuat(maCN, tenCN, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, tsx, trangThai, bangCap, tayNghe, luongSP, phuCap);
				dsCN.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsCN;
	}
	
	
	// lấy dscn theo mã
	public CongNhanSanXuat getCongNhanTheoMa(String ma) {
		CongNhanSanXuat cn = new CongNhanSanXuat(ma);
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from CongNhanSanXuat where maCN = '"+ ma +"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				cn.setHoTenCN(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cn;
	}
	
	
	// lấy dscn theo giới tính
	public ArrayList<CongNhanSanXuat> layCongNhanTheoGioiTinh(boolean gt){
		ArrayList<CongNhanSanXuat> dsCN = new ArrayList<CongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from CongNhanSanXuat where gioiTinh = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, gt);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maCN = rs.getString(1);
				String tenCN = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				ToSanXuat tsx = new ToSanXuat(rs.getInt(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				int tayNghe = rs.getInt(12);
				double luongSP = rs.getDouble(13);
				double phuCap = rs.getDouble(14);
				CongNhanSanXuat cn = new CongNhanSanXuat(maCN, tenCN, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, tsx, trangThai, bangCap, tayNghe, luongSP, phuCap);
				dsCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}
	
	
	// lấy dscn theo trạng thái
	public ArrayList<CongNhanSanXuat> layCongNhanTheoTrangThai(boolean tt){
		ArrayList<CongNhanSanXuat> dsCN = new ArrayList<CongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from CongNhanSanXuat where trangThai = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, tt);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maCN = rs.getString(1);
				String tenCN = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				ToSanXuat tsx = new ToSanXuat(rs.getInt(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				int tayNghe = rs.getInt(12);
				double luongSP = rs.getDouble(13);
				double phuCap = rs.getDouble(14);
				CongNhanSanXuat cn = new CongNhanSanXuat(maCN, tenCN, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, tsx, trangThai, bangCap, tayNghe, luongSP, phuCap);
				dsCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}
	
	
	public ArrayList<CongNhanSanXuat> layCongNhanTheoTSX(int tsx){
		ArrayList<CongNhanSanXuat> dsCN = new ArrayList<CongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from CongNhanSanXuat where maToSX = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, tsx);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maCN = rs.getString(1);
				String tenCN = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String cccd = rs.getString(6);
				String sdt = rs.getString(7);
				Date ngayVao = rs.getDate(8);
				ToSanXuat toSX = new ToSanXuat(rs.getInt(9));
				boolean trangThai = rs.getBoolean(10);
				String bangCap = rs.getString(11);
				int tayNghe = rs.getInt(12);
				double luongSP = rs.getDouble(13);
				double phuCap = rs.getDouble(14);
				CongNhanSanXuat cn = new CongNhanSanXuat(maCN, tenCN, gioiTinh, ngaySinh, diaChi, cccd, sdt, ngayVao, toSX, trangThai, bangCap, tayNghe, luongSP, phuCap);
				dsCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}
	
	
	public boolean themCN(CongNhanSanXuat cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into CongNhanSanXuat values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, cn.getMaCN());
			statement.setString(2, cn.getHoTenCN());
			statement.setBoolean(3, cn.isGioiTinh());
			statement.setDate(4, cn.getNgaySinh());
			statement.setString(5, cn.getDiaChi());
			statement.setString(6, cn.getCCCD());
			statement.setString(7, cn.getSDT());
			statement.setDate(8, cn.getNgayVao());
			statement.setInt(9, cn.getToSanXuat().getMaToSX());
			statement.setBoolean(10, cn.isTrangThai());
			statement.setString(11, cn.getBangCap());
			statement.setInt(12, cn.getTayNghe());
			statement.setDouble(13, cn.getLuongSanPham());
			statement.setDouble(14, cn.getPhuCap());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	public boolean capNhatCN(CongNhanSanXuat cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update SanPham set hoTenCN =?, gioiTinh =?, ngaySinh = ?, diaChi = ?"
					+ ", CCCD = ?, SDT = ?, ngayVao = ?, maToSX = ?, trangThai = ?, bangCap = ?, tayNghe = ?"
					+ ", luongSanPham = ?, phuCap = ? where maCN = ?");
			statement.setString(1, cn.getHoTenCN());
			statement.setBoolean(2, cn.isGioiTinh());
			statement.setDate(3, cn.getNgaySinh());
			statement.setString(4, cn.getDiaChi());
			statement.setString(5, cn.getCCCD());
			statement.setString(6, cn.getSDT());
			statement.setDate(7, cn.getNgayVao());
			statement.setInt(8, cn.getToSanXuat().getMaToSX());
			statement.setBoolean(9, cn.isTrangThai());
			statement.setString(10, cn.getBangCap());
			statement.setInt(11, cn.getTayNghe());
			statement.setDouble(12, cn.getLuongSanPham());
			statement.setDouble(13, cn.getPhuCap());
			statement.setString(14, cn.getMaCN());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	// hàm lấy tiền phụ cấp của công nhân
	public double layTienPhuCap (String maCongNhan) {
		double tienPhuCap = 0;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT phuCap AS tienPhuCap \r\n" +
							"FROM CongNhanSanXuat WHERE maCN = '" + maCongNhan + "'" + "\r\n"; 
							
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
	
	
	// hàm lấy tay nghề của công nhân
	public int layTayNghe (String maCongNhan) {
		int tayNghe = 0;
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 	"SELECT tayNghe AS tayNghe \r\n" +
							"FROM CongNhanSanXuat WHERE maCN = '" + maCongNhan + "'" + "\r\n"; 
							
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				tayNghe = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tayNghe;
	}
}

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

public class DAO_SanPham {
	public ArrayList<SanPham> getalltbSanPham(){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
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
	public boolean themSP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into SanPham values(?, ?, ?, ?, ?)");
			statement.setString(1, sp.getMaSP());
			statement.setString(2, sp.getTenSP());
			statement.setInt(3, sp.getSoLuongTon());
			statement.setDouble(4, sp.getGiaThanh());
			statement.setBoolean(5, sp.getTrangThai());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public SanPham getSanPhamTheoMa(String masp){
		SanPham sp = new SanPham(masp);
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from SanPham where maSP = '" + masp + "'");
			while(rs.next()) {
				sp.setTenSP(rs.getString(2));
				sp.setSoLuongTon(rs.getInt(3));
				sp.setGiaThanh(rs.getDouble(4));
				sp.setTrangThai(rs.getBoolean(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public SanPham getSanPhamTheoTen(String tensp){
		SanPham sp = new SanPham(tensp);
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from SanPham where tenSP = '" + tensp + "'");
			while(rs.next()) {
				sp.setMaSP(rs.getString("maSP"));
				sp.setTenSP(rs.getString("TenSP"));
				sp.setSoLuongTon(rs.getInt("soLuongTon"));
				sp.setGiaThanh(rs.getDouble("giaThanh"));
				sp.setTrangThai(rs.getBoolean("trangThai"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public ArrayList<SanPham> getalltbSanPhamTheoTrangThai(boolean tt){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from SanPham where trangThai = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, tt);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				int soLuongTon = rs.getInt("soLuongTon");
				double giaThanh = rs.getDouble("giaThanh");
				Boolean trangThai = rs.getBoolean("trangThai");
				SanPham sp = new SanPham(maSP, tenSP, soLuongTon, giaThanh, trangThai);
				dsSanPham.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
	public boolean capNhatSP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update SanPham set tenSP =?, soLuongTon =?, giaThanh = ?, trangThai = ? where maSP = ?");
			statement.setString(1, sp.getTenSP());
			statement.setInt(2, sp.getSoLuongTon());
			statement.setDouble(3, sp.getGiaThanh());
			statement.setBoolean(4, sp.getTrangThai());
			statement.setString(5, sp.getMaSP());
			n = statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<SanPham> getalltbSanPhamTheoMaCD(String macd){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			//String maCD = macd.substring(0, 4);
			
			String sql = "Select top 1 sp.maSP, sp.tenSP, sp.soLuongTon, sp.giaThanh, sp.trangThai \r\n"
					+ "from SanPham sp join CongDoan cd on sp.maSP = cd.maSP where maCD = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, macd);
			ResultSet rs = statement.executeQuery();
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
	public SanPham getSanPhamTheoMaCD(String macd){
		SanPham sp = new SanPham();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select top 1 sp.maSP, sp.tenSP, sp.soLuongTon, sp.giaThanh, sp.trangThai \r\n"
					+ "from SanPham sp join CongDoan cd on sp.maSP = cd.maSP where maCD = '"+macd+"'");
			while(rs.next()) {
				sp.setMaSP(rs.getString(1));
				sp.setTenSP(rs.getString(2));
				sp.setSoLuongTon(rs.getInt(3));
				sp.setGiaThanh(rs.getDouble(4));
				sp.setTrangThai(rs.getBoolean(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
}

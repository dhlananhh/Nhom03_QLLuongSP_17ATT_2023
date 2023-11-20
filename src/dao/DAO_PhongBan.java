package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import connection.ConnectDB;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.PhongBan;


public class DAO_PhongBan {
	public List<PhongBan> getDanhSachPhongBan() {
		List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from PhongBan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maPhongBan = rs.getString(1);
				String tenPhongBan = rs.getString(2);
				int soLuongNV = rs.getInt(3);
				String moTa = rs.getString(4);
				
				PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, soLuongNV, moTa);
				dsPhongBan.add(pb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsPhongBan;
	}
	
	
	public PhongBan getPhongBanTheoTen(String tenPhongBan) {
		PhongBan phongBan = new PhongBan();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from PhongBan where tenPhongBan = '" + tenPhongBan + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				phongBan.setMaPhongBan(rs.getString(1));
				phongBan.setTenPhongBan(rs.getString(2));
				phongBan.setSoLuongNV(rs.getInt(3));
				phongBan.setMoTa(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phongBan;
	}
	
	
	public PhongBan getPhongBanTheoMa(String maPhongBan) {
		PhongBan phongBan = new PhongBan();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from PhongBan where maPhongBan = '" + maPhongBan + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				phongBan.setMaPhongBan(rs.getString(1));
				phongBan.setTenPhongBan(rs.getString(2));
				phongBan.setSoLuongNV(rs.getInt(3));
				phongBan.setMoTa(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phongBan;
	}
	
}

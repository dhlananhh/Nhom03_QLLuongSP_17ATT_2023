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

import entity.ToSanXuat;

public class DAO_ToSanXuat {
	public List<ToSanXuat> docDuLieu() {
		List<ToSanXuat> dsToSX = new ArrayList<ToSanXuat>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT maToSX FROM ToSanXuat";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				int maToSX = rs.getInt(1);
				String moTa = rs.getString(2);
				int soLuongCN = rs.getInt(3);
				
				ToSanXuat toSX = new ToSanXuat(maToSX, moTa, soLuongCN);
				dsToSX.add(toSX);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsToSX;
	}
	
	
	public ToSanXuat getToSanXuatTheoMa (int maToSX) {
		ToSanXuat toSX = new ToSanXuat();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from ToSanXuat where maToSX = '" + maToSX + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				toSX.setMaToSX(rs.getInt(1));
				toSX.setMoTa(rs.getString(2));
				toSX.setSoLuongCN(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toSX;
	}
	
	
	public List<ToSanXuat> getDanhSachToSanXuat() {
		List<ToSanXuat> dsToSX = new ArrayList<ToSanXuat>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM ToSanXuat";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				int maToSX = rs.getInt(1);
				String moTa = rs.getString(2);
				int soLuongCN = rs.getInt(3);
				
				ToSanXuat toSX = new ToSanXuat(maToSX, moTa, soLuongCN);
				dsToSX.add(toSX);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsToSX;
	}
}

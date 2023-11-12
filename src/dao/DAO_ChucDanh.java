package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.ChucDanh;
import entity.PhongBan;

public class DAO_ChucDanh {
	public List<ChucDanh> docDuLieu() {
		List<ChucDanh> dsChucDanh = new ArrayList<ChucDanh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT maChucDanh, tenChucDanh FROM ChucDanh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maChucDanh = rs.getString(1);
				String tenChucDanh = rs.getString(2);
				
				ChucDanh chucDanh = new ChucDanh(maChucDanh, tenChucDanh);
				dsChucDanh.add(chucDanh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsChucDanh;
	}
	
	
	public ChucDanh getChucDanhTheoTen (String tenChucDanh) {
		ChucDanh chucDanh = new ChucDanh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from ChucDanh where tenChucDanh = '" + tenChucDanh + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				chucDanh.setMaChucDanh(resultSet.getString(1));
				chucDanh.setTenChucDanh(resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chucDanh;
	}
	
	
	public ChucDanh getChucDanhTheoMa (String maChucDanh) {
		ChucDanh chucDanh = new ChucDanh();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from ChucDanh where maChucDanh = '" + maChucDanh + "'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				chucDanh.setMaChucDanh(resultSet.getString(1));
				chucDanh.setTenChucDanh(resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chucDanh;
	}
	
	
	public List<ChucDanh> getDanhSachChucDanh() {
		List<ChucDanh> dsChucDanh = new ArrayList<ChucDanh>();
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from ChucDanh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maChucDanh = rs.getString(1);
				String tenChucDanh = rs.getString(2);
				
				ChucDanh chucDanh = new ChucDanh(maChucDanh, tenChucDanh);
				dsChucDanh.add(chucDanh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsChucDanh;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.TaiKhoan;

public class DAO_TaiKhoan {
	public ArrayList<TaiKhoan> layDuLieuTK(){
		ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String tenTK = rs.getString("tenTK");
				String matKhau = rs.getString("matKhau");
				TaiKhoan tk = new TaiKhoan(tenTK, matKhau);
				dsTaiKhoan.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTaiKhoan;
	}
	public boolean doiMatKhau(TaiKhoan tk, String mkMoi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update TaiKhoan set matKhau = ? where tenTK = ?");
			statement.setString(1, mkMoi);
			statement.setString(2, tk.getTenTK() );
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public TaiKhoan layTKTheoTen(String tenTK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = new TaiKhoan();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from TaiKhoan where tenTK = '"+ tenTK +"'");
			while(rs.next()) {
				tk.setTenTK(rs.getString("tenTK"));
				tk.setMatKhau(rs.getString("matKhau"));
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}		
		return tk;	
	}
	public TaiKhoan layTKTheoMatKhau(String mk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = new TaiKhoan();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from TaiKhoan where matKhau = '"+ mk +"'");
			while(rs.next()) {
				tk.setTenTK(rs.getString("tenTK"));
				tk.setMatKhau(rs.getString("matKhau"));
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}		
		return tk;	
	}
}

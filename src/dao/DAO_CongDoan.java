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

public class DAO_CongDoan {
	public ArrayList<CongDoan> getalltbCongDoan(){
		ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from CongDoan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maCD = rs.getString("maCD");
				String tenCD = rs.getString("tenCD");
				double luongTheoSP = rs.getDouble("luongTheoSP");
				SanPham sp = new SanPham(rs.getString("maSP"));
				int thuTu = rs.getInt("thuTu");
				CongDoan cd = new CongDoan(maCD, tenCD, luongTheoSP, sp, thuTu);
				dsCongDoan.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return dsCongDoan;
	}
	public boolean themCD(CongDoan cd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into CongDoan values(?, ?, ?, ?, ?)");
			statement.setString(1, cd.getMaCD());
			statement.setString(2, cd.getTenCD());
			statement.setDouble(3, cd.getLuongTheoSanPham());
			statement.setString(4, cd.getMaSP().getMaSP());
			statement.setInt(5, cd.getThuTu());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<CongDoan> getAllCongDoanTheoSP(String maSP){
		ArrayList<CongDoan> dsCD = new ArrayList<CongDoan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from CongDoan where maSP = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maCD = rs.getString("maCD");
				String tenCD = rs.getString("tenCD");
				double luongTheoSP = rs.getDouble("luongTheoSP");
				SanPham sp = new SanPham(rs.getString("maSP"));
				int thuTu = rs.getInt("thuTu");
				CongDoan cd = new CongDoan(maCD, tenCD, luongTheoSP, sp, thuTu);
				dsCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCD;
	}
	public CongDoan getCongDoanTheoMa(String maCD){
		CongDoan cd = new CongDoan(maCD);
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from CongDoan where maCD = '"+maCD+"'");
			while(rs.next()) {
				cd.setTenCD(rs.getString(2));
				cd.setLuongTheoSanPham(rs.getDouble(3));
				cd.setMaSP(new SanPham(rs.getString(4)));
				cd.setThuTu(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}
	public boolean capNhatCD(CongDoan cd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update CongDoan set tenCD =?, luongTheoSP =?, thuTu = ? where maCD = ?");
			statement.setString(1, cd.getTenCD());
			statement.setDouble(2, cd.getLuongTheoSanPham());
			statement.setDouble(3, cd.getThuTu());
			statement.setString(4, cd.getMaCD());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}

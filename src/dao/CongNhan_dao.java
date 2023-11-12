package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.CongNhanSanXuat;
import entity.SanPham;

public class CongNhan_dao {
	public List<CongNhanSanXuat> getDSCongNhan(){
		ArrayList<CongNhanSanXuat> ds = new ArrayList<>();
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
				String trangThai = rs.getString("trangThai");
				CongNhanSanXuat cn = new CongNhanSanXuat();
				cn.setMaCN(maCN);
				cn.setHoTenCN(tenCN);
				ds.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
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
}

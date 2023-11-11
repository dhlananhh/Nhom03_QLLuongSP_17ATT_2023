package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.ChamCong;
import entity.CongDoan;
import entity.NhanVien;

public class ChamCong_dao {
	
	public boolean themBangPhanCong(ChamCong phanCong) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {

			PreparedStatement ps = con.prepareStatement("insert into ChamCong values (?, ?, ?, ?, null)");
			ps.setString(1, phanCong.getMaCN());
			ps.setString(2, phanCong.getMaCD());
			ps.setDate(3, phanCong.getNgayCham());
			ps.setInt(4, phanCong.getChiTieu());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();

		return false;
	}
	public boolean chamCong(ChamCong o) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update ChamCong set soLuongHoanThanh = ? where maCN = ? and ngayCham = ?");
			ps.setInt(1, o.getSoLuongHoanThanh());
			ps.setString(2, o.getMaCN());
			ps.setDate(3, o.getNgayCham());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public boolean xoaPhanCong(String macn, Date date) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			PreparedStatement ps = con.prepareStatement("delete ChamCong where maCN = ? and ngayCham = ?");
			ps.setString(1, macn);
			ps.setDate(2, date);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public List<ChamCong> getDSChamCongTheoNgay(Date ngay){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		List<ChamCong> dsChamCong = new ArrayList<ChamCong>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ChamCong where ngayCham= '"+ ngay +"' ");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				dsChamCong.add(new ChamCong(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3),
						resultSet.getInt(4), resultSet.getInt(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsChamCong;
	}
	
}

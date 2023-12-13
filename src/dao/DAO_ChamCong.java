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

public class DAO_ChamCong {
	
	public boolean themBangPhanCong(ChamCong phanCong) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
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

		return false;
	}
	public boolean suaPhanCong(ChamCong phanCong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update ChamCong set maCD = ?, chiTieu = ? where maCN = ? and ngayCham = ?");
			ps.setString(1, phanCong.getMaCD());
			ps.setInt(2, phanCong.getChiTieu());
			ps.setString(3, phanCong.getMaCN());
			ps.setDate(4, phanCong.getNgayCham());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean chamCong(ChamCong o) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
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
		//con.close();
		return false;
	}
	public boolean xoaPhanCong(String macn, Date date) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete ChamCong where maCN = ? and ngayCham = ?");
			ps.setString(1, macn);
			ps.setDate(2, date);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public List<ChamCong> getDSChamCongTheoNgay(Date ngay){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
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
	public List<ChamCong> getDSChamCongTheoSP(String masp, Date ngayCham) {
	    ConnectDB.getInstance();
	    List<ChamCong> dsChamCong = new ArrayList<>();

	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT cc.maCN, cc.maCD, cc.ngayCham, cc.chiTieu, cc.soLuongHoanThanh \r\n"
	         		+ "FROM ChamCong cc \r\n"
	         		+ "JOIN CongDoan cd ON cc.maCD = cd.maCD \r\n"
	         		+ "WHERE cd.maSP = ? AND cc.ngayCham = ?")) {

	        ps.setString(1, masp);
	        ps.setDate(2, ngayCham);
	        try (ResultSet resultSet = ps.executeQuery()) {
	            while (resultSet.next()) {
	                dsChamCong.add(new ChamCong(
	                        resultSet.getString("maCN"),
	                        resultSet.getString("maCD"),
	                        resultSet.getDate("ngayCham"),
	                        resultSet.getInt("chiTieu"),
	                        resultSet.getInt("soLuongHoanThanh")
	                ));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsChamCong;
	}
	public ChamCong getChamCongTheoCN(String macn, Date ngayCham) {
	    ConnectDB.getInstance();
	    ChamCong kq = new ChamCong(macn, ngayCham);

	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM ChamCong WHERE maCN = ? AND ngayCham = ?")) {

	        ps.setString(1, macn);
	        ps.setDate(2, ngayCham);

	        try (ResultSet resultSet = ps.executeQuery()) {
	            while (resultSet.next()) {
	                kq.setMaCD(resultSet.getString(2));
	                kq.setChiTieu(resultSet.getInt(4));
	                kq.setSoLuongHoanThanh(resultSet.getInt(5));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return kq;
	}

	
}

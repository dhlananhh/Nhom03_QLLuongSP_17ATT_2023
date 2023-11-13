package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.DiemDanh;

public class DAO_DiemDanh {

	public boolean themDiemDanh(DiemDanh dd) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement("insert into NgayNghi values (?,?,?)");
			ps.setString(1, dd.getMaNV());
			ps.setDate(2, dd.getNgayCham());
			ps.setBoolean(3, dd.isPhep());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public List<DiemDanh> getDiemDanhTheoNgay(Date date){
		List<DiemDanh> ds = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from NgayNghi where ngayNghi = '"+ date + "'");
			ResultSet rs = ps.executeQuery();
			DiemDanh dd = new DiemDanh(date);
			dd.setMaNV(rs.getString(1));
			dd.setPhep(rs.getBoolean(3));
			ds.add(dd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	public String getNgayPhep(String ma, Date date){
		String tt = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from NgayNghi where maNV = ? and ngayNghi = ?");
			ps.setString(1, ma);
			ps.setDate(2, date);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				tt = rs.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tt;
	}
}

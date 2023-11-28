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

			PreparedStatement ps = con.prepareStatement("insert into DiemDanh values (?,?,?)");
			ps.setString(1, dd.getMaNV());
			ps.setDate(2, dd.getNgayCham());
			ps.setString(3, dd.getTrangThai());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public boolean ktDiemDanh(Date date) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		boolean kq= true;
		try {
			PreparedStatement ps = con.prepareStatement("select top 1 * from DiemDanh where ngayNghi = ?");
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			kq= rs.next();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}
	public boolean capNhatDiemDanh(DiemDanh dd) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update DiemDanh set trangThai = ? where maNV = ? and ngayNghi = ?");
			ps.setString(1, dd.getTrangThai());
			ps.setString(2, dd.getMaNV());
			ps.setDate(3, dd.getNgayCham());
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
			PreparedStatement ps = con.prepareStatement("select * from DiemDanh where ngayNghi = '"+ date + "'");
			ResultSet rs = ps.executeQuery();
			DiemDanh dd = new DiemDanh(date);
			dd.setMaNV(rs.getString(1));
			dd.setTrangThai(rs.getString(3));
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
			PreparedStatement ps = con.prepareStatement("select * from DiemDanh where maNV = ? and ngayNghi = ?");
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
	public int tongNgayPhepTrongThang(int month, String ma) {
		int sum=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT \r\n"
					+ "    SUM(CASE WHEN trangThai = 'P' AND MONTH(ngayNghi) = ? THEN 1 ELSE 0 END) AS tong_ngay_nghi_phep\r\n"
					+ "FROM \r\n"
					+ "    DiemDanh\r\n"
					+ "WHERE \r\n"
					+ "    MONTH(ngayNghi) = ?\r\n"
					+ "	and maNV = ?\r\n"
					+ "GROUP BY \r\n"
					+ "    maNV;");
			ps.setInt(1, month);
			ps.setInt(2, month);
			ps.setString(3, ma);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				sum = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sum;
	}
	public int tongNgayKhongPhepTrongThang(int month, String ma) {
		int sum=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT \r\n"
					+ "    SUM(CASE WHEN trangThai = 'K' AND MONTH(ngayNghi) = ? THEN 1 ELSE 0 END) AS tong_ngay_nghi_phep\r\n"
					+ "FROM \r\n"
					+ "    DiemDanh\r\n"
					+ "WHERE \r\n"
					+ "    MONTH(ngayNghi) = ?\r\n"
					+ "	and maNV = ?\r\n"
					+ "GROUP BY \r\n"
					+ "    maNV;");
			ps.setInt(1, month);
			ps.setInt(2, month);
			ps.setString(3, ma);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				sum = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sum;
	}
}

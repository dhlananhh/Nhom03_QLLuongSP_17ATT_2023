package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.ChamCong;
import entity.CongDoan;
import entity.CongNhanSanXuat;
import entity.LuongCongNhanSanXuat;

public class DAO_LuongCongNhanSanXuat {
	public List<LuongCongNhanSanXuat> layDuLieuLuongCN(){
		List<LuongCongNhanSanXuat> dsLuongCN = new ArrayList<LuongCongNhanSanXuat>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LuongCongNhanSanXuat";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				LuongCongNhanSanXuat lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
				dsLuongCN.add(lcnsx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuongCN;
	}
	
	
	public List<LuongCongNhanSanXuat> timLuongTheoThangNam(int soNam, int soThang){
		List<LuongCongNhanSanXuat> dsLuong = new ArrayList<LuongCongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from LuongCongNhanSanXuat where nam = ? and thang = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, soNam);
			statement.setInt(2, soThang);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				LuongCongNhanSanXuat lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
				dsLuong.add(lcnsx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}
	
	
	public List<LuongCongNhanSanXuat> timLuongTheoThang(int soThang){
		List<LuongCongNhanSanXuat> dsLuong = new ArrayList<LuongCongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from LuongCongNhanSanXuat where thang = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, soThang);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				LuongCongNhanSanXuat lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
				dsLuong.add(lcnsx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}
	
	
	public List<LuongCongNhanSanXuat> timLuongTheoNam(int soNam){
		List<LuongCongNhanSanXuat> dsLuong = new ArrayList<LuongCongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from LuongCongNhanSanXuat where nam = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, soNam);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				LuongCongNhanSanXuat lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
				dsLuong.add(lcnsx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}
	
	
	public List<LuongCongNhanSanXuat> timLuongTheoMaCN(String maCN){
		List<LuongCongNhanSanXuat> dsLuong = new ArrayList<LuongCongNhanSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from LuongCongNhanSanXuat where maCN = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCN);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				LuongCongNhanSanXuat lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
				dsLuong.add(lcnsx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}
	
	
	public LuongCongNhanSanXuat timLuongCNTheoMaCN(String maCN){
		LuongCongNhanSanXuat lcnsx = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from LuongCongNhanSanXuat where maCN = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCN);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuongCN = rs.getString("maBangLuongCN");
				Date ngayTinhLuong = rs.getDate("ngayTinhLuong");
				int nam = rs.getInt("nam");
				int thang = rs.getInt("thang");
				double luongSanPham = rs.getDouble("luongSanPham");
				double tamUng = rs.getDouble("tienTamUng");
				double baoHiemXaHoi = rs.getDouble("baoHiemXaHoi");
				double baoHiemYTe = rs.getDouble("baoHiemYTe");
				double baoHiemThatNghiep = rs.getDouble("baoHiemThatNghiep");
				double thueTNCN = rs.getDouble("thueTNCN");
				double luongThucLanh = rs.getDouble("luongThucLanh");
				CongNhanSanXuat congNhan = new CongNhanSanXuat(rs.getString("maCN"));
				lcnsx = new LuongCongNhanSanXuat(maBangLuongCN, ngayTinhLuong, thang, nam, luongSanPham, tamUng, baoHiemXaHoi, baoHiemYTe, baoHiemThatNghiep, thueTNCN, luongThucLanh, congNhan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcnsx;
	}
	
	
	public boolean capNhatLuong(LuongCongNhanSanXuat luongCN, double luongCu, double tienCu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update LuongCongNhanSanXuat set tienTamUng =?, luongThucLanh = ?  where maBangLuongCN = ?");
			statement.setDouble(1, luongCN.getTamUng());
			statement.setDouble(2, luongCu-(luongCN.getTamUng()-tienCu));
			statement.setString(3, luongCN.getMaBangLuongCN());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(statement);
		}
		return n > 0;
	}
	
	
	public void close (PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

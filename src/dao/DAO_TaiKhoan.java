package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.TaiKhoan;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class DAO_TaiKhoan {
	
    public int sendEmail(String email){
        final String from = "hieutrungminecraft@gmail.com";
        final String password = "dkkl yitv pwsa cmji";
        
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
       
        Authenticator auth;
        auth = new Authenticator() {
            @Override
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from,password);
            }       
        };
        
        Session session = Session.getInstance(props, auth);
        final String to = email;
        MimeMessage msg = new MimeMessage(session);
        
        try {
            msg.addHeader("Content-type", "text/HTML;charset=UTF-8");

            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
            msg.setSubject("Đặt lại mật khẩu");
            msg.setSentDate(new java.util.Date());
            Random generator = new Random();
            int OTP =  100000 + generator.nextInt(900000);
           
            if(OTP<0) OTP = OTP * (-1);
            msg.setText("Xin chào!\nBạn đang đặt lại mật khẩu cho tài khoản với email "+email+"\nTUYỆT ĐỐI KHÔNG CHIA SẺ MÃ OTP NÀY CHO BẤT KÌ AI!\n\nMã OTP của bạn là: "+String.valueOf(OTP),"UTF-8");
            Transport.send(msg);
            return OTP;
        } catch (Exception e) {
            
            e.printStackTrace();
            return -1;
        }
        
    }
        
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
	public String layEmailTheoTK(String tenTK) {
		String email = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan tk = new TaiKhoan();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select nv.email from NhanVienHanhChinh nv join TaiKhoan tk on nv.tenTK = tk.tenTK where tk.tenTK = '"+tenTK+"'");
			while(rs.next()) {
				email = rs.getString("email");
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}	
		
		return email;
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

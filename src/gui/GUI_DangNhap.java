package gui;


import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import com.formdev.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;


public class GUI_DangNhap extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblTaiKhoan, lblMatKhau;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap, btnThoat, btnQuenMatKhau;
	private JCheckBox chkShowPwd;
	private Font BVNPro;
	
	
	public GUI_DangNhap() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Đăng nhập");
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		//load fonts
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(BVNPro);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		//set color
		Color bgColor = new Color(0, 153, 204);
		
		//main panel
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth chứa tiêu đề
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(bgColor);
		
		lblTieuDe = new JLabel("ĐĂNG NHẬP");
		lblTieuDe.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.WHITE);
		pnNorth.add(lblTieuDe);
		
		
		//pnCenter chứa các box, label & textfield username, password
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		
		lblTaiKhoan = new JLabel("Tài khoản: ");
		lblMatKhau = new JLabel("Mật khẩu: ");
		txtTaiKhoan = new JTextField(20);
		txtMatKhau = new JPasswordField(20);
		chkShowPwd = new JCheckBox("Show password");
		
		lblTaiKhoan.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		lblMatKhau.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		
		b1.add(lblTaiKhoan);
		b1.add(txtTaiKhoan);
		b2.add(lblMatKhau);
		b2.add(txtMatKhau);
		b3.add(chkShowPwd);
		
		btnDangNhap = new JButton("Đăng nhập");
		btnQuenMatKhau = new JButton("Quên mật khẩu");
		btnThoat = new JButton("Thoát");
		
		btnDangNhap.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnQuenMatKhau.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnThoat.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		
		btnDangNhap.setBackground(bgColor);
		btnDangNhap.setForeground(Color.WHITE);
		btnQuenMatKhau.setBackground(bgColor);
		btnQuenMatKhau.setForeground(Color.WHITE);
		btnThoat.setBackground(bgColor);
		btnThoat.setForeground(Color.WHITE);
		
		b4.add(btnDangNhap);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnQuenMatKhau);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnThoat);
		
		lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize());
		
		//thêm các box con (b1,b2...) vào box lớn b
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//thêm box b vào pnCenter
		pnCenter.add(b);
		
		addActionEvent();
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	//đăng ký sự kiện cho các button
	public void addActionEvent() {
		btnDangNhap.addActionListener(this);
		btnQuenMatKhau.addActionListener(this);
		btnThoat.addActionListener(this);
		chkShowPwd.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o.equals(btnDangNhap)) {
			String userText;
            String pwdText;
            userText = txtTaiKhoan.getText();
            pwdText = txtMatKhau.getText();
            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("123456")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
		} 
		else if (o.equals(btnThoat)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }
		} 
		else if (o.equals(chkShowPwd)) {
			if (chkShowPwd.isSelected()) {
				txtMatKhau.setEchoChar((char) 0);
            } else {
            	txtMatKhau.setEchoChar('*');
            }
		}
//		else if (o.equals(btnQuenMatKhau)) {
//			try {
//				new GUI_QuenMatKhau().setVisible(true);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		
		new GUI_DangNhap().setVisible(true);
	}
}

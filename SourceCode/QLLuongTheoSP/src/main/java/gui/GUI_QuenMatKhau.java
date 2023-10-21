package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.UIManager;
import com.formdev.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;


public class GUI_QuenMatKhau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblTaiKhoan, lblMatKhauMoi, lblNhapLaiMKMoi;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhauMoi, txtNhapLaiMKMoi;
	private JButton btnXacNhan, btnHuy;
	private JCheckBox chkShowPwd;
	
	
	public GUI_QuenMatKhau() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Đổi mật khẩu");
		setSize(500, 350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		Color bgColor = new Color(0, 153, 204);
		
		//main panel
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth chứa tiêu đề
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(bgColor);
		
		lblTieuDe = new JLabel("QUÊN MẬT KHẨU");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
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
		lblMatKhauMoi = new JLabel("Mật khẩu mới: ");
		lblNhapLaiMKMoi = new JLabel("Nhập lại mật khẩu mới: ");
		
		txtTaiKhoan = new JTextField(20);
		txtMatKhauMoi = new JPasswordField(20);
		txtNhapLaiMKMoi = new JPasswordField(20);
		chkShowPwd = new JCheckBox("Show password");
		
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMatKhauMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNhapLaiMKMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		b1.add(lblTaiKhoan);
		b1.add(txtTaiKhoan);
		b2.add(lblMatKhauMoi);
		b2.add(txtMatKhauMoi);
		b3.add(lblNhapLaiMKMoi);
		b3.add(txtNhapLaiMKMoi);
		b4.add(chkShowPwd);
		
		btnXacNhan = new JButton("Xác nhận");
		btnHuy = new JButton("Hủy");
		
		btnXacNhan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnXacNhan.setBackground(bgColor);
		btnXacNhan.setForeground(Color.WHITE);
		btnHuy.setBackground(bgColor);
		btnHuy.setForeground(Color.WHITE);
		
		b5.add(btnXacNhan);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(btnHuy);
		
		lblTaiKhoan.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		lblMatKhauMoi.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		
		//thêm các box con (b1,b2...) vào box lớn b
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b5);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
				
		//thêm box b vào pnCenter
		pnCenter.add(b);
		
		addActionEvent();
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	//đăng ký sự kiện cho các button
	public void addActionEvent() {
		btnXacNhan.addActionListener(this);
		btnHuy.addActionListener(this);
		chkShowPwd.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
Object o = e.getSource();
		
		if (o.equals(btnXacNhan)) {
			String userText;
            String pwdText;
            userText = txtTaiKhoan.getText();
            pwdText = txtMatKhauMoi.getText();
            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("123456")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
		} 
		else if (o.equals(btnHuy)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }
		} 
		else if (o.equals(chkShowPwd)) {
			if (chkShowPwd.isSelected()) {
				txtMatKhauMoi.setEchoChar((char) 0);
				txtNhapLaiMKMoi.setEchoChar((char) 0);
            } else {
            	txtMatKhauMoi.setEchoChar('*');
            	txtNhapLaiMKMoi.setEchoChar('*');
            }
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
//		try {
//		    UIManager.setLookAndFeel( new FlatLightLaf() );
//		} catch( Exception ex ) {
//		    System.err.println( "Failed to initialize LaF" );
//		}
		
		FlatDarculaLaf.updateUI();
		
		new GUI_QuenMatKhau().setVisible(true);
	}
}

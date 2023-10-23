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
	private JLabel lblTieuDe, lblTaiKhoan, lblMatKhauMoi, lblNhapLaiMKMoi;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhauMoi, txtNhapLaiMKMoi;
	private JButton btnXacNhan, btnHuy;
	private JCheckBox chkShowPwd;
	
	
	public GUI_QuenMatKhau() {
		setTitle("QUÊN MẬT KHẨU");
		setSize(500, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);	
		createAndDisplayGUI();
	}
	public void createAndDisplayGUI() {
		Color bgColor = new Color(0, 102, 204);
		
		//panel
		JPanel pContent = new JPanel();
		pContent.setLayout(new BorderLayout());
		
		JPanel pTop = new JPanel();
		pContent.add(pTop, BorderLayout.NORTH);
		pTop.setBackground(bgColor);
		
		pTop.add(lblTieuDe = new JLabel("QUÊN MẬT KHẨU"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
	
		JPanel pCenter = new JPanel();
		pContent.add(pCenter, BorderLayout.CENTER);
		
		JPanel pBot = new JPanel();
		pContent.add(pBot, BorderLayout.SOUTH);
		//Box
		Box b, b1, b2, b3, b4;
		b = Box.createVerticalBox();
		b1 = Box.createVerticalBox();
		b2 = Box.createVerticalBox();
		b3 = Box.createVerticalBox();
		//b1
		b1.add(lblTaiKhoan = new JLabel("Tài khoản: "));
		b1.add(Box.createVerticalStrut(10));
		b1.add(txtTaiKhoan = new JTextField(20));
		//b2
		b2.add(lblMatKhauMoi = new JLabel("Mật khẩu mới: "));
		b2.add(Box.createVerticalStrut(10));
		b2.add(txtMatKhauMoi = new JPasswordField(20));
		//b3
		b3.add(lblNhapLaiMKMoi = new JLabel("Nhập lại mật khẩu mới: "));
		b3.add(Box.createVerticalStrut(10));
		b3.add(txtNhapLaiMKMoi = new JPasswordField(20));
		//btn
		pBot.add(btnXacNhan = new JButton("Xác nhận"));
		//pBot.add(new Di)
		pBot.add(btnHuy = new JButton("Hủy bỏ"));
		//chkShowPwd = new JCheckBox("Show password");
		//font label
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMatKhauMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNhapLaiMKMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXacNhan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXacNhan.setBackground(new Color(37, 187, 214));
		btnXacNhan.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(37, 187, 214));
		btnHuy.setForeground(Color.WHITE);
	
		//add box
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		pContent.add(b);
		//action
		btnXacNhan.addActionListener(this);
		btnHuy.addActionListener(this);
		//chkShowPwd.addActionListener(this);
		
		Container container = getContentPane();
		container.add(pContent);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
		/*else if (o.equals(chkShowPwd)) {
			if (chkShowPwd.isSelected()) {
				txtMatKhauMoi.setEchoChar((char) 0);
				txtNhapLaiMKMoi.setEchoChar((char) 0);
            } else {
            	txtMatKhauMoi.setEchoChar('*');
            	txtNhapLaiMKMoi.setEchoChar('*');
            }
		}
		*/
	}
	
	
	public static void main(String[] args) throws Exception {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		FlatDarculaLaf.updateUI();
		
		new GUI_QuenMatKhau().setVisible(true);
	}
}

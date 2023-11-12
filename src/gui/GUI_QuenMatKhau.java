package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

import connection.ConnectDB;
import dao.TaiKhoan_dao;
import entity.TaiKhoan;


public class GUI_QuenMatKhau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblTaiKhoan, lblMatKhauMoi, lblNhapLaiMKMoi;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhauMoi, txtNhapLaiMKMoi;
	private JButton btnXacNhan, btnHuy;
	private JCheckBox chkShowPwd;
	private TaiKhoan_dao tk_dao;
	
	
	public GUI_QuenMatKhau() {
		setTitle("Quên mật khẩu");
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//connect
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tk_dao = new TaiKhoan_dao();
		//panel
		JPanel pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		add(pnContent);
		
		JPanel pnTop = new JPanel();
		pnContent.add(pnTop, BorderLayout.NORTH);
		pnTop.setBackground(new Color(0, 102, 204));
		
		lblTieuDe = new JLabel("QUÊN MẬT KHẨU");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		pnTop.add(lblTieuDe);
		
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setBackground(new Color(245, 251, 255));
		
		JPanel pnBot = new JPanel();
		pnContent.add(pnBot, BorderLayout.SOUTH);
		pnBot.setBackground(new Color(245, 251, 255));
		//Box
		Box b, b1, b2, b3, b4;
		b = Box.createVerticalBox();
		b1 = Box.createVerticalBox();
		b2 = Box.createVerticalBox();
		b3 = Box.createVerticalBox();
		b4 = Box.createHorizontalBox();	
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
		//b4
		chkShowPwd = new JCheckBox("Hiển thị mật khẩu");
		b4.add(chkShowPwd);
		//preference
		lblTaiKhoan.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		lblMatKhauMoi.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		//font
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMatKhauMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNhapLaiMKMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		//add box
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
		pnCenter.add(b);
		//btn
		pnBot.add(btnXacNhan = new JButton("Xác nhận"));
		pnBot.add(btnHuy = new JButton("Hủy"));

		btnXacNhan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXacNhan.setBackground(new Color(0, 102, 204));
		btnXacNhan.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(0, 102, 204));
		btnHuy.setForeground(Color.WHITE);
		//action			
		btnXacNhan.addActionListener(this);
		btnHuy.addActionListener(this);
		btnHuy.setPreferredSize(btnXacNhan.getPreferredSize());
		chkShowPwd.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			String tenTK = txtTaiKhoan.getText();
            String matKhauMoi = txtMatKhauMoi.getText();
			String nhapLaiMK = txtNhapLaiMKMoi.getText();
            TaiKhoan tk = tk_dao.layTKTheoTen(tenTK);
            if(tk.getTenTK() == null) {
    			JOptionPane.showMessageDialog(null, "Tài khoản không đúng!");
    			txtTaiKhoan.setText("");
    			txtTaiKhoan.requestFocus();
    			return;
    		}
            if(matKhauMoi.equals("")) {
            	JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
            	txtMatKhauMoi.setText("");
            	txtMatKhauMoi.requestFocus();
            	return;
            }
            if(!nhapLaiMK.equals(matKhauMoi)) {
            	JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không đúng");
            	txtNhapLaiMKMoi.setText("");
            	txtNhapLaiMKMoi.requestFocus();
            	return;
            }
            else  {
            	tk_dao.doiMatKhau(tk, matKhauMoi);
                JOptionPane.showMessageDialog(this, "Thay đổi thành công");
                this.setVisible(false);
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
		FlatLightLaf.setup();	
		new GUI_QuenMatKhau().setVisible(true);
	}
}
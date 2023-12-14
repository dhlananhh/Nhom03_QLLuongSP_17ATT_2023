package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import dao.DAO_TaiKhoan;
import entity.TaiKhoan;


public class GUI_ThayDoiMatKhau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblMatKhauCu, lblMatKhauMoi, lblNhapLaiMKMoi;
	private JPasswordField txtMatKhauCu;
	private JPasswordField txtMatKhauMoi, txtNhapLaiMKMoi;
	private JButton btnXacNhan, btnHuy;
	private JCheckBox chkShowPwd;
	private DAO_TaiKhoan tk_dao;
	private Icon icon = new Icon();

	public GUI_ThayDoiMatKhau() {
		setTitle("Đổi mật khẩu");
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//connect
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tk_dao = new DAO_TaiKhoan();
		//panel
		JPanel pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		add(pnContent);

		JPanel pnTop = new JPanel();
		pnContent.add(pnTop, BorderLayout.NORTH);
		pnTop.setBackground(new Color(0, 102, 204));

		lblTieuDe = new JLabel("THAY ĐỔI MẬT KHẨU");
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
		b4 = Box.createVerticalBox();
		//b1
		b1.add(lblMatKhauCu = new JLabel("Mật khẩu cũ: "));
		b1.add(Box.createVerticalStrut(10));
		b1.add(txtMatKhauCu= new JPasswordField(20));
		//b2
		b2.add(lblMatKhauMoi = new JLabel("Mật khẩu mới: "));
		b2.add(Box.createVerticalStrut(10));
		b2.add(txtMatKhauMoi = new JPasswordField(20));
		//b3
		b3.add(lblNhapLaiMKMoi = new JLabel("Nhập lại mật khẩu mới: "));
		b3.add(Box.createVerticalStrut(10));
		b3.add(txtNhapLaiMKMoi = new JPasswordField(20));
		//preference
		lblMatKhauCu.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		lblMatKhauMoi.setPreferredSize(lblNhapLaiMKMoi.getPreferredSize());
		//b4
		chkShowPwd = new JCheckBox("Hiển thị mật khẩu");
		b4.add(chkShowPwd);
		//font
		lblMatKhauCu.setFont(new Font("Times New Roman", Font.BOLD, 15));
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
		pnBot.add(btnXacNhan = new JButton(icon.iconCheck));
		btnXacNhan.setText("Xác nhận");
		pnBot.add(btnHuy = new JButton(icon.iconHuy));
		btnHuy.setText("Hủy");

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
		txtMatKhauCu.setEchoChar('*');
		txtMatKhauMoi.setEchoChar('*');
    	txtNhapLaiMKMoi.setEchoChar('*');

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if (o.equals(btnXacNhan)) {
			String matKhauCu = txtMatKhauCu.getText();
            String matKhauMoi = txtMatKhauMoi.getText();
			String nhapLaiMK = txtNhapLaiMKMoi.getText();
            TaiKhoan tk = tk_dao.layTKTheoMatKhau(matKhauCu);
            if(tk.getMatKhau() == null) {
    			JOptionPane.showMessageDialog(null, "Mật khẩu không đúng!");
    			txtMatKhauCu.setText("");
    			txtMatKhauCu.requestFocus();
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
				txtMatKhauCu.setEchoChar((char) 0);
            } else {
            	txtMatKhauMoi.setEchoChar('*');
            	txtNhapLaiMKMoi.setEchoChar('*');
            	txtMatKhauCu.setEchoChar('*');
            }
		}
	}


	public static void main(String[] args) throws Exception {
		FlatLightLaf.setup();	
		new GUI_ThayDoiMatKhau().setVisible(true);
	}
}
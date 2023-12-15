package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import connection.ConnectDB;
import dao.DAO_TaiKhoan;
import entity.TaiKhoan;

import javax.swing.GroupLayout.Alignment;

public class GUI_DangNhap extends JFrame implements ActionListener {
	private JPanel pLeft;
	private JPanel pRight;
	private JButton btnLogin;
	private JButton btnQuenMatKhau;
	private JLabel lblTitle;
	private JLabel lblMaNV;
	private JLabel lblMatKhau;
	private JTextField txtMaNV;
	private JPasswordField txtPassword;
	private JLabel lblQuenMatKhau;
	private Font BVNPro;
	private JCheckBox chkHienMatKhau;
	private DAO_TaiKhoan tk_dao;

	public GUI_DangNhap() {
		setTitle("ĐĂNG NHẬP");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(1220, 540);
		setLocationRelativeTo(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tk_dao = new DAO_TaiKhoan();
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
			

			BVNPro = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/BeVietnamPro-Regular.ttf")).deriveFont(15f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(BVNPro);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		pLeft = new JPanel();
		pLeft.setBackground(new Color(255, 255, 255));
		pLeft.setMinimumSize(new java.awt.Dimension(400, 500));

		lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 30));
		lblTitle.setForeground(new java.awt.Color(0, 102, 102));

		lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setFont(BVNPro);

		txtMaNV = new JTextField();
		txtMaNV.setFont(BVNPro);

		lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(BVNPro);

		txtPassword = new JPasswordField();
		txtPassword.setFont(BVNPro);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(new java.awt.Color(0, 102, 102));
		btnLogin.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnLogin.setForeground(new java.awt.Color(255, 255, 255));

		lblQuenMatKhau = new JLabel("Bạn đã quên mật khẩu ?");
		lblQuenMatKhau.setFont(BVNPro);

		btnQuenMatKhau = new JButton("Quên mật khẩu");
		btnQuenMatKhau.setFont(BVNPro);
		btnQuenMatKhau.setForeground(new java.awt.Color(255, 51, 51));
		chkHienMatKhau = new JCheckBox("Hiển thị mật khẩu");
		chkHienMatKhau.setFont(BVNPro);
		GroupLayout gl_pLeft = new GroupLayout(pLeft);
		gl_pLeft.setHorizontalGroup(gl_pLeft.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pLeft.createSequentialGroup().addContainerGap(101, Short.MAX_VALUE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE).addGap(97))
				.addGroup(Alignment.LEADING, gl_pLeft.createSequentialGroup().addGap(30)
						.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addComponent(chkHienMatKhau)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING, false).addComponent(lblMaNV)
										.addComponent(txtMaNV).addComponent(lblMatKhau)
										.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
								.addGroup(gl_pLeft.createSequentialGroup().addComponent(lblQuenMatKhau)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnQuenMatKhau)))
						.addContainerGap(27, Short.MAX_VALUE)));
		gl_pLeft.setVerticalGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pLeft
				.createSequentialGroup().addGap(46)
				.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMaNV)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(lblMatKhau).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(14)
				.addComponent(chkHienMatKhau).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_pLeft.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuenMatKhau).addComponent(btnQuenMatKhau))
				.addContainerGap(130, Short.MAX_VALUE)));
		pLeft.setLayout(gl_pLeft);

		pRight = new JPanel() {
			private Image backgroundImage;

			{
				try {
					backgroundImage = ImageIO.read(new File("icons/loginLogo.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		pRight.setPreferredSize(new Dimension(800, 500));

		JLabel logoLabel = new JLabel(new ImageIcon("icons/logo.png"));

		GroupLayout gl_pRight = new GroupLayout(pRight);
		gl_pRight.setHorizontalGroup(gl_pRight.createParallelGroup(Alignment.LEADING).addGroup(gl_pRight
				.createSequentialGroup().addGap(141).addComponent(logoLabel).addContainerGap(144, Short.MAX_VALUE)));
		gl_pRight.setVerticalGroup(gl_pRight.createParallelGroup(Alignment.LEADING).addGroup(gl_pRight
				.createSequentialGroup().addGap(19).addComponent(logoLabel).addContainerGap(317, Short.MAX_VALUE)));
		pRight.setLayout(gl_pRight);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pLeft, BorderLayout.WEST);
		getContentPane().add(pRight, BorderLayout.EAST);
		btnLogin.addActionListener(this);
		btnQuenMatKhau.addActionListener(this);
		chkHienMatKhau.addActionListener(this);
		txtPassword.setEchoChar('*');

	}

	public static void main(String[] args) {
		FlatIntelliJLaf.setup();
		new GUI_DangNhap().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
			String tk = txtMaNV.getText();
			String pw = txtPassword.getText();
			if (!(tk.trim().equals("") && pw.trim().equals(""))) {

				if (kiemTraTaiKhoan()) {
					try {
						new GUI_TrangChu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không được để trống!");

			}
		} 	else if (o.equals(chkHienMatKhau)) {
			if (chkHienMatKhau.isSelected()) {
				txtPassword.setEchoChar((char) 0);
            } else {
            	txtPassword.setEchoChar('*');
            }
		} else if(o.equals(btnQuenMatKhau)) {
			new GUI_QuenMatKhau().setVisible(true);
			dispose();
		}

	}

	public boolean kiemTraTaiKhoan() {
		String tk = txtMaNV.getText();
		String pw = txtPassword.getText();
		if (tk_dao.layTKTheoTen(tk).getTenTK() == null) {
			return false;
		}
		if (tk_dao.layTKTheoMatKhau(pw).getMatKhau() == null) {
			return false;
		}
		return true;

	}
}

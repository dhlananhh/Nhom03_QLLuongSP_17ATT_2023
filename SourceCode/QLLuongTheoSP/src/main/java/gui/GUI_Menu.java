package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;


public class GUI_Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnHeader;
	private JLabel lblAccount;
	private JMenuBar menuBar;
	private JMenu menuTrangChu, menuHeThong, menuTroGiup, menuDanhMuc, menuTaiKhoan;
	private JMenuItem 	itemNhanVien, itemDiemDanh, itemChamCong, itemPhanCong,
						itemSanPham, itemCongDoan, itemThongKeLuong,
						itemDoiMatKhau, itemDangXuat;
	
	
	public GUI_Menu() {
		buildGUI();		
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
//		setSize(1200, 500);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createMenu();
		createAndDisplayGUI();
	}
	
		
	public void createMenu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menuTrangChu = new JMenu("Trang chủ");
		menuBar.add(menuTrangChu);
		
		menuDanhMuc = new JMenu("Danh mục");
		menuBar.add(menuDanhMuc);
		
		itemNhanVien = new JMenuItem("Nhân viên");
		menuDanhMuc.add(itemNhanVien);
		menuDanhMuc.addSeparator();
		
		itemDiemDanh = new JMenuItem("Điểm danh");
		menuDanhMuc.add(itemDiemDanh);
		menuDanhMuc.addSeparator();
		
		itemChamCong = new JMenuItem("Chấm công");
		menuDanhMuc.add(itemChamCong);
		menuDanhMuc.addSeparator();
		
		itemPhanCong = new JMenuItem("Phân công");
		menuDanhMuc.add(itemPhanCong);
		menuDanhMuc.addSeparator();
		
		itemSanPham = new JMenuItem("Sản phẩm");
		menuDanhMuc.add(itemSanPham);
		menuDanhMuc.addSeparator();
		
		itemCongDoan = new JMenuItem("Công đoạn");
		menuDanhMuc.add(itemCongDoan);
		menuDanhMuc.addSeparator();
		
		itemThongKeLuong = new JMenuItem("Thống kê lương");
		menuDanhMuc.add(itemThongKeLuong);
		
		menuHeThong = new JMenu("Hệ thống");
		menuBar.add(menuHeThong);
		
		itemDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		menuHeThong.add(itemDoiMatKhau);
		menuHeThong.addSeparator();
		
		itemDangXuat = new JMenuItem("Đăng xuất");
		menuHeThong.add(itemDangXuat);
		
		menuTroGiup = new JMenu("Trợ giúp");
		menuBar.add(menuTroGiup);
		
		menuTaiKhoan = new JMenu();
		menuBar.add(menuTaiKhoan);
		
	}
	
	
	public void createAndDisplayGUI() {
		Color title = new Color(0, 102, 204);
		Color button = new Color(0, 153, 204);
		
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		lblAccount = new JLabel("Xin chào NV001");
		pnContent.add(lblAccount, BorderLayout.NORTH);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public static void main(String[] args) {
		FlatCyanLightIJTheme.setup();
		
		new GUI_Menu().setVisible(true);
	}
}

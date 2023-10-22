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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuMau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent;
	private JMenuBar menuBar;
	private JMenu menuTrangChu, menuNhanVien, menuHeThong, menuCongNhan, menuDanhMuc, menuTroGiup;
	private JMenuItem 	dsNhanVien, dsChamCong, dsTienLuong, dsCongNhan,
						nhanVien, congNhan,
						congDoan, phongBan, chucDanh,
						themMoiNV, themMoiChamCong, themMoiTienLuong, themMoiCN,
						doiMatKhau, dangXuat, hdsd;
	
	
	public MenuMau() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
		createMenu();
	}
	
	
	public void createAndDisplayGUI() {
		Color bgColor = new Color(0, 153, 204);
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createMenu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menuTrangChu = new JMenu("Trang chủ");
		
		menuNhanVien = new JMenu("Nhân viên");
		dsNhanVien = new JMenuItem("Danh sách");
		themMoiNV = new JMenuItem("Thêm mới");
		menuNhanVien.add(dsNhanVien);
		menuNhanVien.add(themMoiNV);
		
		menuCongNhan = new JMenu("Công nhân");
		dsCongNhan = new JMenuItem("Danh sách");
		themMoiCN = new JMenuItem("Thêm mới");
		menuCongNhan.add(dsCongNhan);
		menuCongNhan.add(themMoiCN);
		
		menuDanhMuc = new JMenu("Danh mục");
		nhanVien = new JMenuItem("Nhân viên");
		congNhan = new JMenuItem("Công nhân");
		congDoan = new JMenuItem("Công đoạn");
		phongBan = new JMenuItem("Phòng ban");
		chucDanh = new JMenuItem("Chức danh");
		menuDanhMuc.add(nhanVien);
		menuDanhMuc.add(congNhan);
		menuDanhMuc.add(congDoan);
		menuDanhMuc.add(phongBan);
		menuDanhMuc.add(chucDanh);
		
		
		menuHeThong = new JMenu("Hệ thống");
		dangXuat = new JMenuItem("Đăng xuất");
		doiMatKhau = new JMenuItem("Đổi mật khẩu");
		menuHeThong.add(dangXuat);
		menuHeThong.add(doiMatKhau);
		
		menuTroGiup = new JMenu("Trợ giúp");
		hdsd = new JMenuItem("Hướng dẫn sử dụng phần mềm");
		menuTroGiup.add(hdsd);
		
		addActionEvent();
		
		menuBar.add(menuTrangChu);
		menuBar.add(menuNhanVien);
		menuBar.add(menuCongNhan);
		menuBar.add(menuDanhMuc);
		menuBar.add(menuHeThong);
		menuBar.add(menuTroGiup);
		
	}
	
	
	//đăng ký sự kiện cho các menu item
	public void addActionEvent() {
		dangXuat.addActionListener(this);
		doiMatKhau.addActionListener(this);
		nhanVien.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(dangXuat)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất khỏi chương trình không?", "Đăng xuất khỏi chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }			
		}
		else if (o.equals(nhanVien)) {
			try {
				new GUI_QuanLyNhanVien().setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (o.equals(doiMatKhau)) {
			try {
				new GUI_QuenMatKhau().setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		new MenuMau().setVisible(true);
	}
}

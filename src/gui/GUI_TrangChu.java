package gui;

import bettergui.component.DefaultForm;
import bettergui.menu.MenuEvent;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

public class GUI_TrangChu extends javax.swing.JFrame {

	public GUI_TrangChu() throws SQLException {
        java.net.URL url = ClassLoader.getSystemResource("bettergui/component/lghoaphat.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        setIconImage(img);
		initComponents();
		Component GUI_QuanLyNhanVien = new GUI_QuanLyNhanVienHanhChinh().getContentPane(),
				GUI_QuanLyCongNhan = new GUI_QuanLyCongNhan().getContentPane(),
				GUI_DiemDanh = new GUI_DiemDanh().getContentPane(),
				GUI_ChamCong = new GUI_BangChamCong().getContentPane(),
				GUI_QuanLySanPham = new GUI_QuanLySanPham().getContentPane(),
				GUI_TinhLuong = new GUI_TinhLuong().getContentPane(),
				GUI_ThayDoiMatKhau = new GUI_ThayDoiMatKhau().getContentPane(),
				GUI_BangChamCong = new GUI_BangChamCong().getContentPane(),
				GUI_PhanCong = new GUI_PhanCong().getContentPane(),
				GUI_TaoBangLuong = new GUI_TaoBangLuong().getContentPane(),
				GUI_TraCuuBangLuong = new GUI_TraCuuBangLuong().getContentPane(),
				GUI_LuongNhanVienHanhChinh = new GUI_LuongNhanVienHanhChinh().getContentPane();
		
				
		menu1.setEvent(new MenuEvent() {
			@Override
			public void selected(int index, int subIndex) {
				if (index == 0) {
					showForm(new GUI_Background().getContentPane());
				} else if (index == 1) {
					switch (subIndex) {
					case 1:
						new GUI_ThayDoiMatKhau().setVisible(true);
						break;
					case 2:
						System.exit(0);
						break;
					default:
						break;
					}
				} else if (index == 2) {
					switch (subIndex) {
					case 1:
						showForm(GUI_QuanLyNhanVien);
						break;
					case 2:
						showForm(GUI_QuanLyCongNhan);
						break;
					case 3:
						showForm(GUI_DiemDanh);
						break;		
					case 4:
						showForm(GUI_ChamCong);
						break;		
					case 5:
						showForm(GUI_QuanLySanPham);
						break;			
					case 6:
						showForm(GUI_TinhLuong);
						break;	
					default:
						break;
					}

				} else if (index == 3) {
					switch (subIndex) {
					case 1:
						showForm(GUI_BangChamCong);
						break;
					case 2:
						showForm(GUI_TraCuuBangLuong);
						break;
					case 3:
						showForm(GUI_PhanCong);
						break;		
					case 4:
						showForm(GUI_TaoBangLuong);
						break;		
					case 5:
						showForm(GUI_LuongNhanVienHanhChinh);
						break;			
					default:
						break;
					}

				}
			}
		});
	}

	private void showForm(Component com) {
		if (com != null) {
			body.removeAll();
			body.add(com);
			body.repaint();
			body.revalidate();
		}
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		scrollPaneWin111 = new bettergui.scroll.win11.ScrollPaneWin11();
		menu1 = new bettergui.menu.Menu();
		header1 = new bettergui.component.Header();
		body = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		//setExtendedState(MAXIMIZED_BOTH);
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() -100,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() -50);
		jPanel1.setBackground(new java.awt.Color(245, 245, 245));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 163, 163)));

		scrollPaneWin111.setBorder(null);
		scrollPaneWin111.setViewportView(menu1);

		body.setBackground(new java.awt.Color(245, 245, 245));
		body.setLayout(new java.awt.BorderLayout());

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 225,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addContainerGap())
				.addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
								.addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 565,
												Short.MAX_VALUE)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6)
												.addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addContainerGap()))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		// pack();
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		FlatLightLaf.setup();	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new GUI_TrangChu().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel body;
	private bettergui.component.Header header1;
	private javax.swing.JPanel jPanel1;
	private bettergui.menu.Menu menu1;
	private bettergui.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
	// End of variables declaration//GEN-END:variables
}

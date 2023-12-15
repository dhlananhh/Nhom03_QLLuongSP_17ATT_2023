package gui;

import bettergui.component.DefaultForm;
import bettergui.component.HomeForm;
import bettergui.menu.MenuEvent;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

public class GUI_TrangChu extends javax.swing.JFrame {

	public GUI_TrangChu() throws SQLException {
        java.net.URL url = ClassLoader.getSystemResource("bettergui/component/lghoaphat.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        setIconImage(img);
		initComponents();
		showForm(new HomeForm().getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu1.setEvent(new MenuEvent() {
			@Override
			public void selected(int index, int subIndex) {
				if (index == 0) {
					showForm(new GUI_ThongKe().getContentPane());
				} else if (index == 1) {
					switch (subIndex) {
					case 1:
						new GUI_ThayDoiMatKhau().setVisible(true);
						break;
					case 2:
						int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
			                    JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
			                System.exit(0); 
			            }
						break;
					default:
						break;
					}
				} else if (index == 2) {
					switch (subIndex) {
					case 1:
						showForm(new GUI_QuanLyNhanVienHanhChinh().getContentPane());
						break;
					case 2:
						showForm(new GUI_QuanLyCongNhan().getContentPane());
						break;
					case 3:
						showForm(new GUI_QuanLySanPham().getContentPane());
						break;		
					case 4:
						showForm(new GUI_LuongNhanVienHanhChinh().getContentPane());
						break;		
					case 5:
						showForm(new GUI_LuongCongNhanSanXuat().getContentPane());
						break;			
					default:
						break;
					}

				} else if (index == 3) {
					switch (subIndex) {
					case 1:
						try {
							showForm(new GUI_PhanCong().getContentPane());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 2:
						try {
							showForm(new GUI_BangChamCong().getContentPane());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						try {
							showForm(new GUI_DiemDanh().getContentPane());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;		
					case 4:
						//showForm(GUI_TaoBangLuong);
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
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() -394,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() -300);
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
		showForm(new GUI_ThongKe().getContentPane());
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

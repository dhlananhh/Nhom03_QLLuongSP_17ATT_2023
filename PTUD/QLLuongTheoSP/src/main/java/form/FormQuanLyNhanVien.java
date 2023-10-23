package form;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.sun.tools.javac.Main;
import com.toedter.calendar.JDateChooser;


public class FormQuanLyNhanVien extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnThietLapTTNV, pnThongTinNV, pnTacVu,
					pnFilter, pnTitle;
	private JLabel 	lblTitle, lblTimTheoMaNV, lblTimTheoChucDanh;
	private JLabel 	lblMaNV, lblHoTenNV, lblGioiTinh, 
					lblNgaySinh, lblChucDanh, lblNgayVaoLam;
	private JTextField txtMaNV, txtHoTenNV, txtTimTheoMaNV, txtlblNgayVaoLam;
	private JComboBox cbGioiTinh, cbPhongBan, cbTimTheoPhongBan;
	private JButton	btnThem, btnLuu, btnTim, btnLoc, btnXoa, btnSua, btnXoaTrang, btnThoat;
	private JDateChooser dcNgaySinh, dcNgayVaoLam;
	private DefaultTableModel model;
	private JTable table;
	private Font BVNPro;
	
	
	public FormQuanLyNhanVien() {
		try {
			String fileName = "src/main/java/fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		lblTitle = new JLabel("Danh sách nhân viên");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		FlatCyanLightIJTheme.setup();
		
		new FormQuanLyNhanVien().setVisible(true);
	}



	
}

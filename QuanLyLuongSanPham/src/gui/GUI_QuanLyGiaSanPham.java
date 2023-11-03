package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatLightLaf;


public class GUI_QuanLyGiaSanPham extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
		
	
	public GUI_QuanLyGiaSanPham() {
		//set JFrame properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//load fonts
		try {
			String fileName = "src/fonts/BeVietnamPro-Black.ttf";
			Font BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		//create new color
		Color headerColor = new Color(0, 102, 204);
		Color bgColor = new Color(245, 251, 255);
		Color buttonColor = new Color(0, 153, 204);
		Color panelColor = new Color(227, 243, 255);
		
		JPanel pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth chứa label tiêu đề
		JPanel pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(headerColor);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ GIÁ SẢN PHẨM");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		//pnCenter chứa pnTacVu và pnThongTin
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.NORTH);
		pnThongTin.setBackground(bgColor);
		
		JLabel lblCongDoan = new JLabel("Công đoạn: ");
		JComboBox cbCongDoan = new JComboBox();
		cbCongDoan.addItem("Mộc thô");
		cbCongDoan.addItem("Tinh chế");
		cbCongDoan.addItem("Lắp ráp");
		cbCongDoan.addItem("Kiểm tra");
		cbCongDoan.addItem("Đóng gói");
		
		JLabel lblSanPham = new JLabel("Sản phẩm: ");
		JComboBox cbSanPham = new JComboBox();
		String[] sp = 
			{	"Bàn nhân viên", "Ghế nhân viên", "Salon gỗ", "Bàn học sinh", "Ghế học sinh",
				"Bàn họp", "Ghế họp", "Bàn giám đốc", "Ghế giám đốc"};
		for (int i=0; i<sp.length; i++)
			cbSanPham.addItem(sp[i]);
		
		JLabel lblDonGia = new JLabel("Đơn giá: ");
		JTextField txtDonGia = new JTextField(20);
		
		lblCongDoan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbCongDoan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblSanPham.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbSanPham.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblDonGia.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtDonGia.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		pnThongTin.add(lblCongDoan);
		pnThongTin.add(Box.createHorizontalStrut(10));
		pnThongTin.add(cbCongDoan);
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(lblSanPham);
		pnThongTin.add(Box.createHorizontalStrut(10));
		pnThongTin.add(cbSanPham);
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(lblDonGia);
		pnThongTin.add(Box.createHorizontalStrut(10));
		pnThongTin.add(txtDonGia);
		
		JPanel pnTable = new JPanel();
		pnCenter.add(pnTable, BorderLayout.CENTER);
		pnTable.setBackground(bgColor);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("STT");
		model.addColumn("Công đoạn - Sản phẩm");
		model.addColumn("Đơn giá");
		
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		pnTable.add(scrollPane);
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	}
	
	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyGiaSanPham().setVisible(true);
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class GUI_QuanLySanPham extends JFrame implements ActionListener {
	private JPanel pnContent;
	private JLabel lblTieuDe, lblMSP, lblMCD, lblTenSP, lblTenCD, lblLuongSP, lblTrangThai, lblSoLuong, lblGiaThanh;
	private DefaultTableModel modelSP;
	private JTable tableSP, tableCD;
	private DefaultTableModel modelCD;
	private JButton btnLoc, btnThem, btnSua, btnXoa;
	private JComboBox<String> cbLoc, cbTrangThai;
	private JTextField txtLoc, txtMSP, txtMCD, txtTenSP, txtTenCD, txtLuongSP, txtSoLuong, txtGiaThanh;
	private Font BVNPro;
	public GUI_QuanLySanPham() {
		setTitle("Quản lý sản phẩm");
		setSize(1300, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		try {
			String fileName = "fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		add(pnContent);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(0, 102, 204));
		
		lblTieuDe = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		pnTop.add(lblTieuDe);
		pnContent.add(pnTop, BorderLayout.NORTH);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setBackground(new Color(245, 251, 255));
		
		JPanel pnBot = new JPanel();
		pnContent.add(pnBot, BorderLayout.SOUTH);
		pnBot.setBackground(new Color(245, 251, 255));
		pnBot.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		//Center
		Box b ,b1, b2;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		//b1
		b1.add(Box.createHorizontalStrut(700));
		b1.add(btnLoc = new JButton("Lọc"));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbLoc = new JComboBox<String>());
		cbLoc.addItem("Mã sản phẩm");
		cbLoc.addItem("Tên sản phẩm");
		cbLoc.addItem("Trạng thái");
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtLoc = new JTextField(15));
		
		btnLoc.setBackground(new Color(0, 153, 204));
		btnLoc.setForeground(Color.WHITE);
		//tạo panel chứa box b3 chứa dữ liệu.
		JPanel pnBoxContent = new JPanel();
		pnBoxContent.setBackground(new Color(206, 234, 255));
		b2.add(pnBoxContent);
	
		Box b3, b3_1, b3_2, b3_3, b3_4, b3_5, b3_6;
		b3 = Box.createVerticalBox();
		b3_1 = Box.createHorizontalBox();
		b3_2 = Box.createHorizontalBox();
		b3_3 = Box.createHorizontalBox();
		b3_4 = Box.createHorizontalBox();
		b3_5 = Box.createHorizontalBox();
		b3_6 = Box.createHorizontalBox();
		//add giá trị
		b3_1.add(lblMSP = new JLabel("Mã sản phẩm:"));
		b3_1.add(Box.createHorizontalStrut(20));
		b3_1.add(txtMSP = new JTextField(15));
		b3_1.add(Box.createHorizontalStrut(200));
		b3_1.add(lblMCD = new JLabel("Mã công đoạn:"));
		b3_1.add(Box.createHorizontalStrut(20));
		b3_1.add(txtMCD = new JTextField(15));
		txtMSP.setEditable(false);
		
		b3_2.add(lblTenSP = new JLabel("Tên sản phẩm:"));
		b3_2.add(Box.createHorizontalStrut(20));
		b3_2.add(txtTenSP = new JTextField(15));
		b3_2.add(Box.createHorizontalStrut(200));
		b3_2.add(lblTenCD = new JLabel("Tên công đoạn:"));
		b3_2.add(Box.createHorizontalStrut(20));
		b3_2.add(txtTenCD = new JTextField(15));
		
		b3_3.add(lblTrangThai = new JLabel("Trạng thái:"));
		b3_3.add(Box.createHorizontalStrut(20));
		b3_3.add(cbTrangThai = new JComboBox<String>());
		cbTrangThai.addItem("Còn sản xuất");
		cbTrangThai.addItem("Ngưng sản xuất");
		b3_3.add(Box.createHorizontalStrut(248));
		b3_3.add(lblLuongSP = new JLabel("Lương sản phẩm:"));
		b3_3.add(Box.createHorizontalStrut(22));
		b3_3.add(txtLuongSP = new JTextField(15));
		
		b3_4.add(lblSoLuong = new JLabel("Số lượng:"));
		b3_4.add(Box.createHorizontalStrut(20));
		b3_4.add(txtSoLuong = new JTextField(15));
		b3_4.add(Box.createHorizontalStrut(480));
		
		b3_5.add(lblGiaThanh = new JLabel("Giá thành:"));
		b3_5.add(Box.createHorizontalStrut(20));
		b3_5.add(txtGiaThanh = new JTextField(15));
		b3_5.add(Box.createHorizontalStrut(480));
		
		b3_6.add(btnThem = new JButton("Thêm"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add(btnSua = new JButton("Sửa"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add(btnXoa = new JButton("Xóa"));
		
		btnThem.setBackground(new Color(0, 153, 204));
		btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(0, 153, 204));
        btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(0, 153, 204));
        btnXoa.setForeground(Color.WHITE);
		//preferencec b3
		lblMSP.setPreferredSize(lblTenSP.getPreferredSize());
		lblTrangThai.setPreferredSize(lblTenSP.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTenSP.getPreferredSize());
		lblGiaThanh.setPreferredSize(lblTenSP.getPreferredSize());
		
		lblMCD.setPreferredSize(lblLuongSP.getPreferredSize());
		lblTenCD.setPreferredSize(lblLuongSP.getPreferredSize());
		//add có box b3 nhỏ
		b3.add(Box.createRigidArea(new Dimension(20,20)));
		b3.add(b3_1);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_2);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_3);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_4);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_5);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_6);
		pnBoxContent.add(b3);
		//add box b1 b2 vào b lớn
		b.add(Box.createRigidArea(new Dimension(20,20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(20,20)));
		b.add(b2);
		pnCenter.add(b);
		//bot
		Box c, c1, c2;
		c = Box.createHorizontalBox();
		//table San pham
		String col2[] = {"Mã sản phẩm", "Tên sản phẩm","Số lượng","Trạng thái","Giá thành"};
		modelSP = new DefaultTableModel(col2, 0);
		tableSP = new JTable(modelSP);
		JScrollPane scrollSP = new JScrollPane(tableSP);
		scrollSP.setPreferredSize(new Dimension(500, 250));
		c.add(scrollSP);
		c.add(Box.createHorizontalStrut(80));
		//table Cong doan
		String col3[] = {"Mã công đoạn", "Tên công đoạn","Lương SP"};
		modelCD = new DefaultTableModel(col3, 0);
		tableCD = new JTable(modelCD);
		JScrollPane scrollCD = new JScrollPane(tableCD);
		scrollCD.setPreferredSize(new Dimension(500, 250));
		c.add(scrollCD);
		pnBot.add(c);

		
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();		
		new GUI_QuanLySanPham().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

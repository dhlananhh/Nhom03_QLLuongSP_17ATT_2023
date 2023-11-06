package gui;


import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class GUI_QuanLyCongNhan extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLoc, btnThietLapLuong;
	
	
	public GUI_QuanLyCongNhan() {
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//load fonts
		try {
			String fileName = "fonts/BeVietnamPro-Black.ttf";
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
		
		JPanel pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(headerColor);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ CÔNG NHÂN");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		JLabel lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		JTextField txtHoTenNV = new JTextField();
		
		JLabel lblGioiTinh = new JLabel("Giới tính: ");
		JComboBox cbGioiTinh = new JComboBox();
		cbGioiTinh.addItem("---Chọn---");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
		JDateChooser dcNgaySinh = new JDateChooser();
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		JTextField txtDiaChi = new JTextField();
		
		JLabel lblCCCD = new JLabel("CMND/CCCD: ");
		JTextField txtCCCD = new JTextField();
		
		JLabel lblBHXH = new JLabel("BHXH: ");
		JTextField txtBHXH = new JTextField();
		
		JLabel lblMST = new JLabel("MST: ");
		JTextField txtMST = new JTextField();
		
		JLabel lblNgayVao = new JLabel("Ngày vào: ");
		JDateChooser dcNgayVao = new JDateChooser();
		dcNgayVao.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		JLabel lblTayNghe = new JLabel("Tay nghề: ");
		JTextField txtTayNghe = new JTextField();
		
		JLabel lblToSanXuat = new JLabel("Tổ sản xuất: ");
		JComboBox cbToSanXuat = new JComboBox();
		cbToSanXuat.addItem("---Chọn---");
		cbToSanXuat.addItem("1");
		cbToSanXuat.addItem("2");
		cbToSanXuat.addItem("3");
		cbToSanXuat.addItem("4");
		cbToSanXuat.addItem("5");
		
		JLabel lblTrangThai = new JLabel("Trạng thái: ");
		JComboBox cbTrangThai = new JComboBox();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		JLabel lblCheDoLuong = new JLabel("Chế độ lương: ");
		JComboBox cbCheDoLuong = new JComboBox();
		cbCheDoLuong.addItem("---Chọn---");
		cbCheDoLuong.addItem("Lương hành chính");
		cbCheDoLuong.addItem("Lương sản phẩm");
		
		JLabel lblLuongSP = new JLabel("Lương sản phẩm: ");
		JTextField txtLuongSP = new JTextField();
		JLabel lblPhuCap = new JLabel("Phụ cấp: ");
		JTextField txtPhuCap = new JTextField();
		JLabel lblGiamTru = new JLabel("Giảm trừ: ");
		JTextField txtGiamTru = new JTextField();
		JLabel lblTamUng = new JLabel("Tạm ứng: ");
		JTextField txtTamUng = new JTextField();
		
		JLabel lblBangCap = new JLabel("Bằng cấp: ");
		JComboBox cbBangCap = new JComboBox();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Cử nhân");
		cbBangCap.addItem("Thạc sỹ");
		cbBangCap.addItem("Tiến sỹ");
		
		JLabel lblPhongBan = new JLabel("Phòng ban: ");
		JComboBox cbPhongBan = new JComboBox();
		cbPhongBan.addItem("---Chọn---");
		cbPhongBan.addItem("Quản lý");
		cbPhongBan.addItem("Hành chính");
		cbPhongBan.addItem("Kế toán");
		cbPhongBan.addItem("Sản xuất");
		
		lblHoTenNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtHoTenNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblNgaySinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		dcNgaySinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblDiaChi.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtDiaChi.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblCCCD.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtCCCD.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblBHXH.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtBHXH.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblMST.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtMST.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblNgayVao.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		dcNgayVao.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTayNghe.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTayNghe.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblToSanXuat.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbToSanXuat.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTrangThai.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbTrangThai.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongSP.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongSP.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblPhuCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtPhuCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.NORTH);
		pnThongTin.setBackground(bgColor);
		
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		Box d = Box.createVerticalBox();
		
		pnThongTin.add(a);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(b);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(c);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(d);
		
		//box a
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		Box a4 = Box.createHorizontalBox();
				
		a1.add(lblHoTenNV);
		a1.add(txtHoTenNV);
		a2.add(lblGioiTinh);
		a2.add(cbGioiTinh);
		a3.add(lblNgaySinh);
		a3.add(dcNgaySinh);
		a4.add(lblDiaChi);
		a4.add(txtDiaChi);
		
		lblGioiTinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblHoTenNV.getPreferredSize());
		
		a.add(a1);
		a.add(Box.createVerticalStrut(10));
		a.add(a2);
		a.add(Box.createVerticalStrut(10));
		a.add(a3);
		a.add(Box.createVerticalStrut(10));
		a.add(a4);
		
		//box b
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		b1.add(lblCCCD);
		b1.add(txtCCCD);
		b2.add(lblBHXH);
		b2.add(txtBHXH);
		b3.add(lblMST);
		b3.add(txtMST);
		b4.add(lblNgayVao);
		b4.add(dcNgayVao);
		
		lblBHXH.setPreferredSize(lblCCCD.getPreferredSize());
		lblMST.setPreferredSize(lblCCCD.getPreferredSize());
		lblNgayVao.setPreferredSize(lblCCCD.getPreferredSize());
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		
		//box c
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		Box c4 = Box.createHorizontalBox();
		
//		
		c1.add(lblPhongBan);
		c1.add(cbPhongBan);
		c2.add(lblToSanXuat);
		c2.add(cbToSanXuat);
		c3.add(lblTrangThai);
		c3.add(cbTrangThai);
		c4.add(lblBangCap);
		c4.add(cbBangCap);
		
		lblPhongBan.setPreferredSize(lblToSanXuat.getPreferredSize());
		lblToSanXuat.setPreferredSize(lblToSanXuat.getPreferredSize());
		lblTrangThai.setPreferredSize(lblToSanXuat.getPreferredSize());
		lblBangCap.setPreferredSize(lblToSanXuat.getPreferredSize());
		
		c.add(c1);
		c.add(Box.createVerticalStrut(10));
		c.add(c2);
		c.add(Box.createVerticalStrut(10));
		c.add(c3);
		c.add(Box.createVerticalStrut(10));
		c.add(c4);
		
		Box d1 = Box.createHorizontalBox();
		Box d2 = Box.createHorizontalBox();
		Box d3 = Box.createHorizontalBox();
		Box d4 = Box.createHorizontalBox();
		
		d1.add(lblTayNghe);
		d1.add(txtTayNghe);
		d2.add(lblLuongSP);
		d2.add(txtLuongSP);
		d3.add(lblPhuCap);
		d3.add(txtPhuCap);
		
		lblPhuCap.setPreferredSize(lblLuongSP.getPreferredSize());
		lblTayNghe.setPreferredSize(lblLuongSP.getPreferredSize());
		
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d.add(d2);
		d.add(Box.createVerticalStrut(10));
		d.add(d3);
		d.add(Box.createVerticalStrut(10));
		
		JPanel pnTacVu = new JPanel();
		pnCenter.add(pnTacVu, BorderLayout.CENTER);
		pnTacVu.setBackground(bgColor);
		
		JComboBox cbLoc = new JComboBox();
		cbLoc.addItem("---Chọn---");
		cbLoc.addItem("Giới tính");
		cbLoc.addItem("Trạng thái");
		cbLoc.addItem("Phòng ban");
		cbLoc.addItem("Chức danh");
		JTextField txtLoc = new JTextField();
		btnLoc = new JButton("Lọc");
		
		pnTacVu.add(cbLoc);
		pnTacVu.add(txtLoc);
		pnTacVu.add(btnLoc);
		
		cbLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		btnLoc.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		
		JPanel pnTable = new JPanel();
		pnCenter.add(pnTable, BorderLayout.SOUTH);
		pnTable.setBackground(bgColor);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("STT");
		model.addColumn("Họ tên");
		model.addColumn("Giới tính");
		model.addColumn("Ngày sinh");
		model.addColumn("Địa chỉ");
		model.addColumn("CCCD");
		model.addColumn("BHXH");
		model.addColumn("MST");
		model.addColumn("Ngày vào");
		model.addColumn("Phòng ban");
		model.addColumn("Tổ SX");
		model.addColumn("Trạng thái");
		model.addColumn("Bằng cấp");
		model.addColumn("Tay nghề");
		model.addColumn("Lương SP");
		model.addColumn("Phụ cấp");
		
		
		JScrollPane sp = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(1200, 400));
		pnTable.add(sp);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyCongNhan().setVisible(true);
	}
}

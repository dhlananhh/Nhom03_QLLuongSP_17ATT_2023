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

import connection.ConnectDB;
import dao.DAO_CongNhan;
import entity.CongNhanSanXuat;
import entity.SanPham;
import entity.ToSanXuat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.awt.Component;
import javax.swing.BoxLayout;


public class GUI_QuanLyCongNhan extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLoc, btnThietLapLuong;
	private DAO_CongNhan dao_cn;
	private int soLuongCN;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JTextField txtHoTenNV, txtDiaChi, txtCCCD, txtSDT, txtTayNghe, txtLuongSP, txtPhuCap;
	private JLabel lblGioiTinh, lblNgaySinh, lblHoTenNV, lblDiaChi, lblCCCD, lblSDT, lblNgayVao, lblTayNghe, lblToSanXuat;
	private JComboBox<String> cbGioiTinh, cbToSanXuat, cbTrangThai, cbBangCap,cbLoc;
	private JDateChooser dcNgaySinh, dcNgayVao;
	private JLabel lblTrangThai, lblLuongSP, lblPhuCap, lblBangCap;
	private JTextField txtLoc;
	private Icon icon = new Icon();
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;

	public GUI_QuanLyCongNhan() {
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//connect
			try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		dao_cn = new DAO_CongNhan();
		//load fonts
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
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
		
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField();
		
		lblGioiTinh = new JLabel("Giới tính: ");
		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.addItem("---Chọn---");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		lblNgaySinh = new JLabel("Ngày sinh: ");
		dcNgaySinh = new JDateChooser();
		
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField();
		
		lblCCCD = new JLabel("CMND/CCCD: ");
		txtCCCD = new JTextField();
		
		lblSDT = new JLabel("SDT: ");
		txtSDT = new JTextField();
		
		lblNgayVao = new JLabel("Ngày vào: ");
		dcNgayVao = new JDateChooser();
		dcNgayVao.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblTayNghe = new JLabel("Tay nghề: ");
		txtTayNghe = new JTextField();
		
		lblToSanXuat = new JLabel("Tổ sản xuất: ");
		cbToSanXuat = new JComboBox<String>();
		cbToSanXuat.addItem("---Chọn---");
		cbToSanXuat.addItem("1");
		cbToSanXuat.addItem("2");
		cbToSanXuat.addItem("3");
		cbToSanXuat.addItem("4");
		cbToSanXuat.addItem("5");
		
		lblTrangThai = new JLabel("Trạng thái: ");
		cbTrangThai = new JComboBox<String>();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		
		lblLuongSP = new JLabel("Lương sản phẩm: ");
		txtLuongSP = new JTextField();
		lblPhuCap = new JLabel("Phụ cấp: ");
		txtPhuCap = new JTextField();
		//JLabel lblGiamTru = new JLabel("Giảm trừ: ");
		//JTextField txtGiamTru = new JTextField();
		//JLabel lblTamUng = new JLabel("Tạm ứng: ");
		//JTextField txtTamUng = new JTextField();
		
		lblBangCap = new JLabel("Bằng cấp: ");
		cbBangCap = new JComboBox<String>();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Trung Cap");
		cbBangCap.addItem("Cao Dang");
		cbBangCap.addItem("Dai Hoc");
	
		
		lblHoTenNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtHoTenNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblNgaySinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		dcNgaySinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblDiaChi.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtDiaChi.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblCCCD.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtCCCD.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblSDT.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtSDT.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//lblMST.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//txtMST.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
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
		//lblCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//cbCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongSP.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongSP.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblPhuCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtPhuCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//lblGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//txtGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//lblTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//txtTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//lblPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		//cbPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.NORTH);
		pnThongTin.setBackground(bgColor);
		
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		Box d = Box.createVerticalBox();
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));
		
		horizontalStrut = Box.createHorizontalStrut(20);
		pnThongTin.add(horizontalStrut);
		
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
		b2.add(lblSDT);
		b2.add(txtSDT);
		//b3.add(lblMST);
		//b3.add(txtMST);
		b4.add(lblNgayVao);
		b4.add(dcNgayVao);
		
		lblSDT.setPreferredSize(lblCCCD.getPreferredSize());
		//lblMST.setPreferredSize(lblCCCD.getPreferredSize());
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
		//c1.add(lblPhongBan);
		//c1.add(cbPhongBan);
		c2.add(lblToSanXuat);
		c2.add(cbToSanXuat);
		c3.add(lblTrangThai);
		c3.add(cbTrangThai);
		c4.add(lblBangCap);
		c4.add(cbBangCap);
		
		//lblPhongBan.setPreferredSize(lblToSanXuat.getPreferredSize());
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
		
		cbLoc = new JComboBox<String>();
		cbLoc.setPreferredSize(new Dimension(100, 15));
		cbLoc.addItem("---Chọn---");
		cbLoc.addItem("Giới tính");
		cbLoc.addItem("Trạng thái");
		cbLoc.addItem("Tổ sản xuất");
		txtLoc = new JTextField();
		txtLoc.setPreferredSize(new Dimension(10, 15));
		btnLoc = new JButton(icon.iconTim);
		btnLoc.setText("Lọc");
		pnTacVu.setLayout(new BoxLayout(pnTacVu, BoxLayout.X_AXIS));
		
		horizontalStrut_2 = Box.createHorizontalStrut(300);
		pnTacVu.add(horizontalStrut_2);
		
		pnTacVu.add(cbLoc);
		pnTacVu.add(txtLoc);
		pnTacVu.add(btnLoc);
		
		cbLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		btnLoc.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		pnTacVu.add(horizontalStrut_3);
		Box d4 = Box.createHorizontalBox();
		pnTacVu.add(d4);
		//
		d4.add(btnThem = new JButton(icon.iconThem));
		btnThem.setText("Thêm");
		d4.add(btnSua = new JButton(icon.iconSua));
		btnSua.setText("Sửa");
		d4.add(btnXoa = new JButton(icon.iconXoa));
		btnXoa.setText("Xóa");
		d4.add(btnXoaTrang = new JButton(icon.iconTaiLai));
		
		horizontalStrut_4 = Box.createHorizontalStrut(100);
		horizontalStrut_4.setPreferredSize(new Dimension(300, 0));
		pnTacVu.add(horizontalStrut_4);
		btnXoaTrang.setText("Tải lại");
		//Action
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		JPanel pnTable = new JPanel();
		pnTable.setPreferredSize(new Dimension(3, 430));
		pnCenter.add(pnTable, BorderLayout.SOUTH);
		pnTable.setBackground(bgColor);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("STT");
		model.addColumn("Mã CN");
		model.addColumn("Họ tên");
		model.addColumn("Giới tính");
		model.addColumn("Ngày sinh");
		model.addColumn("Địa chỉ");
		model.addColumn("CCCD");
		model.addColumn("SDT");
		//model.addColumn("MST");
		model.addColumn("Ngày vào");
		//model.addColumn("Phòng ban");
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
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnThongTin.add(horizontalStrut_1);
		btnLoc.addActionListener(this);
		table.addMouseListener(this);
		cbLoc.addActionListener(this);
		//
		layDuLieuCN();
	}
	public void layDuLieuCN() {
		List<CongNhanSanXuat> dscn  = dao_cn.getDSCongNhan();
		soLuongCN = dscn.size();
		int i = 1;
		for (CongNhanSanXuat cn : dscn) {
			model.addRow(new Object[] {i++,
				cn.getMaCN(), cn.getHoTenCN(), cn.isGioiTinh()==true?"Nam":"Nữ",cn.getNgaySinh(),
						cn.getDiaChi(),cn.getCCCD(),cn.getSDT(),cn.getNgayVao(),cn.getToSanXuat().getMaToSX(),
						cn.isTrangThai()==true?"Đang làm":"Nghỉ việc",cn.getBangCap(),cn.getTayNghe(),
								cn.getLuongSanPham(),cn.getPhuCap()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			themDuLieuSP();
		}
		if(o.equals(btnSua)) {
			capNhatCN();

		}
		if(o.equals(btnXoaTrang)) {
			txtHoTenNV.setText("");
			txtCCCD.setText("");
			txtDiaChi.setText("");
			txtLuongSP.setText("");
			txtPhuCap.setText("");
			txtSDT.setText("");
			txtTayNghe.setText("");
			cbGioiTinh.setSelectedIndex(0);
			cbBangCap.setSelectedIndex(0);
			cbToSanXuat.setSelectedIndex(0);
			cbTrangThai.setSelectedIndex(0);
			dcNgaySinh.setDate(null);
			dcNgayVao.setDate(null);
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			model.removeRow(row);
			txtHoTenNV.setText("");
			txtCCCD.setText("");
			txtDiaChi.setText("");
			txtLuongSP.setText("");
			txtPhuCap.setText("");
			txtSDT.setText("");
			txtTayNghe.setText("");
			cbGioiTinh.setSelectedIndex(0);
			cbBangCap.setSelectedIndex(0);
			cbToSanXuat.setSelectedIndex(0);
			cbTrangThai.setSelectedIndex(0);
			dcNgaySinh.setDate(null);
			dcNgayVao.setDate(null);
		}
		if(o.equals(btnLoc)) {
			if(cbLoc.getSelectedItem().equals("Giới tính")) {
				model.getDataVector().removeAllElements();
				timTheoTieuChi("Giới tính");
			}
			else if(cbLoc.getSelectedItem().equals("Trạng thái")) {
				model.getDataVector().removeAllElements();
				timTheoTieuChi("Trạng thái");
			}
			else if(cbLoc.getSelectedItem().equals("Tổ sản xuất")) {
				model.getDataVector().removeAllElements();
				timTheoTieuChi("Tổ sản xuất");
			}
		}
	}
	public boolean themDuLieuSP() {	
		String tenCN = txtHoTenNV.getText();
		boolean gioiTinh = (cbGioiTinh.getSelectedItem().toString().equals("Nam"))?true:false;
		Date ngaySinh = dcNgaySinh.getDate();
		String diaChi = txtDiaChi.getText();
		String cccd = txtCCCD.getText();
		String sdt = txtSDT.getText();
		Date ngayVao = dcNgaySinh.getDate();
		int maTSX = Integer.parseInt(cbToSanXuat.getSelectedItem().toString());
		boolean trangThai = (cbTrangThai.getSelectedItem().toString().equals("Đang làm"))?true:false;
		String bangCap = cbBangCap.getSelectedItem().toString();
		int tayNghe = Integer.parseInt(txtTayNghe.getText());
		double luongSP = Double.parseDouble(txtLuongSP.getText());
		double phuCap = Double.parseDouble(txtPhuCap.getText());
		
		String maCN = String.format("%s%02d","CN",++soLuongCN);
		ToSanXuat tsx = new ToSanXuat(maTSX);
		CongNhanSanXuat cn = new CongNhanSanXuat(maCN, tenCN, gioiTinh, new java.sql.Date(ngaySinh.getTime()), diaChi, cccd, sdt, new java.sql.Date(ngayVao.getTime()), tsx, trangThai, bangCap, tayNghe, luongSP, phuCap);
		try {
			dao_cn.themCN(cn);
			int i = 1;
			model.addRow(new Object[] {i++,
					cn.getMaCN(), cn.getHoTenCN(), cn.isGioiTinh()==true?"Nam":"Nữ",cn.getNgaySinh(),
							cn.getDiaChi(),cn.getCCCD(),cn.getSDT(),cn.getNgayVao(),cn.getToSanXuat().getMaToSX(),
							cn.isTrangThai()==true?"Đang làm":"Nghỉ việc",cn.getBangCap(),cn.getTayNghe(),
									cn.getLuongSanPham(),cn.getPhuCap()
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public void capNhatCN() {
		//String maCN = txtMaCN.getText();
		String tenCN = txtHoTenNV.getText();
		boolean gioiTinh = (cbGioiTinh.getSelectedItem().toString().equals("Nam"))?true:false;
		Date ngaySinh = dcNgaySinh.getDate();
		String diaChi = txtDiaChi.getText();
		String cccd = txtCCCD.getText();
		String sdt = txtSDT.getText();
		Date ngayVao = dcNgaySinh.getDate();
		int maTSX = Integer.parseInt(cbToSanXuat.getSelectedItem().toString());
		boolean trangThai = (cbTrangThai.getSelectedItem().toString().equals("Đang làm"))?true:false;
		String bangCap = cbBangCap.getSelectedItem().toString();
		int tayNghe = Integer.parseInt(txtTayNghe.getText());
		double luongSP = Double.parseDouble(txtLuongSP.getText());
		double phuCap = Double.parseDouble(txtPhuCap.getText());
		ToSanXuat tsx = new ToSanXuat(maTSX);
		CongNhanSanXuat cn = new CongNhanSanXuat(null, tenCN, gioiTinh, new java.sql.Date(ngaySinh.getTime()), diaChi, cccd, sdt, new java.sql.Date(ngayVao.getTime()), tsx, trangThai, bangCap, tayNghe, luongSP, phuCap);

	}
	public void timTheoTieuChi(String luaChon) {
	    List<CongNhanSanXuat> dscn;
	    boolean giaTriTieuChi;
	    String duLieu = txtLoc.getText();

	    if (luaChon.equals("Giới tính")) {
	    	if(duLieu.equals("Nam")) {
	    		giaTriTieuChi = true;
		        dscn = dao_cn.layCongNhanTheoGioiTinh(giaTriTieuChi);
	    	}
	    	else {
	    		giaTriTieuChi = false;
		        dscn = dao_cn.layCongNhanTheoGioiTinh(giaTriTieuChi);
	    	}
	        
	    } else if (luaChon.equals("Trạng thái")) {
	    	if(duLieu.equals("Đang làm")) {
	    		giaTriTieuChi = true;
		        dscn = dao_cn.layCongNhanTheoTrangThai(giaTriTieuChi);
	    	}
	    	else {
	    		giaTriTieuChi = false;
		        dscn = dao_cn.layCongNhanTheoTrangThai(giaTriTieuChi);
	    	}
	    } else if(luaChon.equals("Tổ sản xuất")){
	    	int gtTSX = Integer.parseInt(duLieu);
	        dscn = dao_cn.layCongNhanTheoTSX(gtTSX);
	    }else {
	    	return;
	    } 
	    int i = 1;
	    for (CongNhanSanXuat cn : dscn) {
	        model.addRow(new Object[]{
	                i++, cn.getMaCN(), cn.getHoTenCN(), cn.isGioiTinh() ? "Nam" : "Nữ", cn.getNgaySinh(),
	                cn.getDiaChi(), cn.getCCCD(), cn.getSDT(), cn.getNgayVao(), cn.getToSanXuat().getMaToSX(),
	                cn.isTrangThai() ? "Đang làm" : "Nghỉ việc", cn.getBangCap(), cn.getTayNghe(),
	                cn.getLuongSanPham(), cn.getPhuCap()
	        });
	    }
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyCongNhan().setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtHoTenNV.setText(model.getValueAt(row, 2).toString());
		cbGioiTinh.setSelectedItem(model.getValueAt(row, 3).toString());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dcNgaySinh.setDate(dateFormat1.parse(model.getValueAt(row, 4).toString()));
			dcNgayVao.setDate(dateFormat1.parse(model.getValueAt(row, 8).toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtDiaChi.setText(model.getValueAt(row, 5).toString());
		txtCCCD.setText(model.getValueAt(row, 6).toString());
		txtSDT.setText(model.getValueAt(row, 7).toString());
		cbToSanXuat.setSelectedItem(model.getValueAt(row, 9).toString());
		cbTrangThai.setSelectedItem(model.getValueAt(row, 10).toString());
		cbBangCap.setSelectedItem(model.getValueAt(row, 11).toString());
		txtTayNghe.setText(model.getValueAt(row, 12).toString());
		txtLuongSP.setText(model.getValueAt(row, 13).toString());
		txtPhuCap.setText(model.getValueAt(row, 14).toString());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

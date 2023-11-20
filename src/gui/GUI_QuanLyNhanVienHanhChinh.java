package gui;


import javax.swing.JFrame;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAO_NhanVienHanhChinh;
import dao.DAO_PhongBan;
import dao.DAO_TaiKhoan;
import entity.NhanVienHanhChinh;
import entity.PhongBan;
import entity.TaiKhoan;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class GUI_QuanLyNhanVienHanhChinh extends JFrame implements ActionListener, MouseListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JButton btnLoc;
	private JLabel lblTieuDe, lblError;
	private JLabel lblMaNV, lblHoTenNV, lblGioiTinh, lblNgaySinh, lblDiaChi;
	private JLabel lblCCCD, lblSDT, lblNgayVao;
	private JLabel lblPhongBan, lblTrangThai, lblBangCap;
	private JLabel lblLuongCoBan, lblPhuCap, lblHeSoLuong, lblTaiKhoan, lblEmail;
	private JTextField txtMaNV, txtHoTenNV, txtDiaChi, txtCCCD, txtSDT, txtTaiKhoan, txtEmail;
	private JTextField txtLuongCoBan, txtPhuCap, txtHeSoLuong, txtLoc;
	private JDateChooser dcNgaySinh, dcNgayVao;
	private JComboBox cbGioiTinh, cbPhongBan;
	private JComboBox cbTrangThai, cbBangCap, cbLoc;
	private JButton btnThem, btnSua, btnXoa, btnXoaRong, btnLuu;
	private Color bgColor;
	
	private JPanel pnTable;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	
	private DAO_NhanVienHanhChinh dao_nv;
	private List<NhanVienHanhChinh> listNhanVien = new ArrayList<NhanVienHanhChinh>();
	
	private DAO_PhongBan dao_pb;
	private List<PhongBan> listPhongBan = new ArrayList<PhongBan>();
	
	private DAO_TaiKhoan dao_tk;
	private List<TaiKhoan> listTaiKhoan = new ArrayList<TaiKhoan>();
	
	private Map<String, Boolean> daNhap = new HashMap<>();
	
	
	public GUI_QuanLyNhanVienHanhChinh() {
		//get sql connection
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao_nv = new DAO_NhanVienHanhChinh();
		dao_pb = new DAO_PhongBan();
		dao_tk = new DAO_TaiKhoan();
		
		//set properties
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
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
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnNorth chứa label tiêu đề
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(headerColor);
		
		lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField();
		
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField();
		
		lblGioiTinh = new JLabel("Giới tính: ");
		cbGioiTinh = new JComboBox();
		cbGioiTinh.addItem("---Chọn---");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		lblNgaySinh = new JLabel("Ngày sinh: ");
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		dcNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dcNgaySinh.getCalendarButton().setBackground(Color.WHITE);
		dcNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgaySinh.setBorder(new LineBorder(Color.WHITE));
		dcNgaySinh.setDateFormatString("dd/MM/yyyy");
		
		dcNgaySinh.setDate(new Date());
		
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField();
		
		lblCCCD = new JLabel("CMND/CCCD: ");
		txtCCCD = new JTextField();
		
		lblSDT = new JLabel("SDT: ");
		txtSDT = new JTextField();	
		
		lblNgayVao = new JLabel("Ngày vào: ");
		dcNgayVao = new JDateChooser();
		dcNgayVao.getCalendarButton().setToolTipText("Chọn ngày vào");
		dcNgayVao.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dcNgayVao.getCalendarButton().setBackground(Color.WHITE);
		dcNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgayVao.setBorder(new LineBorder(Color.WHITE));
		dcNgayVao.setDateFormatString("dd/MM/yyyy");
		
		lblPhongBan = new JLabel("Phòng ban: ");
		cbPhongBan = new JComboBox();
		cbPhongBan.addItem("---Chọn---");
//		cbPhongBan.addItem("Phòng Kế toán");
//		cbPhongBan.addItem("Phòng Kinh doanh");
//		cbPhongBan.addItem("Phòng Nhân sự");
//		cbPhongBan.addItem("Phòng Sản xuất");
		
		lblTrangThai = new JLabel("Trạng thái: ");
		cbTrangThai = new JComboBox();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		lblBangCap = new JLabel("Bằng cấp: ");
		cbBangCap = new JComboBox();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Cao đẳng");
		cbBangCap.addItem("Đại học");
		cbBangCap.addItem("Thạc sỹ");
		cbBangCap.addItem("Tiến sỹ");
		
		lblLuongCoBan = new JLabel("Lương cơ bản: ");
		txtLuongCoBan = new JTextField();
		lblPhuCap = new JLabel("Phụ cấp: ");
		txtPhuCap = new JTextField();
		lblHeSoLuong = new JLabel("Hệ số lương: ");
		txtHeSoLuong = new JTextField();
		
		lblTaiKhoan = new JLabel("Tài khoản: ");
		txtTaiKhoan = new JTextField();
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField();
		
		lblMaNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtMaNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblSDT.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtSDT.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));		
		lblBangCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbBangCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblLuongCoBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtLuongCoBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblHeSoLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtHeSoLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblTaiKhoan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtTaiKhoan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblEmail.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtEmail.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.NORTH);
		pnThongTin.setBackground(bgColor);
		
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		Box d = Box.createVerticalBox();
		Box e = Box.createVerticalBox();
		
		pnThongTin.add(a);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(b);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(c);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(d);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(e);
		
		//box a
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		Box a4 = Box.createHorizontalBox();
			
		a1.add(lblMaNV);
		a1.add(txtMaNV);
		a2.add(lblHoTenNV);
		a2.add(txtHoTenNV);
		a3.add(lblGioiTinh);
		a3.add(cbGioiTinh);
		a4.add(lblNgaySinh);
		a4.add(dcNgaySinh);
		
		
		lblMaNV.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		
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
		
		b1.add(lblDiaChi);
		b1.add(txtDiaChi);
		b2.add(lblCCCD);
		b2.add(txtCCCD);
		b3.add(lblSDT);
		b3.add(txtSDT);
		b4.add(lblEmail);
		b4.add(txtEmail);
		
		lblDiaChi.setPreferredSize(lblCCCD.getPreferredSize());
		lblSDT.setPreferredSize(lblCCCD.getPreferredSize());
		lblEmail.setPreferredSize(lblCCCD.getPreferredSize());
		
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
		
		c1.add(lblNgayVao);
		c1.add(dcNgayVao);
		c2.add(lblPhongBan);
		c2.add(cbPhongBan);
		c3.add(lblTrangThai);
		c3.add(cbTrangThai);
		c4.add(lblBangCap);
		c4.add(cbBangCap);
		
		lblNgayVao.setPreferredSize(lblPhongBan.getPreferredSize());
		lblTrangThai.setPreferredSize(lblPhongBan.getPreferredSize());
		lblBangCap.setPreferredSize(lblPhongBan.getPreferredSize());
		
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
		
		d1.add(lblLuongCoBan);
		d1.add(txtLuongCoBan);
		d2.add(lblPhuCap);
		d2.add(txtPhuCap);
		d3.add(lblHeSoLuong);
		d3.add(txtHeSoLuong);
		d4.add(lblTaiKhoan);
		d4.add(txtTaiKhoan);
		
		lblPhuCap.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblHeSoLuong.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblTaiKhoan.setPreferredSize(lblLuongCoBan.getPreferredSize());
		
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d.add(d2);
		d.add(Box.createVerticalStrut(10));
		d.add(d3);
		d.add(Box.createVerticalStrut(10));
		d.add(d4);
		
		JPanel pnTacVu = new JPanel();
		pnCenter.add(pnTacVu, BorderLayout.CENTER);
		pnTacVu.setBackground(bgColor);
		pnTacVu.setLayout(new BorderLayout());
		
		JPanel pnLoc = new JPanel();
		pnTacVu.add(pnLoc, BorderLayout.NORTH);
		pnLoc.setBackground(bgColor);
		
		JComboBox cbLoc = new JComboBox();
		cbLoc.addItem("---Chọn---");
		cbLoc.addItem("Giới tính");
		cbLoc.addItem("Trạng thái");
		cbLoc.addItem("Phòng ban");
		cbLoc.addItem("Chức danh");
		JTextField txtLoc = new JTextField();
		btnLoc = new JButton("Lọc");
		
		pnLoc.add(cbLoc);
		pnLoc.add(txtLoc);
		pnLoc.add(btnLoc);
		
		cbLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		
		JPanel pnChucNang = new JPanel();
		pnTacVu.add(pnChucNang, BorderLayout.CENTER);
		pnChucNang.setBackground(bgColor);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnThem.setBackground(buttonColor);
		btnThem.setForeground(Color.WHITE);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnSua.setBackground(buttonColor);
		btnSua.setForeground(Color.WHITE);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnXoa.setBackground(buttonColor);
		btnXoa.setForeground(Color.WHITE);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnXoaRong.setBackground(buttonColor);
		btnXoaRong.setForeground(Color.WHITE);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		
		// add các button vào pnChucNang
		pnChucNang.add(btnThem);
		pnChucNang.add(btnSua);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaRong);
		pnChucNang.add(btnLuu);
		
		JPanel pnRegex = new JPanel();
		pnTacVu.add(pnRegex, BorderLayout.SOUTH);
		pnRegex.setBackground(bgColor);
		
		lblError = new JLabel(" ");
		pnRegex.add(lblError);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		
		//pnSouth chứa các table
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBackground(bgColor);
		
		pnTable = new JPanel();
		pnSouth.add(pnTable, BorderLayout.NORTH);
		pnTable.setBackground(bgColor);
		
		//tạo bảng chứa thông tin cá nhân của NV
		createTable();
		
		//load dữ liệu vào bảng 1
		loadData();
		
		// lấy data vô combobox phòng ban
		getDataIntoCombobox();
		
		// auto generate mã NV
		autoGenerateMaNV();
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnLoc.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		tableNV.addMouseListener(this);
		dcNgaySinh.getDateEditor().addPropertyChangeListener(this);
		dcNgayVao.getDateEditor().addPropertyChangeListener(this);
	}
	
	
	// tạo bảng nhân viên
	public void createTable() {
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("Mã NV");
		modelNV.addColumn("Họ tên");
		modelNV.addColumn("Giới tính");
		modelNV.addColumn("Ngày sinh");
		modelNV.addColumn("Địa chỉ");
		modelNV.addColumn("CCCD");
		modelNV.addColumn("SDT");
		modelNV.addColumn("Ngày vào");
		modelNV.addColumn("Phòng ban");
		modelNV.addColumn("Trạng thái");
		modelNV.addColumn("Bằng cấp");
		modelNV.addColumn("Lương CB");
		modelNV.addColumn("Phụ cấp");
		modelNV.addColumn("Hệ số lương");
		modelNV.addColumn("Tài khoản");
		modelNV.addColumn("Email");
		
		JScrollPane sp = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(1200, 300));
		pnTable.add(sp);
	}
	
	
	// load dữ liệu vào bảng nhân viên
	public void loadData() {
		dao_nv = new DAO_NhanVienHanhChinh();
		listNhanVien = dao_nv.getDanhSachNhanVien();
		modelNV.setRowCount(0);
		
		for (int i=0; i < listNhanVien.size(); i++) {
			NhanVienHanhChinh nv = listNhanVien.get(i);
			
			Object[] rowData = {
					nv.getMaNV(), nv.getHoTenNV(), 
					nv.isGioiTinh() == true ? "Nam" : "Nữ",
					nv.getNgaySinh(), nv.getDiaChi(), nv.getCCCD(), nv.getSDT(), nv.getNgayVao(), 
					nv.getPhongBan().getMaPhongBan(), 
					nv.isTrangThai() == true ? "Đang làm" : "Nghỉ việc", 
					nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong(),
					nv.getTaiKhoan().getTenTK(), nv.getEmail()
			};
			modelNV.addRow(rowData);
		}
	}
	
	
	// lấy data vô combobox phòng ban
	public void getDataIntoCombobox() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from PhongBan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cbPhongBan.addItem(rs.getString("tenPhongBan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// xuất ra màn hình thông tin nhập bị lỗi và đưa con trỏ chuột vào thông tin bị sai
	public void showMessage (String message, JTextField text) {
		text.requestFocus();
		lblError.setText(message);
	}
	
	
	public void showMessage (String message, JDateChooser text) {
		text.requestFocus();
		lblError.setText(message);
	}
	
	
	// kiểm tra dữ liệu ràng buộc của các thông tin nhập
	public boolean checkRegex() {
		String hoTen = txtHoTenNV.getText().trim();
		String taiKhoan = txtTaiKhoan.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String CCCD = txtCCCD.getText().trim();
		String SDT = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		Float luongCB = Float.parseFloat(txtLuongCoBan.getText().trim());
		Float phuCap = Float.parseFloat(txtPhuCap.getText().trim());
		Float HSL = Float.parseFloat(txtHeSoLuong.getText().trim());
		
		if (!(hoTen.length() > 0) || hoTen.matches("^[A-Za-z ]+$")) {
			showMessage("Lỗi: Họ tên phải là ký tự", txtHoTenNV);
			return false;
		}
		
		if (!(taiKhoan.length() > 0) || taiKhoan.matches("^[a-z]$")) {
			showMessage("Lỗi: Tài khoản phải là ký tự", txtTaiKhoan);
			return false;
		}
		
		if (!(diaChi.length() > 0) || diaChi.matches("^[A-Za-z ]+$")) {
			showMessage("Lỗi: Địa chỉ phải là ký tự", txtDiaChi);
			return false;
		}
		
		if (!(CCCD.length() > 0) || CCCD.matches("^[0-9]{12}$")) {
			showMessage("Lỗi: CCCD phải có 12 chữ số", txtCCCD);
			return false;
		}
		
		if (!(SDT.length() > 0) || SDT.matches("^[0-9]{12}$")) {
			showMessage("Lỗi: SĐT phải có 12 chữ số", txtSDT);
			return false;
		}
		
		if (!(email.length() > 0 || email.matches("/^\\S+@\\S+\\.\\S+$/"))) {
			showMessage("Lỗi: Email phải có định dạng: abc@def.xyz", txtEmail);
			return false;
		}
		
		if (!(luongCB >= 3000000)) {
			showMessage("Lỗi: Lương cơ bản phải lớn hơn 3 triệu", txtLuongCoBan);
			return false;
		}
		
		if (!(phuCap >= 500000)) {
			showMessage("Lỗi: Phụ cấp phải lớn hơn 500 nghìn", txtPhuCap);
			return false;
		}
		
		if (!(HSL >= 1)) {
			showMessage("Lỗi: Hệ số lương phải lớn hơn 1", txtHeSoLuong);
			return false;
		}
		
		return true;
	}
	
	
/*	
	public void kiemTraRong() {
		String hoTen = txtHoTenNV.getText().trim();
		String taiKhoan = txtTaiKhoan.getText();
		String diaChi = txtDiaChi.getText().trim();
		String CCCD = txtCCCD.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String luongCB = txtLuongCoBan.getText();
		String phuCap = txtPhuCap.getText();
		String hsl = txtHeSoLuong.getText();
		String tk = txtTaiKhoan.getText();
		
		if (hoTen.equals("") || diaChi.equals("") || CCCD.equals("") || SDT.equals("") || email.equals("") || 
				luongCB.equals("") || phuCap.equals("") || hsl.equals("") || tk.equals(""))
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
	}
*/
	
	
	// lấy dữ liệu NV từ textfield
	public NhanVienHanhChinh convertNhanVien() {
		String maNV = txtMaNV.getText();
		String hoTen = txtHoTenNV.getText();
		boolean gioiTinh = (cbGioiTinh.equals("Nam")) ? true : false;
		java.sql.Date ngaySinh = new java.sql.Date(dcNgaySinh.getDate().getTime());
//		Date ngaySinh = dcNgaySinh.getDate();
		String diaChi = txtDiaChi.getText();
		String CCCD = txtCCCD.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		java.sql.Date ngayVao = new java.sql.Date(dcNgayVao.getDate().getTime());
//		Date ngayVao = dcNgayVao.getDate();
		String pb = cbPhongBan.getSelectedItem().toString();
		boolean trangThai = (cbTrangThai.equals("Đang làm")) ? true : false;
		String bangCap = cbBangCap.getSelectedItem().toString();
		Float luongCB = Float.parseFloat(txtLuongCoBan.getText());
		Float phuCap = Float.parseFloat(txtPhuCap.getText());
		Float HSL = Float.parseFloat(txtHeSoLuong.getText());
		String tk = txtTaiKhoan.getText();
		
		PhongBan phongBan = new PhongBan(pb);
		TaiKhoan taiKhoan = new TaiKhoan(tk);
		
		NhanVienHanhChinh nhanVien = new NhanVienHanhChinh(maNV, hoTen, gioiTinh, ngaySinh, diaChi, CCCD, pb, tk, ngayVao, phongBan, SDT, trangThai, bangCap, luongCB, phuCap, HSL, taiKhoan, email);
		
		return nhanVien;
	}
	
	
	// lấy dữ liệu PB từ textfield
	public PhongBan convertPhongBan() {
		String pb = cbPhongBan.getSelectedItem().toString();
		PhongBan phongBan = new PhongBan(pb);
		return phongBan;
	}
	
	
	// lấy dữ liệu TK từ textfield
	public TaiKhoan convertTaiKhoan() {
		String tk = txtTaiKhoan.getText();
		TaiKhoan taiKhoan = new TaiKhoan(tk);
		return taiKhoan;
	}
	
	
	// thêm dữ liệu nhân viên vào database
	public boolean themDuLieuNV() throws Exception {
		NhanVienHanhChinh nv = convertNhanVien();
		
		try {
			dao_nv.themMoiNhanVien(nv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Object[] row = {
				nv.getMaNV(), nv.getHoTenNV(), 
				nv.isGioiTinh() == true ? "Nam" : "Nữ",
				nv.getNgaySinh(), nv.getDiaChi(), nv.getCCCD(), nv.getSDT(), nv.getNgayVao(), 
				nv.getPhongBan().getMaPhongBan(), 
				nv.isTrangThai() == true ? "Đang làm việc" : "Nghỉ việc", 
				nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong(),
				nv.getTaiKhoan().getTenTK(), nv.getEmail()
		};
		modelNV.addRow(row);
		JOptionPane.showMessageDialog(this, "Thêm mới nhân viên thành công!");
		xoaRong();
		
		return true;
	}
	
	
	public boolean suaDuLieuNV() {
		String maNV = txtMaNV.getText();
		String hoTen = txtHoTenNV.getText();
		String diaChi = txtDiaChi.getText();
		String CCCD = txtCCCD.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String luongCB = txtLuongCoBan.getText();
		String phuCap = txtPhuCap.getText();
		String hsl = txtHeSoLuong.getText();
		String tk = txtTaiKhoan.getText();
		
		if (checkRegex() == true) {
			int tableRow = tableNV.getSelectedRow();
			String selectedEmp = "";
			
			if (tableRow >= 0) {
				int[] selectedRows = tableNV.getSelectedRows();
				for (int selectedIndex : selectedRows) {
					if (selectedIndex >= 0 && selectedIndex < listNhanVien.size()) {
						NhanVienHanhChinh nhanVienDuocChon = listNhanVien.get(selectedIndex);
						selectedEmp = nhanVienDuocChon.getMaNV();
					}
				}
				
				NhanVienHanhChinh nhanVien = convertNhanVien();
				PhongBan phongBan = convertPhongBan();
				TaiKhoan taiKhoan = convertTaiKhoan();
				
				if (dao_nv.capNhatThongTinNhanVien(nhanVien, phongBan, taiKhoan)) {
					tableNV.setValueAt(hoTen, 2, 1);
					tableNV.setValueAt(diaChi, 1, 2);
					tableNV.setValueAt(CCCD, 2, 2);
					tableNV.setValueAt(SDT, 2, 3);
					tableNV.setValueAt(email, 2, 4);
					tableNV.setValueAt(luongCB, 1, 4);
					tableNV.setValueAt(phuCap, 2, 4);
					tableNV.setValueAt(hsl, 3, 4);
					tableNV.setValueAt(taiKhoan, 4, 4);
					
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
					loadData();
				}
			}
		}
		
		
		return true;
	}
	
	
/*	
	public boolean luuDuLieuNV() {
		int r = tableNV.getSelectedRow();
		NhanVienHanhChinh nv = convertNhanVienNhanVien();
		
		if (dao_nv.themMoiNhanVien(nv)) {
			Object[] row = {
					nv.getMaNV(), nv.getHoTenNV(), 
					nv.isGioiTinh() == true ? "Nam" : "Nữ",
					nv.getNgaySinh(), nv.getDiaChi(), nv.getCCCD(), nv.getSDT(), nv.getNgayVao(), 
					nv.getPhongBan().getMaPhongBan(), 
					nv.isTrangThai() == true ? "Đang làm việc" : "Nghỉ việc", 
					nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong(),
					nv.getTaiKhoan().getTenTK(), nv.getEmail()
			};
			modelNV.addRow(row);
			JOptionPane.showMessageDialog(this, "Lưu thành công!");
			xoaRong();
		}
		
		return true;
	}
*/
	
	
 	 public void autoGenerateMaNV() {
 		 try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT MAX(maNV) AS maxNhanVien from NhanVienHanhChinh";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				String maNhanVien = rs.getString("maxNhanVien");
				if (maNhanVien == null) {
					txtMaNV.setText("NV001");
				} else {
					Long stt = Long.parseLong(maNhanVien.substring(2));
					stt++;
					txtMaNV.setText("NV" + String.format("%03d", stt));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	 }
	
	
	public void xoaRong() {
		txtMaNV.setText("");
		txtHoTenNV.setText("");
		cbGioiTinh.setSelectedIndex(0);
		dcNgaySinh.setDate(null);
		txtDiaChi.setText("");
		txtCCCD.setText("");
		txtSDT.setText("");
		Date currentDate = new Date();
		dcNgayVao.setDate(currentDate);
		cbPhongBan.setSelectedIndex(0);
		cbTrangThai.setSelectedIndex(0);
		cbBangCap.setSelectedIndex(0);
		txtLuongCoBan.setText("");
		txtPhuCap.setText("");
		txtHeSoLuong.setText("");
		txtTaiKhoan.setText("");
		txtEmail.setText("");
		autoGenerateMaNV();
	}
	
	
	//ActionEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnXoaRong)) {
			xoaRong();
		}
		else if (o.equals(btnThem)) {
			if (checkRegex() == true) {
				try {
					themDuLieuNV();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (o.equals(btnSua)) {
			suaDuLieuNV();
		}
		
	}
	
	
	//MouseEvent
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o.equals(tableNV)) {
			int tableRow = tableNV.getSelectedRow();
			
			if (tableRow >= 0 && tableRow < listNhanVien.size()) {
				NhanVienHanhChinh nv = listNhanVien.get(tableRow);
				
				txtMaNV.setText(nv.getMaNV());
				txtHoTenNV.setText(nv.getHoTenNV());
				cbGioiTinh.setSelectedItem(nv.isGioiTinh() ? "Nam" : "Nữ");
				java.sql.Date getNgaySinh = (java.sql.Date) nv.getNgaySinh();
				dcNgaySinh.setDate(getNgaySinh);
				txtDiaChi.setText(nv.getDiaChi());
				txtCCCD.setText(nv.getCCCD());
				txtSDT.setText(nv.getSDT());
				txtEmail.setText(nv.getEmail());
				java.sql.Date getNgayVao = (java.sql.Date) nv.getNgayVao();
				dcNgayVao.setDate(getNgayVao);
				cbPhongBan.setSelectedItem(nv.getPhongBan().getTenPhongBan());
				cbTrangThai.setSelectedItem(nv.isTrangThai() ? "Đang làm" : "Nghỉ việc");
				cbBangCap.setSelectedItem(nv.getBangCap());
				txtLuongCoBan.setText(nv.getLuongCoBan() + "");
				txtPhuCap.setText(nv.getPhuCap() + "");
				txtHeSoLuong.setText(nv.getHeSoLuong() + "");
				txtTaiKhoan.setText(nv.getTaiKhoan().getTenTK());
			}
		}
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
	
	
	//Property Change Event
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object obj = evt.getSource();
		
		if (obj.equals(dcNgayVao)) {
			if ("date".equals(evt.getPropertyName())) {
				String BangCapDuocChon = (String) cbBangCap.getSelectedItem();
				NhanVienHanhChinh nv = new NhanVienHanhChinh();
				java.sql.Date ngayVao = new java.sql.Date(dcNgayVao.getDate().getTime());
				
				float heSoLuong = nv.tinhHeSoLuong(ngayVao, BangCapDuocChon);
				DecimalFormat dfHSL = new DecimalFormat("#,##0.00");
				txtHeSoLuong.setText(dfHSL.format(heSoLuong));
				
				DecimalFormat dfLuongCB = new DecimalFormat("###,###.##000,000.00");
				txtLuongCoBan.setText(dfLuongCB.format(dfLuongCB));
			}
		}
	}
	
	
	//hàm main
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyNhanVienHanhChinh().setVisible(true);
	}

}

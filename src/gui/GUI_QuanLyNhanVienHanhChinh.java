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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GUI_QuanLyNhanVienHanhChinh extends JFrame implements ActionListener, MouseListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JButton btnLoc;
	private JLabel lblTieuDe, lblError;
	private JLabel lblHoTenNV, lblGioiTinh, lblNgaySinh, lblDiaChi;
	private JLabel lblCCCD, lblSDT, lblNgayVao;
	private JLabel lblPhongBan, lblTrangThai, lblBangCap;
	private JLabel lblLuongCoBan, lblPhuCap, lblHeSoLuong, lblTaiKhoan, lblEmail;
	private JTextField txtHoTenNV, txtDiaChi, txtCCCD, txtSDT, txtTaiKhoan, txtEmail;
	private JTextField txtLuongCoBan, txtPhuCap, txtHeSoLuong, txtLoc;
	private JDateChooser dcNgaySinh, dcNgayVao;
	private JComboBox cbGioiTinh, cbPhongBan;
	private JComboBox cbTrangThai, cbBangCap, cbLoc;
	private JButton btnThem, btnSua, btnXoa, btnXoaRong;
	private Color bgColor;
	
	private JPanel pnTable;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	
	private int soLuongNV;
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
		Color buttonColor = new Color(0, 133, 204);
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
		dcNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
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
		dcNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		dcNgayVao.setBorder(new LineBorder(Color.WHITE));
		dcNgayVao.setDateFormatString("dd/MM/yyyy");
		
		lblPhongBan = new JLabel("Phòng ban: ");
		cbPhongBan = new JComboBox<String>();
		
		cbPhongBan.addItem("---Chọn---");
		cbPhongBan.addItem("PB01");
		cbPhongBan.addItem("PB02");
		cbPhongBan.addItem("PB03");
		cbPhongBan.addItem("PB04");
		cbPhongBan.addItem("PB05");
		
		lblTrangThai = new JLabel("Trạng thái: ");
		cbTrangThai = new JComboBox();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		lblBangCap = new JLabel("Bằng cấp: ");
		cbBangCap = new JComboBox();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Cao Dang");
		cbBangCap.addItem("Dai Hoc");
		cbBangCap.addItem("Thac Sy");
		cbBangCap.addItem("Tien Sy");
		
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
		
		lblHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		dcNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblSDT.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtSDT.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		cbGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		dcNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		cbPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		cbTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));		
		lblBangCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		cbBangCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblLuongCoBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtLuongCoBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblHeSoLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtHeSoLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblTaiKhoan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtTaiKhoan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblEmail.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtEmail.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		
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
		b3.add(lblEmail);
		b3.add(txtEmail);
		b4.add(lblTaiKhoan);
		b4.add(txtTaiKhoan);
		
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
		
		lblNgayVao.setPreferredSize(lblTrangThai.getPreferredSize());
		lblPhongBan.setPreferredSize(lblTrangThai.getPreferredSize());
		lblBangCap.setPreferredSize(lblTrangThai.getPreferredSize());
		
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
		d4.add(Box.createHorizontalStrut(10));
		d4.add(Box.createHorizontalStrut(10));
		d4.add(Box.createHorizontalStrut(10));
		
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
		
		cbLoc = new JComboBox();
		cbLoc.addItem("---Chọn---");
		cbLoc.addItem("Giới tính");
		cbLoc.addItem("Trạng thái");
		cbLoc.addItem("Phòng ban");
		txtLoc = new JTextField();
		btnLoc = new JButton("Lọc");
		
		pnTacVu.add(cbLoc);
		pnTacVu.add(txtLoc);
		pnTacVu.add(btnLoc);
		
		cbLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		
		btnThem = new JButton("Thêm");
		pnTacVu.add(btnThem);
		btnThem.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		btnThem.setBackground(buttonColor);
		btnThem.setForeground(Color.WHITE);
		
		btnSua = new JButton("Sửa");
		pnTacVu.add(btnSua);
		btnSua.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		btnSua.setBackground(buttonColor);
		btnSua.setForeground(Color.WHITE);
		
		btnXoa = new JButton("Xóa");
		pnTacVu.add(btnXoa);
		btnXoa.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		btnXoa.setBackground(buttonColor);
		btnXoa.setForeground(Color.WHITE);
		
		btnXoaRong = new JButton("Xóa rỗng");
		pnTacVu.add(btnXoaRong);
		btnXoaRong.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		btnXoaRong.setBackground(buttonColor);
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		
		
		
		//pnSouth chứa các table
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBackground(bgColor);
		
		pnTable = new JPanel();
		pnCenter.add(pnTable, BorderLayout.SOUTH);
		pnTable.setBackground(bgColor);
		
		//tạo bảng chứa thông tin cá nhân của NV
		createTable();
		
		//load dữ liệu vào bảng 1
		layDuLieuNV();
		
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnLoc.addActionListener(this);
		cbLoc.addActionListener(this);
		tableNV.addMouseListener(this);
	}
	
	
	// tạo bảng nhân viên
	public void createTable() {
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("STT");
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
		sp.setPreferredSize(new Dimension(1200, 400));
		pnTable.add(sp);
	}
	
	
	// lấy dữ liệu vào bảng nhân viên
	public void layDuLieuNV() {
		List<NhanVienHanhChinh> dsNhanVien = dao_nv.getDanhSachNhanVien();
		soLuongNV = dsNhanVien.size();
		int i = 1;
		
		for (NhanVienHanhChinh nv : dsNhanVien) {
			Object[] rowData = {
					i++,
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
//		Float luongCB = Float.parseFloat(txtLuongCoBan.getText().trim());
//		Float phuCap = Float.parseFloat(txtPhuCap.getText().trim());
//		Float HSL = Float.parseFloat(txtHeSoLuong.getText().trim());
		
		Double luongCB = Double.parseDouble(txtLuongCoBan.getText());
		Double phuCap = Double.parseDouble(txtPhuCap.getText());
		Double HSL = Double.parseDouble(txtHeSoLuong.getText());
		
		if (!(hoTen.length() > 0) || hoTen.matches("^[A-Za-z ]+$")) {
			showMessage("Lỗi: Họ tên phải là ký tự", txtHoTenNV);
			return false;
		}
		
		if (!(taiKhoan.length() > 0) || taiKhoan.matches("^[a-z]$")) {
			showMessage("Lỗi: Tài khoản phải là ký tự", txtTaiKhoan);
			return false;
		}
		
		if (!(diaChi.length() > 0) || diaChi.matches("^[A-Za-z1-9 ]+$")) {
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
	
	
	// thêm dữ liệu nhân viên vào database
	public boolean themDuLieuNV() {
		String maNV = String.format("%s%02d", "NV", ++soLuongNV);
		String hoTen = txtHoTenNV.getText();
		boolean gioiTinh = (cbGioiTinh.equals("Nam")) ? true : false;
		Date ngaySinh = dcNgaySinh.getDate();
		String diaChi = txtDiaChi.getText();
		String CCCD = txtCCCD.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		Date ngayVao = dcNgayVao.getDate();
		String pb = cbPhongBan.getSelectedItem().toString();
		boolean trangThai = (cbTrangThai.equals("Đang làm")) ? true : false;
		String bangCap = cbBangCap.getSelectedItem().toString();
		double luongCB = Double.parseDouble(txtLuongCoBan.getText());
		double phuCap = Double.parseDouble(txtPhuCap.getText());
		double HSL = Double.parseDouble(txtHeSoLuong.getText());
		String tk = txtTaiKhoan.getText();
		
		PhongBan phongBan = new PhongBan(pb);
		TaiKhoan taiKhoan = new TaiKhoan(tk);
		
		NhanVienHanhChinh nv = new NhanVienHanhChinh(maNV, hoTen, gioiTinh, 
				new java.sql.Date(ngaySinh.getTime()), 
				diaChi, CCCD, SDT, 
				new java.sql.Date(ngayVao.getTime()), 
				phongBan, trangThai, bangCap, 
				luongCB, phuCap, HSL, taiKhoan, email);
		
		try {
			dao_nv.themMoiNhanVien(nv);
			int i = soLuongNV + 1;
			Object[] row = {
					i,
					nv.getMaNV(), nv.getHoTenNV(), 
					nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.getNgaySinh(), 
					nv.getDiaChi(), nv.getCCCD(), nv.getSDT(), nv.getNgayVao(), 
					nv.getPhongBan().getMaPhongBan(), 
					nv.isTrangThai() == true ? "Đang làm việc" : "Nghỉ việc", 
					nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong(),
					nv.getTaiKhoan().getTenTK(), nv.getEmail()
			};
			modelNV.addRow(row);
			JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
			xoaRong();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public boolean suaDuLieuNV() {
		int selectedRow = tableNV.getSelectedRow();
		String maNV = modelNV.getValueAt(selectedRow, 1).toString();
		String hoTen = txtHoTenNV.getText();
		boolean gioiTinh = (cbGioiTinh.equals("Nam")) ? true : false;
		Date ngaySinh = dcNgaySinh.getDate();
		String diaChi = txtDiaChi.getText();
		String CCCD = txtCCCD.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		Date ngayVao = dcNgayVao.getDate();
		String pb = cbPhongBan.getSelectedItem().toString();
		boolean trangThai = (cbTrangThai.equals("Đang làm")) ? true : false;
		String bangCap = cbBangCap.getSelectedItem().toString();
		double luongCB = Double.parseDouble(txtLuongCoBan.getText());
		double phuCap = Double.parseDouble(txtPhuCap.getText());
		double HSL = Double.parseDouble(txtHeSoLuong.getText());
		String tk = txtTaiKhoan.getText();
		
		PhongBan phongBan = new PhongBan(pb);
		TaiKhoan taiKhoan = new TaiKhoan(tk);
		
		NhanVienHanhChinh nv = new NhanVienHanhChinh(maNV, hoTen, gioiTinh, 
				new java.sql.Date(ngaySinh.getTime()), 
				diaChi, CCCD, SDT, 
				new java.sql.Date(ngayVao.getTime()), 
				phongBan, trangThai, bangCap, 
				luongCB, phuCap, HSL, taiKhoan, email);
		
		try {
			int i = selectedRow + 1;
			dao_nv.capNhatThongTinNhanVien(nv);
			Object[] rowData = {
					i,
					nv.getMaNV(), nv.getHoTenNV(), 
					nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.getNgaySinh(), 
					nv.getDiaChi(), nv.getCCCD(), nv.getSDT(), nv.getNgayVao(), 
					nv.getPhongBan().getMaPhongBan(), 
					nv.isTrangThai() == true ? "Đang làm việc" : "Nghỉ việc", 
					nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong(),
					nv.getTaiKhoan().getTenTK(), nv.getEmail()
			};
			modelNV.insertRow(selectedRow, rowData);
			modelNV.removeRow(selectedRow + 1);
			
			JOptionPane.showMessageDialog(this, "Sửa thành công!");
			xoaRong();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	// hàm xóa dữ liệu NV
	public void xoaDuLieuNV() {
		int row = tableNV.getSelectedRow();
		
		if (row != -1) {
			int tb = 	JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa dòng này không?",
						"Delete", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				String hoTenNV = txtHoTenNV.getText().trim();
				
				if (dao_nv.xoaNhanVien(hoTenNV)) {
					modelNV.removeRow(row);
					xoaRong();
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa!");
				}
			}
		}
	}
	
	
	// tìm kiếm NV theo tiêu chí
	public void timTheoTieuChi (String luaChon) {
		List<NhanVienHanhChinh> dsNhanVien = new ArrayList<NhanVienHanhChinh>();
		boolean giaTriTieuChi;
		String duLieu = txtLoc.getText();
		
		if (luaChon.equalsIgnoreCase("Giới tính")) {
			if (duLieu.equalsIgnoreCase("Nam")) {
				giaTriTieuChi = true;
				dsNhanVien = dao_nv.layNhanVienTheoGioiTinh(giaTriTieuChi);
			} else {
				giaTriTieuChi = false;
				dsNhanVien = dao_nv.layNhanVienTheoGioiTinh(giaTriTieuChi);
			}
		}
		
		else if (luaChon.equalsIgnoreCase("Trạng thái")) {
			if (luaChon.equalsIgnoreCase("Giới tính")) {
				if (duLieu.equalsIgnoreCase("Nam")) {
					giaTriTieuChi = true;
					dsNhanVien = dao_nv.layNhanVienTheoTrangThai(giaTriTieuChi);
				} else {
					giaTriTieuChi = false;
					dsNhanVien = dao_nv.layNhanVienTheoTrangThai(giaTriTieuChi);
				}
			}
		}
		
		else if (luaChon.equalsIgnoreCase("Phòng ban")) {
			String gtMaPB = String.valueOf(duLieu);
			dsNhanVien = dao_nv.layNhanVienTheoPhongBan(gtMaPB);
		}
		
		
		int i = 1;
		for (NhanVienHanhChinh nv : dsNhanVien) {
			Object[] rowData = {
					i++,
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
	
	
 	// hàm xóa rỗng
	public void xoaRong() {
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
	}
	
	
	//ActionEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnXoaRong)) {
			xoaRong();
		}
		else if (o.equals(btnThem)) {
			//if (checkRegex() == true) {
				themDuLieuNV();
			//}
		}
		else if (o.equals(btnSua)) {
			suaDuLieuNV();
		}
		else if (o.equals(btnXoa)) {
			xoaDuLieuNV();
		}
		else if (o.equals(btnLoc)) {
			if (cbLoc.getSelectedItem().equals("Giới tính")) {
				modelNV.getDataVector().removeAllElements();
				timTheoTieuChi("Giới tính");
			}
			else if (cbLoc.getSelectedItem().equals("Trạng thái")) {
				modelNV.getDataVector().removeAllElements();
				timTheoTieuChi("Trạng thái");
			}
			else if (cbLoc.getSelectedItem().equals("Phòng ban")) {
				modelNV.getDataVector().removeAllElements();
				timTheoTieuChi("Phòng ban");
			}
		}
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
				
				double heSoLuong = nv.tinhHeSoLuong(ngayVao, BangCapDuocChon);
				DecimalFormat dfHSL = new DecimalFormat("#,##0.00");
				txtHeSoLuong.setText(dfHSL.format(heSoLuong));
				
				DecimalFormat dfLuongCB = new DecimalFormat("###,###.##000,000.00");
				txtLuongCoBan.setText(dfLuongCB.format(dfLuongCB));
			}
		}
	}
	
	
	// hàm hiển thị dữ liệu NV được chọn
	public void hienThiDuLieuNVDuocChon() {
		int selectedRow = tableNV.getSelectedRow();
		
		if (selectedRow >= 0) {
			txtHoTenNV.setText(modelNV.getValueAt(selectedRow, 2).toString());
			cbGioiTinh.setSelectedItem(modelNV.getValueAt(selectedRow, 3).toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dcNgaySinh.setDate(dateFormat.parse(modelNV.getValueAt(selectedRow, 4).toString()));
				dcNgayVao.setDate(dateFormat.parse(modelNV.getValueAt(selectedRow, 8).toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtDiaChi.setText(modelNV.getValueAt(selectedRow, 5).toString());
			txtCCCD.setText(modelNV.getValueAt(selectedRow, 6).toString());
			txtSDT.setText(modelNV.getValueAt(selectedRow, 7).toString());
			txtEmail.setText(modelNV.getValueAt(selectedRow, 16).toString());
			cbPhongBan.setSelectedItem(modelNV.getValueAt(selectedRow, 9).toString());
			cbTrangThai.setSelectedItem(modelNV.getValueAt(selectedRow, 10).toString());
			cbBangCap.setSelectedItem(modelNV.getValueAt(selectedRow, 11).toString());
			txtLuongCoBan.setText(modelNV.getValueAt(selectedRow, 12).toString());
			txtPhuCap.setText(modelNV.getValueAt(selectedRow, 13).toString());
			txtHeSoLuong.setText(modelNV.getValueAt(selectedRow, 14).toString());
			txtTaiKhoan.setText(modelNV.getValueAt(selectedRow, 15).toString());
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNV.getSelectedRow();
		
		if (row >= 0) {
			hienThiDuLieuNVDuocChon();
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
	
	
	//hàm main
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyNhanVienHanhChinh().setVisible(true);
	}

}
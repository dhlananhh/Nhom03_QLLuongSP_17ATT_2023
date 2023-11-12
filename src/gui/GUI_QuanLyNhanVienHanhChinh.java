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
import dao.DAO_ChucDanh;
import dao.DAO_NhanVienHanhChinh;
import dao.DAO_PhongBan;
import entity.ChucDanh;
import entity.NhanVienHanhChinh;
import entity.PhongBan;

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
import java.util.ArrayList;
import java.util.List;


public class GUI_QuanLyNhanVienHanhChinh extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JButton btnLoc;
	private JLabel lblTieuDe, lblError;
	private JLabel lblHoTenNV, lblGioiTinh, lblNgaySinh, lblDiaChi;
	private JLabel lblCCCD, lblBHXH, lblNgayVao;
	private JLabel lblPhongBan, lblChucDanh, lblTrangThai, lblBangCap;
	private JLabel lblLuongCoBan, lblPhuCap, lblHeSoLuong;
	private JTextField txtHoTenNV, txtDiaChi, txtCCCD, txtBHXH;
	private JTextField txtLuongCoBan, txtPhuCap, txtHeSoLuong, txtLoc;
	private JDateChooser dcNgaySinh, dcNgayVao;
	private JComboBox cbGioiTinh, cbPhongBan, cbChucDanh;
	private JComboBox cbTrangThai, cbBangCap, cbLoc;
	private JButton btnThem, btnSua, btnXoa, btnXoaTrang, btnLuu;
	private Color bgColor;
	
	private JPanel pnTable;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	
	private DAO_NhanVienHanhChinh dao_nv;
	private List<NhanVienHanhChinh> listNhanVien = new ArrayList<NhanVienHanhChinh>();
	
	private DAO_PhongBan dao_pb;
	private List<PhongBan> listPhongBan = new ArrayList<PhongBan>();
	
	private DAO_ChucDanh dao_cd;
	private List<ChucDanh> listChucDanh = new ArrayList<ChucDanh>();
	
	
	public GUI_QuanLyNhanVienHanhChinh() {
		//get sql connection
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao_nv = new DAO_NhanVienHanhChinh();
		dao_pb = new DAO_PhongBan();
		dao_cd = new DAO_ChucDanh();
		
		//set properties
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField();
		
		lblGioiTinh = new JLabel("Giới tính: ");
		cbGioiTinh = new JComboBox();
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
		
		lblBHXH = new JLabel("BHXH: ");
		txtBHXH = new JTextField();	
		
		lblNgayVao = new JLabel("Ngày vào: ");
		dcNgayVao = new JDateChooser();
		dcNgayVao.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblPhongBan = new JLabel("Phòng ban: ");
		cbPhongBan = new JComboBox();
		cbPhongBan.addItem("---Chọn---");
		cbPhongBan.addItem("Phòng Kế toán");
		cbPhongBan.addItem("Phòng Kinh doanh");
		cbPhongBan.addItem("Phòng Nhân sự");
		cbPhongBan.addItem("Phòng Sản xuất");
		
		lblChucDanh = new JLabel("Chức danh: ");
		cbChucDanh = new JComboBox();
		cbChucDanh.addItem("---Chọn---");
		cbChucDanh.addItem("Giám đốc");
		cbChucDanh.addItem("Trưởng phòng");
		cbChucDanh.addItem("Nhân viên");
		cbChucDanh.addItem("Công nhân");
		
		lblTrangThai = new JLabel("Trạng thái: ");
		cbTrangThai = new JComboBox();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		lblBangCap = new JLabel("Bằng cấp: ");
		cbBangCap = new JComboBox();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Cử nhân");
		cbBangCap.addItem("Thạc sỹ");
		cbBangCap.addItem("Tiến sỹ");
		
		lblLuongCoBan = new JLabel("Lương cơ bản: ");
		txtLuongCoBan = new JTextField();
		lblPhuCap = new JLabel("Phụ cấp: ");
		txtPhuCap = new JTextField();
		lblHeSoLuong = new JLabel("Hệ số lương: ");
		txtHeSoLuong = new JTextField();
		
		lblHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtHoTenNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgaySinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtDiaChi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtCCCD.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblBHXH.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtBHXH.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbGioiTinh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		dcNgayVao.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbPhongBan.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblChucDanh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbChucDanh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
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
		b3.add(lblNgayVao);
		b3.add(dcNgayVao);
		
		lblBHXH.setPreferredSize(lblCCCD.getPreferredSize());
		lblNgayVao.setPreferredSize(lblCCCD.getPreferredSize());
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
//		b.add(Box.createVerticalStrut(10));
//		b.add(b4);
		
		//box c
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		Box c4 = Box.createHorizontalBox();
		
		c1.add(lblPhongBan);
		c1.add(cbPhongBan);
		c2.add(lblChucDanh);
		c2.add(cbChucDanh);
		c3.add(lblTrangThai);
		c3.add(cbTrangThai);
		c4.add(lblBangCap);
		c4.add(cbBangCap);
		
		lblPhongBan.setPreferredSize(lblChucDanh.getPreferredSize());
		lblChucDanh.setPreferredSize(lblChucDanh.getPreferredSize());
		lblTrangThai.setPreferredSize(lblChucDanh.getPreferredSize());
		lblBangCap.setPreferredSize(lblChucDanh.getPreferredSize());
		
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
//		d3.add(lblGiamTru);
//		d3.add(txtGiamTru);
//		d4.add(lblTamUng);
//		d4.add(txtTamUng);
		
		lblPhuCap.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblHeSoLuong.setPreferredSize(lblLuongCoBan.getPreferredSize());
		
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d.add(d2);
		d.add(Box.createVerticalStrut(10));
		d.add(d3);
//		d.add(Box.createVerticalStrut(10));
//		d.add(d4);
		
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
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnXoaTrang.setBackground(buttonColor);
		btnXoaTrang.setForeground(Color.WHITE);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		
		// add các button vào pnChucNang
		pnChucNang.add(btnThem);
		pnChucNang.add(btnSua);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnLuu);
		
		JPanel pnRegex = new JPanel();
		pnTacVu.add(pnRegex, BorderLayout.SOUTH);
		pnRegex.setBackground(bgColor);
		
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
		
	
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnLoc.addActionListener(this);

	}
	
	
	// tạo bảng 1
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
		modelNV.addColumn("BHXH");
		modelNV.addColumn("Ngày vào");
		modelNV.addColumn("Phòng ban");
		modelNV.addColumn("Chức danh");
		modelNV.addColumn("Trạng thái");
		modelNV.addColumn("Bằng cấp");
		modelNV.addColumn("Lương CB");
		modelNV.addColumn("Phụ cấp");
		modelNV.addColumn("Hệ số lương");
		
		JScrollPane sp = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(1200, 300));
		pnTable.add(sp);
	}
	
	
	// load dữ liệu vào bảng nhân viên
	public void loadData() {
		dao_nv = new DAO_NhanVienHanhChinh();
		listNhanVien = dao_nv.getDanhSachNhanVien();
		try {
			for (NhanVienHanhChinh nv : listNhanVien) {
				Object[] row = {
						nv.getMaNV(), nv.getHoTenNV(), 
						nv.isGioiTinh() == true ? "Nam" : "Nữ",
						nv.getNgaySinh(), nv.getDiaChi(), nv.getCCCD(), nv.getBHXH(), nv.getNgayVao(), 
						nv.getPhongBan().getMaPhongBan(), nv.getChucDanh().getMaChucDanh(), 
						nv.isTrangThai() == true ? "Đang làm việc" : "Nghỉ việc", 
						nv.getBangCap(), nv.getLuongCoBan(), nv.getPhuCap(), nv.getHeSoLuong()
				};
				modelNV.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//ActionEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThem)) {
			if (validData() == true) {
				
			}
		}
	}
	
	
	//MouseEvent
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
	public void showMessage (String message, JTextField text) {
		text.requestFocus();
		lblError.setText(message);
	}
	
	
	public boolean validData() {
		String hoTen = txtHoTenNV.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String CCCD = txtCCCD.getText().trim();
		String BHXH = txtBHXH.getText().trim();
		
		if (!(hoTen.length() > 0) || hoTen.matches("^[A-Za-z ]+$")) {
			showMessage("Lỗi: Họ tên phải là ký tự", txtHoTenNV);
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
		
		if (!(BHXH.length() > 0) || BHXH.matches("^[0-9]{12}$")) {
			showMessage("Lỗi: BHXH phải có 12 chữ số", txtBHXH);
			return false;
		}
		
		
		return true;
	}

	
	//hàm main
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyNhanVienHanhChinh().setVisible(true);
	}

}

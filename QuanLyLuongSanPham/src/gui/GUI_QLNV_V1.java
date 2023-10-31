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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;


public class GUI_QLNV_V1 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblGioiTinh, lblBangCap, lblNghiViec;
	private JLabel lblMaNV, lblHoTenNV, lblNgaySinh, lblCCCD, lblBHXH, lblMST;
	private JLabel lblDiaChi, lblNgayVaoLam, lblChucVu, lblPhongBan;
	private JLabel lblLuongCB, lblLuongPC, lblCheDoLuong, lblKhoanGiamTru, lblTamUng, lblTrangThaiLamViec;
	private JTextField txtMaNV, txtHoTenNV, txtDiaChi;
	private JTextField txtCCCD, txtBHXH, txtMST, txtLoc;
	private JTextField txtLuongCB, txtLuongPC, txtKhoanGiamTru, txtTamUng;
	private JDateChooser dcNgaySinh, dcNgayVaoLam;
	private JComboBox cbGioiTinh, cbLoc, cbPhongBan, cbChucVu, cbBangCap, cbCheDoLuong, cbTrangThaiLamViec;
	private Font BVNPro;
	private JTable table;
	private DefaultTableModel model;
	private ButtonGroup grGioiTinh, grBangCap, grCheDoLuong;
	private JRadioButton radLuongThang, radLuongSP;
	private JRadioButton radNam, radNu;
	private JRadioButton radCuNhan, radThacSy, radTienSy;
	private JCheckBox chkNghiViec;
	private JButton btnLoc, btnThem, btnSua, btnXoa, btnLuu;
	
	
	public GUI_QLNV_V1() {
		//set JFrame properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//load fonts
		try {
			String fileName = "src/fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
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
		
		lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN HÀNH CHÍNH");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		//pnCenter chứa pnTacVu và pnThongTin
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		//pnTacVu chứa các tiêu chí lọc dữ liệu
		JPanel pnTacVu = new JPanel();
		pnCenter.add(pnTacVu, BorderLayout.NORTH);
		pnTacVu.setBackground(bgColor);
		
		Box t = Box.createVerticalBox();
		Box t1 = Box.createHorizontalBox();
				
		btnLoc = new JButton("Lọc");
		btnLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		
		cbLoc = new JComboBox();
		cbLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbLoc.addItem("---Chọn---");
		cbLoc.addItem("Mã nhân viên");
		cbLoc.addItem("Phòng ban");
		cbLoc.addItem("Nghỉ việc");
		
		txtLoc = new JTextField(20);
		txtLoc.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		t1.add(cbLoc);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(txtLoc);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnLoc);
		
		t.add(t1);
		pnTacVu.add(t);
		
		//pnThongTin chứa các textfield nhập thông tin
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setBackground(panelColor);
				
		//mã NV
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(20);
		
		//họ tên NV
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField(20);
		
		//giới tính
		lblGioiTinh = new JLabel("Giới tính: ");
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		
		grGioiTinh = new ButtonGroup();
		grGioiTinh.add(radNam);
		grGioiTinh.add(radNu);
		
		cbGioiTinh = new JComboBox();
		cbGioiTinh.addItem("---Chọn---");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		//ngày sinh
		lblNgaySinh = new JLabel("Ngày sinh: ");
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		//địa chỉ
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField(20);
		
		//cccd
		lblCCCD = new JLabel("CMND/CCCD: ");
		txtCCCD = new JTextField(20);
		
		//bhxh
		lblBHXH = new JLabel("BHXH: ");
		txtBHXH = new JTextField(20);
		
		//mst
		lblMST = new JLabel("MST: ");
		txtMST = new JTextField(20);
		
		//lương cb
		lblLuongCB = new JLabel("Lương cơ bản: ");
		txtLuongCB = new JTextField(20);
		
		lblLuongPC = new JLabel("Lương phụ cấp: ");
		txtLuongPC = new JTextField(20);
		
		lblKhoanGiamTru = new JLabel("Các khoản giảm trừ: ");
		txtKhoanGiamTru = new JTextField(20);
		
		lblTamUng = new JLabel("Tạm ứng: ");
		txtTamUng = new JTextField(20);
		
		lblNghiViec = new JLabel("Nghỉ việc: ");
		chkNghiViec = new JCheckBox();
		
		lblNgayVaoLam = new JLabel("Ngày vào làm: ");
		dcNgayVaoLam = new JDateChooser();
		dcNgayVaoLam.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblBangCap = new JLabel("Bằng cấp: ");
		radCuNhan = new JRadioButton("Cử nhân");
		radThacSy = new JRadioButton("Thạc sỹ");
		radTienSy = new JRadioButton("Tiến sỹ");
		
		grBangCap = new ButtonGroup();
		grBangCap.add(radCuNhan);
		grBangCap.add(radThacSy);
		grBangCap.add(radTienSy);
		
		lblPhongBan = new JLabel("Phòng ban: ");
		cbPhongBan = new JComboBox();
		cbPhongBan.addItem("---Chọn---");
		cbPhongBan.addItem("Phòng Tài chính - Kế toán");
		cbPhongBan.addItem("Phòng kinh doanh - Chăm sóc khách hàng");
		cbPhongBan.addItem("Phòng Hành chính - Nhân sự");
		cbPhongBan.addItem("Phòng kỹ thuật - sản xuất");
		
		lblTrangThaiLamViec = new JLabel("Trạng thái làm việc: ");
		cbTrangThaiLamViec = new JComboBox();
		cbTrangThaiLamViec.addItem("---Chọn---");
		cbTrangThaiLamViec.addItem("Đang làm việc");
		cbTrangThaiLamViec.addItem("Nghỉ việc");
		
		lblChucVu = new JLabel("Chức vụ: ");
		cbChucVu = new JComboBox();
		cbChucVu.addItem("Giám đốc");
		
		cbBangCap = new JComboBox();
		cbBangCap.addItem("---Chọn---");
		cbBangCap.addItem("Cử nhân");
		cbBangCap.addItem("Thạc sỹ");
		cbBangCap.addItem("Tiến sỹ");
		
		cbCheDoLuong = new JComboBox();
		cbCheDoLuong.addItem("---Chọn---");
		cbCheDoLuong.addItem("Lương tháng");
		cbCheDoLuong.addItem("Lương sản phẩm");
		
		lblCheDoLuong = new JLabel("Chế độ lương: ");
		radLuongThang = new JRadioButton("Lương tháng");
		radLuongSP = new JRadioButton("Lương sản phẩm");
		grCheDoLuong = new ButtonGroup();
		grCheDoLuong.add(radLuongThang);
		grCheDoLuong.add(radLuongSP);
		
		//các button: thêm, sửa, xóa, lưu
		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
				
		lblMaNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtMaNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
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
		lblNgayVaoLam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		dcNgayVaoLam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblChucVu.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbChucVu.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radLuongThang.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radLuongSP.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongCB.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongCB.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongPC.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongPC.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblKhoanGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtKhoanGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radNam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radNu.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radCuNhan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radThacSy.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radTienSy.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTrangThaiLamViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbTrangThaiLamViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblNghiViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		chkNghiViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		btnThem.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnSua.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnXoa.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLuu.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLoc.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		btnThem.setBackground(buttonColor);
		btnThem.setForeground(Color.WHITE);
		btnSua.setBackground(buttonColor);
		btnSua.setForeground(Color.WHITE);
		btnXoa.setBackground(buttonColor);
		btnXoa.setForeground(Color.WHITE);
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		
		//tạo các box để chứa dữ liệu
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		Box d = Box.createVerticalBox();
		
		pnThongTin.add(a);
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(b);
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(c);
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(d);
		
		//box a chứa mã, tên, ngày sinh, giới tính của NV
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		Box a4 = Box.createHorizontalBox();
		
		//cột 1
		a1.add(lblMaNV);
		a1.add(txtMaNV);
		a2.add(lblHoTenNV);
		a2.add(txtHoTenNV);
		a3.add(lblNgaySinh);
		a3.add(dcNgaySinh);
		a4.add(lblGioiTinh);
		a4.add(cbGioiTinh);
						
		lblMaNV.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblHoTenNV.getPreferredSize());
		
		a.add(a1);
		a.add(Box.createVerticalStrut(10));
		a.add(a2);
		a.add(Box.createVerticalStrut(10));
		a.add(a3);
		a.add(Box.createVerticalStrut(10));
		a.add(a4);
				
		//box b chứa địa chỉ, cccd, bhxh, mst của NV
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		//cột 2
		b1.add(lblDiaChi);
		b1.add(txtDiaChi);
		b2.add(lblCCCD);
		b2.add(txtCCCD);
		b3.add(lblBHXH);
		b3.add(txtBHXH);
		b4.add(lblMST);
		b4.add(txtMST);
		
		lblDiaChi.setPreferredSize(lblCCCD.getPreferredSize());
		lblBHXH.setPreferredSize(lblCCCD.getPreferredSize());
		lblMST.setPreferredSize(lblCCCD.getPreferredSize());
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		
		//box c chứa ngày vào làm, phòng ban, chức vụ, bằng cấp của NV
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		Box c4 = Box.createHorizontalBox();
		Box c5 = Box.createHorizontalBox();
		
		//cột 3
		c1.add(lblNgayVaoLam);
		c1.add(dcNgayVaoLam);
		c2.add(lblPhongBan);
		c2.add(cbPhongBan);
		c3.add(lblChucVu);
		c3.add(cbChucVu);
		c4.add(lblBangCap);
		c4.add(cbBangCap);
		c5.add(lblTrangThaiLamViec);
		c5.add(cbTrangThaiLamViec);
				
		lblPhongBan.setPreferredSize(lblTrangThaiLamViec.getPreferredSize());
		lblChucVu.setPreferredSize(lblTrangThaiLamViec.getPreferredSize());
		lblBangCap.setPreferredSize(lblTrangThaiLamViec.getPreferredSize());
		lblNgayVaoLam.setPreferredSize(lblTrangThaiLamViec.getPreferredSize());
		
		c.add(c1);
		c.add(Box.createVerticalStrut(10));
		c.add(c2);
		c.add(Box.createVerticalStrut(10));
		c.add(c3);
		c.add(Box.createVerticalStrut(10));
		c.add(c4);
		c.add(Box.createVerticalStrut(10));
		c.add(c5);
		
		//box d chứa lương cơ bản, lương phụ cấp, các khoản giảm trừ, tạm ứng, chế độ lương
		Box d1 = Box.createHorizontalBox();
		Box d2 = Box.createHorizontalBox();
		Box d3 = Box.createHorizontalBox();
		Box d4 = Box.createHorizontalBox();
		Box d5 = Box.createHorizontalBox();
		
		d1.add(lblLuongCB);
		d1.add(txtLuongCB);
		d2.add(lblLuongPC);
		d2.add(txtLuongPC);
		d3.add(lblKhoanGiamTru);
		d3.add(txtKhoanGiamTru);
		d4.add(lblTamUng);
		d4.add(txtTamUng);
		d5.add(lblCheDoLuong);
		d5.add(cbCheDoLuong);
		
		lblLuongCB.setPreferredSize(lblKhoanGiamTru.getPreferredSize());
		lblLuongPC.setPreferredSize(lblKhoanGiamTru.getPreferredSize());
		lblTamUng.setPreferredSize(lblKhoanGiamTru.getPreferredSize());
		lblCheDoLuong.setPreferredSize(lblKhoanGiamTru.getPreferredSize());
		
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d.add(d2);
		d.add(Box.createVerticalStrut(10));
		d.add(d3);
		d.add(Box.createVerticalStrut(10));
		d.add(d4);
		d.add(Box.createVerticalStrut(10));
		d.add(d5);
		
		Box k = Box.createVerticalBox();
		Box k1 = Box.createHorizontalBox();
//		pnThongTin.add(Box.createVerticalStrut(100));
		
		k.add(add(Box.createVerticalStrut(20)));
		k1.add(btnThem);
		k1.add(Box.createHorizontalStrut(10));
		k1.add(btnSua);
		k1.add(Box.createHorizontalStrut(10));
		k1.add(btnXoa);
		k1.add(Box.createHorizontalStrut(10));
		k1.add(btnLuu);
		
		k.add(k1);
		pnThongTin.add(k);
		
		//container chứa pnContent
		Container container = getContentPane();
		container.add(pnContent);
		
		//đăng ký sự kiện cho các button
		btnLoc.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(o)) {
			
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QLNV_V1().setVisible(true);
	}
}

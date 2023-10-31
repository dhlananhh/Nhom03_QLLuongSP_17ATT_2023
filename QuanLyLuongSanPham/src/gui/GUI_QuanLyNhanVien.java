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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;


public class GUI_QuanLyNhanVien extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	
	public GUI_QuanLyNhanVien() {
		//set JFrame properties
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setSize(screenSize.width, screenSize.height);
//		setExtendedState(MAXIMIZED_BOTH);
		
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
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		JPanel pnTTHanhChinh = new JPanel();
		pnCenter.add(pnTTHanhChinh, BorderLayout.NORTH);
		pnTTHanhChinh.setBackground(bgColor);
		pnTTHanhChinh.setBorder(BorderFactory.createTitledBorder(null, "Thông tin hành chính"));
		
		JPanel pnTTLuong = new JPanel();
		pnCenter.add(pnTTLuong, BorderLayout.CENTER);
		pnTTLuong.setBackground(bgColor);
		
		JPanel pnTTLuongHC = new JPanel();
		pnTTLuong.add(pnTTLuongHC);
		pnTTLuongHC.setBackground(bgColor);
		pnTTLuongHC.setBorder(BorderFactory.createTitledBorder(null, "Thông tin lương (hành chính)"));
		
		
		JPanel pnTTLuongSP = new JPanel();
		pnTTLuong.add(pnTTLuongSP);
		pnTTLuongSP.setBackground(bgColor);
		pnTTLuongSP.setBorder(BorderFactory.createTitledBorder(null, "Thông tin lương (sản phẩm)"));
		
		JPanel pnThongTinNV = new JPanel();
		pnTTLuong.add(pnThongTinNV, BorderLayout.SOUTH);
		pnThongTinNV.setBackground(bgColor);
		pnThongTinNV.setBorder(BorderFactory.createTitledBorder(null, "Thông tin nhân viên"));
		
		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		JTextField txtMaNV = new JTextField(20);
		
		JLabel lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		JTextField txtHoTenNV = new JTextField(20);
		
		JLabel lblGioiTinh = new JLabel("Giới tính: ");
				
		JComboBox cbGioiTinh = new JComboBox();
		cbGioiTinh.addItem("---Chọn---");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
		JDateChooser dcNgaySinh = new JDateChooser();
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		JTextField txtDiaChi = new JTextField(20);
		
		JLabel lblCCCD = new JLabel("CMND/CCCD: ");
		JTextField txtCCCD = new JTextField(20);
		
		JLabel lblBHXH = new JLabel("BHXH: ");
		JTextField txtBHXH = new JTextField(20);
		
		JLabel lblMST = new JLabel("MST: ");
		JTextField txtMST = new JTextField(20);
		
		JLabel lblPhongBan = new JLabel("Phòng ban: ");
		JComboBox cbPhongBan = new JComboBox();
		cbPhongBan.addItem("---Chọn---");
		cbPhongBan.addItem("Phòng Tài chính - Kế toán");
		cbPhongBan.addItem("Phòng kinh doanh - Chăm sóc khách hàng");
		cbPhongBan.addItem("Phòng Hành chính - Nhân sự");
		cbPhongBan.addItem("Phòng kỹ thuật - sản xuất");
		
		JLabel lblChucDanh = new JLabel("Chức danh: ");
		JComboBox cbChucDanh = new JComboBox();
		cbChucDanh.addItem("---Chọn---");
		cbChucDanh.addItem("Giám đốc");
		cbChucDanh.addItem("Trưởng phòng");
		cbChucDanh.addItem("Nhân viên");
		cbChucDanh.addItem("Công nhân");
		
		JLabel lblLuongCB = new JLabel("Lương cơ bản: ");
		JTextField txtLuongCB = new JTextField(20);
		
		JLabel lblLuongPC = new JLabel("Lương phụ cấp: ");
		JTextField txtLuongPC = new JTextField(20);
		
		JLabel lblKhoanGiamTru = new JLabel("Các khoản giảm trừ: ");
		JTextField txtKhoanGiamTru = new JTextField(20);
		
		JLabel lblTamUng = new JLabel("Tạm ứng: ");
		JTextField txtTamUng = new JTextField(20);
		
		JLabel lblTrangThai = new JLabel("Trạng thái: ");
		JComboBox cbTrangThai = new JComboBox();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đang làm");
		cbTrangThai.addItem("Nghỉ việc");
		
		JLabel lblNgayVao = new JLabel("Ngày vào: ");
		JDateChooser dcNgayVao = new JDateChooser();
		dcNgayVao.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		JLabel lblCheDoLuong = new JLabel("Chế độ lương: ");
		JComboBox cbCheDoLuong = new JComboBox();
		cbCheDoLuong.addItem("---Chọn---");
		cbCheDoLuong.addItem("Lương hành chính");
		cbCheDoLuong.addItem("Lương sản phẩm");
		
		JButton btnThem1 = new JButton("Thêm");
		JButton btnXoa1 = new JButton("Xóa");
		JButton btnLuu1 = new JButton("Lưu");
		
		JButton btnThem2 = new JButton("Thêm");
		JButton btnXoa2 = new JButton("Xóa");
		JButton btnLuu2 = new JButton("Lưu");
		
		JLabel lblLuongChinh = new JLabel("Lương chính: ");
		JTextField txtLuongChinh = new JTextField(20);
		JLabel lblTrachNhiemNV = new JLabel("Trách nhiệm: ");
		JTextField txtTrachNhiemNV = new JTextField(20);
		JLabel lblAnTruaNV = new JLabel("Ăn trưa: ");
		JTextField txtAnTruaNV = new JTextField(20);
		JLabel lblDienThoaiNV = new JLabel("Điện thoại: ");
		JTextField txtDienThoaiNV = new JTextField(20);
		JLabel lblXangXeNV = new JLabel("Xăng xe: ");
		JTextField txtXangXeNV = new JTextField(20);
		
		JLabel lblAnTruaCN = new JLabel("Ăn trưa: ");
		JTextField txtAnTruaCN = new JTextField(20);
		JLabel lblDienThoaiCN = new JLabel("Điện thoại: ");
		JTextField txtDienThoaiCN = new JTextField(20);
		JLabel lblXangXeCN = new JLabel("Xăng xe: ");
		JTextField txtXangXeCN = new JTextField(20);
		
		JLabel lblCongViec = new JLabel("Công việc được giao: ");
		JComboBox cbCongViec = new JComboBox();
		String[] tasks = {"Mộc thô - Bàn nhân viên", "Tinh thô - Bàn giám đốc", "Lắp ráp - Ghế giám đốc"};
		for (int i = 0; i < tasks.length; i++)
			cbCongViec.addItem(tasks[i]);
		
		JLabel lblDonGiaSP = new JLabel("Đơn giá sản phẩm: ");
		JTextField txtDonGiaSP = new JTextField(20);
		
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
		lblGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblNgayVao.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		dcNgayVao.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongCB.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongCB.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongPC.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongPC.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTamUng.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblKhoanGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtKhoanGiamTru.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbPhongBan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblChucDanh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbChucDanh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTrangThai.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbTrangThai.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbCheDoLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLuongChinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtLuongPC.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTrachNhiemNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTrachNhiemNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblAnTruaNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtAnTruaNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblDienThoaiNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtDienThoaiNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblXangXeNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtXangXeNV.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblAnTruaCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtAnTruaCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblDienThoaiCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtDienThoaiCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblXangXeCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtXangXeCN.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		btnThem1.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnXoa1.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLuu1.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
				
		btnThem1.setBackground(buttonColor);
		btnThem1.setForeground(Color.WHITE);
		btnXoa1.setBackground(buttonColor);
		btnXoa1.setForeground(Color.WHITE);
		btnLuu1.setBackground(buttonColor);
		btnLuu1.setForeground(Color.WHITE);
		
		btnThem2.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnXoa2.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLuu2.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
				
		btnThem2.setBackground(buttonColor);
		btnThem2.setForeground(Color.WHITE);
		btnXoa2.setBackground(buttonColor);
		btnXoa2.setForeground(Color.WHITE);
		btnLuu2.setBackground(buttonColor);
		btnLuu2.setForeground(Color.WHITE);
		
		//pnTTHanhChinh
		//tạo 2 vertical box để chứa dữ liệu
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		
		//thêm các box a, b, c vào pnTTHanhChinh
		pnTTHanhChinh.add(a);
		pnTTHanhChinh.add(Box.createHorizontalStrut(50));
		pnTTHanhChinh.add(b);
		pnTTHanhChinh.add(Box.createHorizontalStrut(50));
		pnTTHanhChinh.add(c);
		
		//box a
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		Box a4 = Box.createHorizontalBox();
		
		//cột 1
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
		
		//box c
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		Box c4 = Box.createHorizontalBox();
		Box c5 = Box.createHorizontalBox();
		
		c1.add(lblNgayVao);
		c1.add(dcNgayVao);
		c2.add(lblPhongBan);
		c2.add(cbPhongBan);
		c3.add(lblChucDanh);
		c3.add(cbChucDanh);
		c4.add(lblTrangThai);
		c4.add(cbTrangThai);
		c5.add(lblCheDoLuong);
		c5.add(cbCheDoLuong);
		
		lblNgayVao.setPreferredSize(lblCheDoLuong.getPreferredSize());
		lblPhongBan.setPreferredSize(lblCheDoLuong.getPreferredSize());
		lblChucDanh.setPreferredSize(lblCheDoLuong.getPreferredSize());
		lblTrangThai.setPreferredSize(lblCheDoLuong.getPreferredSize());
		
		c.add(c1);
		c.add(Box.createVerticalStrut(10));
		c.add(c2);
		c.add(Box.createVerticalStrut(10));
		c.add(c3);
		c.add(Box.createVerticalStrut(10));
		c.add(c4);
		c.add(Box.createVerticalStrut(10));
		c.add(c5);
		
		Box f = Box.createVerticalBox();
		Box f1 = Box.createHorizontalBox();
		
		f1.add(lblLuongChinh);
		f1.add(txtLuongChinh);
		f.add(f1);
		pnTTLuongHC.add(f);
		
		JPanel pnPhuCapNV = new JPanel();
		pnPhuCapNV.setBorder(BorderFactory.createTitledBorder(null, "Phụ cấp"));
		pnTTLuongHC.add(pnPhuCapNV, BorderLayout.CENTER);
		pnPhuCapNV.setBackground(bgColor);
		
		Box d = Box.createVerticalBox();
		Box e = Box.createVerticalBox();
		
		Box d1 = Box.createHorizontalBox();
		Box d2 = Box.createHorizontalBox();
		
		Box e1 = Box.createHorizontalBox();
		Box e2 = Box.createHorizontalBox();
		
		//cột 1
		d1.add(lblTrachNhiemNV);
		d1.add(txtTrachNhiemNV);
		d2.add(lblAnTruaNV);
		d2.add(txtAnTruaNV);
		
		lblAnTruaNV.setPreferredSize(lblTrachNhiemNV.getPreferredSize());
		
		d.add(Box.createVerticalStrut(10));
		d.add(d1);
		d.add(Box.createVerticalStrut(10));
		d.add(d2);
		
		//cột 2
		e1.add(lblDienThoaiNV);
		e1.add(txtDienThoaiNV);
		e2.add(lblXangXeNV);
		e2.add(txtXangXeNV);
		
		lblXangXeNV.setPreferredSize(lblDienThoaiNV.getPreferredSize());
		
		e.add(Box.createVerticalStrut(10));
		e.add(e1);
		e.add(Box.createVerticalStrut(10));
		e.add(e2);
		
		pnPhuCapNV.add(d);
		pnPhuCapNV.add(Box.createHorizontalStrut(50));
		pnPhuCapNV.add(e);

		Box k = Box.createHorizontalBox();
		k.add(btnThem1);
		k.add(Box.createHorizontalStrut(10));
		k.add(btnXoa1);
		k.add(Box.createHorizontalStrut(10));
		k.add(btnLuu1);
		
		pnTTLuongHC.add(k);
				
		Box t = Box.createVerticalBox();
		Box t1 = Box.createHorizontalBox();
		pnTTLuongSP.add(t);
		
		t1.add(lblCongViec);
		t1.add(cbCongViec);
		t.add(t1);
		pnTTLuongSP.add(t);
		
		JPanel pnPhuCapCN = new JPanel();
		pnPhuCapCN.setBorder(BorderFactory.createTitledBorder(null, "Phụ cấp"));
		pnTTLuongSP.add(pnPhuCapCN, BorderLayout.CENTER);
		pnPhuCapCN.setBackground(bgColor);
		
		Box g = Box.createVerticalBox();
		Box h = Box.createVerticalBox(); 		
		
		Box g1 = Box.createHorizontalBox();
		
		Box h1 = Box.createHorizontalBox();
		Box h2 = Box.createHorizontalBox();
		
		//cột 1
		g1.add(lblAnTruaCN);
		g1.add(txtAnTruaCN);
		
		g.add(Box.createVerticalStrut(10));
		g.add(g1);
		
		//cột 2
		h1.add(lblDienThoaiCN);
		h1.add(txtDienThoaiCN);
		h2.add(lblXangXeCN);
		h2.add(txtXangXeCN);
		
		lblXangXeCN.setPreferredSize(lblDienThoaiCN.getPreferredSize());
		
		h.add(Box.createVerticalStrut(10));
		h.add(h1);
		h.add(Box.createVerticalStrut(10));
		h.add(h2);
		
		
		JPanel pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBorder(BorderFactory.createTitledBorder(null, "Danh sách nhân viên"));
		JLabel lblHello = new JLabel("Hello");
		pnSouth.add(lblHello);	
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnThem1.addActionListener(this);
		btnXoa1.addActionListener(this);
		btnLuu1.addActionListener(this);
		btnThem2.addActionListener(this);
		btnXoa2.addActionListener(this);
		btnLuu2.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyNhanVien().setVisible(true);
	}
}

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


public class GUI_QuanLyNhanVienHanhChinh extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblTieuDe, lblGioiTinh, lblBangCap, lblNghiViec;
	private JLabel lblMaNV, lblHoTenNV, lblNgaySinh, lblCCCD, lblBHXH, lblMST;
	private JLabel lblDiaChi, lblNgayVaoLam, lblChucVu, lblPhongBan;
	private JLabel lblLuongCB, lblLuongPC, lblCheDoLuong;
	private JTextField txtMaNV, txtHoTenNV, txtDiaChi;
	private JTextField txtCCCD, txtBHXH, txtMST;
	private JTextField txtLuongCB, txtLuongPC;
	private JDateChooser dcNgaySinh, dcNgayVaoLam;
	private JComboBox cbLoc, cbPhongBan, cbChucVu;
	private Font BVNPro;
	private JTable table;
	private DefaultTableModel model;
	private ButtonGroup grGioiTinh, grBangCap, grCheDoLuong;
	private JRadioButton radLuongThang, radLuongSP;
	private JRadioButton radNam, radNu;
	private JRadioButton radCuNhan, radThacSy, radTienSy;
	private JCheckBox chkNghiViec;
	private JButton btnLoc, btnThem, btnSua, btnXoa;
	
	
	public GUI_QuanLyNhanVienHanhChinh() {
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
		
		Box a = Box.createVerticalBox();
		Box a1 = Box.createHorizontalBox();
				
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
		
		lblGioiTinh = new JLabel("Giới tính: ");
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		
		lblGioiTinh.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radNam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radNu.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		grGioiTinh = new ButtonGroup();
		grGioiTinh.add(radNam);
		grGioiTinh.add(radNu);
		
		lblBangCap = new JLabel("Bằng cấp: ");
		radCuNhan = new JRadioButton("Cử nhân");
		radThacSy = new JRadioButton("Thạc sỹ");
		radTienSy = new JRadioButton("Tiến sỹ");
		
		lblBangCap.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radCuNhan.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radThacSy.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		radTienSy.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		grBangCap = new ButtonGroup();
		grBangCap.add(radCuNhan);
		grBangCap.add(radThacSy);
		grBangCap.add(radTienSy);
		
		lblNghiViec = new JLabel("Nghỉ việc: ");
		chkNghiViec = new JCheckBox();
		
		lblNghiViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		chkNghiViec.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		a1.add(btnLoc);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(cbLoc);
		a1.add(Box.createHorizontalStrut(50));
		a1.add(lblGioiTinh);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(radNam);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(radNu);
		a1.add(Box.createHorizontalStrut(50));
		a1.add(lblBangCap);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(radCuNhan);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(radThacSy);
		a1.add(Box.createHorizontalStrut(10));
		a1.add(radTienSy);
		
		a.add(a1);
		pnTacVu.add(a);
		
		//pnThongTin chứa các textfield nhập thông tin
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setBackground(bgColor);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(20);
		
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField(20);
		
		lblNgaySinh = new JLabel("Ngày sinh: ");
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField(20);
		
		lblCCCD = new JLabel("CMND/CCCD: ");
		txtCCCD = new JTextField(20);
		
		lblBHXH = new JLabel("BHXH: ");
		txtBHXH = new JTextField(20);
		
		lblMST = new JLabel("MST");
		txtMST = new JTextField(20);
		
		lblNgayVaoLam = new JLabel("Ngày vào làm: ");
		dcNgayVaoLam = new JDateChooser();
		dcNgayVaoLam.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblPhongBan = new JLabel("Phòng ban: ");
		cbPhongBan = new JComboBox();
		cbPhongBan.addItem("Phòng Tài chính - Kế toán");
		cbPhongBan.addItem("Phòng kinh doanh - Chăm sóc khách hàng");
		cbPhongBan.addItem("Phòng Hành chính - Nhân sự");
		cbPhongBan.addItem("Phòng kỹ thuật - sản xuất");
		
		lblChucVu = new JLabel("Chức vụ: ");
		cbChucVu = new JComboBox();
		cbChucVu.addItem("Giám đốc");
		
		lblCheDoLuong = new JLabel("Chế độ lương: ");
		radLuongThang = new JRadioButton("Lương tháng");
		radLuongSP = new JRadioButton("Lương sản phẩm");
		grCheDoLuong = new ButtonGroup();
		grCheDoLuong.add(radLuongThang);
		grCheDoLuong.add(radLuongSP);
		
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
		
		b1.add(lblMaNV);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtMaNV);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lblNgayVaoLam);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(dcNgayVaoLam);
		b2.add(lblHoTenNV);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtHoTenNV);
		b2.add(lblPhongBan);
		b2.add(cbPhongBan);
		b3.add(lblGioiTinh);
		b3.add(radNam);
		b3.add(radNu);
		b3.add(lblChucVu);
		b3.add(cbChucVu);
		b4.add(lblNgaySinh);
		b4.add(dcNgaySinh);
		b5.add(lblDiaChi);
		b5.add(txtDiaChi);
		b6.add(lblCCCD);
		b6.add(txtCCCD);
		
		
//		a1.add(Box.createHorizontalStrut(50));
//		a1.add(lblNghiViec);
//		a1.add(Box.createHorizontalStrut(10));
//		a1.add(chkNghiViec);
		
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b5);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b6);
		pnThongTin.add(b);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(o)) {
			
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_QuanLyNhanVienHanhChinh().setVisible(true);
	}
}

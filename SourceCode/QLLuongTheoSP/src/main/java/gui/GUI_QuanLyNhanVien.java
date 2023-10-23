package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.sun.tools.javac.Main;
import com.toedter.calendar.JDateChooser;


public class GUI_QuanLyNhanVien extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnThietLapTTNV, pnThongTinNV, pnTacVu,
					pnFilter, pnTitle;
	private JLabel 	lblTitle, lblTimTheoMaNV, lblTimTheoChucDanh;
	private JLabel 	lblMaNV, lblHoTenNV, lblGioiTinh, 
					lblNgaySinh, lblChucDanh, lblNgayVaoLam;
	private JTextField txtMaNV, txtHoTenNV, txtTimTheoMaNV, txtlblNgayVaoLam;
	private JComboBox cbGioiTinh, cbPhongBan, cbTimTheoPhongBan;
	private JButton	btnThem, btnLuu, btnTim, btnLoc, btnXoa, btnSua, btnXoaTrang, btnThoat;
	private JDateChooser dcNgaySinh, dcNgayVaoLam;
	private DefaultTableModel model;
	private JTable table;
	
	
	public GUI_QuanLyNhanVien() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
		setSize(900, 700);
//		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		Color title = new Color(0, 102, 204);
		Color button = new Color(0, 153, 204);
		
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnTitle = new JPanel();
		pnContent.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setBackground(title);
		
		lblTitle = new JLabel("Danh sách nhân viên");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		
		
		pnThongTinNV = new JPanel();
		pnContent.add(pnThongTinNV, BorderLayout.CENTER);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(20);
		
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField(20);
		
		lblNgaySinh = new JLabel("Ngày sinh: ");
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblGioiTinh = new JLabel("Giới tính: ");
		cbGioiTinh = new JComboBox<>();
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		
		lblNgayVaoLam = new JLabel("Ngày vào làm: ");
		dcNgayVaoLam = new JDateChooser();
		dcNgayVaoLam.getJCalendar().setPreferredSize(new Dimension(300, 200));
		
		lblChucDanh = new JLabel("Chức danh: ");
		cbPhongBan = new JComboBox<>();
		cbPhongBan.addItem("Nhân viên Hành chính");
		cbPhongBan.addItem("Công nhân Sản xuất");
		
		b1.add(lblMaNV);
		b1.add(txtMaNV);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(lblHoTenNV);
		b1.add(txtHoTenNV);
		b2.add(lblChucDanh);
		b2.add(cbPhongBan);
		b2.add(Box.createHorizontalStrut(30));
		b2.add(lblGioiTinh);
		b2.add(cbGioiTinh);
		b3.add(lblNgayVaoLam);
		b3.add(dcNgayVaoLam);
		b3.add(Box.createHorizontalStrut(30));
		b3.add(lblNgaySinh);
		b3.add(dcNgaySinh);
		
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		pnThongTinNV.add(b);
		
		
		createTable();
		
		
		pnFilter = new JPanel();
		pnThongTinNV.add(pnFilter);
		pnFilter.setBorder(BorderFactory.createTitledBorder("Bộ lọc tìm kiếm: "));
		pnFilter.add(Box.createVerticalStrut(50));
		
		lblTimTheoMaNV = new JLabel("Tìm theo mã NV: ");
		txtTimTheoMaNV = new JTextField(10);
		lblTimTheoChucDanh = new JLabel("Tìm theo phòng ban: ");
		cbTimTheoPhongBan = new JComboBox<>();
		cbTimTheoPhongBan.addItem("Nhân viên Hành chính");
		cbTimTheoPhongBan.addItem("Công nhân Sản xuất");
		
		btnTim = new JButton("Tìm");
		btnLoc = new JButton("Lọc");
		
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(button);
		btnLoc.setForeground(Color.WHITE);
		btnLoc.setBackground(button);
		
		pnFilter.add(lblTimTheoMaNV);
		pnFilter.add(txtTimTheoMaNV);
		pnFilter.add(btnTim);
		pnFilter.add(Box.createHorizontalStrut(30));
		pnFilter.add(lblTimTheoChucDanh);
		pnFilter.add(cbTimTheoPhongBan);
		pnFilter.add(btnLoc);
		
		
		pnTacVu = new JPanel();
		pnContent.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnThem = new JButton("Thêm");
		btnLuu = new JButton("Lưu");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnThoat = new JButton("Thoát");
		
		btnThem.setForeground(Color.WHITE);
		btnLuu.setForeground(Color.WHITE);
		btnSua.setForeground(Color.WHITE);
		btnXoa.setForeground(Color.WHITE);
		btnXoaTrang.setForeground(Color.WHITE);
		btnThoat.setForeground(Color.WHITE);
		
		btnThem.setBackground(button);
		btnLuu.setBackground(button);
		btnSua.setBackground(button);
		btnXoa.setBackground(button);
		btnXoaTrang.setBackground(button);
		btnThoat.setBackground(button);
		
		pnTacVu.add(btnThem);
		pnTacVu.add(btnLuu);
		pnTacVu.add(btnSua);
		pnTacVu.add(btnXoa);
		pnTacVu.add(btnXoaTrang);
		pnTacVu.add(btnThoat);
		
		
		addActionEvent();
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	//đăng ký sự kiện cho các button
	public void addActionEvent() {
		btnTim.addActionListener(this);
		btnLoc.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThoat.addActionListener(this);
	}
	
	
	//tạo bảng csdl hiển thị trên giao diện
	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("Mã NV");
		model.addColumn("Họ tên NV");
		model.addColumn("Ngày sinh");
		model.addColumn("Giới tính");
		model.addColumn("Chức danh");
		model.addColumn("Ngày vào làm");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(700, 300));
		
		pnTable.add(scrollPane);
		pnThongTinNV.add(pnTable);
	}
	

	//xử lý sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThoat)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }			
		}
	}

	
	public static void main(String[] args) {
		FlatCyanLightIJTheme.setup();
		
		new GUI_QuanLyNhanVien().setVisible(true);
	}
}

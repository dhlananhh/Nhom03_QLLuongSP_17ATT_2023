package gui;

import javax.swing.JFrame;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import connection.ConnectDB;
import dao.DAO_LuongCongNhanSanXuat;
import dao.DAO_LuongNhanVienHanhChinh;
import dao.DAO_NhanVienHanhChinh;
import entity.LuongNhanVienHanhChinh;



public class GUI_LuongNhanVienHanhChinh extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JPanel pnTable;
	private JLabel lblTieuDe, lblNam, lblThang, lblError;
	private JButton btnThem, btnLoc, btnTimKiem, btnXoa, btnLuu, btnXuatExcel;
	private JComboBox cbNam, cbThang;
	private Font BVNPro;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JTable tableNV, tableCN;
	private DefaultTableModel modelNV, modelCN;
	private DAO_LuongNhanVienHanhChinh dao_luongNV;
	private DAO_NhanVienHanhChinh dao_nv;
	private List<LuongNhanVienHanhChinh> listLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
	
	
	public GUI_LuongNhanVienHanhChinh() {
		//get sql connection
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao_luongNV = new DAO_LuongNhanVienHanhChinh();
		dao_nv = new DAO_NhanVienHanhChinh();
		
		//set JFrame properties
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setSize(screenSize.width, screenSize.height);
		setSize(1300, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//load fonts
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
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
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(headerColor);
		
		lblTieuDe = new JLabel("LƯƠNG NHÂN VIÊN HÀNH CHÍNH");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		JPanel pnThongTin = new JPanel();
		pnCenter.add(pnThongTin, BorderLayout.NORTH);
		pnThongTin.setBackground(bgColor);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		
		lblNam = new JLabel("Năm: ");
		lblNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		String[] years = {"---Chọn---",
	            "2020", "2021", "2022", "2023", "2024"
	        };
		cbNam = new JComboBox<>(years);
		cbNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		lblThang = new JLabel("Tháng: ");
		lblThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		String[] months = {"---Chọn---",
	            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
	            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
	        };
		cbThang = new JComboBox<>(months);
		cbThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		btnLoc = new JButton("Lọc");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnThem = new JButton("Thêm");
		btnXuatExcel = new JButton("Xuất Excel");
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnXoa.setBackground(buttonColor);
		btnXoa.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnThem.setBackground(buttonColor);
		btnThem.setForeground(Color.WHITE);
		btnXuatExcel.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnXuatExcel.setBackground(buttonColor);
		btnXuatExcel.setForeground(Color.WHITE);
		
		b1.add(lblNam);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbNam);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lblThang);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbThang);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnLoc);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnThem);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnXoa);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnLuu);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnXuatExcel);
		
		b.add(b1);
		pnThongTin.add(b);

		//pnTable chứa bảng lương NV 
		pnTable = new JPanel();
		pnTable.setBackground(bgColor);
		pnCenter.add(pnTable, BorderLayout.CENTER);
		pnTable.setLayout(new BorderLayout());
		
		JPanel pnTieuDeBang = new JPanel();
		pnTable.add(pnTieuDeBang, BorderLayout.NORTH);
		pnTieuDeBang.setBackground(bgColor);
		
		JLabel lblTieuDeBang = new JLabel("Bảng lương nhân viên hành chính");
		pnTieuDeBang.add(lblTieuDeBang);
		lblTieuDeBang.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 15));
		
		lblTimKiem = new JLabel(" | Tìm kiếm theo tên nhân viên: ");
		lblTimKiem.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtTimKiem = new JTextField();
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnTimKiem.setBackground(buttonColor);
		btnTimKiem.setForeground(Color.WHITE);
		
		pnTieuDeBang.add(lblTimKiem);
		pnTieuDeBang.add(txtTimKiem);
		pnTieuDeBang.add(btnTimKiem);
		
		// tạo bảng chứa thông tin của NV
		createTableNV();
		
		// load bảng NV
		loadBangNV();
		
//		pnTableNV.setVisible(false);
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}
	
	
	public void createTableNV() {
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("STT");
		modelNV.addColumn("Năm");
		modelNV.addColumn("Tháng");
		modelNV.addColumn("Mã NV");
		modelNV.addColumn("Họ tên");
		modelNV.addColumn("Lương cơ bản");
		modelNV.addColumn("Phụ cấp");
		modelNV.addColumn("Giảm trừ");
		modelNV.addColumn("Tạm ứng");
		modelNV.addColumn("Thực lãnh");
		
		JScrollPane scrollPaneNV = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneNV.setPreferredSize(new Dimension(1000, 300));
		pnTable.add(scrollPaneNV, BorderLayout.CENTER);
	}
	
	
	public void loadBangNV() {
		dao_luongNV = new DAO_LuongNhanVienHanhChinh();
		listLuongNV = dao_luongNV.docDuLieu();
		try {
			for (LuongNhanVienHanhChinh luongNV : listLuongNV) {
				Object[] row = {
						luongNV.getMaBangLuongHC(), luongNV.getNam(), luongNV.getThang(), 
						luongNV.getNhanVien().getMaNV(), luongNV.getNhanVien().getHoTenNV(),
						luongNV.getLuongCoBan(), luongNV.getPhuCap(),
						luongNV.getGiamTru(), luongNV.getTamUng()
						
				};
				modelNV.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnLoc)) {
			
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_LuongNhanVienHanhChinh().setVisible(true);
	}
}

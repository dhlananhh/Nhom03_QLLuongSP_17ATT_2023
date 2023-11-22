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
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import entity.NhanVienHanhChinh;



public class GUI_LuongNhanVienHanhChinh extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JPanel pnTable;
	private JLabel lblTieuDe, lblLocNam, lblLocThang, lblError;
	private JLabel lblMa, lblNam, lblThang, lblTrangThai, lblMaNV, lblHSL;
	private JLabel lblLuongCB, lblPhuCap, lblLuongThucLanh;
	private JTextField txtMa, txtNam, txtThang, txtHSL;
	private JTextField txtLuongCB, txtPhuCap, txtLuongThucLanh;
	private JButton btnThem, btnSua, btnLoc, btnTimKiem, btnXoa, btnLuu, btnXuatExcel;
	private JComboBox cbMaNV, cbTrangThai;
	private JComboBox cbLocNam, cbLocThang;
	private Font BVNPro;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	
	private DAO_LuongNhanVienHanhChinh dao_luongNV;
	private List<LuongNhanVienHanhChinh> listLuongNV = new ArrayList<LuongNhanVienHanhChinh>();
	
	private DAO_NhanVienHanhChinh dao_nv;
	private List<NhanVienHanhChinh> listNhanVien = new ArrayList<NhanVienHanhChinh>();
	
	
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
		
		lblMa = new JLabel("Mã: ");
		txtMa = new JTextField();
		txtMa.setEditable(false);
		
		lblNam = new JLabel("Năm: ");
		txtNam = new JTextField();
		
		lblThang = new JLabel("Tháng: ");
		txtThang = new JTextField();
		
		lblTrangThai = new JLabel("Trạng thái: ");
		cbTrangThai = new JComboBox<>();
		cbTrangThai.addItem("---Chọn---");
		cbTrangThai.addItem("Đã được trả lương");
		cbTrangThai.addItem("Chưa được trả lương");
		
		lblMaNV = new JLabel("Mã NV: ");
		cbMaNV = new JComboBox<>();
		cbMaNV.addItem("---Chọn---");
		
		lblHSL = new JLabel("Hệ số lương: ");
		txtHSL = new JTextField();
		
		lblLuongCB = new JLabel("Lương cơ bản: ");
		txtLuongCB = new JTextField();
		
		lblPhuCap = new JLabel("Phụ cấp: ");
		txtPhuCap = new JTextField();
		
		lblLuongThucLanh = new JLabel("Lương thực lãnh: ");
		txtLuongThucLanh = new JTextField();
		txtLuongThucLanh.setEditable(false);
		
		lblMa.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtMa.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
 		lblThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblMaNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbMaNV.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbTrangThai.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblHSL.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtHSL.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblLuongCB.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtLuongCB.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtPhuCap.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblLuongThucLanh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtLuongThucLanh.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		Box a = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box c = Box.createVerticalBox();
		
		pnThongTin.add(a);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(b);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(c);
		
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		
		a1.add(lblMa);
		a1.add(txtMa);
		a2.add(lblNam);
		a2.add(txtNam);
		a3.add(lblThang);
		a3.add(txtThang);
		
		lblMa.setPreferredSize(lblThang.getPreferredSize());
		lblNam.setPreferredSize(lblThang.getPreferredSize());
		
		a.add(a1);
		a.add(Box.createVerticalStrut(10));
		a.add(a2);
		a.add(Box.createVerticalStrut(10));
		a.add(a3);
		
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		
		b1.add(lblTrangThai);
		b1.add(cbTrangThai);
		b2.add(lblMaNV);
		b2.add(cbMaNV);
		b3.add(lblHSL);
		b3.add(txtHSL);
		
		lblMaNV.setPreferredSize(lblHSL.getPreferredSize());
		lblTrangThai.setPreferredSize(lblHSL.getPreferredSize());
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		
		c1.add(lblLuongCB);
		c1.add(txtLuongCB);
		c2.add(lblPhuCap);
		c2.add(txtPhuCap);
		c3.add(lblLuongThucLanh);
		c3.add(txtLuongThucLanh);
		
		lblLuongCB.setPreferredSize(lblLuongThucLanh.getPreferredSize());
		lblPhuCap.setPreferredSize(lblLuongThucLanh.getPreferredSize());
		
		c.add(c1);
		c.add(Box.createVerticalStrut(10));
		c.add(c2);
		c.add(Box.createVerticalStrut(10));
		c.add(c3);
		
		JPanel pnThongTinLuong = new JPanel();
		pnCenter.add(pnThongTinLuong, BorderLayout.CENTER);
		pnThongTinLuong.setBackground(bgColor);
		pnThongTinLuong.setLayout(new BorderLayout());
		
		JPanel pnTacVu = new JPanel();
		pnThongTinLuong.add(pnTacVu, BorderLayout.NORTH);
		pnTacVu.setBackground(bgColor);
		
		Box t = Box.createVerticalBox();
		Box t1 = Box.createHorizontalBox();
		
		lblLocNam = new JLabel("Năm: ");
		lblLocNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		String[] years = {"0",
	            "2020", "2021", "2022", "2023", "2024"
	        };
		cbLocNam = new JComboBox<>(years);
		cbLocNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		lblLocThang = new JLabel("Tháng: ");
		lblLocThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		String[] months = {"0",
	            "1", "2", "3", "4", "5", "6",
	            "7", "8", "9", "10", "11", "12"
	        };
		cbLocThang = new JComboBox<>(months);
		cbLocThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
		btnLoc = new JButton("Lọc");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
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
		btnSua.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnSua.setBackground(buttonColor);
		btnSua.setForeground(Color.WHITE);
		btnXuatExcel.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnXuatExcel.setBackground(buttonColor);
		btnXuatExcel.setForeground(Color.WHITE);
		
		t1.add(lblLocNam);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(cbLocNam);
		t1.add(Box.createHorizontalStrut(50));
		t1.add(lblLocThang);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(cbLocThang);
		t1.add(Box.createHorizontalStrut(20));
		t1.add(btnLoc);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnThem);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnSua);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnXoa);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnLuu);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnXuatExcel);
		
		t.add(t1);
		pnTacVu.add(t);

		//pnTable chứa bảng lương NV 
		pnTable = new JPanel();
		pnTable.setBackground(bgColor);
		pnThongTinLuong.add(pnTable, BorderLayout.CENTER);
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
		loadData();
		
		// auto generate mã NV
		autoGenerateMaLuongNV();
		
		// lấy data vô combobox mã NV
		getDataIntoCombobox();
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXuatExcel.addActionListener(this);
		tableNV.addMouseListener(this);
	}
	
	
	public void createTableNV() {
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("Mã");
		modelNV.addColumn("Năm");
		modelNV.addColumn("Tháng");
		modelNV.addColumn("Trạng thái");
		modelNV.addColumn("Mã NV");
		modelNV.addColumn("Hệ số lương");
		modelNV.addColumn("Lương cơ bản");
		modelNV.addColumn("Phụ cấp");
		modelNV.addColumn("Thực lãnh");
		
		JScrollPane scrollPaneNV = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneNV.setPreferredSize(new Dimension(500, 100));
		pnTable.add(scrollPaneNV, BorderLayout.CENTER);
	}
	
	
	// auto generate mã bảng lương NV
	public void autoGenerateMaLuongNV() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = 
				"SELECT MAX(maBangLuongHC) AS maxLuongNhanVien FROM LuongNhanVienHanhChinh";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				String maLuongNV = rs.getString("maxLuongNhanVien");
				if (maLuongNV == null) {
					txtMa.setText("LHCNV01");
				} else {
					Long stt = Long.parseLong(maLuongNV.substring(5));
					stt++;
					txtMa.setText("LHCNV" + String.format("%03d", stt));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// lấy data vô combobox phòng ban
	public void getDataIntoCombobox() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from NhanVienHanhChinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cbMaNV.addItem(rs.getString("maNV"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// tải dữ liệu lên bảng lương NV
	public void loadData() {
		dao_luongNV = new DAO_LuongNhanVienHanhChinh();
		listLuongNV = dao_luongNV.getDanhSachLuongNhanVien();
		modelNV.setRowCount(0);
		
		for (int i=0; i < listLuongNV.size(); i++) {
			LuongNhanVienHanhChinh luongNV = listLuongNV.get(i);
			
			Object[] rowData = {
					luongNV.getMaBangLuongHC(), luongNV.getNam(), luongNV.getThang(), 
					luongNV.isTrangThai() == true ? "Được trả lương" : "Chưa được trả lương",
					luongNV.getNhanVien().getMaNV(),
					luongNV.getHeSoLuong(),
					luongNV.getLuongCoBan(), luongNV.getPhuCap(),
					luongNV.getLuongThucLanh()
					
			};
			modelNV.addRow(rowData);
		}
	}
	
	
	// thêm dữ liệu lương NV vào database
	public boolean themDuLieuLuongNV() {
		LuongNhanVienHanhChinh luongNV = new LuongNhanVienHanhChinh();
		
		dao_luongNV.themMoiLuongNhanVien(luongNV);
		
		Object[] rowData = {
				luongNV.getMaBangLuongHC(), luongNV.getNam(), luongNV.getThang(), 
				luongNV.isTrangThai() == true ? "Đã được trả lương" : "Chưa được trả lương",
				luongNV.getNhanVien().getMaNV(),
				luongNV.getHeSoLuong(),
				luongNV.getLuongCoBan(), luongNV.getPhuCap(),
				luongNV.getLuongThucLanh()
				
		};
		modelNV.addRow(rowData);
		JOptionPane.showMessageDialog(this, "Thêm thành công!");
		
		return true;
	}
	
	
	
	
	
	// Hàm xuất file CSV (đuôi .csv)
	public void exportCSV(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(fileChooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file + ".csv"), StandardCharsets.UTF_8);
                 BufferedWriter bwrite = new BufferedWriter(out)) {

                DefaultTableModel modelNV = (DefaultTableModel) table.getModel();

                // Ten Cot
                for (int j = 0; j < modelNV.getColumnCount(); j++) {
                    bwrite.write(modelNV.getColumnName(j));
                    if (j < modelNV.getColumnCount() - 1) {
                        bwrite.write(","); // Phân tách giữa các cột
                    }
                }
                bwrite.write("\n");

                // Lay du lieu dong
                for (int j = 0; j < modelNV.getRowCount(); j++) {
                    for (int k = 0; k < modelNV.getColumnCount(); k++) {
                        bwrite.write(modelNV.getValueAt(j, k).toString());
                        if (k < modelNV.getColumnCount() - 1) {
                            bwrite.write(","); // Phân tách giữa các cột
                        }
                    }
                    bwrite.write("\n");
                }

                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (IOException e2) {
                e2.printStackTrace(); // In lỗi ra console để debug
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o.equals(tableNV)) {
			int tableRow = tableNV.getSelectedRow();
			
			if (tableRow >= 0 && tableRow < listLuongNV.size()) {
				LuongNhanVienHanhChinh luongNV = listLuongNV.get(tableRow);
				
				txtNam.setText(String.valueOf(luongNV.getNam()));
				txtThang.setText(String.valueOf(luongNV.getThang()));
				cbMaNV.setSelectedItem(luongNV.getNhanVien().getMaNV());
				txtHSL.setText(String.valueOf(luongNV.getHeSoLuong()));
				txtLuongCB.setText(String.valueOf(luongNV.getLuongCoBan()));
				txtPhuCap.setText(String.valueOf(luongNV.getPhuCap()));
				txtLuongThucLanh.setText(String.valueOf(luongNV.getLuongThucLanh()));
				cbTrangThai.setSelectedItem(luongNV.isTrangThai() ? "Đã được trả lương" : "Chưa được trả lương");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnXuatExcel)) {
//			exportExcel(tableNV);
			exportCSV(tableNV);
		}
		else if (o.equals(btnThem)) {
			themDuLieuLuongNV();
			loadData();
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_LuongNhanVienHanhChinh().setVisible(true);
	}
}

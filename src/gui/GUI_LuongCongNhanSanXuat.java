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
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAO_LuongCongNhanSanXuat;
import dao.DAO_LuongNhanVienHanhChinh;
import dao.DAO_NhanVienHanhChinh;
import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;



public class GUI_LuongCongNhanSanXuat extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JPanel pnTable;
	private JLabel lblTieuDe, lblLocNam, lblLocThang, lblError;
	private JLabel lblNgayTinhLuong, lblNam, lblThang, lblMaCN;
	private JLabel lblSoNgayDiLam, lblSoNgayNghi, lblSoNghiPhep, lblTienTamUng;
	private JTextField txtNam, txtThang, txtTienTamUng;
	private JDateChooser dcNgayTinhLuong;
	private JTextField txtSoNgayDiLam, txtSoNgayNghi, txtSoNghiPhep;
	private JButton btnThem, btnSua, btnLoc, btnTimKiem, btnXuatExcel;
	private JComboBox cbMaCN;
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
	
	
	public GUI_LuongCongNhanSanXuat() {
		//get sql connection
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		dao_luongNV = new DAO_LuongNhanVienHanhChinh();
//		dao_nv = new DAO_NhanVienHanhChinh();
		
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
		
		lblTieuDe = new JLabel("LƯƠNG CÔNG NHÂN SẢN XUẤT");
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
		
		lblNgayTinhLuong = new JLabel("Ngày tính lương: ");
		dcNgayTinhLuong = new JDateChooser();
		dcNgayTinhLuong.getCalendarButton().setToolTipText("Chọn ngày vào");
		dcNgayTinhLuong.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dcNgayTinhLuong.getCalendarButton().setBackground(Color.WHITE);
		dcNgayTinhLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		dcNgayTinhLuong.setBorder(new LineBorder(Color.WHITE));
		dcNgayTinhLuong.setDateFormatString("dd/MM/yyyy");
		
		lblNam = new JLabel("Năm: ");
		txtNam = new JTextField();
		
		lblThang = new JLabel("Tháng: ");
		txtThang = new JTextField();
		
		lblMaCN = new JLabel("Mã CN: ");
		cbMaCN = new JComboBox<>();
		cbMaCN.addItem("---Chọn---");
		
		lblSoNgayDiLam = new JLabel("Số ngày đi làm: ");
		txtSoNgayDiLam = new JTextField();
		
		lblSoNgayNghi = new JLabel("Số ngày nghỉ: ");
		txtSoNgayNghi = new JTextField();
		
		lblSoNghiPhep = new JLabel("Số ngày nghỉ có phép: ");
		txtSoNghiPhep = new JTextField();
		
		lblTienTamUng = new JLabel("Tiền tạm ứng: ");
		txtTienTamUng = new JTextField();
		
		lblNgayTinhLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
 		lblThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblMaCN.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbMaCN.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblTienTamUng.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtTienTamUng.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblSoNgayDiLam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtSoNgayDiLam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblSoNgayNghi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtSoNgayNghi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		lblSoNghiPhep.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		txtSoNghiPhep.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		
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
		
		a1.add(lblNgayTinhLuong);
		a1.add(dcNgayTinhLuong);
		a2.add(lblNam);
		a2.add(txtNam);
		a3.add(lblThang);
		a3.add(txtThang);
		
		lblNam.setPreferredSize(lblNgayTinhLuong.getPreferredSize());
		lblThang.setPreferredSize(lblNgayTinhLuong.getPreferredSize());
		
		a.add(a1);
		a.add(Box.createVerticalStrut(10));
		a.add(a2);
		a.add(Box.createVerticalStrut(10));
		a.add(a3);
		
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		
		b1.add(lblMaCN);
		b1.add(cbMaCN);
		b2.add(lblTienTamUng);
		b2.add(txtTienTamUng);
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		
		c1.add(lblSoNgayDiLam);
		c1.add(txtSoNgayDiLam);
		c2.add(lblSoNgayNghi);
		c2.add(txtSoNgayNghi);
		c3.add(lblSoNghiPhep);
		c3.add(txtSoNghiPhep);
		
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
		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXuatExcel = new JButton("Xuất Excel");
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
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
		t1.add(Box.createHorizontalStrut(10));
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
		
		JLabel lblTieuDeBang = new JLabel("Bảng lương công nhân sản xuất");
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
//		layDuLieuLuong();
				
		
		// lấy data vô combobox mã NV
//		getDataIntoCombobox();
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnSua.addActionListener(this);
		btnXuatExcel.addActionListener(this);
		tableNV.addMouseListener(this);
	}
	
	
	public void createTableNV() {
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("STT");
		modelNV.addColumn("Mã lương CN");
		modelNV.addColumn("Mã NV");
		modelNV.addColumn("Lương SP");
		modelNV.addColumn("Tiền PC trong tháng");
		modelNV.addColumn("Các khoản trừ vào lương");
		modelNV.addColumn("Tiền tạm ứng");
		modelNV.addColumn("Lương thực lãnh");
		
		JScrollPane scrollPaneNV = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneNV.setPreferredSize(new Dimension(400, 50));
		pnTable.add(scrollPaneNV, BorderLayout.CENTER);
	}
	
	
	
	
	
	// lấy data vô combobox phòng ban
	public void getDataIntoCombobox() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from NhanVienHanhChinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cbMaCN.addItem(rs.getString("maNV"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// tải dữ liệu lên bảng lương NV
	public void layDuLieuLuong() {
		dao_luongNV = new DAO_LuongNhanVienHanhChinh();
		listLuongNV = dao_luongNV.getDanhSachLuongNhanVien();
		modelNV.setRowCount(0);
		int i = 1;
		DecimalFormat df = new DecimalFormat("##,###00,000");
		
		for (LuongNhanVienHanhChinh luongNV : listLuongNV) {
			Object[] rowData = {
					i++,
					luongNV.getMaBangLuongHC(),
					luongNV.getNhanVien().getMaNV(),
					df.format(luongNV.getLuongChinh()),
					df.format(luongNV.getTienPhuCapTrongThang()),
					df.format(luongNV.getCacKhoanTruVaoLuongNV()),
					df.format(luongNV.getTienTamUng()),
					df.format(luongNV.getLuongThucLanh())
			};
			modelNV.addRow(rowData);
		}
	}
	
	/*
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
	*/
	
	
	
	
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
	
	
	public void hienThiDuLieuDuocChon() {
		int selectedRow = tableNV.getSelectedRow();
		
		if (selectedRow >= 0) {
			txtTienTamUng.setText(modelNV.getValueAt(selectedRow, 6).toString());
			cbMaCN.setSelectedItem(modelNV.getValueAt(selectedRow, 2).toString());
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		hienThiDuLieuDuocChon();
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
//			themDuLieuLuongNV();
//			layDuLieuLuong();
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_LuongCongNhanSanXuat().setVisible(true);
	}
}

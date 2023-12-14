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
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAO_CongNhan;
import dao.DAO_LuongCongNhanSanXuat;
import dao.DAO_LuongNhanVienHanhChinh;
import dao.DAO_NhanVienHanhChinh;
import entity.CongNhanSanXuat;
import entity.LuongCongNhanSanXuat;
import entity.LuongNhanVienHanhChinh;
import entity.NhanVienHanhChinh;



public class GUI_LuongCongNhanSanXuat extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JPanel pnTable;
	private JLabel lblTieuDe, lblLocNam, lblLocThang, lblError;
	private JLabel lblNgayTinhLuong, lblNam, lblThang, lblMaCN;
	private JLabel lblTienTamUng;
	private JTextField txtNam, txtThang, txtTienTamUng;
	private JDateChooser dcNgayTinhLuong;
	private JButton btnSua, btnLoc, btnTimKiem;
	private JButton btnXuatExcel, btnInPDF, btnLamMoi;
	private JComboBox cbMaCN;
	private JComboBox cbLocNam, cbLocThang;
	private Font BVNPro;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JTable tableCN;
	private DefaultTableModel modelCN;
	private int soLuongBangLuong;
	private boolean chinhSua = true;
	
	private DAO_LuongCongNhanSanXuat dao_luongCNSX;
	private DAO_CongNhan dao_cn;
	
	private List<LuongCongNhanSanXuat> dsLuongCN = new ArrayList<LuongCongNhanSanXuat>();
	private List<CongNhanSanXuat> dsCongNhan = new ArrayList<CongNhanSanXuat>();
	
	public GUI_LuongCongNhanSanXuat() {
		//get sql connection
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao_luongCNSX = new DAO_LuongCongNhanSanXuat();
		dao_cn = new DAO_CongNhan();
		
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
		Color buttonColor = new Color(0, 133, 204);
		
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
		dcNgayTinhLuong.getCalendarButton().setToolTipText("Chọn ngày: ");
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
		
		lblTienTamUng = new JLabel("Tiền tạm ứng: ");
		txtTienTamUng = new JTextField(10);
		
		lblNgayTinhLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
 		lblThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblMaCN.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		cbMaCN.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		lblTienTamUng.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtTienTamUng.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		
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
		
//		Box c1 = Box.createHorizontalBox();
//		Box c2 = Box.createHorizontalBox();
//		Box c3 = Box.createHorizontalBox();
//		
//		c.add(c1);
//		c.add(Box.createVerticalStrut(10));
//		c.add(c2);
//		c.add(Box.createVerticalStrut(10));
//		c.add(c3);
		
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
		lblLocNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		String[] years = {"0",
	            "2020", "2021", "2022", "2023", "2024"
	        };
		cbLocNam = new JComboBox<>(years);
		cbLocNam.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		
		lblLocThang = new JLabel("Tháng: ");
		lblLocThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		String[] months = {"0",
	            "1", "2", "3", "4", "5", "6",
	            "7", "8", "9", "10", "11", "12"
	        };
		cbLocThang = new JComboBox<>(months);
		cbLocThang.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		
		btnLoc = new JButton("Lọc");
		btnSua = new JButton("Sửa");
		btnXuatExcel = new JButton("Xuất Excel");
		btnLamMoi = new JButton("Làm mới");
		btnInPDF = new JButton("In PDF");
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnSua.setBackground(buttonColor);
		btnSua.setForeground(Color.WHITE);
		btnXuatExcel.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnXuatExcel.setBackground(buttonColor);
		btnXuatExcel.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnLamMoi.setBackground(buttonColor);
		btnLamMoi.setForeground(Color.WHITE);
		btnInPDF.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnInPDF.setBackground(buttonColor);
		btnInPDF.setForeground(Color.WHITE);
		
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
		t1.add(btnSua);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnLamMoi);
		t1.add(Box.createHorizontalStrut(10));
		t1.add(btnInPDF);
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
		lblTieuDeBang.setFont(new Font("Be Vietnam Pro Regular", Font.BOLD, 13));
		
		lblTimKiem = new JLabel(" | Tìm kiếm theo mã công nhân: ");
		lblTimKiem.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		txtTimKiem = new JTextField();
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 13));
		btnTimKiem.setBackground(buttonColor);
		btnTimKiem.setForeground(Color.WHITE);
		
		pnTieuDeBang.add(lblTimKiem);
		pnTieuDeBang.add(txtTimKiem);
		pnTieuDeBang.add(btnTimKiem);
		
		// tạo bảng chứa thông tin của NV
		createTableCN();
		
		// load bảng NV
		layDuLieuLuongCongNhan();
				
		
		// lấy data vô combobox mã NV
		getDataIntoCombobox();
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnLoc.addActionListener(this);
		btnSua.addActionListener(this);
		btnInPDF.addActionListener(this);
		btnXuatExcel.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		tableCN.addMouseListener(this);
	}
	
	
	public void createTableCN() {
		modelCN = new DefaultTableModel();
		tableCN = new JTable(modelCN);
		tableCN.setRowHeight(25);
		
		modelCN.addColumn("STT");
		modelCN.addColumn("Ngày tính lương");
		modelCN.addColumn("Mã lương CN");
		modelCN.addColumn("Năm");
		modelCN.addColumn("Tháng");
		modelCN.addColumn("Lương SP");
		modelCN.addColumn("Tiền tạm ứng");
		modelCN.addColumn("BHXH");
		modelCN.addColumn("BHYT");
		modelCN.addColumn("BHTN");
		modelCN.addColumn("Thuế TNCN");
		modelCN.addColumn("Lương thực lãnh");
		modelCN.addColumn("Mã CN");
		
		JScrollPane scrollPaneCN = new 	JScrollPane(tableCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCN.setPreferredSize(new Dimension(400, 50));
		pnTable.add(scrollPaneCN, BorderLayout.CENTER);
	}
	
	
	// lấy data vô combobox phòng ban
	public void getDataIntoCombobox() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from CongNhanSanXuat";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cbMaCN.addItem(rs.getString("maCN"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// tải dữ liệu lên bảng lương NV
	public void layDuLieuLuongCongNhan() {
		List<LuongCongNhanSanXuat> dsLuongCongNhanSanXuat = dao_luongCNSX.layDuLieuLuongCN();
		soLuongBangLuong = dsLuongCongNhanSanXuat.size();
		int i = 1;
		
		Locale locale = new Locale("vi", "VN");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		
		for (LuongCongNhanSanXuat luongCN : dsLuongCongNhanSanXuat) {
			modelCN.addRow(new Object[] {
					i++, luongCN.getNgayTinhLuong(), 
					luongCN.getMaBangLuongCN(), luongCN.getNam(), luongCN.getThang(), 
					currencyFormatter.format(luongCN.getLuongSanPham()), 
					currencyFormatter.format(luongCN.getTamUng()), 
					currencyFormatter.format(luongCN.getBaoHiemXaHoi()),
					currencyFormatter.format(luongCN.getBaoHiemYTe()), 
					currencyFormatter.format(luongCN.getBaoHiemThatNghiep()), 
					currencyFormatter.format(luongCN.getThueTNCN()), 
					currencyFormatter.format(luongCN.getLuongThucLanh()),
					luongCN.getCongNhan().getMaCN()});
		}
	}
	
	
	public void locDuLieuLuongCN() {
		String strThang = (String) cbLocThang.getSelectedItem();
		String strNam = (String) cbLocNam.getSelectedItem();
		int thang = Integer.parseInt(strThang);
		int nam = Integer.parseInt(strNam);
		List<LuongCongNhanSanXuat> dsLuongCN = new ArrayList<>();
		int i = 1;
		
		Locale locale = new Locale("vi", "VN");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		
		modelCN.getDataVector().removeAllElements();

		if (thang == 0 && nam != 0) {
		    dsLuongCN = dao_luongCNSX.timLuongTheoNam(nam);
		} else if (nam == 0 && thang != 0) {
		    dsLuongCN = dao_luongCNSX.timLuongTheoThang(thang);
		} else {
		    dsLuongCN = dao_luongCNSX.timLuongTheoThangNam(nam, thang);
		}

		for (LuongCongNhanSanXuat luongCN : dsLuongCN) {
		    modelCN.addRow(new Object[] {
			    		i++, luongCN.getNgayTinhLuong(), 
			    		currencyFormatter.format(luongCN.getLuongSanPham()), 
						currencyFormatter.format(luongCN.getTamUng()), 
						currencyFormatter.format(luongCN.getBaoHiemXaHoi()),
						currencyFormatter.format(luongCN.getBaoHiemYTe()), 
						currencyFormatter.format(luongCN.getBaoHiemThatNghiep()), 
						currencyFormatter.format(luongCN.getThueTNCN()), 
						currencyFormatter.format(luongCN.getLuongThucLanh()),
			            luongCN.getCongNhan().getMaCN()
		            }
		    );
		}
	}
	
	
	public void timLuongTheoMaCN() {
		String maCN = txtTimKiem.getText();
		List<LuongCongNhanSanXuat> dsluongCN = dao_luongCNSX.timLuongTheoMaCN(maCN);
		int i = 1;
		
		Locale locale = new Locale("vi", "VN");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		
		modelCN.getDataVector().removeAllElements();
		for (LuongCongNhanSanXuat luongCN : dsluongCN) {
		    modelCN.addRow(new Object[] {
			    		i++, luongCN.getNgayTinhLuong(), 
			    		currencyFormatter.format(luongCN.getLuongSanPham()), 
						currencyFormatter.format(luongCN.getTamUng()), 
						currencyFormatter.format(luongCN.getBaoHiemXaHoi()),
						currencyFormatter.format(luongCN.getBaoHiemYTe()), 
						currencyFormatter.format(luongCN.getBaoHiemThatNghiep()), 
						currencyFormatter.format(luongCN.getThueTNCN()), 
						currencyFormatter.format(luongCN.getLuongThucLanh()),
			            luongCN.getCongNhan().getMaCN()
		            }
		    );
		}
	}
	
	
	// hàm xuất excel
	public void exportExcel (JTable table) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn nơi lưu tệp Excel");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp Excel (.xls)", "xls");
		fileChooser.setFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				File fileToSave = fileChooser.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();
				if (fileToSave.exists()) {
					int response = JOptionPane.showConfirmDialog(null, "Tệp đã tồn tại. Bạn có muốn ghi đè không?",
							"Xác nhận ghi đè", JOptionPane.YES_NO_OPTION);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}
				}
				if (!filePath.endsWith(".xls")) {
					filePath += ".xls";
				}

				Workbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet("DanhSachNhanVien");

				org.apache.poi.ss.usermodel.Font titleFont = sheet.getWorkbook().createFont();
				titleFont.setBold(true);
				titleFont.setFontHeightInPoints((short) 16);

				CellStyle titleCellStyle = sheet.getWorkbook().createCellStyle();
				titleCellStyle.setFont(titleFont);
				titleCellStyle.setAlignment(HorizontalAlignment.CENTER);

				Row titleRow = sheet.createRow(0);

				Cell titleCell = titleRow.createCell(0);
				int nam = Integer.parseInt(cbLocNam.getSelectedItem().toString());
				int thang = Integer.parseInt(cbLocThang.getSelectedItem().toString());
				
				String txt = "Danh sách lương nhân viên Tháng " + thang + " Năm " + nam;
				titleCell.setCellValue(txt);
				titleCell.setCellStyle(titleCellStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableCN.getColumnCount() - 1));

				Row headerRow = sheet.createRow(1);
				for (int col = 0; col < tableCN.getColumnCount(); col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(tableCN.getColumnName(col));
					sheet.autoSizeColumn(col);
				}

				for (int row = 0; row < tableCN.getRowCount(); row++) {
					Row dataRow = sheet.createRow(row + 2);
					for (int col = 0; col < tableCN.getColumnCount(); col++) {
						Cell cell = dataRow.createCell(col);
						Object cellValue = tableCN.getValueAt(row, col);
						if (cellValue != null) {
							if (cellValue instanceof String) {
								cell.setCellValue((String) cellValue);
							} else if (cellValue instanceof Number) {
								cell.setCellValue(((Number) cellValue).doubleValue());
							} else {
								cell.setCellValue(cellValue.toString());
							}
						}
					}
				}

				for (int col = 0; col < tableCN.getColumnCount(); col++) {
					sheet.autoSizeColumn(col);
				}

				try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
					workbook.write(fileOut);
					JOptionPane.showMessageDialog(null, "Tạo và lưu tệp Excel thành công!");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi khi tạo và lưu tệp Excel!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void hienThiDuLieuDuocChon() {
		int selectedRow = tableCN.getSelectedRow();
		
		if (selectedRow >= 0) {
			txtTienTamUng.setText(modelCN.getValueAt(selectedRow, 6).toString());
			cbMaCN.setSelectedItem(modelCN.getValueAt(selectedRow, 12).toString());
			txtNam.setText(modelCN.getValueAt(selectedRow, 3).toString());
			txtThang.setText(modelCN.getValueAt(selectedRow, 4).toString());
			dcNgayTinhLuong.setDate((Date) modelCN.getValueAt(selectedRow, 1));
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
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
			exportExcel(tableCN);
		}
		if (o.equals(btnLamMoi)) {
			modelCN.getDataVector().removeAllElements();
			layDuLieuLuongCongNhan();
		}
		if(o.equals(btnLoc)) {
			locDuLieuLuongCN();
		}
		if(o.equals(btnTimKiem)) {
			timLuongTheoMaCN();
		}
		if (o.equals(btnSua)) {
            if (chinhSua) {
                btnSua.setText("Lưu");
                txtNam.setText("");
                txtThang.setText("");
                txtNam.setEditable(false);
                txtThang.setEditable(false);
                txtTienTamUng.setText("");
            } else {
                btnSua.setText("Sửa");
                double tienTamUng = Double.parseDouble(txtTienTamUng.getText());
                int row = tableCN.getSelectedRow();
                String maLuong = modelCN.getValueAt(row, 2).toString();
                double luongCu = Double.parseDouble(modelCN.getValueAt(row, 11).toString());
                double tienCu = Double.parseDouble(modelCN.getValueAt(row, 11).toString());
                LuongCongNhanSanXuat luongCN = new LuongCongNhanSanXuat(maLuong, tienTamUng);
                dao_luongCNSX.capNhatLuong(luongCN, luongCu, tienCu);
                modelCN.getDataVector().removeAllElements();
                layDuLieuLuongCongNhan();
                JOptionPane.showMessageDialog(null, "Sửa thành công!");
            }
            chinhSua = !chinhSua;
        }
		if (o.equals(btnInPDF)) {
			String maCN = (String) cbMaCN.getSelectedItem();
			gui.PDFLuongCN.InPhieuLuongCN(maCN);
		}
	}

	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_LuongCongNhanSanXuat().setVisible(true);
	}
}

package gui;


import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GUI_TaoBangLuong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnTitle, pnHeading, pnLuong, pnLoaiBangLuong,
					pnThongTin, pnTacVu, pnKyPhatLuong, pnChucDanh;
	private JLabel 	lblTitle, lblLoaiBangLuong,
					lblThang, lblNam, lblTuNgay, lblDenNgay;
	private JLabel	lblTenBangLuong;
	private JTextField txtTenBangLuong;
	private JComboBox cbLoaiBangLuong;
	private JComboBox<String> cbThang, cbNam; 
	private JButton btnLoc, btnChoose ,btnSave, btnRenew, btnCancel, btnExport, btnExit;
	private JTable table;
	private DefaultTableModel model;
	
	
	public GUI_TaoBangLuong() {
		buildGUI();
	}
	
	
	
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
		setSize(800, 600);
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
		
		lblTitle = new JLabel("Tạo bảng lương");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		
		
		pnThongTin = new JPanel();
		pnContent.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setLayout(new BorderLayout());
		
		pnLuong = new JPanel();
		pnThongTin.add(pnLuong, BorderLayout.NORTH);
		
		pnLoaiBangLuong = new JPanel();
		pnLuong.add(pnLoaiBangLuong);
		
		lblLoaiBangLuong = new JLabel("Loại bảng lương: ");
		cbLoaiBangLuong = new JComboBox<String>();
		cbLoaiBangLuong.addItem("Bảng lương cho nhân viên hành chính");
		cbLoaiBangLuong.addItem("Bảng lương cho công nhân sản xuất");

		
		btnLoc = new JButton("Lọc");
		btnLoc.setForeground(Color.WHITE);
		btnLoc.setBackground(button);
		
		pnLoaiBangLuong.add(Box.createHorizontalStrut(50));
		pnLoaiBangLuong.add(lblLoaiBangLuong);
		pnLoaiBangLuong.add(cbLoaiBangLuong);
		pnLoaiBangLuong.add(btnLoc);
		pnLoaiBangLuong.add(Box.createHorizontalStrut(50));
		
		
		pnKyPhatLuong = new JPanel();
		pnThongTin.add(pnKyPhatLuong);
		pnKyPhatLuong.setBorder(BorderFactory.createTitledBorder("Kỳ phát lương"));
		
		lblThang = new JLabel("Tháng: ");
		String[] months = {
	            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
	            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
	        };
		cbThang = new JComboBox<>(months);
		
		lblNam = new JLabel("Năm: ");
		String[] years = {
            "2020", "2021", "2022", "2023", "2024"
        };
        cbNam = new JComboBox<>(years);
		
		lblTuNgay = new JLabel("Từ ngày: ");
		lblDenNgay = new JLabel("Đến ngày: ");
		
		btnChoose = new JButton("Choose");
		btnChoose.setForeground(Color.WHITE);
		btnChoose.setBackground(button);
				
		pnKyPhatLuong.add(lblThang);
		pnKyPhatLuong.add(cbThang);
		pnKyPhatLuong.add(lblNam);
		pnKyPhatLuong.add(cbNam);
		pnKyPhatLuong.add(Box.createHorizontalStrut(50));
        pnKyPhatLuong.add(lblTuNgay);
        pnKyPhatLuong.add(Box.createHorizontalStrut(30));
		pnKyPhatLuong.add(lblDenNgay);
		pnKyPhatLuong.add(Box.createHorizontalStrut(30));
		pnKyPhatLuong.add(btnChoose);
		
		createTable();
		
		pnChucDanh = new JPanel();
		pnThongTin.add(pnChucDanh);
		
		lblTenBangLuong = new JLabel("Tên bảng lương: ");
		txtTenBangLuong = new JTextField(50);
		
		pnChucDanh.add(Box.createVerticalStrut(20));
		pnChucDanh.add(lblTenBangLuong);
		pnChucDanh.add(txtTenBangLuong);
		
		pnTacVu = new JPanel();
		pnContent.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnSave = new JButton("Save");
		btnRenew = new JButton("Renew");
		btnCancel = new JButton("Cancel");
		btnExport = new JButton("Export");
		btnExit = new JButton("Exit");
		
		btnSave.setForeground(Color.WHITE);
		btnRenew.setForeground(Color.WHITE);
		btnCancel.setForeground(Color.WHITE);
		btnExport.setForeground(Color.WHITE);
		btnExit.setForeground(Color.WHITE);
		
		btnSave.setBackground(button);
		btnRenew.setBackground(button);
		btnCancel.setBackground(button);
		btnExport.setBackground(button);
		btnExit.setBackground(button);
		
		pnTacVu.add(btnSave);
		pnTacVu.add(btnRenew);
		pnTacVu.add(btnCancel);
		pnTacVu.add(btnExport);
		pnTacVu.add(btnExit);
		
		
		btnChoose.addActionListener(this);
		btnSave.addActionListener(this);
		btnRenew.addActionListener(this);
		btnCancel.addActionListener(this);
		btnExport.addActionListener(this);
		btnExit.addActionListener(this);
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		pnTable.setBorder(BorderFactory.createTitledBorder("Phòng ban"));
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("");
		model.addColumn("Mã phòng ban");
		model.addColumn("Tên phòng ban");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane.setPreferredSize(new Dimension(600, 200));
		pnTable.add(scrollPane);
		pnThongTin.add(pnTable);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnExit)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }			
		} 
		else if (o.equals(btnSave)) {
			JOptionPane.showMessageDialog(this, "Đã lưu dữ liệu thành công");
		} 
		else if (o.equals(btnChoose)) {
			// Lấy giá trị tháng và năm đã chọn
            String selectedMonth = (String) cbThang.getSelectedItem();
            String selectedYear = (String) cbNam.getSelectedItem();

            // Chuyển đổi giá trị tháng thành số
            int monthValue = monthToNumber(selectedMonth);

            // Tạo lịch và đặt ngày là 1 và năm được chọn
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(selectedYear));
            calendar.set(Calendar.MONTH, monthValue);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            // Lấy ngày đầu tiên của tháng
            Date firstDay = calendar.getTime();

            // Lấy ngày cuối của tháng
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastDay = calendar.getTime();

            // Định dạng ngày thành chuỗi và hiển thị trong JLabel
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            lblTuNgay.setText("Ngày bắt đầu: " + dateFormat.format(firstDay));
            lblDenNgay.setText("Ngày kết thúc: " + dateFormat.format(lastDay));
		}
	}
	
	
	// Hàm chuyển đổi tên tháng thành số tháng (1-12)
    private static int monthToNumber(String month) {
        switch (month) {
            case "Tháng 1": return Calendar.JANUARY;
            case "Tháng 2": return Calendar.FEBRUARY;
            case "Tháng 3": return Calendar.MARCH;
            case "Tháng 4": return Calendar.APRIL;
            case "Tháng 5": return Calendar.MAY;
            case "Tháng 6": return Calendar.JUNE;
            case "Tháng 7": return Calendar.JULY;
            case "Tháng 8": return Calendar.AUGUST;
            case "Tháng 9": return Calendar.SEPTEMBER;
            case "Tháng 10": return Calendar.OCTOBER;
            case "Tháng 11": return Calendar.NOVEMBER;
            case "Tháng 12": return Calendar.DECEMBER;
            default: return -1; // Trường hợp không hợp lệ
        }
    }
	
	
	
	public static void main(String[] args) {
		new GUI_TaoBangLuong().setVisible(true);
	}
}

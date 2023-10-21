package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;


public class GUI_QuanLyLuong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnTitle, pnThongTin, pnLuong,
					pnLoaiBangLuong, pnKyPhatLuong, pnTacVu;
	private JLabel 	lblTitle, lblHeading, lblThang, lblNam,  
					lblTuNgay, lblDenNgay, lblLoaiBangLuong;
	private JComboBox<String> cbLoaiBangLuong, cbThang, cbNam;
	private JButton	btnThem, btnLuu, btnTim, btnLoc, btnChon, 
					btnXoa, btnSua, btnXoaTrang, btnThoat, btnCapNhat;
	private JTable table;
	private DefaultTableModel model;
	
	
	public GUI_QuanLyLuong() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
//		setSize(900, 700);
		setExtendedState(MAXIMIZED_BOTH);
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
		
		lblTitle = new JLabel("Quản lý lương");
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
		pnLuong.add(pnKyPhatLuong);
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
		
		btnChon = new JButton("Chọn");
		btnChon.setForeground(Color.WHITE);
		btnChon.setBackground(button);
		
		pnKyPhatLuong.add(lblThang);
		pnKyPhatLuong.add(cbThang);
		pnKyPhatLuong.add(lblNam);
		pnKyPhatLuong.add(cbNam);
		pnKyPhatLuong.add(Box.createHorizontalStrut(50));
        pnKyPhatLuong.add(lblTuNgay);
        pnKyPhatLuong.add(Box.createHorizontalStrut(30));
		pnKyPhatLuong.add(lblDenNgay);
		pnKyPhatLuong.add(Box.createHorizontalStrut(30));
		pnKyPhatLuong.add(btnChon);
		
		
		createTable();
		
		
		pnTacVu = new JPanel();
		pnContent.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnCapNhat = new JButton("Cập nhật");
		btnThem = new JButton("Thêm");
		btnLuu = new JButton("Lưu");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnThoat = new JButton("Thoát");
		
		btnCapNhat.setForeground(Color.WHITE);
		btnThem.setForeground(Color.WHITE);
		btnLuu.setForeground(Color.WHITE);
		btnSua.setForeground(Color.WHITE);
		btnXoa.setForeground(Color.WHITE);
		btnXoaTrang.setForeground(Color.WHITE);
		btnThoat.setForeground(Color.WHITE);
		
		btnCapNhat.setBackground(button);
		btnThem.setBackground(button);
		btnLuu.setBackground(button);
		btnSua.setBackground(button);
		btnXoa.setBackground(button);
		btnXoaTrang.setBackground(button);
		btnThoat.setBackground(button);
		
		pnTacVu.add(btnCapNhat);
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
	
	
	public void createTable() {
		JPanel pnTable = new JPanel(new GridLayout(1, 4));
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
		
		model.addColumn("Mã NV");
		model.addColumn("Họ tên NV");
		model.addColumn("Giới tính");
		model.addColumn("Chức danh");
		model.addColumn("Lương cứng");
		model.addColumn("Lương phụ cấp");
		model.addColumn("Thưởng");
		model.addColumn("Phạt");
		model.addColumn("Tạm ứng");
		model.addColumn("Thuế TNCN");
		model.addColumn("Lương thực lãnh");
		
		JScrollPane scrollPane = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pnTable.add(scrollPane);
		pnThongTin.add(pnTable);
	}
	
	
	//đăng ký sự kiện cho các button
	public void addActionEvent() {
		btnCapNhat.addActionListener(this);
		btnLoc.addActionListener(this);
		btnChon.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
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
		else if (o.equals(btnLuu)) {
			JOptionPane.showMessageDialog(this, "Đã lưu dữ liệu thành công");
		} 
		else if (o.equals(btnChon)) {
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
		new GUI_QuanLyLuong().setVisible(true);
	}
}

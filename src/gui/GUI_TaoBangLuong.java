package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

public class GUI_TaoBangLuong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth, pnTacVu;
	private JPanel pnBangLuong, pnLuongNhanVien, pnLuongCongNhan;
	private JLabel lblTieuDe;
	private JButton btnXem, btnTaoMoi, btnLuu, btnKhoa;
	private JComboBox cbLoaiBangLuong, cbNam, cbThang;
	
	
	public GUI_TaoBangLuong() {
		setSize(1300, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//load fonts
		try {
			String fileName = "fonts/BeVietnamPro-Black.ttf";
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
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setBackground(headerColor);
		
		lblTieuDe = new JLabel("QUẢN LÝ LƯƠNG");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setBackground(bgColor);
		
		JPanel pnThongTinLuong = new JPanel();
		pnCenter.add(pnThongTinLuong, BorderLayout.NORTH);
		pnThongTinLuong.setBackground(bgColor);
		
		JLabel lblNam = new JLabel("Năm: ");
		cbNam = new JComboBox();
		String[] nam = {"2020", "2021", "2022", "2023", "2024"};
		for (int i = 0; i < nam.length; i++)
			cbNam.addItem(nam[i]);
		
		JLabel lblThang = new JLabel("Tháng: ");
		cbThang = new JComboBox();
		String[] thang = 
			{	"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", 
				"Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", 
				"Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"	};
		for (int i = 0; i < thang.length; i++)
			cbThang.addItem(thang[i]);
		
		JLabel lblLoaiBangLuong = new JLabel("Loại bảng lương: ");
		cbLoaiBangLuong = new JComboBox();
		cbLoaiBangLuong.addItem("---Chọn---");
		cbLoaiBangLuong.addItem("Lương hành chính");
		cbLoaiBangLuong.addItem("Lương sản phẩm");
		
		JLabel lblTenBangLuong = new JLabel("Tên bảng lương: ");
		JTextField txtTenBangLuong = new JTextField();
		btnXem = new JButton("Xem");
		btnTaoMoi = new JButton("Tạo mới");
		
		
		lblNam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbNam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblThang.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbThang.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblLoaiBangLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		cbLoaiBangLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		lblTenBangLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		txtTenBangLuong.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		btnXem.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnXem.setBackground(buttonColor);
		btnXem.setForeground(Color.WHITE);
		
		btnTaoMoi.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnTaoMoi.setBackground(buttonColor);
		btnTaoMoi.setForeground(Color.WHITE);
		
		
		pnThongTinLuong.add(lblNam);
		pnThongTinLuong.add(cbNam);
		pnThongTinLuong.add(lblThang);
		pnThongTinLuong.add(cbThang);
		pnThongTinLuong.add(lblLoaiBangLuong);
		pnThongTinLuong.add(cbLoaiBangLuong);
		pnThongTinLuong.add(lblTenBangLuong);
		pnThongTinLuong.add(txtTenBangLuong);
		pnThongTinLuong.add(btnXem);
		pnThongTinLuong.add(btnTaoMoi);
		
		//bảng lương chứa lương nhân viên và lương công nhân
		pnBangLuong = new JPanel();
		pnCenter.add(pnBangLuong, BorderLayout.CENTER);
		pnBangLuong.setBackground(bgColor);
		
		//lương nhân viên
		pnLuongNhanVien = new JPanel();
		pnBangLuong.add(pnLuongNhanVien);
		pnLuongNhanVien.setBackground(bgColor);
		
		DefaultTableModel modelNV = new DefaultTableModel();
		JTable tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("STT");
		modelNV.addColumn("Họ tên");
		modelNV.addColumn("Giới tính");
		modelNV.addColumn("Ngày sinh");
		modelNV.addColumn("Địa chỉ");
		modelNV.addColumn("CCCD");
		modelNV.addColumn("BHXH");
		modelNV.addColumn("MST");
		modelNV.addColumn("Ngày vào");
		modelNV.addColumn("Phòng ban");
		modelNV.addColumn("Chức danh");
		modelNV.addColumn("Trạng thái");
		modelNV.addColumn("Lương CB");
		modelNV.addColumn("Phụ cấp");
		modelNV.addColumn("Giảm trừ");
		modelNV.addColumn("Tạm ứng");
		
		JScrollPane spNV = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spNV.setPreferredSize(new Dimension(1200, 400));
		pnLuongNhanVien.add(spNV);
		
		//lương công nhân
		pnLuongCongNhan = new JPanel();
		pnBangLuong.add(pnLuongCongNhan);
		pnLuongCongNhan.setBackground(bgColor);
		
		DefaultTableModel modelCN = new DefaultTableModel();
		JTable table = new JTable(modelCN);
		table.setRowHeight(25);
		
		modelCN.addColumn("STT");
		modelCN.addColumn("Họ tên");
		modelCN.addColumn("Giới tính");
		modelCN.addColumn("Ngày sinh");
		modelCN.addColumn("Địa chỉ");
		modelCN.addColumn("CCCD");
		modelCN.addColumn("BHXH");
		modelCN.addColumn("MST");
		modelCN.addColumn("Ngày vào");
		modelCN.addColumn("Tay nghề");
		modelCN.addColumn("Tổ sản xuất");
		modelCN.addColumn("Trạng thái");
		modelCN.addColumn("Lương SP");
		modelCN.addColumn("Phụ cấp");
		modelCN.addColumn("Giảm trừ");
		modelCN.addColumn("Tạm ứng");
		
		JScrollPane spCN = new 	JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spCN.setPreferredSize(new Dimension(1200, 400));
		pnLuongCongNhan.add(spCN);

		
		//pnTacVu chứa 2 button lưu và khóa
		pnTacVu = new JPanel();
		pnCenter.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBackground(bgColor);
		
		btnLuu = new JButton("Lưu");
		btnKhoa = new JButton("Khóa");
		
		btnLuu.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		
		btnKhoa.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		btnKhoa.setBackground(buttonColor);
		btnKhoa.setForeground(Color.WHITE);
		
		pnTacVu.add(btnLuu);
		pnTacVu.add(btnKhoa);
		
		//ẩn các panel
		pnLuongNhanVien.setVisible(false);
		pnLuongCongNhan.setVisible(false);
		pnTacVu.setVisible(false);
		
		Container container = getContentPane();
		container.add(pnContent);
		
		
		//đăng ký sự kiện
		cbLoaiBangLuong.addActionListener(this);
		txtTenBangLuong.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(cbLoaiBangLuong)) {
			String str = (String) cbLoaiBangLuong.getSelectedItem();
			if (str.equalsIgnoreCase("Lương hành chính")) {
				pnLuongNhanVien.setVisible(true);
				pnLuongCongNhan.setVisible(false);
				pnTacVu.setVisible(true);
			} else if (str.equalsIgnoreCase("Lương sản phẩm")) {
				pnLuongCongNhan.setVisible(true);
				pnLuongNhanVien.setVisible(false);
				pnTacVu.setVisible(true);
			} else {
				pnLuongNhanVien.setVisible(false);
				pnLuongCongNhan.setVisible(false);
				pnTacVu.setVisible(false);
			}
		}
		else if (o.equals(o)) {
			
		}
	}
	
	
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_TaoBangLuong().setVisible(true);
	}
}

package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.FlatLightLaf;


public class GUI_TinhLuong extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter;
	private JPanel pnTable, pnTableNV, pnTableCN;
	private JLabel lblTieuDe, lblNam, lblThang, lblLoaiBangLuong, lblError;
	private JButton btnLoc, btnXoa, btnLuu;
	private JComboBox cbNam, cbThang, cbLoaiBangLuong;
	private Font BVNPro;
	private JTable tableNV, tableCN;
	private DefaultTableModel modelNV, modelCN;
	

	public GUI_TinhLuong() {
		//set JFrame properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
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
		
		lblTieuDe = new JLabel("TÍNH LƯƠNG");
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
		
		lblLoaiBangLuong = new JLabel("Loại bảng lương: ");
		lblLoaiBangLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbLoaiBangLuong = new JComboBox();
		cbLoaiBangLuong.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		cbLoaiBangLuong.addItem("---Chọn---");
		cbLoaiBangLuong.addItem("Lương hành chính");
		cbLoaiBangLuong.addItem("Lương sản phẩm");
		
		btnLoc = new JButton("Lọc");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		
		btnLoc.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnLoc.setBackground(buttonColor);
		btnLoc.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnXoa.setBackground(buttonColor);
		btnXoa.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Be Vietnam Pro Regular", Font.PLAIN, 15));
		btnLuu.setBackground(buttonColor);
		btnLuu.setForeground(Color.WHITE);
		
		b1.add(lblNam);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbNam);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lblThang);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbThang);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lblLoaiBangLuong);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbLoaiBangLuong);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(btnLoc);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnXoa);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnLuu);
		
		b.add(b1);
		pnThongTin.add(b);
		
		pnTable = new JPanel();
		pnTable.setBackground(bgColor);
		pnCenter.add(pnTable, BorderLayout.CENTER);
		
		//tạo bảng chứa thông tin của NV
		createTableNV();
		
		
		
		//tạo bảng chứa thông tin của CN
		createTableCN();
		
		
		pnTableNV.setVisible(false);
		pnTableCN.setVisible(false);
		
		Container container = getContentPane();
		container.add(pnContent);
		
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}
	
	
	//tạo bảng chứa thông tin của NV
	public void createTableNV() {
		pnTableNV = new JPanel();
		pnTable.add(pnTableNV);
		
		modelNV = new DefaultTableModel();
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(25);
		
		modelNV.addColumn("STT");
		modelNV.addColumn("Mã NV");
		modelNV.addColumn("Họ tên");
		modelNV.addColumn("CCCD");
		modelNV.addColumn("Phòng ban");
		modelNV.addColumn("Chức danh");
		modelNV.addColumn("Lương cơ bản");
		modelNV.addColumn("Lương phụ cấp");
		modelNV.addColumn("Giảm trừ");
		modelNV.addColumn("Tạm ứng");
		modelNV.addColumn("Thực lãnh");
		
		JScrollPane scrollPaneNV = new 	JScrollPane(tableNV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneNV.setPreferredSize(new Dimension(800, 350));
		pnTableNV.add(scrollPaneNV);
	}
	
	
	//tạo bảng chứa thông tin của CN
	public void createTableCN() {
		pnTableCN = new JPanel();
		pnTable.add(pnTableCN);
		
		modelCN = new DefaultTableModel();
		tableCN = new JTable(modelCN);
		tableCN.setRowHeight(25);
		
		modelCN.addColumn("STT");
		modelCN.addColumn("Mã CN");
		modelCN.addColumn("Họ tên");
		modelCN.addColumn("Lương sản phẩm");
		modelCN.addColumn("Lương phụ cấp");
		modelCN.addColumn("Giảm trừ");
		modelCN.addColumn("Tạm ứng");
		modelCN.addColumn("Thực lãnh");
		
		JScrollPane scrollPaneCN = new 	JScrollPane(tableCN, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCN.setPreferredSize(new Dimension(800, 350));
		pnTableCN.add(scrollPaneCN);
	}
	
	
	//ActionEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		
		if (o.equals(btnLoc)) {
			String str = (String) cbLoaiBangLuong.getSelectedItem();
			if (str.contains("Lương hành chính")) {
				pnTableNV.setVisible(true);
				pnTableCN.setVisible(false);
			} else if (str.contains("Lương sản phẩm")) {
				pnTableCN.setVisible(true);
				pnTableNV.setVisible(false);
			} else {
				pnTableNV.setVisible(false);
				pnTableCN.setVisible(false);
			}
		}
	}
	
	
	//MouseEvent
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
	
	
	//hàm main
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new GUI_TinhLuong().setVisible(true);
	}
}

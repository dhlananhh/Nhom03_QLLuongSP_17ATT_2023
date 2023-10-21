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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;


public class GUI_TinhLuong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnTieuDe, pnThongTin, pnTacVu;
	private JLabel lblHeader, lblTenBangLuong, lblThang, lblNam;
	private JTextField txtTenBangLuong;
	private JComboBox cbThang, cbNam;
	private ArrayList<String> listThang, listNam;
	private JButton btnXoa, btnIn;
	private Font BVNPro;
	
	
	public GUI_TinhLuong() {
		buildGUI();		
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
//		setSize(1200, 500);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		//load fonts
		try {
			String fileName = "src/main/java/fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		
		//set color
		Color title = new Color(0, 102, 204);
		Color button = new Color(0, 153, 204);
		
		
		//pnContent
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		//pnTieuDe
		pnTieuDe = new JPanel();
		pnContent.add(pnTieuDe, BorderLayout.NORTH);
		
		lblHeader = new JLabel("TÍNH LƯƠNG");
		pnTieuDe.add(lblHeader);
		lblHeader.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 20));
		
		//pnThongTin
		pnThongTin = new JPanel();
		pnContent.add(pnThongTin, BorderLayout.CENTER);
//		pnThongTin.setLayout(new BorderLayout());
		
//		Box b = Box.createVerticalBox();
//		Box b1 = Box.createHorizontalBox();
//		Box b2 = Box.createHorizontalBox();
//		Box b3 = Box.createHorizontalBox();
		
		lblThang = new JLabel("Tháng: ");
		lblNam = new JLabel("Năm: ");
		
		lblThang.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		lblNam.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		
		listThang = new ArrayList<>();
		listThang.add("Tháng 1");
		listThang.add("Tháng 2");
		listThang.add("Tháng 3");
		listThang.add("Tháng 4");
		listThang.add("Tháng 5");
		listThang.add("Tháng 6");
		listThang.add("Tháng 7");
		listThang.add("Tháng 8");
		listThang.add("Tháng 9");
		listThang.add("Tháng 10");
		listThang.add("Tháng 11");
		listThang.add("Tháng 12");
		
		cbThang = new JComboBox();
		for (String item : listThang)
			cbThang.addItem(item);
		cbThang.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		listNam = new ArrayList<>();
		cbNam = new JComboBox();
		for (int year = 2000; year <= 2023; year++)
			cbNam.addItem(String.valueOf(year));
		cbNam.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 15));
		
		
		lblTenBangLuong = new JLabel("Tên bảng lương: ");
		txtTenBangLuong = new JTextField(30);
		
		lblTenBangLuong.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 15));
		
		/*
		//thêm vào box
		b1.add(lblThang);		
		b1.add(cbThang);
		b2.add(lblNam);
		b2.add(cbNam);
		b3.add(lblTenBangLuong);
		b3.add(txtTenBangLuong);
		
		//căn chỉnh label
		lblThang.setPreferredSize(lblTenBangLuong.getPreferredSize());
		lblNam.setPreferredSize(lblTenBangLuong.getPreferredSize());
		
		//thêm các box con (b1,b2...) vào box lớn b
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		
		//thêm box b vào pnCenter
		pnThongTin.add(b, BorderLayout.NORTH);
		*/
				
		
		pnThongTin.add(lblThang);
		pnThongTin.add(cbThang);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(lblNam);
		pnThongTin.add(cbNam);
		pnThongTin.add(Box.createHorizontalStrut(30));
		pnThongTin.add(lblTenBangLuong);
		pnThongTin.add(txtTenBangLuong);
		
		//pnTacVu
		pnTacVu = new JPanel();
		pnThongTin.add(Box.createHorizontalStrut(50));
		pnThongTin.add(pnTacVu);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnXoa = new JButton("Xóa");
		btnIn = new JButton("In");
		pnTacVu.add(btnXoa);
		pnTacVu.add(btnIn);
		
		btnXoa.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 13));
		btnIn.setFont(new Font("BeVietnamPro-Black", Font.PLAIN, 13));
		
		btnXoa.setBackground(button);
		btnXoa.setForeground(Color.WHITE);
		btnIn.setBackground(button);
		btnIn.setForeground(Color.WHITE);
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		FlatCyanLightIJTheme.setup();
		
		new GUI_TinhLuong().setVisible(true);
	}
}

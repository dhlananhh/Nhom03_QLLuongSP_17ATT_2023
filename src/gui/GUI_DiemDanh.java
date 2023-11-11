package gui;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JTextField;

public class GUI_DiemDanh extends JFrame {

	private JPanel contentPane;
	private JComboBox<Integer> cbThang, cbNam, cbMaNV;
	private JTextField txtNghiPhep;
	private JTextField txtTenNV;
	private DefaultTableModel modelDiemDanh;
	private JTable tableDiemDanh;
	private int month;
	List<String> columnsDD = new ArrayList<>();
	private JTextField txtKhongPhep;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_DiemDanh frame = new GUI_DiemDanh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_DiemDanh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth = new JPanel();
		contentPane.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.X_AXIS));
		
		pnNorth.add(Box.createHorizontalStrut(50));
		
		JLabel lblNam = new JLabel("Năm");
		pnNorth.add(lblNam);
		
		cbNam = new JComboBox();
		pnNorth.add(cbNam);
		
		JLabel lblThang = new JLabel("Tháng");
		pnNorth.add(lblThang);
		
		cbThang = new JComboBox();
		pnNorth.add(cbThang);
		for(int i=1; i<=12; i++) 
			cbThang.addItem(i);
			
		Component horizontalStrut = Box.createHorizontalStrut(1000);
		pnNorth.add(horizontalStrut);
			
		JPanel pnCenter = new JPanel();
		contentPane.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.X_AXIS));
		pnCenter.add(Box.createVerticalStrut(50));
		
		Box b1 = Box.createVerticalBox();
		pnCenter.add(b1);
		b1.add(Box.createVerticalStrut(30));
		
		Box b3 = Box.createHorizontalBox();
		b3.add(Box.createHorizontalStrut(300));
		JLabel lblMaNV = new JLabel("Mã nhân viên:   ");
		b3.add(lblMaNV);
		
		cbMaNV = new JComboBox();
		b3.add(cbMaNV);
		cbMaNV.setPreferredSize(new Dimension(200, 5));
		b1.add(b3);
		b1.add(Box.createVerticalStrut(30));

		pnCenter.add(Box.createVerticalStrut(20));
		Box b2 = Box.createVerticalBox();
		pnCenter.add(b2);
		b2.add(Box.createVerticalStrut(30));
		Box b4 = Box.createHorizontalBox();
		b4.add(Box.createHorizontalStrut(300));
		JLabel lblNghiPhep = new JLabel("Số ngày nghỉ có phép:         ");
		b4.add(lblNghiPhep);
		txtNghiPhep = new JTextField();
		txtNghiPhep.setText("");
		b4.add(txtNghiPhep);
		txtNghiPhep.setColumns(10);
		b4.add(Box.createHorizontalStrut(200));
		
		b2.add(b4);
		b2.add(Box.createVerticalStrut(30));
		Box b5= Box.createHorizontalBox();
		b5.add(Box.createHorizontalStrut(300));
		JLabel lblTenNV = new JLabel("Tên nhân viên:  ");
		b5.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setText("");
		b5.add(txtTenNV);
		txtTenNV.setColumns(10);
		
		b1.add(b5);
		b1.add(Box.createVerticalStrut(70));
		Box b6 = Box.createHorizontalBox();
		b2.add(b6);
		b2.add(Box.createVerticalStrut(60));
		b6.add(Box.createHorizontalStrut(300));
		JLabel lblKhongPhep = new JLabel("Số ngày nghỉ không phép:   ");
		b6.add(lblKhongPhep);
		txtKhongPhep = new JTextField();
		b6.add(txtKhongPhep);
		b6.add(Box.createHorizontalStrut(200));
		
		JPanel pnTable = new JPanel(new BorderLayout());
		modelDiemDanh = new DefaultTableModel(columnsDD.toArray(), 0);
		tableDiemDanh = new JTable(modelDiemDanh);
		taoCotTheoThang();
		JScrollPane spTableDD = new JScrollPane(tableDiemDanh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spTableDD.setPreferredSize(new Dimension(1200,400));
		contentPane.add(pnTable, BorderLayout.SOUTH);
		tableDiemDanh.setFillsViewportHeight(true);
		pnTable.add(spTableDD, BorderLayout.CENTER);
		
		cbThang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				taoCotTheoThang();
			}
		});
	}
	private void taoCotTheoThang() {
		columnsDD.removeAll(columnsDD);
		columnsDD.add("Mã nhân viên");
		columnsDD.add("Họ tên");
		for(int i=1; i<=30; i++)
			columnsDD.add(i+"/"+cbThang.getSelectedItem());
		modelDiemDanh.setColumnIdentifiers(columnsDD.toArray());
	}
}

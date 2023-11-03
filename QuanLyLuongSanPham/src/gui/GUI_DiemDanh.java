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
	private JTextField txtKhongPhep;
	private DefaultTableModel modelDiemDanh;
	private JTable tableDiemDanh;
	private int month;
	List<String> columnsDD = new ArrayList<>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(Box.createVerticalStrut(50));
		
		Box b1 = Box.createHorizontalBox();
		pnCenter.add(b1);
		
		b1.add(Box.createHorizontalStrut(300));
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		b1.add(lblMaNV);
		
		cbMaNV = new JComboBox();
		b1.add(cbMaNV);
		cbMaNV.setPreferredSize(new Dimension(200, 5));
		
		b1.add(Box.createHorizontalStrut(300));
		JLabel lblNghiPhep = new JLabel("Số ngày nghỉ có phép:");
		b1.add(lblNghiPhep);
		txtNghiPhep = new JTextField();
		txtNghiPhep.setText("");
		b1.add(txtNghiPhep);
		txtNghiPhep.setColumns(10);
		b1.add(Box.createHorizontalStrut(200));

		pnCenter.add(Box.createVerticalStrut(20));
		Box b2 = Box.createHorizontalBox();
		pnCenter.add(b2);
		
		b2.add(Box.createHorizontalStrut(300));
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		b2.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setText("");
		b2.add(txtTenNV);
		txtTenNV.setColumns(10);
		b2.add(Box.createHorizontalStrut(200));
		JLabel lblKhongPhep = new JLabel("Số ngày nghỉ không phép:");
		b2.add(lblKhongPhep);
		
		txtKhongPhep = new JTextField();
		txtKhongPhep.setText("");
		b2.add(txtKhongPhep);
		txtKhongPhep.setPreferredSize(txtNghiPhep.getPreferredSize());
		b2.add(Box.createHorizontalStrut(200));
		
		JPanel pnTable = new JPanel();
		modelDiemDanh = new DefaultTableModel(columnsDD.toArray(), 0);
		tableDiemDanh = new JTable(modelDiemDanh);
		taoCotTheoThang();
		
		JScrollPane spTableDD = new JScrollPane(tableDiemDanh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spTableDD.setPreferredSize(new Dimension(1200,400));
		contentPane.add(pnTable, BorderLayout.SOUTH);
		tableDiemDanh.setFillsViewportHeight(true);
		pnTable.add(spTableDD);
		
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

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.ChamCong_dao;
import dao.CongDoan_dao;
import dao.SanPham_dao;
import entity.ChamCong;
import entity.CongDoan;
import entity.SanPham;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Component;
import javax.swing.table.TableModel;

import org.w3c.dom.CDATASection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GUI_PhanCong extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JDateChooser chooserNgay = new JDateChooser();
	private JTextField txtMaCN, txtTenCN, txtTenSP, txtTenCD;
	private JComboBox<String> cbMaSP, cbMaCD;
	private JSpinner spinChiTieu;
	private JButton btnLuu, btnXoa;
	private DefaultTableModel modelCongNhan, modelPhanCong;
	private JTable tableCongNhan, tablePhanCong;
	private ChamCong_dao chamCong_dao;
	private SanPham_dao sanPham_dao = new SanPham_dao();
	private CongDoan_dao congDoan_dao = new CongDoan_dao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PhanCong frame = new GUI_PhanCong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public GUI_PhanCong() throws SQLException {
		ConnectDB.getInstance().connect();;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1600, 900);
		setSize(1300, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnCongNhan = new JPanel();
		contentPane.add(pnCongNhan, BorderLayout.WEST);
		pnCongNhan.setLayout(new BoxLayout(pnCongNhan, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		pnCongNhan.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("Ngày");
		horizontalBox.add(lblNewLabel);
		
		chooserNgay.getCalendarButton().setToolTipText("Chọn ngày phân công");
		chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
		chooserNgay.setFont(new Font("Arial", Font.PLAIN, 16));
		chooserNgay.setDateFormatString("dd/MM/yyyy");
		chooserNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
		horizontalBox.add(chooserNgay);
		horizontalBox.add(Box.createHorizontalStrut(200));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnCongNhan.add(verticalStrut);
		
		
		
		String[] columnsCN = { "Mã công nhân", "Tên công nhân", "Tổ sản xuất" };
		modelCongNhan = new DefaultTableModel(columnsCN, 0);
		tableCongNhan = new JTable(modelCongNhan);
		
		JScrollPane spTableCN = new JScrollPane(tableCongNhan);
		spTableCN.setPreferredSize(new Dimension(500, 250));
		pnCongNhan.add(spTableCN);
		spTableCN.setViewportView(tableCongNhan);
		pnCongNhan.add(Box.createVerticalStrut(50));
		
		JPanel pnInput = new JPanel();
		contentPane.add(pnInput, BorderLayout.CENTER);
		pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
		
		Box b1 = Box.createHorizontalBox();
		pnInput.add(Box.createVerticalStrut(20));
		pnInput.add(b1);
		pnInput.add(Box.createVerticalStrut(20));
		b1.add(Box.createHorizontalStrut(30));
		JLabel lblMaCN = new JLabel("Mã công nhân:");
		b1.add(lblMaCN);
		b1.add(Box.createHorizontalStrut(30));
		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		b1.add(txtMaCN);
		b1.add(Box.createHorizontalStrut(30));
		JLabel lblTenCN = new JLabel("Tên công nhân:");
		b1.add(lblTenCN);
		b1.add(Box.createHorizontalStrut(30));
		txtTenCN = new JTextField();
		txtTenCN.setEditable(false);
		b1.add(txtTenCN);
		txtTenCN.setColumns(10);
		
		Box b2 = Box.createHorizontalBox();
		pnInput.add(b2);
		pnInput.add(Box.createVerticalStrut(20));
		b2.add(Box.createHorizontalStrut(30));
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		b2.add(lblMaSP);
		b2.add(Box.createHorizontalStrut(30));

		cbMaSP = new JComboBox<>();
		for (SanPham sp : sanPham_dao.getalltbSanPham()) {
			cbMaSP.addItem(sp.getMaSP());
		}
		cbMaSP.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				cbMaCD.removeAllItems();
				for (CongDoan cd : congDoan_dao.getalltbCongDoan()) {
					cbMaCD.addItem(cd.getMaCD());
				}
			}
		});
		b2.add(cbMaSP);
		cbMaSP.setPreferredSize(new Dimension(200, 10));
		b2.add(Box.createHorizontalStrut(30));

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		b2.add(lblTenSP);
		b2.add(Box.createHorizontalStrut(30));

		txtTenSP = new JTextField();
		txtTenSP.setText("");
		b2.add(txtTenSP);
		txtTenSP.setColumns(10);
		
		Box b3 = Box.createHorizontalBox();
		pnInput.add(b3);
		pnInput.add(Box.createVerticalStrut(20));
		b3.add(Box.createHorizontalStrut(30));

		JLabel lblMaCD = new JLabel("Mã công đoạn");
		b3.add(lblMaCD);
		b3.add(Box.createHorizontalStrut(30));
		cbMaCD = new JComboBox<String>();
		cbMaCD.setPreferredSize(new Dimension(200, 10));
		b3.add(cbMaCD);
		b3.add(Box.createHorizontalStrut(30));
		JLabel lblTenCD = new JLabel("Tên công đoạn");
		b3.add(lblTenCD);
		b3.add(Box.createHorizontalStrut(30));
		txtTenCD = new JTextField();
		b3.add(txtTenCD);
		txtTenCD.setColumns(10);
		
		Box b4 = Box.createHorizontalBox();
		pnInput.add(b4);
		pnInput.add(Box.createVerticalStrut(50));
		b4.add(Box.createHorizontalStrut(30));
		JLabel lblChiTieu = new JLabel("Chỉ tiêu");
		b4.add(lblChiTieu);
		b4.add(Box.createHorizontalStrut(30));
		SpinnerModel modelChiTieu = new SpinnerNumberModel(1, 1, 25, 1);
		spinChiTieu = new JSpinner(modelChiTieu);
		b4.add(spinChiTieu);
		b4.add(Box.createHorizontalStrut(800));
		
		Box b5 = Box.createHorizontalBox();
		pnInput.add(b5);
		
		btnLuu = new JButton("Lưu");
		b5.add(btnLuu);
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		b5.add(horizontalStrut);
		
		btnXoa = new JButton("Xóa");
		b5.add(btnXoa);
		pnInput.add(Box.createVerticalStrut(100));
		
		contentPane.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		
		String[] columnsPC = { "Mã công nhân", "Tên công nhân", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Chỉ tiêu" };
		modelPhanCong = new DefaultTableModel(columnsPC, 0);
		tablePhanCong = new JTable(modelPhanCong);
		
		JScrollPane spTablePC = new JScrollPane(tablePhanCong);
		spTablePC.setPreferredSize(new Dimension(1500, 300));
		contentPane.add(spTablePC, BorderLayout.SOUTH);
		spTablePC.setViewportView(tablePhanCong);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			
		}
	}
	
	public void themPhanCong() {
		String maCN = txtMaCN.getText();
		String tenCN = txtTenCN.getText();
		String maSP = cbMaSP.getSelectedItem().toString();
		String tenSP = txtTenSP.getText();
		String maCD = cbMaCD.getSelectedItem().toString();
		String tenCD = txtTenCD.getText();
		String chiTieu = spinChiTieu.getValue().toString();
		ChamCong phanCong = new ChamCong(maCN, maSP, new java.sql.Date(chooserNgay.getDate().getTime()) , Integer.parseInt(chiTieu));
		String object[] = {maCN, tenCN, maSP, tenSP, maCD, tenCD, chiTieu};
		modelPhanCong.addRow(object);
	}
}

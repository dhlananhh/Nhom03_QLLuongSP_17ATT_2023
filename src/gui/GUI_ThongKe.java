package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.toedter.calendar.JDateChooser;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;

import charts.ChartPanelTongCongDoan;
import charts.ChartPanelTongLuong;
import charts.PanelDanhSachCongNhan;
import charts.PanelDanhSachNhanVien;
import charts.PanelDanhSachSanPham;
import connection.ConnectDB;
//import controller.ChuyenManHinh;
//import dao.TongSoHoaDon_DAO;
//import entity.DanhMuc;
import dao.DAO_TongLuongCongNhan;
import dao.DAO_TongLuongNhanVien;
import dao.DAO_TongSanPham;
import entity.TongCongDoan;
import entity.TongLuong;
import entity.TongLuongCongNhan;
import entity.TongLuongNhanVien;
import entity.TongSanPham;

//import entity.TongSoHoaDon;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JMonthChooser;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Canvas;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI_ThongKe extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelTong;
	private JButton btnThongKe;
	private JComboBox cboNam;
	private JComboBox cboThang;
	private JLabel lblTongSanPham;
	private JPanel panelTongHoaDon;
	private JLabel lblTongHoaDon;
	private JPanel panelTongSanPham;
	private JPanel panelDanhSach;
	private Locale vn = new Locale("vi", "VN");
	private JPanel panelBieuDo;
	private JPanel panelLoiNhuanTheoNgay;
	private JLabel lblLoiNhuanTheoNgay;
	private JPanel panelTongThuNhapTheoNgay;
	private JLabel lblTongThuNhapTheoNhay;
	private JLabel lblBieuDo;
	private JLabel lblDanhSach;
	private JPanel panelGiua;
	private JButton btnLuongNhanVien;
	private JButton btnLuongCongNhan;
	private JButton btnSoLuongSanPham;
	private JButton btnLoiNhuanTheoNgay;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLightLaf.setup();
					GUI_ThongKe frame = new GUI_ThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI_ThongKe() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1300, 700);
		setLocationRelativeTo(null);
		panelTong = new JPanel();
		panelTong.setBackground(Color.WHITE);
		panelTong.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelTong);
		panelTong.setLayout(null);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		JPanel panelTren = new JPanel();
		panelTren.setLayout(null);
		panelTren.setBackground(new Color(0, 153, 255));
		panelTren.setBounds(0, 0, 1283, 52);
		panelTong.add(panelTren);

		JLabel lblTieuDe = new JLabel("THỐNG KÊ LƯƠNG CÔNG TY");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.WHITE);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));
		lblTieuDe.setBounds(402, 6, 478, 40);
		panelTren.add(lblTieuDe);

		panelGiua = new JPanel();
		panelGiua.setForeground(new Color(0, 0, 0));
		panelGiua.setBackground(new Color(255, 240, 245));
		panelGiua.setBounds(0, 64, 1283, 601);
		panelTong.add(panelGiua);
		panelGiua.setLayout(null);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBackground(Color.WHITE);
		btnThongKe.setFont(new Font("Arial", Font.PLAIN, 12));
		btnThongKe.setBounds(542, 22, 115, 25);
		panelGiua.add(btnThongKe);

		cboThang = new JComboBox<>();
		cboThang.setBounds(77, 22, 146, 25);
		for (int i = 1; i <= 12; i++) {
			cboThang.addItem(i);
		}

		JLabel lblcboNam = new JLabel("Năm:");
		lblcboNam.setFont(new Font("Arial", Font.PLAIN, 14));
		lblcboNam.setBounds(271, 24, 76, 23);
		panelGiua.add(lblcboNam);

		cboNam = new JComboBox<>();
		cboNam.setBounds(345, 22, 146, 25);
		for (int i = Year.now().getValue() - 3; i <= Year.now().getValue(); i++) {
			cboNam.addItem(i);
		}
		panelGiua.add(cboNam);

		panelDanhSach = new JPanel();
		panelDanhSach.setBackground(Color.WHITE);
		panelDanhSach.setBorder(new LineBorder(Color.ORANGE, 2, true));
		panelDanhSach.setBounds(678, 11, 595, 579);
		panelGiua.add(panelDanhSach);
		panelDanhSach.setLayout(null);

		lblDanhSach = new JLabel("Thông tin chi tiết");
		lblDanhSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSach.setFont(new Font("Arial", Font.ITALIC, 12));
		lblDanhSach.setBounds(104, 273, 387, 32);
		panelDanhSach.add(lblDanhSach);

		panelBieuDo = new JPanel();
		panelBieuDo.setBorder(new LineBorder(Color.ORANGE, 2, true));
		panelBieuDo.setBackground(Color.WHITE);
		panelBieuDo.setBounds(10, 172, 647, 418);
		panelGiua.add(panelBieuDo);
		panelBieuDo.setLayout(null);

		lblBieuDo = new JLabel("Biểu đồ thống kê");
		lblBieuDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBieuDo.setFont(new Font("Arial", Font.ITALIC, 12));
		lblBieuDo.setBounds(257, 202, 132, 14);
		panelBieuDo.add(lblBieuDo);

		btnLuongNhanVien = new JButton("Lương nhân viên");
		btnLuongNhanVien.setBackground(new Color(220, 220, 220));
		btnLuongNhanVien.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnLuongNhanVien.setBounds(10, 77, 146, 66);
		panelGiua.add(btnLuongNhanVien);
		btnLuongNhanVien.setLayout(null);

		btnLuongCongNhan = new JButton("Lương công nhân");
		btnLuongCongNhan.setBackground(new Color(220, 220, 220));
		btnLuongCongNhan.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnLuongCongNhan.setBounds(175, 77, 146, 66);
		panelGiua.add(btnLuongCongNhan);
		btnLuongCongNhan.setLayout(null);

		btnSoLuongSanPham = new JButton("Số lượng sản phẩm");
		btnSoLuongSanPham.setBackground(new Color(220, 220, 220));
		btnSoLuongSanPham.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnSoLuongSanPham.setBounds(345, 77, 146, 66);
		panelGiua.add(btnSoLuongSanPham);
		btnSoLuongSanPham.setLayout(null);

		btnLoiNhuanTheoNgay = new JButton("test 123 test");
		btnLoiNhuanTheoNgay.setBackground(new Color(220, 220, 220));
		btnLoiNhuanTheoNgay.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnLoiNhuanTheoNgay.setBounds(511, 77, 146, 66);
		panelGiua.add(btnLoiNhuanTheoNgay);
		btnLoiNhuanTheoNgay.setLayout(null);

		// Dang ky su kien cho button Thong ke
		btnThongKe.addActionListener(this);
		btnLoiNhuanTheoNgay.addActionListener(this);
		btnLuongNhanVien.addActionListener(this);
		btnLuongCongNhan.addActionListener(this);
		btnSoLuongSanPham.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			thayDoiMauCacPanelButtonKhiNgayHopLe();
			
		}
		if(o.equals(btnLuongCongNhan)){
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongLuongCongNhan dao_luongCN = new DAO_TongLuongCongNhan();
			ArrayList<TongLuong> tongLuongList = dao_luongCN.getTongLuongCongNhan(nam);
			ArrayList<TongLuongCongNhan> tongCN = dao_luongCN.getDanhSachTopCongNhan(nam);
			ChartPanelTongLuong panelBieuDoNew = new ChartPanelTongLuong(tongLuongList);
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        
	        PanelDanhSachCongNhan panelDSCN = new PanelDanhSachCongNhan(tongCN);
	        panelGiua.remove(panelDanhSach);
	        panelDanhSach = panelDSCN;
	        panelDanhSach.setBounds(678, 11, 595, 579);
	        panelGiua.add(panelDanhSach);

	        
	        revalidate();
	        repaint();
		}
		if(o.equals(btnLuongNhanVien)){
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongLuongNhanVien dao_luongNV = new DAO_TongLuongNhanVien();
			ArrayList<TongLuong> tongLuongList = dao_luongNV.getTongLuongCongTy(nam);
			ArrayList<TongLuongNhanVien> tongNV = dao_luongNV.getTongLuongNhanVien(nam);
			ChartPanelTongLuong panelBieuDoNew = new ChartPanelTongLuong(tongLuongList);
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        
	        PanelDanhSachNhanVien panelDSNV = new PanelDanhSachNhanVien(tongNV);
	        panelGiua.remove(panelDanhSach);
	        panelDanhSach = panelDSNV;
	        panelDanhSach.setBounds(678, 11, 595, 579);
	        panelGiua.add(panelDanhSach);

	        
	        revalidate();
	        repaint();
		}
		if(o.equals(btnSoLuongSanPham)){
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongSanPham dao_tongSP = new DAO_TongSanPham();
			ArrayList<TongCongDoan> tongCD = dao_tongSP.getSoLuongSanPhamHoanThanh(nam);
			ChartPanelTongCongDoan panelBieuDoNew = new ChartPanelTongCongDoan(tongCD);
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        
			ArrayList<TongSanPham> tongSP = dao_tongSP.getTopSanPham(nam);
			System.out.println(tongSP);
	        PanelDanhSachSanPham panelSP = new PanelDanhSachSanPham(tongSP);
	        panelGiua.remove(panelDanhSach);
	        panelDanhSach = panelSP;
	        panelDanhSach.setBounds(678, 11, 595, 579);
	        panelGiua.add(panelDanhSach);
	 
	        
	        revalidate();
	        repaint();
			
		}
		

	}

	public void thayDoiMauCacPanelButtonKhiNgayHopLe() {
		btnLuongNhanVien.setBackground(new Color(116, 214, 128));
		btnLuongNhanVien.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnLuongNhanVien.setForeground(Color.BLACK);

		btnLuongCongNhan.setBackground(new Color(116, 214, 128));
		btnLuongCongNhan.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnLuongCongNhan.setForeground(Color.BLACK);

		btnSoLuongSanPham.setBackground(new Color(116, 214, 128));
		btnSoLuongSanPham.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnSoLuongSanPham.setForeground(Color.BLACK);

		btnLoiNhuanTheoNgay.setBackground(new Color(116, 214, 128));
		btnLoiNhuanTheoNgay.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnLoiNhuanTheoNgay.setForeground(Color.BLACK);
	}

	public void thayDoiMauCacPanelButtonKhiNgayBiLoi() {
		panelTongHoaDon.setBackground(new Color(220, 220, 220));
		panelTongHoaDon.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblTongHoaDon.setBackground(new Color(220, 220, 220));
		lblTongHoaDon.setForeground(Color.LIGHT_GRAY);

		panelTongSanPham.setBackground(new Color(220, 220, 220));
		panelTongSanPham.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblTongSanPham.setBackground(new Color(220, 220, 220));
		lblTongSanPham.setForeground(Color.LIGHT_GRAY);

		panelTongThuNhapTheoNgay.setBackground(new Color(220, 220, 220));
		panelTongThuNhapTheoNgay.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblTongThuNhapTheoNhay.setBackground(new Color(220, 220, 220));
		lblTongThuNhapTheoNhay.setForeground(Color.LIGHT_GRAY);

		panelLoiNhuanTheoNgay.setBackground(new Color(220, 220, 220));
		panelLoiNhuanTheoNgay.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblLoiNhuanTheoNgay.setBackground(new Color(220, 220, 220));
		lblLoiNhuanTheoNgay.setForeground(Color.LIGHT_GRAY);
	}

}

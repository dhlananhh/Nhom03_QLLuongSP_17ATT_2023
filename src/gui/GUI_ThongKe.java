package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;

import java.util.ArrayList;

import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;



import com.formdev.flatlaf.FlatLightLaf;


import charts.ChartPanelTongCongDoan;
import charts.ChartPanelTongLuong;
import charts.PanelDanhSachCongNhan;
import charts.PanelDanhSachNhanVien;
import charts.PanelDanhSachSanPham;
import connection.ConnectDB;

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


//import org.jfree.data.category.DefaultCategoryDataset;


import java.awt.event.ActionListener;
import java.sql.SQLException;
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

		btnThongKe = new JButton();
		btnThongKe.setIcon(new ImageIcon("icons/stat.png"));
		btnThongKe.setText("Thống kê");
		btnThongKe.setBackground(Color.WHITE);
		btnThongKe.setFont(new Font("Arial", Font.PLAIN, 12));
		btnThongKe.setBounds(382, 22, 115, 30);
		panelGiua.add(btnThongKe);

		cboThang = new JComboBox<>();
		cboThang.setBounds(77, 22, 146, 25);
		for (int i = 1; i <= 12; i++) {
			cboThang.addItem(i);
		}

		JLabel lblcboNam = new JLabel("Năm:");
		lblcboNam.setFont(new Font("Arial", Font.PLAIN, 14));
		lblcboNam.setBounds(139, 22, 76, 23);
		panelGiua.add(lblcboNam);

		cboNam = new JComboBox<>();
		cboNam.setBounds(191, 22, 146, 25);
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
		btnLuongNhanVien.setBounds(32, 77, 146, 66);
		panelGiua.add(btnLuongNhanVien);
		btnLuongNhanVien.setLayout(null);

		btnLuongCongNhan = new JButton("Lương công nhân");
		btnLuongCongNhan.setBackground(new Color(220, 220, 220));
		btnLuongCongNhan.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnLuongCongNhan.setBounds(254, 77, 146, 66);
		panelGiua.add(btnLuongCongNhan);
		btnLuongCongNhan.setLayout(null);

		btnSoLuongSanPham = new JButton("Số lượng sản phẩm");
		btnSoLuongSanPham.setBackground(new Color(220, 220, 220));
		btnSoLuongSanPham.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnSoLuongSanPham.setBounds(473, 77, 146, 66);
		panelGiua.add(btnSoLuongSanPham);
		btnSoLuongSanPham.setLayout(null);

		btnLuongNhanVien.setEnabled(false);
		btnLuongCongNhan.setEnabled(false);
		btnSoLuongSanPham.setEnabled(false);
		
		// Dang ky su kien cho button Thong ke
		btnThongKe.addActionListener(this);
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
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongLuongCongNhan dao_luongCN = new DAO_TongLuongCongNhan();
			ArrayList<TongLuong> tongLuongList = dao_luongCN.getTongLuongCongNhan(nam);
			ArrayList<TongLuongCongNhan> tongCN = dao_luongCN.getDanhSachTopCongNhan(nam);
			ChartPanelTongLuong panelBieuDoNew = new ChartPanelTongLuong(tongLuongList);
			panelBieuDo.removeAll();
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        PanelDanhSachCongNhan panelDSCN = new PanelDanhSachCongNhan(tongCN);
	        panelDanhSach.removeAll();
	        panelGiua.remove(panelDanhSach);
	        panelDanhSach = panelDSCN;
	        panelDanhSach.setBounds(678, 11, 595, 579);
	        panelGiua.add(panelDanhSach);
	        repaint();
	        revalidate();
	        
		}
		if(o.equals(btnLuongNhanVien)){
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongLuongNhanVien dao_luongNV = new DAO_TongLuongNhanVien();
			ArrayList<TongLuong> tongLuongList = dao_luongNV.getTongLuongCongTy(nam);
			ArrayList<TongLuongNhanVien> tongNV = dao_luongNV.getTongLuongNhanVien(nam);
			ChartPanelTongLuong panelBieuDoNew = new ChartPanelTongLuong(tongLuongList);
			panelBieuDo.removeAll();
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        repaint();
	        revalidate();
	        PanelDanhSachNhanVien panelDSNV = new PanelDanhSachNhanVien(tongNV);
	        panelGiua.remove(panelDanhSach);
	        panelDanhSach = panelDSNV;
	        panelDanhSach.setBounds(678, 11, 595, 579);
	        panelGiua.add(panelDanhSach);

	        repaint();
	        revalidate();
	        
		}
		if(o.equals(btnSoLuongSanPham)){
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			btnThongKe.setBorder(new LineBorder(Color.WHITE, 2, true));
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			DAO_TongSanPham dao_tongSP = new DAO_TongSanPham();
			ArrayList<TongCongDoan> tongCD = dao_tongSP.getSoLuongSanPhamHoanThanh(nam);
			ChartPanelTongCongDoan panelBieuDoNew = new ChartPanelTongCongDoan(tongCD);
			panelBieuDo.removeAll();
	        panelGiua.remove(panelBieuDo);
	        panelBieuDo = panelBieuDoNew;
	        panelBieuDo.setBounds(10, 172, 647, 418);
	        panelGiua.add(panelBieuDo);
	        
			ArrayList<TongSanPham> tongSP = dao_tongSP.getTopSanPham(nam);
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
		btnLuongNhanVien.setEnabled(true);
		btnLuongCongNhan.setEnabled(true);
		btnSoLuongSanPham.setEnabled(true);
		
		btnSoLuongSanPham.setBackground(new Color(220, 220, 220));
		btnSoLuongSanPham.setBorder(new LineBorder(Color.WHITE, 1, true));
		
		btnLuongCongNhan.setBackground(new Color(220, 220, 220));
		btnLuongCongNhan.setBorder(new LineBorder(Color.WHITE, 1, true));
		
		btnLuongNhanVien.setBackground(new Color(220, 220, 220));
		btnLuongNhanVien.setBorder(new LineBorder(Color.WHITE, 1, true));
		
		btnLuongNhanVien.setBackground(new Color(116, 214, 128));
		btnLuongNhanVien.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnLuongNhanVien.setForeground(Color.BLACK);

		btnLuongCongNhan.setBackground(new Color(116, 214, 128));
		btnLuongCongNhan.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnLuongCongNhan.setForeground(Color.BLACK);

		btnSoLuongSanPham.setBackground(new Color(116, 214, 128));
		btnSoLuongSanPham.setBorder(new LineBorder(Color.ORANGE, 2, true));
		btnSoLuongSanPham.setForeground(Color.BLACK);
		


	}

}

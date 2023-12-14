package charts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.TongLuongCongNhan;
import entity.TongLuongNhanVien;
import entity.TongSanPham;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PanelDanhSachSanPham extends JPanel {

    private JTable tableDanhSach;
    private DefaultTableModel modelTableDanhSach;
	private JLabel lblLoiNhuan;
	private float tongChi = 0;
	DecimalFormat df = new DecimalFormat("###,### VND");
    public PanelDanhSachSanPham(ArrayList<TongSanPham> danhSach) {
        initGUI();
        hienDanhSachTopSanPham(danhSach);
    }

    private void initGUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(678, 11, 595, 579);

        String[] header = {"Mã Sản phẩm", "Tên Sản phẩm", "Số lượng hoàn thành"};
        modelTableDanhSach = new DefaultTableModel(header, 0);

        tableDanhSach = new JTable(modelTableDanhSach);
        tableDanhSach.setBackground(Color.WHITE);
        tableDanhSach.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tableDanhSach);
        tableDanhSach.setRowHeight(20);
		scrollPane.setSize(575, 464);
		scrollPane.setLocation(10, 51);
        add(scrollPane);
        
		JLabel lblNewLabel = new JLabel("DANH SÁCH TỔNG HỢP SẢN PHÂM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 11, 423, 29);
		add(lblNewLabel);
		
		lblLoiNhuan = new JLabel("TỔNG SỐ LƯỢNG: ");
		lblLoiNhuan.setFont(new Font("Arial", Font.BOLD, 14));
		lblLoiNhuan.setBounds(10, 536, 721, 21);
		add(lblLoiNhuan);
    }

    private void hienDanhSachTopSanPham(ArrayList<TongSanPham> danhSach) {
        modelTableDanhSach.setRowCount(0);

        for (TongSanPham tl : danhSach) {
            Object[] rowData = {
            		tl.getMaSP(),
            		tl.getTenSP(),
                    tl.getTongSoLuong()
            };
            modelTableDanhSach.addRow(rowData);
            tongChi+= tl.getTongSoLuong();
        }
        lblLoiNhuan.setText("TỔNG SỐ LƯỢNG: "+tongChi);
    }

    public static JPanel createPanel(ArrayList<TongSanPham> danhSach) {
        return new PanelDanhSachSanPham(danhSach);
    }
}

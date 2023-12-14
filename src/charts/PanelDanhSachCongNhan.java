package charts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.TongLuongCongNhan;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PanelDanhSachCongNhan extends JPanel {

    private JTable tableDanhSach;
    private DefaultTableModel modelTableDanhSach;
	private JLabel lblLoiNhuan;
	private float tongChi = 0;
	DecimalFormat df = new DecimalFormat("###,### VND");
    public PanelDanhSachCongNhan(ArrayList<TongLuongCongNhan> danhSach) {
        initGUI();
        hienDanhSachTopCongNhan(danhSach);
    }

    private void initGUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(678, 11, 595, 579);

        String[] header = {"Mã CN", "Họ Tên CN", "Mã Tổ SX", "Tổng Lương"};
        modelTableDanhSach = new DefaultTableModel(header, 0);

        tableDanhSach = new JTable(modelTableDanhSach);
        tableDanhSach.setBackground(Color.WHITE);
        tableDanhSach.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tableDanhSach);
        tableDanhSach.setRowHeight(20);
		scrollPane.setSize(575, 464);
		scrollPane.setLocation(10, 51);
        add(scrollPane);
        
		JLabel lblNewLabel = new JLabel("DANH SÁCH TỔNG HỢP LƯƠNG CÔNG NHÂN");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 11, 423, 29);
		add(lblNewLabel);
		
		lblLoiNhuan = new JLabel("TỔNG CHI: ");
		lblLoiNhuan.setFont(new Font("Arial", Font.BOLD, 14));
		lblLoiNhuan.setBounds(10, 536, 721, 21);
		add(lblLoiNhuan);
    }

    private void hienDanhSachTopCongNhan(ArrayList<TongLuongCongNhan> danhSach) {
        modelTableDanhSach.setRowCount(0);

        for (TongLuongCongNhan tl : danhSach) {
            Object[] rowData = {
                    tl.getMaCongNhan(),
                    tl.getHoTenCongNhan(),
                    tl.getMaToSanXuat(),
                    df.format(tl.getTongLuong())
            };
            modelTableDanhSach.addRow(rowData);
            tongChi+= tl.getTongLuong();
        }
        lblLoiNhuan.setText("TỔNG CHI: "+df.format(tongChi));
    }

    public static JPanel createPanel(ArrayList<TongLuongCongNhan> danhSach) {
        return new PanelDanhSachCongNhan(danhSach);
    }
}

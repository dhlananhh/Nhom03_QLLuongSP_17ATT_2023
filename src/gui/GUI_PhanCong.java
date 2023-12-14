package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAO_ChamCong;
import dao.DAO_CongDoan;
import dao.DAO_CongNhan;
import dao.DAO_SanPham;
import entity.ChamCong;
import entity.CongDoan;
import entity.CongNhanSanXuat;
import entity.SanPham;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Component;
import javax.swing.table.TableModel;

import org.w3c.dom.CDATASection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GUI_PhanCong extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JDateChooser chooserNgay = new JDateChooser();
	private JTextField txtMaCN, txtTenCN, txtTenSP, txtTenCD;
	private JComboBox<String> cbMaSP, cbMaCD;
	private JSpinner spinChiTieu;
	private JButton btnLuu, btnXoa;
	private DefaultTableModel modelCongNhan, modelPhanCong;
	private JTable tableCongNhan, tablePhanCong;
	private DAO_ChamCong chamCong_dao = new DAO_ChamCong();
	private DAO_SanPham sanPham_dao = new DAO_SanPham();
	private DAO_CongDoan congDoan_dao = new DAO_CongDoan();
	private DAO_CongNhan congNhan_dao = new DAO_CongNhan();
	private Icon icon = new Icon();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLightLaf.setup();
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
		ConnectDB.getInstance().connect();
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
		pnCongNhan.setLayout(new BoxLayout(pnCongNhan, BoxLayout.X_AXIS));
		
		Box b = Box.createHorizontalBox();
		
		
		JPanel pnNgay = new JPanel();
		pnNgay.setLayout(new BoxLayout(pnNgay, BoxLayout.Y_AXIS));
		pnNgay.setBackground(new Color(245, 251, 255));
		JLabel lblNgay = new JLabel("Ngày:  ");
		
		pnNgay.add(b);
		pnNgay.add(Box.createVerticalStrut(20));
		b.add(lblNgay);
		contentPane.add(pnNgay, BorderLayout.NORTH);
		chooserNgay.getCalendarButton().setToolTipText("Chọn ngày phân công");
		chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
		chooserNgay.setFont(new Font("Arial", Font.PLAIN, 16));
		chooserNgay.setDateFormatString("dd/MM/yyyy");
		chooserNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
		chooserNgay.setDate(new Date());
		b.add(chooserNgay);
		b.add(Box.createHorizontalStrut(1000));
		chooserNgay.addPropertyChangeListener("date", new PropertyChangeListener() {
			
			@Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    loadBangCN();
                    loadBangPhanCong();
                    clear();
                    Instant instant = chooserNgay.getDate().toInstant();
                    LocalDate day = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                    if(day.isBefore(LocalDate.now()) ) {
                    	JOptionPane.showMessageDialog(null, "Lưu ý: bạn không thể chỉnh sửa danh sách phân công của ngày này!");
                    	btnLuu.setEnabled(false);
                    	tableCongNhan.setEnabled(false);
                    	cbMaSP.setEnabled(false);
                    	btnXoa.setEnabled(false);
                    }else {
                    	btnLuu.setEnabled(true);
                    	tableCongNhan.setEnabled(true);
                    	cbMaSP.setEnabled(true);
                    	btnXoa.setEnabled(true);
                    }
                }
            }
		});
		Component verticalStrut = Box.createVerticalStrut(20);
		pnCongNhan.add(verticalStrut);
		
		
		
		String[] columnsCN = { "Mã công nhân", "Tên công nhân", "Tổ sản xuất" };
		modelCongNhan = new DefaultTableModel(columnsCN, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể sửa đổi
                return false;
            }
		};
		tableCongNhan = new JTable(modelCongNhan);
		JScrollPane spTableCN = new JScrollPane(tableCongNhan);
		spTableCN.setPreferredSize(new Dimension(500, 250));
		pnCongNhan.add(spTableCN);
		spTableCN.setViewportView(tableCongNhan);
		spTableCN.setBorder(BorderFactory.createTitledBorder("Công nhân chưa phân công"));
		pnCongNhan.add(Box.createVerticalStrut(20));
		pnCongNhan.setBackground(new Color(245, 251, 255));
		
		loadBangCN();
		
		JPanel pnInput = new JPanel();
		contentPane.add(pnInput, BorderLayout.CENTER);
		pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
		pnInput.setBorder(BorderFactory.createTitledBorder("Phân công"));
		pnInput.setBackground(new Color(130, 199, 250));
		
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
		cbMaSP.addItem("");
		for (SanPham sp : sanPham_dao.getalltbSanPham()) {
			cbMaSP.addItem(sp.getMaSP());
		}
		cbMaSP.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				cbMaCD.removeAllItems();
				txtTenSP.setText(sanPham_dao.getSanPhamTheoMa(cbMaSP.getSelectedItem().toString()).getTenSP());
				for (CongDoan cd : congDoan_dao.getalltbCongDoan()) {
					if(cbMaSP.getSelectedItem().toString().equals(cd.getMaSP().getMaSP()))
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
		txtTenSP.setEditable(false);
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
		cbMaCD.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object macd = cbMaCD.getSelectedItem();
				if(macd!=null)
					txtTenCD.setText(congDoan_dao.getCongDoanTheoMa(macd.toString()).getTenCD());
			}
		});
		cbMaCD.setPreferredSize(new Dimension(200, 10));
		b3.add(cbMaCD);
		b3.add(Box.createHorizontalStrut(30));
		JLabel lblTenCD = new JLabel("Tên công đoạn");
		b3.add(lblTenCD);
		b3.add(Box.createHorizontalStrut(30));
		txtTenCD = new JTextField();
		b3.add(txtTenCD);
		txtTenCD.setEditable(false);
		
		Box b4 = Box.createHorizontalBox();
		pnInput.add(b4);
		//pnInput.add(Box.createVerticalStrut(30));
		b4.add(Box.createHorizontalStrut(30));
		JLabel lblChiTieu = new JLabel("Chỉ tiêu");
		b4.add(lblChiTieu);
		b4.add(Box.createHorizontalStrut(30));
		SpinnerModel modelChiTieu = new SpinnerNumberModel(1, 1, 100, 1);
		spinChiTieu = new JSpinner(modelChiTieu);
		b4.add(spinChiTieu);
		b4.add(Box.createHorizontalStrut(800));
		
		Box b5 = Box.createHorizontalBox();
		pnInput.add(b5);
		btnLuu = new JButton(icon.iconThem);
		btnLuu.setText("Thêm");
		btnLuu.setEnabled(false);
		btnLuu.setBackground(new Color(0, 153, 204));
		btnLuu.setForeground(Color.WHITE);
		b5.add(btnLuu);
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		b5.add(horizontalStrut);
		
		btnXoa = new JButton(icon.iconXoa);
		btnXoa.setText("Xóa");
		btnXoa.setBackground(Color.red);
		btnXoa.setEnabled(false);
		b5.add(btnXoa);
		pnInput.add(Box.createVerticalStrut(70));
		
		contentPane.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		
		String[] columnsPC = { "Mã công nhân", "Tên công nhân", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Chỉ tiêu" };
		modelPhanCong = new DefaultTableModel(columnsPC, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể sửa đổi
                return false;
            }
		};
		tablePhanCong = new JTable(modelPhanCong);
		
		JScrollPane spTablePC = new JScrollPane(tablePhanCong);
		spTablePC.setPreferredSize(new Dimension(1500, 350));
		contentPane.add(spTablePC, BorderLayout.SOUTH);
		spTablePC.setViewportView(tablePhanCong);
		spTablePC.setBorder(BorderFactory.createTitledBorder("Bảng phân công"));
		loadBangPhanCong();
		tableCongNhan.addMouseListener(this);
		tablePhanCong.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			//CongDoan cd= congDoan_dao.getCongDoanTheoMa(cbMaCD.getSelectedItem().toString());
            //String maCDTruoc = String.format("%s%02d","CD",Integer.parseInt(cd.getMaCD().split("CD")[1])-1);
            //if(cd.getThuTu()!=1 && (Integer) spinChiTieu.getValue())
			if(btnLuu.getText().equals("Thêm"))
				themPhanCong();
			else
				suaPhanCong();
			clear();
		}
		else if(o.equals(btnXoa)) {
			xoaPhanCong();
			clear();
		}
	}
	public void clear() {
		txtMaCN.setText("");
		txtTenCN.setText("");
		txtTenCD.setText("");
		cbMaSP.setSelectedIndex(0);
		tableCongNhan.clearSelection();
		tablePhanCong.clearSelection();
		btnXoa.setEnabled(false);
		btnLuu.setEnabled(false);
		spinChiTieu.setValue(1);
		loadBangPhanCong();
		loadBangCN();
	}
	public void xoaPhanCong() {
		try {
			chamCong_dao.xoaPhanCong(txtMaCN.getText(), new java.sql.Date(chooserNgay.getDate().getTime()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadBangCN() {
		modelCongNhan.setRowCount(0);
		Date ngay = chooserNgay.getDate();
		ArrayList<CongNhanSanXuat> congNhanDaPhanCong = new ArrayList<>();
		for (ChamCong phanCong : chamCong_dao.getDSChamCongTheoNgay(new java.sql.Date(ngay.getTime()))) {
			congNhanDaPhanCong.add(new CongNhanSanXuat(phanCong.getMaCN()));
		}
		List<CongNhanSanXuat> congNhanChuaPhanCong = congNhan_dao.getDSCongNhan();
		congNhanChuaPhanCong.removeAll(congNhanDaPhanCong);
		for (CongNhanSanXuat cn : congNhanChuaPhanCong) {
			String[] row = {cn.getMaCN(), cn.getHoTenCN(), cn.getToSanXuat().getMaToSX()+""};
			modelCongNhan.addRow(row);
		}
	}
	public void loadBangPhanCong() {
		modelPhanCong.setRowCount(0);
		Date ngay = chooserNgay.getDate();
		for (ChamCong phanCong : chamCong_dao.getDSChamCongTheoNgay(new java.sql.Date(ngay.getTime()))) {
			String macd = phanCong.getMaCD();
			String[] row = {phanCong.getMaCN(), congNhan_dao.getCongNhanTheoMa(phanCong.getMaCN()).getHoTenCN(),
					sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getMaSP(),
					sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getTenSP(),macd,
					congDoan_dao.getCongDoanTheoMa(macd).getTenCD(), phanCong.getChiTieu()+""};
			modelPhanCong.addRow(row);
		}
	}
	public void themPhanCong(){
		String maCN = txtMaCN.getText();
		String tenCN = txtTenCN.getText();
		String maSP = cbMaSP.getSelectedItem().toString();
		String tenSP = txtTenSP.getText();
		String maCD = cbMaCD.getSelectedItem().toString();
		String tenCD = txtTenCD.getText();
		String chiTieu = spinChiTieu.getValue().toString();
		ChamCong phanCong = new ChamCong(maCN, maCD, new java.sql.Date(chooserNgay.getDate().getTime()) , Integer.parseInt(chiTieu));
		try {
			chamCong_dao.themBangPhanCong(phanCong);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String object[] = {maCN, tenCN, maSP, tenSP, maCD, tenCD, chiTieu};
		modelPhanCong.addRow(object);
	}
	public void suaPhanCong() {
		String maCN = txtMaCN.getText();
		String maCD = cbMaCD.getSelectedItem().toString();
		String chiTieu = spinChiTieu.getValue().toString();
		ChamCong phanCong = new ChamCong(maCN, maCD, new java.sql.Date(chooserNgay.getDate().getTime()) , Integer.parseInt(chiTieu));
		chamCong_dao.suaPhanCong(phanCong);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int rowPC = tablePhanCong.getSelectedRow();
		int rowCN = tableCongNhan.getSelectedRow();
		if(rowCN != -1) {
			btnXoa.setEnabled(false);
			btnLuu.setIcon(icon.iconThem);
			btnLuu.setText("Thêm");
			btnLuu.setEnabled(true);
			txtMaCN.setText(modelCongNhan.getValueAt(rowCN, 0).toString());
			txtTenCN.setText(modelCongNhan.getValueAt(rowCN, 1).toString());
			cbMaSP.setSelectedIndex(0);
			txtTenCD.setText("");
			tablePhanCong.clearSelection();
		}
		
		if(rowPC != -1){
			btnXoa.setEnabled(true);
			btnLuu.setIcon(icon.iconSua);
			btnLuu.setText("Sửa");
			btnLuu.setEnabled(true);
			txtMaCN.setText(modelPhanCong.getValueAt(rowPC, 0).toString());
			txtTenCN.setText(modelPhanCong.getValueAt(rowPC, 1).toString());
			cbMaSP.setSelectedItem(modelPhanCong.getValueAt(rowPC, 2));
			cbMaCD.setSelectedItem(modelPhanCong.getValueAt(rowPC, 4));
			spinChiTieu.setValue(Integer.parseInt(modelPhanCong.getValueAt(rowPC, 6).toString()));
			tableCongNhan.clearSelection();
		}
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
}

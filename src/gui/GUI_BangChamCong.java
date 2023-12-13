package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAO_ChamCong;
import dao.DAO_CongDoan;
import dao.DAO_CongNhan;
import dao.DAO_SanPham;
import entity.ChamCong;
import java.awt.Component;

public class GUI_BangChamCong extends JFrame implements ActionListener, MouseListener{
	private JPanel pnContent;
	private JLabel lblTieuDe, lblNgay, lblMaCN, lblTenCD, lblMaSP, lblTenSP, lblMaCD, lblTenCN, lblChiTieu, lblSoLuongHT;
	private Font BVNPro;
	private DefaultTableModel modelCC, modelHT;
	private JTable tableCC, tableHT;
	private JDateChooser chooserNgay = new JDateChooser();
	private JSpinner spinSLHT;
	//private JLabel lblNgay;
	private JButton btnLoc, btnLuu;
	private JComboBox<String> cbLoc;
	private JTextField txtLoc, txtMaCN, txtTenCN, txtMaSP, txtTenSP, txtMaCD, txtTenCD, txtChiTieu;
	private JCheckBox ckDatCT;
	private DAO_ChamCong chamCong_dao = new DAO_ChamCong();
	private DAO_SanPham sanPham_dao = new DAO_SanPham();
	private DAO_CongDoan congDoan_dao = new DAO_CongDoan();
	private DAO_CongNhan congNhan_dao = new DAO_CongNhan();
	private Component horizontalStrut;
	private JButton btnTaiLai;
	private Icon icon = new Icon();
	
	public GUI_BangChamCong() throws SQLException{
		ConnectDB.getInstance().connect();;
		setTitle("Bảng chấm công");
		setSize(1300, 700);
		setResizable(false);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//font
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		//panel
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		getContentPane().add(pnContent);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(0, 102, 204));
		
		
		lblTieuDe = new JLabel("BẢNG CHẤM CÔNG");
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		pnTop.add(lblTieuDe);
		pnContent.add(pnTop, BorderLayout.NORTH);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setBackground(new Color(245, 251, 255));
		
		JPanel pnBot = new JPanel();
		pnContent.add(pnBot, BorderLayout.SOUTH);
		pnBot.setBackground(new Color(245, 251, 255));
		pnBot.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		//pnCenter
		Box b, b1, b2, b2_1, b2_2;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b2_1 = Box.createVerticalBox();
		b2_2 = Box.createVerticalBox();
		//Box 1
		b1.add(lblNgay = new JLabel("Ngày:"));
		b1.add(Box.createHorizontalStrut(20));
		
        chooserNgay.getCalendarButton().setToolTipText("Chọn ngày");
        chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
        chooserNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
        chooserNgay.setFont(new Font("Arial", Font.PLAIN, 16));
        chooserNgay.setDateFormatString("dd/MM/yyyy");
        chooserNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
        chooserNgay.setDate(new Date());
        chooserNgay.addPropertyChangeListener("date", new PropertyChangeListener() {
			
			@Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                	modelHT.setRowCount(0);
                    loadBang();
                    clear();
                    Instant instant = chooserNgay.getDate().toInstant();
                    LocalDate day = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                    if(!day.isEqual(LocalDate.now()) ) {
                    	JOptionPane.showMessageDialog(null, "Lưu ý: bạn không thể chấm công cho ngày này!");
                    	btnLuu.setEnabled(false);
                    	tableCC.setEnabled(false);
                    }else {
                    	btnLuu.setEnabled(true);
                    	tableCC.setEnabled(true);
                    }
                }
            }
		});
        
        b1.add(chooserNgay);
        b1.add(Box.createHorizontalStrut(400));
        b1.add(cbLoc = new JComboBox<String>());
        cbLoc.addItem("Mã công nhân");
        cbLoc.addItem("Mã sản phẩm");
        cbLoc.addItem("Mã công đoạn");
        b1.add(Box.createHorizontalStrut(10));
        b1.add(btnLoc = new JButton(icon.iconTim));
        btnLoc.setText("Lọc");
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtLoc = new JTextField(5));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(ckDatCT = new JCheckBox("Đạt chỉ tiêu"));
        
        btnLoc.setBackground(new Color(0, 153, 204));
        btnLoc.setForeground(Color.WHITE);
        //Box 2 
        //Box 2 trái
        JPanel pnChamCong = new JPanel();
        pnChamCong.setBackground(new Color(130, 199, 250));
        Box c1, c2, c3, c4, c5;
        c1 = Box.createHorizontalBox();
        c2 = Box.createHorizontalBox();
        c3 = Box.createHorizontalBox();
        c4 = Box.createHorizontalBox();
        c5 = Box.createHorizontalBox();
        
        c1.add(lblMaCN = new JLabel("Mã công nhân:"));
        c1.add(Box.createHorizontalStrut(20));
        c1.add(txtMaCN = new JTextField(12));
        c1.add(Box.createHorizontalStrut(40));
        c1.add(lblTenCN = new JLabel("Tên công nhân:"));
        c1.add(Box.createHorizontalStrut(20));
        c1.add(txtTenCN = new JTextField(12));
        
        c2.add(lblMaSP = new JLabel("Mã sản phẩm:"));
        c2.add(Box.createHorizontalStrut(20));
        c2.add(txtMaSP = new JTextField(12));
        c2.add(Box.createHorizontalStrut(40));
        c2.add(lblTenSP = new JLabel("Tên sản phẩm:"));
        c2.add(Box.createHorizontalStrut(20));
        c2.add(txtTenSP = new JTextField(12));
        
        c3.add(lblMaCD = new JLabel("Mã công đoạn:"));
        c3.add(Box.createHorizontalStrut(20));
        c3.add(txtMaCD = new JTextField(12));
        c3.add(Box.createHorizontalStrut(40));
        c3.add(lblTenCD = new JLabel("Tên công đoạn:"));
        c3.add(Box.createHorizontalStrut(20));
        c3.add(txtTenCD = new JTextField(12));
        
        c4.add(lblChiTieu = new JLabel("Chỉ tiêu:"));
        c4.add(Box.createHorizontalStrut(20));
        c4.add(txtChiTieu = new JTextField(10));
        txtChiTieu.setMaximumSize(new Dimension(100, 2147483647));
        c4.add(Box.createHorizontalStrut(150));
        c4.add(lblSoLuongHT= new JLabel("Số lượng hoàn thành:"));
        c4.add(Box.createHorizontalStrut(20));
        SpinnerModel modelSLHT = new SpinnerNumberModel(0, 0, 100, 1);
        
		spinSLHT = new JSpinner(modelSLHT);
		spinSLHT.setMaximumSize(new Dimension(30, 32767));
		spinSLHT.setValue(0);
        c4.add(spinSLHT);
        
        btnTaiLai = new JButton(icon.iconTaiLai);
        btnTaiLai.setText("Tải lại");
        btnTaiLai.setBackground(Color.green);
        c5.add(btnTaiLai);
        
        c5.add(Box.createHorizontalStrut(350));
        c5.add(btnLuu = new JButton(icon.iconLuu));
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.setBackground(new Color(0, 153, 204));
        btnLuu.setForeground(Color.WHITE);
        //preference
        lblMaCN.setPreferredSize(lblMaCD.getPreferredSize());
        lblMaSP.setPreferredSize(lblMaCD.getPreferredSize());
        lblChiTieu.setPreferredSize(lblMaCD.getPreferredSize());
        
        lblTenCD.setPreferredSize(lblSoLuongHT.getPreferredSize());
        lblTenCN.setPreferredSize(lblSoLuongHT.getPreferredSize());
        lblTenSP.setPreferredSize(lblSoLuongHT.getPreferredSize());
        b2_1.add(Box.createRigidArea(new Dimension(20, 20)));
        b2_1.add(c1);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c2);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c3);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c4);
        
        horizontalStrut = Box.createHorizontalStrut(100);
        c4.add(horizontalStrut);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c5);
        pnChamCong.add(b2_1);
        b2.add(pnChamCong);
        pnChamCong.setBorder(BorderFactory.createTitledBorder("Chấm công"));
        
        //box 2 phải
        JPanel pnHoanThanh = new JPanel();
        pnHoanThanh.setBorder(BorderFactory.createTitledBorder("Sản phẩm hoàn thành"));
        pnHoanThanh.setBackground(new Color(245, 251, 255));
        String col2[] = {"Mã sản phẩm","Tên sản phẩm","Số lượng hoàn thành"};
        modelHT = new DefaultTableModel(col2, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể sửa đổi
                return false;
            }
		};
        tableHT = new JTable(modelHT);
        JScrollPane scroll2 = new JScrollPane(tableHT);
        b2_2.add(scroll2);
        pnHoanThanh.add(b2_2);
        b2.add(Box.createHorizontalStrut(50));
        b2.add(pnHoanThanh);
        
        
        b.add(Box.createRigidArea(new Dimension(20, 20)));
        b.add(b1);
        b.add(Box.createRigidArea(new Dimension(0, 20)));
        b.add(b2);
        pnCenter.add(b);
        //
        txtMaCN.setEditable(false);
        txtTenCN.setEditable(false);
        txtMaCD.setEditable(false);
        txtTenCD.setEditable(false);
        txtChiTieu.setEditable(false);
        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
		//pbot
		String col[] = {"Mã công nhân","Tên công nhân","Mã sản phẩm","Tên sản phẩm","Mã công đoạn"
				,"Tên công đoạn","Chỉ tiêu","Hoàn thành"};
		modelCC = new DefaultTableModel(col,0) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
		};
		tableCC = new JTable(modelCC);
		JScrollPane scroll = new JScrollPane(tableCC);
		scroll.setPreferredSize(new Dimension(1200, 250));
		pnBot.add(scroll);
		modelCC.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
                int column = e.getColumn();
                Object changedValue = 0;
                if (column != TableModelEvent.ALL_COLUMNS && row != TableModelEvent.HEADER_ROW) 
                    // Lấy giá trị cụ thể từ ô vừa được thay đổi
                    changedValue = modelCC.getValueAt(row, column);
                if(column == 7) {
                	int sltruoc,slsau;
                    sltruoc = (Integer)spinSLHT.getValue();
                    if(changedValue.equals(""))
                    	slsau = 0 ;
                    else
                    	slsau = Integer.parseInt(changedValue.toString());
                    String macd = modelCC.getValueAt(row, 4).toString();
                    ChamCong chamCong= new ChamCong(macd, null, (slsau-sltruoc));
                    loadBangHT(modelCC.getValueAt(row, 2).toString(), chamCong, macd);
                    spinSLHT.setValue(slsau);
                    nhapSLHT();
                }
                
			}
		});
		loadBang();
		tableCC.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnLoc.addActionListener(this);
		btnTaiLai.addActionListener(this);
	}
	
	public static void main(String[] args) throws SQLException {
		FlatLightLaf.setup();	
		new GUI_BangChamCong().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			int row= tableCC.getSelectedRow();
			int sltruoc= Integer.parseInt(modelCC.getValueAt(row, 7).toString());
			int slsau = (Integer) spinSLHT.getValue();
			loadBangHT(modelCC.getValueAt(row, 2).toString(), new ChamCong("", null, (slsau-sltruoc)), modelCC.getValueAt(row, 4).toString());
			modelCC.setValueAt(spinSLHT.getValue(), tableCC.getSelectedRow(), 7);
			JOptionPane.showMessageDialog(null, "Chấm công thành công!");
		}
		if(o.equals(btnLoc)) {
			String tieuChi = cbLoc.getSelectedItem().toString();
			if(tieuChi.equalsIgnoreCase("Mã sản phẩm"))
				locTheoMaSP();
			if(tieuChi.equalsIgnoreCase("Mã công nhân"))
				locTheoCN();
		}
		if(o.equals(btnTaiLai))
			clear();
	}
	public void nhapSLHT() {
		ChamCong chamCong = new ChamCong(txtMaCN.getText(),new java.sql.Date(chooserNgay.getDate().getTime()),
				(Integer) spinSLHT.getValue());
		try {
			chamCong_dao.chamCong(chamCong);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadBangHT(String masp, ChamCong chamCong, String macd) {
		int slcd = congDoan_dao.getAllCongDoanTheoSP(masp.toString()).size();
        if(congDoan_dao.getCongDoanTheoMa(macd).getThuTu()==slcd && chamCong.getSoLuongHoanThanh()!=0) {
			int rowHT= modelHT.getRowCount();
			boolean capNhat = false;
			for (int i = 0; i < rowHT; i++) {
	            if (modelHT.getValueAt(i, 0).equals(masp)) {
	                int slht= Integer.parseInt(modelHT.getValueAt(i, 2).toString())+ chamCong.getSoLuongHoanThanh();
	                modelHT.setValueAt(slht, i, 2);
	                capNhat= true;
	                break;
	            }
	        }
			if(!capNhat) {
				String[] addRowHT= {masp, sanPham_dao.getSanPhamTheoMa(masp).getTenSP(),chamCong.getSoLuongHoanThanh()+""}; 
				modelHT.addRow(addRowHT);
			}
        }
		
	}
	public void loadBang() {
		modelCC.setRowCount(0);
		for (ChamCong chamCong : chamCong_dao.getDSChamCongTheoNgay(new java.sql.Date(chooserNgay.getDate().getTime()))) {
			String macd = chamCong.getMaCD();
			String masp = sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getMaSP();
			String[] row = {chamCong.getMaCN(), congNhan_dao.getCongNhanTheoMa(chamCong.getMaCN()).getHoTenCN(),masp,
					sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getTenSP(),macd,
					congDoan_dao.getCongDoanTheoMa(macd).getTenCD(), chamCong.getChiTieu()+"", chamCong.getSoLuongHoanThanh()+""};
			modelCC.addRow(row);
			loadBangHT(masp, chamCong, macd);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int rowCCong = tableCC.getSelectedRow();
		if(rowCCong != -1) {
			btnLuu.setEnabled(true);
			txtMaCN.setText(modelCC.getValueAt(rowCCong, 0).toString());
			txtTenCN.setText(modelCC.getValueAt(rowCCong, 1).toString());
			txtMaSP.setText(modelCC.getValueAt(rowCCong, 2).toString());
			txtTenSP.setText(modelCC.getValueAt(rowCCong, 3).toString());
			txtMaCD.setText(modelCC.getValueAt(rowCCong, 4).toString());
			txtTenCD.setText(modelCC.getValueAt(rowCCong, 5).toString());
			txtChiTieu.setText(modelCC.getValueAt(rowCCong, 6).toString());
			spinSLHT.setValue(Integer.parseInt(modelCC.getValueAt(rowCCong, 7).toString()) );
		}
		else
			btnLuu.setEnabled(false);
			
	}
	public void clear() {
		tableCC.clearSelection();
		//modelHT.setRowCount(0);
		modelHT.getDataVector().removeAllElements();
		txtMaCN.setText("");
		txtTenCN.setText("");
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtChiTieu.setText("");
		spinSLHT.setValue(0);
		btnLuu.setEnabled(false);
		loadBang();
	}
	public void locTheoMaSP() {
		String ma = txtLoc.getText();
		modelCC.setRowCount(0);
		
//		for (ChamCong chamCong : chamCong_dao.getDSChamCongTheoSP(ma, new java.sql.Date(chooserNgay.getDate().getTime()))) {
//			String macd = chamCong.getMaCD();
//			String masp = sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getMaSP();
//			String[] row = {chamCong.getMaCN(), congNhan_dao.getCongNhanTheoMa(chamCong.getMaCN()).getHoTenCN(),masp,
//					sanPham_dao.getalltbSanPhamTheoMaCD(macd).get(0).getTenSP(),macd,
//					congDoan_dao.getCongDoanTheoMa(macd).getTenCD(), chamCong.getChiTieu()+"", chamCong.getSoLuongHoanThanh()+""};
//			modelCC.addRow(row);
//		}
	}
	public void locTheoCN() {
		modelCC.setRowCount(0);
		String ma = txtLoc.getText();
		ChamCong chamCong = chamCong_dao.getChamCongTheoCN(ma, new java.sql.Date(chooserNgay.getDate().getTime()));
		String macd = chamCong.getMaCD();
		String masp = "";
		String[] row = {chamCong.getMaCN(), "","",
				"",macd,
				"", chamCong.getChiTieu()+"", chamCong.getSoLuongHoanThanh()+""};
		modelCC.addRow(row);
	}
	public void loc() {
		DAO_SanPham sp_dao = new DAO_SanPham();
		DAO_CongNhan cn_dao = new DAO_CongNhan();
		DAO_CongDoan cd_dao = new DAO_CongDoan();
		String tieuChi = cbLoc.getSelectedItem().toString();
		String ma = txtLoc.getText();
		modelCC.setRowCount(0);
		if(tieuChi.equals("Mã sản phẩm")) {
			for (ChamCong chamCong : chamCong_dao.getDSChamCongTheoSP(ma, new java.sql.Date(chooserNgay.getDate().getTime()))) {
				String macd = chamCong.getMaCD();
				String masp = sp_dao.getalltbSanPhamTheoMaCD(macd).get(0).getMaSP();
				String[] row = {chamCong.getMaCN(), cn_dao.getCongNhanTheoMa(chamCong.getMaCN()).getHoTenCN(),masp,
						sp_dao.getalltbSanPhamTheoMaCD(macd).get(0).getTenSP(),macd,
						cd_dao.getCongDoanTheoMa(macd).getTenCD(), chamCong.getChiTieu()+"", chamCong.getSoLuongHoanThanh()+""};
				modelCC.addRow(row);
			}
		}
		if(tieuChi.equals("Mã công nhân")) {
//			for (ChamCong chamCong : chamCong_dao.getDSChamCongTheoCN(ma, new java.sql.Date(chooserNgay.getDate().getTime()))) {
//				String macd = chamCong.getMaCD();
//				String masp = sanPham_dao.getSanPhamTheoMaCD(macd).getMaSP();
//				String[] row = {chamCong.getMaCN(), "","",
//						sanPham_dao.getSanPhamTheoMaCD(macd).getTenSP(),macd,
//						congDoan_dao.getCongDoanTheoMa(macd).getTenCD(), chamCong.getChiTieu()+"", chamCong.getSoLuongHoanThanh()+""};
//				modelCC.addRow(row);
//			}
		}
		if(modelCC.getRowCount()==0)
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin");
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

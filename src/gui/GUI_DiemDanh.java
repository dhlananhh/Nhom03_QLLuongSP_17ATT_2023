package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import connection.ConnectDB;
import dao.DAO_DiemDanh;
import dao.DAO_NhanVienHanhChinh;
import dao.DAO_PhongBan;
import entity.CongNhanSanXuat;
import entity.DiemDanh;
import entity.NhanVienHanhChinh;
import entity.PhongBan;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI_DiemDanh extends JFrame implements MouseListener, ActionListener{

	private JPanel contentPane;
	private JComboBox<Integer> cbThang, cbNam;
	private JComboBox<String> cbMaNV, cbMaPB;
	private LocalDate ngayHT = LocalDate.now();
	private JComboBox<String> cbPhep = new JComboBox<String>(new String[] {"","P","K"});
	private JTextField txtNghiPhep;
	private JTextField txtTenNV;
	private DefaultTableModel modelDiemDanh;
	private JTable tableDiemDanh;
	private List<String> columnsDD = new ArrayList<>();
	private JTextField txtKhongPhep;
	private DAO_NhanVienHanhChinh dao_NVHC = new DAO_NhanVienHanhChinh();
	private DAO_DiemDanh dao_DiemDanh = new DAO_DiemDanh();
	private DAO_PhongBan dao_PhongBan = new DAO_PhongBan();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtTenPB;
	private JButton btnTaiLai;
	private Icon icon = new Icon();
	private boolean isloadBang = true;
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
	 * @throws SQLException 
	 */
	public GUI_DiemDanh() throws SQLException {
		ConnectDB.getInstance().connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth = new JPanel();
		contentPane.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.X_AXIS));
		
		pnNorth.add(Box.createHorizontalStrut(50));
		
		JLabel lblNam = new JLabel("Năm:  ");
		pnNorth.add(lblNam);
		
		cbNam = new JComboBox<Integer>();
		for(int i=2023; i<=2030; i++)
			cbNam.addItem(i);
		cbNam.setSelectedItem(ngayHT.getYear());
		pnNorth.add(cbNam);
		
		JLabel lblThang = new JLabel("   Tháng:  ");
		pnNorth.add(lblThang);
		cbThang = new JComboBox<Integer>();
		pnNorth.add(cbThang);
		for(int i=1; i<=12; i++) 
			cbThang.addItem(i);
		cbThang.setSelectedItem(ngayHT.getMonthValue());
		Component horizontalStrut = Box.createHorizontalStrut(800);
		pnNorth.add(horizontalStrut);
		
		btnTaiLai = new JButton(icon.iconTaiLai);
		btnTaiLai.setText("Tải lại");
		pnNorth.add(btnTaiLai);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(100);
		pnNorth.add(horizontalStrut_3);

		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(245, 251, 255));
		contentPane.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.X_AXIS));
		
		Box b1 = Box.createVerticalBox();
		pnCenter.add(b1);
		b1.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		b3.add(Box.createHorizontalStrut(200));
		JLabel lblMaPB = new JLabel("Mã phòng ban:   ");
		b3.add(lblMaPB);
		taoDSDiemDanhTrongNgay();
		
		b1.add(b3);
		
		cbMaPB = new JComboBox();
		cbMaPB.setPreferredSize(new Dimension(100, 40));
		b3.add(cbMaPB);
		cbMaPB.addItem("");
		for (PhongBan pb : dao_PhongBan.getDanhSachPhongBan()) {
			cbMaPB.addItem(pb.getMaPhongBan());
		}
		cbMaPB.setMaximumRowCount(20);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(100);
		b3.add(horizontalStrut_1);
		b1.add(Box.createVerticalStrut(20));
		Box b2 = Box.createVerticalBox();
		pnCenter.add(b2);
		b2.add(Box.createVerticalStrut(20));
		Box b4 = Box.createHorizontalBox();
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên:    ");
		b4.add(lblNewLabel);
		cbMaNV = new JComboBox();
		cbMaNV.addItem("");
		for (NhanVienHanhChinh nv : dao_NVHC.getDanhSachNhanVien()) {
			cbMaNV.addItem(nv.getMaNV());
		}
		b4.add(cbMaNV);
		cbMaNV.setPreferredSize(new Dimension(200, 25));
		b4.add(Box.createHorizontalStrut(100));
		JLabel lblNghiPhep = new JLabel("Số ngày nghỉ có phép:         ");
		b4.add(lblNghiPhep);
		txtNghiPhep = new JTextField();
		txtNghiPhep.setEditable(false);
		txtNghiPhep.setText("");
		b4.add(txtNghiPhep);
		txtNghiPhep.setColumns(7);
		b4.add(Box.createHorizontalStrut(200));
		
		b2.add(b4);
		b2.add(Box.createVerticalStrut(20));
		Box b5= Box.createHorizontalBox();
		b5.add(Box.createHorizontalStrut(200));
		JLabel lblTenPB = new JLabel("Tên phòng ban:  ");
		b5.add(lblTenPB);
		
		b1.add(b5);
		
		txtTenPB = new JTextField();
		txtTenPB.setEditable(false);
		b5.add(txtTenPB);
		txtTenPB.setColumns(17);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(100);
		b5.add(horizontalStrut_2);
		b1.add(Box.createVerticalStrut(60));
		Box b6 = Box.createHorizontalBox();
		b2.add(b6);
		b2.add(Box.createVerticalStrut(60));
		
		JLabel lblNewLabel_1 = new JLabel("Tên nhân viên:    ");
		b6.add(lblNewLabel_1);
		
		txtTenNV = new JTextField();
		txtTenNV.setEditable(false);
		b6.add(txtTenNV);
		txtTenNV.setText("");
		txtTenNV.setColumns(24);
		b6.add(Box.createHorizontalStrut(80));
		JLabel lblKhongPhep = new JLabel("Số ngày nghỉ không phép:         ");
		b6.add(lblKhongPhep);
		txtKhongPhep = new JTextField();
		txtKhongPhep.setEditable(false);
		txtKhongPhep.setColumns(7);
		b6.add(txtKhongPhep);
		b6.add(Box.createHorizontalStrut(200));
		
		JPanel pnTable = new JPanel(new BorderLayout());
		Box b7 = Box.createHorizontalBox();
		pnTable.add(b7, BorderLayout.NORTH);
		b7.add(Box.createHorizontalStrut(50));
		b7.add(new JLabel("P: vắng có phép"));
		b7.add(Box.createHorizontalStrut(50));
		b7.add(new JLabel("K: vắng không phép"));
		
		Box b8 = Box.createVerticalBox();
		b8.add(Box.createVerticalStrut(10));
		pnTable.add(b8, BorderLayout.CENTER);
		modelDiemDanh = new DefaultTableModel(columnsDD.toArray(), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if((Integer)cbThang.getSelectedItem()== ngayHT.getMonthValue())
					return column == ngayHT.getDayOfMonth()+1;
				return false;
            }
		};
		tableDiemDanh = new JTable(modelDiemDanh);
		taoCotTheoThang();
		JScrollPane spTableDD = new JScrollPane(tableDiemDanh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spTableDD.setPreferredSize(new Dimension(1200, 500));
		contentPane.add(pnTable, BorderLayout.SOUTH);
		tableDiemDanh.setFillsViewportHeight(true);
//		modelDiemDanh.addTableModelListener(new TableModelListener() {
//			
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				// TODO Auto-generated method stub
//				int row = e.getFirstRow();
//                int column = e.getColumn();
//                Object changedValue = "";
//                if (column != TableModelEvent.ALL_COLUMNS && row != TableModelEvent.HEADER_ROW) 
//                    // Lấy giá trị cụ thể từ ô vừa được thay đổi
//                    changedValue = modelDiemDanh.getValueAt(row, column);
//                
//			}
//		});
		pnTable.add(spTableDD, BorderLayout.SOUTH);
		loadBang();
		modelDiemDanh.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
                int column = e.getColumn();
                if(!isloadBang && modelDiemDanh.getRowCount()!=-1 && column > 1) {
	                String changedValue = dao_DiemDanh.getNgayPhep(modelDiemDanh.getValueAt(row, 0).toString(), Date.valueOf(ngayHT));
	                if (column != TableModelEvent.ALL_COLUMNS && row != TableModelEvent.HEADER_ROW) 
	                    // Lấy giá trị cụ thể từ ô vừa được thay đổi
	                    changedValue = modelDiemDanh.getValueAt(row, column)+"".toString();
	                
	                	try {
	                    	dao_DiemDanh.capNhatDiemDanh(new DiemDanh(modelDiemDanh.getValueAt(row, 0).toString(),Date.valueOf(ngayHT) , changedValue.toString()));
	                    	txtNghiPhep.setText(dao_DiemDanh.tongNgayPhepTrongThang((Integer)cbThang.getSelectedItem(),modelDiemDanh.getValueAt(row, 0).toString())+"");
	                		txtKhongPhep.setText(dao_DiemDanh.tongNgayKhongPhepTrongThang((Integer)cbThang.getSelectedItem(),modelDiemDanh.getValueAt(row, 0).toString())+"");
	                		cbMaNV.setSelectedItem(modelDiemDanh.getValueAt(row, 0).toString());
	                		txtTenNV.setText(modelDiemDanh.getValueAt(row, 1).toString());
	                		cbMaPB.setEnabled(false);
	                		cbMaPB.setSelectedItem(dao_NVHC.layNhanVienTheoMa(modelDiemDanh.getValueAt(row, 0).toString()).getPhongBan().getMaPhongBan());
	                		txtTenPB.setText(dao_PhongBan.getPhongBanTheoMa(dao_NVHC.layNhanVienTheoMa(modelDiemDanh.getValueAt(row, 0).toString()).getPhongBan().getMaPhongBan()).getTenPhongBan());
	    	            } catch (SQLException e1) {
	    	            	   // TODO Auto-generated catch block
	    	            	   e1.printStackTrace();
	    	            }
	                }
			}
		});
		cbNam.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cbThang.getSelectedItem().toString().equals("2"))
					taoCotTheoThang();
				loadBang();
				
				if((Integer)cbNam.getSelectedItem()!= ngayHT.getYear())
					tableDiemDanh.setEnabled(false);
				else
					tableDiemDanh.setEnabled(true);
			}
		});
		cbThang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				modelDiemDanh.getDataVector().removeAllElements();
				taoCotTheoThang();
				loadBang();
				clear();
//				if((Integer)cbThang.getSelectedItem()!= ngayHT.getMonthValue())
//					tableDiemDanh.setEnabled(false);
//				else
//					tableDiemDanh.setEnabled(true);
			}
		});
		cbMaPB.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				txtTenPB.setText(dao_PhongBan.getPhongBanTheoMa(cbMaPB.getSelectedItem().toString()).getTenPhongBan());
				if(tableDiemDanh.getSelectedRow()==-1) {
					cbMaNV.removeAllItems();
					cbMaNV.addItem("");
					isloadBang = true;
					modelDiemDanh.setRowCount(0);
					int dem = 0;
					int ngayTrongThang = YearMonth.of(Integer.parseInt(cbNam.getSelectedItem().toString()), Integer.parseInt(cbThang.getSelectedItem().toString())).lengthOfMonth();
					for (NhanVienHanhChinh nv : dao_NVHC.layNhanVienTheoPhongBan(cbMaPB.getSelectedItem().toString())) {
						cbMaNV.addItem(nv.getMaNV());
						List<Object> row = new ArrayList<>();
						row.add(nv.getMaNV());
						row.add(nv.getHoTenNV());
						
						for(int i=1; i<= ngayTrongThang; i++) {
							String strDate = cbNam.getSelectedItem().toString()+"-"+cbThang.getSelectedItem().toString()+"-"+i;
							Date date;
							try {
								date = new java.sql.Date(df.parse(strDate).getTime());
								String value= dao_DiemDanh.getNgayPhep(nv.getMaNV(), date);
								row.add(value);
								
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						modelDiemDanh.addRow(row.toArray());
						dem++;
					}
					isloadBang = false;
				}
			}
		});
		
		tableDiemDanh.addMouseListener(this);
		btnTaiLai.addActionListener(this);
	}
	public void taoDSDiemDanhTrongNgay() {
		if(!dao_DiemDanh.ktDiemDanh(Date.valueOf(ngayHT)))
			for (NhanVienHanhChinh nv : dao_NVHC.getDanhSachNhanVien()) {
				try {
					dao_DiemDanh.themDiemDanh(new DiemDanh(nv.getMaNV(), Date.valueOf(ngayHT), ""));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	private void taoCotTheoThang() {
		columnsDD.removeAll(columnsDD);
		columnsDD.add("Mã nhân viên");
		columnsDD.add("Họ tên");
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
				Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// Đặt border cho ô
				Border border = BorderFactory.createLineBorder(Color.lightGray);
				((JComponent) component).setBorder(border);
				
				return component;
				}
		};
		int ngayTrongThang = YearMonth.of(Integer.parseInt(cbNam.getSelectedItem().toString()), Integer.parseInt(cbThang.getSelectedItem().toString())).lengthOfMonth();

		for(int i=1; i<= ngayTrongThang; i++)
			columnsDD.add(i+"/"+cbThang.getSelectedItem());
		modelDiemDanh.setColumnIdentifiers(columnsDD.toArray());
		tableDiemDanh.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableDiemDanh.getColumnModel().getColumn(0).setCellRenderer(cellRender);
		tableDiemDanh.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableDiemDanh.getColumnModel().getColumn(1).setCellRenderer(cellRender);
		
		for(int i=1; i<= ngayTrongThang; i++) {
			tableDiemDanh.getColumnModel().getColumn(i+1).setPreferredWidth(50);
			tableDiemDanh.getColumnModel().getColumn(i+1).setCellEditor(new DefaultCellEditor(cbPhep));
			tableDiemDanh.getColumnModel().getColumn(i+1).setCellRenderer(cellRender);
		}
		tableDiemDanh.getTableHeader().setResizingAllowed(false);
        tableDiemDanh.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
	}
	private void loadBang() {
		isloadBang = true;
		modelDiemDanh.setRowCount(0);
		int dem = 0;
		int ngayTrongThang = YearMonth.of(Integer.parseInt(cbNam.getSelectedItem().toString()), Integer.parseInt(cbThang.getSelectedItem().toString())).lengthOfMonth();
		for (NhanVienHanhChinh nv : dao_NVHC.getDanhSachNhanVien()) {
			List<Object> row = new ArrayList<>();
			row.add(nv.getMaNV());
			row.add(nv.getHoTenNV());
			
			for(int i=1; i<= ngayTrongThang; i++) {
				String strDate = cbNam.getSelectedItem().toString()+"-"+cbThang.getSelectedItem().toString()+"-"+i;
				Date date;
				try {
					date = new java.sql.Date(df.parse(strDate).getTime());
					String value= dao_DiemDanh.getNgayPhep(nv.getMaNV(), date);
					row.add(value);
					//tableDiemDanh.setValueAt(value, dem, i+1);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			modelDiemDanh.addRow(row.toArray());
			dem++;
		}
		isloadBang = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableDiemDanh.getSelectedRow();
		if(row == -1)
			cbMaPB.setEnabled(true);
		else
			cbMaPB.setEnabled(false);
		cbMaNV.setSelectedItem(modelDiemDanh.getValueAt(row, 0).toString());
		txtTenNV.setText(modelDiemDanh.getValueAt(row, 1).toString());
		txtNghiPhep.setText(dao_DiemDanh.tongNgayPhepTrongThang((Integer)cbThang.getSelectedItem(),modelDiemDanh.getValueAt(row, 0).toString())+"");
		txtKhongPhep.setText(dao_DiemDanh.tongNgayKhongPhepTrongThang((Integer)cbThang.getSelectedItem(),modelDiemDanh.getValueAt(row, 0).toString())+"");
		cbMaPB.setSelectedItem(dao_NVHC.layNhanVienTheoMa(modelDiemDanh.getValueAt(row, 0).toString()).getPhongBan().getMaPhongBan());
		txtTenPB.setText(dao_PhongBan.getPhongBanTheoMa(dao_NVHC.layNhanVienTheoMa(modelDiemDanh.getValueAt(row, 0).toString()).getPhongBan().getMaPhongBan()).getTenPhongBan());
	}
	public void clear() {
		cbMaPB.setSelectedIndex(0);
		cbMaNV.setSelectedIndex(0);
		txtKhongPhep.setText("");
		txtNghiPhep.setText("");
		txtTenNV.setText("");
		txtTenPB.setText("");
		tableDiemDanh.clearSelection();
		cbMaPB.setEnabled(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTaiLai)) {
			clear();
			loadBang();
		}
	}
}

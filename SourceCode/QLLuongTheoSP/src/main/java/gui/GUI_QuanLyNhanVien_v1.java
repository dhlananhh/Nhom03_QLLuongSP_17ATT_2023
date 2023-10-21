package gui;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class GUI_QuanLyNhanVien_v1 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnThietLapTTNV, pnThongTinNV, pnTacVu,
					pnNorth, pnCenter, pnSouth, pnTitle;
	private JLabel 	lblTitle, lblMaNV, lblHoTenNV, 
					lblPhongBan, lblChucVu, lblLuongCung,
					lblNgayCongQuyDinh, lblNgayCongThucTe;
	private JTextField 	txtMaNV, txtHoTenNV, txtPhongBan, 
						txtChucVu, txtLuongCung,
						txtNgayCongQuyDinh, txtNgayCongThucTe;
	private JLabel lblThuong, lblPhat, lblLuongPhuCap, lblLuongThucLanh;
	private JTextField txtThuong, txtPhat, txtLuongPhuCap, txtLuongThucLanh;
	private JLabel	lblKhauTru, lblTienDongBHXH, lblTienDongBHYT, lblTienDongBHTN,
					lblThueTNCN, lblTamUng;
	private JTextField 	txtKhauTru, txtTienDongBHXH, txtTienDongBHYT, txtTienDongBHTN,
						txtThueTNCN, txtTamUng;
	private JButton btnExit;
	private DefaultTableModel model;
	private JTable table;
	
	
	public GUI_QuanLyNhanVien_v1() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
//		setSize(1200, 500);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		Color title = new Color(0, 102, 204);
		Color button = new Color(0, 153, 204);
		
		
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnTitle = new JPanel();
		pnContent.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setBackground(title);
		
		lblTitle = new JLabel("Bảng danh sách thông tin nhân viên");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		
		
		pnThongTinNV = new JPanel();
		pnContent.add(pnThongTinNV, BorderLayout.CENTER);
		pnThongTinNV.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		
		pnThietLapTTNV = new JPanel();
		pnThongTinNV.add(pnThietLapTTNV);
		pnThietLapTTNV.setBorder(BorderFactory.createTitledBorder("Thiết lập thông tin nhân viên"));
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		
		lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(20);
		lblHoTenNV = new JLabel("Họ tên nhân viên: ");
		txtHoTenNV = new JTextField(20);
		lblPhongBan = new JLabel("Phòng ban: ");
		txtPhongBan = new JTextField(20);
		lblChucVu = new JLabel("Chức vụ: ");
		txtChucVu = new JTextField(20);
		lblLuongCung = new JLabel("Lương cứng: ");
		txtLuongCung = new JTextField(20);
		lblNgayCongQuyDinh = new JLabel("Ngày công quy định: ");
		txtNgayCongQuyDinh = new JTextField(20);
		lblNgayCongThucTe = new JLabel("Ngày công thực tế: ");
		txtNgayCongThucTe = new JTextField(20);
		lblThuong = new JLabel("Tiền thưởng: ");
		txtThuong = new JTextField(20);
		lblPhat = new JLabel("Tiền phạt: ");
		txtPhat = new JTextField(20);
		lblTamUng = new JLabel("Tạm ứng: ");
		txtTamUng = new JTextField(20);
		lblThueTNCN = new JLabel("Thuế TNCN: ");
		txtThueTNCN = new JTextField(20);
		lblLuongPhuCap = new JLabel("Lương phụ cấp: ");
		txtLuongPhuCap = new JTextField(20);
		lblLuongThucLanh = new JLabel("Lương thực lãnh: ");
		txtLuongThucLanh = new JTextField(20);
		
		lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblHoTenNV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhongBan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLuongCung.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgayCongQuyDinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgayCongThucTe.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTamUng.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThueTNCN.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLuongPhuCap.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLuongThucLanh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		b1.add(lblMaNV);
		b1.add(txtMaNV);
		b1.add(lblHoTenNV);
		b1.add(txtHoTenNV);
		b2.add(lblPhongBan);
		b2.add(txtPhongBan);
		b2.add(lblChucVu);
		b2.add(txtChucVu);
		b3.add(lblLuongCung);
		b3.add(txtLuongCung);
		b3.add(lblLuongPhuCap);
		b3.add(txtLuongPhuCap);
		b4.add(lblNgayCongQuyDinh);
		b4.add(txtNgayCongQuyDinh);
		b4.add(lblNgayCongThucTe);
		b4.add(txtNgayCongThucTe);
		
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
//		b.add(Box.createRigidArea(new Dimension(0, 20)));
//		b.add(b5);
//		b.add(Box.createRigidArea(new Dimension(0, 20)));
//		b.add(b6);
//		b.add(Box.createRigidArea(new Dimension(0, 20)));
//		b.add(b7);
//		b.add(Box.createRigidArea(new Dimension(0, 20)));
//		b.add(b8);
		pnThietLapTTNV.add(b);
		
		createTable();
		
		pnTacVu = new JPanel();
		pnContent.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		pnTable.setBorder(BorderFactory.createTitledBorder(null, "Bảng thông tin nhân viên"));
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
				
		model.addColumn("Mã nhân viên");
		model.addColumn("Họ tên nhân viên");
		model.addColumn("Phòng ban");
		model.addColumn("Chức vụ");
		model.addColumn("Lương cứng");
		model.addColumn("Lương phụ cấp");
		model.addColumn("Ngày công quy định");
		model.addColumn("Ngày công thực tế");
		model.addColumn("Thưởng");
		model.addColumn("Phạt");
		model.addColumn("Tạm ứng");
		model.addColumn("Thuế TNCN");
		model.addColumn("Lương thực lãnh");
//		model.addColumn("");
//		model.addColumn("");
		
		TableColumn column = new TableColumn();
		column.setPreferredWidth(1000);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(1000, 400));
		pnTable.add(scrollPane);
		pnThongTinNV.add(pnTable);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnExit)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }
		}
	}
	
	
	public static void main(String[] args) {
		new GUI_QuanLyNhanVien_v1().setVisible(true);
	}
}

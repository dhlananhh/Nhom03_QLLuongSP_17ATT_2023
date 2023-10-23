package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI_QuyDinhLuong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel 	pnContent, pnTitle, pnThietLapThongTin, pnTacVu,
					pnThietLapLuongCung, pnThietLapLuongPhuCap, 
					pnThietLapCacKhoanKhac, pnThietLapKhauTru;
	private JLabel 	lblTitle, lblMucLuongToiThieu, lblBacLuong,
					lblNgayCongQuyDinh, lblNgayCongThucTe;
	private JTextField 	txtMucLuongToiThieu, txtBacLuong,
						txtNgayCongQuyDinh, txtNgayCongThucTe;
	private JLabel	lblTienAnTrua, lblTienDiLai, lblTienDienThoai;
	private JTextField 	txtTienAnTrua, txtTienDiLai, txtTienDienThoai;
	private JLabel lblThuong, lblPhat;
	private JTextField txtThuong, txtPhat;
	private JLabel	lblKhauTru, lblTienDongBHXH, lblTienDongBHYT, lblTienDongBHTN,
					lblThueTNCN, lblTamUng;
	private JTextField 	txtKhauTru, txtTienDongBHXH, txtTienDongBHYT, txtTienDongBHTN,
						txtThueTNCN, txtTamUng;
		
	private JButton btnLuu, btnLamMoi, btnHuy, btnThoat;
	
	
	public GUI_QuyDinhLuong() {
		buildGUI();
	}
	
	
	public void buildGUI() {
		setTitle("Phần mềm quản lý lương");
		setSize(1200, 500);
//		setExtendedState(MAXIMIZED_BOTH);
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
		
		lblTitle = new JLabel("Quy định lương, bảo hiểm, thuế TNCN");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		
		
		pnThietLapThongTin = new JPanel();
		pnContent.add(pnThietLapThongTin, BorderLayout.CENTER);
		pnThietLapThongTin.setBorder(BorderFactory.createTitledBorder("Thiết lập thông tin"));
		
		pnThietLapLuongCung = new JPanel();
		pnThietLapThongTin.add(pnThietLapLuongCung);
		pnThietLapLuongCung.setBorder(BorderFactory.createTitledBorder("Thiết lập lương cứng"));
		
		Box a = Box.createVerticalBox();
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box a3 = Box.createHorizontalBox();
		
		lblMucLuongToiThieu = new JLabel("Mức lương tối thiểu: ");
		txtMucLuongToiThieu = new JTextField(20);
		lblBacLuong = new JLabel("Bậc lương: ");
		txtBacLuong = new JTextField(20);
		lblNgayCongQuyDinh = new JLabel("Ngày công quy định: ");
		txtNgayCongQuyDinh = new JTextField(20);
		lblNgayCongThucTe = new JLabel("Ngày công thực tế: ");
		txtNgayCongThucTe = new JTextField(20);
				
		lblMucLuongToiThieu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBacLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgayCongQuyDinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgayCongThucTe.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblBacLuong.setPreferredSize(lblMucLuongToiThieu.getPreferredSize());
		lblNgayCongThucTe.setPreferredSize(lblNgayCongQuyDinh.getPreferredSize());
		
		a1.add(lblMucLuongToiThieu);
		a1.add(txtMucLuongToiThieu);
		a1.add(Box.createHorizontalStrut(20));
		a1.add(lblNgayCongQuyDinh);
		a1.add(txtNgayCongQuyDinh);
		a2.add(lblBacLuong);
		a2.add(txtBacLuong);
		a2.add(Box.createHorizontalStrut(20));
		a2.add(lblNgayCongThucTe);
		a2.add(txtNgayCongThucTe);
				
		a.add(Box.createRigidArea(new Dimension(0, 20)));
		a.add(a1);
		a.add(Box.createRigidArea(new Dimension(0, 20)));
		a.add(a2);
		a.add(Box.createRigidArea(new Dimension(0, 20)));
		a.add(a3);
		pnThietLapLuongCung.add(a);
		
		
		pnThietLapLuongPhuCap = new JPanel();
		pnThietLapThongTin.add(pnThietLapLuongPhuCap);
		pnThietLapLuongPhuCap.setBorder(BorderFactory.createTitledBorder("Thiết lập lương phụ cấp"));
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		
		lblTienAnTrua = new JLabel("Tiền ăn trưa: ");
		txtTienAnTrua = new JTextField(20);
		lblTienDiLai = new JLabel("Tiền đi lại: ");
		txtTienDiLai = new JTextField(20);
		lblTienDienThoai = new JLabel("Tiền điện thoại: ");
		txtTienDienThoai = new JTextField(20);

		lblTienAnTrua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienDiLai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblTienAnTrua.setPreferredSize(lblTienDienThoai.getPreferredSize());
		lblTienDiLai.setPreferredSize(lblTienDienThoai.getPreferredSize());
		
		b1.add(lblTienAnTrua);
		b1.add(txtTienAnTrua);
		b2.add(lblTienDiLai);
		b2.add(txtTienDiLai);
		b3.add(lblTienDienThoai);
		b3.add(txtTienDienThoai);
		
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		pnThietLapLuongPhuCap.add(b);
		
		
		pnThietLapCacKhoanKhac = new JPanel();
		pnThietLapThongTin.add(pnThietLapCacKhoanKhac);
		pnThietLapCacKhoanKhac.setBorder(BorderFactory.createTitledBorder("Thiết lập các khoản khác"));
		
		Box c = Box.createVerticalBox();
		Box c1 = Box.createHorizontalBox();
		Box c2 = Box.createHorizontalBox();
		Box c3 = Box.createHorizontalBox();
		
		lblThuong = new JLabel("Tiền thưởng: ");
		txtThuong = new JTextField(20);
		lblPhat = new JLabel("Tiền phạt: ");
		txtPhat = new JTextField(20);
		lblTamUng = new JLabel("Tạm ứng: ");
		txtTamUng = new JTextField(20);
		lblThueTNCN = new JLabel("Thuế TNCN: ");
		txtThueTNCN = new JTextField(20);
		
		lblThuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTamUng.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThueTNCN.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblPhat.setPreferredSize(lblThuong.getPreferredSize());
		lblTamUng.setPreferredSize(lblThueTNCN.getPreferredSize());
		
		c1.add(lblThuong);
		c1.add(txtThuong);
		c1.add(Box.createHorizontalStrut(20));
		c1.add(lblTamUng);
		c1.add(txtTamUng);
		c2.add(lblPhat);
		c2.add(txtPhat);
		c2.add(Box.createHorizontalStrut(20));
		c2.add(lblThueTNCN);
		c2.add(txtThueTNCN);
		
		c.add(Box.createRigidArea(new Dimension(0, 20)));
		c.add(c1);
		c.add(Box.createRigidArea(new Dimension(0, 20)));
		c.add(c2);
		c.add(Box.createRigidArea(new Dimension(0, 20)));
		c.add(c3);
		pnThietLapCacKhoanKhac.add(c);
		
		
		pnThietLapKhauTru = new JPanel();
		pnThietLapThongTin.add(pnThietLapKhauTru);
		pnThietLapKhauTru.setBorder(BorderFactory.createTitledBorder("Thiết lập khấu trừ"));
		
		Box d = Box.createVerticalBox();
		Box d1 = Box.createHorizontalBox();
		Box d2 = Box.createHorizontalBox();
		Box d3 = Box.createHorizontalBox();
		
		lblTienDongBHXH = new JLabel("Tiền đóng BHXH: ");
		txtTienDongBHXH = new JTextField(20);
		lblTienDongBHYT = new JLabel("Tiền đóng BHYT: ");
		txtTienDongBHYT = new JTextField(20);
		lblTienDongBHTN = new JLabel("Tiền đóng BHTN: ");
		txtTienDongBHTN = new JTextField(20);
		
		lblTienDongBHXH.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienDongBHYT.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienDongBHTN.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		d1.add(lblTienDongBHXH);
		d1.add(txtTienDongBHXH);
		d2.add(lblTienDongBHYT);
		d2.add(txtTienDongBHYT);
		d3.add(lblTienDongBHTN);
		d3.add(txtTienDongBHTN);
		
		d.add(Box.createRigidArea(new Dimension(0, 20)));
		d.add(d1);
		d.add(Box.createRigidArea(new Dimension(0, 20)));
		d.add(d2);
		d.add(Box.createRigidArea(new Dimension(0, 20)));
		d.add(d3);
		pnThietLapKhauTru.add(d);
		
		pnTacVu = new JPanel();
		pnContent.add(pnTacVu, BorderLayout.SOUTH);
		pnTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		
		btnLuu = new JButton("Lưu");
		btnLamMoi = new JButton("Làm mới");
		btnHuy = new JButton("Hủy");
		btnThoat = new JButton("Thoát");
		
		btnLuu.setForeground(Color.WHITE);
		btnLamMoi.setForeground(Color.WHITE);
		btnHuy.setForeground(Color.WHITE);
		btnThoat.setForeground(Color.WHITE);
		
		btnLuu.setBackground(button);
		btnLamMoi.setBackground(button);
		btnHuy.setBackground(button);
		btnThoat.setBackground(button);
		
		pnTacVu.add(btnLuu);
		pnTacVu.add(btnLamMoi);
		pnTacVu.add(btnHuy);
		pnTacVu.add(btnThoat);
		
		
		btnLuu.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);
		btnThoat.addActionListener(this);
		
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThoat)) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát chương trình",
                    JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); 
            }			
		}
	}
	
	
	public static void main(String[] args) {
		new GUI_QuyDinhLuong().setVisible(true);
	}
}

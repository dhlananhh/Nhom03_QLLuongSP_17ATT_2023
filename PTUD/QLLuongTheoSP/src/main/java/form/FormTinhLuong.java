package form;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class FormTinhLuong extends JPanel {
	private static final long serialVersionUID = 1L;
	private Font BVNPro;
	private JLabel lblHeader;
	
	
	
	public FormTinhLuong() {
		setSize(new Dimension(1100, 610));
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thiết lập thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setLayout(null);
		
		//load fonts
		try {
			String fileName = "src/main/java/fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		lblHeader = new JLabel("TÍNH LƯƠNG");
		panel.add(lblHeader);
		lblHeader.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 20));
		lblHeader.setBounds(10, 51, 139, 33);
		panel.add(lblHeader);
		
		
	}
}

package gui.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.formdev.flatlaf.FlatLightLaf;


public class Dashboard extends JFrame {
	private JPanel pnContent;
	private BufferedImage image;
	
	public Dashboard() {
		//set JFrame properties
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		pnContent = new JPanel();
		
		//load image	
		try {
			image = ImageIO.read(new File("images/salary_statistics.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel bgIcon = new JLabel(new ImageIcon(image));
		
		
		//add bgIcon to pnContent
		pnContent.add(bgIcon);
		
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public static void main(String[] args) throws IOException {
		FlatLightLaf.setup();
		new Dashboard().setVisible(true);
	}
}

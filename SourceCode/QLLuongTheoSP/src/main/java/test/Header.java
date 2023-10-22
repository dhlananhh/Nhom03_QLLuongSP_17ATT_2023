package test;


import test.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;


public class Header extends JPanel {
	private ImageAvatar imageAvatar1;
    private JLabel jLabel1;
    private JLabel jLabel2;

    
    public Header() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        imageAvatar1 = new test.ImageAvatar();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        setBackground(new Color(89, 168, 255));

        imageAvatar1.setBorderSize(1);
        imageAvatar1.setBorderSpace(1);
        imageAvatar1.setGradientColor1(new Color(255, 0, 0));
        imageAvatar1.setGradientColor2(new Color(27, 0, 255));
        imageAvatar1.setImage(new ImageIcon(getClass().getResource("/icons/logoHoaPhat.png"))); 

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 15)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Phần mềm tính lương sản phẩm");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); 
        jLabel2.setForeground(new java.awt.Color(216, 216, 216));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("+855 9998881001");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
    }

}

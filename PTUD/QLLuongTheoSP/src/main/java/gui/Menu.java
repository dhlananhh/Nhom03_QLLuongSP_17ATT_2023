package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
	public static void main(String[] args) {
        // Tạo cửa sổ JFrame
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo thanh menu
        JMenuBar menuBar = new JMenuBar();

        // Tạo menu "File"
        JMenu fileMenu = new JMenu("Nhân viên");

        // Tạo các mục trong menu "File"
        JMenuItem newItem = new JMenuItem("Danh sách");
        JMenuItem openItem = new JMenuItem("Thêm mới");
        
     // Tạo thanh menu
        JMenuBar menuBar2 = new JMenuBar();

        // Tạo menu "File"
        JMenu fileMenu2 = new JMenu("Chấm công");

        // Tạo các mục trong menu "File"
        JMenuItem newItem2 = new JMenuItem("Danh sách");
        JMenuItem openItem2 = new JMenuItem("Thêm mới");
        
        
     // Tạo thanh menu
        JMenuBar menuBar3 = new JMenuBar();

        // Tạo menu "File"
        JMenu fileMenu3 = new JMenu("Tính lương");

        // Tạo các mục trong menu "File"
        JMenuItem newItem3 = new JMenuItem("Danh sách");
        JMenuItem openItem3 = new JMenuItem("Thêm mới");
        
     // Tạo menu "File"
        JMenu fileMenu4 = new JMenu("Danh mục");

        // Tạo các mục trong menu "File"
        JMenuItem newItem4 = new JMenuItem("Công đoạn");
        JMenuItem openItem4 = new JMenuItem("Phòng ban");
        JMenuItem saItem4 = new JMenuItem("Chức vụ");
        
     // Tạo menu "File"
        JMenu fileMenu5 = new JMenu("Hệ thống");

        // Tạo các mục trong menu "File"
        JMenuItem newItem5 = new JMenuItem("Đăng xuất");
        JMenuItem openItem5 = new JMenuItem("Đổi mật khẩu");
        
        

        // Thêm các mục vào menu "File"
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        
        fileMenu2.add(newItem2);
        fileMenu2.add(openItem2);
        
        fileMenu3.add(newItem3);
        fileMenu3.add(openItem3);
        
        fileMenu4.add(newItem4);
        fileMenu4.add(openItem4);
        fileMenu4.add(saItem4);
        
        fileMenu5.add(newItem5);
        fileMenu5.add(openItem5);
        
        fileMenu.addSeparator(); // Thêm đường gạch ngang
        

        
        

        // Thêm menu "File" vào thanh menu
        menuBar.add(fileMenu);
        menuBar.add(fileMenu2);
        menuBar.add(fileMenu3);
        menuBar.add(fileMenu4);
        menuBar.add(fileMenu5);

        // Đặt thanh menu cho JFrame
        frame.setJMenuBar(menuBar);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}

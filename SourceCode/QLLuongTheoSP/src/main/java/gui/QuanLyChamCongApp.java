package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyChamCongApp extends JFrame {
    private JTextField maNVField, hoTenField;
    private JButton updateButton;
    private JTable table;

    public QuanLyChamCongApp() {
        // Thiết lập cửa sổ
        setTitle("Quản lý chấm công nhân viên");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Tạo panel chứa thông tin nhân viên
        JPanel infoPanel = new JPanel(new GridLayout(1, 4));
        JLabel maNVLabel = new JLabel("Mã NV:");
        maNVField = new JTextField();
        JLabel hoTenLabel = new JLabel("Họ tên:");
        hoTenField = new JTextField();
        infoPanel.add(maNVLabel);
        infoPanel.add(maNVField);
        infoPanel.add(hoTenLabel);
        infoPanel.add(hoTenField);

        // Tạo bảng chấm công
        String[] columnNames = new String[32];
        columnNames[0] = "Mã NV";
        columnNames[1] = "Họ tên";
        for (int i = 2; i <= 31; i++) {
            columnNames[i] = Integer.toString(i - 1);
        }
        Object[][] data = new Object[1][32]; // Dữ liệu mẫu, bạn có thể sửa đổi dựa trên nhu cầu
        table = new JTable(data, columnNames);

        // Tạo panel chứa bảng chấm công
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Tạo button cập nhật chấm công
        updateButton = new JButton("Cập nhật chấm công");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện cập nhật chấm công ở đây
                // Bạn có thể lấy thông tin từ các trường mã NV, họ tên và bảng chấm công
                String maNV = maNVField.getText();
                String hoTen = hoTenField.getText();
                // Lấy dữ liệu từ bảng chấm công và thực hiện xử lý
                for (int col = 2; col < table.getColumnCount(); col++) {
                    Object ngayCong = table.getValueAt(0, col);
                    // Xử lý dữ liệu ngày công ở đây (cập nhật vào cơ sở dữ liệu hoặc tệp tin)
                    // Ví dụ: System.out.println("Ngày " + (col - 1) + ": " + ngayCong);
                }
            }
        });

        // Đặt các thành phần vào panel chính
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(updateButton, BorderLayout.SOUTH);

        // Thêm panel chính vào cửa sổ
        add(mainPanel);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChamCongApp();
            }
        });
    }
}

package demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JComboBoxEventExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JComboBox Event Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        String[] options = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        
        // Add an ActionListener to the JComboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This method is called when an item is selected
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                String selectedOption = (String) source.getSelectedItem();
                System.out.println("Selected: " + selectedOption);
            }
        });

        frame.add(comboBox);
        frame.setVisible(true);
    }
}


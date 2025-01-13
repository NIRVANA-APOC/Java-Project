package project.animation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

public class MyFrame extends JFrame {

    MyPanel panel;

    public MyFrame() {
        panel = new MyPanel();
        panel.setOpaque(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

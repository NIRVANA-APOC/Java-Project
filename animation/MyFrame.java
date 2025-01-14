package project.animation;

import javax.swing.*;

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

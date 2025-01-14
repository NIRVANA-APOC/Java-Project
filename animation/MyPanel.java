package project.animation;
import project.utils.Map;
import project.utils.Shape.Point;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MyPanel extends JPanel implements ActionListener {

    public final Map map;
    private final Timer timer;
    private final int timeTick = 50;

    public MyPanel() {
        super();

        this.map = new Map();
        var bot = map.robots.getFirst();
        bot.setVelocity(8);
        var outArrow = map.outArrows.getFirst();
        bot.moveTo(outArrow.getPosition(), map);
        var plug = map.chargePlug.getFirst();
        bot.moveTo(plug.getPosition(), map);
        int ROWS = map.ROWS;
        int COLS = map.COLS;
        int CELL_SIZE = map.CELL_SIZE;
        this.setPreferredSize(new Dimension((COLS + 2) * CELL_SIZE, (ROWS + 2) * CELL_SIZE));
        this.setBackground(Color.lightGray);

        timer = new Timer(timeTick, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        map.draw(g);

        Toolkit.getDefaultToolkit().sync(); // for Linux user
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.map.move();
        repaint();
    }

    public static void main(String[] args) {
        var panel = new MyPanel();
    }

}

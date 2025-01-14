package project.animation;
import project.Item.Map;
import project.Item.Robot.C;
import project.Item.Robot.Robot;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    public final Map map;
    private final Timer timer;
    private final int timeTick = 50;
    private final int WIDTH;
    private final int HEIGHT;

    JTextArea status;

    public MyPanel() {
        super();
        initStatus();
        this.map = new Map();
//        var bot = map.robots.getFirst();
//        bot.setVelocity(8);
//        var outArrow = map.outArrows.getFirst();
//        bot.moveTo(outArrow.getPosition(), map);
//        var plug = map.chargePlug.getFirst();
//        bot.moveTo(plug.getPosition(), map);
        int ROWS = map.ROWS;
        int COLS = map.COLS;
        int CELL_SIZE = map.CELL_SIZE;
        this.WIDTH = (COLS + 2) * CELL_SIZE;
        this.HEIGHT = (ROWS + 2) * CELL_SIZE;
        this.setPreferredSize(new Dimension(WIDTH + 400, HEIGHT));
        this.setBackground(Color.lightGray);

        timer = new Timer(timeTick, this);
        timer.start();
    }

    private void initStatus() {
        status = new JTextArea();
        status.setFont(new Font("Serif", Font.BOLD, 16));
        status.setSize(400, HEIGHT);
        status.setEditable(false);
        status.setLineWrap(true);
        status.setWrapStyleWord(true);
    }

    private void updateStatus() {
        status.setText("");
        for (Robot bot: map.robots) {
            status.append("bot-%s: \n".formatted(bot.id));
            status.append("\tBattery-Level: %d\n".formatted(bot.batteryLevel));
            status.append("\n");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateStatus();
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

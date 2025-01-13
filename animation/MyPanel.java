package project.animation;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import javax.swing.Timer;
import javax.xml.stream.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MyPanel extends JPanel implements ActionListener {

    private static final String configFilePath = "animation/config.xml";

    private int ROWS = 20;
    private int COLS = 30;
    private int CELL_SIZE = 30;

    private int x1, x2, x_step, y1, y2, y_step;

    private Timer timer;
    private final HashMap<String, Item> items;

    public MyPanel() {
        super();
        this.setPreferredSize(new Dimension((COLS + 2) * CELL_SIZE, (ROWS + 2) * CELL_SIZE));
        this.setBackground(Color.lightGray);
        this.items = new HashMap<>();
        List<Item> itemList = new ArrayList<>();
        Robot botA = new Robot(
                "a",
                new MovingItem.Velocity(1, 0),
                getPoint(4, 5),
                0
        );
        addItem("a", botA);
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(configFilePath));

            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT){
                    if (Objects.equals(reader.getLocalName(), "Global")) {
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            if (Objects.equals(reader.getAttributeLocalName(i), "ROWS")) {
                                this.ROWS = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "COLS")) {
                                this.COLS = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "CELL_SIZE")) {
                                this.CELL_SIZE = Integer.parseInt(reader.getAttributeValue(i));
                            }
                        }
                    }
                    else if (Objects.equals(reader.getLocalName(), "Items")) {
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            if (reader.getAttributeLocalName(i).startsWith("arrow")) {
                                Scanner sc = new Scanner(reader.getAttributeValue(i));
                                System.out.println(reader.getAttributeValue(i));
                                itemList.add(new Item(getPoint(sc.nextInt(), sc.nextInt()), Item.Type.Arrow));
                            }
                            if (reader.getAttributeLocalName(i).startsWith("plug")) {
                                Scanner sc = new Scanner(reader.getAttributeValue(i));
                                itemList.add(new Item(getPoint(sc.nextInt(), sc.nextInt()), Item.Type.Plug));
                            }
                        }
                    }
                    else if (Objects.equals(reader.getLocalName(), "Warehouse")) {
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            if (Objects.equals(reader.getAttributeLocalName(i), "x1")) {
                                x1 = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "x2")) {
                                x2 = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "x-step")) {
                                x_step = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "y1")) {
                                y1 = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "y2")) {
                                y2 = Integer.parseInt(reader.getAttributeValue(i));
                            }
                            if (Objects.equals(reader.getAttributeLocalName(i), "y-step")) {
                                y_step = Integer.parseInt(reader.getAttributeValue(i));
                            }
                        }
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
    }
        for (int i = 0; i < itemList.size(); i++) {
            addItem("item-%d".formatted(i), itemList.get(i));
        }
        timer = new Timer(50, this);
        timer.start();
    }

    private Point getPoint(int x, int y) {
        return new Point(x * CELL_SIZE, y * CELL_SIZE);
    }

    private void paintPoint(Graphics g, Point p) {
        g.setColor(Color.BLACK);
        g.drawLine(p.x, p.y, p.x + CELL_SIZE, p.y);
        g.drawLine(p.x, p.y, p.x, p.y + CELL_SIZE);
        g.drawLine(p.x, p.y + CELL_SIZE, p.x + CELL_SIZE, p.y + CELL_SIZE);
        g.drawLine(p.x + CELL_SIZE, p.y, p.x + CELL_SIZE, p.y + CELL_SIZE);
    }

    private void paintRectangle(Graphics g, Point p1, Point p2) {
        int x_min = Integer.min(p1.x, p2.x);
        int x_max = Integer.max(p1.x, p2.x);
        int y_min = Integer.min(p1.y, p2.y);
        int y_max = Integer.max(p1.y, p2.y);

        for (int y = y_min; y <= y_max; y += CELL_SIZE) {
            g.drawLine(x_min, y, x_max, y);
        }

        for (int x = x_min; x <= x_max; x += CELL_SIZE) {
            g.drawLine(x, y_min, x, y_max);
        }
    }

    private void paintTable(Graphics g) {
        paintTable(g, 2, 28, 2, 2, 19, 5);
    }

    private void paintTable(Graphics g, int x1, int x2, int x_step, int y1, int y2, int y_step) {
        // 19 x 28  (5, 2)
        for (int i = x1; i <= x2; i += x_step + 1) {
            for (int j = y1; j <= y2; j += y_step + 1) {
                this.paintRectangle(g, getPoint(i, j), getPoint(i + 2, j + 5));
            }
        }
    }

    private void paintItems(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.items.forEach((id, item) -> {
            g2d.drawImage(item.getImage(), item.location.x, item.location.y, null);
        });
    }

    public void addItem(String id, Item item) {
        this.items.put(id, item);
    }

    public static void readConfig() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(configFilePath));

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    System.out.println("Element: " + reader.getLocalName());
                    int cnt = reader.getAttributeCount();
                    System.out.println(cnt);
                    for (int i = 0; i < cnt; i++) {
                        System.out.printf("Name: %s\tValue: %s%n", reader.getAttributeLocalName(i), reader.getAttributeValue(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeConfig() {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(configFilePath));

            writer.writeStartDocument();
            writer.writeStartElement("root");

            writer.writeStartElement("Global");
            writer.writeAttribute("ROWS", "20");
            writer.writeAttribute("COLS", "30");
            writer.writeAttribute("CELL_SIZE", "30");
            writer.writeEndElement();

            writer.writeStartElement("Items");
            writer.writeAttribute("arrow-0", "0 4");
            writer.writeAttribute("arrow-1", "0 7");
            writer.writeAttribute("arrow-2", "0 10");
            writer.writeAttribute("arrow-3", "0 13");

            writer.writeAttribute("arrow-4", "29 5");
            writer.writeAttribute("arrow-5", "29 9");
            writer.writeAttribute("arrow-6", "29 13");
            writer.writeAttribute("arrow-7", "29 17");

            writer.writeAttribute("plug-8", "2 20");
            writer.writeAttribute("plug-9", "4 20");
            writer.writeAttribute("plug-10", "6 20");
            writer.writeAttribute("plug-11", "8 20");
            writer.writeEndElement();

            writer.writeStartElement("Warehouse");
            writer.writeAttribute("x1", "2");
            writer.writeAttribute("x2", "28");
            writer.writeAttribute("x-step", "2");
            writer.writeAttribute("y1", "2");
            writer.writeAttribute("y2", "19");
            writer.writeAttribute("y-step", "5");
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            System.out.println("XML file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制表格
        this.paintTable(g, x1, x2, x_step, y1, y2, y_step);
        // 绘制item
        this.paintItems(g);

        Toolkit.getDefaultToolkit().sync(); // for Linux user
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        items.forEach((id, item) -> item.move());
        repaint();
    }

    public static void main(String[] args) {
        writeConfig();
        readConfig();
    }

}

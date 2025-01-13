package project.animation;

import javax.swing.*;
import java.awt.*;

public class Item {

    private Image image;
    public Point location;

    public enum Type{
        Arrow, Plug
    }

    public Item() {
        this(0, 0, "animation/letter-a.png");
    }

    public Item(int x, int y, Image image) {
        this.image = image;
        this.location = new Point(x, y);
    }

    public Item(int x, int y, String imageUrl) {
        this(x, y, new ImageIcon(imageUrl).getImage());
    }

    public Item(int x, int y, Type type) {
        this(new Point(x, y), type);
    }

    public Item(Point location, Image image) {
        this.image = image;
        this.location = location;
    }

    public Item(Point location, String imageUrl) {
        this(location, new ImageIcon(imageUrl).getImage());
    }

    public Item(Point location, Type type) {
        this(location, switch (type) {
            case Plug -> "images/plug-24.png";
            case Arrow -> "images/arrow-24.png";
        });
    }



    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void move() {}

}

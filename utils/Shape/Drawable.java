package project.utils.Shape;

import javax.swing.*;
import java.awt.*;

public class Drawable extends Point {

    protected String imageUrl;
    private Image image;

    public Drawable() {
        super();
        this.imageUrl = null;
        this.image = null;
    }

    public Drawable(String imageUrl) {
        super();
        this.imageUrl = imageUrl;
        this.image = new ImageIcon(imageUrl).getImage();
    }

    public Drawable(int x, int y) {
        super(x, y);
        this.imageUrl = null;
        this.image = null;
    }

    public Drawable(int x, int y, String imageUrl) {
        super(x, y);
        this.imageUrl = imageUrl;
        this.image = new ImageIcon(imageUrl).getImage();
    }

    public Drawable(int x, int y, int cellSize) {
        super(x, y, cellSize);
        this.imageUrl = null;
        this.image = null;
    }

    public Drawable(int x, int y, String imageUrl, int cellSize) {
        super(x, y, cellSize);
        this.imageUrl = imageUrl;
        this.image = new ImageIcon(imageUrl).getImage();
    }

    public Image getImage() {
        return this.image;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void draw(Graphics g) {
        if (this.imageUrl == null) {
            super.transDraw(g);
        }
        else {
            Point p = getRelativePosition();
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(getImage(), p.y, p.x, null);
        }
    }
}

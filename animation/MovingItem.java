package project.animation;


import project.Item.Shape.Point;

public class MovingItem extends Item{

    public static class Velocity extends Point {
        public Velocity() {
            super();
        }
        public Velocity(int x, int y) {
            super(x, y);
        }
    }

    private Velocity velocity;

    public MovingItem() {
        super();
        this.velocity = new Velocity();
    }

    public MovingItem(int xVelocity, int yVelocity, int x, int y, String imageUrl) {
        super(x, y, imageUrl);
        this.velocity = new Velocity(xVelocity, yVelocity);
    }

    public MovingItem(Velocity velocity, Point location, String imageUrl) {
        super(location, imageUrl);
        this.velocity = velocity;
    }

    @Override
    public void move() {
        this.location.add(this.velocity);
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}

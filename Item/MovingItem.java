package project.Item;

import project.Item.Shape.Point;

import java.util.ArrayList;

public class MovingItem extends Item {

    enum State {
        Stop, Move
    }

    private int V = 10;
    private int velocity;
    private State state;
    private ArrayList<Point> movePath;
    private int timeTick = 0;

    public MovingItem(Point location, ItemType type) {
        super(location, type);
        this.state = State.Stop;
    }

    public MovingItem(Point location, ItemType type, String imageUrl) {
        super(location, type, imageUrl);
        this.state = State.Stop;
    }

    public MovingItem(Point location, ItemType type, int cellSize) {
        super(location, type, cellSize);
        this.state = State.Stop;
    }

    public MovingItem(Point location, ItemType type, String imageUrl, int velocity) {
        super(location, type, imageUrl);
        this.state = State.Stop;
        this.velocity = V - velocity;
    }

    public MovingItem(Point location, ItemType type, int cellSize, int velocity) {
        super(location, type, cellSize);
        this.state = State.Stop;
        this.velocity = V - velocity;
    }

    public int getVelocity() {
        return V - this.velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = V - velocity;
    }

    public void setStateStop() {
        this.state = State.Stop;
    }

    public void setStateMove() {
        this.state = State.Move;
    }

    public void setMovePath(ArrayList<Point> movePath) {
        this.movePath = movePath;
    }

    public void moveTo(Point to, Map map) {
        this.state = State.Move;
        this.movePath = map.findPath(new Point(x, y), to);
    }

    @Override
    public void move() {
        if (timeTick != 0 && velocity != 0 && timeTick % velocity == 0) {
            if (!this.movePath.isEmpty()){
                Point nextPostion = this.movePath.removeFirst();
                this.x = nextPostion.x;
                this.y = nextPostion.y;
            }
        }
        timeTick++;
    }
}



import java.awt.*;

public class Pancake implements Comparable<Pancake>{
    private Color color;
    private int size;
    private boolean selected;
    public static int WORLD_W, WORLD_H;
    private int number;

    public Pancake(int size, Color color, int number)
    {
        this.size = size;
        this.color = color;
        this.number = number;
    }

    public int compareTo(Pancake other)
    {
        return (this.size - other.size);
    }

    public int getSize()
    {
        return size;
    }

    public void highlight(boolean selected)
    {
        this.selected = selected;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect((WORLD_W/2)-(size/2),800-(40*number),size,40);
    }

    public int getNumber()
    {
        return number;
    }

    public Color getColor()
    {
        return color;
    }
}

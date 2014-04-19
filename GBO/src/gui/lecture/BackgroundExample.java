package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class BackgroundExample extends JPanel
{
    private final static Color[] COLORS =
    {
        Color.BLACK, Color.RED, Color.BLUE,
        Color.GREEN, Color.PINK,
        Color.YELLOW, Color.WHITE
    };

    private int colorIndex = 0;

    public void paintComponent(Graphics g)
    {
        g.setColor(COLORS[colorIndex]);
        colorIndex++;
        if(colorIndex == COLORS.length)
        {
            colorIndex = 0;
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String argv[])
    {
        JFrame f = new JFrame("Hintergrund");
        BackgroundExample back = new BackgroundExample();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(back);
        f.setLocation(20, 20);
        f.setSize(400, 400);
        f.setVisible(true);
    }
}

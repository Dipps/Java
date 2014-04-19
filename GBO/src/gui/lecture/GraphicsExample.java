package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class GraphicsExample extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(10, 50, 400, 50);
        g.drawLine(50, 10, 50, 302);
        g.drawString("(50,50)", 50 + 5, 50 - 5);
        g.drawLine(48, 300, 52, 300);
        g.drawString("(50,300)", 50 + 5, 300 - 5);
        g.drawLine(200, 48, 200, 52);
        g.drawString("(200,50)", 200 + 5, 50 - 5);
        g.drawLine(350, 48, 350, 52);
        g.drawString("(350,50)", 350 + 5, 50 - 5);

        g.setColor(Color.BLUE);
        g.drawRect(100, 100, 70, 30);
        g.drawString("drawRect:", 100, 100 - 5);

        g.fillRect(100, 170, 70, 30);
        g.drawString("fillRect:", 100, 170 - 5);

        g.fillRoundRect(100, 240, 70, 30, 20, 15);
        g.drawString("fillRoundRect:", 100, 240 - 5);

        g.setColor(Color.RED);
        g.draw3DRect(200, 100, 70, 30, true);
        g.drawString("draw3DRect:", 200, 100 - 5);

        g.fill3DRect(200, 170, 70, 30, true);
        g.drawString("fill3DRect:", 200, 170 - 5);

        g.setColor(Color.GRAY);
        g.fill3DRect(200, 240, 70, 30, true);
        g.fill3DRect(201, 241, 68, 28, true);
        g.fill3DRect(202, 242, 66, 26, true);

        g.setColor(Color.ORANGE);
        g.drawOval(300, 100, 50, 30);
        g.drawString("drawOval:", 300, 100 - 5);

        g.setColor(new Color(0, 180, 0));
        g.fillOval(300, 170, 50, 100);
        g.drawString("fillOval:", 300, 170 - 5);
    }

    public static void main(String args[])
    {
        JFrame f = new JFrame("Grafik-Demo");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GraphicsExample demo = new GraphicsExample();
        f.add(demo);
        f.setLocation(100, 100);
        f.setSize(420, 400);
        f.setVisible(true);
    }
}

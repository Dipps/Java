package gui.lecture;

import java.awt.*;

import javax.swing.*;

public class HelloExample extends JPanel
{
    private int times;

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);

        times++;
        String s = "Hello World (" + times + ")";
        g.drawString(s, 10, 20);
        System.out.println(s);
    }

    public static void main(String argv[])
    {
        JFrame f = new JFrame("Hello World");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        HelloExample hello = new HelloExample();
        f.add(hello);
        f.setLocation(200, 200);
        f.setSize(200, 200);
        f.setVisible(true);
    }
}

package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CirclePanel extends JPanel
{
    private Color color;

    public void setColor(Color c)
    {
        color = c;
        repaint();
        /*
        int diameter = Math.min(getWidth(), getHeight()) - 6;
        repaint(3 + diameter/2 - 10, 3 + diameter/2 - 10, 20, 20);
        */
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(color);
        int diameter = Math.min(getWidth(), getHeight()) - 6;
        g.fillOval(3, 3, diameter, diameter);
    }
}

public class RepaintExample extends JFrame implements ActionListener
{
    private CirclePanel circlePanel;

    public RepaintExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel total = new JPanel(new BorderLayout());

        circlePanel = new CirclePanel();
        circlePanel.setColor(Color.BLACK);
        total.add(circlePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        JButton red = new JButton("Rot");
        red.setBackground(Color.RED);
        red.addActionListener(this);
        buttonPanel.add(red);
        JButton green = new JButton("Grün");
        green.setBackground(Color.GREEN);
        green.addActionListener(this);
        buttonPanel.add(green);
        total.add(buttonPanel, BorderLayout.SOUTH);

        add(total);
        setLocation(200, 200);
        setSize(300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("Rot"))
        {
            circlePanel.setColor(Color.RED);
        }
        else
        // if(e.equals("Grün"))
        {
            circlePanel.setColor(Color.GREEN);
        }
    }

    public static void main(String argv[])
    {
        new RepaintExample("Farbenwahl");
    }
}

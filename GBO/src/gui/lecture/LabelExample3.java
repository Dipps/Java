package gui.lecture;

import java.awt.*; // Layouts
import javax.swing.*;

class MyOtherFrame extends JFrame
{
    public MyOtherFrame(String title, String[] labelText,
                        int x, int y)
    {
        super(title);
        for(int i = 0; i < labelText.length; i++)
        {
            add(new JLabel(labelText[i]));
        }
        setLayout(new FlowLayout());
        //setLayout(new GridLayout(2, 0));
        setLocation(x, y);
        setSize(400, 90);
        setVisible(true);
    }
}

public class LabelExample3
{
    public static void main(String[] args)
    {
        new MyOtherFrame("Beispiel für Label", args, 100, 100);
        new MyOtherFrame("Anderes Beispiel für Label", args, 100, 200);
    }
}

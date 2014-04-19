package gui.lecture;

import javax.swing.*;

public class ButtonExample9
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ButtonExample7();
            }
        });
    }
}
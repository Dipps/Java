package gui.lecture;

import javax.swing.*;

class MyRunnable implements Runnable
{
    public void run()
    {
        new ButtonExample7();
    }
}

public class ButtonExample8
{
    
    public static void main(String[] args)
    {
        MyRunnable myRunnable = new MyRunnable();
        SwingUtilities.invokeLater(myRunnable);
    }
}
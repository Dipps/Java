package gui.lecture;

import javax.swing.*;

public class LookAndFeelExample
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
            for(int i = 0; i < lafs.length; i++)
            {
                UIManager.setLookAndFeel(lafs[i].getClassName());
                JFrame frame = new ButtonExample5();
                frame.setTitle("Beispiel für "
                               + lafs[i].getName() + "-L&F <"
                               + lafs[i].getClassName() + ">");
                frame.setLocation(100, 100 + i * 110);
                frame.setSize(400, 100);
                frame.setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

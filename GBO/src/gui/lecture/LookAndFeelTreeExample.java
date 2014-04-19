package gui.lecture;

import javax.swing.*;

public class LookAndFeelTreeExample
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.LookAndFeelInfo[] lafs = UIManager
                                                        .getInstalledLookAndFeels();
            for(int i = 0; i < lafs.length; i++)
            {
                UIManager.setLookAndFeel(lafs[i].getClassName());
                JFrame frame = new TreeExample("Beispiel für "
                                               + lafs[i].getName() + "-L&F <"
                                               + lafs[i].getClassName() + ">");
                frame.setLocation(100 + i * 220, 100);
                frame.setSize(200, 600);
                frame.setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

package gui.lecture;

import javax.swing.*;

public class AppletExample extends JApplet
{
    private JTextArea ta;

    private int initCount = 0;
    private int startCount = 0; 
    private int stopCount = 0;
    private int destroyCount = 0;

    public void init()
    {
        initCount++;
        String s = "init(" + initCount + ") <" + 
                   Thread.currentThread().getName() + 
                   ">\n";
        ta = new JTextArea(s, 5, 40);
        JScrollPane sp = new JScrollPane(ta);
        add(sp);
        System.out.println(s);
    }

    public void start()
    {
        startCount++;
        String s = "start (" + startCount + ") <" +
                   Thread.currentThread().getName() + 
                   ">\n";
        ta.append(s);
        System.out.println(s);
    }

    public void stop()
    {
        stopCount++;
        String s = "stop (" + stopCount + ") <" +
                   Thread.currentThread().getName() + 
                   ">\n";
        ta.append(s);
        System.out.println(s);
    }

    public void destroy()
    {
        destroyCount++;
        String s = "destroy (" + destroyCount + ") <" +
                   Thread.currentThread().getName() + 
                   ">\n";
        ta.append(s);
        System.out.println(s);
    }
}

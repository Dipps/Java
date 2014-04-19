package gui.lecture;

import java.awt.*;
import javax.swing.*;

public class FontExample extends JPanel
{
    private static final String STRING1 = "Für mich soll's ";
    private static final String STRING2 = "rote Rosen ";
    private static final String STRING3 = "regnen.";

    private Font ft, fti, fh, fc;
    private FontMetrics fmt, fmti;

    private void loadFonts(Graphics g)
    {
        ft = new Font("TimesRoman", Font.PLAIN, 17);
        fti = new Font("TimesRoman", Font.ITALIC, 17);
        fh = new Font("Helvetica", Font.PLAIN, 20);
        fc = new Font("Courier", Font.PLAIN, 14);
        fmt = g.getFontMetrics(ft);
        fmti = g.getFontMetrics(fti);
    }

    public void paintComponent(Graphics g)
    {
        if(fh == null)
        {
            loadFonts(g);
        }

        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(ft);
        g.drawString(STRING1 + STRING2 + STRING3, 20, 50);
        g.setFont(fh);
        g.drawString(STRING1 + STRING2 + STRING3, 20, 100);
        g.setFont(fc);
        g.drawString(STRING1 + STRING2 + STRING3, 20, 150);

        /* Schriftarten mischen, String zentrieren */
        int w1 = fmt.stringWidth(STRING1);
        int w2 = fmti.stringWidth(STRING2);
        int w3 = fmt.stringWidth(STRING3);
        int x0 = (getWidth() - (w1 + w2 + w3)) / 2;
        g.setFont(ft);
        g.drawString(STRING1, x0, 200);
        g.setFont(fti);
        g.drawString(STRING2, x0 + w1, 200);
        g.setFont(ft);
        g.drawString(STRING3, x0 + w1 + w2, 200);
    }

    public static void main(String args[])
    {
        JFrame f = new JFrame("Font-Demo");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        FontExample demo = new FontExample();
        f.add(demo);
        f.setLocation(100, 100);
        f.setSize(360, 270);
        f.setVisible(true);
    }
}

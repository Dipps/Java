package gui.lecture;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Graphics2DExample extends JPanel
{
    private final BasicStroke stroke = new BasicStroke(2);

    private final BasicStroke wideStroke = new BasicStroke(10);

    private final float dash[] =
    { 20, 5 };

    private final BasicStroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dash, 0);

    @Override
    public void paintComponent(final Graphics g)
    {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;

        // g2d.translate(-19, -19);
        // g2d.translate(100, 0);
        // g2d.scale(2, 2);
        // g2d.scale(0.5, 0.5);
        // g2d.scale(1, 0.5);
        // g2d.scale(0.5, 1);
        // g2d.rotate(Math.PI/4);
        // g2d.rotate(Math.PI/4, 250, 250);
        // g2d.shear(0.5, 0);
        // g2d.shear(0, 0.5);
        // g2d.shear(0.5, 0.5);

        // g2d.rotate(Math.PI/4, 120, 120);
        int x = 20, y = 20;
        drawExample(g, x, y);

        // g2d.rotate(-Math.PI/4, 120, 120);
        // g2d.rotate(Math.PI/4, 350, 120);
        x += 230;
        g2d.setStroke(stroke);
        drawExample(g, x, y);
        //
        // g2d.rotate(-Math.PI / 4, 350, 120);
        // g2d.rotate(Math.PI / 4, 120, 350);
        x -= 230;
        y += 230;
        g2d.setStroke(wideStroke);
        drawExample(g, x, y);

        // g2d.rotate(-Math.PI/4, 120, 350);
        // g2d.rotate(Math.PI/4, 350, 350);
        x += 230;
        g2d.setStroke(dashed);
        drawExample(g, x, y);
    }

    private void drawExample(final Graphics g, final int x, final int y)
    {
        g.drawRect(x, y, 200, 200);
        g.drawOval(x + 10, y + 10, 180, 180);
        g.drawString("Beispieltext", x, y + 220);
    }

    public static void main(final String argv[])
    {
        final JFrame f = new JFrame("Linien");
        final Graphics2DExample example = new Graphics2DExample();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(example);
        f.setLocation(200, 200);
        f.setSize(500, 520);
        f.setVisible(true);
    }
}

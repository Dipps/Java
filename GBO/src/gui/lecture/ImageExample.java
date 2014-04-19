package gui.lecture;

import java.awt.*;

import javax.swing.*;

public class ImageExample extends JPanel
{
    private Image image;
    
    public ImageExample(Image image)
    {
        this.image = image;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        //g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //g.drawLine(0, 0, getWidth(), getHeight());
        //g.drawLine(0, getHeight(), getWidth(), 0);
        //g.fillOval(10, 10, 10, 10);
    }

    public static void main(String args[])
    {
        JFrame f = new JFrame("Bild");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("EmmlerWeinberge.png");

        MediaTracker tracker = new MediaTracker(f);
        tracker.addImage(image, 0);
        try
        {
            tracker.waitForID(0);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
        ImageExample imagePanel = new ImageExample(image);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.add(imagePanel);
        f.setLocation(200, 200);
        f.setSize(300, 300);
        f.setVisible(true);
    }
}

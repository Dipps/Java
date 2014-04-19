package gui.lecture;

import javax.swing.*;
import javax.swing.event.*;

public class SliderExample extends JFrame implements ChangeListener
{
    public SliderExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JSlider slider = new JSlider(0, 100, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.addChangeListener(this);
        add(slider);
        setSize(300, 100);
        setVisible(true);
    }

    public void stateChanged(ChangeEvent evt)
    {
        JSlider slider = (JSlider) evt.getSource();
        int value = slider.getValue();
        System.out.println("neuer Wert: " + value);
    }

    public static void main(String[] args)
    {
        new SliderExample("Beispiel für Slider");
    }
}

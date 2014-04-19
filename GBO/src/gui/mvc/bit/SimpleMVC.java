package gui.mvc.bit;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimpleMVC extends JFrame
{
    public SimpleMVC()
    {
        super("Bit View");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final JPanel panel = new JPanel(new GridLayout(0, 1));
        final BitModel model = new BitModel(16);

        for (int i = 0; i < 2; i++)
        {
            final DecimalView decimalView = new DecimalView(model);
            model.addModelListener(decimalView);
            panel.add(decimalView);
        }
        for (int i = 0; i < 2; i++)
        {
            final BinaryView binaryView = new BinaryView(model);
            model.addModelListener(binaryView);
            panel.add(binaryView);
        }
        for (int i = 0; i < 2; i++)
        {
            final ControlView controlView = new ControlView(model);
            model.addModelListener(controlView);
            panel.add(controlView);
        }

        add(panel);
        pack();
        setVisible(true);
    }

    public static void main(final String[] args)
    {
        new SimpleMVC();
    }
}

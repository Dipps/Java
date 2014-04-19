package gui.state.phone;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PhoneFrame extends JFrame
{
    private final PhoneModel model;

    public PhoneFrame()
    {
        this(null);
    }

    public PhoneFrame(final String[] book)
    {
        super("Telefon");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new PhoneModel(book);
        final TextView tv = new TextView();
        model.addPhoneListener(tv);
        final PhoneController pc = new PhoneController(model);

        add(tv, BorderLayout.NORTH);
        add(pc, BorderLayout.CENTER);

        pack();
        setVisible(true);

    }

    public static void main(final String[] args)
    {
        final String[] book =
        { "12345", "54321", "987456", "321654", "1587964" };
        // new PhoneFrame();
        new PhoneFrame(book);
    }

}

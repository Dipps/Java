package gui.composite;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ItemDialog extends JDialog implements ActionListener
{
    private final DPCTreeModel model;

    private final JTextField addItemName;

    private final JFormattedTextField addItemWeight;

    private final JTextField addItemDescription;

    private final Container selected;

    public ItemDialog(final JFrame owner, final boolean modal, final DPCTreeModel model, final Container selected)
    {
        super(owner, "Gegenstand hinzufügen", modal);
        setName("addItemDialog");
        setLayout(new GridLayout(0, 2));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.model = model;
        this.selected = selected;

        final JLabel name = new JLabel("Name: ");

        addItemName = new JTextField();
        addItemName.setName("addItemName");

        final JLabel gewicht = new JLabel("Gewicht: ");

        addItemWeight = new JFormattedTextField(new Integer(0));
        addItemWeight.setName("addItemWeight");

        final JLabel description = new JLabel("Beschreibung: ");

        addItemDescription = new JTextField();
        addItemDescription.setName("addItemDescription");

        final JButton addButton = new JButton("hinzufügen");
        addButton.setName("addItemBtnAdd");
        addButton.addActionListener(this);

        final JButton cancel = new JButton("Abbrechen");
        cancel.addActionListener(this);

        add(name);
        add(addItemName);
        add(gewicht);
        add(addItemWeight);
        add(description);
        add(addItemDescription);
        add(addButton);
        add(cancel);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        // TODO Auto-generated method stub
        final String s = e.getActionCommand();

        if (s.equals("hinzufügen"))
        {
            final Component child = new PrimitiveElement(addItemName.getText(), (int) addItemWeight.getValue(), addItemDescription.getText());
            model.add(selected, child);
            dispose();
        }
        else if (s.equals("Abbrechen"))
        {
            dispose();
        }
        else
        {
            throw new RuntimeException("Button nicht vorhanden");
        }

    }
}

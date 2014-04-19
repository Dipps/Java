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

public class ContainerDialog extends JDialog implements ActionListener
{
    private final DPCTreeModel model;

    private final JTextField addContainerName;

    private final JFormattedTextField addContainerWeight;

    private final Container selected;

    public ContainerDialog(final JFrame owner, final boolean modal, final DPCTreeModel model, final Container selected)
    {
        super(owner, "Schachtel hinzufügen", modal);
        setName("addContainerDialog");
        setLayout(new GridLayout(0, 2));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.model = model;
        this.selected = selected;

        final JLabel name = new JLabel("Name: ");

        addContainerName = new JTextField();
        addContainerName.setName("addContainerName");

        final JLabel gewicht = new JLabel("Gewicht: ");

        addContainerWeight = new JFormattedTextField(new Integer(0));
        addContainerWeight.setName("addContainerWeight");

        final JButton addButton = new JButton("hinzufügen");
        addButton.setName("addContainerBtnAdd");
        addButton.addActionListener(this);

        final JButton cancel = new JButton("Abbrechen");
        cancel.addActionListener(this);

        add(name);
        add(addContainerName);
        add(gewicht);
        add(addContainerWeight);
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
            final Component child = new Container(addContainerName.getText(), (int) addContainerWeight.getValue());
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

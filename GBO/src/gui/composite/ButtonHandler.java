package gui.composite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ButtonHandler implements ActionListener
{
    private final DesignPatternCompositeExampleAsTreeModel dpc;

    public ButtonHandler(final DesignPatternCompositeExampleAsTreeModel dpc)
    {
        this.dpc = dpc;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("<html>" + "Schachtel" + "<br>" + "hinzufügen" + "</html>") || s.equals("Schachtel hinzufügen"))
        {
            if (!dpc.getTypeBox().isSelected())
            {
                final String fehler = "Es kann nur zu Schachteln etwas hinzugefügt werden";
                JOptionPane.showMessageDialog(dpc, fehler, "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                new ContainerDialog(dpc, true, dpc.getModel(), (Container) dpc.getSelected());
            }

        }
        else if (s.equals("<html>" + "Gegenstand" + "<br>" + "hinzufügen" + "</html>") || s.equals("Gegenstand hinzufügen"))
        {
            if (!dpc.getTypeBox().isSelected())
            {
                final String fehler = "Es kann nur zu Schachteln etwas hinzugefügt werden";
                JOptionPane.showMessageDialog(dpc, fehler, "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                new ItemDialog(dpc, true, dpc.getModel(), (Container) dpc.getSelected());
            }

        }

        else if (s.equals("löschen"))
        {
            final String frage = "Wollen sie den Knoten \"" + dpc.getSelected().getName() + "\" wirklich löschen?";
            final int x = JOptionPane.showConfirmDialog(dpc, frage, "Löschen eine Knotens", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION && dpc.getSelected().getPredecessor() != null)
            {
                dpc.getModel().remove(dpc.getSelected().getPredecessor(), dpc.getSelected());
            }

        }

    }

}

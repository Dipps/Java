package gui.land;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LaenderInfos extends JFrame implements ActionListener
{
    private final JComboBox<Land> auswahl;

    private final JCheckBox genau;

    private final JLabel land;

    private final JLabel hauptstadt;

    private final JLabel einwohner;

    private final JLabel flaeche;

    private final JLabel dichte;

    public LaenderInfos(final Land[] laender)
    {
        super("LanderInfos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // JComboBox und JCheckBox erzeugen
        //
        auswahl = new JComboBox<>(laender);
        auswahl.setName("countrySelector");
        genau = new JCheckBox("genaue Angaben");
        genau.setName("exactValues");

        // ActionListener an JComboBox und JCheckBox anmelden
        //
        auswahl.addActionListener(this);
        genau.addActionListener(this);

        // alle JLabels und evtl. weitere Container
        // erzeugen, Layouts setzen und alle Elemente so
        // zu Container hinzufügen, dass mit
        // Abbildungen übereinstimmt; bitte darauf achten,
        // welche Attribute die Klasse besitzt (s.o.)

        final JLabel lLand = new JLabel("Land: ");
        final JLabel lHauptstadt = new JLabel("Hauptstadt: ");
        final JLabel lEinwohner = new JLabel("Einwohner: ");
        final JLabel lFlaeche = new JLabel("Fläche (in qKm): ");
        final JLabel lDichte = new JLabel("Bevölkerungsdichte (in Person pro qKm): ");

        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(0, 1));
        leftPanel.add(lLand);
        leftPanel.add(lHauptstadt);
        leftPanel.add(lEinwohner);
        leftPanel.add(lFlaeche);
        leftPanel.add(lDichte);

        land = new JLabel();
        land.setName("countryName");
        hauptstadt = new JLabel();
        hauptstadt.setName("capital");
        einwohner = new JLabel();
        einwohner.setName("population");
        flaeche = new JLabel();
        flaeche.setName("area");
        dichte = new JLabel();
        dichte.setName("density");

        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(0, 1));
        rightPanel.add(land);
        rightPanel.add(hauptstadt);
        rightPanel.add(einwohner);
        rightPanel.add(flaeche);
        rightPanel.add(dichte);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(auswahl);
        add(genau);
        add(mainPanel);

        setLayout(new GridLayout(0, 1));
        setLocation(200, 200);
        setSize(530, 210);
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final Land tmp = (Land) auswahl.getSelectedItem();

        if (genau.isSelected())
        {

            land.setText(tmp.getName());
            hauptstadt.setText(tmp.getHauptstadt());
            einwohner.setText("" + tmp.getEinwohner());
            flaeche.setText("" + tmp.getFlaeche());
            dichte.setText("" + tmp.getDichte());

        }
        else
        {
            land.setText(tmp.getName());
            hauptstadt.setText(tmp.getHauptstadt());
            einwohner.setText(formatieren(tmp.getEinwohner()));
            flaeche.setText(formatieren(tmp.getFlaeche()));
            dichte.setText(formatieren(tmp.getDichte()));
        }
    }

    private String formatieren(final long zahl)
    {
        // TODO ->
        if (zahl >= 1000000)
        {
            if (zahl % 1000000 >= 500000)
            {
                return ((zahl / 1000000) + 1) + " Mill.";
            }
            else
            {
                return (zahl / 1000000) + " Mill.";
            }
        }
        else if (zahl >= 1000)
        {
            if (zahl % 1000 >= 500)
            {
                return ((zahl / 1000) + 1) + ".000";
            }
            else
            {
                return (zahl / 1000) + ".000";
            }
        }
        else
        {
            return "" + zahl;
        }

    }

    public static void main(final String[] args)
    {
        final Land land1 = new Land("Belgien", "Brüssel", 10839905, 30528);
        final Land land2 = new Land("Luxemburg", "Luxemburg", 511840, 2586);
        final Land land3 = new Land("Kanada", "Ottawa", 34000000, 10000000);
        final Land schweden = new Land("Schweden", "Stockholm", 9415570, 450295);
        final Land luxemburg = new Land("Luxemburg", "Luxemburg", 511840, 2586);
        final Land belgien = new Land("Belgien", "Brüssel", 10839905, 30528);
        final Land taka = new Land("Taka-Tuka-Land", "Säbelweiler", 467, 25);

        final Land[] laender =
        { land1, land2, land3, schweden, luxemburg, belgien, taka };
        new LaenderInfos(laender);
    }
}

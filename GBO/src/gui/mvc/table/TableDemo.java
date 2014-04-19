package gui.mvc.table;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class TableDemo
{
    public static void main(final String[] args)
    {
        // Erzeugen eines JFrame-Objekts
        // (beachten Sie dabei den Fenstertitel der Abbildung;
        // das voreingestellte Layout ist okay):
        //
        final JFrame jf = new JFrame("Einmaleins");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Erzeugen des Modells und der JTable:
        final MyTableModel model = new MyTableModel(10, 10);
        final JTable table = new JTable(model);

        // Erzeugen in einer JScrollPane,
        // die die JTable enthält:
        final JScrollPane sc = new JScrollPane(table);

        // Hinzufügen der JScrollPane in das Frame,
        // Position und Größe setzen und Frame sichtbar machen
        jf.add(sc);
        jf.pack();
        jf.setVisible(true);

        // System.out.println(model.getValueAt(0, 0));

    }
}

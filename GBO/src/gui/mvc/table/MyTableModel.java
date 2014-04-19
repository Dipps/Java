package gui.mvc.table;

/* import-Anweisungen
 */
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel
{
    /*
     * Attribute.
     */
    private final int rows;

    private final int columns;

    private final String[][] data;

    private final String[] title;

    /*
     * Konstruktor.
     */
    public MyTableModel(final int rows, final int columns)
    {
        this.rows = rows;
        this.columns = columns;
        data = new String[rows][columns];
        title = new String[columns];

        for (int i = 0; i < columns; i++)
        {
            title[i] = (i + 1) + "er";
        }

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                final int x = i + 1;
                final int y = j + 1;
                final int ergebnis = x * y;
                data[i][j] = x + " * " + y + " = " + ergebnis;
            }
        }

    }

    /*
     * Achtung: Bei der Anzahl der Zeilen zählen die Spaltenüberschriften nicht
     * mit. Mit anderen Worten: Es geht nur um die Anzahl der Zeilen mit weißem
     * Hintergrund.
     */
    @Override
    public int getRowCount()
    {
        return rows;
    }

    @Override
    public int getColumnCount()
    {
        return columns;
    }

    /*
     * Achtung: Beachten Sie, dass das erste Feld ganz links oben in der Tabelle
     * mit der Beschriftung "1 * 1 = 1" in der Zeile 0 und der Spalte 0 steht.
     * Hinweis: Geben Sie ein Objekt vom Typ String zurück.
     */
    @Override
    public Object getValueAt(final int row, final int column)
    {
        return data[row][column];
    }

    /*
     * Liefert Tabellenüberschrift zurück.
     */
    @Override
    public String getColumnName(final int column)
    {
        return title[column];
    }

}

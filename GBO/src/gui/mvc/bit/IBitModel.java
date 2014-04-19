package gui.mvc.bit;

/**
 * Dieses Interface definiert die Schnittstelle des Modells der GBO-Aufgabe 2,
 * Blatt 7 (BitModel)
 * 
 * @author Patrick Fries
 */
public interface IBitModel
{
    /**
     * Gibt die Laenge des BitModels zurueck
     * 
     * @return Laenge des BitModels
     */
    public int getLength();

    /**
     * Liefert den Wert des BitModels an einer uebergebenen Stelle
     * 
     * @param index -
     *            Indexposition, deren Wert ausgelesen werden soll
     * @return Wert des BitModels an einer uebergebenen Stelle
     */
    public boolean get(int index);

    /**
     * Setzt den Wert des BitModel an einer uebergebenen Stelle
     * 
     * @param index -
     *            Indexpositoin, deren Wert gesetzt werden soll
     * @param b -
     *            Wert, der an die uebergebene Indexposition gesetzt werden soll
     */
    public void set(int index, boolean b);

    /**
     * Fuegt dem BitModel einen neuen BitModelListener hinzu
     * 
     * @param l -
     *            Hinzuzufuegender BitModelListener
     */
    public void addModelListener(IBitModelListener l);

    /**
     * Entfernt einen BitModelListener aus dem BitModel
     * 
     * @param l -
     *            Zu entfernender BitModelListener
     */
    public void removeModelListener(IBitModelListener l);

}
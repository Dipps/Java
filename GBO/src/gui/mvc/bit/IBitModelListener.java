package gui.mvc.bit;

/**
 * Dieses Interface muss von allen Views implementiert werden, 
 * die sich am BitModel anmelden wollen.
 * 
 * @author Patrick Fries
 */

public interface IBitModelListener
{
    /**
     * Wird vom Model aufgerufen, wenn es sich geaendert hat
     */
    public void modelChanged();
}

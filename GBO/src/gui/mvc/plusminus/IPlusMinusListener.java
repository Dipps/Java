package gui.mvc.plusminus;

/**
 * Interface fuer alle Views, die sich an einem PlusMinusModel
 * anmelden wollen.
 */
public interface IPlusMinusListener
{
    /**
     * Wird vom Modell aufgerufen, sobald sich der Zaehlwert des
     * Modells geaendert hat.
     * 
     * @param model Modell, dessen Zaehlwert sich geandert hat
     */
    public void plusMinusModelChanged(final PlusMinusModel model);
}

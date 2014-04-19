package gui.state.phone;

import java.util.ArrayList;

public class PhoneModel
{
    private PhoneState state;

    private String previousNumber;

    private String currentNumber;

    private String[] phoneBook;

    private int phoneBookIndex;

    private final ArrayList<PhoneListener> listeners;

    public PhoneModel()
    {
        this(null);
    }

    public PhoneModel(final String[] book)
    {
        state = new IdleState(this);
        previousNumber = "";
        currentNumber = "";

        if (book != null)
        {
            phoneBook = book.clone();
        }
        else
        {
            phoneBook = new String[0];
        }
        phoneBookIndex = 0;
        listeners = new ArrayList<>();

        fireNumberChanged(currentNumber);
        fireStateChanged(state.toString());
    }

    /**********************************************/
    /* internal methods called from state classes */
    /**********************************************/
    /**
     * 
     * @param state
     *            zu setzender State
     */
    void setState(final PhoneState state)
    {
        this.state = state;
        fireStateChanged(state.toString());
    }

    void reset()
    {
        currentNumber = "";
        fireNumberChanged(currentNumber);
    }

    void addDigitToCurrentNumber(final int d)
    {
        if (d < 0 || d > 9)
        {
            throw new IllegalArgumentException("Ziffer muss zwichen " + "0 und 9 sein");
        }
        currentNumber += d;
        fireNumberChanged(currentNumber);
    }

    String getPreviousNumber()
    {
        return previousNumber;
    }

    void setCurrentNumber(final String number)
    {
        currentNumber = number;
        fireNumberChanged(currentNumber);
    }

    int getPhoneBookLength()
    {
        return phoneBook.length;
    }

    void gotoFirstPhoneBookEntry()
    {
        phoneBookIndex = 0;
        currentNumber = phoneBook[phoneBookIndex];
        fireNumberChanged(currentNumber);
    }

    void gotoNextPhoneBookEntry()
    {
        if (phoneBookIndex < getPhoneBookLength() - 1)
        {
            phoneBookIndex++;
            currentNumber = phoneBook[phoneBookIndex];
            fireNumberChanged(currentNumber);
        }

    }

    void gotoPreviousPhoneBookEntry()
    {
        if (phoneBookIndex > 0)
        {
            phoneBookIndex--;
            currentNumber = phoneBook[phoneBookIndex];
            fireNumberChanged(currentNumber);
        }
    }

    void rememberNumber()
    {
        previousNumber = currentNumber;
    }

    void connect()
    {
        System.out.println("SIMULATION DES VERBINDUNGSAUFBAUS");
    }

    void disconnect()
    {
        System.out.println("SIMULATION DES VERBINDUNGSABBAUS");
    }

    /***************************************************/
    /* external methods called from controller classes */
    /***************************************************/
    /**
     * 
     * @param d
     *            zu setzende Nummer
     * @throws PhoneException
     */
    public void digit(final int d) throws PhoneException
    {
        state.digit(d);
    }

    public void okay() throws PhoneException
    {
        state.okay();
    }

    public void cancel()
    {
        state.cancel();
    }

    public void phoneList() throws PhoneException
    {
        state.phoneList();
    }

    public void menu() throws PhoneException
    {
        state.menu();
    }

    public void up() throws PhoneException
    {
        state.up();
    }

    public void down() throws PhoneException
    {
        state.down();
    }

    public void addPhoneListener(final PhoneListener pl)
    {
        listeners.add(pl);
    }

    public void removePhoneListeners(final PhoneListener pl)
    {
        listeners.remove(pl);
    }

    public void fireStateChanged(final String newState)
    {
        for (final PhoneListener l : listeners)
        {
            l.stateChanged(newState);
        }
    }

    public void fireNumberChanged(final String newCurrentNumber)
    {
        for (final PhoneListener l : listeners)
        {
            l.currentNumberChanged(newCurrentNumber);
        }

    }
}

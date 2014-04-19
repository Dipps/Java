package gui.state.phone;

public interface PhoneListener
{
    public void stateChanged(String newState);

    public void currentNumberChanged(String newCurrentNumber);
}

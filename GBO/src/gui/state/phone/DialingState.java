package gui.state.phone;

class DialingState extends PhoneState
{
    public DialingState(final PhoneModel model)
    {
        super(model);
    }

    @Override
    public void digit(final int d) throws PhoneException
    {
        model.addDigitToCurrentNumber(d);
    }

    @Override
    public void okay() throws PhoneException
    {
        model.rememberNumber();
        model.connect();
        model.setState(new ConnectedState(model));
    }

    @Override
    public String toString()
    {
        return "Dialing";
    }
}

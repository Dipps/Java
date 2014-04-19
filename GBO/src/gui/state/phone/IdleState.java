package gui.state.phone;

class IdleState extends PhoneState
{
    public IdleState(final PhoneModel model)
    {
        super(model);
    }

    @Override
    public void digit(final int d) throws PhoneException
    {
        model.addDigitToCurrentNumber(d);
        model.setState(new DialingState(model));
    }

    @Override
    public void okay() throws PhoneException
    {
        final String pNumber = model.getPreviousNumber();
        if (pNumber != null)
        {
            model.setCurrentNumber(pNumber);
            model.setState(new DialingState(model));
        }
    }

    @Override
    public void phoneList() throws PhoneException
    {
        final int entries = model.getPhoneBookLength();
        if (entries != 0)
        {
            model.gotoFirstPhoneBookEntry();
            model.setState(new PhoneListState(model));
        }
    }

    @Override
    public void menu() throws PhoneException
    {
        model.setState(new MenuState(model));
    }

    @Override
    public String toString()
    {
        return "Idle";
    }
}

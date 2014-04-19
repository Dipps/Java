package gui.state.phone;

class PhoneListState extends PhoneState
{
    public PhoneListState(final PhoneModel model)
    {
        super(model);
    }

    @Override
    public void okay() throws PhoneException
    {
        model.rememberNumber();
        model.connect();
        model.setState(new ConnectedState(model));
    }

    @Override
    public void up() throws PhoneException
    {
        model.gotoNextPhoneBookEntry();
    }

    @Override
    public void down() throws PhoneException
    {
        model.gotoPreviousPhoneBookEntry();
    }

    @Override
    public String toString()
    {
        return "PhoneList";
    }
}

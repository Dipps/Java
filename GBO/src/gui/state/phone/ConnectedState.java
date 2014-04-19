package gui.state.phone;

class ConnectedState extends PhoneState
{
    public ConnectedState(final PhoneModel model)
    {
        super(model);
    }

    @Override
    public String toString()
    {
        return "Verbunden";
    }
}

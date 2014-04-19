package gui.state.phone;

abstract class PhoneState
{
    protected PhoneModel model;

    public PhoneState(final PhoneModel model)
    {
        this.model = model;
    }

    public void digit(final int d) throws PhoneException
    {
        throw new PhoneException("Ziffer " + d + " in diesem Zustand nicht möglich");

    }

    public void okay() throws PhoneException
    {
        throw new PhoneException("Okay-Taste" + " in diesem Zustand nicht möglich");
    }

    public void cancel()
    {
        model.reset();
        model.setState(new IdleState(model));
    }

    public void phoneList() throws PhoneException
    {
        throw new PhoneException("Telefonbuch" + " in diesem Zustand nicht möglich");
    }

    public void menu() throws PhoneException
    {
        throw new PhoneException("Menü" + " in diesem Zustand nicht möglich");
    }

    public void up() throws PhoneException
    {
        throw new PhoneException("Pfeil nach oben" + " in diesem Zustand nicht möglich");
    }

    public void down() throws PhoneException
    {
        throw new PhoneException("Pfeil nach unten" + " in diesem Zustand nicht möglich");
    }
}

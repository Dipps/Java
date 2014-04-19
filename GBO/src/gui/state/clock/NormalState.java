package gui.state.clock;

public class NormalState extends State
{
    public NormalState(final Clock model)
    {
        super(model);
    }

    @Override
    public void set()
    {
        model.setState(new HoursSettingState(model));
    }

}

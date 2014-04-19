package gui.state.clock;

public class HoursSettingState extends State
{
    public HoursSettingState(final Clock model)
    {
        super(model);
    }

    @Override
    public void set()
    {
        model.setState(new MinutesSettingState(model));
    }

    @Override
    public void plus()
    {
        model.incrementHours();
    }

    @Override
    public void minus()
    {
        model.decrementHours();
    }

}

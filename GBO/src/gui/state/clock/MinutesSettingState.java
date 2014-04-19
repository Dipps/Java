package gui.state.clock;

public class MinutesSettingState extends State
{
    public MinutesSettingState(final Clock model)
    {
        super(model);
    }

    @Override
    public void set()
    {
        model.setState(new NormalState(model));
    }

    @Override
    public void plus()
    {
        model.incrementMinutes();
    }

    @Override
    public void minus()
    {
        model.decrementMinutes();
    }
}

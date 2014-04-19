package gui.state.clock;

public abstract class State
{
    protected Clock model;

    public State(final Clock model)
    {
        this.model = model;
    }

    public void set()
    {
        System.out.println("Set in diesem Zustand nicht m�glich!");
    }

    public void plus()
    {
        System.out.println("plus in diesem Zustand nicht m�glich!");
    }

    public void minus()
    {
        System.out.println("minus in diesem Zustand nicht m�glich!");
    }
}

package gui.lecture;

import javax.swing.*;

public class ButtonExample6 extends ButtonExample4
{
    public ButtonExample6(int i)
    {
        super();
        switch(i)
        {
            case 1:
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setTitle("Exit");
                break;
            case 2:
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                setTitle("Do Nothing");
                break;
            case 3:
                setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                setTitle("Hide");
                break;
            case 4:
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Dispose");
                break;
            default:
        }
    }

    public static void main(String[] args)
    {
        int value = 0;
        try
        {
            if(args.length != 1)
            {
                throw new NumberFormatException();
            }
            value = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e)
        {
            System.out.println("argument: 1 | 2 | 3 | 4");
            System.exit(0);
        }
        for(int i = 1; i <= 4; i++)
        {
            new ButtonExample6(value);
        }
    }
}

package gui.lecture;

import java.awt.*;
import java.awt.event.*;

public class RobotExample1
{
    public static void main(String[] args) throws AWTException
    {
        Robot robot = null;
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            System.err.println(e);
            return;
        }
        robot.delay(2000);

        robot.setAutoDelay(50);
        robot.setAutoWaitForIdle(true);

        robot.mouseMove(40, 130);
        leftClick(robot);
        type(robot, "Hallo Welt");
        robot.mouseMove(40, 230);
        leftClick(robot);
        type(robot, "Dies ist ein Test der Klasse Robot");
        type(robot, KeyEvent.VK_DOWN);
        type(robot, "Und das war dann auch schon alles");
        //type(robot, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        
        robot.delay(2000);
    }

    private static void leftClick(Robot robot)
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private static void type(Robot robot, int i)
    {
        robot.keyPress(i);
        robot.keyRelease(i);
    }

    private static void type(Robot robot, String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            boolean upperCase = true;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123) //[a-z]
            {
                code = code - 32;
                upperCase = false;
            }
            if(upperCase)
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            type(robot, code);
            if(upperCase)
            {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
}
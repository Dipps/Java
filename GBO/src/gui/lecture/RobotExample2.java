package gui.lecture;

import java.awt.*;
import java.awt.event.*;

public class RobotExample2
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

        robot.setAutoDelay(1000);
        robot.setAutoWaitForIdle(true);

        robot.mouseMove(40, 140);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(240, 140);
        robot.mouseMove(240, 340);
        robot.mouseMove(40, 340);
        robot.mouseMove(40, 140);
        robot.mouseMove(240, 340);
        robot.mouseMove(40, 340);
        robot.mouseMove(240, 140);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        robot.delay(2000);
    }
}
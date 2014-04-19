package gui.lecture;

import java.io.*;

/**
 * Ausnahme StateException. Wird bei allen nicht erlaubten Ereignissen
 * in einem gewissen Zustand ausgelöst.
 * 
 * @author Rainer Oechsle
 */
class StateException extends Exception
{
    /**
     * Konstruktor.
     */
    public StateException(String msg)
    {
        super(msg);
    }
}

/**
 * Abstrakte Basisklasse State. Daraus werden alle Zustandsklassen
 * abgeleitet. Folgende Zustandsübergänge werden realisiert:
 * 
 * <pre>
 *     state1 ------event1------&gt; state2
 *            ------event2------&gt; state3
 *            ------event3------&gt; state1
 * </pre>
 * 
 * @author Rainer Oechsle
 */
abstract class State
{
    /**
     * Referenz auf Context-Objekt.
     */
    protected StateMachine stateMachine;

    /**
     * Methode zum Setzen des Kontexts.
     */
    public State(StateMachine stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    /**
     * event1 löst als Standardreaktion Ausnahme aus. Kann in
     * abgeleiteter Klasse überschrieben werden.
     */
    public void event1() throws StateException
    {
        throw new StateException("event1 not allowed in this state");
    }

    /**
     * event2 löst als Standardreaktion Ausnahme aus. Kann in
     * abgeleiteter Klasse überschrieben werden.
     */
    public void event2() throws StateException
    {
        throw new StateException("event2 not allowed in this state");
    }

    /**
     * event3 löst als Standardreaktion Ausnahme aus. Kann in
     * abgeleiteter Klasse überschrieben werden.
     */
    public void event3() throws StateException
    {
        throw new StateException("event3 not allowed in this state");
    }
}

/**
 * Klasse State1. Abgeleitet aus State.
 * 
 * @author Rainer Oechsle
 */
class State1 extends State
{
    /**
     * Konstruktor.
     */
    public State1(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    /**
     * Ereignis event1 ist im Zustand State1 möglich.
     */
    public void event1() throws StateException
    {
        stateMachine.recordEvent("1");
        stateMachine.setState(new State2(stateMachine));
    }

    /**
     * Textdarstellung von State1.
     */
    public String toString()
    {
        return "state 1";
    }
}

/**
 * Klasse State2. Abgeleitet aus State.
 * 
 * @author Rainer Oechsle
 */
class State2 extends State
{
    /**
     * Konstruktor.
     */
    public State2(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    /**
     * Ereignis event2 ist im Zustand State2 möglich.
     */
    public void event2() throws StateException
    {
        stateMachine.recordEvent("2");
        stateMachine.setState(new State3(stateMachine));
    }

    /**
     * Textdarstellung von State2.
     */
    public String toString()
    {
        return "state 2";
    }
}

/**
 * Klasse State3. Abgeleitet aus State.
 * 
 * @author Rainer Oechsle
 */
class State3 extends State
{
    /**
     * Konstruktor.
     */
    public State3(StateMachine stateMachine)
    {
        super(stateMachine);
    }

    /**
     * Ereignis event3 ist im Zustand State3 möglich.
     */
    public void event3() throws StateException
    {
        stateMachine.recordEvent("3");
        stateMachine.setState(new State1(stateMachine));
    }

    /**
     * Textdarstellung von State3.
     */
    public String toString()
    {
        return "state 3";
    }
}

/**
 * Klasse Context, deren Verhalten über endlichen Automaten gesteuert
 * wird.
 * 
 * @author Rainer Oechsle
 */
class StateMachine
{
    /**
     * Referenz auf aktuellen Zustand.
     */
    private State state;

    /**
     * Historie der eingetretenen Ereignisse als Zeichenkette.
     */
    private String events;

    /**
     * Konstruktor.
     */
    public StateMachine()
    {
        state = new State1(this);
        events = "";
    }

    /**
     * Aktuellen Zustand setzen.
     */
    public void setState(State state)
    {
        this.state = state;
    }

    /**
     * Ereignis event1. Wird delegiert an State-Objekt.
     */
    public void event1() throws StateException
    {
        state.event1();
    }

    /**
     * Ereignis event2. Wird delegiert an State-Objekt.
     */
    public void event2() throws StateException
    {
        state.event2();
    }

    /**
     * Ereignis event3. Wird delegiert an State-Objekt.
     */
    public void event3() throws StateException
    {
        state.event3();
    }

    /**
     * Ereignis aufzeichnen. Beispiel einer Aktion, die neben dem
     * Zustandsübergang ausgeführt wird.
     */
    public void recordEvent(String s)
    {
        events += " -> " + s;
    }

    /**
     * Textdarstellung von Context. Gibt den aktuellen Zustand und die
     * Historie auf Standardausgabe aus.
     */
    public String toString()
    {
        return state.toString() + " (history: " + events + ")";
    }
}

/**
 * Klasse, die nur die main-Methode enthält.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternStateExample
{
    /**
     * main-Methode. Arbeitet interaktiv. Durch Eingabe einer Zahl 1,
     * 2 oder 3 kann event1, event2 oder event3 simuliert werden.
     */
    public static void main(String[] args)
    {
        StateMachine ctx = new StateMachine();
        BufferedReader input = new BufferedReader(
                                   new InputStreamReader(System.in));

        while(true)
        {
            System.out.print("event (1 | 2 | 3 | exit): ");
            String line;
            try
            {
                line = input.readLine();
                if(line == null || line.equals("exit") || line.equals("quit"))
                {
                    break;
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
                break;
            }

            int event;
            try
            {
                event = Integer.parseInt(line);
            }
            catch(NumberFormatException e)
            {
                System.out.println("no integer");
                continue;
            }

            try
            {
                switch(event)
                {
                    case 1:
                        ctx.event1();
                        break;
                    case 2:
                        ctx.event2();
                        break;
                    case 3:
                        ctx.event3();
                        break;
                    default:
                        System.out.println("wrong event");
                }
            }
            catch(StateException e)
            {
                System.out.println(e);
            }

            System.out.println(ctx);
        }
    }
}

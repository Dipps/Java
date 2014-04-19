package gui.lecture;

/**
 * Schnittstelle Observer, die ein Beobachter implementieren muss,
 * damit er bei einem zu beobachtenden Objekt angemeldet werden kann.
 * 
 * @author Rainer Oechsle
 */

interface Observer
{
    /**
     * Methode zum Benachrichtigen eines Beobachters.
     */
    public void eventHappened(Observable source, String description);
}

/**
 * Klasse Observable, an der Beobachter angemeldet werden können.
 * 
 * @author Rainer Oechsle
 */
class Observable
{
    /**
     * Angemeldete Beoabachter.
     */
    private Observer[] listeners;

    /**
     * Anzahl der angemeldeten Beoabachter.
     */
    private int numberOfListeners;

    /**
     * Konstruktor. Erzeugt Feld für die angemeldeten Beobachter.
     */
    public Observable()
    {
        listeners = new Observer[2];
        numberOfListeners = 0;
    }

    /**
     * Methode zum Anmelden eines Beobachters. Einfache
     * Implementierung, da nur eine begrenzte Anzahl von Beobachtern
     * angemeldet werden kann. Alles, war darüber hinaus geht, wird
     * stillschweigend ignoriert.
     */
    public void addObserver(Observer o)
    {
        if(numberOfListeners == listeners.length)
        {
            Observer[] newListeners = new Observer[2 * listeners.length];
            for(int i = 0; i < numberOfListeners; i++)
            {
                newListeners[i] = listeners[i];
            }
            listeners = newListeners;
        }
        listeners[numberOfListeners++] = o;
    }

    /**
     * Simuliert das Eintreffen eines Ereignisses. Alle angemeldeten
     * Beobachter werden benachrichtigt.
     */
    public void fireEvent()
    {
        for(int i = 0; i < numberOfListeners; i++)
        {
            listeners[i].eventHappened(this, "Ereignis passiert");
        }
    }
}

/**
 * Klasse X. Beispiel einer Klasse, welche die Schnittstelle Observer
 * implementiert.
 * 
 * @author Rainer Oechsle
 */
class Observer1 implements Observer
{
    /**
     * Beispielattribut int.
     */
    private int i;

    /**
     * Konstruktor. Setzen des Beispielattributs.
     */
    public Observer1(int i)
    {
        this.i = i;
    }

    /**
     * Methode der Schnittstelle Observer. Ausgabe auf
     * Standardausgabe.
     */
    public void eventHappened(Observable source, String description)
    {
        System.out.println("x " + i + ": " + description);
    }
}

/**
 * Klasse Y. Beispiel einer Klasse, welche die Schnittstelle Observer
 * implementiert.
 * 
 * @author Rainer Oechsle
 */
class Observer2 implements Observer
{
    /**
     * Beispielattribut double.
     */
    private double d;

    /**
     * Konstruktor. Setzen des Beispielattributs.
     */
    public Observer2(double d)
    {
        this.d = d;
    }

    /**
     * Methode der Schnittstelle Observer. Ausgabe auf
     * Standardausgabe.
     */
    public void eventHappened(Observable source, String description)
    {
        System.out.println("y " + d + ": " + description);
    }
}

/**
 * Klasse mit main-Methode zum Testen des Entwurfsmusters.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternObserverExample
{

    /**
     * main-Methode.
     */
    public static void main(String[] args)
    {
        Observable observable = new Observable();
        Observer1 obs11 = new Observer1(1);
        observable.addObserver(obs11);
        Observer1 obs12 = new Observer1(2);
        observable.addObserver(obs12);
        Observer2 obs21 = new Observer2(1.111);
        observable.addObserver(obs21);
        Observer2 obs22 = new Observer2(2.222);
        observable.addObserver(obs22);
        observable.fireEvent();
    }
}

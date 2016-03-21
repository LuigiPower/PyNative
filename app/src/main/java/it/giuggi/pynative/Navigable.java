package it.giuggi.pynative;

/**
 * Created by Federico Giuggioloni on 21/03/16.
 * Se aggiungo questa riga magari
 * AndroidStudio smette di lamentarsi...
 */
public class Navigable
{
    protected INavigationController controller;

    public Navigable(INavigationController controller)
    {
        this.controller = controller;
    }
}

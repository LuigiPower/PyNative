package it.giuggi.pynative;

import org.json.JSONObject;

/**
 * Created by Federico Giuggioloni on 21/03/16.
 * Se aggiungo questa riga magari
 * AndroidStudio smette di lamentarsi...
 */
public interface INavigationController
{
    public abstract void loadLayout(JSONObject obj);
}

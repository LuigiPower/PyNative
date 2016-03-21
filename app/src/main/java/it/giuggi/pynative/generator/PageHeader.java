package it.giuggi.pynative.generator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Federico Giuggioloni on 21/03/16.
 * Se aggiungo questa riga magari
 * AndroidStudio smette di lamentarsi...
 */
public class PageHeader
{
    public String screenName;
    public String screenTitle;

    public PageHeader(JSONObject header) throws JSONException
    {
        this.screenName = header.getString("screenname");
        this.screenTitle = header.getString("title");
    }
}

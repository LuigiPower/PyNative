package it.giuggi.pynative.generator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.giuggi.pynative.INavigationController;
import it.giuggi.pynative.Navigable;
import it.giuggi.pynative.net.WebRequestTask;

/**
 * Created by Federico Giuggioloni on 21/03/16.
 * Se aggiungo questa riga magari
 * AndroidStudio smette di lamentarsi...
 */
public class PageBody extends Navigable
{
    public static final String EL_TEXTVIEW = "textview";
    public static final String EL_BUTTON = "button";

    public static final String OPT_TYPE = "type";

    public static final String ATTR_TEXT = "text";
    public static final String ATTR_ACTION = "action";

    public static final String ACTION_GOTO = "goto";

    public static final String ACTION_TOSCREEN = "screen";

    public View[] bodyElements;

    public PageBody(INavigationController controller, Context context, JSONArray elements) throws JSONException
    {
        super(controller);

        bodyElements = new View[elements.length()];

        for(int i = 0; i < elements.length(); i++)
        {
            JSONObject obj = elements.getJSONObject(i);

            View v = jsonToElement(context, obj);
            bodyElements[i] = v;
        }
    }

    public View getElementAt(int i)
    {
        return bodyElements[i];
    }

    public int getCount()
    {
        return bodyElements.length;
    }

    public View jsonToElement(Context context, JSONObject obj) throws JSONException
    {
        View element;
        String type = obj.getString(OPT_TYPE);
        Log.e("CREATING ELEMENT", "CREATING ELEMENT " + type);

        if(type.equalsIgnoreCase(EL_TEXTVIEW))
        {
            TextView text = new TextView(context);
            text.setText(obj.getString(ATTR_TEXT));
            element = text;
        }
        else if(type.equalsIgnoreCase(EL_BUTTON))
        {
            Button button = new Button(context);
            //TODO set action
            JSONObject action = obj.getJSONObject(ATTR_ACTION);

            String text = obj.getString(ATTR_TEXT);
            button.setText(text);

            String actionType = action.getString(OPT_TYPE);
            if(actionType.equalsIgnoreCase(ACTION_GOTO))
            {
                final String toScreen = action.getString(ACTION_TOSCREEN);
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Bundle dati = new Bundle();
                        dati.putStringArray(WebRequestTask.DATA, new String[]{
                                toScreen
                        });

                        WebRequestTask.perform(WebRequestTask.Azione.GET_PAGE)
                                .with(dati)
                                .listen(new WebRequestTask.OnResponseListener()
                                {
                                    @Override
                                    public void onResponseReceived(Object ris, WebRequestTask.Tipo t, Object... datiIniziali)
                                    {
                                        Log.e("DONE BUTTON", "BUTTON PRESSED, REQUESTED " + ris);

                                        JSONObject obj = (JSONObject) ris;
                                        controller.loadLayout(obj);
                                    }
                                })
                                .send();
                    }
                });
            }

            element = button;
        }
        else
        {
            element = new View(context);
        }

        return element;
    }
}

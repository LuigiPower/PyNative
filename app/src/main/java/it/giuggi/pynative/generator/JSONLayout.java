package it.giuggi.pynative.generator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import it.giuggi.pynative.INavigationController;

/**
 * Created by Federico Giuggioloni on 21/03/16.
 * Se aggiungo questa riga magari
 * AndroidStudio smette di lamentarsi...
 */
public class JSONLayout
{
    private JSONObject currentJSON;

    private PageHeader header;
    private PageBody body;

    public JSONLayout(INavigationController controller, Activity context, JSONObject json)
    {
        this.currentJSON = json;

        try
        {
            header = new PageHeader(json);
            body = new PageBody(controller, context, json.getJSONObject("layout").getJSONArray("elements"));
        } catch (JSONException e)
        {
            e.printStackTrace();
            return;
        }

        context.setTitle(header.screenTitle);
    }

    public ViewGroup getView(Context context)
    {
        ScrollView scroll = new ScrollView(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        for(int i = 0; i < body.getCount(); i++)
        {
            layout.addView(body.getElementAt(i));
        }

        scroll.addView(layout);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return scroll;
    }
}

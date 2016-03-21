package it.giuggi.pynative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONObject;

import it.giuggi.pynative.generator.JSONLayout;
import it.giuggi.pynative.net.WebRequestTask;

public class MainActivity extends AppCompatActivity implements INavigationController
{
    LinearLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContainer = (LinearLayout) findViewById(R.id.main_container);

        Bundle data = new Bundle();
        data.putStringArray(WebRequestTask.DATA, new String[]{
                "screenname", "index"
        });

        WebRequestTask.perform(WebRequestTask.Azione.GET_PAGE)
                .with(data)
                .listen(new WebRequestTask.OnResponseListener()
                {
                    @Override
                    public void onResponseReceived(Object ris, WebRequestTask.Tipo t, Object... datiIniziali)
                    {
                        Log.e("RESPONSE RECEIVED", "RESPONSE IS " + ris);

                        JSONObject obj = (JSONObject) ris;

                        loadLayout(obj);
                    }
                })
                .send();
    }

    public void loadLayout(JSONObject obj)
    {
        JSONLayout layout = new JSONLayout(this, this, obj);

        View v = layout.getView(this);
        mainContainer.removeAllViews();
        mainContainer.addView(v);
    }
}

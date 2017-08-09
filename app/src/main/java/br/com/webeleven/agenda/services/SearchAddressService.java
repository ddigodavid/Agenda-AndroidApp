package br.com.webeleven.agenda.services;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import br.com.webeleven.agenda.SearchAddressActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchAddressService {

    public void getAddressInformation(String addressKeyword, AppCompatActivity instance, final TextView textViewField) {
        textViewField.setText(String.format("Procurando por '%s'...", addressKeyword));

        RequestQueue queue = Volley.newRequestQueue(instance);
        String url = this.buildGoogleUriWithAddressParam(addressKeyword);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");
                    String formattedAddress = results.getJSONObject(0).getString("formatted_address");
                    textViewField.setText(formattedAddress);
                } catch (JSONException $e) {
                    textViewField.setText("That didn't work!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewField.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }

    public String buildGoogleUriWithAddressParam(String addressKeyword) {
        Uri.Builder uri = Uri.parse("http://maps.google.com/maps/api/geocode/json")
                .buildUpon();

        uri.appendQueryParameter("address", addressKeyword);

        return uri.build().toString();
    }

}

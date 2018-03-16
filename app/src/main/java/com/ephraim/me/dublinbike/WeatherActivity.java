package com.ephraim.me.dublinbike;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;



public class WeatherActivity extends AppCompatActivity {

    /* Written by Ephraim Kanyandula (15328) */

    EditText cityName;
    TextView textResult;
    Button findWeather;
    String message ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = (EditText) findViewById(R.id.cityName);
        textResult = (TextView) findViewById(R.id.textResult);
        findWeather = (Button) findViewById(R.id.findWeather);

        findWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i( "cityName", cityName.getText().toString());

                // managing the keyboard
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow( cityName.getWindowToken(), 0);



                DownloadTask task = new DownloadTask();
                task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + cityName.getText().toString() +"&APPID=72e2d0ce1f566bf48695111225b4b112" );


                textResult.setText(message);
            }
        });





    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                //String message = "";

                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main ="";
                    String description = "";
                    main = jsonPart.getString ("main");


                    Log.i("main", jsonPart.getString("main"));
                    Log.i("description", jsonPart.getString("description"));

                    if ( main != "" && description != ""  ){

                 message += main + ": " + description + "\r\n";
                    test();
                    }

                }

                if (message != "") {
                    textResult.setText(message);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }

    private void test(){
       textResult.setText(message);

    }

}

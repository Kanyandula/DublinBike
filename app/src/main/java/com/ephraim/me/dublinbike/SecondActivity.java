// Ephraim Kanyandula (15328)

package com.ephraim.me.dublinbike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Iterator;

public class SecondActivity extends AppCompatActivity {

    Button btnList;
    Button btnAbout;
    Button back;
    Button weather;


    //public static TextView textView;
    public static TextView data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        btnList = (Button) findViewById(R.id.btnList);


        btnAbout = (Button) findViewById(R.id.btnAbout);
        weather = (Button) findViewById(R.id.weather);
//this takes you to the weather page
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, WeatherActivity.class));
            }
        });


//this takes you to about page
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, About.class));


            }
        });

// this take you to a page where you have the list view
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, listData.class);
                startActivity(intent);

            }
        });




        configureNextButton();

    }
//the map button takes you to the map page.

    private void configureNextButton() {
        Button map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, MapsActivity.class));
            }
        });





    }




    }


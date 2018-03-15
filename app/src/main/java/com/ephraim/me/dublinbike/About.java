package com.ephraim.me.dublinbike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {
    Button info;

    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        info =  (Button) findViewById(R.id.etInfo);
        show = (TextView) findViewById(R.id.show);
//button that displays information on a textview
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Infomation about the app
                show.setText("This is a Dublin Bike App. It will help you to find locations where you can get a Dublin city Bike," +
                        "The number of available Bikes and their stand numbe" +
                        "The App also contain Map to help you with directions" +
                        "This App was designed by Ephraim Kanyandula");

            }
        });


        configureNextButton();


    }

    private void configureNextButton() {
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(About.this, SecondActivity.class));

            }
        });

    }

}

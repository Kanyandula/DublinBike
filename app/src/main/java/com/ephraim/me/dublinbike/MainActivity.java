package com.ephraim.me.dublinbike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
private  int counter = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.etInfo);

        Login = (Button)findViewById(R.id.btnLogin);
       // Info.setText("No of attempts remaining: 5 ");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });



    }

    private void validate(String userName, String userPassword) {
      if ((userName.equals("Student")) && (userPassword.equals("12345") )) {

          Intent intent = new Intent(MainActivity.this, SecondActivity.class);
          startActivity(intent);
      }
      else {
          counter--;
          Info.setText("Try Again");

          if(counter == 0){
              Login.setEnabled(false);
          }
      }
    }

    }

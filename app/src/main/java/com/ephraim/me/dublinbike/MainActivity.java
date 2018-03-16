
// Ephraim Kanyandula (15328)

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

//This project was done by Ephraim Kanyandula (15328)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.etInfo);

        Login = (Button)findViewById(R.id.btnLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });



    }
    // checking the credentials before granting permission
    private void validate(String userName, String userPassword) {
      if ((userName.equals("Student")) && (userPassword.equals("12345") )) {

          Intent intent = new Intent(MainActivity.this, SecondActivity.class);
          startActivity(intent);
      }
      else {
          // subtracting the number of attempts to login
          counter--;
          Info.setText("Try Again");
          //disabling the button login when the attempts are equal to 6
          if(counter == 0){
              Login.setEnabled(false);
          }
      }
    }

    }

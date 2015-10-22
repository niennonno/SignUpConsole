package org.niennonno.signupconsole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInActivity extends AppCompatActivity {

    EditText password, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        password = (EditText)findViewById(R.id.password_login);
        number = (EditText)findViewById(R.id.phone_login);

        Button logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNumberValid(number.getText().toString())){
                    Toast.makeText(LogInActivity.this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
                }else{
                    ParseUser.logInInBackground(number.getText().toString(),
                            password.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser parseUser, ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(LogInActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LogInActivity.this,ThankYou2Activity.class);
                                        startActivity(i);
                                    } else {
                                        int a = e.getCode();
                                        String error = String.valueOf(a);
                                        e.printStackTrace();
                                        Toast.makeText(LogInActivity.this, error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    boolean isNumberValid(String number){
        if (isNumeric(number)){
            if (number.length()==10){
                return true;
            }
            else{
                return false;
            }
        }else
            return false;
    }

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}

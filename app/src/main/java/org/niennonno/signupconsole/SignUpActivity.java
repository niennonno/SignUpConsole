package org.niennonno.signupconsole;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText phone, email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TelephonyManager telephonyManager = (TelephonyManager) this.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = telephonyManager.getLine1Number();

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        phone.setText(phoneNumber);
        password = (EditText) findViewById(R.id.password);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid(email.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                }else if (!isNumberValid(phone.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String phoneNumber = phone.getText().toString();
                    String pass = password.getText().toString();

                    ParseUser user = new ParseUser();
                    user.setUsername(phoneNumber);
                    user.setPassword(pass);
                    user.setEmail(email.getText().toString());
                    user.put("Name", name.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUpActivity.this, "Signed Up!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this,ThankYouActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
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

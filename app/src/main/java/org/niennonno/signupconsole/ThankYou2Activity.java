package org.niennonno.signupconsole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;
import com.twitter.sdk.android.Twitter;

public class ThankYou2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button logOut=(Button)findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOutInBackground();
                ParseUser user = ParseUser.getCurrentUser();
                LoginManager.getInstance().logOut();
                Twitter.getInstance().logOut();
                if(user==null){
                    Toast.makeText(ThankYou2Activity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ThankYou2Activity.this, "Error! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}

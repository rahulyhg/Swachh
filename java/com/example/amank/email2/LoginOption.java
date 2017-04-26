package com.example.amank.email2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginOption extends AppCompatActivity {

    Button guestlogin,userlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        userlogin=(Button)findViewById(R.id.userlogin);
        guestlogin=(Button)findViewById(R.id.guestlogin);

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(LoginOption.this,SignUpActivity.class);
                startActivity(in);

            }
        });


        guestlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(LoginOption.this,HomePage.class);
                startActivity(in);
            }
        });


    }
}

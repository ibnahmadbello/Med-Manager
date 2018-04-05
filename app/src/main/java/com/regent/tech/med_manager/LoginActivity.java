package com.regent.tech.med_manager;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout = (RelativeLayout) findViewById(R.id.login_layout);
        setupSnackbar();

    }


    public void setupSnackbar(){
        Snackbar.make(layout, "Health is Wealth", Snackbar.LENGTH_INDEFINITE)
                .setAction("Sign In", null)
                .setActionTextColor(Color.GREEN)
                .show();
    }

}

package com.regent.tech.med_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by root on 3/26/18.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}

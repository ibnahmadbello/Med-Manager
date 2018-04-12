package com.regent.tech.med_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    LoginActivity loginActivity;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mButton = (Button) findViewById(R.id.test_sign_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu options from the res/menu/menu_login.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Sign out" menu option
            case R.id.sign_out:
                loginActivity.signOut();
                return true;
            case R.id.disconnect:
//                loginActivity.revokeAccess();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

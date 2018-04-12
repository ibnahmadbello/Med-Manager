package com.regent.tech.med_manager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Button Listener
        findViewById(R.id.btn_sign_in).setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("1054952682027-11fraa65ll0917cro1kg56rrosomlg4s.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // [END configure_signin]

//        // [START build_client]
//        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        // [END build_client]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//        mGoogleApiClient.connect();

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.btn_sign_in);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        // [END customize_button]

        mAuth = FirebaseAuth.getInstance();

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
////        // [START on_start_sign_in]
////        // Check for existing Google Sign In account, if the user is already signed in
////        // the GoogleSignInAccount will be non-null.
////        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);
////        // [END on_start_sign_in]
//
//        // Check if user is signed in (non-null) and update UI accordingly
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//
//    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
            if (data!= null){
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                // Google Sign In was successful, authenticate with firebase
                GoogleSignInAccount account = result.getSignInAccount();
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
//                intent.putExtra("name", account.getDisplayName());
//                intent.putExtra("picture", account.getPhotoUrl());
//                intent.putExtra("email", account.getEmail());
                startActivity(intent);
//                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In was not successful
//                updateUI(null);
                Toast.makeText(this, "Could not log in", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // [END onActivityResult]

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//        mProgressBar.setVisibility(View.VISIBLE);
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            // Sign In success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user
//                            Log.w(TAG, "singInWithCredential:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT)
//                                    .show();
//                            updateUI(null);
//                        }
//
//                        mProgressBar.setVisibility(View.GONE);
//                    }
//                });
//    }

//    // [START handleSignInResult]
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }
//    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    public void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

//    public void signOut(){
//        // Firebase Sign out
//        mAuth.signOut();
//
//        // Google Sign out
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }



//    private void updateUI(@Nullable GoogleSignInAccount account) {
//        if (account != null) {
//            Intent intent = new Intent(this, WelcomeActivity.class);
//            startActivity(intent);
//        }
//    }

//    // [START revokeAccess]
//    public void revokeAccess() {
//        mGoogleSignInClient.revokeAccess()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // [START_EXCLUDE]
//                        updateUI(null);
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//    // [END revokeAccess]

//    public void revokeAccess(){
//        // Firebase sign out
//        mAuth.signOut();
//
//        // Google revoke access
//        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                updateUI(null);
//            }
//        });
//    }

//    private void updateUI(FirebaseUser user){
////        mProgressBar.setVisibility(View.GONE);
//        if (user != null){
//            Intent intent = new Intent(this, WelcomeActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Could not log in", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){
        // An unresolvable error has occured and GOOGLE APIs (including sign-in) will not be
        // available
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_sign_in:
                signIn();
                break;
        }
    }

}

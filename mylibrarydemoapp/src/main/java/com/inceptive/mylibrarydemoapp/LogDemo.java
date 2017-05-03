package com.inceptive.mylibrarydemoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by inceptive on 3/5/17.
 */

public class LogDemo extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    public static GoogleApiClient mGoogleApiClient;
    public static int RC_SIGN_IN =100;
    public static Context context;
    int a,b = 0;
    public static String name;
    public static String email;
    public static String userid;

    GoogleSignInResult result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail_activity);

        sum(a,b);
        handleSignInResult(result);


    }



    public static int sum(int a, int b){

        int c = a+b;
        return c;
    }



    public static void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            name = acct.getDisplayName();
            email = acct.getEmail();
            userid = acct.getId();

            getusername();
            getuseremail();
            getuserid();

//            AlertDialog.Builder builder = new AlertDialog.Builder(LogDemo.this);
//            builder.setMessage("Login Successfully");
//            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });

        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    public static String getusername() {
        return name;
    }

    public static String getuseremail() {
        return email;
    }

    public static String getuserid() {
        return userid;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

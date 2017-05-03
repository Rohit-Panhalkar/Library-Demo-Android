package com.inceptive.mylibrarydemoapp;

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

    int a,b = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail_activity);

        sum(a,b);


        InitializeGooglePlusApi(LogDemo.this);
        gmailIntegration(LogDemo.this);



    }

    public static void InitializeGooglePlusApi(LogDemo logDemo) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(logDemo)
                .enableAutoManage(logDemo /* FragmentActivity */, logDemo /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public static void gmailIntegration(LogDemo logDemo) {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        logDemo.startActivityForResult(signInIntent, RC_SIGN_IN);
//
    }



    public static int sum(int a, int b){

        int c = a+b;
        return c;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    public static void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String name = acct.getDisplayName();
            String email = acct.getEmail();
            String userid = acct.getId();

            getusername(name);
            getuseremail(email);
            getuserid(userid);

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

    public static String getusername(String name) {
        return name;
    }

    public static String getuseremail(String email) {
        return email;
    }

    public static String getuserid(String userid) {
        return userid;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

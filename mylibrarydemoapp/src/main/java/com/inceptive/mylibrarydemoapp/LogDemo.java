package com.inceptive.mylibrarydemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

/**
 * Created by inceptive on 3/5/17.
 */

public class LogDemo extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final int CAMERA_REQUEST = 100;
    public static GoogleApiClient mGoogleApiClient;
    public static int RC_SIGN_IN =100;
    public static Context context;
    int a,b = 0;
    public static String name;
    public static String email;
    public static String userid;
    public static String selectedImagePath;
    GoogleSignInResult result;
    public static Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail_activity);

        context = LogDemo.this;
        sum(a,b);
        handleSignInResult(result);

        alertDialogBox(context);
        camera();
    }

    public static void camera() {
                final CharSequence[] items = { "Take Photo", "Choose from Library",
                        "Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo")) {
                            cameraIntent((LogDemo) context);
                        } else if (items[item].equals("Choose from Library")) {
                            galleryIntent((LogDemo) context);
                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }

    private static void galleryIntent(LogDemo context) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(galleryIntent, 2);
    }

    public static void cameraIntent(Activity context) {
        context.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST);
    }

    private void alertDialogBox(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Login Successfully");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
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
            acct.getPhotoUrl();



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("data is"+data);
        if(data!=null) {

            if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = LogDemo.this.getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                selectedImagePath = c.getString(columnIndex);

                getSelectedImage();
                c.close();
//                Bitmap thumbnail = (BitmapFactory.decodeFile(selectedImagePath));
//                profileimage.setImageBitmap(thumbnail);

            } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK ) {

                bitmap = (Bitmap) data.getExtras().get("data");

                getbitmapImage();

                getbase64toString(bitmap);



                //  piccaptureimg.setImageBitmap(BitmapFactory.decodeFile(result));;

            }
        }
}

    public static String getbase64toString(Bitmap bitmap) {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        selectedImagePath= Base64.encodeToString(b, Base64.DEFAULT);
        return selectedImagePath;
    }

    public static Bitmap getbitmapImage() {
        return bitmap;
    }

    public static String getSelectedImage() {
        return selectedImagePath;
    }
    }

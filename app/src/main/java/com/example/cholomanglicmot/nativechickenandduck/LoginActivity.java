package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity{

   //private SignInButton SignIn;
   // private GoogleApiClient googleApiClient;

    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View view){

       Intent intent = new Intent(this, MainActivity.class);



       startActivity(intent);
    }


    public void onClick(View view) {



    }


}

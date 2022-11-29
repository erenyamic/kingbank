package com.erenyamic.KingBank;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
;

import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.RoundedCorner;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.WriteResult;
import com.google.rpc.context.AttributeContext;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    variables variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ata();
    }
    private void ata(){
        variables=new variables();
       variables.sign_up=findViewById(R.id.button3);
       variables.log_email=findViewById(R.id.editTextTextEmailAddress);
       variables.log_pass=findViewById(R.id.editTextTextPassword);
       variables.lion=findViewById(R.id.imageView2);
    }
    public void SignUp(View view){

        variables.intent=new Intent(MainActivity.this,signup.class);
        startActivity(variables.intent);
    }

    public void reset_pass(View view){
        if (!variables.log_email.getText().toString().matches("")){
            String url2="https://cryptoymc.com/sendEmail.php";
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();

                    params.put("email",variables.log_email.getText().toString());
                    params.put("code","https://cryptoymc.com/passReset.php");
                    return params;
                }
            };requestQueue.add(stringRequest);

        }else
            Toast.makeText(MainActivity.this,"Can't be left blank",Toast.LENGTH_LONG).show();
    }




    @Override
    public void onBackPressed() {

        if (variables.pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        variables.pressedTime = System.currentTimeMillis();
    }


    public void Log_in(View view){
        if (!variables.log_email.getText().toString().matches("")&&!variables.log_pass.getText().toString().matches("")){
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, variables.url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.matches("Successfully")){
                        String url="https://cryptoymc.com/lastActions.php";
                        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response){

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Date date=new Date();
                                Map<String,String> map=new HashMap<>();
                                map.put("email",variables.log_email.getText().toString());
                                map.put("date",date.toString());
                                return map;
                            }
                        };requestQueue.add(stringRequest);
                        variables.sharedPreferences=MainActivity.this.getSharedPreferences("com.erenyamic.KingBank",Context.MODE_PRIVATE);
                        variables.sharedPreferences.edit().putString("email",variables.log_email.getText().toString()).apply();
                        Intent intent=new Intent(MainActivity.this,homepage.class);
                        startActivity(intent);
                        finish();
                    }else if(response.matches("Wrong Password")){
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        RequestQueue requestQueue1=Volley.newRequestQueue(getApplicationContext());
                        String urll="https://cryptoymc.com/lastWrongLogin.php";
                        StringRequest stringRequest1=new StringRequest(Request.Method.POST, urll, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Date date2=new Date();
                                Map<String,String> params=new HashMap<>();
                                params.put("date",date2.toString());
                                params.put("email",variables.log_email.getText().toString());
                                return params;
                            }
                        };requestQueue1.add(stringRequest1);

                    }

                    else
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("log_email",variables.log_email.getText().toString());
                    params.put("log_pass",variables.log_pass.getText().toString());
                    return params;
                }
            };requestQueue.add(stringRequest);
        }else
            Toast.makeText(MainActivity.this,"Not Null",Toast.LENGTH_LONG).show();

    }


}


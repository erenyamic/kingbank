package com.erenyamic.KingBank;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Blob;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class profil extends AppCompatActivity {
    variables variables;String email;
    TextView textView,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Ata();
    }
    private void Ata(){
        variables=new variables();
        variables.sharedPreferences=profil.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");
        textView=findViewById(R.id.textView30);textView2=findViewById(R.id.textView31);textView3=findViewById(R.id.textView32);
        textView.setText(email);
        lastSuccessfulLogin();
        lastWrongLogin();
    }
    public void logOut(View view){
        Intent intent=new Intent(profil.this,MainActivity.class);
        startActivity(intent);finish();
    }
    private void lastSuccessfulLogin(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/lastSuccessfullLogin.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    String[] dizi=response.split(" ");
                    textView2.setText(dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]+" "+dizi[5]);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profil.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }
    private void lastWrongLogin(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/wrongLogin.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    String[] dizi=response.split(" ");
                    textView3.setText(dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]+" "+dizi[5]);
                }
                else
                    textView3.setText("-");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profil.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }
    public void deleteAccount(View view){
        Snackbar.make(view,"Are You Sure ?",Snackbar.LENGTH_INDEFINITE).setAction("Delete", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                String url="https://cryptoymc.com/deleteAccount.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.matches("Deleted")){
                            Intent intent=new Intent(profil.this,MainActivity.class);
                            startActivity(intent);finish();
                        }else
                            Toast.makeText(profil.this,"Failed",Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profil.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email",email);
                        return params;
                    }
                };requestQueue.add(stringRequest);
            }
        }).show();
    }
}
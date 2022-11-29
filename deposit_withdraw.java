package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.ActionCodeUrl;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class deposit_withdraw extends AppCompatActivity {
    TextView ibn,balance2,mail,iban;variables variables;String email;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_withdraw);
        Ata();
        getIbn();currentBalance();
    }
    private void Ata(){
        ibn=findViewById(R.id.textView24);
        variables=new variables();
        variables.sharedPreferences=deposit_withdraw.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");
        balance2=findViewById(R.id.balance);
        rl=findViewById(R.id.rl);
        rl.setVisibility(View.GONE);
        mail=findViewById(R.id.balance7);
        iban=findViewById(R.id.balance8);
        String[] dizi=email.split("@");
        mail.setText(dizi[0]);
    }
    private void getIbn(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/getIban.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    ibn.setText(response);
                    iban.setText(response);
                }


                else
                    Toast.makeText(deposit_withdraw.this,"Error",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(deposit_withdraw.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("cmail",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }
    private void currentBalance(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/defaultMoney.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                double balance=Double.parseDouble(response);
                Locale locale =new Locale("en","US");
                NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                balance2.setText(numberFormat.format(balance));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(deposit_withdraw.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("cmail",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }
    public void refresh3(View view){
        Intent refresh=getIntent();
        finish();
        startActivity(refresh);
    }
    public void withdraw(View view){
        rl.setVisibility(View.GONE);
        Intent intent=new Intent(deposit_withdraw.this,send_money.class);
        startActivity(intent);
    }
    public void deposit(View view){
        rl.setVisibility(View.VISIBLE);
    }
}
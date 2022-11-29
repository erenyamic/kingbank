package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Assets extends AppCompatActivity {
    variables variables;
    TextView balance,ibn,sent,received;
    PieChart chart;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        ata();
        variables.sharedPreferences=Assets.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");
        String ibn2=variables.sharedPreferences.getString("ibn","");
        ibn.setText(ibn2);

    }
    private void ata(){
        variables=new variables();
        ibn=findViewById(R.id.textView16);
        sent=findViewById(R.id.textView19);
        sent();
        received=findViewById(R.id.textView18);
        Received();


        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String url2="https://cryptoymc.com/defaultMoney.php";
        StringRequest stringRequest1=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    double balance2=Double.parseDouble(response);
                    Locale locale =new Locale("en","US");
                    NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                    balance.setText(numberFormat.format(balance2));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Assets.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("cmail",email);
                return params;
            }
        };requestQueue.add(stringRequest1);

        balance=findViewById(R.id.balance2);
        chart=findViewById(R.id.chart);
        chart.addPieSlice(new PieModel("Balance", 1,Color.parseColor("#FFBB86FC")));
        chart.startAnimation();
    }


    private void Received() {
        String url="https://cryptoymc.com/totalGet.php";
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    double getM=Double.parseDouble(response);
                    Locale locale=new Locale("en","US");
                    NumberFormat numberFormat= NumberFormat.getCurrencyInstance(locale);
                    received.setText(numberFormat.format(getM));
                }else{
                    double getM=0;
                    Locale locale=new Locale("en","US");
                    NumberFormat numberFormat= NumberFormat.getCurrencyInstance(locale);
                    received.setText(numberFormat.format(getM));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Assets.this,"Refresh the page",Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }

    private void sent() {
        String url="https://cryptoymc.com/totalSent.php";
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.matches("")){
                    double sent2=Double.parseDouble(response);
                    Locale locale=new Locale("en","US");
                    NumberFormat numberFormat= NumberFormat.getCurrencyInstance(locale);
                    sent.setText(numberFormat.format(sent2));
                }else
                {
                    double sent2=0;
                    Locale locale=new Locale("en","US");
                    NumberFormat numberFormat= NumberFormat.getCurrencyInstance(locale);
                    sent.setText(numberFormat.format(sent2));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Assets.this,"Refresh the page",Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest);
    }

    public void home(View view){
        Intent intent= new Intent(Assets.this,homepage.class);
        startActivity(intent);
        finish();
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
    public void refresh2(View view){
        Intent refresh=getIntent();
        finish();
        startActivity(refresh);
    }
    public void transactions2(View view){
        Intent intent=new Intent(Assets.this,transactions.class);
        startActivity(intent);
    }
    public void support2(View view){
        Intent intent=new Intent(Assets.this,support.class);
        startActivity(intent);
    }
}
package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class homepage extends AppCompatActivity {

    TextView textView;
    LinearLayout layout;
    LinearLayout.LayoutParams lp;
    variables variables;String email;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ata();
        variables.sharedPreferences=homepage.this.getSharedPreferences("com.erenyamic.KingBank",Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");


        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/money1.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] dizi= response.split("---");
                for (int i=0;i<dizi.length-1;i++){
                        if (i==0){
                            textView = new TextView(homepage.this);
                            textView.setText("-SENDING MONEY-\n" + dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            textView.setWidth(layout.getWidth());
                            textView.setPadding(30, 20, 30, 0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0, 0, 0, 60);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }
                        else{
                            textView = new TextView(homepage.this);
                            textView.setText("-SENDING MONEY-" + dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            textView.setWidth(layout.getWidth());
                            textView.setPadding(30, 20, 30, 0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0, 0, 0, 60);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
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


        String url2="https://cryptoymc.com/money2.php";
        StringRequest stringRequest1=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] dizi=response.split("---");
                for (int i=0;i<dizi.length-1;i++){
                        if (i==0){
                            textView=new TextView(homepage.this);
                            textView.setText("-GETTING MONEY-\n"+dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            textView.setWidth(layout.getWidth());
                            textView.setPadding(30,20,30,0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0,0,0,60);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }
                        else {
                            textView=new TextView(homepage.this);
                            textView.setText("-GETTING MONEY-"+dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            textView.setWidth(layout.getWidth());
                            textView.setPadding(30,20,30,0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0,0,0,60);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest1);

    }
    private void ata(){
        variables=new variables();
        layout=findViewById(R.id.linear);
        lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        variables.type_account=findViewById(R.id.textView4);
        variables.ibn=findViewById(R.id.textView5);btn1=findViewById(R.id.button26);btn2=findViewById(R.id.button27);
        btn2.setEnabled(false);btn2.setVisibility(View.GONE);
        variables.amount=findViewById(R.id.textView7);
        String url="https://cryptoymc.com/getIban.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                variables.ibn.setText(response);
                variables.sharedPreferences.edit().putString("ibn",response).apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
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


        String url2="https://cryptoymc.com/defaultMoney.php";
        StringRequest stringRequest1=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                double balance=Double.parseDouble(response);
                Locale locale =new Locale("en","US");
                NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                variables.amount.setText(numberFormat.format(balance));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
    public void send_money(View view){
        Intent intent=new Intent(homepage.this,send_money.class);
        startActivity(intent);
    }
    public void refresh(View view){
        Intent refresh=getIntent();
        finish();
        startActivity(refresh);
    }
    public void assets(View view){
        Intent intent=new Intent(homepage.this,Assets.class);
        startActivity(intent);
        finish();
    }
    public void cards(View view){
        String url="https://cryptoymc.com/anyCard.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.matches("yes")){
                    Intent intent=new Intent(homepage.this,Cards.class);
                    startActivity(intent);
                }else if (response.matches("no")){
                    Intent intent=new Intent(homepage.this,CreateNewCard.class);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
    public void depWith(View view){
        Intent intent=new Intent(homepage.this,deposit_withdraw.class);
        startActivity(intent);
    }
    public void profil(View view){
        Intent intent=new Intent(homepage.this,profil.class);
        startActivity(intent);
    }
    public void filter1(View view){
        btn1.setEnabled(false);btn1.setVisibility(View.GONE);layout.removeAllViews();
        btn2.setVisibility(View.VISIBLE);btn2.setEnabled(true);
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/money1.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] dizi= response.split("---");
                for (int i=0;i<dizi.length-1;i++){
                    if (i==0){
                        textView = new TextView(homepage.this);
                        textView.setText("-SENDING MONEY-\n" + dizi[i]);
                        textView.setTextColor(Color.parseColor("#3e4444"));
                        textView.setTextSize(15);
                        textView.setWidth(layout.getWidth());
                        textView.setPadding(30, 20, 30, 0);
                        textView.setBackgroundResource(R.drawable.colored_border);
                        lp.setMargins(0, 0, 0, 60);
                        textView.setLayoutParams(lp);
                        layout.addView(textView);
                    }
                    else{
                        textView = new TextView(homepage.this);
                        textView.setText("-SENDING MONEY-" + dizi[i]);
                        textView.setTextColor(Color.parseColor("#3e4444"));
                        textView.setTextSize(15);
                        textView.setWidth(layout.getWidth());
                        textView.setPadding(30, 20, 30, 0);
                        textView.setBackgroundResource(R.drawable.colored_border);
                        lp.setMargins(0, 0, 0, 60);
                        textView.setLayoutParams(lp);
                        layout.addView(textView);
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
    public void filter2(View view){
        btn2.setEnabled(false);btn2.setVisibility(View.GONE);
        btn1.setVisibility(View.VISIBLE);btn1.setEnabled(true);layout.removeAllViews();
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String url2="https://cryptoymc.com/money2.php";
        StringRequest stringRequest1=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] dizi=response.split("---");
                for (int i=0;i<dizi.length-1;i++){
                    if (i==0){
                        textView=new TextView(homepage.this);
                        textView.setText("-GETTING MONEY-\n"+dizi[i]);
                        textView.setTextColor(Color.parseColor("#3e4444"));
                        textView.setTextSize(15);
                        textView.setWidth(layout.getWidth());
                        textView.setPadding(30,20,30,0);
                        textView.setBackgroundResource(R.drawable.colored_border);
                        lp.setMargins(0,0,0,60);
                        textView.setLayoutParams(lp);
                        layout.addView(textView);
                    }
                    else {
                        textView=new TextView(homepage.this);
                        textView.setText("-GETTING MONEY-"+dizi[i]);
                        textView.setTextColor(Color.parseColor("#3e4444"));
                        textView.setTextSize(15);
                        textView.setWidth(layout.getWidth());
                        textView.setPadding(30,20,30,0);
                        textView.setBackgroundResource(R.drawable.colored_border);
                        lp.setMargins(0,0,0,60);
                        textView.setLayoutParams(lp);
                        layout.addView(textView);
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(homepage.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                return params;
            }
        };requestQueue.add(stringRequest1);
    }
    public void transactions(View view){
        Intent intent=new Intent(homepage.this,transactions.class);
        startActivity(intent);
    }
    public void support(View view){
        Intent intent=new Intent(homepage.this,support.class);
        startActivity(intent);
    }
}

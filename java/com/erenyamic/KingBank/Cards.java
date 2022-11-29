package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
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

public class Cards extends AppCompatActivity {

    TextView cardno,valid,cvc,cardname,cardno2,valid2,cvc2,cardname2,limit;variables variables;String email;
    Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        ata();
    }

    private void ata() {
        cardno=findViewById(R.id.cardno);valid=findViewById(R.id.valid);cvc=findViewById(R.id.cvc);cardname=findViewById(R.id.cardname);
        switch1=findViewById(R.id.switch2);
        cardname2=findViewById(R.id.textView17);cardno2=findViewById(R.id.textView20);valid2=findViewById(R.id.textView21);cvc2=findViewById(R.id.textView22);
        limit=findViewById(R.id.textView13);
        variables=new variables();
        variables.sharedPreferences=Cards.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");
        String url="https://cryptoymc.com/cardInfo.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] dizi= response.split(" ");
                if (dizi[9].matches("successfully")){
                    cardno.setText(dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                    cvc.setText("CVC: "+dizi[4]);
                    cardname.setText(dizi[5]+" "+dizi[6]);
                    valid.setText("VALID: "+dizi[7]);
                    double balance=Double.parseDouble(dizi[8]);
                    Locale locale =new Locale("en","US");
                    NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                    limit.setText("Limit: "+numberFormat.format(balance));
                    cvc2.setText("CVC: "+dizi[4]);
                    cardname2.setText("Name: "+dizi[5]+" "+dizi[6]);
                    cardno2.setText("Card Number: "+dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                    valid2.setText("Discard Date: "+dizi[7]);
                }else{
                    Intent intent=new Intent(Cards.this,CreateNewCard.class);
                    startActivity(intent);finish();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Cards.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
    public void createCard(View view){
                String url ="https://cryptoymc.com/issetCard.php";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.matches("yes")){
                            Intent intent=new Intent(Cards.this,CreateNewCard.class);
                            startActivity(intent);
                        }else if(response.matches("no"))
                            Toast.makeText(Cards.this,"No more cards can be created",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cards.this,error.getMessage(),Toast.LENGTH_LONG).show();
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

    public void check1(View view){
            if (!switch1.isChecked()){
                String url="https://cryptoymc.com/selectCard.php";
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] dizi= response.split(" ");
                        cardno.setText(dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                        cvc.setText("CVC: "+dizi[4]);
                        cardname.setText(dizi[5]+" "+dizi[6]);
                        valid.setText("VALID: "+dizi[7]);
                        double balance=Double.parseDouble(dizi[8]);
                        Locale locale =new Locale("en","US");
                        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                        limit.setText("Limit: "+numberFormat.format(balance));
                        cvc2.setText("CVC: "+dizi[4]);
                        cardname2.setText("Name: "+dizi[5]+" "+dizi[6]);
                        cardno2.setText("Card Number: "+dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                        valid2.setText("Discard Date: "+dizi[7]);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cards.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email",email);
                        params.put("card","1");
                        return params;
                    }
                };requestQueue.add(stringRequest);
            }
            if (switch1.isChecked()){
                String url="https://cryptoymc.com/selectCard.php";
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] dizi= response.split(" ");
                        cardno.setText(dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                        cvc.setText("CVC: "+dizi[4]);
                        cardname.setText(dizi[5]+" "+dizi[6]);
                        valid.setText("VALID: "+dizi[7]);
                        double balance=Double.parseDouble(dizi[8]);
                        Locale locale =new Locale("en","US");
                        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                        limit.setText("Limit: "+numberFormat.format(balance));
                        cvc2.setText("CVC: "+dizi[4]);
                        cardname2.setText("Name: "+dizi[5]+" "+dizi[6]);
                        cardno2.setText("Card Number: "+dizi[0]+" "+dizi[1]+" "+dizi[2]+" "+dizi[3]);
                        valid2.setText("Discard Date: "+dizi[7]);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cards.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email",email);
                        params.put("card","2");
                        return params;
                    }
                };requestQueue.add(stringRequest);
            }
    }

    public void deleteCard(View view){
        String url="https://cryptoymc.com/deleteCard.php",cardNo=cardno.getText().toString();
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.matches("Successfully")){
                    Toast.makeText(Cards.this,"Card Deleted",Toast.LENGTH_LONG).show();
                    Intent refresh=getIntent();
                    finish();
                    startActivity(refresh);
                }
                else if (response.matches("Failed")){
                    Toast.makeText(Cards.this,"Card is not exists",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Cards.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                params.put("cardno",cardNo);
                return params;
            }
        };requestQueue.add(stringRequest);
    }
}
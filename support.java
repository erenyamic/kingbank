package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class support extends AppCompatActivity {

    LinearLayout layout;
    LinearLayout.LayoutParams lp;
    TextView textView;
    variables variables;
    String email;
    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Ata();
    }
    private void Ata(){
        variables=new variables();
        layout=findViewById(R.id.ly5);
        lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        message=findViewById(R.id.editTextTextPersonName3);
        variables.sharedPreferences=support.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");


        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String url="https://cryptoymc.com/getmessage.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.matches("")){
                    String[] dizi= response.split("---");
                    for (int i=0;i<dizi.length-1;i++){
                        if (i==0){
                            textView = new TextView(support.this);
                            textView.setText(dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            //textView.setWidth(layout.getWidth());
                            textView.setPadding(20, 0, 20, 0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0, 0, 0, 50);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }
                        else{
                            textView = new TextView(support.this);
                            textView.setText(dizi[i]);
                            textView.setTextColor(Color.parseColor("#3e4444"));
                            textView.setTextSize(15);
                            //textView.setWidth(layout.getWidth());
                            textView.setPadding(20, 0, 20, 0);
                            textView.setBackgroundResource(R.drawable.colored_border);
                            lp.setMargins(0, 0, 0, 50);
                            textView.setLayoutParams(lp);
                            layout.addView(textView);
                        }

                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(support.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
    public void message(View view){
        if (!message.getText().toString().matches("")){
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            String url="https://cryptoymc.com/message.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.matches("Successfully")){
                        Toast.makeText(support.this,"Message send refresh the page",Toast.LENGTH_LONG).show();
                        message.setText("");
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(support.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String>params=new HashMap<>();
                    params.put("email",email);
                    params.put("message",message.getText().toString());
                    return params;
                }
            };requestQueue.add(stringRequest);
        }
    }
    public void hp(View view){
        Intent intent=new Intent(support.this,homepage.class);
        startActivity(intent);
    }
    public void rf(View view){
        Intent refresh=getIntent();
        finish();
        startActivity(refresh);
    }
}
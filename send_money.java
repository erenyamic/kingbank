package com.erenyamic.KingBank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class send_money extends AppCompatActivity {

    variables variables;
    EditText amount2,iban_no,comment;
    String ibn1,email_now,ibn_now;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        ata();
        variables.sharedPreferences=send_money.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email_now=variables.sharedPreferences.getString("email","");
        ibn_now=variables.sharedPreferences.getString("ibn","");
    }



    public void  ata(){
        variables=new variables();
         amount2=findViewById(R.id.editTextNumber);
         iban_no=findViewById(R.id.editTextTextPersonName);
         comment=findViewById(R.id.comment);
        date=new Date();

    }
    public void money(View view){
        ibn1=iban_no.getText().toString();
        if (!ibn1.matches("")&&!amount2.getText().toString().matches("")&&!amount2.getText().toString().matches("0")&&!ibn_now.matches("")&&!ibn1.matches(ibn_now)){
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            String url="https://cryptoymc.com/sendMoney.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                        if (response.matches("successfully")){
                            Intent intent=new Intent(send_money.this,done.class);
                            startActivity(intent);
                            finish();
                        }else
                            Toast.makeText(send_money.this,"Failed",Toast.LENGTH_LONG).show();
                    }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(send_money.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email_now);
                    params.put("ibn",ibn1);
                    params.put("send",amount2.getText().toString());
                    params.put("comment",comment.getText().toString());
                    params.put("date",date.toString());
                    return params;
                }
            };requestQueue.add(stringRequest);
        }else
            Toast.makeText(send_money.this,"Not Null",Toast.LENGTH_LONG).show();

        if (!ibn1.matches("")&&!amount2.getText().toString().matches("")&&!amount2.getText().toString().matches("0")&&!ibn_now.matches("")&&!ibn1.matches(ibn_now)){
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            String url2="https://cryptoymc.com/getMoney.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.matches("successfully")){
                        Intent intent=new Intent(send_money.this,done.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(send_money.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email_now);
                    params.put("ibn",ibn1);
                    params.put("send",amount2.getText().toString());
                    params.put("comment",comment.getText().toString());
                    params.put("date",date.toString());
                    return params;
                }
            };requestQueue.add(stringRequest);
        }else
            Toast.makeText(send_money.this,"Not Null",Toast.LENGTH_LONG).show();

    }
}
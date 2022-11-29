package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.HashMap;
import java.util.Map;

public class changeLimit extends AppCompatActivity {

    EditText editText,editText1;
    TextView textView;
    variables variables;String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_limit);
        editText=findViewById(R.id.editTextTextEmailAddress6);
        editText1=findViewById(R.id.editTextTextEmailAddress7);
        variables=new variables();
        variables.sharedPreferences=changeLimit.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");textView=findViewById(R.id.textView43);
    }
    public void changeLimit(View view){
        if (!editText.getText().toString().matches("")&&!editText1.getText().toString().matches("")){
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            String url="https://cryptoymc.com/changeCardLimit.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.matches("Successfully"))
                        Toast.makeText(changeLimit.this,"Limit changed",Toast.LENGTH_LONG).show();
                    else if (response.matches("Failed"))
                        Toast.makeText(changeLimit.this,"Wrong, Use Spaces",Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(changeLimit.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String > params=new HashMap<>();
                    params.put("email",email);
                    params.put("cardno",editText.getText().toString());
                    params.put("limit",editText1.getText().toString());
                    return params;
                }
            };requestQueue.add(stringRequest);
        }else
            Toast.makeText(changeLimit.this,"Not Null",Toast.LENGTH_LONG).show();




    }
}
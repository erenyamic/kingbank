package com.erenyamic.KingBank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateNewCard extends AppCompatActivity {

    EditText name,limit;
    String email;variables variables;int sayac=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_card);
        ata();
    }
    private void ata(){
        name=findViewById(R.id.editTextTextEmailAddress3);
        limit=findViewById(R.id.editTextTextEmailAddress5);
        variables=new variables();
        variables.sharedPreferences=CreateNewCard.this.getSharedPreferences("com.erenyamic.KingBank", Context.MODE_PRIVATE);
        email=variables.sharedPreferences.getString("email","");
    }

    public void createCard(View view){
            String url="https://cryptoymc.com/createCard.php";
            RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.matches("successfully")){
                        Toast.makeText(CreateNewCard.this,response,Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(CreateNewCard.this,Cards.class);
                        startActivity(intent);finish();
                    }else if(response.matches("Failed"))
                        Toast.makeText(CreateNewCard.this,response,Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CreateNewCard.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    Date d=new Date();

                    Calendar calendar= Calendar.getInstance();calendar.setTime(d);
                    params.put("email",email);
                    params.put("limit",limit.getText().toString());
                    params.put("name",name.getText().toString());
                    params.put("creation",Integer.toString(calendar.get(Calendar.MONTH)+1)+"/"+Integer.toString(calendar.get(Calendar.YEAR)%2000));
                    params.put("discard",Integer.toString(calendar.get(Calendar.MONTH)+1+5)+"/"+Integer.toString((calendar.get(Calendar.YEAR)%2000)+5));
                    return params;
                }
            };requestQueue.add(stringRequest);

    }
}
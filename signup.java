package com.erenyamic.KingBank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    variables variables;
    int code;String ibn="IBN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ata();
        variables.verify.setVisibility(View.GONE);
        variables.verify_btn.setVisibility(View.GONE);
    }
    private void ata(){
        variables = new variables();
        variables.turn_to_login=findViewById(R.id.button5);
        variables.sign_email= findViewById(R.id.editTextTextEmailAddress2);
        variables.sign_pass=findViewById(R.id.editTextTextPassword2);
        variables.verify=findViewById(R.id.editTextTextEmailAddress4);
        variables.verify_btn=findViewById(R.id.button7);
        variables.getSign_up=findViewById(R.id.button6);




    }
    public void Turn_Login_Page(View view){
        variables.intent=new Intent(signup.this,MainActivity.class);
        startActivity(variables.intent);
    }
    public void Sign_Up_2(View view){
        try{
            String isEmail=variables.sign_email.getText().toString();
            if (!isEmailValid(isEmail)){
                Toast.makeText(signup.this,"Email is not valid",Toast.LENGTH_LONG).show();
            }else{
                if (!variables.sign_email.getText().toString().matches("")&&!variables.sign_pass.getText().toString().matches("")){
                    Random rnd = new Random();
                    code = rnd.nextInt(8999)+1000;
                    String url="https://cryptoymc.com/sendEmail.php";
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(signup.this, response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(signup.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("email",variables.sign_email.getText().toString());
                            params.put("code",String.valueOf(code));
                            return params;
                        }
                    };requestQueue.add(stringRequest);

                    variables.sign_email.setVisibility(View.GONE);
                    variables.sign_pass.setVisibility(View.GONE);
                    variables.verify.setVisibility(View.VISIBLE);
                    variables.verify_btn.setVisibility(View.VISIBLE);
                    variables.getSign_up.setVisibility(View.GONE);
                }else{
                    Toast.makeText(signup.this,"Not Null",Toast.LENGTH_LONG).show();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void Verify(View view){
        String inputCode;
        inputCode=variables.verify.getText().toString();
        if (inputCode.equals(String.valueOf(code))){
            String url="https://cryptoymc.com/signup.php";
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(signup.this,response,Toast.LENGTH_LONG).show();
                    if (response.matches("Successfully"))
                    {
                        Intent intent=new Intent(signup.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(signup.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Random rnd=new Random();int no;
                    for (int i=0;i<=9;i++){
                        no=rnd.nextInt(10)+1;
                        if (ibn.length()<=13){
                            ibn+=Integer.toString(no);
                        }
                    }
                    Map<String,String> map=new HashMap<>();
                    map.put("email",variables.sign_email.getText().toString());
                    map.put("password",variables.sign_pass.getText().toString());
                    map.put("iban",ibn);
                    map.put("resetCode",Integer.toString(code));
                    return map;
                }
            };requestQueue.add(stringRequest);


        }else{
            Toast.makeText(signup.this,"verification failed",Toast.LENGTH_LONG).show();
        }
    }
    public boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
}


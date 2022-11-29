package com.erenyamic.KingBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class transactions extends AppCompatActivity {
variables variables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        variables=new variables();
    }
    public void account(View view){
        Intent intent=new Intent(transactions.this,homepage.class);
        startActivity(intent);finish();
    }
    public void sendmoney(View view){
        Intent intent=new Intent(transactions.this,send_money.class);
        startActivity(intent);
    }
    public void cards(View view){
        Intent intent=new Intent(transactions.this,Cards.class);
        startActivity(intent);
    }
    public void invest(View view){
        Intent intent=new Intent(transactions.this,invest.class);
        startActivity(intent);
    }
    public void limit(View view){
        Intent intent=new Intent(transactions.this,changeLimit.class);
        startActivity(intent);
    }
    public void creditTransactions(View view){
        Intent intent=new Intent(transactions.this,creditCaltulation.class);
        startActivity(intent);
    }
    public void home2(View view){
        Intent intent=new Intent(transactions.this,homepage.class);
        startActivity(intent);finish();
    }
    public void assets2(View view){
        Intent intent=new Intent(transactions.this,Assets.class);
        startActivity(intent);finish();
    }
    public void support3(View view){
        Intent intent=new Intent(transactions.this,support.class);
        startActivity(intent);
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
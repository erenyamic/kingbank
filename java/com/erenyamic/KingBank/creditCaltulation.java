package com.erenyamic.KingBank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class creditCaltulation extends AppCompatActivity {

    EditText editText,editText2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_caltulation);
        editText=findViewById(R.id.editTextNumber2);
        editText2=findViewById(R.id.editTextNumber3);
        textView=findViewById(R.id.textView41);
    }
    public void creditCalculation(View view){
        if (!editText.getText().toString().matches("")&&!editText2.getText().toString().matches("")){
            double amount=Double.parseDouble(editText.getText().toString());
            double expiry=Double.parseDouble(editText2.getText().toString());
            if (amount>=1000&&amount<=9000&&expiry>=3&&expiry<=36){
                double result=amount+((amount*1.91)/100);
                textView.setText("Total Dept: $"+(result)+"\n\nMonthly Payment: $"+result/expiry+"\n\nMonthly Interest Rate: %1,91");
            }else {
                Toast.makeText(creditCaltulation.this,"Check your info",Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(creditCaltulation.this,"Not Null",Toast.LENGTH_LONG).show();
    }
}
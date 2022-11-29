package com.erenyamic.KingBank;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class invest extends AppCompatActivity {
    List<String> dizi;TextView textView;
    EditText editText;
    ArrayList<investclass> investclasses;
    String base_url="https://api.nomics.com/v1/currencies/";
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        Ata();
    }
    private void Ata(){
        dizi=new ArrayList<>();
        Gson gson=new GsonBuilder().setLenient().create();editText=findViewById(R.id.editTextTextPersonName2);textView=findViewById(R.id.textView35);
        retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
        loadData();
    }

    private void loadData() {

        investInterface investInterface=retrofit.create(com.erenyamic.KingBank.investInterface.class);
        Call<List<investclass>> call=investInterface.getData();
        call.enqueue(new Callback<List<investclass>>() {
            @Override
            public void onResponse(Call<List<investclass>> call, Response<List<investclass>> response) {
                if (response.isSuccessful()){
                    List<investclass> responseList = response.body();
                    investclasses=new ArrayList<>(responseList);
                    for (investclass a:investclasses){
                        dizi.add(a.currency);
                        if(a.currency.matches("BTC")) {
                            double coin=Double.parseDouble(a.price);
                            Locale locale =new Locale("en","US");
                            NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                            double marketcap=Double.parseDouble(a.market_cap);
                            double high=Double.parseDouble(a.high);
                            textView.setText(a.currency+": "+numberFormat.format(coin));
                            textView.append("\n\nMax Supply: "+a.max_supply+"\n\nCirculating Supply: "+a.circulating_supply+"\n\nMarket Cap: "+numberFormat.format(marketcap)+"\n\nHigh: "+numberFormat.format(high));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<investclass>> call, Throwable throwable) {
                Toast.makeText(invest.this,"Try Again",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void SearchCoin(View view){
        for (String item:dizi) {
            if (item.matches(editText.getText().toString().toUpperCase())){
                for (investclass a:investclasses){
                    if(a.currency.matches(item)) {
                        double coin=Double.parseDouble(a.price);
                        Locale locale =new Locale("en","US");
                        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
                        double marketcap=Double.parseDouble(a.market_cap);
                        double high=Double.parseDouble(a.high);
                        textView.setText(a.currency+": "+numberFormat.format(coin));
                        textView.append("\n\nMax Supply: "+a.max_supply+"\n\nCirculating Supply: "+a.circulating_supply+"\n\nMarket Cap: "+numberFormat.format(marketcap)+"\n\nHigh: "+numberFormat.format(high));


                    }
                }
            }

        }
    }
}
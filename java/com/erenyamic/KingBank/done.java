package com.erenyamic.KingBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import pl.droidsonroids.gif.GifImageView;

public class done extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        imageView=findViewById(R.id.giff);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanim);
        imageView.startAnimation(animation);
    }
    public void home(View view){
        Intent intent=new Intent(done.this,homepage.class);
        startActivity(intent);finish();
    }
}
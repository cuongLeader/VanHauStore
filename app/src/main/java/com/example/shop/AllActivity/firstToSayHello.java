package com.example.shop.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;

public class firstToSayHello extends AppCompatActivity {
    private static int SPLASH_SCREEN= 5000;
    Animation top_anim, bot_anim;
    ImageView Image;
    TextView name, slogan;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_to_say_hello);
        top_anim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bot_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //
        Image = findViewById(R.id.img);
        name = findViewById(R.id.name_shop);
        slogan = findViewById(R.id.slogan);
        Image.setAnimation(top_anim);
        name.setAnimation(bot_anim);
        slogan.setAnimation(bot_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(firstToSayHello.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
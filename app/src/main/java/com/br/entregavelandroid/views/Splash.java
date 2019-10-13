package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.br.entregavelandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private ImageView imagemSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Encontra a imagem de splash
        imagemSplash = findViewById(R.id.imagem_splash);

        //realiza a troca de tela ao escutar o click na tela de splash
        imagemSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump();
            }
        });

        //Defini quanto tempo vai demorar o Splash
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump();
            }
        }, 3500);
    }

    //defini de para qual activity vai apos o splash
    private void jump(){
        timer.cancel();
        startActivity(new Intent( Splash.this, Login.class));
        finish();
    }
}

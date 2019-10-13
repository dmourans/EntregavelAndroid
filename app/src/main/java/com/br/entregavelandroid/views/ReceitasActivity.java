package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.models.Receita;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ReceitasActivity extends AppCompatActivity {

    private CollapsingToolbarLayout toolbarLayout;
    private ImageView imagemReceita;
    private TextView detalheReceita;

    public static final String RECEITA = "receita";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.toolbar_layout);
        imagemReceita = findViewById(R.id.image_receitas);
        detalheReceita = findViewById(R.id.content_receitas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            Receita receita = getIntent().getExtras().getParcelable(RECEITA);
            Drawable drawable = getResources().getDrawable(receita.getFotoPrato());

            toolbarLayout.setTitle(receita.getNomePrato());
            imagemReceita.setImageDrawable(drawable);
            detalheReceita.setText(receita.getInforPrato());

        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

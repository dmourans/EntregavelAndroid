package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.adapters.ReceitaAdapter;
import com.br.entregavelandroid.interfaces.ReceitaOnClick;
import com.br.entregavelandroid.models.Receita;
import com.br.entregavelandroid.models.Restaurante;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import static com.br.entregavelandroid.views.MainActivity.RESTAURANTE;
import static com.br.entregavelandroid.views.ReceitasActivity.RECEITA;

public class RestaurantesActivity extends AppCompatActivity implements ReceitaOnClick {

    private CollapsingToolbarLayout toolbarLayout;
    private ImageView imagemRestaurante;
    private RecyclerView recyclerReceitas;
    private ReceitaAdapter receitaAdapter;
    private List<Receita> receitaList = new ArrayList<>();
    private Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.toolbar_layout);
        imagemRestaurante = findViewById(R.id.image_restaurante);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null){
            restaurante = getIntent().getExtras().getParcelable(RESTAURANTE);
            Drawable drawable = getResources().getDrawable(restaurante.getFotoRestaurante());

            toolbarLayout.setTitle(restaurante.getNome());
            imagemRestaurante.setImageDrawable(drawable);
            receitaList = restaurante.getListaDeReceita();
        }

        recyclerReceitas = findViewById(R.id.receitas_recyclerView);
        receitaAdapter = new ReceitaAdapter(receitaList, this);
        recyclerReceitas.setAdapter(receitaAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerReceitas, false);
        recyclerReceitas.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void onClick(Receita receita) {
        Intent intent = new Intent(RestaurantesActivity.this, ReceitasActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECEITA, receita);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

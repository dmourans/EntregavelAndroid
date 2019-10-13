package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.adapters.RestauranteAdapter;
import com.br.entregavelandroid.interfaces.RestauranteOnClick;
import com.br.entregavelandroid.models.Receita;
import com.br.entregavelandroid.models.Restaurante;
import com.br.entregavelandroid.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import static com.br.entregavelandroid.views.Login.NEW_USER;

public class MainActivity extends AppCompatActivity implements RestauranteOnClick {

    private RecyclerView recyclerRestaurante;
    private RestauranteAdapter adapterRestaurante;
    private List<Restaurante> listaRestaurante = new ArrayList<>();
    private List<Receita> listaReceitas;

    public static final String RESTAURANTE = "restaurante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerRestaurante = findViewById(R.id.recycler_main);
        adapterRestaurante = new RestauranteAdapter(listarRestaurantes(), this);
        recyclerRestaurante.setAdapter(adapterRestaurante);
        recyclerRestaurante.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Restaurante> listarRestaurantes() {
        listaRestaurante.add(new Restaurante("Bulguer", "Av. Paulista, 100 - Cerqueira Cesar, São Paulo", "Fecha às 22h", R.drawable.bulguer, listarReceitas()));
        listaRestaurante.add(new Restaurante("Madero", "Av. Nove de Julho, 200 - Itaim Bibi, São Paulo", "Fecha às 0h", R.drawable.restaurante1, listarReceitas()));
        listaRestaurante.add(new Restaurante("McDonalds", "Av. Paulista, 100 - Cerqueira Cesar, São Paulo", "Fecha às 23h", R.drawable.mcdonalds, listarReceitas()));
        listaRestaurante.add(new Restaurante("Burguer King", "Av. Paulista, 100 - Cerqueira Cesar, São Paulo", "Fecha às 23:45h", R.drawable.bk, listarReceitas()));

        return listaRestaurante;
    }

    private List<Receita> listarReceitas() {
        listaReceitas = new ArrayList<>();
        listaReceitas.add(new Receita("Especial de Carne", R.drawable.bulguer1, getString(R.string.texto_grande_receita)));
        listaReceitas.add(new Receita("Especial de Frango", R.drawable.bulguer2, getString(R.string.texto_grande_receita)));
        listaReceitas.add(new Receita("Especial de Bacon", R.drawable.bulguer3, getString(R.string.texto_grande_receita)));
        return listaReceitas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            bundleToProfile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Restaurante restaurante) {
        Intent intent = new Intent(MainActivity.this, RestaurantesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESTAURANTE, restaurante);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void bundleToProfile() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Usuario usuario = getIntent().getExtras().getParcelable(NEW_USER);
            Intent Intent = new Intent(MainActivity.this, Perfil.class);
            Bundle Bundle = new Bundle();
            Bundle.putParcelable(NEW_USER, usuario);
            Intent.putExtras(Bundle);
            startActivity(Intent);
        }
    }
}

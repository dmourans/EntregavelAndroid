package com.br.entregavelandroid.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.interfaces.RestauranteOnClick;
import com.br.entregavelandroid.models.Restaurante;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder> {

    private List<Restaurante> restauranteLista;
    private RestauranteOnClick listener;

    public RestauranteAdapter(List<Restaurante> restauranteLista, RestauranteOnClick listener) {
        this.restauranteLista = restauranteLista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteAdapter.ViewHolder holder, int position) {
        final Restaurante restaurante = restauranteLista.get(position);
        holder.onBind(restaurante);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(restaurante);
            }
        });

    }

    @Override
    public int getItemCount() {
        return restauranteLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageRestaurante;
        private TextView nomeRestaurante;
        private TextView enderecoRestaurante;
        private TextView horarioRestaurante;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageRestaurante = itemView.findViewById(R.id.item_imagemRestaurante);
            nomeRestaurante = itemView.findViewById(R.id.item_nomeRestaurante);
            enderecoRestaurante = itemView.findViewById(R.id.item_enderecoRestaurante);
            horarioRestaurante = itemView.findViewById(R.id.item_horarioRestaurante);

        }

        public void onBind(Restaurante restaurante) {
            Drawable drawable = itemView.getResources().getDrawable(restaurante.getFotoRestaurante());

            imageRestaurante.setImageDrawable(drawable);
            nomeRestaurante.setText(restaurante.getNome());
            enderecoRestaurante.setText(restaurante.getEndereco());
            horarioRestaurante.setText(restaurante.getHorario());
        }
    }
}

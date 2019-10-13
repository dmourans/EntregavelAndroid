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
import com.br.entregavelandroid.interfaces.ReceitaOnClick;
import com.br.entregavelandroid.models.Receita;

import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ViewHolder> {
    private List<Receita> listaReceita;
    private ReceitaOnClick listener;

    public ReceitaAdapter(List<Receita> listaReceita, ReceitaOnClick listener) {
        this.listaReceita = listaReceita;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Receita receita = listaReceita.get(position);

        holder.onBind(receita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(receita);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaReceita.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        //variaveis dos items
        private ImageView imagemReceita;
        private TextView nomeReceita;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //localizo no layout do item os campos
            imagemReceita = itemView.findViewById(R.id.item_imagemReceita);
            nomeReceita = itemView.findViewById(R.id.item_nomeReceita);

        }

        public void onBind(Receita receita) {

            Drawable drawable = itemView.getResources().getDrawable(receita.getFotoPrato());

            //set as imagens variaveis nos campos de tela
            imagemReceita.setImageDrawable(drawable);
            nomeReceita.setText(receita.getNomePrato());
        }
    }
}

package com.emendoza.pkmmaster;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.emendoza.pkmmaster.Entities.ePoke;

import java.util.ArrayList;

public class listaPokemonAdapter extends RecyclerView.Adapter<listaPokemonAdapter.ViewHolder> {

    private ArrayList<ePoke> dataset;
    private Context context;

    public listaPokemonAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }
    @NonNull
    @Override
    public listaPokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaPokemonAdapter.ViewHolder viewHolder, int i) {
        ePoke p = dataset.get(i);
        viewHolder.nombre_poke.setText(p.getName());


        Glide.with(context).load(p.getSprites().getFront_default())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagen_poke);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<ePoke> listaPokemon) {
        dataset.clear();
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen_poke;
        private TextView nombre_poke;

        public ViewHolder(View itemView) {
            super(itemView);

            imagen_poke = (ImageView) itemView.findViewById(R.id.imagen_poke);
            nombre_poke = (TextView) itemView.findViewById(R.id.nombre_poke);
        }
    }
}
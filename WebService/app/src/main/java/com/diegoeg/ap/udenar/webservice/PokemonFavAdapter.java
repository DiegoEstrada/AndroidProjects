package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonFavAdapter extends RecyclerView.Adapter<PokemonFavAdapter.PokemonFavViewHolder> {

    private ArrayList<Pokemon> pokemones;

    public  PokemonFavAdapter (ArrayList<Pokemon> list)  {
        pokemones = list;
    }


    @NonNull
    @Override
    public PokemonFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_favourite,parent,false);

        return new PokemonFavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonFavViewHolder holder, int position) {
        TextView mtvNombre = holder.tvNombre;
        ImageView ivPicture = holder.ivFoto;
        Context c = holder.c;

        mtvNombre.setText( FragmentListaPokemones.stringName+" : " +pokemones.get(position).getName());
        Picasso.with(c).load(pokemones.get(position).getUrl()).fit().centerInside().into(ivPicture);
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public  class PokemonFavViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre;
        public ImageView ivFoto;
        public Context c;

        public PokemonFavViewHolder(View itemView) {
            super(itemView);
            c = itemView.getContext();
            tvNombre  = itemView.findViewById(R.id.tvNombrePokemonInicial);
            ivFoto = itemView.findViewById(R.id.ivPicture);
        }
    }
}

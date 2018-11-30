package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class PokemonInitialAdapter extends RecyclerView.Adapter<PokemonInitialAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemones;



    public PokemonInitialAdapter(ArrayList<Pokemon> list){
        pokemones = list;
    }


    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(mContext).inflate(R.layout.nombre_pokemon,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon,parent,false);
        return  new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PokemonViewHolder holder, int position) {

        TextView mtvNombre = holder.tvNombre;

        mtvNombre.setText( MainActivity.stringName+" : " +pokemones.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return pokemones.size();
    }



    public class  PokemonViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            tvNombre  = itemView.findViewById(R.id.tvNombrePokemonInicial);

        }
    }
}

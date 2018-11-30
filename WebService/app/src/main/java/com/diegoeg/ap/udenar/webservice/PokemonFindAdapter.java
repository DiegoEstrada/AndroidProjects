package com.diegoeg.ap.udenar.webservice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonFindAdapter extends RecyclerView.Adapter<PokemonFindAdapter.PokemonFindViewHolder> {

    private ArrayList<Pokemon> pokemones;

    public PokemonFindAdapter(ArrayList<Pokemon> list)  {
        pokemones = list;
    }


    @Override
    public PokemonFindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nombre_pokemon,parent,false);
        return  new PokemonFindAdapter.PokemonFindViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PokemonFindViewHolder holder, int position) {
        TextView mtvNombre = holder.tvNombre;
        TextView mtvHab1 = holder.tvHab1;
        TextView mtvHab2 = holder.tvHab2;
        TextView mtvExp = holder.tvExp;


        mtvNombre.setText( MainActivity.stringName+" : " +pokemones.get(position).getName());
        mtvHab1.setText(MainActivity.stringHab1+" : "+ pokemones.get(position).getHab1());
        mtvHab2.setText(MainActivity.stringNHab2+" : "+pokemones.get(position).getHab2());
        mtvExp.setText(MainActivity.stringExp+" = "+pokemones.get(position).getExp());
    }

    @Override
    public int getItemCount() {
        return pokemones.size();
    }


    public class  PokemonFindViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre;
        public TextView tvHab1;
        public TextView tvHab2;
        public TextView tvExp;

        public PokemonFindViewHolder(View itemView) {
            super(itemView);
            tvNombre  = itemView.findViewById(R.id.textViewNombre);
            tvHab1 = itemView.findViewById( R.id.textViewHab1);
            tvHab2 = itemView.findViewById(R.id.textViewHab2);
            tvExp = itemView.findViewById(R.id.textViewExperience);
        }
    }


}

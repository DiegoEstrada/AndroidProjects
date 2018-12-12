package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonFindAdapter extends RecyclerView.Adapter<PokemonFindAdapter.PokemonFindViewHolder>  {

    private ArrayList<Pokemon> pokemones;


    public PokemonFindAdapter(ArrayList<Pokemon> list)  {
        pokemones = list;
    }


    @Override
    public PokemonFindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card,parent,false);

        return  new PokemonFindViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PokemonFindViewHolder holder, int position) {
        TextView mtvNombre = holder.tvNombre;
        TextView mtvHab = holder.tvHab;
        TextView mtvTip = holder.tvTip;
        TextView mtvPeso = holder.tvPeso;
        ImageView ivPicture = holder.ivFoto;
        Context c = holder.c;

        mtvNombre.setText( FragmentListaPokemones.stringName+" : " +pokemones.get(position).getName());
        mtvHab.setText( FragmentListaPokemones.stringAbility+" : " +pokemones.get(position).getHab());
        mtvTip.setText( FragmentListaPokemones.stringType+" : " +pokemones.get(position).getType());
        mtvPeso.setText( FragmentListaPokemones.stringWeigth+" : " +pokemones.get(position).getWeigth());
        Picasso.with(c).load(pokemones.get(position).getUrl()).fit().centerInside().into(ivPicture);

    }

    @Override
    public int getItemCount() {
        return pokemones.size();
    }




    public class  PokemonFindViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre;
        public TextView tvHab;
        public TextView tvTip;
        public TextView tvPeso;
        public ImageView ivFoto;
        public Context c;

        public PokemonFindViewHolder(View itemView) {
            super(itemView);
            c = itemView.getContext();
            tvNombre  = itemView.findViewById(R.id.tvNombrePokemonInicial);
            tvHab = itemView.findViewById(R.id.tvAbility);
            tvTip = itemView.findViewById(R.id.tvType);
            tvPeso = itemView.findViewById(R.id.tvWeight);
            ivFoto = itemView.findViewById(R.id.ivPicture);


        }
    }


}

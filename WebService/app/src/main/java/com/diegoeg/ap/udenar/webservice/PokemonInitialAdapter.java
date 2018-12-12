package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PokemonInitialAdapter extends RecyclerView.Adapter<PokemonInitialAdapter.PokemonViewHolder>   {

    private ArrayList<Pokemon> pokemones;
    private OnItemClickListener mListener;

    public  interface OnItemClickListener { void onItemClick(int position);}





    public PokemonInitialAdapter(ArrayList<Pokemon> list){
        pokemones = list;
    }


    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        //View v = LayoutInflater.from(mContext).inflate(R.layout.nombre_pokemon,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nombre_pokemon,parent,false);

        final PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v);


        pokemonViewHolder.tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textView = v.findViewById(R.id.tvNombrePokemonInicial) ;
                String nombre = textView.getText().toString();

                Context context = parent.getContext();
                CharSequence text = "Hello toast!" + nombre;
                int duration = Toast.LENGTH_SHORT;

                FragmentDetallePokemon detallePokemon = FragmentDetallePokemon.newInstance(nombre);

                MainActivity.lanzar(detallePokemon);

                Toast toast = Toast.makeText(context, text, duration);
                //toast.show();
            }
        });


        return  new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PokemonViewHolder holder, int position) {

        TextView mtvNombre = holder.tvNombre;
        mtvNombre.setText( FragmentListaPokemones.stringName+" : " +pokemones.get(position).getName());



        /*
        TextView mtvPeso = holder.tvPeso;
        TextView mtvHabilidad = holder.tvHabilidad;
        TextView mtvTipo = holder.tvTipo;
        ImageView ivFoto = holder.ivFoto;






        mtvTipo.setText(MainActivity.stringType + " : "+pokemones.get(position).getType());
        mtvPeso.setText(MainActivity.stringWeigth+ " = "+pokemones.get(position).getWeigth());
        mtvHabilidad.setText(MainActivity.stringAbility+ " : "+pokemones.get(position).getHab());

    */
    }

    @Override
    public int getItemCount() {
        return pokemones.size();
    }



    public class  PokemonViewHolder extends RecyclerView.ViewHolder  {

        public TextView tvNombre;


        public ImageView ivFoto;
        public TextView tvTipo;
        public TextView tvHabilidad;
        public TextView tvPeso;


        public PokemonViewHolder(View itemView) {
            super(itemView);
            tvNombre  = itemView.findViewById(R.id.tvNombrePokemonInicial);

            //itemView.setOnClickListener(this); //LIGANDO EL listener

            /*
            tvTipo = itemView.findViewById(R.id.tvType);
            tvHabilidad = itemView.findViewById(R.id.tvAbility);
            tvPeso = itemView.findViewById(R.id.tvWeight);
            ivFoto = itemView.findViewById(R.id.ivPicture);

                */

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
            */
        }

    }
}

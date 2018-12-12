package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetallePokemon extends AppCompatActivity {
    public  static final String NOMBRE = "name";
    private RequestQueue otherRequestQueue;
    private TextView tvNombre;
    private  TextView tvHabilidad;
    private TextView tvTipo;
    private TextView tvPeso;
    private ImageView ivFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         final Context context = this;
        otherRequestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_detalle_pokemon);

        tvNombre = findViewById(R.id.tvNombrePokemonInicial);
        tvHabilidad = findViewById(R.id.tvAbility);
        tvPeso = findViewById(R.id.tvWeight);
        tvTipo = findViewById(R.id.tvType);
        ivFoto = findViewById(R.id.ivPicture);

        Intent intent = getIntent();
        final String nombre = intent.getStringExtra(NOMBRE);
        String urlDetalle = "https://pokeapi.co/api/v2/pokemon/";

        tvNombre.setText(nombre);

        final JsonObjectRequest requestDetalle = new JsonObjectRequest

                (Request.Method.GET, urlDetalle+"/"+nombre+"/", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray abilityes = response.getJSONArray("abilities");

                            JSONArray types = response.getJSONArray("types");
                            JSONObject sprites = response.getJSONObject("sprites");


                            //JSONObject weight = response.getJSONObject("weight");
                            JSONObject abilityDetail = abilityes.getJSONObject(0);
                            JSONObject typeDetail = types.getJSONObject(0);

                            JSONObject ability = abilityDetail.getJSONObject("ability");
                            JSONObject type =typeDetail.getJSONObject("type");


                            String habilidadPokemon = ""+ability.getString("name");
                            String tipoPokemon = ""+type.getString("name");
                            String imagenPokemon = sprites.getString("front_default");
                            String pesoPokemon = ""+response.getString("weight");


                            tvHabilidad.setText(FragmentListaPokemones.stringAbility+" : "+habilidadPokemon);
                            tvTipo.setText(FragmentListaPokemones.stringType+" : "+tipoPokemon);
                            tvPeso.setText(FragmentListaPokemones.stringWeigth+" : "+pesoPokemon);
                            Picasso.with(context).load(imagenPokemon).fit().centerInside().into(ivFoto);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        otherRequestQueue.add(requestDetalle);


    }
}

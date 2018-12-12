package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentElectrico.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentElectrico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentElectrico extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private   String[] POKEMONES_ELECTRICOS = {"pikachu","electabuzz","manectric" };
            //"","dedenne","elekid","pichu","zapdos","voltorb"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context c;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private ArrayList<Pokemon> listaPokemones = new ArrayList();
    private RequestQueue mRequestQueue;
    private Retrofit retrofit;
    /*
    private  ArrayList<String> nameAbility = new ArrayList();
    private  ArrayList<String> nameType = new ArrayList();
    private  ArrayList<String> namePic = new ArrayList();
    private  ArrayList<String> weigth = new ArrayList();
    private  ArrayList<String> namePok = new ArrayList();
    */
    private TextView tvNombre;
    private TextView tvHabilidad;
    private TextView tvPeso;
    private TextView tvTipo;
    private ImageView ivFoto;
    private FragmentActivity myContext;

    private OnFragmentInteractionListener mListener;

    public FragmentElectrico() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentElectrico.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentElectrico newInstance(String param1, String param2) {
        FragmentElectrico fragment = new FragmentElectrico();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_electrico, container, false);

        c = view.getContext();


        recyclerView =  view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mRequestQueue = Volley.newRequestQueue(view.getContext());

         retrofit = new Retrofit.Builder().
                baseUrl("https://pokeapi.co/api/v2/").
                addConverterFactory(GsonConverterFactory.create()).build() ;

        parseJSON();
        //cargarJSON();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        myContext = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void parseJSON() {



        for (int i = 0; i<POKEMONES_ELECTRICOS.length; i++) {


             final String nombrePokemon = POKEMONES_ELECTRICOS[i];


            //tvNombre.setText(FragmentListaPokemones.stringName + " : "+ POKEMONES_ELECTRICOS[i]);


            String url = "https://pokeapi.co/api/v2/pokemon/"+nombrePokemon+"/";
            Log.w("LLEGO",""+url);


             JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray jsonArrayAb = response.getJSONArray("abilities");
                                JSONObject hitAb = jsonArrayAb.getJSONObject(0);
                                JSONObject ability = hitAb.getJSONObject("ability");

                                String  nameAbility = ( ability.getString("name"));


                                JSONArray jsonArrayTy = response.getJSONArray("types");
                                JSONObject hitTy = jsonArrayTy.getJSONObject(0);
                                JSONObject type = hitTy.getJSONObject("type");

                                String nameType = (type.getString("name"));


                                JSONObject jsonArrayPc = response.getJSONObject("sprites");
                                 String namePic = ( jsonArrayPc.getString("front_default"));


                                 String weigth =( response.getString("weight"));

                                Log.w("NAME","Name "+nombrePokemon);
                                Log.w("HAB","HAbilty "+nameAbility);
                                Log.w("URL","IMG "+namePic);
                                Log.w("TYOPE","TIPO "+nameType);
                                Log.w("WEIGHT","PESO "+weigth);

                                Pokemon p = new Pokemon(nombrePokemon,nameAbility,nameType,namePic,weigth);

                                Log.w("POKEMON ","CREADODO"+p.toString());

                                listaPokemones.add(p);

                                /*
                                tvNombre.setText(FragmentListaPokemones.stringName + " : "+ POKEMONES_ELECTRICOS[i]);
                                tvTipo.setText(FragmentListaPokemones.stringType+" : "+nameType);
                                tvHabilidad.setText(FragmentListaPokemones.stringAbility+" : "+nameAbility);
                                tvPeso.setText(FragmentListaPokemones.stringType+" : "+weigth);
                                Picasso.with(myContext).load(namePic).fit().centerInside().into(ivFoto);
                                */

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

            mRequestQueue.add(request);


        }

        Log.w("POKEELEC",""+listaPokemones.size());

        for (int l = 0; l < listaPokemones.size(); l++){

            Log.w("CREATED ",""+listaPokemones.get(1).toString());
        }

        PokemonFindAdapter findAdapter = new PokemonFindAdapter(listaPokemones);
        recyclerView.setAdapter(findAdapter);


    }

    private void cargarJSON() {
        RestClient service = retrofit.create(RestClient.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.getData();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {

            @Override
            public void onResponse(Call<PokemonRespuesta> call, retrofit2.Response<PokemonRespuesta> response) {
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();

                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                    for(int i=0;i<listaPokemon.size();i++)
                    {
                        Pokemon p = listaPokemon.get(i);
                        Log.i("MSJ"," Pokemon: "+p.getName()+" url: "+p.getUrl());
                    }

                }else
                {
                    Log.e("ERR", "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e("ERRR"," onFailure: "+t.getMessage());
            }
        });






    }
}

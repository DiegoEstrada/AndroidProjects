package com.diegoeg.ap.udenar.webservice;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListaPokemones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListaPokemones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListaPokemones extends Fragment implements PokemonInitialAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private   View v;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public  Comunicacion comunicacion;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Context c;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private ArrayList<Pokemon> listaPokemones;
    private RequestQueue mRequestQueue;

    static  String stringName;
    static  String  stringHab1 ;
    static String  stringNHab2 ;
    static String  stringWeigth ;
    static String  stringAbility;
    static String  stringType ;


    private OnFragmentInteractionListener mListener;

    public FragmentListaPokemones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListaPokemones.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListaPokemones newInstance(String param1, String param2) {
        FragmentListaPokemones fragment = new FragmentListaPokemones();
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
            v = inflater.inflate(R.layout.fragment_fragment_lista_pokemones, container, false);
            c = v.getContext();
          stringName = getString(R.string.crdNombre);
          stringHab1 = getString(R.string.crdHab1);
          stringNHab2 = getString(R.string.crdHab2);
          stringWeigth = getString(R.string.crdPeso);
          stringAbility = getString(R.string.crdHab);
          stringType = getString(R.string.crdTipo);


        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listaPokemones = new ArrayList();

        /*
        for (int i = 0; i < Data.names.length; i++) {
            listaPokemones.add(new Pokemon(
                    Data.names[i]

            ));
        }

        adapter = new PokemonInitialAdapter(listaPokemones);
        recyclerView.setAdapter(adapter);
        */
        mRequestQueue = Volley.newRequestQueue(v.getContext());

        //otherRequestQueue = Volley.newRequestQueue(this);
       parseJSON();


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        try {
            comunicacion  = (Comunicacion) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        try {
            comunicacion  = (Comunicacion) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }


*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        Context context = v.getContext();
        CharSequence text = "Hello toast! POSTITIION "+position;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



    /*
    @Override
    public void onItemClick(int position) {
        Context context = v.getContext();
        CharSequence text = "Hello toast! POSTITIION "+position;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
*/
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

    private void parseJSON(){
        String url = "https://pokeapi.co/api/v2/pokemon/";



        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String urlDetalle = "https://pokeapi.co/api/v2/pokemon/";
                            JSONArray jsonArray = response.getJSONArray("results");
                            //System.out.println("TAMANIO "+jsonArray.length());
                            Log.w("TAM","T "+jsonArray.length());
                            int j = 0;
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject hit = jsonArray.getJSONObject(i);

                                String pokemonName = hit.getString("name");
                                final Pokemon p = new Pokemon(pokemonName);
                                j = i++;
                                Pokemon po = p;
                                final JsonObjectRequest requestDetalle = new JsonObjectRequest

                                        (Request.Method.GET, urlDetalle+"/"+j+"/", null, new Response.Listener<JSONObject>() {




                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    JSONArray abilityes = response.getJSONArray("abilities");
                                                    //Log.w("TAMA","T "+abilityes.length());

                                                    //JSONArray types = response.getJSONArray("types");
                                                    //JSONArray sprites = response.getJSONArray("sprites");

                                                    //JSONObject image = response.getJSONObject("front_default");
                                                    //JSONObject weight = response.getJSONObject("weight");
                                                    JSONObject abilityDetail = abilityes.getJSONObject(0);
                                                    //JSONObject typeDetail = types.getJSONObject(0);

                                                    JSONObject ability = abilityDetail.getJSONObject("ability");


                                                    //Log.w("ABLT","ABILITYE "+ability.getString("name"));


                                                    //po.setHab(""+ability.getString("name"));
                                                    //pokemonData[1] = "";

                                                    //pokemonData[3] = ""+weight.getString("weight");


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
                                //bueno = new Pokemon(po.getName());

                                //otherRequestQueue.add(requestDetalle);



                                listaPokemones.add(new Pokemon(pokemonName));
                                //listaPokemones.add(new Pokemon(pokemonName));
                            }


                            PokemonInitialAdapter initialAdapter = new PokemonInitialAdapter( listaPokemones);
                            recyclerView.setAdapter(initialAdapter);
                            //initialAdapter.setOnItemClickListener(this);
                            //initialAdapter.setClickListener(MainActivity.this);
                            //initialAdapter.setOnItemClickListener(MainActivity.this);


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
}

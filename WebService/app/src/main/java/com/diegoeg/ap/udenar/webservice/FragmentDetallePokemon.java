package com.diegoeg.ap.udenar.webservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDetallePokemon.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDetallePokemon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetallePokemon extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private  static String name = "";
    private RequestQueue mRequestQueue;
    private TextView tvNombre;
    private TextView tvHabilidad;
    private TextView tvPeso;
    private TextView tvTipo;
    private ImageView ivFoto;
    private FragmentActivity myContext;


    //private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "ExampleFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentDetallePokemon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment FragmentDetallePokemon.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetallePokemon newInstance(String param1) {
        FragmentDetallePokemon fragment = new FragmentDetallePokemon();
        name = param1;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_pokemon, container, false);
        //Context context = getApplicationContext();
        //CharSequence text = "Hello toast!";
        //int duration = Toast.LENGTH_SHORT;

        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();
        tvNombre = v.findViewById(R.id.tvNombrePokemonInicial);
        tvHabilidad = v.findViewById(R.id.tvAbility);
        tvPeso = v.findViewById(R.id.tvWeight);
        tvTipo = v.findViewById(R.id.tvType);
        ivFoto = v.findViewById(R.id.ivPicture);


        tvNombre.setText(""+mParam1);

        Button btnCerrar = v.findViewById(R.id.button);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment fragment = null;
                Class fragmentClass= FragmentListaPokemones.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentListaPokemones fragmentListaPokemones = new FragmentListaPokemones();
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frgContendio,fragmentListaPokemones);
                transaction.commit();
            }
        });


        mRequestQueue = Volley.newRequestQueue(v.getContext());
        parseJSON();
        return v;
    }

    protected void obtenerNombre(String name){
        tvNombre.setText(name);
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

        myContext=(FragmentActivity) getActivity();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public Context getApplicationContext() {
        return this.getContext();
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

    private void parseJSON(){

        String[] pokemon =mParam1.split(":");
        String nombrePoemon = pokemon[1].trim();


        String url = "https://pokeapi.co/api/v2/pokemon/"+nombrePoemon+"/";

        Log.w("CHEKING ",url);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArrayAb = response.getJSONArray("abilities");
                            JSONObject hitAb = jsonArrayAb.getJSONObject(0);
                            JSONObject ability = hitAb.getJSONObject("ability");

                            String nameAbility = ability.getString("name");


                            JSONArray jsonArrayTy = response.getJSONArray("types");
                            JSONObject hitTy = jsonArrayTy.getJSONObject(0);
                            JSONObject type = hitTy.getJSONObject("type");

                            String nameType = type.getString("name");


                            JSONObject jsonArrayPc = response.getJSONObject("sprites");
                            String namePic = jsonArrayPc.getString("front_default");


                            String weigth = response.getString("weight");


                            Log.w("ABLT","ABILITYE "+ability.getString("name"));


                            tvTipo.setText(FragmentListaPokemones.stringType+" : "+nameType);
                            tvHabilidad.setText(FragmentListaPokemones.stringAbility+" : "+nameAbility);
                            tvPeso.setText(FragmentListaPokemones.stringType+" : "+weigth);
                            Picasso.with(myContext).load(namePic).fit().centerInside().into(ivFoto);



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

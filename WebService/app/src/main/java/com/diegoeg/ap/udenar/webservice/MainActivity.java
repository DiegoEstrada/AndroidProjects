package com.diegoeg.ap.udenar.webservice;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentListaPokemones.OnFragmentInteractionListener,
        FragmentAbout.OnFragmentInteractionListener,
        FragmentElectrico.OnFragmentInteractionListener,
        FragmentFavourite.OnFragmentInteractionListener,
        Comunicacion
        //HeadlinesFragment.OnHeadlineSelectedListener
        {

    public static final String NOMBRE = "name";
            private static FragmentManager fragmentManager;
            private static Context cLanzar;
            private static View vLanzar;
            private static Activity tLanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        cLanzar = this.getBaseContext();
        tLanzar = this;
        vLanzar = getLayoutInflater().inflate(R.layout.fragment_detalle_pokemon, null);

        FragmentListaPokemones fragmentListaPokemones = new FragmentListaPokemones();
        FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frgContendio,fragmentListaPokemones);
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            /*
            Fragment fragment = null;
            Class fragmentClass= FragmentAbout.class;
            try{
                fragment = (Fragment) fragmentClass.newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frgContendio, fragment).commit();



             */

            AlertDialog.Builder uBuilder = new AlertDialog.Builder(this);
            View aView = getLayoutInflater().inflate(R.layout.fragment_fragment_about, null);
            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

            Button btnCerrar = aView.findViewById(R.id.btnCloseAboutFragment);

            btnCerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favourite) {

            /*
            AlertDialog.Builder uBuilder = new AlertDialog.Builder(this);
            View aView = getLayoutInflater().inflate(R.layout.fragment_fragment_favourite, null);
            //View v2 = getLayoutInflater().inflate(R.layout.pokemon_favourite, null);;
            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

            Button btnCerrar = aView.findViewById(R.id.btnCerrarRecycler);

            btnCerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });

            */
            FragmentFavourite favourite = new FragmentFavourite();
            FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frgContendio,favourite);
            transaction.commit();

        } else if (id == R.id.nav_electric) {

            FragmentElectrico electrico = new FragmentElectrico();
            FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frgContendio,electrico);
            transaction.commit();

            /*
            AlertDialog.Builder uBuilder = new AlertDialog.Builder(this);
            View aView2 = getLayoutInflater().inflate(R.layout.pokemon, null);
            View aView = getLayoutInflater().inflate(R.layout.fragment_fragment_electrico, null);

            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

            Button btnCerrar = aView2.findViewById(R.id.btnClose);

            btnCerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });

        */
        } else if (id == R.id.nav_water) {

        } else if (id == R.id.nav_fire) {

        } else if (id == R.id.nav_share) {

            AlertDialog.Builder uBuilder = new AlertDialog.Builder(this);
            View aView = getLayoutInflater().inflate(R.layout.fragment_fragment_about, null);
            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

            Button btnCerrar = aView.findViewById(R.id.btnCloseAboutFragment);

            btnCerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void comunicar(String nombre) {

        FragmentDetallePokemon detallePokemon = (FragmentDetallePokemon) getSupportFragmentManager().findFragmentById(R.id.frgContendio);
        detallePokemon.obtenerNombre(nombre);

        AlertDialog.Builder uBuilder = new AlertDialog.Builder(this);
        View aView = getLayoutInflater().inflate(R.layout.fragment_detalle_pokemon, null);
        uBuilder.setView(aView);
        final AlertDialog dialog = uBuilder.create();
        dialog.show();

        Button btnCerrar = aView.findViewById(R.id.button);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });



    }





    public static void lanzar(FragmentDetallePokemon detallePokemon){
        //FragmentDetallePokemon detallePokemon = FragmentDetallePokemon.newInstance(nombre);

        /*
        AlertDialog.Builder uBuilder = new AlertDialog.Builder(tLanzar);
        View aView = vLanzar;
        uBuilder.setView(aView);



        final AlertDialog dialog = uBuilder.create();


        dialog.show();


        Button btnCerrar = aView.findViewById(R.id.button);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        */
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgContendio,detallePokemon);
        transaction.commit();
    }






        }

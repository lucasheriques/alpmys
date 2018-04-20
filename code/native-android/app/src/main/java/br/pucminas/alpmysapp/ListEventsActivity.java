package br.pucminas.alpmysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.APIServices;
import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private APIServices mAPIServices;
    private String listUsuario;

    private static final String KEY_NOME = "nome";
    private static final String KEY_DATA = "data";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_HORARIOINICIO = "horarioInicio";
    private static final String KEY_HORARIOTERMINO = "horarioTermino";
    private static final String KEY_LINKPAGINA = "linkPagina";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listaEventos();
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
        getMenuInflater().inflate(R.menu.alpmys_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list_event) {
            Intent listEventActivity = new Intent(this, ListEventsActivity.class);
            startActivity(listEventActivity);
        }
        else if(id == R.id.nav_principal){
            Intent alpmysMainActivity = new Intent(this, AlpmysMainActivity.class);
            startActivity(alpmysMainActivity);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void listaEventos() {

        final List<Evento> listEvento = new ArrayList<>();

        mAPIServices = RetrofitClient.getAPIService();

        mAPIServices.getEventos().enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful()) {
                    listEvento.addAll(response.body());

                    exibeEventos(listEvento);

                    Log.i("info", response.body().toString());
                    Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.e("ERROR", "Unable to submit post to API.");
            }
        });
    }

    private void exibeEventos(List<Evento> listEvento){
        ListView lblEventos = (ListView) findViewById(R.id.list_view);
        if (lblEventos.getVisibility() == View.GONE)
            lblEventos.setVisibility(View.VISIBLE);

        List<HashMap<String, String>> mapEvento = listToHash(listEvento);

        ListAdapter adapter = new SimpleAdapter(ListEventsActivity.this, mapEvento, R.layout.activity_list_item,
                new String[] { KEY_NOME, KEY_DATA, KEY_DESCRICAO, KEY_HORARIOINICIO, KEY_HORARIOTERMINO, KEY_LINKPAGINA },
                new int[] { R.id.nome,R.id.data, R.id.descricao, R.id.horarioInicio, R.id.horarioTermino, R.id.linkPagina });

        lblEventos.setAdapter(adapter);
    }

    private List<HashMap<String, String>> listToHash(List<Evento> listEvento){
        List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

        for(Evento evento: listEvento){
            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_NOME, evento.getNome());

            map.put(KEY_DATA, evento.getData());

            map.put(KEY_DESCRICAO, evento.getDescricao());

            map.put(KEY_HORARIOINICIO, evento.getHorarioInicio());

            map.put(KEY_HORARIOTERMINO, evento.getHorarioTermino());

            map.put(KEY_LINKPAGINA, evento.getLinkPagina());

            mAndroidMapList.add(map);
        }

        return mAndroidMapList;
    }
}

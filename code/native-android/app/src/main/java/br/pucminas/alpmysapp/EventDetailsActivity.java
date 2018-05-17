package br.pucminas.alpmysapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import api.APIServices;
import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String nomeDoEvento;

    public static final String MyPREFERENCES = "MyPrefs";
    private SharedPreferences sharedPreferences;

    private APIServices mAPIServices;

    private static final String KEY_NOME = "nome";
    private static final String KEY_DATA = "data";
    private static final String KEY_LOCAL = "NomeLocal";
    private static final String KEY_ENDERECO = "endereco";
    private static final String KEY_CIDADE = "cidade";
    private static final String KEY_ESTADO = "estado";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_HORARIOINICIO = "horarioInicio";
    private static final String KEY_HORARIOTERMINO = "horarioTermino";
    private static final String KEY_LINKPAGINA = "linkPagina";

    private ListView lblEventos;

    private final List<Evento> listEvento = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
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

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        nomeDoEvento = sharedPreferences.getString("nomeEvento", "none");
        Evento evento = new Evento();
        evento.setNome(nomeDoEvento);

        lblEventos = (ListView) findViewById(R.id.listViewDetails);

        mAPIServices = RetrofitClient.getAPIService();

        mAPIServices.getEventosByName(evento).enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                Log.i("info", "Teste: "+response.toString());
                if (response.isSuccessful()) {
                    int i = 0;
                    listEvento.addAll(response.body());

                    formataData();

                    //getEvento(listEvento);

                    exibeEventos(listEvento);

                    Log.i("info", response.body().toString());
                    Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.e("ERROR", "Can not query get for API. Throwable: "+t.getMessage().toString());
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void formataData(){
            for(Evento evento : listEvento){
                SimpleDateFormat sdfSource = new SimpleDateFormat(
                        "dd-MM-yyyy");

                SimpleDateFormat sdfSourceHour = new SimpleDateFormat(
                        "h:mm a");

                // parse the string into Date object
                try {
                    Date date = sdfSource.parse(evento.getData());
                    evento.setData(sdfSource.format(date));

                    String strHora = evento.getHorarioInicio().substring(11, 19);

                    evento.setHorarioInicio(strHora);

                    strHora = evento.getHorarioTermino().substring(11, 19);

                    evento.setHorarioTermino(strHora);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
    }

    private void exibeEventos(List<Evento> listEvento){

        if (lblEventos.getVisibility() == View.GONE)
            lblEventos.setVisibility(View.VISIBLE);

        List<HashMap<String, String>> mapEvento = listToHash(listEvento);

        ListAdapter adapter = new SimpleAdapter(EventDetailsActivity.this, mapEvento, R.layout.content_event_details,
                new String[] { KEY_NOME, KEY_LOCAL, KEY_ENDERECO, KEY_CIDADE, KEY_ESTADO, KEY_DATA, KEY_DESCRICAO, KEY_HORARIOINICIO, KEY_HORARIOTERMINO, KEY_LINKPAGINA },
                new int[] { R.id.nome, R.id.local, R.id.endereco, R.id.cidade, R.id.estado, R.id.data, R.id.descricao, R.id.horarioInicio, R.id.horarioTermino, R.id.linkPagina });

        lblEventos.setAdapter(adapter);
    }

    private List<HashMap<String, String>> listToHash(List<Evento> listEvento){
        List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

        for(Evento evento: listEvento){
            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_NOME, "Nome: " + evento.getNome());

            map.put(KEY_LOCAL, "Local: " + evento.getNomeLocal());

            map.put(KEY_ENDERECO, "Endereço: " + evento.getEnderecoCompleto());

            map.put(KEY_CIDADE, "Cidade: " + evento.getCidade());

            map.put(KEY_ESTADO, "Estado: " + evento.getEstado());

            map.put(KEY_DATA, "Data: " + evento.getData());

            map.put(KEY_DESCRICAO, "Descrição: " + evento.getDescricao());

            map.put(KEY_HORARIOINICIO, "Horário de Inicio do Evento: " +  evento.getHorarioInicio());

            map.put(KEY_HORARIOTERMINO, "Horário de Término do Evento: " +   evento.getHorarioTermino());

            map.put(KEY_LINKPAGINA, "Link da Página: " + evento.getLinkPagina());

            mAndroidMapList.add(map);
        }

        return mAndroidMapList;
    }

}

package br.pucminas.alpmysapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import android.widget.AdapterView.OnItemClickListener;

public class ListEventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private APIServices mAPIServices;
    private String listUsuario;

    private static final String KEY_NOME = "nome";
    private static final String KEY_DATA = "data";
    private static final String KEY_CIDADE = "cidade";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_HORARIOINICIO = "horarioInicio";
    private static final String KEY_HORARIOTERMINO = "horarioTermino";
    private static final String KEY_LINKPAGINA = "linkPagina";

    private ListView lblEventos;
    private ArrayAdapter<Evento> listAdapter;

    private Evento[] listViewAdapterContent;
    private final List<Evento> listEvento = new ArrayList<>();

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

        lblEventos = (ListView) findViewById(R.id.listView);

        lblEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] dadosEvento = lblEventos.getItemAtPosition(i).toString().split("=");

                int pos = 4;
                for(int j = 0; j < dadosEvento.length; j++){
                    if(dadosEvento[j].contains("nome:")){
                        pos = j;
                        j = dadosEvento.length;
                    }
                }

                String nomeEvento = dadosEvento[pos];
                nomeEvento = nomeEvento.substring(6, nomeEvento.lastIndexOf(","));

                String MyPREFERENCES = "MyPrefs";

                SharedPreferences sharedPreferences;
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nomeEvento", nomeEvento);
                editor.apply();

                Intent eventDetailsActivity = new Intent(ListEventsActivity.this, EventDetailsActivity.class);
                startActivity(eventDetailsActivity);
            }
        });

        listaEventos(false);

        listViewAdapterContent = listEvento.toArray(new Evento[listEvento.size()]);

        //friendListAdapter = new FriendListAdapter(this, null);

        listAdapter = new ArrayAdapter<Evento>(this, R.layout.activity_list_events, R.id.inputSearch, listViewAdapterContent);

        EditText filterText;
        EditText filterTextCity;
        EditText filterTextDate;

        filterText = (EditText)findViewById(R.id.inputSearch);

        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Info", s.toString());
                if(s.toString().isEmpty()){
                    listaEventos(true);
                }
                else{
                    ListEventsActivity.this.listAdapter.getFilter().filter(s);

                    ListEventsActivity.this.lblEventos.setTextFilterEnabled(true);
                    ListEventsActivity.this.lblEventos.setFilterText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        filterTextCity = (EditText)findViewById(R.id.inputSearchCity);

        filterTextCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    listaEventos(true);
                }
                else {

                    Log.i("Info", s.toString());
                    //ListEventsActivity.this.listAdapter.getItem(1).getNome().get
                    ListEventsActivity.this.listAdapter.getFilter().filter(s);

                    ListEventsActivity.this.lblEventos.setTextFilterEnabled(true);
                    ListEventsActivity.this.lblEventos.setFilterText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        filterTextDate = (EditText)findViewById(R.id.inputSearchDate);

        filterTextDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    listaEventos(true);
                }
                else {

                    Log.i("Info", s.toString());
                    //ListEventsActivity.this.listAdapter.getItem(1).getNome().get
                    ListEventsActivity.this.listAdapter.getFilter().filter(s);

                    ListEventsActivity.this.lblEventos.setTextFilterEnabled(true);
                    ListEventsActivity.this.lblEventos.setFilterText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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


    public void listaEventos(final Boolean reload) {

        mAPIServices = RetrofitClient.getAPIService();

        mAPIServices.getEventos().enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                Log.i("info", "Teste: "+response.toString());
                if (response.isSuccessful()) {
                    int i = 0;
                    listEvento.addAll(response.body());

                    formataData(reload);

                    //getEvento(listEvento);

                    exibeEventos(listEvento);

                    Log.i("info", response.body().toString());
                    Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.e("ERROR", "Can not query get for API.");
            }
        });
    }

    private void formataData(Boolean reload){
        if(!reload){
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
    }

    private void exibeEventos(List<Evento> listEvento){

        if (lblEventos.getVisibility() == View.GONE)
            lblEventos.setVisibility(View.VISIBLE);

        List<HashMap<String, String>> mapEvento = listToHash(listEvento);

        ListAdapter adapter = new SimpleAdapter(ListEventsActivity.this, mapEvento, R.layout.activity_list_item,
                new String[] { KEY_NOME, KEY_CIDADE, KEY_DATA, KEY_DESCRICAO, KEY_HORARIOINICIO, KEY_HORARIOTERMINO, KEY_LINKPAGINA },
                new int[] { R.id.nome,R.id.cidade, R.id.data, R.id.descricao, R.id.horarioInicio, R.id.horarioTermino, R.id.linkPagina });

        lblEventos.setAdapter(adapter);
    }

    private List<HashMap<String, String>> listToHash(List<Evento> listEvento){
        List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

        for(Evento evento: listEvento){
            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_NOME, "Nome: " + evento.getNome());

            map.put(KEY_CIDADE, "Cidade: " + evento.getCidade());

            map.put(KEY_DATA, "Data: " + evento.getData());

            map.put(KEY_DESCRICAO, "Descrição: " + evento.getDescricao());

            map.put(KEY_HORARIOINICIO, "Horário de Inicio do Evento: " +  evento.getHorarioInicio());

            map.put(KEY_HORARIOTERMINO, "Horário de Término do Evento: " +   evento.getHorarioTermino());

            map.put(KEY_LINKPAGINA, "Link da Página: " + evento.getLinkPagina());

            mAndroidMapList.add(map);
        }

        return mAndroidMapList;
    }

    private void getEvento(List<Evento> listEvento){
        int i = 0;
        Log.i("info", "Inicio getEvento()");

        for(Evento evento : listEvento){
            listViewAdapterContent[i] = evento;
        }

    }

    private void teste(String selectedItem){
        Log.i("INFO", selectedItem);
    }

}

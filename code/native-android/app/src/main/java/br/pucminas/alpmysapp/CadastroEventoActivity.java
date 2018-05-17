package br.pucminas.alpmysapp;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import api.APIServices;

import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Endereco;
import br.pucminas.alpmysapp.models.Evento;
import br.pucminas.alpmysapp.models.Local;
import br.pucminas.alpmysapp.models.TipoIngresso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.design.widget.TabLayout.*;
import static java.time.LocalDateTime.of;


public class CadastroEventoActivity extends AppCompatActivity implements LocalFragment.OnFragmentInteractionListener,EventoFragment.OnFragmentInteractionListener,EnderecoFragment.OnFragmentInteractionListener,IngressoFragment.OnFragmentInteractionListener{
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TextInputEditText tiedtNomeEvento, tiedtDescricao,tiedtHoraInicio,tiedtHoraTermino,tiedtLinkPagina;
    private   SimpleDraweeView draweeView;
    private TabLayout tabLayout;
    private Button buttonCadastro;
    private APIServices mAPIServices;
    private Evento evento=new Evento();
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        evento.setLocal(new Local());
        evento.getLocal().setEndereco(new Endereco());
        evento.setTipoIngressos(new ArrayList<TipoIngresso>());
        Fresco.initialize(this);
        setContentView(R.layout.activity_cadastro_evento);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAPIServices= RetrofitClient.getAPIService();
                mAPIServices.createEvento(evento).enqueue(new Callback<Evento>() {
                    @Override
                    public void onResponse(Call<Evento> call, Response<Evento> response) {
                        if (response.isSuccessful()) {
                            Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                            Intent intent=new Intent(CadastroEventoActivity.this,AlpmysMainActivity.class);
                            startActivity(intent);
                        }else{
                            Log.i("ERRO",response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Evento> call, Throwable t) {
                        Log.e("ERROR", "Unable to submit post to API.");
                    }
                });

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Evento"));
        tabLayout.addTab(tabLayout.newTab().setText("Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Endereço"));
        tabLayout.addTab(tabLayout.newTab().setText("Ingresso"));
        tabLayout.setTabGravity(GRAVITY_FILL);
        draweeView = (SimpleDraweeView) findViewById(R.id.image_view_evento);
        draweeView.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                final AlertDialog.Builder dialog = new AlertDialog.Builder(CadastroEventoActivity.this);
                final EditText input = new EditText(CadastroEventoActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog.setView(input);
                dialog.setTitle("Url Imagem");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adicionarImagem(input.getText().toString());
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
            }
        });
        Log.i("EVENTO", evento.toString());
        final ViewPager viewPager = findViewById(R.id.viewpager);
        final PagerAdapter pagerAdapter = new PageAdapterEvento(getSupportFragmentManager(), evento);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }
    public void adicionarImagem(String link){
        try {
            Uri uri = Uri.parse(link);
            draweeView.setImageURI(uri);
        }catch (Exception e){
            Log.i("ERRO","sintaxe errada");
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

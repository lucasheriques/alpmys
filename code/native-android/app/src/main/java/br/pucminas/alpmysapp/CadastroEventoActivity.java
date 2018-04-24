package br.pucminas.alpmysapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;

import api.APIServices;

import api.RetrofitClient;
import models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadastroEventoActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TextInputEditText tiedtNomeEvento, tiedtDescricao,tiedtHoraInicio,tiedtHoraTermino,tiedtLinkPagina;
    private Button buttonCadastro;
    private APIServices mAPIServices;
    private Evento evento=new Evento();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_cadastro_evento);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                final AlertDialog.Builder dialog = new  AlertDialog.Builder(CadastroEventoActivity.this);
                final EditText input=new EditText(CadastroEventoActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT );
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

        tiedtNomeEvento = (TextInputEditText) findViewById(R.id.tiedtNomeEvento);
        tiedtDescricao = (TextInputEditText) findViewById(R.id.tiedtDescricao);
        tiedtHoraInicio=(TextInputEditText) findViewById(R.id.tiedtHorarioInicio);
        tiedtHoraTermino=(TextInputEditText) findViewById(R.id.tiedtHorarioTermino);
        tiedtLinkPagina=(TextInputEditText) findViewById(R.id.tiedtLinkPagina);
        buttonCadastro=(Button) findViewById(R.id.buttonCadastro);

        tiedtHoraInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int ano=calendar.get(Calendar.YEAR);
                int mes=calendar.get(Calendar.MONTH);
                int dia=calendar.get(Calendar.DAY_OF_MONTH);
                int hora=calendar.get(Calendar.HOUR_OF_DAY);
                int minuto=calendar.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog2=new TimePickerDialog(CadastroEventoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        tiedtHoraInicio.setText(hora+":"+minuto+" do dia "+tiedtHoraInicio.getText().toString());
                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(CadastroEventoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog2.show();
                        tiedtHoraInicio.setText(+dia+"/"+mes+"/"+ano);
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
        tiedtHoraTermino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int ano=calendar.get(Calendar.YEAR);
                int mes=calendar.get(Calendar.MONTH);
                int dia=calendar.get(Calendar.DAY_OF_MONTH);
                int hora=calendar.get(Calendar.HOUR_OF_DAY);
                int minuto=calendar.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog=new TimePickerDialog(CadastroEventoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        tiedtHoraTermino.setText(hora+":"+minuto+" do dia "+tiedtHoraTermino.getText().toString());
                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(CadastroEventoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog.show();
                        tiedtHoraTermino.setText(+dia+"/"+mes+"/"+ano);
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
        tiedtNomeEvento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try{
                    evento.setNome(tiedtNomeEvento.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtNomeEvento.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }

            }
        });
        tiedtDescricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try{
                    evento.setDescricao(tiedtDescricao.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtDescricao.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtLinkPagina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAPIServices= RetrofitClient.getAPIService();
                mAPIServices.createEvento(evento).enqueue(new Callback<Evento>() {
                    @Override
                    public void onResponse(Call<Evento> call, Response<Evento> response) {
                        if (response.isSuccessful()) {
                            Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                            startActivity(new Intent(CadastroEventoActivity.this,AlpmysMainActivity.class));
                        }else{
                            try {
                                tiedtDescricao.setText(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //edtDescricao.setText(response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Evento> call, Throwable t) {
                        Log.e("ERROR", "Unable to submit post to API.");
                        tiedtDescricao.setText("falhow");
                    }
                });

            }
        });
    }

    public void adicionarImagem(String link){
        try {
            Uri uri = Uri.parse(link);
            SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.image_view_evento);
            draweeView.setImageURI(uri);
        }catch (Exception e){
            Log.i("ERRO","sintaxe errada");
            e.printStackTrace();
        }
    }


}

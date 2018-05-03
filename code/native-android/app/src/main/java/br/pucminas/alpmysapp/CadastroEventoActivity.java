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
import android.support.design.widget.TextInputEditText;
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
import java.util.Calendar;
import java.util.Date;

import api.APIServices;

import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.time.LocalDateTime.of;


public class CadastroEventoActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TextInputEditText tiedtNomeEvento, tiedtDescricao,tiedtHoraInicio,tiedtHoraTermino,tiedtLinkPagina;
    private Button buttonCadastro;
    private APIServices mAPIServices;
    private Evento evento=new Evento();
    private String data;
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
        buttonCadastro.setEnabled(false);
        tiedtHoraInicio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtHoraInicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtHoraInicio.getText().toString().isEmpty()){
                    tiedtHoraInicio.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }
        });
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
                        calendar.set(Calendar.HOUR_OF_DAY,hora);
                        calendar.set(Calendar.MINUTE,minuto);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        Date data=calendar.getTime();
                        simpleDateFormat.format(data);
                        tiedtHoraInicio.setText(simpleDateFormat.format(data));
                        String dataText [] =simpleDateFormat.format(data).split(" ");
                        evento.setHorarioInicio(dataText[0]+"T"+dataText[1]+".00Z");



                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(CadastroEventoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog2.show();
                        calendar.set(Calendar.YEAR, ano);
                        calendar.set(Calendar.MONTH, mes);
                        calendar.set(Calendar.DAY_OF_MONTH, dia);

                    }
                },ano,mes,dia);
                datePickerDialog.show();


            }
        });
        tiedtHoraTermino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtHoraTermino.getText().toString().isEmpty()){
                    tiedtHoraTermino.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtHoraTermino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
                        calendar.set(Calendar.HOUR_OF_DAY,hora);
                        calendar.set(Calendar.MINUTE,minuto);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        Date data=calendar.getTime();
                        tiedtHoraTermino.setText(simpleDateFormat.format(data));
                        String dataText [] =simpleDateFormat.format(data).split(" ");
                        evento.setHorarioTermino(dataText[0]+"T"+dataText[1]+".00Z");

                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(CadastroEventoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog.show();
                        calendar.set(Calendar.YEAR, ano);
                        calendar.set(Calendar.MONTH, mes);
                        calendar.set(Calendar.DAY_OF_MONTH, dia);
                    }
                },ano,mes,dia);
                datePickerDialog.show();

            }
        });
        tiedtNomeEvento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtNomeEvento.getText().toString().isEmpty()){
                    tiedtNomeEvento.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtNomeEvento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonCadastro.setEnabled(validaForm());
                try{
                    evento.setNome(tiedtNomeEvento.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtNomeEvento.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
                buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtDescricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtDescricao.getText().toString().isEmpty()){
                    tiedtDescricao.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.setDescricao(tiedtDescricao.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtDescricao.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
                buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* mAPIServices= RetrofitClient.getAPIService();
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
                    }
                });*/
                Intent intent=new Intent(CadastroEventoActivity.this,LocalActivity.class);
                intent.putExtra("evento",  evento);
                startActivity(intent);
            }
        });
    }
    public boolean validaForm(){
        if(tiedtNomeEvento.getText().toString().isEmpty()||tiedtHoraTermino.getText().toString().isEmpty()||tiedtHoraInicio.getText().toString().isEmpty()||tiedtDescricao.getText().toString().isEmpty()){
            return false;
        }
        return true;
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

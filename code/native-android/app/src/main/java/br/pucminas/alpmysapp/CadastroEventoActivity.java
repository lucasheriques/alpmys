package br.pucminas.alpmysapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import api.APIServices;
import api.RetrofitClient;
import br.com.sapereaude.maskedEditText.MaskedEditText;
import models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroEventoActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private EditText edtNomeEvento, edtDescricao;
    private MaskedEditText medtData, medtHoraInicio, medtHoraTermino;
    private Button buttonCadastro;
    private APIServices mAPIServices;
    private Evento evento = new Evento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        edtNomeEvento = (EditText) findViewById(R.id.editTextNomeEvento);
        medtData = (MaskedEditText) findViewById(R.id.editTextData);
        medtHoraInicio = (MaskedEditText) findViewById(R.id.editTextHoraInicio);
        medtHoraTermino = (MaskedEditText) findViewById(R.id.editTextHoraTermino);
        edtDescricao = (EditText) findViewById(R.id.editTextDescricao);
        buttonCadastro = (Button) findViewById(R.id.buttonCadastroEvento);
        edtNomeEvento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (edtNomeEvento.getText().toString().isEmpty()) {
                    edtNomeEvento.setError(getBaseContext().getString(R.string.errorNomeEventoVazio));
                }

            }
        });
        medtData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (medtData.getText().toString().isEmpty()) {
                    medtData.setError(getBaseContext().getString(R.string.errorDataVazio));
                }
            }
        });
        medtHoraInicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (medtHoraInicio.getText().toString().isEmpty()) {
                    medtHoraInicio.setError(getBaseContext().getString(R.string.errorHoraInicioVazio));
                }
            }
        });
        medtHoraTermino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (medtHoraTermino.getText().toString().isEmpty()) {
                    medtHoraTermino.setError(getBaseContext().getString(R.string.errorHoraTerminoVazio));
                }
            }
        });
        edtDescricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (edtDescricao.getText().toString().isEmpty()) {
                    edtDescricao.setError(getBaseContext().getString(R.string.errorNomeEventoVazio));
                } else {

                }
            }
        });
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               evento=new Evento();
               evento.setDescricao(edtDescricao.getText().toString());
               evento.setNome(edtNomeEvento.getText().toString());
               evento.setHorarioTermino("2018-04-19T13:19:03.090Z");
               evento.setHorarioInicio("2018-04-19T13:19:03.090Z");
               evento.setLinkPagina("gooogle.com");
                mAPIServices= RetrofitClient.getAPIService();
                mAPIServices.createEvento(evento).enqueue(new Callback<Evento>() {
                    @Override
                    public void onResponse(Call<Evento> call, Response<Evento> response) {
                        if (response.isSuccessful()) {
                            Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                            edtDescricao.setText("deu camarada");
                        }else{
                            try {
                                edtDescricao.setText(response.errorBody().string());
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
                });

            }
        });
    }

    public boolean validaForm() {
        if (!edtNomeEvento.getText().toString().isEmpty() && !medtData.getText().toString().isEmpty() && !medtHoraInicio.getText().toString().isEmpty() && !medtHoraTermino.getText().toString().isEmpty() && !edtDescricao.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

}

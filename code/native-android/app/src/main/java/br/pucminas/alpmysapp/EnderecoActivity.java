package br.pucminas.alpmysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import api.APIServices;
import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Endereco;
import br.pucminas.alpmysapp.models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnderecoActivity extends AppCompatActivity {
    private TextInputEditText tiedtLogradouro,tiedtNumero,tiedtComplemento,tiedtBairro,tiedtCidade,tiedtUf,tiedtCep;
    private APIServices  mAPIServices;
    private Evento evento;
    private Endereco endereco;
    private Button continuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        endereco=new Endereco();
        evento=(Evento) getIntent().getSerializableExtra("evento");
        tiedtLogradouro=(TextInputEditText) findViewById(R.id.tiedtLogradouro);
        tiedtNumero=(TextInputEditText) findViewById(R.id.tiedtNumero);
        tiedtComplemento=(TextInputEditText) findViewById(R.id.tiedtComplemento);
        tiedtBairro=(TextInputEditText) findViewById(R.id.tiedtBairro);
        tiedtCidade=(TextInputEditText) findViewById(R.id.tiedtCidade);
        tiedtUf=(TextInputEditText) findViewById(R.id.tiedtUf);
        tiedtCep=(TextInputEditText) findViewById(R.id.tiedtCep);
        continuar=(Button)findViewById(R.id.buttonContinuar);
        tiedtLogradouro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setLogradouro(tiedtLogradouro.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtLogradouro.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setNumero(Integer.parseInt(tiedtNumero.getText().toString()));
                }catch (IllegalArgumentException iae){
                    tiedtNumero.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtComplemento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setComplemento(tiedtComplemento.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtComplemento.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtBairro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setBairro(tiedtBairro.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtBairro.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtCidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setCidade(tiedtCidade.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtCidade.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtUf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setUf(tiedtUf.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtUf.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    endereco.setCep(tiedtCep.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtCep.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAPIServices= RetrofitClient.getAPIService();
                evento.getLocal().setEndereco(endereco);
                mAPIServices.createEvento(evento).enqueue(new Callback<Evento>() {
                    @Override
                    public void onResponse(Call<Evento> call, Response<Evento> response) {
                        if (response.isSuccessful()) {
                            Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                            Intent intent=new Intent(EnderecoActivity.this,AlpmysMainActivity.class);
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


    }
}

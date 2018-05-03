package br.pucminas.alpmysapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import br.pucminas.alpmysapp.models.Evento;
import br.pucminas.alpmysapp.models.Local;

public class LocalActivity extends AppCompatActivity {
    private TextInputEditText tiedtNomeLocal,tiedtCapacidadePessoas,tiedtDescricao;
    private Button continuar;
    private Evento evento ;
    private Local local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        evento=(Evento) getIntent().getSerializableExtra("evento");
        local=new Local();
        tiedtNomeLocal=(TextInputEditText)findViewById(R.id.tiedtNomeLocal);
        tiedtCapacidadePessoas=(TextInputEditText)findViewById(R.id.tiedtCapacidadePessoas);
        tiedtDescricao=(TextInputEditText)findViewById(R.id.tiedtDescricaoLocal);
        continuar=(Button)findViewById(R.id.buttonContinuarLocal);
        continuar.setEnabled(false);
        tiedtNomeLocal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    local.setNome(tiedtNomeLocal.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtNomeLocal.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
                continuar.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    local.setDescricao(tiedtDescricao.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtDescricao.setError(getBaseContext().getString(R.string.tiedt_vazio));
                }
                continuar.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtCapacidadePessoas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty())
                    tiedtCapacidadePessoas.setError(getBaseContext().getString(R.string.tiedt_vazio));
                else
                    local.setCapacidadeDePessoas(Integer.parseInt(tiedtCapacidadePessoas.getText().toString()));
                continuar.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evento.setLocal(local);
                Intent intent=new Intent(LocalActivity.this,EnderecoActivity.class);
                intent.putExtra("evento", evento);
                startActivity(intent);
            }
        });

}
    public boolean validaForm(){
        if(tiedtNomeLocal.getText().toString().isEmpty()||tiedtDescricao.getText().toString().isEmpty()||tiedtCapacidadePessoas.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}

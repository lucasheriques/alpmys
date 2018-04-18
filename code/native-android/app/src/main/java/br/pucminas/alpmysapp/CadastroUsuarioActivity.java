package br.pucminas.alpmysapp;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import api.APIServices;
import api.RetrofitClient;
import models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private APIServices mAPIServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        mAPIServices = RetrofitClient.getAPIService();
    }

    public void criarUsuario(View view) {
        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtCPF = findViewById(R.id.txtCPF);
        EditText txtSenha = findViewById(R.id.txtSenha);

        Usuario usuario = new Usuario(txtNome.getText().toString(), txtEmail.getText().toString(), txtSenha.getText().toString(), txtCPF.getText().toString());

        mAPIServices.createUsuario(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    mostrarResposta("Usuario criado com sucesso!");
                    Log.i("SUCESSO", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR", "Unable to submit post to API.");
            }
        });
    }

    public void mostrarResposta(String resposta) {
        TextView lblResposta = (TextView) findViewById(R.id.lblResponse);
        if (lblResposta.getVisibility() == View.GONE)
            lblResposta.setVisibility(View.VISIBLE);
        lblResposta.setText(resposta);
    }
}


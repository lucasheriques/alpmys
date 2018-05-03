package br.pucminas.alpmysapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import api.APIServices;
import api.RetrofitClient;
import br.pucminas.alpmysapp.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogin extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    private APIServices mAPIServices;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mAPIServices = RetrofitClient.getAPIService();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("click", "google");
                googleSignIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Log.i("onStart", "onStart");
    }

    public void showSnackbar(View view, String message, int duration) {
        // Create snackbar
        final Snackbar snackbar = Snackbar.make(view, message, duration);

        // Set an action on it, and a handler
        snackbar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

    public void formSignUp(View v) {
        Intent signUpActivity = new Intent(this, SignupActivity.class);
        startActivity(signUpActivity);
    }

    public void formSignIn(View v) {
        boolean errors = false;

        AutoCompleteTextView mEmailView = findViewById(R.id.signin_email);
        EditText mPasswordView = findViewById(R.id.signin_password);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            errors = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            errors = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            errors = true;
        }

        if (errors) {
            focusView.requestFocus();
        }
        else {
            Usuario user = new Usuario(email, password);
            mAPIServices.getUsuarioByEmail(user).enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful())
                        successfulLogin(response.body().getEmail());
                    else {
                        showSnackbar(findViewById(R.id.login_layout), "Usuario ou senha incorretos", 3000);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    showSnackbar(findViewById(R.id.login_layout), "Falha de conexão com a API", 3000);
                }
            });
        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }

    static final int RC_SIGN_IN = 1;

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            Log.i("SUCESSO-LOGIN", "SUCESSO COM LOGIN " + account.getEmail());

            final Usuario user = new Usuario(account.getEmail());

            mAPIServices.getUsuarioByEmail(user).enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        showSnackbar(findViewById(R.id.login_layout), "Login feito com sucesso!", 2000);
                        successfulLogin(response.body().getEmail());

                    } else if (response.code() == 404) {
                        mAPIServices.createUsuario(user).enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                showSnackbar(findViewById(R.id.login_layout), "Cadastrado com sucesso!", 3);
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("ERROR", "Unable to submit post to API " + t.getMessage());
                }
            });

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERRO LOGIN", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void successfulLogin(String email) {
        Intent listEventActivity = new Intent(this, AlpmysMainActivity.class);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.apply();
        startActivity(listEventActivity);
    }
}

package api;

import java.util.List;

import models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {
    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("usuarios")
    Call<Usuario> createUsuario(@Body Usuario user);
}

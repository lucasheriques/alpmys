package api;

import java.util.List;

import br.pucminas.alpmysapp.models.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {
    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("usuarios")
    Call<Usuario> createUsuario(@Body Usuario user);

    @GET("eventos")
    Call<List<Evento>> getEventos();

    @POST("eventos")
    Call<Evento> createEventos(@Body Evento event);
}

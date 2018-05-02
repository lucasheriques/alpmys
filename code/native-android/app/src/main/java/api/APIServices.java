package api;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import java.util.List;
import br.pucminas.alpmysapp.models.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIServices {
    @GET("eventos")
    Call<List<Evento>> getEventos();

    @POST("eventos")
    Call<Evento> createEvento(@Body Evento evento);

    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("usuarios")
    Call<Usuario> createUsuario(@Body Usuario user);
}

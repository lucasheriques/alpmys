package api;
import models.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface APIServices {
    @GET("eventos")
    Call<List<Evento>> getEventos();

    @POST("eventos")
    Call<Evento> createEvento(@Body Evento evento);
}

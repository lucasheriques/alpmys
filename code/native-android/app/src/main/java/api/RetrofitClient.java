package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String ROOT_URL = "http://192.168.25.4:45457/api/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIServices getAPIService() {
        return getRetrofitInstance().create(APIServices.class);
    }

}

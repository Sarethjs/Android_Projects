package dev.sareth.anime.services;

import java.util.List;

import dev.sareth.anime.models.Anime;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface iAnimeService {

    public final  String API_URL = "https://6477a3d79233e82dd53bfab3.mockapi.io/sarethcontact/";
    public static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
     
     @GET("anime") Call<List<Anime>> getAllAnime();

    @GET("anime/{id}")
    Call<Anime> findAnime(@Path("id") int id);

    @POST("anime")
    Call<Anime> create(@Body Anime anime);

    @PUT("anime/{id}")
    Call<Anime> update(@Path("id") int id, @Body Anime anime);

    @DELETE("anime/{id}")
    Call<Void> delete(@Path("id") int id);
}

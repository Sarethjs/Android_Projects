package dev.sareth.contact.services;

import dev.sareth.contact.models.Landscape;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

interface iLandscapeService {

    String API_URL = "https://6477a3d79233e82dd53bfab3.mockapi.io/sarethcontact/";
     Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("landscape") Call<List<Landscape>> getAllContacts();

    @GET("contact/{id}")
    Call<Landscape> findContact(@Path("id") int id);

    @POST("landscape")
    Call<Landscape> create(@Body Landscape landscape);

    @PUT("landscape/{id}")
    Call<Landscape> update(@Path("id") int id, @Body Landscape landscape);

    @DELETE("contact/{id}")
    Call<Void> delete(@Path("id") int id);

}

package dev.sareth.contact.services;


import dev.sareth.contact.models.ImageUploadRequest;
import dev.sareth.contact.models.ImageUploadResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface iImageService {

    public final  String API_URL = "https://demo-upn.bit2bittest.com/";

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("image")
    Call<ImageUploadResponse> create(@Body ImageUploadRequest request);
}

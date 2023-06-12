package dev.sareth.contact.services;

import java.util.List;

import dev.sareth.contact.models.Comment;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface iCommentService {

    public final  String API_URL = "https://6477a3d79233e82dd53bfab3.mockapi.io/sarethcontact/";
    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @POST("comments")
    Call<Comment> create(@Body Comment comment);

    @GET("comments")
    Call<List<Comment>> getComments();
}

package dev.sareth.contact.services;

import dev.sareth.contact.models.Contact;
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

interface iContacService {

    public final  String API_URL = "https://6477a3d79233e82dd53bfab3.mockapi.io/sarethcontact/";
    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("contact") Call<List<Contact>> getAllContacts();

    @GET("contact/{id}")
    Call<Contact> findContact(@Path("id") int id);

    @POST("contact")
    Call<Contact> create(@Body Contact contact);

    @PUT("contact/{id}")
    Call<Contact> update(@Path("id") int id, @Body Contact contact);

    @DELETE("contact/{id}")
    Call<Void> delete(@Path("id") int id);

}

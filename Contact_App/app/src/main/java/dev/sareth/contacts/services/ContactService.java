package dev.sareth.contacts.services;

import java.util.List;
import dev.sareth.contacts.models.Contact;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {

    @GET("contact")
    Call<List<Contact>> getAllContact();

    @GET("contact/{id}")
    Call<Contact> findContact(@Path("id") int id);

    @POST("contact")
    Call<Contact> create(@Body Contact contact);

    @PUT("contact/{id}")
    Call<Contact> update(@Path("id") int id, @Body Contact contact);

    @DELETE("contact/{id}")
    Call<Void> delete(@Path("id") int id);
}

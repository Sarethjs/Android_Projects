package dev.sareth.contacts.models;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIContact {

    private static final String API_URL = "https://6477a3d79233e82dd53bfab3.mockapi.io/sarethcontact/";

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(APIContact.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

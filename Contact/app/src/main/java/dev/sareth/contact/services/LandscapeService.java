package dev.sareth.contact.services;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.models.Landscape;
import dev.sareth.contact.listeners.CallbackListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandscapeService {

    private static final iLandscapeService service =
            iLandscapeService.RETROFIT.create(iLandscapeService.class);

    public static void create(Landscape landscape, CallbackListener.item listener){

        service.create(landscape).enqueue(new Callback<Landscape>() {
            @Override
            public void onResponse(@NonNull Call<Landscape> call,
                                   @NonNull Response<Landscape> response) {
                if (response.isSuccessful()){
                    listener.itemNotReceived("Paisaje saved");
                } else{
                    listener.itemNotReceived("Paisaje not saved");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Landscape> call,
                                  @NonNull Throwable t){
                listener.itemNotReceived("Paisaje not saved: Unknown error");
            }
        });
    }

    public static void findAll(CallbackListener.items listener){
        service.getAllContacts().enqueue(new Callback<List<Landscape>>() {
            List<Landscape> landscapes = new ArrayList<>();
            @Override
            public void onResponse(@NonNull Call<List<Landscape>> call,
                                   @NonNull Response<List<Landscape>> response) {
                if (response.isSuccessful()) {
                    landscapes = response.body();
                    listener.itemsReceived(landscapes);
                } else listener.itemsNotReceived("Items not loaded");
            }

            @Override
            public void onFailure(@NonNull Call<List<Landscape>> call,
                                  @NonNull Throwable t) {
                listener.itemsNotReceived("Items not loaded: " + t);
            }
        });
    }

}

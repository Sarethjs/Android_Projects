package dev.sareth.contact.models;

import android.util.Log;

import androidx.annotation.NonNull;

import dev.sareth.contact.services.iImageService;
import dev.sareth.contact.listeners.CallbackListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public  class SarethImage{

    public void uploadImageToApi(String base64Image, CallbackListener.item listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo-upn.bit2bittest.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iImageService imageUploadService = retrofit.create(iImageService.class);

        ImageUploadRequest request = new ImageUploadRequest(base64Image);

        Call<ImageUploadResponse> call = imageUploadService.create(request);
        call.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<ImageUploadResponse> call,
                                   @NonNull Response<ImageUploadResponse> response) {
                if (response.isSuccessful()) {
                    ImageUploadResponse uploadResponse = response.body();
                    String imageUrl = uploadResponse.getImageUrl();

                    Log.d("api_res", "onResponse: " + imageUrl);
                    listener.itemReceived(imageUrl);
                } else {
                    listener.itemNotReceived("Image not uploaded");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ImageUploadResponse> call, @NonNull Throwable t) {
                listener.itemNotReceived("Image not uploaded: " + t);
            }
        });
    }

}

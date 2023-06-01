package dev.sareth.anime.controllers;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import dev.sareth.anime.AddAnime;
import dev.sareth.anime.AnimeView;
import dev.sareth.anime.MainActivity;
import dev.sareth.anime.models.Anime;
import dev.sareth.anime.services.iAnimeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeController implements iAnimeService {

    private  final Activity activity;

    public AnimeController(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Call<List<Anime>> getAllAnime() {

        iAnimeService contactService = RETROFIT.create(iAnimeService.class);
        Call<List<Anime>> call = contactService.getAllAnime();

        call.enqueue(new Callback<List<Anime>>() {

            final MainActivity mainActivity = (MainActivity) activity;

            @Override
            public void onResponse(@NonNull Call<List<Anime>> call, @NonNull Response<List<Anime>> response) {
                if (response.isSuccessful()) {
                    List<Anime> contacts = response.body();
                    mainActivity.onItemsLoaded(contacts);
                    Toast.makeText(mainActivity, "Items loaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mainActivity, "Items not loaded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Anime>> call, @NonNull Throwable t) {
                Toast.makeText(mainActivity, "ERROR items not loaded", Toast.LENGTH_SHORT).show();
            }
        });

        return call;
    }

    @Override
    public Call<Anime> findAnime(int id) {
        return null;
    }

    @Override
    public Call<Anime> create(Anime anime) {

        iAnimeService contactService = RETROFIT.create(iAnimeService.class);
        Call<Anime> call = contactService.create(anime);

        call.enqueue(new Callback<Anime>() {
            final AddAnime addAnime = (AddAnime) activity;

            @Override
            public void onResponse(@NonNull Call<Anime> call, @NonNull Response<Anime> response) {
                Toast.makeText(addAnime, "Anime added", Toast.LENGTH_SHORT).show();
                addAnime.finish();
            }

            @Override
            public void onFailure(@NonNull Call<Anime> call, @NonNull Throwable t) {
                Toast.makeText(addAnime, "Anime NOT added", Toast.LENGTH_SHORT).show();
            }
        });
        return call;
    }

    @Override
    public Call<Anime> update(int id, Anime anime) {

        iAnimeService contactService = RETROFIT.create(iAnimeService.class);

        Call<Anime> call = contactService.update(id, anime);

        call.enqueue(new Callback<Anime>() {
            final AnimeView animeView = (AnimeView) activity;

            @Override
            public void onResponse(@NonNull Call<Anime> call, @NonNull Response<Anime> response) {
                if(animeView != null) {
                    Toast.makeText(animeView.getApplicationContext(), "Anime updated", Toast.LENGTH_SHORT).show();
                    animeView.finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Anime> call, @NonNull Throwable t) {
                Toast.makeText(animeView, "Anime not updated", Toast.LENGTH_SHORT).show();
            }
        });

        return call;
    }

    @Override
    public Call<Void> delete(int id) {

        iAnimeService contactService = RETROFIT.create(iAnimeService.class);
        Call<Void> call = contactService.delete(id);

        call.enqueue(new Callback<Void>() {
            final AnimeView animeView = (AnimeView) activity;
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(animeView.getApplicationContext(), "Anime removed from API", Toast.LENGTH_SHORT).show();
                animeView.finish();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(animeView, "Anime can't be removed", Toast.LENGTH_SHORT).show();
            }
        });

        return call;
    }
}

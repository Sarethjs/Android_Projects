package dev.sareth.anime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.anime.adapters.AnimeAdapter;
import dev.sareth.anime.controllers.AnimeController;
import dev.sareth.anime.models.Anime;

public class MainActivity extends AppCompatActivity implements AnimeAdapter.iFavoriteClickListener{

    private List<Anime> animes;
    AnimeAdapter animeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize components
        this.initComponents();

    }

    @Override
    protected void onResume(){
        super.onResume();
        this.fetchItems();
    }

    @Override
    public void onFavoriteClick(Anime anime) {
        AnimeController animeController = new AnimeController(null);
        animeController.update(anime.getId(), anime);
        this.animeAdapter.notifyDataSetChanged();
    }

    private void initComponents(){
        animes = new ArrayList<>();
        //this.setValues();

        // Getting anime list from API
        fetchItems();

        animeAdapter = new AnimeAdapter(animes, this);
        animeAdapter.setFavoriteListener(this);
        RecyclerView recyclerView = this.findViewById(R.id.rvAnime);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animeAdapter);

        // Add button
        this.findViewById(R.id.btnAdd).setOnClickListener(view ->{
            Intent intent = new Intent(this, AddAnime.class);
            this.startActivity(intent);
        });
    }

    private void fetchItems(){
        AnimeController animeController = new AnimeController(this);
        animeController.getAllAnime();
    }


    public void onItemsLoaded(List<Anime> items){
        this.animeAdapter.setItems(items);
        this.animeAdapter.notifyItemRangeChanged(0, items.size());
    }

    private void setValues(){

        String im1 = "https://assets-prd.ignimgs.com/2022/08/17/top25animecharacters-blogroll-1660777571580.jpg";
        String im2 = "https://img.freepik.com/premium-photo/anime-girl-watching-sunset-3d-illustration_717906-1415.jpg";
        String im3 = "https://t4.ftcdn.net/jpg/03/04/22/21/360_F_304222113_So6kI9E0ocOLQRBDT096mNLMVUhk2zCK.jpg";
        String im4 = "https://thumbs.dreamstime.com/z/anime-girl-snowy-edge-skyscraper-roof-anime-style-blonde-girl-sword-snowy-edge-skyscraper-roof-back-view-126945001.jpg";
        String im5 = "https://memes.co.in/wallpapers/uploads/1625829867.jpg";


        animes.add(new Anime(im1, "Anime 1", "Description 01", true));
        animes.add(new Anime(im2,"Anime 2", "Description 02", true));
        animes.add(new Anime(im3,"Anime 3", "Description 03", false));
        animes.add(new Anime(im4,"Anime 4", "Description 04", true));
        animes.add(new Anime(im5,"Anime 5", "Description 05", false));
    }


}
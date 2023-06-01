package dev.sareth.anime.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.sareth.anime.AnimeView;
import dev.sareth.anime.MainActivity;
import dev.sareth.anime.R;
import dev.sareth.anime.controllers.AnimeController;
import dev.sareth.anime.models.Anime;
import com.squareup.picasso.Picasso;

public class AnimeAdapter  extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>{

    private List<Anime> items;
    private Context context;
    private iFavoriteClickListener favoriteListener;

    public AnimeAdapter(List<Anime> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimeViewHolder(LayoutInflater.from(context).inflate(R.layout.anime_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {

        holder.tvName.setText(items.get(position).getName());
        holder.tvDescription.setText(items.get(position).getDescription());
        Picasso.get().load(items.get(position).getImage()).into(holder.imAnime);

        if (items.get(position).isFavorite())  holder.ibFavorite.setImageResource(R.drawable.favorite);
        else holder.ibFavorite.setImageResource(R.drawable.nofavorite);

        holder.ibFavorite.setOnClickListener(new View.OnClickListener() {
            final boolean isFavorite = !items.get(holder.getAdapterPosition()).isFavorite();
            @Override
            public void onClick(View v) {
                items.get(holder.getAdapterPosition()).isFavorite(isFavorite);
                favoriteListener.onFavoriteClick(items.get(holder.getAdapterPosition()));
            }
        });

        holder.animeZone.setOnClickListener(view -> {
            Intent intent = new Intent(context, AnimeView.class);
            intent.putExtra("anime", items.get(position));
            context.startActivity(intent);
        });

    }

    public interface iFavoriteClickListener{
        void onFavoriteClick(Anime anime);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFavoriteListener(iFavoriteClickListener favoriteListener) {
        this.favoriteListener = favoriteListener;
    }

    public void setItems(List<Anime> items) {
        this.items = items;
    }

    class AnimeViewHolder extends RecyclerView.ViewHolder{

        LinearLayout animeZone;
        ImageView imAnime;
        TextView tvName;
        TextView tvDescription;
        ImageButton ibFavorite;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize components
            imAnime = itemView.findViewById(R.id.imageAnime);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ibFavorite = itemView.findViewById(R.id.btnFavorite);
            animeZone = itemView.findViewById(R.id.animeZone);
        }
    }
}

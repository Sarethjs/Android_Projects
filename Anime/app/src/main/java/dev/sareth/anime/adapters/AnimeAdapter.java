package dev.sareth.anime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.sareth.anime.R;
import dev.sareth.anime.models.Anime;
import com.squareup.picasso.Picasso;

public class AnimeAdapter  extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>{

    private List<Anime> items;
    private Context context;

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

        boolean isFavorite = items.get(position).isFavorite();

        holder.ibFavorite.setOnClickListener(view-> {
            items.get(position).isFavorite(!isFavorite);
            this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AnimeViewHolder extends RecyclerView.ViewHolder{

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
        }
    }
}

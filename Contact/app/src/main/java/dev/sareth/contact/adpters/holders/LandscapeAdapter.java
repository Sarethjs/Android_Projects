package dev.sareth.contact.adpters.holders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.sareth.contact.CommentIndex;
import dev.sareth.contact.R;
import dev.sareth.contact.models.Landscape;
import dev.sareth.contact.services.iImageService;

public class LandscapeAdapter extends RecyclerView.Adapter<LandscapeHolder>{

    private List<Landscape> items;
    private final Context context;

    public LandscapeAdapter(List<Landscape> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public LandscapeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_item, parent, false);
        return new LandscapeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandscapeHolder holder, int position) {

        String names = "Name: " + items.get(position).getName();
        String phone = "" + items.get(position).getContactLocation();
        String host = iImageService.API_URL;
        String imageUrl = host + items.get(position).getImageUrl();

        holder.tvNames.setText(names);
        holder.tvPhone.setText(phone);
        Picasso.get().load(imageUrl).into(holder.ivProfile);

        // Fetching comments
        //CommentService.find(items.get(position), this);

        // Recycler view for comments
        holder.btnEdit.setOnClickListener(view -> {
            // Open comments page
            Intent intent = new Intent(context, CommentIndex.class);
            intent.putExtra("landscape", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Landscape> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}

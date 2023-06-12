package dev.sareth.contact.adpters.holders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.CommentIndex;
import dev.sareth.contact.R;
import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.Comment;
import dev.sareth.contact.models.Contact;
import dev.sareth.contact.services.CommentService;
import dev.sareth.contact.services.iImageService;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder>{

    private List<Contact> items;
    private final Context context;

    public ContactAdapter(List<Contact> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_item, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {

        String names = "Name: " + items.get(position).getName();
        String phone = "Phone: " + items.get(position).getPhoneNumber();
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
            intent.putExtra("contact", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Contact> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}

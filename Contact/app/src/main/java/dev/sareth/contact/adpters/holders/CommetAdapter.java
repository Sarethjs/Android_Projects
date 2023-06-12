package dev.sareth.contact.adpters.holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.sareth.contact.R;
import dev.sareth.contact.models.Comment;

public class CommetAdapter extends RecyclerView.Adapter<CommentHolder> {

    private List<Comment> items;
    private final Context context;

    public CommetAdapter(List<Comment> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.comment_item, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.tvComment.setText(items.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Comment> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }
}

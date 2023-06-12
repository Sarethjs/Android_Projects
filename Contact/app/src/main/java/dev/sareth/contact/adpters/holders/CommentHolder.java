package dev.sareth.contact.adpters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.sareth.contact.R;

public class CommentHolder extends RecyclerView.ViewHolder {

    TextView tvComment;

    public CommentHolder(@NonNull View itemView) {
        super(itemView);
        this.tvComment = itemView.findViewById(R.id.tvComment);
    }
}

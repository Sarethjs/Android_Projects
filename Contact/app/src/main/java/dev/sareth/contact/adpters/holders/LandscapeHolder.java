package dev.sareth.contact.adpters.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.sareth.contact.R;

public class LandscapeHolder extends RecyclerView.ViewHolder {

    TextView tvNames, tvPhone;
    Button btnEdit;
    ImageView ivProfile;
    RecyclerView rc;

    public LandscapeHolder(@NonNull View itemView) {
        super(itemView);

        this.tvNames = itemView.findViewById(R.id.tvNames);
        this.tvPhone = itemView.findViewById(R.id.tvPhone);
        this.ivProfile = itemView.findViewById(R.id.ivProfile);
        this.ivProfile = itemView.findViewById(R.id.ivProfile);
        this.btnEdit = itemView.findViewById(R.id.btnComment);
        this.rc = itemView.findViewById(R.id.rcComments);
    }
}

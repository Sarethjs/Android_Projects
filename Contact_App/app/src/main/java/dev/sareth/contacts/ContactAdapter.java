package dev.sareth.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.sareth.contacts.models.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private  final Context context;
    private List<Contact> items;

    public ContactAdapter(Context context, List<Contact> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        Picasso.get().load(items.get(position).getImage()).into(holder.imageContact);
        holder.tvNames.setText(items.get(position).getNames());
        holder.tvPhoneNumber.setText(items.get(position).getPhoneNumber());
        holder.btnCall.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + items.get(position).getPhoneNumber()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        });

        holder.btnSms.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + items.get(position).getPhoneNumber()));
            intent.putExtra("sms_body", "Hola, " + items.get(position).getNames());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        });

        holder.contactArea.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactActivity.class);
            intent.putExtra("contact", items.get(position));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Contact> items) {
        this.items = items;
    }

    public List<Contact> getItems() {
        return items;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageContact;
        private final TextView tvNames;
        private final TextView tvPhoneNumber;
        private final ImageButton btnCall;
        private final ImageButton btnSms;
        private final LinearLayout contactArea;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize components
            this.imageContact = itemView.findViewById(R.id.imageProfile);
            this.tvNames = itemView.findViewById(R.id.contactName);
            this.tvPhoneNumber = itemView.findViewById(R.id.contactPhoneNumber);
            this.btnCall = itemView.findViewById(R.id.btnCall);
            this.btnSms = itemView.findViewById(R.id.btnSms);
            this.contactArea = itemView.findViewById(R.id.linContact);
        }
    }
}


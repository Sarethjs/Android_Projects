package dev.sareth.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.sareth.contacts.models.Contact;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private  Context context;
    private List<Contact> items;

    public MyAdapter(Context context, List<Contact> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNames;
        private TextView tvPhoneNumber;
        private ImageButton btnCall;
        private ImageButton btnSms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize components
            this.tvNames = itemView.findViewById(R.id.contactName);
            this.tvPhoneNumber = itemView.findViewById(R.id.contactPhoneNumber);
            this.btnCall = itemView.findViewById(R.id.btnCall);
            this.btnSms = itemView.findViewById(R.id.btnSms);
        }
    }
}


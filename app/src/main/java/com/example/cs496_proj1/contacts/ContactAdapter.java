package com.example.cs496_proj1.contacts;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_proj1.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> mData;

    // ViewHolder: store item view
    public class ViewHolder extends RecyclerView.
            ViewHolder {
        ImageView imageView;
        TextView nameView, numView;
        ImageButton callButton;

        ViewHolder(View itemView) {
            // Init Views
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameView = itemView.findViewById(R.id.nameTextView);
            numView = itemView.findViewById(R.id.numTextView);

            // Init Button
            callButton = (ImageButton) itemView.findViewById(R.id.callButton);
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL,
                                                Uri.parse("tel:" + numView.getText()));
                    itemView.getContext().startActivity(intent); }
            });
        }
    }

    // Constructor
    ContactAdapter(ArrayList<Contact> list) {
        mData = list ;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,
                                                                parent, false);
        ContactAdapter.ViewHolder vh = new ContactAdapter.ViewHolder(v) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        Contact element = mData.get(position) ;
        //holder.imageView.setImage???(element.thumnailId);
        holder.nameView.setText(element.fullName);
        holder.numView.setText(element.phone);
    }

    @Override
    public int getItemCount() {
        if (mData.isEmpty())
            return 0;
        return mData.size();
    }
}

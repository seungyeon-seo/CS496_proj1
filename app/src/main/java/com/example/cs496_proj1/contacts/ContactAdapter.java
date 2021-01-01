package com.example.cs496_proj1.contacts;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_proj1.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> mData;
    private ArrayList<Contact> searchList;

    // Constructor
    ContactAdapter(ArrayList<Contact> list) {
        mData = list ;
        searchList = list;
    }

    @Override
    public int getItemCount() {
        if (mData.isEmpty())
            return 0;
        return mData.size();
    }

    // ViewHolder: store item view
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, numView;
        ImageButton callButton;

        ViewHolder(View itemView) {
            // Init Views
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameView = itemView.findViewById(R.id.nameTextView);
            numView = itemView.findViewById(R.id.numTextView);

            // Init Button info
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Contact> filtering = new ArrayList<Contact>();
                String txt = constraint.toString();

                if (txt.isEmpty()) {
                    searchList = mData;
                }
                else {
                    for (int i = 0; i < getItemCount(); i++) {
                        Contact element = mData.get(i);
                        if (element.fullName.toLowerCase().contains(constraint)
                                || element.phone.toString().contains(constraint)) {
                            filtering.add(element);
                        }
                    }
                    if (filtering.isEmpty())
                        searchList = mData;
                    else
                        searchList = filtering;
                }
                FilterResults res = new FilterResults();
                res.values = searchList;
                return res;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                searchList = (ArrayList<Contact>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
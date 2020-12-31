package com.example.cs496_proj1.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_proj1.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> mData;

    // ViewHolder: store item view
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        ViewHolder(View itemView) {
            super(itemView) ;
            textView1 = itemView.findViewById(R.id.textView1) ;
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
        holder.textView1.setText("1") ;
        // holder.oooView.setooo(element);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}

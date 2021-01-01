package com.example.cs496_proj1.contacts;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.cs496_proj1.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ContactFragment extends Fragment {
    public ArrayList<Contact> contacts;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ContactAdapter adapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance() {
        // no arguments
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // RecyclerView Initialization
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        // Set LayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(0);

        // Init contact list
        contacts = getContacts();

        // Set Adatper
        adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Init SearchView
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    private ArrayList<Contact> getContacts() {
        // Init Cursor
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone._ID
        };
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor = requireActivity().getContentResolver().query(
                uri, projection, null, null, sortOrder);

        // Read Data
        LinkedHashSet<Contact> hasList = new LinkedHashSet<Contact>();
        ArrayList<Contact> contacts;

        if (cursor.moveToFirst()) {
            do {
                String phone = cursor.getString(0);
                String fullName = cursor.getString(1);
                long photo_id = cursor.getLong(2);
                long person = cursor.getLong(3);

                Contact contact = new Contact(phone, fullName, photo_id, person);

                if (contact.isStartWith("01")) {
                    hasList.add(contact);
                    Log.d("<<CONTACTS>>", contact.getMsg());
                }

            } while (cursor.moveToNext());
        }

        contacts = new ArrayList<Contact>(hasList);
        for (int i = 0; i < contacts.size(); i++) {
            contacts.get(i).setId(i);
        }

        if (cursor != null) {
            cursor.close();

        }return contacts;
    }
}

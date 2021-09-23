package com.example.contactpro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactpro.R;
import com.example.contactpro.pojos.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private OnItemClickListener listener;

    public ContactAdapter(List<Contact> contacts, OnItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNomPrenom;
        public TextView tvSociete;

        public ContactViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvNomPrenom = itemView.findViewById(R.id.tvNomPrenom);
            tvSociete = itemView.findViewById(R.id.tvSociete);
        }
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_main_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.tvNomPrenom.setText(contact.getNom() + " " + contact.getPrenom());
        holder.tvSociete.setText(contact.getSociete());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

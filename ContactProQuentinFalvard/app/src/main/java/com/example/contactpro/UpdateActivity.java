package com.example.contactpro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.contactpro.dao.ContactDao;
import com.example.contactpro.pojos.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private Context context;
    private EditText edtPrenom;
    private EditText edtNom;
    private EditText edtSociete;
    private EditText edtAdresse;
    private EditText edtTel;
    private EditText edtEmail;
    private EditText edtWebsite;
    private Spinner spnActivite;
    private Button btnAdd;
    private Button btnCancel;
    private Contact contactToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        context = getApplicationContext();

        // récupère la barre d'action
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get reference of widgets from XML layout
        edtPrenom = findViewById(R.id.edtPrenom);
        edtNom = findViewById(R.id.edtNom);
        edtSociete = findViewById(R.id.edtSociete);
        edtAdresse = findViewById(R.id.edtAdresse);
        edtTel = findViewById(R.id.edtTel);
        edtEmail = findViewById(R.id.edtEmail);
        edtWebsite = findViewById(R.id.edtWebsite);
        spnActivite = findViewById(R.id.spnActivite);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);

        // Initializing a String Array
        String[] secteurs = new String[]{
                "Industrie",
                "Informatique",
                "Paysage",
                "Batiment",
                "Autre",
        };

        final List<String> categoriesList = new ArrayList<>(Arrays.asList(secteurs));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,categoriesList
        );

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnActivite.setAdapter(spinnerArrayAdapter);

        contactToUpdate = getIntent().getParcelableExtra(MainActivity.CONTACT_KEY);

        edtNom.setText(contactToUpdate.getNom());
        edtPrenom.setText(contactToUpdate.getPrenom());
        edtSociete.setText(contactToUpdate.getSociete());
        edtAdresse.setText(contactToUpdate.getAdresse());
        edtTel.setText(contactToUpdate.getTel());
        edtEmail.setText(contactToUpdate.getEmail());
        edtWebsite.setText(contactToUpdate.getWebsite());
        btnAdd.setText("MODIFIER");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = edtNom.getText().toString();
                String prenom = edtPrenom.getText().toString();
                String societe = edtSociete.getText().toString();
                String adresse = edtAdresse.getText().toString();
                String tel = edtTel.getText().toString();
                String email = edtEmail.getText().toString();
                String website = edtWebsite.getText().toString();
                String activite = spnActivite.getSelectedItem().toString();

                if (nom.isEmpty() || prenom.isEmpty() || societe.isEmpty() || adresse.isEmpty() || tel.isEmpty() || email.isEmpty() || website.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Tous les champs doivent être remplis", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    ContactDao contactDAO = new ContactDao(context);
                    Contact contact = new Contact();
                    contact.setNom(nom);
                    contact.setPrenom(prenom);
                    contact.setSociete(societe);
                    contact.setAdresse(adresse);
                    contact.setTel(tel);
                    contact.setEmail(email);
                    contact.setWebsite(website);
                    contact.setActivite(activite);
                    contact.setFavoris(0);
                    contact.setId(contactToUpdate.getId());

                    contactDAO.update(contact);

                    Toast toast = Toast.makeText(getApplicationContext(), "Contact modifié", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
package com.example.contactpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactpro.dao.ContactDao;
import com.example.contactpro.pojos.Contact;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNomPrenom;
    private TextView tvSociete;
    private TextView tvAdresse;
    private TextView tvTel;
    private TextView tvEmail;
    private TextView tvSite;
    private TextView tvActivite;
    private Button btnCall;
    private Button btnLocate;
    private Button btnSms;
    private Contact contact;
    private Context context;

    private String nom;
    private String prenom;
    private String societe;
    private String adresse;
    private String tel;
    private String email;
    private String site;
    private String activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = getApplicationContext();

        tvNomPrenom = findViewById(R.id.tvNomPrenom);
        tvSociete = findViewById(R.id.tvSociete);
        tvAdresse = findViewById(R.id.tvAdresse);
        tvTel = findViewById(R.id.tvTel);
        tvEmail = findViewById(R.id.tvEmail);
        tvSite = findViewById(R.id.tvSite);
        tvActivite = findViewById(R.id.tvActivite);
        btnCall = findViewById(R.id.btnCall) ;
        btnLocate = findViewById(R.id.btnLocate) ;
        btnSms = findViewById(R.id.btnSms);

        contact = getIntent().getParcelableExtra(MainActivity.CONTACT_KEY);

        nom = contact.getNom();
        prenom = contact.getPrenom();
        societe = contact.getSociete();
        adresse = contact.getAdresse();
        tel = contact.getTel();
        email= contact.getEmail();
        site = contact.getWebsite();
        activite = contact.getActivite();

        btnSms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context=getApplicationContext();
                String text="Redirection en cours";
                int duration = Toast.LENGTH_SHORT;
                Toast toast=Toast.makeText(context,text,duration);
                toast.show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",contact.getTel(), null)));
            }
        });

       btnCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context=getApplicationContext();
                String text="Redirection en cours";
                int duration = Toast.LENGTH_SHORT;
                Toast toast=Toast.makeText(context,text,duration);
                toast.show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("tel",contact.getTel(), null)));
            }
        });

        btnLocate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context=getApplicationContext();
                String text="Redirection en cours";
                int duration = Toast.LENGTH_SHORT;
                Toast toast=Toast.makeText(context,text,duration);
                toast.show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.fr/maps/place/" + contact.getAdresse())));
            }
        });
    }

    @SuppressLint("SetTextI18n")



    @Override
    protected void onStart() {
        super.onStart();

        tvNomPrenom.setText(nom + " " + prenom);
        tvSociete.setText(societe);
        tvAdresse.setText(adresse);
        tvTel.setText(tel);
        tvEmail.setText(email);
        tvSite.setText(site);
        tvActivite.setText(activite);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_contact,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        Contact contactToDelete;
        Contact contactToUpdate;
        ContactDao contactDAO = new ContactDao(context);

        // effectue une action suivant l'item sélectionné
        // on test avec un switch l'id de l'item
        switch (item.getItemId()){
            case R.id.itmDelete:
                contactToDelete = getIntent().getParcelableExtra(MainActivity.CONTACT_KEY);
                contactDAO.delete(contactToDelete);
                Toast toast = Toast.makeText(getApplicationContext(), "Contact supprimé", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                return true;
            case R.id.itmUpdate:
                contactToUpdate = getIntent().getParcelableExtra(MainActivity.CONTACT_KEY);
                intent = new Intent(DetailActivity.this, UpdateActivity.class);
                intent.putExtra(MainActivity.CONTACT_KEY, contactToUpdate);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}
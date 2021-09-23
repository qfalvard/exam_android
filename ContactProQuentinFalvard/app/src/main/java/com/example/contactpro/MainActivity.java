package com.example.contactpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.contactpro.adapter.ContactAdapter;
import com.example.contactpro.dao.ContactDao;
import com.example.contactpro.pojos.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // CONSTANT
    private static final String TAG = "ContactPro";
    public static String CONTACT_KEY = "Contact";

    // VIEWS
    private RecyclerView rvContact;

    // BDD
    private ContactDao contactDao;

    //TOOLS
    private Context context;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContact = findViewById(R.id.rvContact);

        // Create Context
        context = getApplicationContext();
        //instanciate video DAO
        contactDao = new ContactDao(context);
        // Create a LinearLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvContact.setHasFixedSize(true);
        // bind the LayoutManager to the recyclerview
        rvContact.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");

        ContactAsyncTasks toDoAsyncTasks  = new ContactAsyncTasks();
        toDoAsyncTasks.execute();
    }

    public class ContactAsyncTasks extends AsyncTask<String, String, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(String... strings) {

            List<Contact> responseTodo = new ArrayList<>();

            try {
                responseTodo = contactDao.list();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseTodo;
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {

            contactAdapter = new ContactAdapter(contacts, new ContactAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Contact contact) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(CONTACT_KEY, contact);
                    startActivity(intent);
                }
            });
            rvContact.setAdapter(contactAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        // effectue une action suivant l'item sélectionné
        // on test avec un switch l'id de l'item
        switch (item.getItemId()){
            case R.id.itmAdd:
                // Créer un Intent pour ouvrir la page Add
                intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
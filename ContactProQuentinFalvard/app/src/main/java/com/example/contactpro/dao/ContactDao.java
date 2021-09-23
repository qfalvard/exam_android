package com.example.contactpro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.contactpro.database.ContactDbHelper;
import com.example.contactpro.pojos.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDao extends Dao {
    public ContactDao(Context context) {
        super(new ContactDbHelper(context));
    }

    public Contact find (int id){

        //déclare une variable qui stockera l'objet créé
        Contact Contact = null;

        //ouvre la base de données
        open();

        //éxécute la requete et renvoue un Cursor contenant les données
        Cursor cursor = db.rawQuery("select * from " + ContactDbHelper.CONTACT_TABLE_NAME +
                        " where " + ContactDbHelper.CONTACT_KEY + " = ?",
                new String[] {String.valueOf(id)});

        // positionne le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()){
            Contact = new Contact();
            Contact.setId(cursor.getLong(ContactDbHelper.CONTACT_KEY_COLUMN_INDEX));
            Contact.setNom(cursor.getString(ContactDbHelper.CONTACT_KEY_COLUMN_INDEX));
            Contact.setPrenom(cursor.getString(ContactDbHelper.CONTACT_NOM_COLUMN_INDEX));
            Contact.setSociete(cursor.getString(ContactDbHelper.CONTACT_PRENOM_COLUMN_INDEX));
            Contact.setAdresse(cursor.getString(ContactDbHelper.CONTACT_SOCIETE_COLUMN_INDEX));
            Contact.setTel(cursor.getString(ContactDbHelper.CONTACT_ADRESSE_COLUMN_INDEX));
            Contact.setEmail(cursor.getString(ContactDbHelper.CONTACT_TEL_COLUMN_INDEX));
            Contact.setWebsite(cursor.getString(ContactDbHelper.CONTACT_EMAIL_COLUMN_INDEX));
            Contact.setActivite(cursor.getString(ContactDbHelper.CONTACT_WEBSITE_COLUMN_INDEX));
            Contact.setFavoris(cursor.getInt(ContactDbHelper.CONTACT_ACTIVITE_COLUMN_INDEX));
        }

        //ferme la bdd
        close();

        return Contact;
    }

    public List<Contact> list (){
        //déclare une variable qui stockera la liste d'objets
        List<Contact> Contacts = new ArrayList<>();

        //ouvre la base de données
        open();

        //éxécute la requete et renvoue un Cursor contenant les données
        Cursor cursor = db.rawQuery("select * from " + ContactDbHelper.CONTACT_TABLE_NAME, null);

        // positionne le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()){
            // boucle tant que le cursor n'est pas arrivé sur le dernier enregistrement
            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();
                contact.setId(cursor.getLong(ContactDbHelper.CONTACT_KEY_COLUMN_INDEX));
                contact.setNom(cursor.getString(ContactDbHelper.CONTACT_NOM_COLUMN_INDEX));
                contact.setPrenom(cursor.getString(ContactDbHelper.CONTACT_PRENOM_COLUMN_INDEX));
                contact.setSociete(cursor.getString(ContactDbHelper.CONTACT_SOCIETE_COLUMN_INDEX));
                contact.setAdresse(cursor.getString(ContactDbHelper.CONTACT_ADRESSE_COLUMN_INDEX));
                contact.setTel(cursor.getString(ContactDbHelper.CONTACT_TEL_COLUMN_INDEX));
                contact.setEmail(cursor.getString(ContactDbHelper.CONTACT_EMAIL_COLUMN_INDEX));
                contact.setWebsite(cursor.getString(ContactDbHelper.CONTACT_WEBSITE_COLUMN_INDEX));
                contact.setActivite(cursor.getString(ContactDbHelper.CONTACT_ACTIVITE_COLUMN_INDEX));
                contact.setFavoris(cursor.getInt(ContactDbHelper.CONTACT_FAVORIS_COLUMN_INDEX));

                //ajoute le Contact créé dans la liste
                Contacts.add(contact);

                // passe à l'enregisstrement suivant
                cursor.moveToNext();
            }
            // ferme le cursor
            cursor.close();
        }

        //ferme la bdd
        close();

        return Contacts;
    }

    public void add (Contact contact){

        //ouvre la base de données
        open();

        ContentValues values = new ContentValues();

        values.put(ContactDbHelper.CONTACT_PRENOM, contact.getPrenom());
        values.put(ContactDbHelper.CONTACT_NOM, contact.getNom());
        values.put(ContactDbHelper.CONTACT_SOCIETE, contact.getSociete());
        values.put(ContactDbHelper.CONTACT_ADRESSE, contact.getAdresse());
        values.put(ContactDbHelper.CONTACT_TEL, contact.getTel());
        values.put(ContactDbHelper.CONTACT_EMAIL, contact.getEmail());
        values.put(ContactDbHelper.CONTACT_WEBSITE, contact.getWebsite());
        values.put(ContactDbHelper.CONTACT_ACTIVITE, contact.getActivite());

        //effectue une insertion des données et récupère l'id généré
        long id = db.insert(ContactDbHelper.CONTACT_TABLE_NAME, null, values);

        // met à jour l'id de l'objet et le favoris
        contact.setId(id);
        contact.setFavoris(0);

        //ferme la bdd
        close();
    }

    public void update (Contact Contact){

        //ouvre la base de données
        open();

        ContentValues values = new ContentValues();

        values.put(ContactDbHelper.CONTACT_NOM, Contact.getNom());
        values.put(ContactDbHelper.CONTACT_PRENOM, Contact.getPrenom());
        values.put(ContactDbHelper.CONTACT_SOCIETE, Contact.getSociete());
        values.put(ContactDbHelper.CONTACT_ADRESSE, Contact.getAdresse());
        values.put(ContactDbHelper.CONTACT_TEL, Contact.getTel());
        values.put(ContactDbHelper.CONTACT_EMAIL, Contact.getEmail());
        values.put(ContactDbHelper.CONTACT_WEBSITE, Contact.getWebsite());
        values.put(ContactDbHelper.CONTACT_ACTIVITE, Contact.getActivite());
        Contact.getId();

        //exécute la requete update avec la claure where id = ?
        db.update(ContactDbHelper.CONTACT_TABLE_NAME, values, ContactDbHelper.CONTACT_KEY + " = ?",
                new String[] {
                        String.valueOf(Contact.getId())
                });

        //ferme la bdd
        close();
    }

    public void delete (Contact Contact){

        //ouvre la base de données
        open();

        //exécute la requete update avec la claure where id = ?
        db.delete(ContactDbHelper.CONTACT_TABLE_NAME, ContactDbHelper.CONTACT_KEY + " = ?",
                new String[] {
                        String.valueOf(Contact.getId())
                });

        //ferme la bdd
        close();
    }
}

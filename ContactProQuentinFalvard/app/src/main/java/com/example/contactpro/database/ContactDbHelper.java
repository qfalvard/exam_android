package com.example.contactpro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";

    public static final String CONTACT_KEY = "id";
    public static final String CONTACT_NOM = "nom";
    public static final String CONTACT_PRENOM = "prenom";
    public static final String CONTACT_SOCIETE = "societe";
    public static final String CONTACT_ADRESSE = "adresse";
    public static final String CONTACT_TEL = "tel";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_WEBSITE = "website";
    public static final String CONTACT_ACTIVITE = "activite";
    public static final String CONTACT_FAVORIS = "favoris";

    public static final String CONTACT_TABLE_NAME = "contact";

    public static final int CONTACT_KEY_COLUMN_INDEX = 0;
    public static final int CONTACT_NOM_COLUMN_INDEX = 1;
    public static final int CONTACT_PRENOM_COLUMN_INDEX = 2;
    public static final int CONTACT_SOCIETE_COLUMN_INDEX = 3;
    public static final int CONTACT_ADRESSE_COLUMN_INDEX = 4;
    public static final int CONTACT_TEL_COLUMN_INDEX = 5;
    public static final int CONTACT_EMAIL_COLUMN_INDEX = 6;
    public static final int CONTACT_WEBSITE_COLUMN_INDEX = 7;
    public static final int CONTACT_ACTIVITE_COLUMN_INDEX = 8;
    public static final int CONTACT_FAVORIS_COLUMN_INDEX = 9;

    public ContactDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final String CONTACT_TABLE_CREATE =
            "CREATE TABLE " + CONTACT_TABLE_NAME + " (" +
                    CONTACT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                    CONTACT_NOM + " TEXT, " +
                    CONTACT_PRENOM + " TEXT, " +
                    CONTACT_SOCIETE + " TEXT, " +
                    CONTACT_ADRESSE + " TEXT, " +
                    CONTACT_TEL + " TEXT, " +
                    CONTACT_EMAIL + " TEXT, " +
                    CONTACT_WEBSITE + " TEXT, " +
                    CONTACT_ACTIVITE + " TEXT, " +
                    CONTACT_FAVORIS + " INTEGER);";

    private static final String TODO_TABLE_DROP = " DROP TABLE IF EXISTS "  + CONTACT_TABLE_NAME + ";";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TODO_TABLE_DROP);
        onCreate(db);

    }
}

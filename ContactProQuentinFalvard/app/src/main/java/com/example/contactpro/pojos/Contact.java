package com.example.contactpro.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    private long id;
    private String nom;
    private String prenom;
    private String societe;
    private String adresse;
    private String tel;
    private String email;
    private String website;
    private String activite;
    private int favoris;

    public Contact() {
    }

    public Contact(String nom, String prenom, String societe, String adresse, String tel, String email, String website, String activite, int favoris) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.website = website;
        this.activite = activite;
        this.favoris = favoris;
    }

    protected Contact(Parcel parcel) {
        this.id = parcel.readLong();
        this.nom = parcel.readString();
        this.prenom = parcel.readString();
        this.societe = parcel.readString();
        this.adresse = parcel.readString();
        this.tel = parcel.readString();
        this.email = parcel.readString();
        this.website = parcel.readString();
        this.activite = parcel.readString();
        this.favoris = parcel.readInt();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public int getFavoris() {
        return favoris;
    }

    public void setFavoris(int favoris) {
        this.favoris = favoris;
    }

    public static Creator<Contact> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", societe='" + societe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", activite='" + activite + '\'' +
                ", favoris=" + favoris +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(societe);
        dest.writeString(adresse);
        dest.writeString(tel);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(activite);
        dest.writeInt(favoris);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}

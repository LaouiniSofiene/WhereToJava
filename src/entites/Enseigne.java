/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mohamed El Hammi
 */
public class Enseigne {
    private int idEnseigne;
    private int idUtilisateur;
    private String type;
    private String nom;
    private String description;
    private String adresse;
    private float note;
    private String specialite;
    private String horaire;
    private String numTel;
    
    private String photo1;
    private String photo2;
    private String photo3;
    private ImageView image = new ImageView();;
    
    private int signalEnseigne;
    
    private String nomUtilisateur;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Enseigne() {
    }



    public Enseigne(int idEnseigne, int idUtilisateur, String type, String nom, String description, String adresse, float note, String specialite, String numTel,int singnalEnseigne,String photo1,String photo2,String photo3) {
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;
        this.signalEnseigne = signalEnseigne;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }
    
    
    /************/
    public Enseigne(String nomUtilisateur,int idEnseigne, int idUtilisateur, String type, String nom, String description, String adresse, float note, String specialite, String numTel,int singnalEnseigne,String photo1,String photo2,String photo3) {
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;
        this.signalEnseigne = signalEnseigne;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        
        this.nomUtilisateur = nomUtilisateur;
    }
    /************************/
    public Enseigne(int idEnseigne, int idUtilisateur, String type, String nom, String description, String adresse, float note, String specialite, String horaire, String numTel) {
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;
    }
    public Enseigne(int idUtilisateur, String type, String nom, String description, String adresse, String specialite, String horaire, String numTel) {
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;

        
    }
    //tebaa fazet find by name

    public Enseigne( int idUtilisateur,String type, String nom, String description, String adresse,float note, String specialite, String horaire, String numTel,String photo1 , String photo2,String photo3) {
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;

    }

    
    
    /*kaed njareb beha*/
        public Enseigne(int idUtilisateur, String type, String nom, String description, String adresse, String specialite, String numTel,String photo1 , String photo2,String photo3) {
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.numTel = numTel;
        this.photo1=photo1;
        this.photo2=photo2;
        this.photo3=photo3;
        

    }
    
    public Enseigne(String type, String nom, String description, String adresse, float note, String specialite, String horaire, String numTel,int signalEnseigne,String photo1) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.horaire = horaire;
        this.numTel = numTel;
        this.signalEnseigne = signalEnseigne;
        this.photo1 = photo1;
    }
    // admin constructor
     public Enseigne(String nomUtilisateur,String type, String nom, String description, String adresse, float note, String specialite, String numTel,int signalEnseigne,String photo1) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.note = note;
        this.specialite = specialite;
        this.numTel = numTel;
        this.signalEnseigne = signalEnseigne;
        this.photo1 = photo1;
        
        this.nomUtilisateur = nomUtilisateur;
    }
/*************************************************/
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    /*************************************************/


    public int getSignalEnseigne() {
        return signalEnseigne;
    }

    public void setSignalEnseigne(int signalEnseigne) {
        this.signalEnseigne = signalEnseigne;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public int getIdEnseigne() {
        return idEnseigne;
    }

    public void setIdEnseigne(int idEnseigne) {
        this.idEnseigne = idEnseigne;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idEnseigne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enseigne other = (Enseigne) obj;
        if (this.idEnseigne != other.idEnseigne) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enseigne{" + "idEnseigne=" + idEnseigne + ", idUtilisateur=" + idUtilisateur + ", type=" + type + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + ", note=" + note + ", specialite=" + specialite + ", horaire=" + horaire + ", numTel=" + numTel + '}';
    }


    
    
}

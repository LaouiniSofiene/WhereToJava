/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author Ghaieth
 */
public class Utilisateur extends RecursiveTreeObject<Utilisateur> {

    private int _Id;
    private String _Role;
    private String _Email;
    private String _Motdp;
    private String _Nom;
    private String _Prenom;
    private String _Photo;
    private String _Telephone;
    private String _Ville;
    private String _Bio;
    private int _nbAbo;
    private int _nbAmi;    
    private Boolean _bloque;

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "_Id=" + _Id + ", _Role=" + _Role + ", _Email=" + _Email + ", _Motdp=" + _Motdp + ", _Nom=" + _Nom + ", _Prenom=" + _Prenom + ", _Photo=" + _Photo + ", _Telephone=" + _Telephone + ", _Ville=" + _Ville + ", _Bio=" + _Bio + '}';
    }

    public Utilisateur(String _Nom) {
        this._Nom = _Nom;
    }

    public Utilisateur(String _Role, String _Email, String _Motdp, String _Nom, String _Prenom, String _Photo, String _Telephone, String _Ville, String _Bio) {
        this._Role = _Role;
        this._Email = _Email;
        this._Motdp = _Motdp;
        this._Nom = _Nom;
        this._Prenom = _Prenom;
        this._Photo = _Photo;
        this._Telephone = _Telephone;
        this._Ville = _Ville;
        this._Bio = _Bio;
    }

    public Utilisateur(int _Id, String _Nom, String _Prenom, String _Photo, String _Telephone, String _Ville, String _Bio) {
        this._Id = _Id;
        this._Nom = _Nom;
        this._Prenom = _Prenom;
        this._Photo = _Photo;
        this._Telephone = _Telephone;
        this._Ville = _Ville;
        this._Bio = _Bio;
    }

    public Utilisateur(int _Id, String _Role, String _Email, String _Motdp, String _Nom, String _Prenom, String _Photo, String _Telephone, String _Ville, String _Bio, int _nbAbo, int _nbAmi, Boolean _bloque) {
        this._Id = _Id;
        this._Role = _Role;
        this._Email = _Email;
        this._Motdp = _Motdp;
        this._Nom = _Nom;
        this._Prenom = _Prenom;
        this._Photo = _Photo;
        this._Telephone = _Telephone;
        this._Ville = _Ville;
        this._Bio = _Bio;
        this._nbAbo = _nbAbo;
        this._nbAmi = _nbAmi;
        this._bloque = _bloque;        
    }

    public int getId() {
        return _Id;
    }

    public void setId(int value) {
        _Id = value;
    }

    public String getRole() {
        return _Role;
    }

    public void setRole(String value) {
        _Role = value;
    }

    public String getEmail() {
        return _Email;
    }

    public void setEmail(String value) {
        _Email = value;
    }

    public String getMotdp() {
        return _Motdp;
    }

    public void setMotdp(String value) {
        _Motdp = value;
    }

    public String getNom() {
        return _Nom;
    }

    public void setNom(String value) {
        _Nom = value;
    }

    public String getPrenom() {
        return _Prenom;
    }

    public void setPrenom(String value) {
        _Prenom = value;
    }

    public String getPhoto() {
        return _Photo;
    }

    public void setPhoto(String value) {
        _Photo = value;
    }

    public String getTelephone() {
        return _Telephone;
    }

    public void setTelephone(String value) {
        _Telephone = value;
    }

    public String getVille() {
        return _Ville;
    }

    public void setVille(String value) {
        _Ville = value;
    }

    public String getBio() {
        return _Bio;
    }

    public void setBio(String _Bio) {
        this._Bio = _Bio;
    }

    public int getNbAbo() {
        return _nbAbo;
    }

    public void setNbAbo(int _nbAbo) {
        this._nbAbo = _nbAbo;
    }

    public int getNbAmi() {
        return _nbAmi;
    }

    public void setNbAmi(int _nbAmi) {
        this._nbAmi = _nbAmi;
    }

    public Boolean getBloque() {
        return _bloque;
    }

    public void setBloque(Boolean _bloque) {
        this._bloque = _bloque;
    }
    
    
}

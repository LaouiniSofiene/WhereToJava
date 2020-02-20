/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;



/**
 *
 * @author Asus
 */
public class Evenement {

    private int _Ref;
    private String _Nom;
    private String Etab;
    private String _DateDebut;
    private String _DateFin;
    private String _Description;
    private String _Image;
    private String _Theme;
    private int nbr_place;
    private int prix;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Evenement() {
    }

    

    public String getEtab() {
        return Etab;
    }

    public void setEtab(String Etab) {
        this.Etab = Etab;
    }

    public String getTheme() {
        return _Theme;
    }

    public void setTheme(String _Theme) {
        this._Theme = _Theme;
    }
    

    

    public int getRef() {
        return _Ref;
    }

    public void setRef(int value) {
        _Ref = value;
    }

    public String getNom() {
        return _Nom;
    }

    public void setNom(String value) {
        _Nom = value;
    }

    public String getRefEtab() {
        return Etab;
    }

    public void setRefEtab(String value) {
        Etab = value;
    }

    public String getDateDebut() {
        return _DateDebut;
    }

    public void setDateDebut(String value) {
        _DateDebut = value;
    }

    public String getDateFin() {
        return _DateFin;
    }

    public void setDateFin(String value) {
        _DateFin = value;
    }

    public String getDescription() {
        return _Description;
    }

    public void setDescription(String value) {
        _Description = value;
    }

    public String getIimage() {
        return _Image;
    }

    public void setImage(String value) {
        _Image = value;
    }

    public Evenement(String _Nom, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, String _Theme,int nbr_place,int prix,int idUser) {
        
        this._Nom = _Nom;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this._Theme = _Theme;
        this.nbr_place=nbr_place;
        this.prix=prix;
        this.idUser=idUser;
    }

    public Evenement(int _Ref, String _Nom, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, String _Theme,int nbr_place,int prix,int idUser) {
        this._Ref = _Ref;
        this._Nom = _Nom;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this._Theme = _Theme;
        this.nbr_place=nbr_place;
        this.prix=prix;
        this.idUser=idUser;
    }

    @Override
    public String toString() {
        return "Evenement{" + "_Ref=" + _Ref + ", _Nom=" + _Nom + ", Etab=" + Etab + ", _DateDebut=" + _DateDebut + ", _DateFin=" + _DateFin + ", _Description=" + _Description + ", _Image=" + _Image + ", _Theme=" + _Theme + ", nbr_place=" + nbr_place + ", prix=" + prix + '}';
    }

    

    
    
}

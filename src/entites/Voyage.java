/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author khalil
 */
public class Voyage {

    private int _Id;
    private String _DateDebut;
    private String _DateFin;
    private float _Prix;
    private String _Destination;
    private String _Description;
    private String _Image;
    private int _NbPlaces;
    private int _Nb_dispo;
    private int _idUser;

       public Voyage( String _DateDebut, String _DateFin, float _Prix, String _Destination, String _Description, String _Image, int _NbPlaces,int _Nb_dispo,int _idUser) {
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Prix = _Prix;
        this._Destination = _Destination;
        this._Description = _Description;
        this._Image = _Image;
        this._NbPlaces = _NbPlaces;
        this._Nb_dispo = _Nb_dispo;
        this._idUser = _idUser;
    }

 
    public Voyage(int _Id, String _DateDebut, String _DateFin, float _Prix, String _Destination, String _Description, String _Image, int _NbPlaces,int _Nb_dispo,int _idUser) {
        this._Id = _Id;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Prix = _Prix;
        this._Destination = _Destination;
        this._Description = _Description;
        this._Image = _Image;
        this._NbPlaces = _NbPlaces;
        this._Nb_dispo = _Nb_dispo;
        this._idUser = _idUser;
    }

    

    public int getId() {
        return _Id;
    }

    public void setId(int value) {
        _Id = value;
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

    public float getPrix() {
        return _Prix;
    }

    public void setPrix(float value) {
        _Prix = value;
    }

    public String getDestination() {
        return _Destination;
    }

    public void setDestination(String value) {
        _Destination = value;
    }

    public String getDescription() {
        return _Description;
    }

    public void setDescription(String value) {
        _Description = value;
    }

    public String getImage() {
        return _Image;
    }

    public void setImage(String value) {
        _Image = value;
    }

    public int getNbPlaces() {
        return _NbPlaces;
    }

    public void setNbPlaces(int value) {
        _NbPlaces = value;
    } 
    

    public int getNb_dispo() {
        return _Nb_dispo;
    }
      public void setNb_dispo(int _Nb_dispo) {
        this._Nb_dispo = _Nb_dispo;
    }

    @Override
    public String toString() {
        return "Voyage{" + "_Id=" + _Id + ", _DateDebut=" + _DateDebut + ", _DateFin=" + _DateFin + ", _Prix=" + _Prix + ", _Destination=" + _Destination + ", _Description=" + _Description + ", _Image=" + _Image + ", _NbPlaces=" + _NbPlaces + '}';
    }

    public int getNb_palces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdUser() {
        return _idUser;
    }

    public void setIdUser(int _idUser) {
        this._idUser = _idUser;
    }
    
    
    
}

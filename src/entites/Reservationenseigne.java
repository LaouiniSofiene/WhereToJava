/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author Sofiene Laouini
 */
public class Reservationenseigne {

    private int _RefRes;
    private Date _DateReservation;
    private int _NbPlace;
    private String type;
    private int prixRes;
    private int _idUser;
    private int _idBonplan;

    public Reservationenseigne(int _RefRes,Date _DateReservation, int _NbPlace, String type ,int prixRes ,int _idUser ,int _idBonplan) {
        this._RefRes = _RefRes;
        this._DateReservation = _DateReservation;
        this._NbPlace = _NbPlace;
        this.type = type;
        this.prixRes= prixRes;
        this._idUser =  _idUser;
        this._idBonplan =  _idBonplan;
    }

    public Reservationenseigne(Date _DateReservation, int _NbPlace, String type, int prixRes ,int _idUser ,int _idBonplan) {
        this._DateReservation = _DateReservation;
        this._NbPlace = _NbPlace;
        this.type = type;
        this.prixRes= prixRes;
        this._idUser =  _idUser;
        this._idBonplan =  _idBonplan;
    }

    @Override
    public String toString() {
        return "Reservationenseigne{" + "_RefRes=" + _RefRes + ", _DateReservation=" + _DateReservation + ", _NbPlace=" + _NbPlace + ", Type=" + type +"Prix=" + prixRes ;
    }

    public void setIdUser(int _idUser) {
        this._idUser = _idUser;
    }

    public void setIdBonplan(int _idBonplan) {
        this._idBonplan = _idBonplan;
    }

    public int getIdUser() {
        return _idUser;
    }

    public int getIdBonplan() {
        return _idBonplan;
    }

    public void setPrixRes(int prixRes) {
        this.prixRes = prixRes;
    }

    public int getPrixRes() {
        return prixRes;
    }

//    
    public int getRefRes() {
        return _RefRes;
    }

    public void setRefRes(int value) {
        _RefRes = value;
    }

    public Date getDateReservation() {
        return _DateReservation;
    }

    public void setDateReservation(Date value) {
        _DateReservation = value;
    }

    public int getNbPlace() {
        return _NbPlace;
    }

    public void setNbPlace(int value) {
        _NbPlace = value;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String value) {
        type = value;
    }
}

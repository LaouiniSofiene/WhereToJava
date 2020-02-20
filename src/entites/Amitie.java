/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Ghaieth
 */
public class Amitie {
    
    private int _IdAmitie;
    private int _IdUtilisateur1;
    private int _IdUtilisateur2;

    public Amitie(int _IdUtilisateur1, int _IdUtilisateur2) {
        this._IdUtilisateur1 = _IdUtilisateur1;
        this._IdUtilisateur2 = _IdUtilisateur2;
    }

    public int getIdAmitie() {
        return _IdAmitie;
    }

    public void setIdAmitie(int _IdAmitie) {
        this._IdAmitie = _IdAmitie;
    }

    public int getIdUtilisateur1() {
        return _IdUtilisateur1;
    }

    public void setIdUtilisateur1(int _IdUtilisateur1) {
        this._IdUtilisateur1 = _IdUtilisateur1;
    }

    public int getIdUtilisateur2() {
        return _IdUtilisateur2;
    }

    public void setIdUtilisateur2(int _IdUtilisateur2) {
        this._IdUtilisateur2 = _IdUtilisateur2;
    }
    
    
    
}

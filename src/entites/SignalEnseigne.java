/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Mohamed El Hammi
 */
public class SignalEnseigne {
    private int idSignal;
    private int idEnseigne;
    private int idUtilisateur;
    private int nbrSignal;

    public SignalEnseigne(int idSignal, int idEnseigne, int idUtilisateur, int nbrSignal) {
        this.idSignal = idSignal;
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.nbrSignal = nbrSignal;
    }
    public SignalEnseigne( int idEnseigne, int idUtilisateur, int nbrSignal) {
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.nbrSignal = nbrSignal;
    }

    public int getIdSignal() {
        return idSignal;
    }

    public void setIdSignal(int idSignal) {
        this.idSignal = idSignal;
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

    public int getNbrSignal() {
        return nbrSignal;
    }

    public void setNbrSignal(int nbrSignal) {
        this.nbrSignal = nbrSignal;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idSignal;
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
        final SignalEnseigne other = (SignalEnseigne) obj;
        if (this.idSignal != other.idSignal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SignalEnseigne{" + "idSignal=" + idSignal + ", idEnseigne=" + idEnseigne + ", idUtilisateur=" + idUtilisateur + ", nbrSignal=" + nbrSignal + '}';
    }
    
    
}

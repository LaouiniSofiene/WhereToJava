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
public class NoteEnseigne {
    private int idNote;
    private int idEnseigne;
    private int idUtilisateur;
    private int noteEnseigne;

    public NoteEnseigne(int idNote, int idEnseigne, int idUtilisateur, int noteEnseigne) {
        this.idNote = idNote;
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.noteEnseigne = noteEnseigne;
    }
    
    public NoteEnseigne(int idEnseigne, int idUtilisateur, int noteEnseigne) {
        this.idEnseigne = idEnseigne;
        this.idUtilisateur = idUtilisateur;
        this.noteEnseigne = noteEnseigne;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
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

    public int getNoteEnseigne() {
        return noteEnseigne;
    }

    public void setNoteEnseigne(int noteEnseigne) {
        this.noteEnseigne = noteEnseigne;
    }
    
    
    
    
    
}

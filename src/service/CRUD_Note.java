/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed El Hammi
 */
public class CRUD_Note {
    Statement st;
    PreparedStatement pst;
    ResultSet res;

    
    public void insertSt(int idEnseigne, int idUtilisateur,int noteUtilisateur) throws SQLException{
        String req="insert into noteenseigne (idEnseigne,idUtilisateur,noteUtilisateur) values ('"+idEnseigne+"', '"+idUtilisateur+"','"+noteUtilisateur+"')";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    public int displayNote(int idUtilisateur ,int idEnseigne) throws SQLException {
            int nbr = 0;
        String req = "Select noteUtilisateur from noteenseigne where idUtilisateur = '" +idUtilisateur+ "' and idEnseigne = '"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res = st.executeQuery(req);
        while(res.next()){
             nbr = res.getInt("noteUtilisateur");
        }
        return nbr;
    }
    
    public void updateNote(int idUtilisateur , int idEnseigne ,int nvNote) throws SQLException{
        String req="UPDATE noteenseigne SET noteUtilisateur = '"+nvNote+"' where idUtilisateur = '"+idUtilisateur+"' and idEnseigne ='"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    public float displayNoteMoy(int idEnseigne) throws SQLException {
        float moyenne = 0;
        String req = "Select AVG(noteUtilisateur) as moyenneNote from noteenseigne where idEnseigne = '"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res = st.executeQuery(req);
        while(res.next()){
             moyenne = res.getFloat("moyenneNote");
        }
        return moyenne;
    }
}

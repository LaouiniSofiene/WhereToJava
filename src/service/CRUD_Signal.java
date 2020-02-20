/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DataSource;
import entites.SignalEnseigne;

/**
 *
 * @author Mohamed El Hammi
 */
public class CRUD_Signal {
    Statement st;
    PreparedStatement pst;
    ResultSet res;

    
    public void insertSt(int idEnseigne, int idUtilisateur,int nbrSignal) throws SQLException{
        String req="insert into signalenseigne (idEnseigne,idUtilisateur,nbrSignal) values ('"+idEnseigne+"', '"+idUtilisateur+"','"+nbrSignal+"')";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    
    public int displayNbr(int idUtilisateur) throws SQLException {
            int nbr = 0;
        String req = "Select nbrSignal from signalenseigne where idUtilisateur = '" +idUtilisateur+ "'";
        st=DataSource.getInstance().getConnection().createStatement();
        res = st.executeQuery(req);
        while(res.next()){
             nbr = res.getInt("nbrSignal");
        }
        return nbr;
    }
    
    public int displayNbr2(int idUtilisateur ,int idEnseigne) throws SQLException {
            int nbr = 0;
        String req = "Select nbrSignal from signalenseigne where idUtilisateur = '" +idUtilisateur+ "' and idEnseigne = '"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res = st.executeQuery(req);
        while(res.next()){
             nbr = res.getInt("nbrSignal");
        }
        return nbr;
    }
    
}

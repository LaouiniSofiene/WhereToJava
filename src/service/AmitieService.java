/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.Amitie;
import entites.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static service.UtilisateurService.ds;
import util.DataSource;

/**
 *
 * @author Ghaieth
 */
public class AmitieService {
    static DataSource ds =DataSource.getInstance(); 
    
    public static void creeAmitie (Amitie a) {
        String req="INSERT INTO amitie (idUtilisateur1,idUtilisateur2) VALUES(?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setInt(1,a.getIdUtilisateur1()) ; 
            ste.setInt(2,a.getIdUtilisateur2()) ; 
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }
    
    public static void delAmi (int id)    {
        String req="DELETE  from amitie where  idAmitie =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;          
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    
    public static int isAmi(Utilisateur u1, Utilisateur u2) throws SQLException{
        String req = "SELECT idAmitie FROM amitie WHERE idUtilisateur1 =? and idUtilisateur2 =?";
        PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
        ste.setInt(1,u1.getId()) ;
        ste.setInt(2,u2.getId()) ;
        ResultSet result =ste.executeQuery() ; 
        if(result.next()){
            return result.getInt("idAmitie");
        }else{
            String req2 = "SELECT idAmitie FROM amitie WHERE idUtilisateur1 =? and idUtilisateur2 =?";
            PreparedStatement ste2 = ds.getConnection().prepareStatement(req2) ;
            ste2.setInt(2,u1.getId()) ;
            ste2.setInt(1,u2.getId()) ;
            ResultSet result2 =ste2.executeQuery() ;
            if(result2.next()){
                return result2.getInt("idAmitie");
            }else{
                return 0;
            }
        }
    }
    
}

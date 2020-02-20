/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entites.Evenement;
import entites.Voyage;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;



/**
 *
 * @author Guideinfo
 */
public class EvenementService {

    static DataSource ds =DataSource.getInstance(); 
    
    
    public static void insererEvenement (Evenement e)
    {
    String req="INSERT INTO evenement (nom,ref_etab,date_debut,date_fin,description,image,theme,nbr_place,prix,idUser) VALUES(?,?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getNom()) ; 
            ste.setString(2,e.getRefEtab()) ; 
            ste.setString(3,e.getDateDebut());
            ste.setString(4,e.getDateFin());
            ste.setString(5,e.getDescription());
            ste.setString(6,e.getIimage());
            ste.setString(7, e.getTheme());
            ste.setInt(8,e.getNbr_place());
            ste.setInt(9,e.getPrix());
            ste.setInt(10,e.getIdUser());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     
    public static void updateEvenement (Evenement e )
    {
    String req="UPDATE evenement SET nom=?,ref_etab=?,date_debut=?,date_fin=?,description=?,image=?,theme=?,nbr_place=?,prix=? WHERE ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getNom()) ; 
            ste.setString(2,e.getRefEtab()) ; 
            ste.setString(3,e.getDateDebut());
            ste.setString(4,e.getDateFin());
            ste.setString(5,e.getDescription());
            ste.setString(6,e.getIimage());
            ste.setInt(10,e.getRef()) ;
            ste.setString(7, e.getTheme());
            ste.setInt(8,e.getNbr_place());
            ste.setInt(9,e.getPrix());
           
            System.out.println(e.getRef());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     public static void DeletEvenement (Evenement e)
    {
    String req="DELETE  from evenement where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e.getRef()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
     public static void DeletEvenementByID (int e)
    {
    String req="DELETE  from evenement where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
     public static void insererArEvenement (Evenement e)
    {
    String req="INSERT INTO archive (nom,ref_etab,date_debut,date_fin,description,image,theme,nnr_place,prix) VALUES(?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getNom()) ; 
            ste.setString(2,e.getRefEtab()) ; 
            ste.setString(3,e.getDateDebut());
            ste.setString(4,e.getDateFin());
            ste.setString(5,e.getDescription());
            ste.setString(6,e.getIimage());
            ste.setString(7, e.getTheme());
            ste.setInt(8,e.getNbr_place());
            ste.setInt(9,e.getPrix());
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
     public static void Archive(Evenement v){
    
    if ( LocalDate.parse(v.getDateFin()).compareTo(LocalDate.now())<0){ 
        insererArEvenement(v);
        
        DeletEvenement(v);}
       
    
    } 
    public static List<Evenement> selectEvenement ()
    {
        List<Evenement> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM evenement ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Evenement(
                                    result.getInt("ref"),
                                    result.getString("nom"),
                                    result.getString("ref_etab"),
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getString("theme"),
                                    result.getInt("nbr_place"),
                                    result.getInt("prix"),
                                    result.getInt("idUser")
                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
    
      }
    public static Evenement selectEvenementByRef (int ref)
    {
        List<Evenement> list =new ArrayList<>() ;
            String req ;
            req = "SELECT *  FROM evenement WHERE ref=?";
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setInt(1,ref);
            ResultSet result =ste.executeQuery() ;
            while (result.next()){
            result.toString();
            list.add(new Evenement(
                result.getInt("ref"),
                result.getString("nom"),
                result.getString("ref_etab"),
                result.getString("date_debut"),
                result.getString("date_fin"),
                result.getString("description"),
                result.getString("image"),
                result.getString("theme"),
                result.getInt("nbr_place"),
                result.getInt("prix"),
                result.getInt("idUser")
                    
            )); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list.get(0) ;
    
      }
    
    public static List<Evenement> selectEvenementByIdUser (int id)
    {
        List<Evenement> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM evenement WHERE idUser=?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setInt(1,id);
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Evenement(
                                    result.getInt("ref"),
                                    result.getString("nom"),
                                    result.getString("ref_etab"),
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getString("theme"),
                                    result.getInt("nbr_place"),
                                    result.getInt("prix"),
                                    result.getInt("idUser")
                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
    
      }
    
    public ResultSet selectEvent2() throws SQLException{
         List<Evenement> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM evenement ";
         
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            
            
        
    return result ; 
     }
     public static void  participer (Evenement e, int nbPlaces){
        if(e.getNbr_place()> 2){
            e.setNbr_place(e.getNbr_place()-nbPlaces);
            updateEvenement(e);
        }
        
    }
     
    
    
    
    
}

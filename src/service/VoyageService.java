/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.Voyage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DataSource;

/**
 *
 * @author khalil
 */
public class VoyageService {
      static DataSource ds =DataSource.getInstance(); 
    
    
 public static void insererVoyage(Voyage v)
    {
    String req="INSERT INTO voyage (date_debut,date_fin,prix,destination,description,image,nb_places,nb_dispo,idUser) VALUES(?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setString(1,v.getDateDebut());
            ste.setString(2,v.getDateFin());
             ste.setFloat(3,v.getPrix());
             ste.setString(4,v.getDestination());
            ste.setString(5,v.getDescription());
            ste.setString(6,v.getImage());
            ste.setInt(7,v.getNbPlaces()) ;
            ste.setInt(8,v.getNbPlaces()) ;
            ste.setInt(9,v.getIdUser()) ;
            
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
     
    public static void updateVoyage (Voyage v )
    {
    String req="UPDATE voyage SET date_debut=?,date_fin=?,prix=?,destination=?,description=?,image=?, nb_places=?, nb_dispo=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
           ste.setString(1,v.getDateDebut());
            ste.setString(2,v.getDateFin());
             ste.setFloat(3,v.getPrix()) ; 
              ste.setString(4,v.getDestination()) ;              
            ste.setString(5,v.getDescription());
            ste.setString(6,v.getImage());
             ste.setInt(7,v.getNbPlaces()) ;
             ste.setInt(8,v.getNb_dispo()) ; 
             ste.setInt(9,v.getId()) ; 
             
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
     public static void DeletVoyage (Voyage v)
    {
    String req="DELETE  from voyage where  id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,v.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
     public static void DeletVoyagrByID (int e)
    {
    String req="DELETE  from voyage where  id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    public static List<Voyage> selectVoyage ()
    {
        List<Voyage> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM voyage ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Voyage(
                                    result.getInt("id"),
                                    
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getFloat("prix"),
                                    result.getString("destination"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getInt("nb_places"),
                                    result.getInt("nb_dispo"),
                                    result.getInt("idUser")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    public static List<Voyage> selectVoyageByUserId (int id)
    {
        List<Voyage> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM voyage WHERE idUser=?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setInt(1,id) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Voyage(
                                    result.getInt("id"),
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getFloat("prix"),
                                    result.getString("destination"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getInt("nb_places"),
                                    result.getInt("nb_dispo"),
                                    result.getInt("idUser")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
     
     public static Voyage selectVoyageParID (int id)
    {
        List<Voyage> list =new ArrayList<>() ; 
        String req ; 
        req = "SELECT *  FROM voyage WHERE id =?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setInt(1,id);
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Voyage(
                                    result.getInt("id"),
                                    
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getFloat("prix"),
                                    result.getString("destination"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getInt("nb_places"),
                                    result.getInt("nb_dispo"),
                                    result.getInt("idUser")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list.get(0) ; 
      }
     public static void  participer (Voyage v, int nbPlaces){
        if(v.getNb_dispo()> 2){
            v.setNb_dispo(v.getNb_dispo()-nbPlaces);
            updateVoyage(v);
        }
        else
            DeletVoyage(v);
    }
}

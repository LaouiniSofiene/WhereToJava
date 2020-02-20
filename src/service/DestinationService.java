/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.*;
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
public class DestinationService {
     static DataSource ds =DataSource.getInstance(); 
     
     
     public static void insererDestination(Destination d)
    {
    String req="INSERT INTO destination (nom,adresse,type,rating,id_voyage) VALUES(?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setString(1,d.getNom());
            ste.setString(2,d.getAdresse());
             ste.setString(3,d.getType());
             ste.setFloat(4,d.getRating());
           ste.setInt(5, d.getV().getId());
            
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
        
    }
    
     
       public static void DeletDestinationByID (int e)
    {
    String req="DELETE  from destination where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    
    
        public static List<Destination> selectDestination ()
    {
        List<Destination> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM destination ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Destination(
                                    result.getInt("ref"),
                                    result.getString("nom"),
                                    result.getString("adresse"),
                                    result.getString("type"),
                                    result.getFloat("rating"),
                    VoyageService.selectVoyageParID(result.getInt("id_voyage"))
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import entites.Utilisateur;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import util.DataSource;

/**
 *
 * @author Ghaieth
 */
public class UtilisateurService {
    static DataSource ds =DataSource.getInstance(); 
    
    
    public static void insererUtilisateur (Utilisateur e)
    {
    String req="INSERT INTO utilisateur (role,email,motdp,nom,prenom,photo,telephone,ville,bio,nbAbo,nbAmi,bloque) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getRole()) ; 
            ste.setString(2,e.getEmail()) ; 
            ste.setString(3,e.getMotdp());
            ste.setString(4,e.getNom());
            ste.setString(5,e.getPrenom());
            ste.setString(6,e.getPhoto());
            ste.setString(7,e.getTelephone());
            ste.setString(8,e.getVille());
            ste.setString(9,e.getBio());
            ste.setInt(10,0);
            ste.setInt(11,0);
            ste.setInt(12,0);
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }
    
     
    public static void updateUtilisateur (Utilisateur e )
    {
    String req="UPDATE utilisateur SET role=?,email=?,motdp=?,nom=?,prenom=?,photo=?, telephone=?, ville=?, bio=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getRole()) ; 
            ste.setString(2,e.getEmail()) ; 
            ste.setString(3,e.getMotdp());
            ste.setString(4,e.getNom());
            ste.setString(5,e.getPrenom());
            ste.setString(6,e.getPhoto());
            ste.setString(7,e.getTelephone());
            ste.setString(8,e.getVille());
            ste.setString(9,e.getBio());
            
            
            ste.setInt(10,e.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void updateProfile (Utilisateur e )
    {
    String req="UPDATE utilisateur SET nom=?,prenom=?,photo=?, telephone=?, ville=?, bio=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,e.getNom());
            ste.setString(2,e.getPrenom());
            ste.setString(3,e.getPhoto());
            ste.setString(4,e.getTelephone());
            ste.setString(5,e.getVille());
            ste.setString(6,e.getBio());
            
            
            ste.setInt(7,e.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void updateParametre (String mail, String mdp, int id)
    {
    String req="UPDATE utilisateur SET email=?,motdp=? WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,mail);
            ste.setString(2,mdp);
            
            
            ste.setInt(3,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void bloquer (Utilisateur e )
    {
    String req="UPDATE utilisateur SET bloque= 1 WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             System.out.println(""+e.getId());
            ste.setInt(1,e.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void debloquer (Utilisateur e )
    {
    String req="UPDATE utilisateur SET bloque=0 WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setInt(1,e.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void addAmi (Utilisateur e )
    {
        String req="UPDATE utilisateur SET nbAmi = nbAmi+1 WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;    
            ste.setInt(1,e.getId()) ;
            ste.executeUpdate() ;             
        } catch (SQLException ex) {
        }    
    }
    
    public static void delAmi (Utilisateur e )
    {
        String req="UPDATE utilisateur SET nbAmi = nbAmi-1 WHERE id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;    
            ste.setInt(1,e.getId()) ;
            ste.executeUpdate() ;             
        } catch (SQLException ex) {
        }    
    }
    
     public static void DeletUtilisateur (Utilisateur e)
    {
    String req="DELETE  from utilisateur where  id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
     public static void DeletUtilisateurByID (int e)
    {
    String req="DELETE  from Utilisateur where  id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    public static List<Utilisateur> selectUtilisateur ()
    {
        List<Utilisateur> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM utilisateur ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Utilisateur(
                                    result.getInt("id"),
                                    result.getString("role"),
                                    result.getString("email"),
                                    result.getString("motdp"),
                                    result.getString("nom"),
                                    result.getString("prenom"),
                                    result.getString("photo"),
                                    result.getString("telephone"),
                                    result.getString("ville"),
                                    result.getString("bio"),
                                    result.getInt("nbAbo"),
                                    result.getInt("nbAmi"),
                                    result.getBoolean("bloque")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    public static List<Utilisateur> selectAmis (int id)
    {
        List<Utilisateur> list =new ArrayList<>() ; 
        String req = "SELECT u.* FROM utilisateur u JOIN amitie a ON u.id = a.idUtilisateur2 WHERE a.idUtilisateur1=?";
        String req2 ="SELECT u.* FROM utilisateur u JOIN amitie a ON u.id = a.idUtilisateur1 WHERE a.idUtilisateur2=?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setInt(1,id) ;
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
                list.add(new Utilisateur(
                                        result.getInt("id"),
                                        result.getString("role"),
                                        result.getString("email"),
                                        result.getString("motdp"),
                                        result.getString("nom"),
                                        result.getString("prenom"),
                                        result.getString("photo"),
                                        result.getString("telephone"),
                                        result.getString("ville"),
                                        result.getString("bio"),
                                        result.getInt("nbAbo"),
                                        result.getInt("nbAmi"),
                                    result.getBoolean("bloque")
                )); 
            }
            ste = ds.getConnection().prepareStatement(req2) ;
            ste.setInt(1,id) ;
            result =ste.executeQuery() ; 
            while (result.next()){
                list.add(new Utilisateur(
                                        result.getInt("id"),
                                        result.getString("role"),
                                        result.getString("email"),
                                        result.getString("motdp"),
                                        result.getString("nom"),
                                        result.getString("prenom"),
                                        result.getString("photo"),
                                        result.getString("telephone"),
                                        result.getString("ville"),
                                        result.getString("bio"),
                                        result.getInt("nbAbo"),
                                        result.getInt("nbAmi"),
                                    result.getBoolean("bloque")
                )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    public static TreeSet<String> selectNoms ()
    {
        TreeSet<String> list =new TreeSet<>() ; 
    String req ; 
        req = "SELECT *  FROM utilisateur ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(result.getString("prenom") + " " + result.getString("nom")); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    public static Utilisateur getUtilisateur (int id) throws SQLException 
    {        
        String req = "SELECT *  FROM utilisateur WHERE id =?";
        Utilisateur user = new Utilisateur();
        PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
        ste.setInt(1,id) ;
        ResultSet result =ste.executeQuery() ; 
        if (result.next()) {
            user = new Utilisateur(
                                    result.getInt("id"),
                                    result.getString("role"),
                                    result.getString("email"),
                                    result.getString("motdp"),
                                    result.getString("nom"),
                                    result.getString("prenom"),
                                    result.getString("photo"),
                                    result.getString("telephone"),
                                    result.getString("ville"),
                                    result.getString("bio"),
                                    result.getInt("nbAbo"),
                                    result.getInt("nbAmi"),
                                    result.getBoolean("bloque")
            );
        
        }
         return user ;
      }
    
    public static Utilisateur getUtilisateurByNom (String prenom, String nom) throws SQLException 
    {        
        String req = "SELECT *  FROM utilisateur WHERE prenom=? AND nom=?";
        Utilisateur user = new Utilisateur();
        PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
        ste.setString(1,prenom) ;
        ste.setString(2,nom) ;
        ResultSet result =ste.executeQuery() ; 
        if (result.next()) {
            user = new Utilisateur(
                                    result.getInt("id"),
                                    result.getString("role"),
                                    result.getString("email"),
                                    result.getString("motdp"),
                                    result.getString("nom"),
                                    result.getString("prenom"),
                                    result.getString("photo"),
                                    result.getString("telephone"),
                                    result.getString("ville"),
                                    result.getString("bio"),
                                    result.getInt("nbAbo"),
                                    result.getInt("nbAmi"),
                                    result.getBoolean("bloque")
            );
        
        }
         return user ;
      }
    
    private static LoadingCache<Integer, Utilisateur> empCache;
    static {
	empCache = CacheBuilder.newBuilder()
	       .maximumSize(5)
	       .expireAfterWrite(1, TimeUnit.DAYS)
	       .build(
	           new CacheLoader<Integer, Utilisateur>() {
        		@Override
			public Utilisateur load(Integer id) throws Exception {
				return getUtilisateur(id);
			}
	           }
	       );
    }
    public static LoadingCache<Integer, Utilisateur> getLoadingCache() {
	return empCache;
    }
    
    public static int authenticate (String email, String mdp) throws SQLException {
        String req = "SELECT id FROM utilisateur WHERE email =? and motdp =?";
        PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
        ste.setString(1,email) ;
        ste.setString(2,mdp) ;
        ResultSet result =ste.executeQuery() ; 
        if (result.next()) {
            return result.getInt("id");
        }else{
            return 0;
        }
    }
    
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    
    
}

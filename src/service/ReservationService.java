/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entites.Reservationenseigne;
import java.io.IOException;
import java.util.Properties;
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
 * @author Guideinfo
 */
public class ReservationService {

    static DataSource ds =DataSource.getInstance(); 
    
    
    public static void insererReservation (Reservationenseigne r)
    {
    String req="INSERT INTO reservationenseigne (date_reservation,nb_place,type,prix,idUser,idBonplan) VALUES(?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            //ste.setInt(1,r.getRefRes()) ; 
            
            ste.setDate(1,r.getDateReservation());
            ste.setInt(2,r.getNbPlace());
            ste.setString(3,r.getType());
            ste.setInt(4,r.getPrixRes());
            ste.setInt(5,r.getIdUser()); 
            ste.setInt(6,r.getIdBonplan());
            
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     
    public static void updateReservation (Reservationenseigne r )
    {
    String req="UPDATE reservationenseigne SET nb_place=?,prix=? WHERE ref_res =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
//            ste.setDate(1,r.getDateReservation());
            ste.setInt(1,r.getNbPlace());
            
            ste.setInt(2, r.getPrixRes());
            
            
            
            ste.setInt(3,r.getRefRes()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }   
    
    }
    
     public static void DeletReservation (Reservationenseigne r)
    {
    String req="DELETE from reservationenseigne where  ref_res =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,r.getRefRes()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
     public static void DeletReservationByID (int r)
    {
    String req="DELETE  from reservationenseigne where  ref_res =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,r) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    public static List<Reservationenseigne> selectReservation ()
    {
        List<Reservationenseigne> list =new ArrayList<>() ; 
        String req ; 
        req = "SELECT *  FROM reservationenseigne ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Reservationenseigne(
                                    result.getInt("ref_res"),
                                    result.getDate("date_reservation"),
                                    result.getInt("nb_place"),
                                    result.getString("type"),
                                    result.getInt("prix"),
                                    result.getInt("idUser"),
                                    result.getInt("idBonplan") 
                                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
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
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
  msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
     
    
    
    
    
}

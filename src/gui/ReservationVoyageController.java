/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXTextField;
import entites.Reservationenseigne;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ReservationService;
import com.google.zxing.qrcode.encoder.QRCode;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entites.Evenement;
import entites.Utilisateur;
import entites.Voyage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import net.glxn.qrgen.image.ImageType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UtilisateurService;
import service.VoyageService;

/**
 *
 * @author Sofiene Laouini
 */
public class ReservationVoyageController implements Initializable {
    
    private Label label;
    private JFXTextField nom;
    private JFXTextField client;
    @FXML
    private Label date;
    @FXML
    private JFXTextField nbplace;
    @FXML
    private Label type;
    private JFXTextField prix;
    @FXML
    private JFXButton acceuil;
    @FXML
    private JFXButton profil;
    @FXML
    private JFXButton enseigne;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton voyage;
    @FXML
    private JFXButton parametre;
    @FXML
    private AnchorPane pageProfil;
    @FXML
    private JFXButton ajout;
    @FXML
    private Label nomUser;
    @FXML
    private Label nomEvent;
    
    private Voyage v;
    private Utilisateur user;
    
    
    public void setVoyage(Voyage v){
        this.v = v;
        try {
            // TODO
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            user = UtilisateurService.getUtilisateur(id);
            nomUser.setText(user.getPrenom() + " " + user.getNom());
            nomEvent.setText(v.getDestination());
            date.setText(v.getDateDebut());
            type.setText("Voyage");
        } catch (ExecutionException | SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void ajout (ActionEvent event) throws FileNotFoundException, IOException, ExecutionException {
        Reservationenseigne p;
        p = new Reservationenseigne(Date.valueOf(v.getDateDebut()),Integer.parseInt(nbplace.getText()),"Voyage",Math.round(v.getPrix())*Integer.parseInt(nbplace.getText()),user.getId(),v.getId());
        ReservationService rs = new ReservationService();
        ReservationService.insererReservation(p);
        VoyageService.participer(v,Integer.parseInt(nbplace.getText()));
        String details ="Destination : "+ v.getDestination()+ " Date de reservation : " + Date.valueOf(v.getDateDebut()) + " Nom Client : " + user.getPrenom() + " " + user.getNom() + " Nombre de place : " + Integer.parseInt(nbplace.getText()) + " Prix : " + v.getPrix()*Integer.parseInt(nbplace.getText()) + ".";
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(details).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\Sofiene Laouini\\Desktop\\2eme semestre\\PIDEV\\Integration\\PI-Dev\\QRVoy.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
        Document doc = new Document();
        try {
            
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Sofiene Laouini\\Desktop\\2eme semestre\\PIDEV\\Integration\\PI-Dev\\TicketVoy.pdf"));
            doc.open();
            
            Image im = Image.getInstance("C:\\Users\\Sofiene Laouini\\Desktop\\2eme semestre\\PIDEV\\Integration\\PI-Dev\\QRVoy.jpg");
            doc.add(im);
            doc.add(new Paragraph("Votre Code QR de votre reservation de " + Integer.parseInt(nbplace.getText()) + " Tickets , Pour le " + v.getDateDebut() + "."));
            
            doc.close();
            // Construct data
            String apiKey = "apikey=" + "jsWMrkozARE-wGDOguSYYgoO8jrTgaf6wY8FlEZUPt";
            String message = "&message=" + "Mr/Mme " + user.getPrenom() + " " + user.getNom() +" votre reservation de " + Integer.parseInt(nbplace.getText()) + " place(s) est confirmée , pour le " + v.getDateDebut() + ".";
            String sender = "&sender=" + "Jims Autos";
            String numbers = "&numbers=" + "00216" + user.getTelephone();
            
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "sofiene.laouini@esprit.tn";
            String password = "kiki@159";
            
            // message info
            String mailTo = "sofienelaouini123@gmail.com";
            String subject = "New email with attachments";
            String mess = "I have some attachments for you.";
            
            // attachments
            String[] attachFiles = new String[2];
            attachFiles[0] = "C:\\\\Users\\\\Sofiene Laouini\\\\Desktop\\\\2eme semestre\\\\PIDEV\\\\Integration\\\\PI-Dev\\\\Ticket.pdf";
            attachFiles[1] = "C:\\\\Users\\\\Sofiene Laouini\\\\Desktop\\\\2eme semestre\\\\PIDEV\\\\Integration\\\\PI-Dev\\\\QR.jpg";
            ReservationService.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, mess, attachFiles);
            System.out.println("Email sent.");
            
            Parent root = FXMLLoader.load(getClass().getResource("SimpleFXML.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("WhereTo - Voyage");
            s.setScene(scene);
            s.show();
            //return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            //return "Error "+e;
        }
	}
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        @FXML
    private void handleBtnAccueil(ActionEvent event) {
    }

    @FXML
    private void handleBtnProfil(ActionEvent event) {
    }

    @FXML
    private void handleBtnEnseigne(ActionEvent event) {
    }

    @FXML
    private void handleBtnEvenement(ActionEvent event) {
    }

    @FXML
    private void handleBtnVoyage(ActionEvent event) {
    }

    @FXML
    private void handleBtnParametre(ActionEvent event) {
    }

    @FXML
    private void handleAjHot(ActionEvent event) {
        try {
            AjoutDestinationFXMLController cont = new AjoutDestinationFXMLController(v);
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("ajoutDestinationFXML.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
//            stage.initOwner(liste.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

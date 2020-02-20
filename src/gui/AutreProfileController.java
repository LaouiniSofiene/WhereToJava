/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Amitie;
import entites.Utilisateur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import org.controlsfx.control.textfield.TextFields;
import service.AmitieService;
import service.ReservationService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class AutreProfileController implements Initializable {
        
    Utilisateur user = new Utilisateur();
    Utilisateur currentProfile = new Utilisateur();
    Utilisateur userRecherche = new Utilisateur();
    
    Stage dialogStage = new Stage();
    Scene scene;
    
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
    private Label nbAbo;
    @FXML
    private Label nom;
    @FXML
    private Label bio;
    @FXML
    private JFXTextField search;
    @FXML
    private ImageView photoProfil;
    @FXML
    private Label ville;
    @FXML
    private Label telephone;
    
    private File file;
    @FXML
    private JFXButton addAmi;
    @FXML
    private JFXButton delAmi;
    @FXML
    private Label nbAmi;
    @FXML
    private JFXButton deco;

    /**
     * Initializes the controller class.
     */
    
    public void setUser(Utilisateur u) {
        try {
            currentProfile = UtilisateurService.getUtilisateur(u.getId());
            nbAbo.setText(currentProfile.getNbAbo()+"");
            nbAmi.setText(currentProfile.getNbAmi()+"");
            nom.setText(currentProfile.getPrenom() + " " + currentProfile.getNom());
            ville.setText(currentProfile.getVille());
            telephone.setText(currentProfile.getTelephone());
            if(currentProfile.getBio()==""){;
            bio.setText("Ajoutez une bio");
            }else{
                bio.setText(currentProfile.getBio());
            }
            try {
                file = new File(currentProfile.getPhoto());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photoProfil.setImage(image);
                this.round(photoProfil);
            } catch (IOException ex) {
                
            }
            try {
                if(AmitieService.isAmi(user,currentProfile) != 0){
                    addAmi.setVisible(false);
                    delAmi.setVisible(true);
                }else{
                    addAmi.setVisible(true);
                    delAmi.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AutreProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AutreProfileController.class.getName()).log(Level.SEVERE, null, ex);

        }       
        
    }
    
    private void round(ImageView img){
        Rectangle clip = new Rectangle(
            img.getFitWidth(), img.getFitHeight()
        );
        clip.setArcWidth(133);
        clip.setArcHeight(133);
        img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img.setClip(null);

        // apply a shadow effect.
        img.setEffect(new DropShadow(20, Color.BLACK));

        img.setImage(image);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TextFields.bindAutoCompletion(search, UtilisateurService.selectNoms());
        try{
                LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
                int id = empCache.get(1).getId();

                user = UtilisateurService.getUtilisateur(id);
            } catch (ExecutionException e) {
		e.printStackTrace();
            } catch (SQLException ex) {
            Logger.getLogger(AutreProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void handleBtnAccueil(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
        dialogStage.setTitle("WhereTo - Accueil");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnProfil(ActionEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
        dialogStage.setTitle("WhereTo - Votre Profil");
        dialogStage.setScene(scene);
        dialogStage.show();
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
    private void handleRecherche(ActionEvent event) throws IOException {
        try{
            String[] splitNoms = search.getText().split(" ");
            userRecherche = UtilisateurService.getUtilisateurByNom(splitNoms[0], splitNoms[1]);
            if(user.getId() != userRecherche.getId()){
                Node source = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AutreProfile.fxml"));
                Parent root = (Parent) loader.load();
                AutreProfileController ctrl = loader.getController();
                ctrl.setUser(userRecherche);
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.hide();
                scene = new Scene(root);
                dialogStage.setTitle("WhereTo - " + search.getText());
                dialogStage.setScene(scene);
                dialogStage.show();
            }else{
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
                dialogStage.setTitle("WhereTo - Votre Profil");
                dialogStage.setScene(scene);
                dialogStage.show();
            }
            

            

        }catch(SQLException e){

        }
    }

    @FXML
    private void handleBtnAddAmi(ActionEvent event) throws IOException, MessagingException {
        Amitie a = new Amitie(user.getId(),currentProfile.getId());
        AmitieService.creeAmitie(a);
        UtilisateurService.addAmi(user);
        UtilisateurService.addAmi(currentProfile);
        
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "ghaieth.benmoussa@esprit.tn";
        String password = "bulk255firm208";

        // message info
        String mailTo = "ghaiethbmoussa@gmail.com";
        String subject = "WhereTo: Vous avez un nouvel ami";
        String mess = "Bonjour " + currentProfile.getPrenom() + " " + currentProfile.getNom() + 
                "! L'utilisateur " + user.getPrenom() + " " + user.getNom() + " vous à ajouter en tant qu'ami. " +
                "Vous pouvez le retirer de votre liste d'amis en vous connectant à l'application WhereTo.";

        UtilisateurService.sendEmailWithAttachments(host, port, mailFrom, password, mailTo, subject, mess);
        System.out.println("Email sent.");
        
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AutreProfile.fxml"));
        Parent root = (Parent) loader.load();
        AutreProfileController ctrl = loader.getController();
        ctrl.setUser(currentProfile);
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - " + nom.getText());
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnDelAmi(ActionEvent event) throws SQLException, IOException {
        AmitieService.delAmi(AmitieService.isAmi(user,currentProfile));
        UtilisateurService.delAmi(user);
        UtilisateurService.delAmi(currentProfile);
        
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AutreProfile.fxml"));
        Parent root = (Parent) loader.load();
        AutreProfileController ctrl = loader.getController();
        ctrl.setUser(currentProfile);
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - " + nom.getText());
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnDeco(ActionEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Utilisateur;
import static gui.LoginController.infoBox;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ParametresController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Utilisateur user = new Utilisateur();

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
    private JFXButton deco;
    @FXML
    private AnchorPane pageProfil;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField mdpAct;
    @FXML
    private JFXTextField mdpNouv2;
    @FXML
    private JFXTextField mdpNouv1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            
            user = UtilisateurService.getUtilisateur(id);
            mail.setText(user.getEmail());
        } catch (ExecutionException | SQLException ex) {
            Logger.getLogger(ParametresController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void handleBtnDeco(ActionEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleSupprimer(ActionEvent event) throws IOException {
        UtilisateurService.DeletUtilisateur(user);
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
        
    }

    @FXML
    private void handleModifier(ActionEvent event) throws IOException {
        
        String mdpActuel = mdpAct.getText();
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(mdpActuel.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        
        if(mdpNouv1.getText().equals("") && mdpNouv2.getText().equals("")){
            if(mdpAct.getText().equals("")){
                infoBox("Pour modifier votre E-mail, veuillez tapper votre mot de passe actuel", "Failed", null);
            }else if(!Objects.equals(generatedPassword,user.getMotdp())){
                infoBox("Votre mot de passe actuel est érroné", "Failed", null);
            }else{
                UtilisateurService.updateParametre(mail.getText(), user.getMotdp(), user.getId());
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
                dialogStage.setTitle("WhereTo - Accueil");
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }else{
            if(mdpAct.getText().equals("")){
                infoBox("Pour modifier votre E-mail et votre mot de passe, veuillez tapper votre mot de passe actuel", "Failed", null);
            }else if(!Objects.equals(generatedPassword,user.getMotdp())){
                infoBox("Votre mot de passe actuel est érroné", "Failed", null);
            }else if(!Objects.equals(mdpNouv1.getText(),mdpNouv2.getText())){
                infoBox("La confirmation du mot de passe est érroné", "Failed", null);
            }else{
                String mdpNouveau = mdpNouv1.getText();
                String pass = null;
                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(mdpNouveau.getBytes());
                    //Get the hash's bytes
                    byte[] bytes = md.digest();
                    //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    //Get complete hashed password in hex format
                    pass = sb.toString();
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }
                UtilisateurService.updateParametre(mail.getText(), pass, user.getId());
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
                dialogStage.setTitle("WhereTo - Accueil");
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    
}

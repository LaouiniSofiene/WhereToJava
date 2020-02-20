/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.CRUD_Enseigne;
import entites.Enseigne;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mohamed El Hammi
 */
public class AccueilProprioController implements Initializable {


    @FXML
    private Button btProp;
    @FXML
    private ImageView img1;
    @FXML
    private Label nomEns1;
    @FXML
    private ImageView img2;
    @FXML
    private Label nomEns2;
    @FXML
    private ImageView img3;
    @FXML
    private Label nomEns3;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CRUD_Enseigne crE = new CRUD_Enseigne();
        
//        try {
//            List<Enseigne> lEnseigne= crE.displayEnsNote();
//                  nomEns1.setText(lEnseigne.get(0).getNom());
//                  Image imgA = new Image(lEnseigne.get(0).getPhoto1());
//                  img1.setImage(imgA);
//                  
//                  nomEns2.setText(lEnseigne.get(1).getNom());
//                  Image imgB = new Image(lEnseigne.get(1).getPhoto1());
//                  img2.setImage(imgB);
//                  
//                  nomEns3.setText(lEnseigne.get(1).getNom());
//                  Image imgC = new Image(lEnseigne.get(2).getPhoto1());
//                  img3.setImage(imgC);
//
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(AccueilProprioController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        
    }    


    
    
    @FXML
    private void accueilbt(ActionEvent event) throws IOException {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AccueilProprio.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
        @FXML
    private void afficheRestaurant(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    } 
    
    @FXML
    private void afficheBar(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Bar.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private void afficheCinema(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    } 

    @FXML
    private void handleBtnAccueil(ActionEvent event) {

    }

    @FXML
    private void handleBtnProfil(ActionEvent event) {
//        Node source = (Node) event.getSource();
//        dialogStage = (Stage) source.getScene().getWindow();
//        dialogStage.close();
//        scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
//        dialogStage.setTitle("WhereTo - Votre Profil");
//        dialogStage.setScene(scene);
//        dialogStage.show();
    }

    @FXML
    private void handleBtnEnseigne(ActionEvent event) throws IOException {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AccueilProprio.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class AccueilController implements Initializable {

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
    
    Stage dialogStage = new Stage();
    Scene scene;
    @FXML
    private AnchorPane pageProfil;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton deco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleBtnAccueil(ActionEvent event) {
    }

    @FXML
    private void handleBtnProfil(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
        dialogStage.setTitle("WhereTo - Votre Profil");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnEnseigne(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("AccueilProprio.fxml")));
        dialogStage.setTitle("WhereTo - Enseignes");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnEvenement(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("AffichageEvenement.fxml")));
        dialogStage.setTitle("WhereTo - Evénements");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnVoyage(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("SimpleFXML.fxml")));
        dialogStage.setTitle("WhereTo - Voyages");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnParametre(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Parametres.fxml")));
        dialogStage.setTitle("WhereTo - Paramètres");
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

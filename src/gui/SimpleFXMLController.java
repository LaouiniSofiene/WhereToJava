/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXListView;
import entites.Voyage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.VoyageService;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class SimpleFXMLController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private Button reserver;
    @FXML
    private Button info;
    @FXML
    private JFXListView<Voyage> liste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    VoyageService service = new VoyageService();
        
        ObservableList<Voyage> listeVoyage = FXCollections.observableArrayList(service.selectVoyage());
        liste.setItems(listeVoyage);
        liste.setCellFactory(param->new AdminFXMLController.Cell());

        
    }    

    @FXML
    private void reserver(ActionEvent event) {
        try {
            
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationVoyage.fxml"));
        Parent root = (Parent) loader.load();
        ReservationVoyageController ctrl = loader.getController();
        ctrl.setVoyage(liste.getSelectionModel().getSelectedItem());
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - Réservation");
        dialogStage.setScene(scene);
        dialogStage.show();
        } catch (IOException ex) {
        }
//    VoyageService.participer(liste.getSelectionModel().getSelectedItem());
//     VoyageService service = new VoyageService();
//        
//        ObservableList<Voyage> listeVoyage = FXCollections.observableArrayList(service.selectVoyage());
//        liste.setItems(listeVoyage);
//        liste.setCellFactory(param->new AdminFXMLController.Cell());

    }

    @FXML
    private void info(ActionEvent event) {
        try {
            AjoutDestinationFXMLController cont = new AjoutDestinationFXMLController(liste.getSelectionModel().getSelectedItem());
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("ajoutDestinationFXML.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(liste.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    private void handleBtnAccueil(ActionEvent event) {
    }

    @FXML
    private void handleBtnProfil(ActionEvent event){
    
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
    
    
    }

    


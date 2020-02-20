/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXListView;
import entites.Utilisateur;
import entites.Voyage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.UtilisateurService;
import service.VoyageService;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class ListeVoyageController implements Initializable {

    @FXML
    private JFXListView<Voyage> Liste;
    
    private Utilisateur user;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VoyageService service = new VoyageService();
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            user = UtilisateurService.getUtilisateur(id);
            ObservableList<Voyage> listeVoyage = FXCollections.observableArrayList(service.selectVoyageByUserId(id));
            Liste.setItems(listeVoyage);
            Liste.setCellFactory(param -> new AdminFXMLController.Cell());
        } catch (ExecutionException | SQLException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Modifier_voyage(ActionEvent event) {
        try {
            ModifierVoyageController cont = new ModifierVoyageController(Liste.getSelectionModel().getSelectedItem());
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("ModifierVoyage.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(Liste.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
@FXML
    private void Supprimer_voyage(ActionEvent event) {
try {
            int id = Liste.getSelectionModel().getSelectedItem().getId();
           VoyageService s = new VoyageService();
            s.DeletVoyagrByID(id);
            Parent root = FXMLLoader.load(getClass().getResource("listeVoyage.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.Liste.getScene().getWindow();
            x.setTitle("Ajout Voyage");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } ;   


    @FXML
    private void ajout_voyage(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("TestFXML.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) this.Liste.getScene().getWindow();
            s.setTitle("Ajout Voyage");
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
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

    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Utilisateur;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class UtilisateurAdminController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private AnchorPane plane;
    @FXML
    private AnchorPane topbar;
    @FXML
    private ImageView Voyage;
    @FXML
    private ImageView enseignes;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView event;
    @FXML
    private ImageView user;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, String> idUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> prenomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> emailUtilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurService service = new UtilisateurService();
        
        ObservableList<Utilisateur> listeUtilisateur = FXCollections.observableArrayList(service.selectUtilisateur());
        table.setItems(listeUtilisateur);
        idUtilisateur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getId());
            }
        });
        prenomUtilisateur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrenom());
            }
        });
        nomUtilisateur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
        emailUtilisateur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEmail());
            }
        });
         
    }
     

    @FXML
    private void handleVoyage(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("AdminFXML.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Voyage");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnConsulter(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileAdmin.fxml"));
        Parent root = (Parent) loader.load();
        ProfileAdminController ctrl = loader.getController();
        ctrl.setUser(table.getSelectionModel().getSelectedItem());
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - " + table.getSelectionModel().getSelectedItem().getPrenom() + " " + table.getSelectionModel().getSelectedItem().getNom());
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
    @FXML
    private void handleBtnDeco(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleEvent(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("ListeEvenement.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Evénements");
        dialogStage.setScene(scene);
        dialogStage.show();
        
    }

    @FXML
    private void handleEnseigne(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("AdminEnseigne.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Enseignes");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
}

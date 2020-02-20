/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Enseigne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CRUD_Enseigne;
import entites.Enseigne;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mohamed El Hammi
 */
public class BarController implements Initializable {

    @FXML
    private TableView<Enseigne> tbInformation;
    @FXML
    private TableColumn<Enseigne, String> colNom;
    @FXML
    private TableColumn<Enseigne, String> colDescription;
    @FXML
    private TableColumn<Object, ImageView> colImage;
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

        assert tbInformation != null : "fx:id=\"tbInformation\" was not injected: check your FXML file 'FormulaireEnseigne.fxml'.";

        colNom.setCellValueFactory(
                new PropertyValueFactory<Enseigne, String>("nom"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<Enseigne, String>("description"));
        colImage.setCellValueFactory(
                new PropertyValueFactory<Object, ImageView>("image"));

        buildData();

        /**
         * *********************************************
         */
        tbInformation.setRowFactory(tv -> {
            TableRow<Enseigne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //String rowData = row.getItem();
                    //System.out.println(rowData);
                    Enseigne EnseigneSelected = tbInformation.getSelectionModel().getSelectedItem();

                    Enseigne e = new Enseigne(EnseigneSelected.getIdEnseigne(), EnseigneSelected.getIdUtilisateur(), EnseigneSelected.getType(), EnseigneSelected.getNom(), EnseigneSelected.getDescription(), EnseigneSelected.getAdresse(), EnseigneSelected.getNote(), EnseigneSelected.getSpecialite(), EnseigneSelected.getNumTel(), EnseigneSelected.getSignalEnseigne(), EnseigneSelected.getPhoto1(), EnseigneSelected.getPhoto2(), EnseigneSelected.getPhoto3());

                    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("EnseigneInterface.fxml"));
                    Parent root;
                    try {
                        root = myLoader.load();
                          EnseigneInterfaceController.en=e;
                        EnseigneInterfaceController enseigneInterfaceController = (EnseigneInterfaceController) myLoader.getController();                       
                        enseigneInterfaceController.setNom(e);
                        enseigneInterfaceController.setDescription(e);
                        enseigneInterfaceController.setAdresse(e);
                        enseigneInterfaceController.setSpecialite(e);
                        enseigneInterfaceController.setHoraire(e);
                        enseigneInterfaceController.setNumTel(e);
                        enseigneInterfaceController.setSignal(e);
                        
                        enseigneInterfaceController.setPhoto1(e);
                        enseigneInterfaceController.setPhoto2(e);
                        enseigneInterfaceController.setPhoto3(e);

                        Stage stage = new Stage();
                        stage.setTitle("Enseigne");
                        stage.setScene(new Scene(root));
                        stage.show();
                        // Hide this current window (if this is what you want)
                        ((Node) (event.getSource())).getScene().getWindow().hide();

                    } catch (IOException ex) {
                        Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(BarController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;

        });
    }

    private ObservableList<Enseigne> data;

    public void buildData() {
        data = FXCollections.observableArrayList();
        try {
            CRUD_Enseigne crE = new CRUD_Enseigne();

            List<Enseigne> lEnseigne = crE.displayType("bar");

            for (Enseigne e : lEnseigne) {
                Image img = new Image(e.getPhoto1());
                ImageView mv = new ImageView();
                mv.setImage(img);
                mv.setFitWidth(70);
                mv.setFitHeight(80);
                e.setImage(mv);
            }

            data.addAll(lEnseigne);
            tbInformation.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        /* el double click row yomken nbadalha houni */
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
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void afficheRestaurant(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
        Parent root = myLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Formulaire");
        stage.setScene(new Scene(root));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void afficheBar(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Bar.fxml"));
        Parent root = myLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Formulaire");
        stage.setScene(new Scene(root));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void afficheCinema(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
        Parent root = myLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Formulaire");
        stage.setScene(new Scene(root));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void handleBtnAccueil(ActionEvent event) {
    }

    @FXML
    private void handleBtnProfil(ActionEvent event) {
//                Node source = (Node) event.getSource();
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

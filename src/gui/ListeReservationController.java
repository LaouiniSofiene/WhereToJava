/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Reservationenseigne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ListeReservationController implements Initializable {

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
    private JFXTextField txtRecherche;
    @FXML
    private TableView<Reservationenseigne> table;
    @FXML
    private TableColumn<Reservationenseigne, String> date;
    @FXML
    private TableColumn<Reservationenseigne, String> nbplace;
    @FXML
    private TableColumn<Reservationenseigne, String> type;
    @FXML
    private JFXButton modifier;
    @FXML
    private TableColumn<Reservationenseigne, String> bonplan;
    @FXML
    private TableColumn<Reservationenseigne, String> prix;

    public static Reservationenseigne selectedReservation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(table.getSelectionModel().getSelectedItem() != null) 
        {    
           
           System.out.println("Selected Value" + table.getSelectionModel().getSelectedItem());
          selectedReservation=table.getSelectionModel().getSelectedItem();
           
         }
         }
     });
       
      
        ReservationService service = new ReservationService();
        
        ObservableList<Reservationenseigne> listeReservation = FXCollections.observableArrayList(service.selectReservation());
        table.setItems(listeReservation);
        bonplan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationenseigne, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservationenseigne, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getIdBonplan());
            }
        });
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationenseigne, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservationenseigne, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateReservation());
            }
        });
        nbplace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationenseigne, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservationenseigne, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNbPlace());
            }
        });
        type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationenseigne, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservationenseigne, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType());
            }
        });
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservationenseigne, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservationenseigne, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixRes());
            }
        });
        
        
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reservationenseigne> filteredData = new FilteredList<>( listeReservation , p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getType().toLowerCase().contains(lowerCaseFilter)) 
                {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });

       
        table.setItems(filteredData);
         
        // TODO
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
    private void modifier(ActionEvent event) {
        try {
            ModifierReservationController cont = new ModifierReservationController(table.getSelectionModel().getSelectedItem());
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("modifierReservation.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            final Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(table.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
         try {
            int id = table.getSelectionModel().getSelectedItem().getRefRes();
            ReservationService s = new ReservationService();
            s.DeletReservationByID(id);
            Parent root = FXMLLoader.load(getClass().getResource("ListeResrvation.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.table.getScene().getWindow();
            x.setTitle("liste");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Evenement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListeEvenementController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TableView<Evenement> tableEvenement;
    
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> etab;
    @FXML
    private TableColumn<Evenement, String> debut;
    @FXML
    private TableColumn<Evenement, String> fin;
    @FXML
    private TableColumn<Evenement, String> desc;
    @FXML
    private TableColumn<Evenement,String> theme;
    public static  Evenement selectedEvent;
    @FXML
    private TextField txtRecherche;
    private Image imageE;
    @FXML
    private TableColumn<Evenement, Integer> nbr_place;
    @FXML
    private TableColumn<Evenement, Integer> prix;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
             EvenementService service = new EvenementService();
             selectedEvent = new Evenement();
        ObservableList<Evenement> listeEvenement = FXCollections.observableArrayList(service.selectEvenement());
        
        tableEvenement.setItems(listeEvenement);
        
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
        etab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRefEtab());
            }
        });
        debut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDebut());
            }
        });
        fin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateFin());
            }
        });
        desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
         theme.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Evenement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTheme());
            }
        });
         nbr_place.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Evenement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNbr_place());
            }
        });
          prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Evenement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix());
            }
        });
         
        
        
         tableEvenement.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(tableEvenement.getSelectionModel().getSelectedItem() != null) 
        {    
           
           System.out.println("Selected Value" + tableEvenement.getSelectionModel().getSelectedItem());
           selectedEvent=tableEvenement.getSelectionModel().getSelectedItem();
           
         }
         }
     });
       
     
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(listeEvenement, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getNom().toLowerCase().contains(lowerCaseFilter)) 
                {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });

       
        tableEvenement.setItems(filteredData);
    }    

    

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
          try {
            int id = tableEvenement.getSelectionModel().getSelectedItem().getRef();
            EvenementService s = new EvenementService();
            s.DeletEvenementByID(id);
            Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.tableEvenement.getScene().getWindow();
            x.setTitle("");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ModifierEv(ActionEvent event) {
          
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) this.tableEvenement.getScene().getWindow();
            s.setTitle("Modif Evenement");
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void AjouterEv(ActionEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) this.tableEvenement.getScene().getWindow();
            s.setTitle("Ajout Evenement");
            s.setScene(scene);
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleVoyages(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("AdminFXML.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Voyage");
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
    private void handleUsers(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("UtilisateurAdmin.fxml")));
        dialogStage.setTitle("WhereTo - Géstion Utilisateurs");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    

    
        
}


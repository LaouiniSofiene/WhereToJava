/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import entites.Voyage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.VoyageService;
import util.RemoteFileHandler;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AdminFXMLController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;

   
    private ListView<Voyage> Liste;
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
    private TableView<Voyage> table;
    @FXML
    private TableColumn<Voyage, String> debut;
    @FXML
    private TableColumn<Voyage, String> fin;
    @FXML
    private TableColumn<Voyage, Float> prix;
    @FXML
    private TableColumn<Voyage, String> destination;
    @FXML
    private TableColumn<Voyage, String> description;
    @FXML
    private TableColumn<Voyage, Integer> nb;
    @FXML
    private JFXTextField txtRecherche;

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
    private void handleUsers(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("UtilisateurAdmin.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Utilisateurs");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    

    static class Cell extends ListCell<Voyage> {
          protected final HBox hBox;
    protected final ImageView imageView;
    protected final Separator separator;
    protected final VBox vBox;
    protected final Label destination;
    protected final Separator separator0;
    protected final Label duree;
    protected final Separator separator1;
    protected final Label desc;
    protected final Separator separator2;
    protected final VBox vBox0;
    protected final Label prix;
    protected final Label places;

        public Cell() {
         super();
         hBox = new HBox();
        imageView = new ImageView();
        separator = new Separator();
        vBox = new VBox();
        destination = new Label();
        separator0 = new Separator();
        duree = new Label();
        separator1 = new Separator();
        desc = new Label();
        separator2 = new Separator();
        vBox0 = new VBox();
        prix = new Label();
        places = new Label();

        setId("AnchorPane");
        setPrefHeight(150.0);
        setPrefWidth(550.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);
        

        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(200.0);

        HBox.setHgrow(vBox, javafx.scene.layout.Priority.ALWAYS);
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);
        vBox.setStyle("-fx-background-color: #008F95;");

        destination.setText("Label");
        destination.setFont(new Font(36.0));

        separator0.setPrefWidth(200.0);

        duree.setText("Label");
        duree.setFont(new Font(20.0));

        separator1.setPrefWidth(200.0);

        desc.setText("Label");
        desc.setFont(new Font(18.0));

        separator2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator2.setPrefHeight(200.0);

        vBox0.setPrefHeight(200.0);
        vBox0.setPrefWidth(100.0);
vBox0.setStyle("-fx-background-color: #008F95;");
        prix.setText("Label");
        prix.setFont(new Font(20.0));

        places.setText("Label");
        places.setFont(new Font(20.0));

        hBox.getChildren().add(imageView);
        hBox.getChildren().add(separator);
        vBox.getChildren().add(destination);
        vBox.getChildren().add(separator0);
        vBox.getChildren().add(duree);
        vBox.getChildren().add(separator1);
        vBox.getChildren().add(desc);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(separator2);
        vBox0.getChildren().add(prix);
        vBox0.getChildren().add(places);
        hBox.getChildren().add(vBox0);
        getChildren().add(hBox);

        }
        
        protected void updateItem(Voyage item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null) {
                destination.setText(item.getDestination());
                desc.setText(item.getDescription());
                prix.setText("" + item.getPrix() + " TND");
                try {
                    imageView.setImage(new Image(new FileInputStream(RemoteFileHandler.download(item.getImage()))));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                duree.setText("Du " + item.getDateDebut() + " jusqu'a " + item.getDateFin());
               places.setText(""+item.getNb_dispo()+" Place(s)");
                setGraphic(hBox);

            }
        }

    
        
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Voyage> listeVoyage = FXCollections.observableArrayList(VoyageService.selectVoyage());
        table.setItems(listeVoyage);
        debut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Voyage, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDebut());
            }
        });
        fin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Voyage, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateFin());
            }
        });
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Voyage, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix());
            }
        });
        destination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Voyage, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination());
            }
        });
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Voyage, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
        
        nb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Voyage, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Voyage, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNb_dispo());
            }
        });
        FilteredList<Voyage> filteredData = new FilteredList<>(listeVoyage, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getDestination().toLowerCase().contains(lowerCaseFilter)) 
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
    private void bloquer(ActionEvent event) {
        try {
            int id = table.getSelectionModel().getSelectedItem().getId();
           VoyageService s = new VoyageService();
            s.DeletVoyagrByID(id);
            Parent root = FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.table.getScene().getWindow();
            x.setTitle("Ajout Voyage");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

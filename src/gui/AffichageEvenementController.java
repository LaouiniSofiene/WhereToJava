/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Evenement;
import entites.Utilisateur;
import static gui.ListeEvenementController.selectedEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.EvenementService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class AffichageEvenementController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;

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
    private JFXTextField txt_recherche;
    @FXML
    private ListView<Evenement> list_event;

    static class cell extends ListCell<Evenement>{
        
         protected final HBox hBox;
    protected final VBox vBox;
    protected final ImageView imageView;
    protected final Separator separator;
    protected final VBox duree;
    protected final Label event;
    protected final Separator separator0;
    protected final Label label;
    protected final Separator separator1;
    protected final Label theme;
    protected final Separator separator2;
    protected final Label desc;
    protected final Separator separator3;
    protected final VBox vBox0;
    protected final Separator separator4;
    protected final Label nbrpl;
    protected final Separator separator5;
    protected final Label prix;
    protected final Label label0;
    protected final Label label1;
    protected final Label etab;

        public cell() {
            super();
            hBox = new HBox();
        vBox = new VBox();
        imageView = new ImageView();
        separator = new Separator();
        duree = new VBox();
        event = new Label();
        separator0 = new Separator();
        label = new Label();
        separator1 = new Separator();
        theme = new Label();
        separator2 = new Separator();
        desc = new Label();
        separator3 = new Separator();
        vBox0 = new VBox();
        separator4 = new Separator();
        nbrpl = new Label();
        separator5 = new Separator();
        etab = new Label();
        prix = new Label();
        label0 = new Label();
        label1 = new Label();

        setId("AnchorPane");
        setPrefHeight(200.0);
        setPrefWidth(500.0);
        setStyle("-fx-background-color: #E9B000;");

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(200.0);
        hBox.setPrefWidth(500.0);
        hBox.setStyle("-fx-background-color: #008F95;");

        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);
        vBox.setStyle("-fx-background-color: #008F95;");

        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(150.0);
        separator.setPrefWidth(13.0);
        separator.setStyle("-fx-background-color: #008F95;");
        separator.setOpacity(0.0);
        
        

        duree.setPrefHeight(150.0);
        duree.setPrefWidth(200.0);

        event.setPrefWidth(136.0);
        event.setText("Label");
        event.setFont(new Font(37.0));
        
        separator0.setOpacity(0.0);
        separator0.setPrefWidth(200.0);
        separator0.setStyle("-fx-background-color: #008F95;");

        label.setText("Label");
        label.setFont(new Font(11.0));
        separator1.setOpacity(0.0);
        separator1.setPrefWidth(200.0);
        separator1.setStyle("-fx-background-color: #008F95;");

        theme.setPrefHeight(17.0);
        theme.setPrefWidth(61.0);
        theme.setText("Label");
        theme.setFont(new Font(13.0));
        
        separator2.setOpacity(0.0);
        separator2.setPrefWidth(200.0);
        separator2.setStyle("-fx-background-color: #008F95;");

        desc.setText("Label");
        desc.setFont(new Font(23.0));
        
        separator3.setOpacity(0.0);
        separator3.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator3.setPrefHeight(200.0);
        separator3.setPrefWidth(15.0);
        separator3.setStyle("-fx-background-color: #008F95;");

        vBox0.setPrefHeight(200.0);
        vBox0.setPrefWidth(100.0);
        
        separator4.setOpacity(0.0);
        separator4.setPrefHeight(46.0);
        separator4.setPrefWidth(100.0);
        separator4.setStyle("-fx-background-color: #008F95;");
        label0.setText("places");

         etab.setText("Label");
        etab.setVisible(true);
        
        nbrpl.setText("Label");
        nbrpl.setFont(new Font(21.0));

        separator5.setOpacity(0.0);
        separator5.setPrefHeight(60.0);
        separator5.setPrefWidth(100.0);
        separator5.setStyle("-fx-background-color: #008F95;");
        label1.setText("Dt");

        prix.setText("Label");
        prix.setFont(new Font(22.0));

        vBox.getChildren().add(imageView);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(separator);
        duree.getChildren().add(event);
        duree.getChildren().add(separator0);
        duree.getChildren().add(label);
        duree.getChildren().add(separator1);
        duree.getChildren().add(theme);
        duree.getChildren().add(separator2);
        duree.getChildren().add(desc);
        hBox.getChildren().add(duree);
        hBox.getChildren().add(separator3);
        vBox0.getChildren().add(separator4);
        vBox0.getChildren().add(nbrpl);
        vBox0.getChildren().add(label0);
        vBox0.getChildren().add(separator5);
        vBox0.getChildren().add(prix);
        hBox.getChildren().add(vBox0);
        vBox0.getChildren().add(label1);
        hBox.getChildren().add(etab);
        
        getChildren().add(hBox);
        
        }

        @Override
        protected void updateItem(Evenement item, boolean empty) {
            super.updateItem(item, empty); 
            setText(null);
            setGraphic(null);
            if (item != null) {
                desc.setText(item.getDescription());
               event.setText(item.getNom());
              nbrpl.setText(""+item.getNbr_place());
              theme.setText(item.getTheme());
                prix.setText(""+item.getPrix());
                etab.setText(item.getEtab());
               
                try {
                    imageView.setImage(new Image(new URL(item.getIimage()).openStream()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FrontEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FrontEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrontEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
               label.setText("Du "+item.getDateDebut()+" jusqu'a "+item.getDateFin());
              
                setGraphic(hBox);
                
            }
            
        }
    
        
        
        
        
        
        
        
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EvenementService service = new EvenementService();
        ObservableList<Evenement> listeEvenement = FXCollections.observableArrayList(service.selectEvenement());
        list_event.setItems(listeEvenement);
        list_event.setCellFactory(param-> new cell());
        list_event.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(list_event.getSelectionModel().getSelectedItem() != null)
                {
                    
                    System.out.println("Selected Value" + list_event.getSelectionModel().getSelectedItem());
                    selectedEvent=list_event.getSelectionModel().getSelectedItem();
                    
                }
            }
        });
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<Evenement>(list_event.getItems(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        txt_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
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
        list_event.setItems(filteredData);
    }    

    @FXML
    private void handleBtnAccueil(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
        dialogStage.setTitle("WhereTo - Accueil");
        dialogStage.setScene(scene);
        dialogStage.show();
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
    private void handleBtnEnseigne(ActionEvent event) {
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
    private void handleBtnVoyage(ActionEvent event) {
    }

    @FXML
    private void handleBtnParametre(ActionEvent event) {
    }

    @FXML
    private void handleReserv(ActionEvent event) {
        try {
            
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        FXMLDocumentController ctrl = loader.getController();
        ctrl.setEvent(list_event.getSelectionModel().getSelectedItem());
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - Réservation");
        dialogStage.setScene(scene);
        dialogStage.show();
        } catch (IOException ex) {
        }
    }
    
}

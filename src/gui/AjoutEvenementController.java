/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.ComponentFilter;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import entites.Evenement;
import entites.Utilisateur;
import  javafx.scene.input.KeyEvent ;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import service.EvenementService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutEvenementController implements Initializable ,MapComponentInitializedListener{
    
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    protected Button VoirEtab;
    @FXML
    protected GoogleMapView mapView;
    @FXML
    private TextField nom;
    @FXML
    private TextField etab;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField decs;
    @FXML
    private TextField image;
    File file;
    Stage stage;
    @FXML
    private Label label1;
    @FXML
    private Button btAjoutEvent;
    @FXML
    private ComboBox<String> comboTheme;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField prix;
   private GoogleMap map;
   private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    GeoApiContext.Builder b = new GeoApiContext.Builder().apiKey("AIzaSyDx80qyb9RKt3CcYtDfyt8paY5gpMK10bc");
   
    GeoApiContext context = b.build();

    List<String> hints = new ArrayList<>();
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mapView.addMapInializedListener(this);
        address.bind(etab.textProperty());
        TextFields.bindAutoCompletion(etab, e -> {
            hints.clear();
            
            if (etab.getText().trim().length() > 0) {
                PlaceAutocompleteRequest req = PlacesApi.placeAutocomplete(context, etab.getText());
                req.components(ComponentFilter.country("tn"));
                try {
                    AutocompletePrediction[] results = req.await();
                    System.out.println();
                    for (AutocompletePrediction item : results) {
                        hints.add(item.description);
                    }        
                } catch (ApiException | InterruptedException | IOException ex) {
                    //Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return hints;
            
        });
          comboTheme.getItems().addAll("Musique","Culture","Conference","Enfance","Autres");
          comboTheme.setValue("Musique");
            fin.setDisable(true);
          debut.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) 
                        {         
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
             }
            }
        });
          debut.valueProperty().addListener(new ChangeListener(){
             @Override
            public void changed (ObservableValue observable, Object oldValue, Object newValue)
            {
              fin.setDisable(false);
              fin.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(debut.getValue())) 
                        {         
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
             }
            }
        });
          
             }
         });
           
    }    
    
   

    void restrictNumbersOnly(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
        case TAB:
        case BACK_SPACE:
        case DELETE:
            break;
        default:
            if (!keyEvent.getText().matches("\\d")) {
            // if (!keyEvent.getCode().isDigitKey()) {
                keyEvent.consume();
            }
    }
}

void restrictLettersOnly(KeyEvent keyEvent) {
    KeyCode code = keyEvent.getCode();
    switch (code) {
        case TAB:
        case BACK_SPACE:
        case DELETE:
            break;
        default:
            if (!code.isLetterKey()) {
                keyEvent.consume();
            }
    }
}
    
   
    
    
    @FXML
    private void AjouterEvenement(ActionEvent event) throws Exception {
        
        nom.addEventFilter(KeyEvent.KEY_PRESSED, this::restrictLettersOnly);
       
                 
            if(decs.getText().isEmpty() | nom.getText().isEmpty() | etab.getText().isEmpty()|debut.getValue()==null |fin.getValue()==null| nbr_place.getText().isEmpty()|prix.getText().isEmpty())
            {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Verify Your TextFields");
            alert.showAndWait();
            
             }
	    else
            {
                LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
                int id = empCache.get(1).getId();
                         Evenement v =new Evenement(nom.getText(),etab.getText(),debut.getValue().toString(),fin.getValue().toString(),decs.getText(),image.getText(),comboTheme.getValue(),Integer.parseInt(nbr_place.getText()),Integer.parseInt(prix.getText()),id);

                EvenementService sv=new EvenementService();
                sv.insererEvenement(v);
        
                 Notifications notificationBuilder = Notifications.create()
                
                .title("WhereTo")
                .text("votre evenement a été ajouter")
                 .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("");
                        }
                });
         notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
              
            }
        
    }
  @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.73, 10.04))
                
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
         map = mapView.createMap(options);
       
    }
    @FXML
    private void toTextFieldAction( ActionEvent event) {
           geocodingService.geocode(etab.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            map.clearMarkers();
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);
            map.setZoom(15);
            MarkerOptions markerOptions = new MarkerOptions();
             markerOptions.position(latLong)
            .visible(true);
             Marker marker = new Marker(markerOptions);
             map.addMarker(marker);

        });}
    private void AficherEvenement(ActionEvent event)  {
               try {
            Parent root = FXMLLoader.load(getClass().getResource("listeEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Liste");
            s.setScene(scene);
            s.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        

        file = fileChooser.showOpenDialog(stage);
        
        
        if (file!=null) {
            try {
                String img = file.toURI().toURL().toString();
                System.out.println(img);
                System.out.println("jawek béhi");
                image.setText(img);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      
 
      
          
    }
     void setStage(Stage primaryStage) {
        this.stage = primaryStage;  }

    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("FrontEvenement.fxml")));
        dialogStage.setTitle("WhereTo - Evénements");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    

    }
    


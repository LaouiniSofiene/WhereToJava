/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import entites.Destination;
import entites.Voyage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import org.controlsfx.control.ListSelectionView;
import service.DestinationService;
import service.VoyageService;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AjoutDestinationFXMLController implements Initializable {

    @FXML
    private ListSelectionView<Destination> TableDestination;
    private final Voyage voyage;
    List<Destination> destinationList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       GeoApiContext.Builder b = new GeoApiContext.Builder();
       destinationList= new ArrayList<>();
        b.apiKey("AIzaSyB2mdcSMjO3CWf3D5B5bkY_aUQXRlZprxw");
        GeoApiContext con = b.build();
        GeocodingApiRequest geocodingApiRequest = GeocodingApi.geocode(con,voyage.getDestination());
        try {
            GeocodingResult[] geocodingResults = geocodingApiRequest.await();
            GeocodingResult results = geocodingResults[0];
            NearbySearchRequest r = PlacesApi.nearbySearchQuery(con, results.geometry.location);
            r.radius(10000);
            r.type(PlaceType.ESTABLISHMENT);
            PlacesSearchResult[] res =  r.await().results;
            for (PlacesSearchResult e :
                    res) {
                System.out.println(e.placeId);
                PlaceDetailsRequest request = new PlaceDetailsRequest(con);
                request.placeId(e.placeId);
                PlaceDetails details = request.await();
                Destination d = new Destination();
                d.setNom(details.name);
                d.setAdresse(details.formattedAddress);
                d.setRating(details.rating);
                d.setType(details.types[0]);
                d.setV(voyage);
                destinationList.add(d);
            }

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Destination> listeDes = FXCollections.observableArrayList(destinationList);
        TableDestination.setSourceItems(listeDes);
        TableDestination.setCellFactory(listView -> {
                    ListCell<Destination> cell = new ListCell<Destination>() {
                        @Override
                        public void updateItem(Destination item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                setText(item.getNom());
                                setGraphic(null);
                            }
                        }
                    };
                    cell.setFont(Font.font("Arial", FontWeight.BOLD,
                            FontPosture.ITALIC, 18));
                    return cell;
                });
    }    

    public AjoutDestinationFXMLController(Voyage voyage) {
        this.voyage = voyage;
    }

    
    
    @FXML
    private void handleConfirmerAction(ActionEvent event) {
        VoyageService.insererVoyage(voyage);
        
        for(Destination d : TableDestination.getTargetItems())
        {   
            d.setV(voyage);
            DestinationService.insererDestination(d);
        }

        
    }
    
}

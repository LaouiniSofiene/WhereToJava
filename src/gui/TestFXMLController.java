/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import entites.Destination;
import entites.Utilisateur;
import entites.Voyage;
import static java.awt.Event.BACK_SPACE;
import static java.awt.Event.DELETE;
import static java.awt.Event.TAB;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UtilisateurService;
import service.VoyageService;
import util.RemoteFileHandler;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class TestFXMLController implements Initializable {
    private File fichier;
    public static List<Destination> listDestination = new ArrayList<>();
    @FXML
    private DatePicker dt_date_debut;
    @FXML
    private DatePicker dt_date_fin;
    @FXML
    private TextField d_dest;
    @FXML
    private TextArea d_desc;
    private TextArea i_img;
    @FXML
    private TextField n_nb;
    @FXML
    private TextField p_prix;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Info;
    
    private Utilisateur user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        VoyageService service = new VoyageService();
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            user = UtilisateurService.getUtilisateur(id);
        }catch(ExecutionException | SQLException ex){
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);            
        }
        if("a".equals(d_dest.getText())){
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le type doit etre inséré");
            alert.show();
        // TODO
        }  
        dt_date_fin.setDisable(true);
          dt_date_debut.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) 
                        {         
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
             }
            }
        });
          dt_date_debut.valueProperty().addListener(new ChangeListener(){
             @Override
            public void changed (ObservableValue observable, Object oldValue, Object newValue)
            {
              dt_date_fin.setDisable(false);
              dt_date_fin.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(dt_date_debut.getValue())) 
                        {         
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;");
             }
            }
        });
          
             }

                    });
           
    }    
    
   public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


    @FXML
    private void choisir(ActionEvent event) {
        try {
             Voyage v ;
            v=new Voyage(dt_date_debut.getValue().toString(),dt_date_fin.getValue().toString(),Float.parseFloat(p_prix.getText()),d_dest.getText(),d_desc.getText(),i_img.getText(),Integer.parseInt(n_nb.getText()),Integer.parseInt(n_nb.getText()),user.getId());
            AjoutDestinationFXMLController cont = new AjoutDestinationFXMLController(v);
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("ajoutDestinationFXML.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(n_nb.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void choix(ActionEvent event) {
        
        Pattern VALID_ENTIER_REGEX = Pattern.compile("", Pattern.CASE_INSENSITIVE);
if(p_prix.getText().equals("") || d_dest.getText().equals("") || d_desc.getText().equals("") || fichier.getName().equals("") || n_nb.getText().equals("")){
                infoBox("Tous les champs sont nécessaires", "Echec", null);
            }else if(!VALID_ENTIER_REGEX .matcher(p_prix.getText()).find()){
                infoBox("Veuiilez tapper un prix entier", "Echec", null);
            }else if(!VALID_ENTIER_REGEX .matcher(n_nb.getText()).find()){
                infoBox("Veuiilez tapper un nombre de places entier", "Echec", null);
            }else{
                try {
                 Voyage v =new Voyage(dt_date_debut.getValue().toString(),dt_date_fin.getValue().toString(),Float.parseFloat(p_prix.getText()),d_dest.getText(),d_desc.getText(),fichier.getName(),Integer.parseInt(n_nb.getText()),Integer.parseInt(n_nb.getText()),user.getId()); 
                 VoyageService.insererVoyage(v);
                 RemoteFileHandler.upload(fichier);
           Parent root = FXMLLoader.load(getClass().getResource("ListeVoyage.fxml"));
            Scene scene = new Scene(root);
            Stage x = (Stage) this.Ajouter.getScene().getWindow();
            x.setTitle("Ajout Voyage");
            x.setScene(scene);
            x.show();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
             
    }

    @FXML
    private void image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png","*.jpg")
        );
        fileChooser.setTitle("Choisir le fichier");
        File file = fileChooser.showOpenDialog(Ajouter.getScene().getWindow());
        if (file != null) {
                fichier = file;
        }

    }
    
}

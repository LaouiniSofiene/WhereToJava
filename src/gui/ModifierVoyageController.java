/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Voyage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.VoyageService;
import util.RemoteFileHandler;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class ModifierVoyageController implements Initializable {

    @FXML
    private TextField p_prix;
    @FXML
    private TextField d_dest;
    @FXML
    private TextField d_desc;
    private TextField i_img;
    @FXML
    private Button bouton;
    @FXML
    private DatePicker dt_date_debut;
    @FXML
    private DatePicker dt_date_fin;
    @FXML
    private TextField n_nb;
        private Voyage a;
    private File fichier;
    public ModifierVoyageController(Voyage a) {
        this.a = a;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
             
              dt_date_debut.setValue(LocalDate.parse(a.getDateDebut()));
              dt_date_fin.setValue(LocalDate.parse(a.getDateFin()));
               p_prix.setText(""+a.getPrix());
               d_dest.setText(a.getDestination());
              d_desc.setText(a.getDescription());
              n_nb.setText(""+a.getNbPlaces());
        // TODO
    }    

    @FXML
    private void Modifier_voyage(ActionEvent event) {
                
                a.setDateDebut(dt_date_debut.getValue().toString());
                a.setDateFin(dt_date_fin.getValue().toString());
                       a.setPrix(Float.parseFloat(p_prix.getText()));
                a.setDestination(d_dest.getText());
                a.setDescription(d_desc.getText());
                
                try{
                    a.setImage(fichier.getName());
                    RemoteFileHandler.upload(fichier);
                }catch(Exception e){
                    a.setImage(a.getImage());
                }
                a.setNbPlaces(Integer.parseInt(n_nb.getText()));
                


                VoyageService sv=new VoyageService();
                sv.updateVoyage(a);
            try {
            Parent root = FXMLLoader.load(getClass().getResource("listeVoyage.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Liste");
            s.setScene(scene);
            s.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ListeVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png","*.jpg")
        );
        fileChooser.setTitle("Choisir le fichier");
        File file = fileChooser.showOpenDialog(bouton.getScene().getWindow());
        if (file != null) {
                fichier = file;
        }

    }
    
}

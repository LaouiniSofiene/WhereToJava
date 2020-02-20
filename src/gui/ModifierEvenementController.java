/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierEvenementController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TextField nom;
    @FXML
    private TextField etab;
    @FXML
    private TextField disc;
    @FXML
    private TextField image;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    private Evenement a;  
    @FXML
    private ComboBox<String> comboTheme;
    File file;
    Stage stage;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField prix;
    @FXML
    private Button modif_event;
    
    public ModifierEvenementController() {
        this.a = new Evenement();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
           a = ListeEvenementController.selectedEvent;
           System.out.println(a);
              nom.setText(a.getNom());
              etab.setText(a.getRefEtab());
              debut.setValue(LocalDate.parse(a.getDateDebut()));
              fin.setValue(LocalDate.parse(a.getDateFin()));
              disc.setText(a.getDescription());
              image.setText(a.getIimage());
              comboTheme.getItems().addAll("Musique","Culture","Conference","Enfance","Autres");
              comboTheme.setValue(a.getTheme());
              
              
       

    }    

    private void ModifierEvenement(ActionEvent event) {
                a.setNom(nom.getText());
                a.setRefEtab(etab.getText());
                a.setDateDebut(debut.getValue().toString());
                a.setDateFin(fin.getValue().toString());
                a.setDescription(disc.getText());
                a.setImage(image.getText());
                a.setTheme(comboTheme.getValue());
                a.setNbr_place(Integer.parseInt(nbr_place.getText()));
                a.setPrix(Integer.parseInt(prix.getText()));
                 
                 if(disc.getText().isEmpty() | nom.getText().isEmpty() | etab.getText().isEmpty()|debut.getValue()==null |fin.getValue()==null| nbr_place.getText().isEmpty()|prix.getText().isEmpty())
            {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Verify Your TextFields");
            alert.showAndWait();
            modif_event.setDisable(true);
            
             }
	    else
            {

            modif_event.setDisable(false);
                EvenementService sv=new EvenementService();
                sv.updateEvenement(a);
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
            
            Notifications notificationBuilder = Notifications.create()
                
                .title("WhereTo")
                .text("votre evenement a été modifier")
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

    @FXML
    private void ajoutPhoto2(ActionEvent event) {
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
                Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
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

   
    
    
 

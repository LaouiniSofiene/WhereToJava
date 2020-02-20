/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import entites.Enseigne;
import entites.SignalEnseigne;
import service.CRUD_Enseigne;
import service.CRUD_Signal;
import service.CRUD_Note;
import entites.NoteEnseigne;
import com.jfoenix.controls.JFXButton;
import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Mohamed El Hammi
 */
public class EnseigneInterfaceController implements Initializable {
    public static Enseigne en=null;
    @FXML
    private ImageView photo1;
    @FXML
    private ImageView photo2;
    @FXML
    private ImageView photo3;
    @FXML
    private Label nomEnseigne;
    @FXML
    private Label descriptionEnseigne;
    @FXML
    private Label adresseEnseigne;
    @FXML
    private Label specialiteEnseigne;
    @FXML
    private Label horaireEnseigne;
    @FXML
    private Label numTelEnseigne;
    @FXML
    private Label noteEnseigne;
    
    private Enseigne e1 = new Enseigne();
    @FXML
    private Button btSignal;
    @FXML
    private ComboBox<Number> noteField;
        ObservableList<Number> myData = FXCollections
            .observableArrayList(1, 2, 3, 4, 5);
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
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    public void setNom(Enseigne e){
        nomEnseigne.setText(e.getNom());        
    }
    
    public void setDescription(Enseigne e){
        descriptionEnseigne.setText(e.getDescription());        
    }
    public void setAdresse(Enseigne e){
        adresseEnseigne.setText(e.getAdresse());        
    }
    public void setSpecialite(Enseigne e){
        specialiteEnseigne.setText(e.getSpecialite());        
    }
    public void setHoraire(Enseigne e){
        horaireEnseigne.setText(e.getHoraire());        
    }
    public void setNumTel(Enseigne e){
        numTelEnseigne.setText(e.getNumTel());        
    }
    public void setPhoto1(Enseigne e) {
                Image img = new Image(e.getPhoto1());                
                photo1.setImage(img);
                photo1.setFitWidth(200);
                photo1.setFitHeight(150);       
    }
    public void setPhoto2(Enseigne e) {
                Image img = new Image(e.getPhoto2());                
                photo2.setImage(img);
                photo2.setFitWidth(200);
                photo2.setFitHeight(150);
    }
    public void setPhoto3(Enseigne e) {
                Image img = new Image(e.getPhoto3());                
                photo3.setImage(img);
                photo3.setFitWidth(200);
                photo3.setFitHeight(150);
    }
    
    
    public void setSignal(Enseigne e) throws SQLException{
            e1 = e;
            CRUD_Note crN = new CRUD_Note();
            float noteGlobale = crN.displayNoteMoy(e1.getIdEnseigne());
            String stNote = String.valueOf(noteGlobale);
            noteEnseigne.setText(stNote);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            // System.out.println(en);
            //System.out.println(e1.getIdEnseigne());
            noteField.setItems(myData);
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();            
            user = UtilisateurService.getUtilisateur(id);
        } catch (ExecutionException | SQLException ex) {
            Logger.getLogger(EnseigneInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
        @FXML
    private void afficheRestaurant(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    } 
    
    @FXML
    private void afficheBar(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Bar.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private void afficheCinema(ActionEvent event) throws IOException{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    } 


    @FXML
    private void signalerEnseigne(ActionEvent event) throws SQLException {
                        System.out.println(e1.getIdEnseigne()); 
        CRUD_Signal crS = new CRUD_Signal();
        
        Alert alert = new Alert(AlertType.INFORMATION);

                 int nbrSignalActuel = crS.displayNbr2(user.getId(),e1.getIdEnseigne());

                 if(nbrSignalActuel == 1) {
                     
                     alert.setTitle("Alerte !");
                     alert.setHeaderText("Look, an Information Dialog");
                     alert.setContentText("Vous avez deja signalé cette Enseigne !!");
                     alert.showAndWait();
                      return ;
                    }       
                         nbrSignalActuel = nbrSignalActuel + 1;
                                          crS.insertSt(e1.getIdEnseigne(), user.getId(), nbrSignalActuel);
                                            System.out.println(nbrSignalActuel);

        CRUD_Enseigne crE = new CRUD_Enseigne();
                System.out.println(e1.getIdEnseigne()); 
        crE.addSignal(e1.getIdEnseigne());
        

    }

    @FXML
    private void noterEnseigne(ActionEvent event) throws SQLException {
        
        CRUD_Enseigne crE = new CRUD_Enseigne();
        //System.out.println(noteField.getValue());
        int noteNbr = (int)noteField.getValue();
        CRUD_Note crN = new CRUD_Note();
        int a = crN.displayNote(user.getId(), e1.getIdEnseigne()) ;
        System.out.println(a);
        if ( a != 0) {
            crN.updateNote(user.getId(), e1.getIdEnseigne(), noteNbr);
                        crE.updateNoteEnseigne(e1.getIdEnseigne(), crN.displayNoteMoy(e1.getIdEnseigne()));
                                                    setSignal(e1);


        }
        else {
            crN.insertSt(e1.getIdEnseigne(),user.getId() , noteNbr);
                        crE.updateNoteEnseigne(e1.getIdEnseigne(), crN.displayNoteMoy(e1.getIdEnseigne()));
                            setSignal(e1);
        }
    }

    @FXML
    private void handleBtnAccueil(ActionEvent event) {
    }

    @FXML
    private void handleBtnProfil(ActionEvent event) {
//        Node source = (Node) event.getSource();
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

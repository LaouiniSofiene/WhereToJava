/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.base.Objects;
import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Reservationenseigne;
import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.EvenementService;
import service.ReservationService;
import service.UtilisateurService;
import service.VoyageService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ModifierReservationController implements Initializable {

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
    private JFXButton ajout;
    @FXML
    private JFXTextField nbplace;
    @FXML
    private Label nomUser;
    @FXML
    private Label nomEvent;
    @FXML
    private Label date;
    @FXML
    private Label type;
    
     private Reservationenseigne r;
     private Utilisateur user;
    
    public ModifierReservationController(Reservationenseigne r){
        this.r = r;
 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            user = UtilisateurService.getUtilisateur(id);
            nomUser.setText(user.getPrenom() + " " + user.getNom());
            nomEvent.setText(String.valueOf(r.getIdBonplan()));
            date.setText(df.format(r.getDateReservation()));
            type.setText(r.getType());
        } catch (ExecutionException | SQLException ex) {
            Logger.getLogger(ModifierReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void modifierRes(ActionEvent event){
        
        int prix =0;
        r.setNbPlace(Integer.parseInt(nbplace.getText()));
        if(Objects.equal(r.getType(), "Evénement")){
            prix = EvenementService.selectEvenementByRef(r.getIdBonplan()).getPrix();            
        }else if(Objects.equal(r.getType(), "Voyage")){
            prix = Math.round(VoyageService.selectVoyageParID(r.getIdBonplan()).getPrix());
        }
        r.setPrixRes(prix*Integer.parseInt(nbplace.getText()));
                
                

                
                ReservationService sv=new ReservationService();
                sv.updateReservation(r);
            try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeResrvation.fxml"));
            Scene scene = new Scene(root);
            Stage s = new Stage();
            s.setTitle("Liste");
            s.setScene(scene);
            s.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ListeReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Util;
import entites.Utilisateur;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeString.search;
import org.controlsfx.control.textfield.TextFields;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ProfilController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Utilisateur user = new Utilisateur();
    Utilisateur userRecherche = new Utilisateur();

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
    private JFXButton modifier;
    @FXML
    private JFXButton sauvgarder;
    @FXML
    private AnchorPane pageProfil;
    @FXML
    private AnchorPane pageModif;
    
    private File file;
    @FXML
    private Label nom;
    @FXML
    private Label ville;
    @FXML
    private Label telephone;
    @FXML
    private JFXTextField prenomModif;
    @FXML
    private JFXTextField nomModif;
    @FXML
    private JFXTextArea bioModif;
    @FXML
    private JFXComboBox<String> villeModif;
    ObservableList<String> villes = FXCollections.observableArrayList("Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","La Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");

    @FXML
    private JFXTextField telephoneModif;
    @FXML
    private JFXButton phptoModif;
    @FXML
    private ImageView photoModifie;
    @FXML
    private ImageView photoProfil;
    @FXML
    private Label bio;
    @FXML
    private Label nbAbo;
    @FXML
    private JFXTextField search;
    @FXML
    private Label nbAmi;
    @FXML
    private JFXButton nbAmiBtn;
    @FXML
    private JFXButton deco;
    @FXML
    private JFXButton gererVoy;
    @FXML
    private JFXButton gererEns;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        pageModif.setVisible(false);        
        TextFields.bindAutoCompletion(search, UtilisateurService.selectNoms());
        
        gererVoy.setVisible(false);
        gererEns.setVisible(false);
        
        try {
            try{
                LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
                int id = empCache.get(1).getId();

                user = UtilisateurService.getUtilisateur(id);
            } catch (ExecutionException e) {
		e.printStackTrace();
            }
            nbAbo.setText(user.getNbAbo()+"");
            nbAmiBtn.setText(user.getNbAmi()+"");
            nbAmi.setText(user.getNbAmi()+"");
            nom.setText(user.getPrenom() + " " + user.getNom());
            ville.setText(user.getVille());
            telephone.setText(user.getTelephone());
            if(user.getBio()==""){
                bio.setText("Ajoutez une bio");
            }else{
                bio.setText(user.getBio());
            }
        
            if(Objects.equals(user.getRole(),"Propriétaire")){
                gererEns.setVisible(true);
            }

            if(Objects.equals(user.getRole(),"Agence de voyage")){
                gererVoy.setVisible(true);
            }

            try {
                file = new File(user.getPhoto());
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                photoProfil.setImage(image);                
                photoModifie.setImage(image);
                this.round(photoProfil);
                this.round(photoModifie);
                
            } catch (IOException ex) {

            }
            
            

            prenomModif.setText(user.getPrenom());
            nomModif.setText(user.getNom());
            villeModif.setValue(user.getVille());
            villeModif.setItems(villes);
            telephoneModif.setText(user.getTelephone());
            bioModif.setText(user.getBio());
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    private void handleBtnModifier(ActionEvent event) {
        pageProfil.setVisible(false);
        pageModif.setVisible(true);
    }

    @FXML
    private void handleBtnSauvegarder(ActionEvent event) throws IOException {
        try{
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            Utilisateur u1 = new Utilisateur(id,nomModif.getText(),prenomModif.getText(),file.getPath(),telephoneModif.getText(),villeModif.getValue(),bioModif.getText());
            UtilisateurService.updateProfile(u1);
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
            dialogStage.setTitle("WhereTo - Votre Profil");
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (ExecutionException e) {
		e.printStackTrace();
	}
    }

    @FXML
    private void handleBtnPhotoModif(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              
            //Show open file dialog
            file = fileChooser.showOpenDialog(null);
                       
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photoModifie.setImage(image);
                this.round(photoModifie);
            } catch (IOException ex) {
                
            }
    }

    @FXML
    private void handleRecherche(ActionEvent event) throws IOException {
        try{
            String[] splitNoms = search.getText().split(" ");
            userRecherche = UtilisateurService.getUtilisateurByNom(splitNoms[0], splitNoms[1]);
            if(user.getId() != userRecherche.getId()){
                Node source = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AutreProfile.fxml"));
                Parent root = (Parent) loader.load();
                AutreProfileController ctrl = loader.getController();
                ctrl.setUser(userRecherche);
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.hide();
                scene = new Scene(root);
                dialogStage.setTitle("WhereTo - " + search.getText());
                dialogStage.setScene(scene);
                dialogStage.show();
            }else{
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
                dialogStage.setTitle("WhereTo - Votre Profil");
                dialogStage.setScene(scene);
                dialogStage.show();
            }
            

            

        }catch(SQLException e){

        }
    }

    @FXML
    private void handleNbAmiBtn(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("ListeAmis.fxml")));
        dialogStage.setTitle("WhereTo - Vos Amis");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
    private void round(ImageView img){
        Rectangle clip = new Rectangle(
            img.getFitWidth(), img.getFitHeight()
        );
        clip.setArcWidth(133);
        clip.setArcHeight(133);
        img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img.setClip(null);

        // apply a shadow effect.
        img.setEffect(new DropShadow(20, Color.BLACK));

        img.setImage(image);
    }

    @FXML
    private void handleBtnDeco(ActionEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleGererEvent(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("FrontEvenement.fxml")));
        dialogStage.setTitle("WhereTo - Vos Evénements");
        dialogStage.setScene(scene);
        dialogStage.show();        
    }

    @FXML
    private void handleGererVoy(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("ListeVoyage.fxml")));
        dialogStage.setTitle("WhereTo - Vos Voyages");
        dialogStage.setScene(scene);
        dialogStage.show();    
    }

    @FXML
    private void handleGererEns(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("FormulaireEnseigne.fxml")));
        dialogStage.setTitle("WhereTo - Vos Enseignes");
        dialogStage.setScene(scene);
        dialogStage.show();    
    }

    @FXML
    private void handleGererReservation(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("ListeResrvation.fxml")));
        dialogStage.setTitle("WhereTo - Vos Réservations");
        dialogStage.setScene(scene);
        dialogStage.show();    
    }
    
}

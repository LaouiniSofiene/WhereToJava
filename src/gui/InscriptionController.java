/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Utilisateur;
import static gui.LoginController.infoBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class InscriptionController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    @FXML
    private ComboBox<String> role;
    ObservableList<String> types = FXCollections.observableArrayList("Utilisateur","Propriétaire","Agence de voyage");
    @FXML
    private TextField email;
    @FXML
    private TextField motdp;
    @FXML
    private TextField motdp2;
    @FXML
    private Button btnSuivant;
    @FXML
    private GridPane etape1;
    @FXML
    private GridPane etape2;
    @FXML
    private GridPane etape3;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField telephone;
    @FXML
    private ComboBox<String> ville;
    ObservableList<String> villes = FXCollections.observableArrayList("Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","La Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");

    private int etape = 1;
    @FXML
    private Button btnPrecedent;
    @FXML
    private ImageView photo;
    @FXML
    private Button btnPhoto;
    
    private File file;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.setItems(types);
        ville.setItems(villes);
        etape2.setVisible(false);
        etape3.setVisible(false);
        btnPrecedent.setDisable(true);
        
    } 
    
    @FXML
    private void handleBtnSuivant(ActionEvent event) throws IOException {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("\\d{8}", Pattern.CASE_INSENSITIVE);
        if(etape == 3){
            if(!file.exists()){
                infoBox("Vous devez choisir une photo de profil", "Echec", null);
            }
            else{   
                String passwordToHash = motdp.getText();
                String generatedPassword = null;
                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(passwordToHash.getBytes());
                    //Get the hash's bytes
                    byte[] bytes = md.digest();
                    //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    //Get complete hashed password in hex format
                    generatedPassword = sb.toString();
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }
                
                Utilisateur u1 = new Utilisateur(role.getValue(),email.getText(),generatedPassword,nom.getText(),prenom.getText(),file.getPath(),telephone.getText(),ville.getValue(),"");
                UtilisateurService.insererUtilisateur(u1);
                infoBox("Votre compte à été créer", "Succés", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                dialogStage.setTitle("WhereTo - Login");
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        if(etape == 2){
            if(ville.getValue().equals("Votre ville") || nom.getText().equals("") || prenom.getText().equals("") || telephone.getText().equals("")){
                infoBox("Tous les champs sont nécessaires", "Echec", null);
            }else if(!VALID_PHONE_NUMBER_REGEX .matcher(telephone.getText()).find()){
                infoBox("Veuiilez tapper un numéro de téléphone valide", "Echec", null);
            }else{
                etape2.setVisible(false);
                etape3.setVisible(true);  
                etape = 3;
            }            
        }   
        if(etape == 1){
            if(role.getValue().equals("Type") || email.getText().equals("") || motdp.getText().equals("") || motdp2.getText().equals("")){
                infoBox("Tous les champs sont nécessaires", "Echec", null);
            }
            else if(!motdp.getText().equals(motdp2.getText())){
                infoBox("La confirmation du mot de passe ne correspond pas", "Echec", null);
            }else if(!VALID_EMAIL_ADDRESS_REGEX .matcher(email.getText()).find()){
                infoBox("Veuiilez tapper une adresse mail valide", "Echec", null);
            }else{
                etape1.setVisible(false);
                etape2.setVisible(true);
                btnPrecedent.setDisable(false);
                etape = 2;
            }            
        }
                            
                    
        
    }
    
    @FXML
    private void handleBtnPrecedent(ActionEvent event) {
        if(etape == 2){
            etape2.setVisible(false);
            etape1.setVisible(true); 
            btnPrecedent.setDisable(true); 
            etape = 1;
        } 
        if(etape == 3){
            etape3.setVisible(false);
            etape2.setVisible(true);  
            etape = 2;
        } 
    }

    @FXML
    private void handleBtnAnnul(ActionEvent event) throws IOException {
        
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo - Login");
        dialogStage.setScene(scene);
        dialogStage.show();     
    }

    @FXML
    private void handleBtnPhoto(ActionEvent event) {
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
                photo.setImage(image);
                this.round(photo);
            } catch (IOException ex) {
                
            }
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
    
    
    
}

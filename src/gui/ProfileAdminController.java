/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Utilisateur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ProfileAdminController implements Initializable {
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Utilisateur u = new Utilisateur();
    
    @FXML
    private AnchorPane plane;
    @FXML
    private ImageView photo;
    @FXML
    private Label nom;
    @FXML
    private Label role;
    @FXML
    private Label email;
    @FXML
    private Label ville;
    @FXML
    private Label telephone;
    @FXML
    private AnchorPane topbar;
    @FXML
    private ImageView Voyage;
    @FXML
    private ImageView enseignes;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView event;
    @FXML
    private ImageView user;
    private File file;
    @FXML
    private Button block;
    @FXML
    private Button deblock;

    /**
     * Initializes the controller class.
     */
    
    public void setUser(Utilisateur u){
        this.u = u;
        try {
                file = new File(u.getPhoto());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);
                this.round(photo);
            } catch (IOException ex) {
                
            }
        
        nom.setText(u.getPrenom() + " " + u.getNom());
        role.setText(u.getRole());
        email.setText(u.getEmail());
        ville.setText(u.getVille());
        telephone.setText(u.getTelephone());
        if(u.getBloque()){
            block.setVisible(false);
            deblock.setVisible(true);
            nom.setTextFill(Color.web("#ee0000"));
        }else{
            block.setVisible(true);
            deblock.setVisible(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void handleVoyage(MouseEvent event) {
    }

    @FXML
    private void handleBtnRetour(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("UtilisateurAdmin.fxml")));
        dialogStage.setTitle("WhereTo - Gestion Utilisateur");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    @FXML
   private void handleBtnDeco(MouseEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setTitle("WhereTo");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnBlock(ActionEvent event) throws IOException, SQLException {
        UtilisateurService.bloquer(u);
        u = UtilisateurService.getUtilisateur(u.getId());
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileAdmin.fxml"));
        Parent root = (Parent) loader.load();
        ProfileAdminController ctrl = loader.getController();
        ctrl.setUser(u);
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - " + u.getPrenom() + " " + u.getNom());
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void handleBtnDeblock(ActionEvent event) throws IOException, SQLException {
        UtilisateurService.debloquer(u);
        u = UtilisateurService.getUtilisateur(u.getId());
        Node source = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileAdmin.fxml"));
        Parent root = (Parent) loader.load();
        ProfileAdminController ctrl = loader.getController();
        ctrl.setUser(u);
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.hide();
        scene = new Scene(root);
        dialogStage.setTitle("WhereTo - " + u.getPrenom() + " " + u.getNom());
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
}

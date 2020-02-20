/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entites.Utilisateur;
import static gui.ListeEvenementController.selectedEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.textfield.TextFields;
import service.AmitieService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class ListeAmisController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;
    
    Utilisateur user = new Utilisateur();
    Utilisateur userRecherche = new Utilisateur();
    Utilisateur selectedUser = new Utilisateur();
    
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
    private JFXListView<Utilisateur> listeVue;
    @FXML
    private AnchorPane pageProfil;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton deco;
    @FXML
    private JFXButton delAmi;

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
    private void handleBtnRetour(ActionEvent event)  throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Profil.fxml")));
        dialogStage.setTitle("WhereTo - Votre Profil");
        dialogStage.setScene(scene);
        dialogStage.show();
        
    }

    @FXML
    private void handleBtnDelAmi(ActionEvent event) throws SQLException, IOException {
        if(listeVue.getSelectionModel().getSelectedItem() != null){
            AmitieService.delAmi(AmitieService.isAmi(user,listeVue.getSelectionModel().getSelectedItem()));
            UtilisateurService.delAmi(user);
            UtilisateurService.delAmi(listeVue.getSelectionModel().getSelectedItem());

            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("ListeAmis.fxml")));
            dialogStage.setTitle("WhereTo - Vos Amis");
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        
    }
    
    public class cellBase extends ListCell<Utilisateur> {

    protected final Pane pane;
    protected final Pane pane0;
    protected final Label nom;
    protected final Label label;
    protected final Label nbAmis;
    protected final Line line;
    protected final Label label0;
    protected final Label nbAbos;
    protected final ImageView photo;
    private File file;

    public cellBase() {
        super();
        pane = new Pane();
        pane0 = new Pane();
        nom = new Label();
        label = new Label();
        nbAmis = new Label();
        line = new Line();
        label0 = new Label();
        nbAbos = new Label();
        photo = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(90.0);
        setPrefWidth(630.0);
        setStyle("-fx-background-color: #E9B000;");

        pane.setPrefHeight(90.0);
        pane.setPrefWidth(630.0);

        pane0.setPrefHeight(45.0);
        pane0.setPrefWidth(630.0);
        pane0.setStyle("-fx-background-color: #008F95;");

        nom.setLayoutX(123.0);
        nom.setLayoutY(7.0);
        nom.setText("");
        nom.setTextFill(javafx.scene.paint.Color.WHITE);
        nom.setFont(new Font(24.0));

        label.setLayoutX(389.0);
        label.setLayoutY(8.0);
        label.setText("Amis:");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#9e9e9e"));
        label.setFont(new Font(18.0));

        nbAmis.setLayoutX(440.0);
        nbAmis.setLayoutY(9.0);
        nbAmis.setText("");
        nbAmis.setFont(new Font(18.0));

        line.setLayoutX(466.0);
        line.setLayoutY(10.0);
        line.setStartY(25.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#9e9e9e"));

        label0.setLayoutX(484.0);
        label0.setLayoutY(9.0);
        label0.setText("Abonnements:");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#9e9e9e"));
        label0.setFont(new Font(18.0));

        nbAbos.setLayoutX(606.0);
        nbAbos.setLayoutY(9.0);
        nbAbos.setText("");
        nbAbos.setFont(new Font(18.0));

        photo.setFitHeight(66);
        photo.setFitWidth(66);
        photo.setLayoutX(30.0);
        photo.setLayoutY(12.0);
        photo.setPickOnBounds(true);
        photo.setPreserveRatio(true);

        pane0.getChildren().add(nom);
        pane0.getChildren().add(label);
        pane0.getChildren().add(nbAmis);
        pane0.getChildren().add(line);
        pane0.getChildren().add(label0);
        pane0.getChildren().add(nbAbos);
        pane0.getChildren().add(photo);
        pane.getChildren().add(pane0);
        getChildren().add(pane);

    }
    
    protected void updateItem(Utilisateur item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null) {
            nom.setText(item.getPrenom() + " " + item.getNom());
            nbAmis.setText(""+item.getNbAmi());
            nbAbos.setText(""+item.getNbAbo());
            try {
                file = new File(item.getPhoto());
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);  
                //this.round(photo);
            } catch (IOException ex) {
            }

            setGraphic(pane);
        }
    }
    
    private void round(ImageView img){
        Rectangle clip = new Rectangle(
            img.getFitWidth(), img.getFitHeight()
        );
        clip.setArcWidth(66);
        clip.setArcHeight(66);
        img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img.setClip(null);

        // apply a shadow effect.
        img.setEffect(new DropShadow(10, Color.BLACK));

        img.setImage(image);
    }
    
}

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            UtilisateurService us = new UtilisateurService();
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            user = UtilisateurService.getUtilisateur(id);
            ObservableList<Utilisateur> listeUtilisateur = FXCollections.observableArrayList(us.selectAmis(user.getId()));
            listeVue.setItems(listeUtilisateur);
            listeVue.setCellFactory((param) -> new cellBase());
            
            listeVue.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if(listeVue.getSelectionModel().getSelectedItem() != null) 
                    {    
                       selectedUser=listeVue.getSelectionModel().getSelectedItem();
                       System.out.println("Selected Value" + listeVue.getSelectionModel().getSelectedItem());
                       delAmi.setDisable(false);
                    }
                }
            });
            
            if(listeVue.getSelectionModel().getSelectedItem() == null){
                delAmi.setDisable(true);
            }
        } catch (ExecutionException ex) {
            Logger.getLogger(ListeAmisController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListeAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TextFields.bindAutoCompletion(search, UtilisateurService.selectNoms());
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
    
    
    
}

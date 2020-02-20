/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import entites.Enseigne;
import service.CRUD_Enseigne;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import entites.Enseigne;
import service.CRUD_Enseigne;
import service.CRUD_Note;
import com.jfoenix.controls.JFXButton;
import entites.Utilisateur;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.UtilisateurService;
/**
 * FXML Controller class
 *
 * @author Mohamed El Hammi
 */
public class FormulaireEnseigneController implements Initializable {

    private int idEnseigne,idUtilisateur;
    
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfSpecialite;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfNumTel;
    @FXML
    private TextArea taDescription;
    @FXML
    private ComboBox<String> typeEnseigne;
    
    ObservableList<String> myData = FXCollections
            .observableArrayList("Restaurant", "Bar", "Cafe");
    @FXML
    private Button btAjouter;
    @FXML
    private TableView<Enseigne> tbInformation;
    @FXML
    private TableColumn<Enseigne, String> colType;
    @FXML
    private TableColumn<Enseigne, String> colNom;
    @FXML
    private TableColumn<Enseigne, String> colNote;
    @FXML
    private TableColumn<Enseigne, String> colSignalEnseigne;
    @FXML
    private TextField photo1URL;
    @FXML
    private TextField photo2URL;
    @FXML
    private TextField photo3URL;
    
    @FXML
    private TableColumn<Object, ImageView> colImage;
    
   Stage stage;
   File file;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            int id = empCache.get(1).getId();
            
            user = UtilisateurService.getUtilisateur(id);
            typeEnseigne.setValue("Select Type");
            typeEnseigne.setItems(myData);
            
            assert tbInformation != null : "fx:id=\"tbInformation\" was not injected: check your FXML file 'FormulaireEnseigne.fxml'.";
            colType.setCellValueFactory(
                    new PropertyValueFactory<Enseigne,String>("type"));
            colNom.setCellValueFactory(
                    new PropertyValueFactory<Enseigne,String>("nom"));
            colNote.setCellValueFactory(  
                    new PropertyValueFactory<Enseigne,String>("note"));
            colSignalEnseigne.setCellValueFactory(
                    new PropertyValueFactory<Enseigne,String>("signalEnseigne"));
            colImage.setCellValueFactory(
                    new PropertyValueFactory<Object,ImageView>("image"));
            
            
            photo1URL.setEditable(false);
            photo1URL.setDisable(true);
            
            photo2URL.setEditable(false);
            photo2URL.setDisable(true);
            
            photo3URL.setEditable(false);
            photo3URL.setDisable(true);
            
            
            buildData();
        } catch (ExecutionException ex) {
            Logger.getLogger(FormulaireEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormulaireEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Enseigne> data;
    
    public void buildData(){        
    data = FXCollections.observableArrayList();
    try{      
        CRUD_Enseigne crE = new CRUD_Enseigne();
        CRUD_Note crN =  new CRUD_Note();
        
        List<Enseigne> lEnseigne = crE.displayAll(user.getId());
        
        for(Enseigne e: lEnseigne) {
            Image img = new Image(e.getPhoto1());
            ImageView mv = new ImageView();
            mv.setImage(img);
            mv.setFitWidth(70);
            mv.setFitHeight(80);
            e.setImage(mv);
            
            e.setNote(crN.displayNoteMoy(e.getIdEnseigne()));
            e.setSignalEnseigne(crE.findSignal(e.getIdEnseigne()));
        }
        

        
        data.addAll(lEnseigne);
        tbInformation.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
}



    @FXML
    private void clickAjEns(ActionEvent event) throws SQLException{
        String ensNom = null;
        String ensSpecialite = null;
        String ensAdresse = null;
        String ensNumTel = null;
        String ensDescription = null;
        String ensType = null;
        String ensPhoto1 = null;
        String ensPhoto2 = null;
        String ensPhoto3 = null;
        
        
         ensNom = tfNom.getText();
         ensSpecialite = tfSpecialite.getText();
         ensAdresse = tfAdresse.getText();
         ensNumTel = tfNumTel.getText();
         ensDescription = taDescription.getText();
         ensType = typeEnseigne.getValue();
         ensPhoto1 = photo1URL.getText();
         ensPhoto2 = photo2URL.getText();
         ensPhoto3 = photo3URL.getText();

        Alert alert = new Alert(AlertType.INFORMATION);

                
                System.out.println(ensType.length());
        if(ensNom.length() == 0 || ensSpecialite.length() == 0 || ensAdresse.length() == 0 || ensNumTel.length() == 0 || ensDescription.length() == 0 || ensType.length() == 11 || ensPhoto1.length() == 0 || ensPhoto2.length() == 0 || ensPhoto3.length() == 0) {

                     
                                                      alert.setTitle("Alerte !");
                        alert.setHeaderText("Look, an Information Dialog");
                     alert.setContentText("Vous avez laissez des champs vide !!");
                     alert.showAndWait();
    }
        else if(ensNumTel.matches("^[a-zA-Z]*$") ){
            System.out.println("eeee");
            
                                                                  alert.setTitle("Alerte !");
                     alert.setHeaderText("Look, an Information Dialog");
                     alert.setContentText("Le numero de telephone doit etre un entier !!");
                     alert.showAndWait();
        }
        else{
                               Enseigne e = new Enseigne(user.getId(),ensType,ensNom,ensDescription,ensAdresse,ensSpecialite,ensNumTel,ensPhoto1,ensPhoto2,ensPhoto3);
                    CRUD_Enseigne crE = new CRUD_Enseigne();
        
                     crE.insertSt(e); 

        }

        
        /*Enseigne e1 = crE.findByName(ensNom);  //faza
        Photo p1 = new Photo(e1.getIdEnseigne(),ensPhoto1);
        Photo p2 = new Photo(e1.getIdEnseigne(),ensPhoto2);
        Photo p3 = new Photo(e1.getIdEnseigne(),ensPhoto3);

        CRUD_Photo crP = new CRUD_Photo();
        crP.insertSt(p1);
        crP.insertSt(p2);
        crP.insertSt(p3);*/
        
        
        
        
        
        buildData();
    }

    @FXML
    private void suppEnseigne(ActionEvent event) throws SQLException {
        int id = 0;
        CRUD_Enseigne crE = new CRUD_Enseigne();

            Enseigne e = (Enseigne)tbInformation.getSelectionModel().getSelectedItem();
            System.out.println(e.getIdEnseigne());
            id = e.getIdEnseigne();
            crE.delete(id);
            buildData();
    }
    
    @FXML
    public void updateEnseigne(ActionEvent event) throws SQLException {
        Enseigne e = (Enseigne)tbInformation.getSelectionModel().getSelectedItem();
        CRUD_Enseigne crE = new CRUD_Enseigne();
        
        Enseigne e1 = crE.findByName(e.getNom(),user.getId());
        
        tfNom.setText(e1.getNom());
        tfSpecialite.setText(e1.getSpecialite());
        tfAdresse.setText(e1.getAdresse());
        tfNumTel.setText(e1.getNumTel());
        taDescription.setText(e1.getDescription());
        typeEnseigne.setValue(e1.getType());
        idEnseigne = e1.getIdEnseigne();
        photo1URL.setText(e1.getPhoto1());
        photo2URL.setText(e1.getPhoto2());
        photo3URL.setText(e1.getPhoto3());

        
        btAjouter.setDisable(true);
        
    }



    @FXML
    private void EnseigneModif(ActionEvent event) throws SQLException {
        String ensNom = tfNom.getText();
        String ensSpecialite = tfSpecialite.getText();
        String ensAdresse = tfAdresse.getText();
        String ensNumTel = tfNumTel.getText();
        String ensDescription = taDescription.getText();
        String ensType = typeEnseigne.getValue();
        
        String ensPhoto1 = photo1URL.getText();
        String ensPhoto2 = photo2URL.getText();
        String ensPhoto3 = photo3URL.getText();

        Enseigne e = new Enseigne(user.getId(),ensType,ensNom,ensDescription,ensAdresse,ensSpecialite,ensNumTel,ensPhoto1,ensPhoto2,ensPhoto3);
        System.out.println(user.getId());
        CRUD_Enseigne crE = new CRUD_Enseigne();
        
        crE.updateEnseigne(e,idEnseigne,user.getId());
        
        btAjouter.setDisable(false);

        
        buildData();
    }
    
    
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
    private void photo1test(ActionEvent event) {
                        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        
        file = fileChooser.showOpenDialog(stage);
        
        if(file != null) {
            try {
                String img2 = file.toURI().toURL().toString();
                System.out.println(img2);
                System.out.println("jawek behi");
                photo1URL.setText(img2);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FormulaireEnseigneController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    @FXML
    private void photo2test(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        
        file = fileChooser.showOpenDialog(stage);
        
        if(file != null) {
            try {
                String img2 = file.toURI().toURL().toString();
                System.out.println(img2);
                System.out.println("jawek behi");
                photo2URL.setText(img2);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FormulaireEnseigneController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    
        @FXML
    private void photo3test(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        
        file = fileChooser.showOpenDialog(stage);
        
        if(file != null) {
            try {
                String img2 = file.toURI().toURL().toString();
                System.out.println(img2);
                System.out.println("jawek behi");
                photo3URL.setText(img2);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FormulaireEnseigneController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    void setStage(Stage primaryStage) {
        this.stage = primaryStage;
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

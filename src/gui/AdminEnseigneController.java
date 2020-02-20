/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import entites.Enseigne;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CRUD_Enseigne;
import service.CRUD_Note;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class AdminEnseigneController implements Initializable {

    @FXML
    private AnchorPane plane;
    @FXML
    private TableView<Enseigne> tbInformation;
    @FXML
    private TableColumn<Enseigne, String> colUtilisateur;
    @FXML
    private TableColumn<Enseigne, String> colNom;
    @FXML
    private TableColumn<Enseigne, String> colType;
    @FXML
    private TableColumn<Enseigne, String> colNote;
    @FXML
    private TableColumn<Enseigne, String> colSignalEnseigne;
    @FXML
    private Button btSupp;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        assert tbInformation != null : "fx:id=\"tbInformation\" was not injected: check your FXML file 'FormulaireEnseigne.fxml'.";
     
        
        colUtilisateur.setCellValueFactory(                
        new PropertyValueFactory<Enseigne,String>("nomUtilisateur"));
        
        colNom.setCellValueFactory(                
        new PropertyValueFactory<Enseigne,String>("nom"));
        
        colType.setCellValueFactory(     
        new PropertyValueFactory<Enseigne,String>("type"));        
    
    colNote.setCellValueFactory(
        new PropertyValueFactory<Enseigne,String>("note"));  
    
        colSignalEnseigne.setCellValueFactory(
        new PropertyValueFactory<Enseigne,String>("signalEnseigne"));  
        // TODO
        
        buildData();
        btSupp.setDisable(true);
        
        
        /*************************************************/
        
        CRUD_Enseigne crE = new CRUD_Enseigne();
                tbInformation.setRowFactory(tv -> {
            TableRow<Enseigne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    //String rowData = row.getItem();
                    //System.out.println(rowData);
                    Enseigne EnseigneSelected = tbInformation.getSelectionModel().getSelectedItem();
                    try {
                        if(crE.findSignal(EnseigneSelected.getIdEnseigne()) >= 5 ) {
                            btSupp.setDisable(false);
                        }
                        else {
                            btSupp.setDisable(true);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            });
            return row;

        });
    }    
    
    private ObservableList<Enseigne> data;
    
    public void buildData(){        
    data = FXCollections.observableArrayList();
    try{      
        CRUD_Enseigne crE = new CRUD_Enseigne();
        CRUD_Note crN = new CRUD_Note();
        
        List<Enseigne> lEnseigne = crE.displayAllAdmin();
        
        for (Enseigne e : lEnseigne) {
            System.out.println(e.getNomUtilisateur());
                        System.out.println(e.getNote());
                                                System.out.println(e.getSignalEnseigne());
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
    private void suppEnseigne(ActionEvent event) throws SQLException {
        int id = 0;
        CRUD_Enseigne crE = new CRUD_Enseigne();

            Enseigne e = (Enseigne)tbInformation.getSelectionModel().getSelectedItem();
            id = e.getIdEnseigne();
            crE.delete(id);
            buildData();
    }

    private void enseigneAff(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AdminEnseigne.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void afficheRestaurant(ActionEvent event) throws IOException {
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ChartEnseigne.fxml"));
            Parent root = myLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Formulaire");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void exportPdf(ActionEvent event) {
    }

    @FXML
    private void handleVoyage(MouseEvent event) {
    }

    @FXML
    private void handleBtnDeco(MouseEvent event) {
    }

    @FXML
    private void handleEvent(MouseEvent event) {
    }

    @FXML
    private void enseigneAff(MouseEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.common.cache.LoadingCache;
import com.jfoenix.controls.JFXButton;
import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UtilisateurService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author Ghaieth
 */
public class LoginController implements Initializable {
    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    static DataSource ds =DataSource.getInstance(); 
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Label label;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCreer;

    @FXML
    private void handleBtnLogin(ActionEvent event) {
        String email = textEmail.getText().toString();
        String passwordToHash = textPassword.getText().toString();
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

        try{
            int id = UtilisateurService.authenticate(email,generatedPassword);
            Utilisateur u = UtilisateurService.getUtilisateur(id);
            LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
            empCache.put(1,u);
            if(id == 0){
                infoBox("Enter Correct Email and Password", "Failed", null);
            }else if(UtilisateurService.getUtilisateur(id).getBloque()){
                infoBox("Vous avez été bloqué par l'administrateur", "Failed", null);
            }else{                
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                
                String role = u.getRole();
                
                if(role.equals("Utilisateur") || role.equals("Propriétaire") || role.equals("Agence de voyage")){
                    scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
                    dialogStage.setTitle("WhereTo - Accueil");
                }else  if(role.equals("Admin")){
                    dialogStage.setTitle("WhereTo - Gestion Utilisateurs");
                    scene = new Scene(FXMLLoader.load(getClass().getResource("UtilisateurAdmin.fxml")));
                }
                
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private Utilisateur getUserUsingGuava(int id) throws ExecutionException {
	LoadingCache<Integer, Utilisateur> empCache = UtilisateurService.getLoadingCache();
	//System.out.println(empCache.stats());
	System.out.println("Cache Size:" + empCache.size());
	return empCache.get(id);
  }
    
      @FXML
    void handleBtnCreer(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));
        dialogStage.setTitle("WhereTo - Inscription");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    
}

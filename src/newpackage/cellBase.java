package newpackage;

import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import com.jfoenix.controls.*;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public abstract class cellBase extends AnchorPane {

    protected final Pane pane;
    protected final Pane pane0;
    protected final Label nom;
    protected final Label label;
    protected final Label nbAmis;
    protected final Line line;
    protected final Label label0;
    protected final Label nbAbos;
    protected final ImageView photo;

    public cellBase() {

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
        nom.setText("Carl Cox");
        nom.setTextFill(javafx.scene.paint.Color.WHITE);
        nom.setFont(new Font(24.0));

        label.setLayoutX(389.0);
        label.setLayoutY(8.0);
        label.setText("Amis:");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#9e9e9e"));
        label.setFont(new Font(18.0));

        nbAmis.setLayoutX(440.0);
        nbAmis.setLayoutY(9.0);
        nbAmis.setText("0");
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
        nbAbos.setText("0");
        nbAbos.setFont(new Font(18.0));

        photo.setFitHeight(66.5);
        photo.setFitWidth(66.5);
        photo.setLayoutX(33.0);
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
}

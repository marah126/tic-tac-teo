package com.example.ai;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.EventObject;

public class OnePlayer {
    @FXML
    private Button easy;

    @FXML
    private Button hard;

    @FXML
    private Button medium;

    @FXML
    private Button back;

    public static String level;
    @FXML
    void playLevel(ActionEvent e) throws Exception {
        Button b= (Button) e.getSource();
        level=b.getText();
        //System.out.println(level);
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("onePlayMode.fxml"));
        stage.setScene(root.getScene());

    }
    @FXML
    void goBack(ActionEvent e) throws Exception {
        Button b= (Button) e.getSource();
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
       // stage.setScene(root.getScene());
        Parent root =  FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root,370,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

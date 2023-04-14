package com.example.ai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.EventObject;

public class HelloController {
    @FXML
    private Button onePlayer;

    @FXML
    private Button twoPlayers;

    @FXML
    void onePlayerGame(ActionEvent e) throws Exception{
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("onePlayer.fxml"));
        stage.setScene(root.getScene());
    }

    @FXML
    void twoPlayerGame(ActionEvent e) throws Exception{
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("twoPlayer.fxml"));
        stage.setScene(root.getScene());
    }


}
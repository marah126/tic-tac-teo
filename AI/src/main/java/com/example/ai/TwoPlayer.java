package com.example.ai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.ResourceBundle;

public class TwoPlayer implements Initializable{

    @FXML
    private Button playAgain;
    @FXML
    private Button eight;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Label result;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button back;
    boolean playerTurn=true; // X turn
    int counter=0;
    ArrayList<Button> buttons=new ArrayList<Button>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(four);
        buttons.add(five);
        buttons.add(six);
        buttons.add(seven);
        buttons.add(eight);
        buttons.add(nine);
        playAgain.setDisable(true);
    //    System.out.println("hhhhhhhhhhh");
        result.setText("X turn");
        for (int i=0 ; i<9 ; i++){
            buttons.get(i).setText("");
            buttons.get(i).setDisable(false);
        }

    }

    @FXML
    void clicked(ActionEvent event) {
            counter++;
            Button b= (Button) event.getSource();
           // System.out.print(b.getId());
        if(playerTurn){
            b.setText("X");
            playerTurn=!playerTurn;
            b.setDisable(true);
            result.setText("O turn");


        }
        else {
            b.setText("O");
            playerTurn=!playerTurn;
            b.setDisable(true);
            result.setText("X turn");
        }
        checkWinner(playerTurn);
    }

    @FXML
    void playAgain(ActionEvent event) {
        counter=0;
        playerTurn=true;
        result.setText("X turn");
        for(int i=0;i<9;i++){
            buttons.get(i).setText("");
            buttons.get(i).setDisable(false);
            playAgain.setDisable(true);
        }

    }
    void checkWinner(boolean f){
        String r1,r2,r3,r4,r5,r6,r7,r8;
           r1=one.getText()+two.getText()+three.getText();
           r2=four.getText()+five.getText()+six.getText();
           r3=seven.getText()+eight.getText()+nine.getText();
           r4=one.getText()+four.getText()+seven.getText();
           r5=two.getText()+five.getText()+eight.getText();
           r6=three.getText()+six.getText()+nine.getText();
           r7=one.getText()+five.getText()+nine.getText();
           r8=three.getText()+five.getText()+seven.getText();
           if(r1.equals("XXX") || r2.equals("XXX") || r3.equals("XXX") || r4.equals("XXX") || r5.equals("XXX") ||r6.equals("XXX") || r7.equals("XXX") ||r8.equals("XXX")){
               result.setText("X is the WINNER");
           for (int i=0;i<9;i++){
               buttons.get(i).setDisable(true);
               playAgain.setDisable(false);
           }

       }
       else if(r1.equals("OOO") || r2.equals("OOO") || r3.equals("OOO") || r4.equals("OOO") || r5.equals("OOO") || r6.equals("OOO") || r7.equals("OOO") || r8.equals("OOO")){
           result.setText("O is the WINNER");
           for (int i=0;i<9;i++){
               buttons.get(i).setDisable(true);
               playAgain.setDisable(false);
           }
       }

        if(counter==9){
           result.setText("Draw");
           for (int i=0;i<9;i++){
               buttons.get(i).setDisable(true);
               playAgain.setDisable(false);
           }
       }
    }
    @FXML
    void goBack(ActionEvent e) throws Exception {
        Button b= (Button) e.getSource();
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
       // Stage root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
       // stage.setScene(root.getScene());
        Parent root =  FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root,370,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

}

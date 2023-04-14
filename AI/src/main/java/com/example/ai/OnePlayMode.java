package com.example.ai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Random;
import java.util.ResourceBundle;

public class OnePlayMode implements Initializable {

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
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button playAgain;

    @FXML
    private Label result;

    @FXML
    private Label mode;

    @FXML
    private Button back;

    ArrayList<String> board=new ArrayList<>();
    ArrayList<Button> buttons=new ArrayList<Button>();
    int counter=0;
    int depth=0;

    @FXML
    void goBack(ActionEvent e) throws Exception {
        Button b= (Button) e.getSource();
        EventObject event;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("onePlayer.fxml"));
        stage.setScene(root.getScene());


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // random first move X
        buttons.add(one); board.add("");
        buttons.add(two); board.add("");
        buttons.add(three); board.add("");
        buttons.add(four); board.add("");
        buttons.add(five); board.add("");
        buttons.add(six); board.add("");
        buttons.add(seven); board.add("");
        buttons.add(eight); board.add("");
        buttons.add(nine); board.add("");
        mode.setText("One Player Mode");
        if(OnePlayer.level.equals("Hard")){
            depth=5;
        }
        else if(OnePlayer.level.equals("Medium")){
            depth=3;
        }
        else {
            depth=1;
        }

        random();
    }
    void random(){
        Random rand = new Random();
        int value = rand.nextInt(9);
        System.out.println(value);
        buttons.get(value).setText("X");
        buttons.get(value).setDisable(true);
        board.set(value,"X");
    }
    @FXML
    void clicked(ActionEvent event) {
        Button b= (Button) event.getSource();
        //System.out.print(b.getId());
        b.setText("O");
        b.setDisable(true);
        result.setText("PC turn");
        if (!checkWinner2()) pcTurn();

    }
    public void pcTurn(){
        counter++;
        int alpha=Integer.MIN_VALUE;
        int beta=Integer.MAX_VALUE;
        int a[]=new int[2];
        for (int i=0;i<9;i++){
            board.set(i,buttons.get(i).getText());
        }

        a=alphaBeta(board,depth,alpha,beta,true);
        buttons.get(a[0]).setText("X");
        buttons.get(a[0]).setDisable(true);
        result.setText("Your turn");
        checkWinner2();

    }

    public void playAgain(ActionEvent actionEvent) {
        counter=0;
        result.setText("PC turn");
        for(int i=0;i<9;i++){
            buttons.get(i).setText("");
            buttons.get(i).setDisable(false);
            playAgain.setDisable(true);
        }
        random();
    }

    public int[] alphaBeta(ArrayList<String> board,int depth, int alpha,int beta,boolean player){
        int v;
        int index=100;
        int z;
        int arr[]=new int[2];
        z=checkWinner(board);
        if (z!=0){
            arr[0]=100; arr[1]=z;
            return arr;
        }else if (depth==0){
            z=evResult(board);
            arr[0]=100; arr[1]=z;
            return arr;
        }

        if (player) { //max ==> X
                v = Integer.MIN_VALUE;
                for (int i = 0; i < 9; i++) {
                    if (board.get(i).equals("")) {
                        board.set(i,"X");
                        arr=alphaBeta(board, depth - 1, alpha, beta, false);
                        board.set(i,"");
                        z=arr[1];
                        if(z>v){
                            v=z;
                            index=i;
                        }
                        //v = Math.max(v, alphaBeta(board, depth - 1, alpha, beta, true));
                        alpha = Math.max(alpha, v);
                        if (alpha >= beta) {
                            break;
                        }
                    }
                }
                arr[0]=index; arr[1]=v;
                return arr;
            } else {
                v = Integer.MAX_VALUE;
                for (int i = 0; i < 9; i++) {
                    if (board.get(i).equals("")) {
                        board.set(i, "O");
                        arr = alphaBeta(board, depth - 1, alpha, beta, true);
                        board.set(i,"");
                        z = arr[1];
                        if (z < v) {
                            v = z;
                            index = i;
                        }
                        //v = Math.max(v, alphaBeta(board, depth - 1, alpha, beta, true));
                        beta = Math.min(beta, v);
                        if (alpha >= beta) {
                            break;
                        }
                    }
                }
                arr[0] = index;
                arr[1] = v;
                return arr;
            }
    }
    public int checkWinner(ArrayList<String>board){
        String r1,r2,r3,r4,r5,r6,r7,r8;
        r1=board.get(0)+board.get(1)+board.get(2);
        r2=board.get(3)+board.get(4)+board.get(5);
        r3=board.get(6)+board.get(7)+board.get(8);
        r4=board.get(0)+board.get(3)+board.get(6);
        r5=board.get(1)+board.get(4)+board.get(7);
        r6=board.get(2)+board.get(5)+board.get(8);
        r7=board.get(0)+board.get(4)+board.get(8);
        r8=board.get(2)+board.get(4)+board.get(6);
        if(r1.equals("XXX") || r2.equals("XXX") || r3.equals("XXX") || r4.equals("XXX") || r5.equals("XXX") ||r6.equals("XXX") || r7.equals("XXX") ||r8.equals("XXX")) {
            return 100;
        }
        else if(r1.equals("OOO") || r2.equals("OOO") || r3.equals("OOO") || r4.equals("OOO") || r5.equals("OOO") || r6.equals("OOO") || r7.equals("OOO") || r8.equals("OOO")) {
            return -100;
        }
        return 0;
    }
    public int evaluation(ArrayList <String>board,String player){
        int check=0;
        int paths=0;
        int i=0;
        for (int j=1;j<4;j++){
            for ( ;i<3*j;i++){
                if(board.get(i).equals(player) || board.get(i).equals("")){
                    check++;
                }
            }
            if (check == 3)paths++;
            check=0;
        }
        for (int j=0;j<3;j++){
            for (int k=0;k<3;k++){
                if (board.get((k*3)+j).equals(player) || board.get((k*3)+j).equals("")){
                    check++;
                }
            }
            if (check == 3)paths++;
            check=0;
        }
        for (int j=0;j<9;j=j+4){
            if(board.get(j).equals(player) || board.get(j).equals("")){
                check++;
            }
        }
        if (check == 3)paths++;
        check=0;

        for (int j=2;j<7;j=j+2){
            if(board.get(j).equals(player) || board.get(j).equals("")){
                check++;
            }
        }
        if (check == 3)paths++;
        check=0;

        return paths;
    }

    public int evResult (ArrayList<String>b){
        int x=evaluation(b,"X");
        int o=evaluation(b,"O");
        return x-o;
    }
    public boolean checkWinner2(){
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
            result.setText("PC is the WINNER");
            for (int i=0;i<9;i++){
                buttons.get(i).setDisable(true);
                playAgain.setDisable(false);
            }
        return true;
        }
        else if(r1.equals("OOO") || r2.equals("OOO") || r3.equals("OOO") || r4.equals("OOO") || r5.equals("OOO") || r6.equals("OOO") || r7.equals("OOO") || r8.equals("OOO")){
            result.setText("You are the WINNER");
            for (int i=0;i<9;i++){
                buttons.get(i).setDisable(true);
                playAgain.setDisable(false);
            }
            return true;
        }
        else {
            System.out.println(counter);

        if(counter==4) {
            result.setText("Draw");
            for (int i = 0; i < 9; i++) {
                buttons.get(i).setDisable(true);
                playAgain.setDisable(false);
            }
            return true;
        }
        return false;
        }

    }
}


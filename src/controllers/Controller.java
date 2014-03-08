package controllers;

import javafx.geometry.Pos;
import model.*;
import view.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class Controller implements Runnable {

    private Player activePlayer;

    private Player player1;
    private Player player2;
    private Player player3;
    private Field field;
    private View view;

    public Controller(Player player1, Player player2, Player player3, Field field, View view) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.field = field;
        this.view = view;
    }

    public void run(){
        if(player1 != null){
            player1.setToken('X');
        }
        if(player2 != null){
            player2.setToken('O');
        }
        if(player3 != null){
            player3.setToken('#');
        }
        chooseActivePlayer(player1, player2, player3);
        view.displayWhoPlayFirst(activePlayer);
        Position position = new Position();
        while(true) {
            view.displayWhatPlayersTurnNow(activePlayer);
            view.drawField(field);
            position = activePlayer.makeMove(field, view);
            if(position == null){
                view.displayNoSide();
                break;
            } else {
                field.setToken(position.x, position.y, activePlayer.getToken());
            }
            if(field.isOver()){
                view.drawField(field);
                view.displayWinner(activePlayer);
                break;
            }
            nextPlayer(player1, player2, player3);
        }
    }

    private void chooseActivePlayer(Player player1, Player player2, Player player3){
        Random rand = new Random();
        int tmp = rand.nextInt(3);
        if (tmp == 0){
            activePlayer=player1;
        } else if (tmp == 1) {
            activePlayer=player2;
        } else {
            activePlayer=player3;
        }
        if(activePlayer == null){
            chooseActivePlayer(player1, player2, player3);
        }
    }

    public void nextPlayer(Player player1, Player player2, Player player3){
        if(activePlayer.equals(player1)) {
            if(player2 != null){
                activePlayer = player2;
            } else if (player3 != null){
                activePlayer = player3;
            }
        } else if (activePlayer.equals(player2)){
            if (player3 != null){
                activePlayer = player3;
            } else if (player1 != null){
                activePlayer = player1;
            }
        } else if (activePlayer.equals(player3)){
            if (player1 != null){
                activePlayer = player1;
            } else if (player2 != null){
                activePlayer = player2;
            }
        }
    }
}

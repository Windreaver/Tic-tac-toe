package model;

import view.*;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class Player {

    private char myToken;

    public boolean makeMove(Field field, View view){
        System.out.println("model.Player " + myToken + " make some move");
        return true;
    }

    public void setToken(char myToken) {
        this.myToken = myToken;
    }

    public char getToken() {
        return myToken;
    }

}

package model;

import view.*;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class HumanPlayer extends Player {
    public boolean makeMove(Field field, View view) {
        if (field.hasEmptyPosition()){
            Position position = view.readTokenPosition(this, field);
            field.setToken(position.x, position.y, getToken());
            return true;
        }
        return false;
    }
}

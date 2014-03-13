package model;

import view.View;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class HumanPlayer extends Player {

    public Position makeMove(Field field, View view) {
        if (field.hasEmptyPosition()){
            return view.readTokenPosition(this, field);
        }
        return null;
    }
}

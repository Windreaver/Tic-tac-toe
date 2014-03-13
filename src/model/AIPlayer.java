package model;

import view.View;

import java.util.Random;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class AIPlayer extends Player {

    public Position makeMove(Field field, View view){
        Position position = new Position();
        if (field.hasEmptyPosition()){
            int x, y, dir;

            for (int i = 0; i < field.getFieldSize(); i++) {
                for (int j = 0; j <field.getFieldSize(); j++) {
                    if(field.getToken(i,j) == Field.DEFAULT_CHAR){
                        dir = field.getDirectionThatHaveXTokenInLine(i, j, field.getNumberOfTokenToWin() - 1);
                        if (dir != -1){
                            position.setPosition(i, j);
                            return position;
                        }
                    }
                }
            }

            for (int i = 0; i < field.getFieldSize(); i++) {
                for (int j = 0; j <field.getFieldSize(); j++) {
                    if(field.getToken(i,j) == Field.DEFAULT_CHAR){
                        dir = field.getDirectionThatHaveXTokenInLine(i, j, field.getNumberOfTokenToWin() - 2);
                        if (dir != -1){
                            if(checkForDefaultTokenInDirOnDelta(field, i, j, dir, field.getNumberOfTokenToWin() - 2)){
                                position.setPosition(i, j);
                                return position;
                            }
                        }
                    }
                }
            }
//comment to test IDEA commit
//            for (int i = 0; i < field.getFieldSize(); i++) {
//                for (int j = 0; j <field.getFieldSize(); j++) {
//                    if(field.getToken(i,j) == Field.DEFAULT_CHAR){
//                        dir = field.getDirectionThatHaveXTokenInLine(i, j, field.getNumberOfTokenToWin() - 2);
//                        if (dir != -1){
//                            position.setPosition(i, j);
//                            return position;
//                        }
//                    }
//                }
//            }

            Random rand = new Random();
            do{
                x = rand.nextInt(field.getFieldSize());
                y = rand.nextInt(field.getFieldSize());
            } while (!field.placeIsEmpty(x,y));

            position.setPosition(x, y);
            return position;
        }
        return null;
    }

    private boolean checkForDefaultTokenInDirOnDelta(Field field, int i, int j, int dir, int delta) {
        switch (dir) {
            case 0:
                if(j - delta - 1 >= 0 && field.getToken(i, j - delta - 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 1:
                if(j - delta - 1 >= 0  && i + delta + 1 < field.getFieldSize() && field.getToken(i + delta + 1, j - delta - 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 2:
                if(i + delta + 1 < field.getFieldSize() && field.getToken(i + delta + 1, j) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 3:
                if(i + delta + 1 < field.getFieldSize() && j + delta + 1 < field.getFieldSize() && field.getToken(i + delta + 1, j + delta + 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 4:
                if(j + delta + 1 < field.getFieldSize() && field.getToken(i, j + delta + 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 5:
                if(i - delta - 1 >= 0 && j + delta + 1 < field.getFieldSize() && field.getToken(i - delta - 1, j + delta + 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 6:
                if(i - delta - 1 >= 0 && field.getToken(i - delta - 1, j) == Field.DEFAULT_CHAR)
                    return true;
                break;
            case 7:
                if(j - delta - 1 >= 0  && i - delta - 1 >= 0 && field.getToken(i - delta - 1, j - delta - 1) == Field.DEFAULT_CHAR)
                    return true;
                break;
        }
        return false;
    }
}

package model;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class Field {

    public static final char DEFAULT_CHAR = ' ';

    private final int fieldSize;

    private final int numberOfTokenToWin;

    private final char[][] field;

    public Field(int fieldSize, int numberOfTokenToWin) {
        this.fieldSize = fieldSize;
        if(numberOfTokenToWin <= fieldSize){
            this.numberOfTokenToWin = numberOfTokenToWin;
        } else{
            this.numberOfTokenToWin = fieldSize;
        }
        field = new char[this.fieldSize][this.fieldSize];
        eraseField();
    }

    public void eraseField(){
        for(int i=0;i< fieldSize;i++){
            for(int j=0;j< fieldSize;j++){
                field[i][j] = DEFAULT_CHAR;
            }
        }
    }

    public boolean placeIsEmpty(int i, int j){
        return field[i][j] == DEFAULT_CHAR;
    }

    public void setToken(int i, int j, char token){
        field[i][j]=token;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public char getToken(int i, int j) {
        return field[i][j];
    }

    public boolean hasEmptyPosition(){
        for(int i=0;i< fieldSize;i++){
            for(int j=0;j< fieldSize;j++){
                if(field[i][j]==DEFAULT_CHAR)
                    return true;
            }
        }
        return false;
    }

    public int getNumberOfTokenToWin() {
        return numberOfTokenToWin;
    }

    public boolean checkFalseDiagonalForXTokenInLine(int i, int j, int numberOfToken) {
        boolean result=true;
        if(getToken(i, j) != DEFAULT_CHAR){
            for (int k = 0; k < numberOfToken - 1; k++) {
                if(getToken(i, j)!= getToken(i - k - 1, j + k + 1)){
                    result = false;
                }
            }
        }
        else {
            result = false;
        }
        return result;
    }

    public boolean checkTrueDiagonalForXTokenInLine(int i, int j, int numberOfToken) {
        boolean result=true;
        if(getToken(i, j) != DEFAULT_CHAR){
            for (int k = 0; k < numberOfToken - 1; k++) {
                if(getToken(i, j)!= getToken(i + k + 1, j + k + 1)){
                    result = false;
                }
            }
        }
        else {
            result = false;
        }
        return result;
    }

    public boolean checkRowForXTokenInLine(int i, int j, int numberOfToken){
        boolean result=true;
        if(getToken(i, j) != DEFAULT_CHAR){
            for (int k = 0; k < numberOfToken - 1; k++) {
                if(getToken(i, j)!= getToken(i + k + 1, j)){
                    result = false;
                }
            }
        }
        else {
            result = false;
        }
        return result;
    }

    public boolean checkColumnForXTokenInLine(int i, int j, int numberOfToken){
        boolean result=true;
        if(getToken(i, j) != DEFAULT_CHAR){
            for (int k = 0; k < numberOfToken - 1; k++) {
                if(getToken(i, j)!= getToken(i, j + k + 1)){
                    result = false;
                }
            }
        }
        else {
            result = false;
        }
        return result;
    }



    public int getDirectionThatHaveXTokenInLine(int x, int y, int numberOfToken){
        if(checkNDirectionToXTokenInLine(x, y, numberOfToken)) return 0;
        if(checkNEDirectionToXTokenInLine(x, y, numberOfToken)) return 1;
        if(checkEDirectionToXTokenInLine(x, y, numberOfToken)) return 2;
        if(checkSEDirectionToXTokenInLine(x, y, numberOfToken)) return 3;
        if(checkSDirectionToXTokenInLine(x, y, numberOfToken)) return 4;
        if(checkSWDirectionToXTokenInLine(x, y, numberOfToken)) return 5;
        if(checkWDirectionToXTokenInLine(x, y, numberOfToken)) return 6;
        if(checkNWDirectionToXTokenInLine(x, y, numberOfToken)) return 7;
        return -1;
    }

    private boolean checkWDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < numberOfToken || x > fieldSize || y < 0 || y > fieldSize || field[x - 1][y] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x - 1][y] != field[x - 1 - k][y]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSWDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < numberOfToken || x > fieldSize || y < 0 || y > fieldSize - numberOfToken - 1 || field[x - 1][y + 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x - 1][y + 1] != field[x - 1 - k][y + 1 + k]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < 0 || x > fieldSize || y < 0 || y > fieldSize - numberOfToken - 1 || field[x][y + 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x][y + 1] != field[x][y + 1 + k]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSEDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < 0 || x > fieldSize - numberOfToken - 1 || y < 0 || y > fieldSize - numberOfToken - 1 || field[x + 1][y + 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x + 1][y + 1] != field[x + 1 + k][y + 1 + k]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkEDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < 0 || x > fieldSize - numberOfToken - 1 || y < 0 || y > fieldSize || field[x + 1][y] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x + 1][y] != field[x + 1 + k][y]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkNWDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < numberOfToken || x > fieldSize || y < numberOfToken || y > fieldSize || field[x - 1][y - 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x - 1][y - 1] != field[x - 1 - k][y - 1 - k]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkNDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < 0 || x > fieldSize || y < numberOfToken || y > fieldSize || field[x][y - 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x][y - 1] != field[x][y - 1 - k]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkNEDirectionToXTokenInLine(int x, int y, int numberOfToken) {
        if(x < 0 || x > fieldSize - numberOfToken -1 || y < numberOfToken || y > fieldSize || field[x + 1][y - 1] == DEFAULT_CHAR){
            return false;
        } else {
            for (int k = 1; k < numberOfToken; k++) {
                if(field[x + 1][y - 1] != field[x + 1 + k][y - 1 - k]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOver(){
        for (int i = 0; i < getFieldSize() - getNumberOfTokenToWin() + 1; i++) {
            for (int j = 0; j < getFieldSize(); j++) {
                if(checkRowForXTokenInLine(i, j, getNumberOfTokenToWin())){
                    return true;
                }
            }
        }
        for (int i = 0; i < getFieldSize(); i++) {
            for (int j = 0; j < getFieldSize() - getNumberOfTokenToWin() + 1; j++) {
                if(checkColumnForXTokenInLine(i, j, getNumberOfTokenToWin())){
                    return true;
                }
            }
        }
        for (int i = 0; i < getFieldSize() - getNumberOfTokenToWin() + 1; i++) {
            for (int j = 0; j < getFieldSize() - getNumberOfTokenToWin() + 1; j++) {
                if(checkTrueDiagonalForXTokenInLine(i, j, getNumberOfTokenToWin())){
                    return true;
                }
            }
        }
        for (int i = getNumberOfTokenToWin() - 1; i < getFieldSize(); i++) {
            for (int j = 0; j < getFieldSize()- getNumberOfTokenToWin() + 1; j++) {
                if(checkFalseDiagonalForXTokenInLine(i, j, getNumberOfTokenToWin())){
                    return true;
                }
            }
        }
        return false;
    }
}

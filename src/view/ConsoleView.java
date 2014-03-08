package view;

import model.*;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class ConsoleView implements View {

    public ConsoleView(Player player1, Player player2, Player player3, Field field) {
    }

    @Override
    public void displayWhoPlayFirst(Player player) {
        System.out.println("Player " + player.getToken() + " turns first");
    }

    @Override
    public void drawField(Field field) {
        System.out.print(" ");
        for(int i=1; i<=field.getFieldSize();i++){
            System.out.print("  " + i);
        }
        System.out.println();
        for(int i=0;i<field.getFieldSize();i++){
            drawFieldRow(field, i);
        }
        System.out.println();
    }

    private void drawFieldRow (Field field, int row){
        System.out.print(row + 1 + " ");
        for(int j=0;j<field.getFieldSize();j++){
            System.out.print("[" + field.getToken(row, j) + "]");
        }
        System.out.println();
    }

    @Override
    public void displayWhatPlayersTurnNow(Player player) {
        System.out.println("Player " + player.getToken() + " turn");
    }

    @Override
    public void displayWinner(Player player) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Congratulations!!! Player " + player.getToken() + " win.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void displayNoSide() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~No side!~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public Position readTokenPosition(Player player, Field field) {
        Position position;
        System.out.println(player.getToken() + " type your move like [<X position> <Y position>]");
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        if( (tmp.toCharArray().length < 3) || (tmp.charAt(2) - 49 >= 0 && tmp.charAt(2) - 49 < field.getFieldSize() && tmp.charAt(0) - 49 >= 0 && tmp.charAt(0) - 49 < field.getFieldSize())){
            if (field.getToken(tmp.charAt(2) - 49,tmp.charAt(0) - 49) == Field.DEFAULT_CHAR){
                position = new Position();
                position.setPosition(tmp.charAt(2) - 49, tmp.charAt(0) - 49);
            } else {
                System.out.println("You choose busy position, try again");
                drawField(field);
                position = readTokenPosition(player, field);
            }
        } else {
            System.out.println("You choose out-of-field position, try again");
            drawField(field);
            position = readTokenPosition(player, field);
        }
        return position;
    }


}
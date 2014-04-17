package view;

import model.Field;
import model.Player;
import model.Position;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public interface View {
    void displayWhoPlayFirst(Player player);

    void drawField(Field field);

    void displayWhatPlayersTurnNow(Player player);

    void displayWinner(Player player);

    void displayNoSide();

    Position readTokenPosition(Player player, Field field);
}

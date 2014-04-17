import controllers.Controller;
import model.AIPlayer;
import model.Field;
import model.HumanPlayer;
import model.Player;
import view.FrameView;
import view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 09.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class Main {
    public static void main(String[] args) {
        Player player1 = new AIPlayer();
        Player player2 = new AIPlayer();
        Player player3 = new HumanPlayer();
        Field field = new Field(15, 5);
        View view = new FrameView(player1, player2, player3, field);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Controller(player1, player2, player3, field, view));
    }
}
package view;

import controllers.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 12.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */

public class FrameView extends JFrame implements View {

    private JButton[][] buttons;

    Position position = new Position();

    private int X;

    private int Y;

    private int newFieldSize = 15;

    private int newNumberOfTokens = 5;

    private Player player1;

    private Player player2;

    private Player player3;

    private Field field;

    private JButton player1Button, player2Button, player3Button;

    JLabel pl1Label, pl2Label, pl3Label;

    public FrameView(Player player1, Player player2, Player player3, Field field){
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.field = field;
        newFieldSize = field.getFieldSize();
        newNumberOfTokens = field.getNumberOfTokenToWin();
        InitFrame();
    }

    public void InitFrame() {

        setTitle("Tic-tac-toe");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel fieldPanel, menuPanel;

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 9));

        player1Button = new JButton(buttonText(player1));
        player1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setNextPlayerOnButtonClick((JButton) actionEvent.getSource());
            }
        });
        player2Button = new JButton(buttonText(player2));
        player2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setNextPlayerOnButtonClick((JButton) actionEvent.getSource());
            }
        });
        player3Button = new JButton(buttonText(player3));
        player3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setNextPlayerOnButtonClick((JButton) actionEvent.getSource());
            }
        });
        menuPanel.add(player1Button);
        menuPanel.add(player2Button);
        menuPanel.add(player3Button);

        pl1Label = new JLabel("X", JLabel.CENTER);
        pl2Label = new JLabel("O", JLabel.CENTER);
        pl3Label = new JLabel("#", JLabel.CENTER);
        pl1Label.setEnabled(false);
        pl2Label.setEnabled(false);
        pl3Label.setEnabled(false);
        menuPanel.add(pl1Label);
        menuPanel.add(pl2Label);
        menuPanel.add(pl3Label);

        JScrollBar fieldScrollBar = new JScrollBar(Adjustable.HORIZONTAL);
        final JLabel fieldLabel = new JLabel();
        fieldScrollBar.setMaximum(29);
        fieldScrollBar.setMinimum(3);
        fieldScrollBar.setValue(field.getFieldSize());
        fieldScrollBar.setBlockIncrement(1);
        fieldLabel.setText(String.valueOf(newFieldSize) + " [" + newNumberOfTokens + " tokens]");
        fieldScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
                newFieldSize = adjustmentEvent.getValue();
                if (newFieldSize == 3){
                    newNumberOfTokens = 3;
                } else if (newFieldSize < 9){
                    newNumberOfTokens = 4;
                } else {
                    newNumberOfTokens = 5;
                }

                fieldLabel.setText(String.valueOf(newFieldSize) + " [" + newNumberOfTokens + " tokens]");
            }
        });

        menuPanel.add(fieldScrollBar);
        menuPanel.add(fieldLabel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            synchronized public void actionPerformed(ActionEvent actionEvent) {
                Player player1 = setNewPlayer(player1Button);
                Player player2 = setNewPlayer(player2Button);
                Player player3 = setNewPlayer(player3Button);
                Field field = new Field(newFieldSize, newNumberOfTokens);
                View view = new FrameView(player1, player2, player3, field);
                ExecutorService exec = Executors.newSingleThreadExecutor();
                exec.execute(new Controller(player1, player2, player3, field, view));
                setVisible(false);
            }
        });
        menuPanel.add(startButton);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(field.getFieldSize(), field.getFieldSize()));

        buttons = new JButton[field.getFieldSize()][field.getFieldSize()];

        for (int i = 0; i <field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                buttons[i][j] = new JButton(String.valueOf(Field.DEFAULT_CHAR));
                buttons[i][j].setName(String.valueOf(100 + i + j * field.getFieldSize()));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    synchronized public void actionPerformed(ActionEvent actionEvent) {
                        int tmp = Integer.valueOf(actionEvent.toString().substring(91,94)) - 100;
                        X = tmp % field.getFieldSize();
                        Y = tmp / field.getFieldSize();
                        position.go();
                    }
                });
                fieldPanel.add(buttons[i][j]);
            }
        }
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    private String buttonText(Player player){
        if (player == null){
            return "None";
        } else if (player.getClass().getSimpleName().equals("AIPlayer")){
            return "AI";
        }
        return "Human";
    }

    private void setNextPlayerOnButtonClick(JButton button) {
        if (button.getText().equals("None")){
            button.setText("Human");
        } else if (button.getText().equals("Human")){
            button.setText("AI");
        } else {
            button.setText("None");
        }
    }

    private Player setNewPlayer(JButton button){
        String tmp = button.getText();
        if (tmp.equals("AI")){
            return new AIPlayer();
        } else if (tmp.equals("Human")){
            return new HumanPlayer();
        }
        return null;
    }

    @Override
    public void displayWhoPlayFirst(Player player) {
        displayWhatPlayersTurnNow(player);
    }

    @Override
    public void drawField(Field field) {
        for (int i = 0; i <field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                buttons[i][j].setText(String.valueOf(field.getToken(i, j)));
            }
        }
    }

    @Override
    public void displayWhatPlayersTurnNow(Player player) {
        pl1Label.setEnabled(false);
        pl2Label.setEnabled(false);
        pl3Label.setEnabled(false);
        if (player.getToken() == 'X'){
            pl1Label.setEnabled(true);
        }
        if (player.getToken() == 'O'){
            pl2Label.setEnabled(true);
        }
        if (player.getToken() == '#'){
            pl3Label.setEnabled(true);
        }
    }

    @Override
    public void displayWinner(Player player) {
        JOptionPane.showMessageDialog(null, "Player " + player.getToken() + " win.");

    }

    @Override
    public void displayNoSide() {
        JOptionPane.showMessageDialog(null, "No side!!!");
    }

    @Override
    synchronized public Position readTokenPosition(Player player, Field field) {
        position.stop();
        if (field.getToken(X, Y) != Field.DEFAULT_CHAR){
            //Message that position is busy!!!
            readTokenPosition(player, field);
        }
        position.setPosition(X, Y);
        return position;
    }

    public static void main(String[] args){
        Player player1 = new AIPlayer();
        Player player2 = new AIPlayer();
        Player player3 = new HumanPlayer();
        Field field = new Field(15, 5);
        View view = new FrameView(player1, player2, player3, field);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Controller(player1, player2, player3, field, view));
    }

}

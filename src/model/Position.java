package model;

/**
 * Created with IntelliJ IDEA
 * User: Vova
 * Date: 11.02.14
 * Project: Tic-tac-toe
 * To change this template use File | Settings | File Templates
 */
public class Position {
    int x;
    int y;

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    synchronized public void stop() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void go(){
        notifyAll();
    }
}

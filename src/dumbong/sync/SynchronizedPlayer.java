package dumbong.sync;

import pingpong.PingPongConstant;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class SynchronizedPlayer extends Thread {
    private static final Object lock = new Object();
    private static PingPongConstant turn = PING;
    private final Runnable action;
    private final String word;
    private int times;

    public SynchronizedPlayer(PingPongConstant repeat, int times) {
        this.word = repeat.getWord();
        this.action = turn(repeat);
        this.times = times;
    }

    @Override
    public void run() {
        while (times > 0) {
            action.run();
        }
    }


    private void ping() {
        synchronized (lock) {
            try {
                makeTurn(PING, PONG);
                while (turn == PONG) {
                    lock.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pong() {
        synchronized (lock) {
            makeTurn(PONG, PING);
            lock.notifyAll();
        }
    }

    private void makeTurn(PingPongConstant mine, PingPongConstant other) {
        if (turn == mine) {
            System.out.println(word);
            times--;
            turn = other;
        }
    }

    private Runnable turn(PingPongConstant repeat) {
        switch (repeat) {
            case PING:
                return this::ping;
            case PONG:
                return this::pong;
        }
        return null;
    }
}

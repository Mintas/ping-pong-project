package dumbong.vltl;

import pingpong.PingPongConstant;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class VolatilePlayer extends Thread {
    private static volatile PingPongConstant turn = PING;
    private final Runnable action;
    private final String word;
    private int times;

    public VolatilePlayer(PingPongConstant repeat, int times) {
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

    private void action(PingPongConstant mine, PingPongConstant other) {
        while (true) {
            if (turn == mine) {
                System.out.println(word);
                times--;
                turn = other;
                return;
            }
        }
    }

    private Runnable turn(PingPongConstant repeat) {
        switch (repeat) {
            case PING:
                return () -> action(PING, PONG);
            case PONG:
                return () -> action(PONG, PING);
        }
        return null;
    }
}

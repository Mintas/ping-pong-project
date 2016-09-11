package dumbong.semaphore;

import pingpong.PingPongConstant;

import java.util.concurrent.Semaphore;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class SemaphorePlayer extends Thread {
    private static final Semaphore ping = new Semaphore(1);
    private static final Semaphore pong = new Semaphore(0);
    private final Runnable action;
    private final String word;
    private int times;

    public SemaphorePlayer(PingPongConstant repeat, int times) {
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

    private void action(Semaphore mine, Semaphore other) {
        mine.acquireUninterruptibly();
        System.out.println(word);
        times--;
        other.release();
    }

    private Runnable turn(PingPongConstant repeat) {
        switch (repeat) {
            case PING:
                return () -> action(ping, pong);
            case PONG:
                return () -> action(pong, ping);
        }
        return null;
    }
}

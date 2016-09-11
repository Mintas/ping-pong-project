package dumbong.reentrant;

import pingpong.PingPongConstant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class ReentrantPlayer extends Thread {
    private static final Lock lock = new ReentrantLock();
    private static final Condition ping = lock.newCondition();
    private static final Condition pong = lock.newCondition();
    private final Runnable action;
    private final String word;
    private int times;

    public ReentrantPlayer(PingPongConstant repeat, int times) {
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

    private void action(Condition mine, Condition other) {
        try {
            lock.lock();
            System.out.println(word);
            times--;
            mine.signalAll();
            if (times > 0)
                other.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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

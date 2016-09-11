package dumbong.reentrant;

import pingpong.PingPongConstant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class ReentrantPlayerFixed extends Thread {
    private static PingPongConstant turn = PING;
    private static final Lock lock = new ReentrantLock();
    private static final Condition ping = lock.newCondition();
    private static final Condition pong = lock.newCondition();
    private final Runnable action;
    private final String word;
    private int times;

    public ReentrantPlayerFixed(PingPongConstant repeat, int times) {
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

    private void action(PingPongConstant myTurn, Condition mine, PingPongConstant otherTurn, Condition other) {
        if (turn == myTurn) {
            try {
                lock.lock();
                System.out.println(word);
                times--;
                turn = otherTurn;
                mine.signalAll();
                if (times > 0)
                    while (turn == otherTurn)
                        other.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private Runnable turn(PingPongConstant repeat) {
        switch (repeat) {
            case PING:
                return () -> action(PING, ping, PONG, pong);
            case PONG:
                return () -> action(PONG, pong, PING, ping);
        }
        return null;
    }
}

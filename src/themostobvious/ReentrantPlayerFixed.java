package themostobvious;

import pingpong.PingPongConstant;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class ReentrantPlayerFixed implements Runnable {
    private final AtomicReference<PingPongConstant> state;
    private final Lock lock;
    private final Condition mine;
    private final Condition other;
    private final PingPongConstant word;

    public ReentrantPlayerFixed(PingPongConstant repeat, AtomicReference<PingPongConstant> state, Lock lock, Condition mine, Condition other) {
        this.word = repeat;
        this.state = state;
        this.lock = lock;
        this.mine = mine;
        this.other = other;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (state.get() == word) {
                    try {
                        other.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(word);
                state.set(word);
                mine.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        AtomicReference<PingPongConstant> state = new AtomicReference<>(PONG);
        Lock lock = new ReentrantLock();
        Condition ping = lock.newCondition();
        Condition pong = lock.newCondition();

        start(PING, state, lock, ping, pong);
        start(PONG, state, lock, pong, ping);
    }

    private static void start(PingPongConstant word, AtomicReference<PingPongConstant> state, Lock lock, Condition mine, Condition other) {
        new Thread(new ReentrantPlayerFixed(word, state, lock, mine, other)).start();
    }
}

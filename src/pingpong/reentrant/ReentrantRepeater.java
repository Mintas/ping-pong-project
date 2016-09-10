package pingpong.reentrant;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class ReentrantRepeater extends WordRepeater {
    private final Lock lock;
    private final Condition mine;
    private final Condition other;

    public ReentrantRepeater(PingPongConstant repeat, int times, Lock lock, Condition mine, Condition other) {
        super(repeat, times);
        this.lock = lock;
        this.mine = mine;
        this.other = other;
    }

    @Override
    public void run() {
        while (times > 0) {
            action();
        }
    }

    private void action() {
        try {
            lock.lock();
            System.out.println(word);
            times--;
            mine.signalAll();
            if (times > 0)
                //todo: add Turn to this Repeater; call await in while cycle (see SynchronizedRepeater)
                other.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

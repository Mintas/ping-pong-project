package pingpong.semaphore;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.concurrent.Semaphore;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class SemaphoreRepeater extends WordRepeater {
    private final Semaphore mine;
    private final Semaphore other;

    public SemaphoreRepeater(PingPongConstant repeat, int times, Semaphore mine, Semaphore other) {
        super(repeat, times);
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
        mine.acquireUninterruptibly();
        System.out.println(word);
        other.release();
        times--;
    }
}

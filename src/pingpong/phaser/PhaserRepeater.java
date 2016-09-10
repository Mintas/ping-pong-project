package pingpong.phaser;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.concurrent.Phaser;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class PhaserRepeater extends WordRepeater {
    private final Phaser phaser;
    private final int phaserStep;

    public PhaserRepeater(PingPongConstant repeat, int times, Phaser phaser, int repeatersCount) {
        super(repeat, times);
        this.phaser = phaser;
        this.phaserStep = repeatersCount - 1;
    }

    @Override
    public void run() {
        while (times > 0) {
            action();
        }
    }

    private void action() {
        System.out.println(word);
        times--;
        int arriveAt = phaser.arrive();
        if (times > 0) {
            phaser.awaitAdvance(arriveAt + phaserStep);
        }
    }
}

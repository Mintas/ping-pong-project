package dumbong.phaser;

import pingpong.PingPongConstant;

import java.util.concurrent.Phaser;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class PhaserPlayer extends Thread {
    private static final Phaser phaser = new Phaser(1);
    private final String word;
    private int times;

    public PhaserPlayer(PingPongConstant repeat, int times) {
        this.word = repeat.getWord();
        this.times = times;
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
            phaser.awaitAdvance(arriveAt + 1);
        }
    }
}

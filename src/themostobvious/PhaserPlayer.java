package themostobvious;

import pingpong.PingPongConstant;

import java.util.concurrent.Phaser;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class PhaserPlayer implements Runnable {
    private final Phaser phaser;
    private final PingPongConstant word;

    public PhaserPlayer(PingPongConstant repeat, Phaser phaser) {
        this.word = repeat;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(word);
            int arriveAt = phaser.arrive();
            phaser.awaitAdvance(arriveAt + 1);
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        start(PING, phaser);
        start(PONG, phaser);
    }

    private static void start(PingPongConstant mine, Phaser phaser) {
        new Thread(new PhaserPlayer(mine, phaser)).start();
    }
}

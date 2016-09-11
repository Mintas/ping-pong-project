package dumbong;

import dumbong.phaser.PhaserPlayer;
import dumbong.reentrant.ReentrantPlayer;
import dumbong.reentrant.ReentrantPlayerFixed;
import dumbong.semaphore.SemaphorePlayer;
import dumbong.sync.SynchronizedPlayer;
import dumbong.vltl.VolatilePlayer;
import org.junit.Test;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class PingPongImplTest {
    private final PingPong game = new PingPongImpl();
    private final int timesToRepeat = 5;

    @Test
    public void syncPingPong() throws Exception {
        SynchronizedPlayer pinger = new SynchronizedPlayer(PING, timesToRepeat);
        SynchronizedPlayer ponger = new SynchronizedPlayer(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

    @Test
    public void volatilePingPong() throws Exception {
        VolatilePlayer pinger = new VolatilePlayer(PING, timesToRepeat);
        VolatilePlayer ponger = new VolatilePlayer(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

    @Test
    public void reentrantPingPong() throws Exception {
        ReentrantPlayer pinger = new ReentrantPlayer(PING, timesToRepeat);
        ReentrantPlayer ponger = new ReentrantPlayer(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

    @Test
    public void reentrantPingPongFixed() throws Exception {
        ReentrantPlayerFixed pinger = new ReentrantPlayerFixed(PING, timesToRepeat);
        ReentrantPlayerFixed ponger = new ReentrantPlayerFixed(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

    @Test
    public void semaphorePingPong() throws Exception {
        SemaphorePlayer pinger = new SemaphorePlayer(PING, timesToRepeat);
        SemaphorePlayer ponger = new SemaphorePlayer(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

    @Test
    public void phaserPingPong() throws Exception {
        PhaserPlayer pinger = new PhaserPlayer(PING, timesToRepeat);
        PhaserPlayer ponger = new PhaserPlayer(PONG, timesToRepeat);

        game.playPingPong(pinger, ponger);
    }

}
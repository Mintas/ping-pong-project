package pingpong.factory;

import javafx.util.Pair;
import pingpong.SynchronizationStrategy;
import pingpong.WordRepeater;
import pingpong.phaser.PhaserRepeater;
import pingpong.reentrant.ReentrantRepeater;
import pingpong.semaphore.SemaphoreRepeater;
import pingpong.primitive.synchronizedshare.SimpleTurn;
import pingpong.primitive.synchronizedshare.SynchronizedRepeater;
import pingpong.primitive.volatileshare.VolatileRepeater;
import pingpong.primitive.volatileshare.VolatileTurn;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class PingPongRepeatersFactoryImpl implements PingPongRepeatersFactory {
    @Override
    public Pair<WordRepeater, WordRepeater> createRepeaters(SynchronizationStrategy strategy, int timesToRepeat) {
        switch (strategy) {
            case SYNCHRONIZED:
                return createSynchronizedRepeaters(timesToRepeat);
            case VOLATILE:
                return createVolatileRepeaters(timesToRepeat);
            case REENTRANT:
                return createReentrantRepeaters(timesToRepeat);
            case SEMAPHORE:
                return createSemaphoreRepeaters(timesToRepeat);
            case PHASER:
                return createPhaserRepeaters(timesToRepeat);
        }

        return null;
    }

    private Pair<WordRepeater, WordRepeater> createPhaserRepeaters(int timesToRepeat) {
        Phaser phaser = new Phaser(1);
        return new Pair<>(
                new PhaserRepeater(PING, timesToRepeat, phaser, 2),
                new PhaserRepeater(PONG, timesToRepeat, phaser, 2));
    }

    private Pair<WordRepeater, WordRepeater> createSynchronizedRepeaters(int timesToRepeat) {
        SimpleTurn turn = new SimpleTurn();
        return new Pair<>(
                new SynchronizedRepeater(PING, timesToRepeat, turn),
                new SynchronizedRepeater(PONG, timesToRepeat, turn));
    }

    private Pair<WordRepeater, WordRepeater> createVolatileRepeaters(int timesToRepeat) {
        VolatileTurn turn = new VolatileTurn();
        return new Pair<>(
                new VolatileRepeater(PING, timesToRepeat, turn),
                new VolatileRepeater(PONG, timesToRepeat, turn));
    }

    private Pair<WordRepeater, WordRepeater> createReentrantRepeaters(int timesToRepeat) {
        Lock lock = new ReentrantLock();
        Condition pinged = lock.newCondition();
        Condition ponged = lock.newCondition();
        return new Pair<>(
                new ReentrantRepeater(PING, timesToRepeat, lock, pinged, ponged),
                new ReentrantRepeater(PONG, timesToRepeat, lock, ponged, pinged));
    }

    private Pair<WordRepeater, WordRepeater> createSemaphoreRepeaters(int timesToRepeat) {
        Semaphore mutexPing = new Semaphore(1);
        Semaphore mutexPong = new Semaphore(0);

        return new Pair<>(
                new SemaphoreRepeater(PING, timesToRepeat, mutexPing, mutexPong),
                new SemaphoreRepeater(PONG, timesToRepeat, mutexPong, mutexPing));
    }
}

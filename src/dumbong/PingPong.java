package dumbong;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public interface PingPong {
    void playPingPong(Thread pinger, Thread ponger) throws InterruptedException;
}

package pingpong;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public interface PingPong {
    void playPingPong(SynchronizationStrategy withSyncStrategy, int hitsCount) throws InterruptedException;
}

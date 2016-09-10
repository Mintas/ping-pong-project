package pingpong;

import javafx.util.Pair;
import pingpong.factory.PingPongRepeatersFactory;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class PingPongImpl implements PingPong {
    private final PingPongRepeatersFactory repeatersFactory;

    public PingPongImpl(PingPongRepeatersFactory repeatersFactory) {
        this.repeatersFactory = repeatersFactory;
    }

    @Override
    public void playPingPong(SynchronizationStrategy withSyncStrategy, int hitsCount) throws InterruptedException {
        Pair<WordRepeater, WordRepeater> repeaters = repeatersFactory.createRepeaters(withSyncStrategy, hitsCount);
        System.out.println("Ready… Set… Go!");

        Thread ping = repeaters.getKey();
        Thread pong = repeaters.getValue();

        ping.start();
        pong.start();

        ping.join();
        pong.join();

        System.out.println("Done!");
    }
}

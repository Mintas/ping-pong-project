package pingpong.factory;

import javafx.util.Pair;
import pingpong.SynchronizationStrategy;
import pingpong.WordRepeater;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public interface PingPongRepeatersFactory {
    Pair<WordRepeater, WordRepeater> createRepeaters(SynchronizationStrategy forStrategy, int timesToRepeat);
}

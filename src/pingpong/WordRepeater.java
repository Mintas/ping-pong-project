package pingpong;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public abstract class WordRepeater extends Thread {
    protected final String word;
    protected int times;

    protected WordRepeater(PingPongConstant repeat, int times) {
        this.word = repeat.getWord();
        this.times = times;
    }
}

package pingpong;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public enum PingPongConstant {
    PING("pIng! ---> "),
    PONG("pOng! ---> ");

    private final String word;

    PingPongConstant(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}

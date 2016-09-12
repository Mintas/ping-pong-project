package themostobvious;

import pingpong.PingPongConstant;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class SynchronizedPlayer implements Runnable {
    private final PingPongConstant word;
    private final State state;

    public SynchronizedPlayer(PingPongConstant word, State state) {
        this.word = word;
        this.state = state;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (state) {
                while (state.getCurrent() == word) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(word);
                state.setCurrent(word);
                state.notify();
            }
        }
    }

    public static void main(String[] args) {
        State state = new State();
        start(state, PING);
        start(state, PONG);
    }

    private static void start(State state, PingPongConstant ping) {
        new Thread(new SynchronizedPlayer(ping, state)).start();
    }

    public static class State {
        private PingPongConstant current = PingPongConstant.PONG;

        public void setCurrent(PingPongConstant current) {
            this.current = current;
        }

        public PingPongConstant getCurrent() {
            return current;
        }
    }
}

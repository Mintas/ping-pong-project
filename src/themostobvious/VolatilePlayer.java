package themostobvious;

import pingpong.PingPongConstant;

import java.util.concurrent.atomic.AtomicReference;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class VolatilePlayer implements Runnable {
    private final PingPongConstant mine;
    private final AtomicReference<PingPongConstant> state;

    public VolatilePlayer(PingPongConstant mine, AtomicReference<PingPongConstant> state) {
        this.mine = mine;
        this.state = state;
    }

    @Override
    public void run() {
        while (true) {
            while (state.get() == mine) {
            }
            System.out.println(mine);
            state.set(mine);
        }
    }

    public static void main(String[] args) {
        AtomicReference<PingPongConstant> reference = new AtomicReference<>(PONG);
        start(PING, reference);
        start(PONG, reference);
    }

    private static void start(PingPongConstant state, AtomicReference<PingPongConstant> reference) {
        new Thread(new VolatilePlayer(state, reference)).start();
    }
}

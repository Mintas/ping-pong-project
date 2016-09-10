package pingpong.primitive.volatileshare;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.function.Supplier;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class VolatileRepeaterOld extends WordRepeater {
    private final VolatileTurn turn;
    private final Supplier<Void> action;

    public VolatileRepeaterOld(PingPongConstant repeat, int times, VolatileTurn turn) {
        super(repeat, times);
        this.turn = turn;
        this.action = turn(repeat);
    }

    @Override
    public void run() {
        while (times > 0) {
            action.get();
        }
    }

    private Supplier<Void> turn(PingPongConstant repeat) {
        switch (repeat) {
            case PING:
                return this::ping;
            case PONG:
                return this::pong;
        }
        return null;
    }

    private Void ping() {
        return action(PING, PONG);
    }

    private Void pong() {
        return action(PONG, PING);
    }

    private Void action(PingPongConstant mine, PingPongConstant other) {
        while (true) {
            if (turn.getHit() == mine) {
                System.out.println(word);
                times--;
                turn.setHit(other);
                return null;
            }
        }
    }
}

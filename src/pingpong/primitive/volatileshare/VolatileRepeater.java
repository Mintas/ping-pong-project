package pingpong.primitive.volatileshare;

import pingpong.PingPongConstant;
import pingpong.primitive.PrimitiveRepeater;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class VolatileRepeater extends PrimitiveRepeater {
    private final VolatileTurn turn;

    public VolatileRepeater(PingPongConstant repeat, int times, VolatileTurn turn) {
        super(repeat, times, turn);
        this.turn = turn;
    }

    @Override
    public Void ping() {
        return action(PING, PONG);
    }

    @Override
    public Void pong() {
        return action(PONG, PING);
    }

    private Void action(PingPongConstant mine, PingPongConstant other) {
        while (true) {
            if(makeHit(mine, other)) return null;
        }
    }
}

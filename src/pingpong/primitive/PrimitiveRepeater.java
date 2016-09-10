package pingpong.primitive;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.function.Supplier;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public abstract class PrimitiveRepeater extends WordRepeater implements PingPonger {
    protected final Turn turn;
    protected final Supplier<Void> action;

    protected PrimitiveRepeater(PingPongConstant repeat, int times, Turn turn) {
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

    protected boolean makeHit(PingPongConstant mine, PingPongConstant other) {
        if (turn.getHit() == mine) {
            System.out.println(word);
            times--;
            turn.setHit(other);
            return true;
        }
        return false;
    }
}

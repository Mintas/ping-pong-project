package pingpong.primitive.synchronizedshare;

import pingpong.PingPongConstant;
import pingpong.WordRepeater;

import java.util.function.Supplier;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class SynchronizedRepeaterOld extends WordRepeater {
    private final SimpleTurn turn;
    private final Supplier<Void> action;

    public SynchronizedRepeaterOld(PingPongConstant repeat, int times, SimpleTurn turn) {
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
        synchronized (turn) {
            try {
                makeTurn(PING, PONG);
                turn.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Void pong() {
        synchronized (turn) {
            makeTurn(PONG, PING);
            turn.notifyAll();
        }

        return null;
    }

    private void makeTurn(PingPongConstant mine, PingPongConstant other) {
        if (turn.getHit() == mine) {
            System.out.println(word);
            times--;
            turn.setHit(other);
        }
    }
}

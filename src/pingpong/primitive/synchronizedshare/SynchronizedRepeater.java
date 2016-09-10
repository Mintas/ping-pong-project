package pingpong.primitive.synchronizedshare;

import pingpong.PingPongConstant;
import pingpong.primitive.PrimitiveRepeater;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class SynchronizedRepeater extends PrimitiveRepeater {
    private final SimpleTurn turn;

    public SynchronizedRepeater(PingPongConstant repeat, int times, SimpleTurn turn) {
        super(repeat, times, turn);
        this.turn = turn;
    }

    @Override
    public Void ping() {
        synchronized (turn) {
            try {
                makeHit(PING, PONG);
                while (turn.getHit() == PONG) {
                    turn.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Void pong() {
        synchronized (turn) {
            makeHit(PONG, PING);
            turn.notifyAll();
        }

        return null;
    }
}

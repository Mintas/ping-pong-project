package themostobvious;

import pingpong.PingPongConstant;

import java.util.concurrent.Semaphore;

import static pingpong.PingPongConstant.PING;
import static pingpong.PingPongConstant.PONG;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class SemaphorePlayer implements Runnable {
    private final Semaphore mine;
    private final Semaphore other;
    private final PingPongConstant pingPong;

    public SemaphorePlayer(Semaphore mine, Semaphore other, PingPongConstant pingPong) {
        this.mine = mine;
        this.other = other;
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        while (true) {
            mine.acquireUninterruptibly();
            System.out.println(pingPong);
            other.release();
        }
    }

    public static void main(String[] args) {
        Semaphore ping = new Semaphore(1);
        Semaphore pong = new Semaphore(0);
        start(ping, pong, PING);
        start(pong, ping, PONG);
    }

    private static void start(Semaphore mine, Semaphore other, PingPongConstant ping) {
        new Thread(new SemaphorePlayer(mine, other, ping)).start();
    }
}

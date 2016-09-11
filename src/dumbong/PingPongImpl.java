package dumbong;

/**
 * Created by SBT-Kovalev-DA on 11.09.2016.
 */
public class PingPongImpl implements PingPong {
    @Override
    public void playPingPong(Thread pinger, Thread ponger) throws InterruptedException {
        System.out.println("Ready… Set… Go!");

        pinger.start();
        ponger.start();

        pinger.join();
        ponger.join();

        System.out.println("Done!");
    }
}

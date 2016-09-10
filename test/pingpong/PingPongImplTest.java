package pingpong;

import org.junit.Test;
import pingpong.factory.PingPongRepeatersFactory;
import pingpong.factory.PingPongRepeatersFactoryImpl;

import static pingpong.SynchronizationStrategy.*;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class PingPongImplTest {
    private final PingPongRepeatersFactory repeatersFactory = new PingPongRepeatersFactoryImpl();
    private final PingPong theGame = new PingPongImpl(repeatersFactory);

    @Test
    public void playPingPong() throws Exception {
        //theGame.playPingPong(SEMAPHORE, 5);

        //theGame.playPingPong(VOLATILE, 5);

        theGame.playPingPong(SYNCHRONIZED, 5);

        //theGame.playPingPong(REENTRANT, 5);

        //theGame.playPingPong(PHASER, 5);
    }
}
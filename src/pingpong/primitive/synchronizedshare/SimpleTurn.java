package pingpong.primitive.synchronizedshare;

import pingpong.PingPongConstant;
import pingpong.primitive.Turn;

import static pingpong.PingPongConstant.PING;

/**
 * Created by SBT-Kovalev-DA on 10.09.2016.
 */
public class SimpleTurn  implements Turn{
    private PingPongConstant hit = PING;

    @Override
    public PingPongConstant getHit() {
        return hit;
    }

    @Override
    public void setHit(PingPongConstant hit) {
        this.hit = hit;
    }
}

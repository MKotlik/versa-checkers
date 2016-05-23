package networkGame.sin;

/**
 * Created by Sam on 5/22/2016.
 */

public class Packet {
    public static class Packet0LoginRequest{
    }

    public static class Packet1LoginAnswer{
        public boolean accepted = false;
    }

    public static class Packet2Message{
        public String message;
    }
}

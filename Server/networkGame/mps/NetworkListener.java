package networkGame.mps;

import com.esotericsoftware.kryonet.Client;
import networkGame.mps.Packet.*;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class NetworkListener extends Listener{

    public void connected(Connection arg0){
        Log.info("[Server] Someone is trying to connect");
    }

    public void disconnected(Connection arg0){
        Log.info("[Server] Someone is trying to disconnect");
    }

    public void received(Connection c, Object o) {
        if (o instanceof Packet0LoginRequest) {
            Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
            loginAnswer.accepted = true;
            c.sendTCP(loginAnswer);
        }
        if (o instanceof Packet2Message) {
            String message = ((Packet2Message) o).message;
            Log.info(message);
        }
    }
}
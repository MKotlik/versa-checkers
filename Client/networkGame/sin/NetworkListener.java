package networkGame.sin;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import networkGame.sin.Packet.Packet0LoginRequest;
import networkGame.sin.Packet.Packet1LoginAnswer;
import networkGame.sin.Packet.Packet2Message;

public class NetworkListener extends Listener{
    private Client client;

    public void init(Client client){
        this.client = client;
    }

    public void connected(Connection arg0){
        Log.info("[Client] You have connected");
        client.sendTCP(new Packet0LoginRequest());
    }

    public void disconnected(Connection arg0){
        Log.info("[Client] You have disconnected");
    }

    public void received(Connection c, Object o) {
        if (o instanceof Packet1LoginAnswer) {
            boolean answer = ((Packet1LoginAnswer) o).accepted;
            if(answer){
                Log.info("Please enter your first message for the server");
                while(true){
                    if(MPClient.scanner.hasNext()){
                        Packet2Message mpacket = new Packet2Message();
                        mpacket.message = MPClient.scanner.nextLine();
                        client.sendTCP(mpacket);
                        Log.info("Please send another message!");
                    }
                }
            }else{
                c.close();
            }
        }
        if (o instanceof Packet2Message) {
            String message = ((Packet2Message) o).message;
            Log.info(message);
        }
    }
}
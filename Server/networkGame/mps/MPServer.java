package networkGame.mps;

import networkGame.mps.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.kryonet.Server;

import java.io.*;

public class MPServer{
    private Server server;

    public MPServer() throws IOException {
        server = new Server();
        registerPackets();
        server.addListener(new NetworkListener());
        server.bind(1216);
        server.start();
    }

    private void registerPackets(){
        Kryo kryo = server.getKryo();
        kryo.register(Packet0LoginRequest.class);
        kryo.register(Packet1LoginAnswer.class);
        kryo.register(Packet2Message.class);
    }

    public static void main(String [] args) {
        try {
            new MPServer();
            Log.set(Log.LEVEL_DEBUG);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
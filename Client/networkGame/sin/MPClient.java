package networkGame.sin;

import com.esotericsoftware.minlog.Log;
import networkGame.sin.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.util.Scanner;
import java.io.*;

/**
 * Created by Sam on 5/22/2016.
 */
public class MPClient {
    public Client client;
    public static Scanner scanner;

    public MPClient() {
        scanner = new Scanner(System.in);
        client = new Client();
        register();

        NetworkListener n1 = new NetworkListener();
        n1.init(client);
        client.addListener(n1);

        client.start();
        try {
            Log.info("Please enter the specified IP:");
            client.connect(5000, scanner.nextLine(), 1216);
        }catch (IOException e){
            e.printStackTrace();
            client.stop();
        }
    }

    private void register(){
        Kryo kryo = client.getKryo();
        kryo.register(Packet0LoginRequest.class);
        kryo.register(Packet1LoginAnswer.class);
        kryo.register(Packet2Message.class);
    }

    public static void main(String [] args){
        new MPClient();
        Log.set(Log.LEVEL_DEBUG);
        while(true){

        }
    }
}

package code.Socket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;

import code.RSA;
import code.RSA.PublicKey;
import code.ServerModel;

public class BroadcastGame implements Runnable  {
	private ServerModel model;
    private DatagramSocket socket;
    private final RSA encryption;
    private boolean running;
    private byte[] sendBuf = new byte[128];
    private byte[] recieveBuf = new byte[128];
	public BroadcastGame(int serverPort, ServerModel serverModel) {
		encryption = new RSA();
        try {
			socket = new DatagramSocket(serverPort);
			model = serverModel;
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }

	/**
	 * Runs UDP server
	 */
    @Override
    public void run() {
    	running = true;
    	
    	try {
            while (running) {
            	// receive bobE
                byte[] bobE = new byte[1];
                DatagramPacket packet = new DatagramPacket(bobE, 1);
                socket.receive(packet);
                packet = new DatagramPacket(bobE, 1, packet.getAddress(), packet.getPort());
                bobE = packet.getData();
                
                // receive bobN length
                byte[] length = new byte[2];
            	packet = new DatagramPacket(length, 2);
                socket.receive(packet);
                packet = new DatagramPacket(length, 2, packet.getAddress(), packet.getPort());
                length = packet.getData();
                
                // receive bobN
                byte[] bobN = new byte[Integer.parseInt(new BigInteger(length).toString())];
                packet = new DatagramPacket(bobN, bobN.length);
                socket.receive(packet);
                packet = new DatagramPacket(bobN, bobN.length, packet.getAddress(), packet.getPort());
                bobN = packet.getData();
                
                PublicKey bobPublicKey = new PublicKey(bobE, bobN);
                
            	sendBuf = encryption.encrypt(createMessage().getBytes(), bobPublicKey);
            	
            	// send length of send length
            	byte[] sendLength = new BigInteger(Integer.toString(sendBuf.length)).toByteArray();
            	byte[] lengthOfSendLength = new BigInteger(Integer.toString(sendLength.length)).toByteArray();
              	packet = new DatagramPacket(lengthOfSendLength, lengthOfSendLength.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
            	
                // send buffer length
            	packet = new DatagramPacket(sendLength, sendLength.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
                
                // send buffer
                packet = new DatagramPacket(sendBuf, sendBuf.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
            }
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Closes UDP server
     */
    public void close() {
    	socket.close();
    	running = false;
    }
    
    /**
     * Creates mesage to send to client about room
     * @return
     */
    public String createMessage() {

        try {
        	Socket ip = new Socket();
			ip.connect(new InetSocketAddress("google.com", 80));
			String message = model.GetHostName() + "/" + ip.getLocalAddress().getHostAddress() + "/" + model.GetCurrentPlayers() + 
					"/" + model.GetMaxPlayers() + "/" + model.GetAccessType() + "/" + model.GetPassword();
			for (int i = 0; i < model.getPlayers().size(); i++) {
				message += "/" + model.getPlayers().get(i).PlayerName;
			}
			
			return message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "";
    }
}

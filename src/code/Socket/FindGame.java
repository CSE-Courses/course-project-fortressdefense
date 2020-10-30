package code.Socket;

import java.io.IOException;
import java.net.*;

import code.GameConstants;

public class FindGame {
    private DatagramSocket socket;
	
	private byte[] sendBuf = new byte[256];
	private byte[] recieveBuf = new byte[256];
	
	public FindGame() {
	    try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String sendEcho(String msg) {
    	try {
    		sendBuf = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName("255.255.255.255"), GameConstants.port);
            socket.send(packet);
            socket.setSoTimeout(1000);
            packet = new DatagramPacket(recieveBuf, recieveBuf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            return received.trim();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return "ERROR";

    }
 
    public void close() {
        socket.close();
    }
}

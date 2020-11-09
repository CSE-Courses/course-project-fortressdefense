package code.Socket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;

import code.GameConstants;
import code.RSA;

public class FindGame {
	private final RSA encryption;
    private DatagramSocket socket;
	
	private byte[] sendBuf = new byte[128];
	private byte[] recieveBuf = new byte[128];
	
	public FindGame() {
		encryption = new RSA();
	    try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends message to UDP server
	 * @param msg
	 * @return
	 */
    public String pingServer() {
    	try {
            sendBuf = "whoami".getBytes();
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName(getBroadcastAddress()), GameConstants.udpPort);
            socket.send(packet);
            socket.setSoTimeout(1000);
            packet = new DatagramPacket(recieveBuf, recieveBuf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            return received.trim();
            /*
    		// Send Bob's public E and N to Alice.
            byte[] bobE = encryption.getPublicKey().getE();
            byte[] bobN = encryption.getPublicKey().getN();
            byte[] bobNLength = new BigInteger(Integer.toString(bobN.length)).toByteArray();

            // send BobE
            DatagramPacket packet = new DatagramPacket(bobE, bobE.length, InetAddress.getByName(getBroadcastAddress()), GameConstants.udpPort);
            socket.send(packet);
            
            // send BobN length
            packet = new DatagramPacket(bobNLength, bobNLength.length, InetAddress.getByName(getBroadcastAddress()), GameConstants.udpPort);
            socket.send(packet);
            
            // send BobN
            packet = new DatagramPacket(bobN, bobN.length, InetAddress.getByName(getBroadcastAddress()), GameConstants.udpPort);
            socket.send(packet);
            
            socket.setSoTimeout(1000);
            
            // receive length of receive length
            byte[] lengthOfRecieveLength = new byte[1];
            packet = new DatagramPacket(lengthOfRecieveLength, lengthOfRecieveLength.length);
            socket.receive(packet);
            lengthOfRecieveLength = packet.getData();
            
            // receive receive length
            byte[] recieveLength = new byte[Integer.parseInt(new BigInteger(lengthOfRecieveLength).toString())];   
            packet = new DatagramPacket(recieveLength, recieveLength.length);
            socket.receive(packet);
            recieveLength = packet.getData();
            
            // recieve buffer
            recieveBuf = new byte[Integer.parseInt(new BigInteger(recieveLength).toString())];
            packet = new DatagramPacket(recieveBuf, recieveBuf.length);
            socket.receive(packet);
            String received = new String(encryption.decrypt(packet.getData()));
            return received.trim();
            */
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return "";

    }
    
    /**
     * Close UDP client
     */
    public void close() {
        socket.close();
    }
    
    
    /**
     * Gets Broadcast address
     * @return
     */
    private String getBroadcastAddress() {

		try {
	    	Socket ip = new Socket();
			ip.connect(new InetSocketAddress("google.com", 80));
			InetAddress localIP = ip.getLocalAddress();
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localIP);
			short subnet = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
			String[] address = localIP.getHostAddress().split("\\.");
			switch (subnet) {
				case 8:
					address[1] = "255";
					address[2] = "255";
					address[3] = "255";
					break;
				case 16:
					address[2] = "255";
					address[3] = "255";
					break;
				case 24:
					address[3] = "255";
					break;
					
			}

			return String.join(".", address);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";

    }
}

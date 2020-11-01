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
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName(getBroadcastAddress()), GameConstants.udpPort);
            socket.send(packet);
            socket.setSoTimeout(1000);
            packet = new DatagramPacket(recieveBuf, recieveBuf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            return received.trim();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return "";

    }
 
    public void close() {
        socket.close();
    }
    
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

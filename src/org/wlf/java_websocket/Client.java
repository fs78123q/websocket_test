package org.wlf.java_websocket;
import java.net.URI;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Client extends WebSocketClient {

    public Client(URI serverURI) {
		super(serverURI);
	}

    @Override
    public void onOpen(ServerHandshake arg0) {
    	System.out.println( "opened connection" );
    }
    
	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		System.out.println( "closed connection" );
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();
	}

	@Override
	public void onMessage(String arg0) {
		System.out.println("onMessage : " + arg0);
	}
	
    public static void main(String[] args) {
    	
        try {
        	String uri = "ws://serverIP:8887/";
        	Client client = new Client(new URI( uri ));
            System.out.println("Connecting to " + uri);
            client.connectBlocking();
            WebSocket ws = client.getConnection();
            if(ws.isOpen()){
            	client.send("hi");
            }else{
            	System.out.println("ws.isOpen() " + ws.isOpen());
            }
            client.close();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        
    }
}
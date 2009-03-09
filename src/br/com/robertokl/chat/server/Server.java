package br.com.robertokl.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.robertokl.chat.commoms.handlers.IncomingMessageListener;
import br.com.robertokl.chat.server.actions.ServerActionFactory;
import br.com.robertokl.chat.server.models.Client;

public class Server {

    private static final int PORT = 1234;
    public static Map<Socket, Client> clients = new HashMap<Socket, Client>();
    private final ServerActionFactory factory = new ServerActionFactory();
    
    public static void main(String[] args) throws Exception {
	new Server().start();
    }

    private void start() throws IOException {
	ServerSocket server = new ServerSocket(PORT);
	new Thread(new ConnectionListner(server)).start();
	System.out.println("Server started at " + Calendar.getInstance().getTime() + " on port " + PORT);
    }

    private class ConnectionListner implements Runnable {

	private ServerSocket server;

	public ConnectionListner(ServerSocket server) {
	    this.server = server;
	}

	public void run() {
	    while (true) {
		try {
		    Socket s = server.accept();
		    System.out.println("New connection from: " + s.getInetAddress());
		    new Thread(new IncomingMessageListener(s, factory)).start();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
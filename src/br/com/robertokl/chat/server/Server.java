package br.com.robertokl.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.robertokl.chat.commoms.handlers.IncomingMessageListener;
import br.com.robertokl.chat.server.actions.ServerActionFactory;
import br.com.robertokl.chat.server.models.Client;

public class Server {

	private static final int PORT = 1234;
	public static Map<Socket, Client> clients = new HashMap<Socket, Client>();
	/**
	 */
	private ServerActionFactory factory;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite a chave de criptografia");
		new Server().start(in.nextLine());
	}

	private void start(String key) throws IOException {
		factory = new ServerActionFactory(key);
		ServerSocket server = new ServerSocket(PORT);
		new Thread(new ConnectionListner(server, key)).start();
		System.out.println("Server started at "
				+ Calendar.getInstance().getTime() + " on port " + PORT);
	}

	private class ConnectionListner implements Runnable {

		private ServerSocket server;
		private String key;

		public ConnectionListner(ServerSocket server, String key) {
			this.server = server;
			this.key = key;
		}

		public void run() {
			while (true) {
				try {
					Socket s = server.accept();
					System.out.println("New connection from: "
							+ s.getInetAddress());
					new Thread(new IncomingMessageListener(s, factory, key)).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
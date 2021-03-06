package br.com.robertokl.chat.server.actions;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import br.com.robertokl.chat.commoms.actions.Action;
import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.commoms.constants.Status;
import br.com.robertokl.chat.commoms.helpers.Encriptor;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public abstract class ServerAction implements Action {

	/**
	 */
	protected String[] params;
	/**
	 */
	protected Socket client;
	private String key;

	public ServerAction(String key) {
		this.key = key;
	}
	/**
	 * @param params
	 */
	public void setParams(String[] params) {
		this.params = params;
	}

	/**
	 * @param client
	 */
	public void setClient(Socket client) {
		this.client = client;
	}

	protected synchronized void broadcast(String message) throws Exception {
		for (Socket c : Server.clients.keySet()) {
			sendMessage(c, message);
		}
	}

	protected void sendMessage(Socket c, String message) throws IOException,
			Exception {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(c
				.getOutputStream()));
		out.writeUTF(Encriptor.encode(key, "newActionToClient:" + message));
		out.flush();
	}

	private void createConnectedList(StringBuffer buf) {
		for (Client c : Server.clients.values()) {
			String status = "";
			if (c.getStatus() == Status.AWAY) {
				status = "(A)";
			} else if (c.getStatus() == Status.BUSY) {
				status = "(O)";
			} else if (c.getStatus() == Status.UNAVAILABLE) {
				status = "(I)";
			}
			buf.append("; " + status + " " + c.getName());
		}
	}

	protected void sendInfoMessage(String message) throws Exception {
		broadcast(Actions.BROADCAST_MESSAGE.getAction() + ";Server;" + message);
	}

	protected Socket findConnectionByName(String name) {
		for (Socket client : Server.clients.keySet()) {
			if (Server.clients.get(client).getName().equals(name)) {
				return client;
			}
		}
		return null;
	}

	protected String createConnectedList(Actions action) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(action.getAction());
		createConnectedList(buf);
		return buf.toString();
	}
}

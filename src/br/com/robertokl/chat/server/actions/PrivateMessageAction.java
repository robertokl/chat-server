package br.com.robertokl.chat.server.actions;

import java.net.Socket;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;

public class PrivateMessageAction extends ServerAction {

    public void execute() throws Exception {
	String senderName = Server.clients.get(super.client).getName();
	String receiverName = super.params[0];
	Socket receiver = findConnectionByName(receiverName);
	if (receiver == null) {
	    super.sendMessage(super.client, Actions.ERROR.getAction() + ";\"" + receiverName + "\" não encontrado.");
	    return;
	}
	super.sendMessage(receiver, getActionMessage(senderName));
	super.sendMessage(super.client, getActionMessage(senderName));
    }

    private String getActionMessage(String name) {
	return Actions.PRIVATE_MESSAGE.getAction() + ";" + name + "(PM)" + ";" + super.params[1];
    }

    private Socket findConnectionByName(String name) {
	for (Socket client : Server.clients.keySet()) {
	    if (Server.clients.get(client).getName().equals(name)) {
		return client;
	    }
	}
	return null;
    }
}

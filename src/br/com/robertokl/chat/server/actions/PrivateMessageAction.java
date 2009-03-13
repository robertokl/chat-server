package br.com.robertokl.chat.server.actions;

import java.net.Socket;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class PrivateMessageAction extends ServerAction {

    public void execute() throws Exception {
	Client sender = Server.clients.get(super.client);
	if (sender.isMute()) {
	    sendMessage(client, Actions.MUTE.getAction());
	    return;
	}
	String senderName = sender.getName();
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
}

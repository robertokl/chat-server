package br.com.robertokl.chat.server.actions;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class SendMessageAction extends ServerAction {

    public SendMessageAction(String key) {
		super(key);
	}

	private String getMessage() throws Exception {
	return Actions.BROADCAST_MESSAGE.getAction() + ";" + Server.clients.get(super.client).getName()
		+ ";" + super.params[0];
    }

    public synchronized void execute() throws Exception {
	Client c = Server.clients.get(client);
	if (c.isMute()) {
	    sendMessage(client, Actions.MUTE.getAction());
	    return;
	}
	broadcast(getMessage());
    }

}

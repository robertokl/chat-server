package br.com.robertokl.chat.server.actions;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;

public class SendMessageAction extends ServerAction {

    private String getMessage() throws Exception {
	return Actions.BROADCAST_MESSAGE.getAction() + ";" + Server.clients.get(super.client).getName()
		+ ";" + super.params[0];
    }

    public synchronized void execute() throws Exception {
	broadcast(getMessage());
    }

}
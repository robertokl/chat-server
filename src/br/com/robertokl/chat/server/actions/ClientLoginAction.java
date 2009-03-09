package br.com.robertokl.chat.server.actions;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.commoms.constants.Status;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class ClientLoginAction extends ServerAction {

    protected String getLoginActionMessage() throws Exception {
	StringBuffer buf = new StringBuffer();
	buf.append(Actions.CLIENT_LOGIN.getAction());
	createConnectedList(buf);
	return buf.toString();
    }

    public synchronized void execute() throws Exception {
	Client c = new Client();
	c.setName(super.params[0]);
	c.setStatus(Status.AVAILABLE);
	Server.clients.put(super.client, c);
	broadcast(getLoginActionMessage());
	super.sendInfoMessage(Server.clients.get(super.client).getName() + " entrou no chat.");
    }
}

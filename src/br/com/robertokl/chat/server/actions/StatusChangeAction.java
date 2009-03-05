package br.com.robertokl.chat.server.actions;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.commoms.constants.Status;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class StatusChangeAction extends ServerAction {

    private String getStatusChangeActionMessage() throws Exception {
	StringBuffer buf = new StringBuffer();
	buf.append(Actions.STATUS_CHANGE.getAction());
	createConnectedList(buf);
	return buf.toString();
    }

    public synchronized void execute() throws Exception {
	String newStatus = super.params[0];
	Client c = Server.clients.get(super.client);
	c.setStatus(Status.getStatus(newStatus));
	broadcast(getStatusChangeActionMessage());
	super.sendInfoMessage(c.getName() + " alterou seu status para " + newStatus);
    }

}

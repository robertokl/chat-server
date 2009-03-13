package br.com.robertokl.chat.server.actions;

import java.net.Socket;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class MuteAction extends ServerAction {
    private static final String notAdminMsg = "Você não é admin. Só admin pode mutar. Digite /adminlogin <senha> para logar como admin.";

    public void execute() throws Exception {
	Client c = Server.clients.get(client);
	if (!c.isAdmin()) {
	    sendMessage(client, Actions.ERROR.getAction() + ";" + notAdminMsg);
	    return;
	}
	Socket clientSocket = findConnectionByName(params[0]);
	if (clientSocket == null) {
	    sendMessage(client, Actions.ERROR + ";Usuário não encontrado!");
	    return;
	}
	Client toMuteClient = Server.clients.get(clientSocket);
	toMuteClient.setMute(true);
	toMuteClient.setName("-" + toMuteClient.getName());
	broadcast(createConnectedList(Actions.STATUS_CHANGE));
	sendInfoMessage(toMuteClient.getName() + " está mudo.");
    }

}

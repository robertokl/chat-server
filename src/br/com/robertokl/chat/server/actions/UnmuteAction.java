package br.com.robertokl.chat.server.actions;

import java.net.Socket;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class UnmuteAction extends ServerAction {
    private static final String notAdminMsg = "Você não é admin. Só admin pode desmutar. Digite /adminlogin <senha> para logar como admin.";

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
	Client toUnmuteClient = Server.clients.get(clientSocket);
	toUnmuteClient.setMute(false);
	toUnmuteClient.setName(toUnmuteClient.getName().substring(1));
	broadcast(createConnectedList(Actions.STATUS_CHANGE));
	sendInfoMessage(toUnmuteClient.getName() + " não está mais mudo.");
    }

}

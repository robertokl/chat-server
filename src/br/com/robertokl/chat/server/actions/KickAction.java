package br.com.robertokl.chat.server.actions;

import java.net.Socket;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class KickAction extends ServerAction {

    private static final String notAdminMsg = "Voc� n�o � admin. S� admin pode kickar. Digite /adminlogin <senha> para logar como admin.";

    public void execute() throws Exception {
	Client c = Server.clients.get(client);
	if (!c.isAdmin()) {
	    sendMessage(client, Actions.ERROR.getAction() + ";" + notAdminMsg);
	    return;
	}
	Socket kicked = findConnectionByName(params[0]);
	if (kicked == null) {
	    sendMessage(client, Actions.ERROR.getAction() + ";Usu�rio n�o encontrado.");
	    return;
	}
	Client cKicked = Server.clients.remove(kicked);
	sendMessage(kicked, Actions.KICK.getAction());
	sendInfoMessage(cKicked.getName() + " foi kickado por " + c.getName());
    }

}
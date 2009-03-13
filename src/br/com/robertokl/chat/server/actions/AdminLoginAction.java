package br.com.robertokl.chat.server.actions;

import br.com.robertokl.chat.commoms.constants.Actions;
import br.com.robertokl.chat.server.Server;
import br.com.robertokl.chat.server.models.Client;

public class AdminLoginAction extends ServerAction {

    private final String adminPassword = "aaa123";
    
    public void execute() throws Exception {
	if(!adminPassword.equals(super.params[0])) {
	    sendMessage(client, Actions.ERROR.getAction() + ";Senha inválida.");
	    return;
	}
	Client c = Server.clients.get(client);
	c.setName("@" + c.getName());
	c.setAdmin(true);
	broadcast(createConnectedList(Actions.ADMIN_LOGIN));
	super.sendInfoMessage(c.getName() + " logou como admin.");
    }
    
}

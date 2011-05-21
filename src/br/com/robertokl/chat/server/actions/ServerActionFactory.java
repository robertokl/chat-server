package br.com.robertokl.chat.server.actions;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.robertokl.chat.commoms.actions.Action;
import br.com.robertokl.chat.commoms.actions.ActionFactory;
import br.com.robertokl.chat.server.Server;

public class ServerActionFactory extends ActionFactory {

	private String key;

	public ServerActionFactory(String key) {
		this.key = key;
	}
	
    private void updateClosedConnections() {
	List<Socket> closedSockets = new ArrayList<Socket>();
	for (Socket socket : Server.clients.keySet()) {
	    if (socket.isClosed()) {
		closedSockets.add(socket);
	    }
	}
	for (Socket socket : closedSockets) {
	    Server.clients.remove(socket);
	}
    }

    protected String initialize(String data) {
	if (!data.startsWith("newActionToServer:")) {
	    throw new IllegalAccessError("Mensagem enviada errada ao servidor!");
	}
	updateClosedConnections();
	data = data.replace("newActionToServer:", "");
	return data;
    }
    
    protected Action getClientLoginAction() {
	return new ClientLoginAction(key);
    }
    
    protected Action getSendMessageAction() {
	return new SendMessageAction(key);
    }

    protected Action getStatusChangeAction() {
	return new StatusChangeAction(key);
    }

    protected Action getPrivateMessageAction() {
	return new PrivateMessageAction(key);
    }

    protected Action getErrorAction() {
	return null;
    }

    protected Action getAdminLoginAction() {
	return new AdminLoginAction(key);
    }

    protected Action getKickAction() {
	return new KickAction(key);
    }
    
    protected Action getMuteAction() {
	return new MuteAction(key);
    }

    protected Action getUnmuteAction() {
	return new UnmuteAction(key);
    }
}

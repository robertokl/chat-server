package br.com.robertokl.chat.server.actions;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.robertokl.chat.commoms.actions.Action;
import br.com.robertokl.chat.commoms.actions.ActionFactory;
import br.com.robertokl.chat.server.Server;

public class ServerActionFactory extends ActionFactory {

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

    protected Action getClientLoginAction() {
	return new ClientLoginAction();
    }

    protected Action getSendMessageAction() {
	return new SendMessageAction();
    }

    protected String initialize(String data) {
	if (!data.startsWith("newActionToServer:")) {
	    throw new IllegalAccessError("Mensagem enviada errada ao servidor!");
	}
	updateClosedConnections();
	data = data.replace("newActionToServer:", "");
	return data;
    }

    protected Action getStatusChangeAction() {
	return new StatusChangeAction();
    }

    protected Action getPrivateMessageAction() {
	return new PrivateMessageAction();
    }
}

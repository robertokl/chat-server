package br.com.robertokl.chat.server.models;

import br.com.robertokl.chat.commoms.constants.Status;


public class Client {

    private String name;
    private Status status;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

}
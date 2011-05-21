package br.com.robertokl.chat.server.models;

import br.com.robertokl.chat.commoms.constants.Status;


public class Client {

    /**
	 */
    private String name;
    /**
	 */
    private Status status;
    /**
	 */
    private boolean admin = false;
    /**
	 */
    private boolean mute = false;

    /**
	 * @return
	 */
    public boolean isMute() {
        return mute;
    }

    /**
	 * @param  mute
	 */
    public void setMute(boolean mute) {
        this.mute = mute;
    }

    /**
	 * @return
	 */
    public String getName() {
	return name;
    }

    /**
	 * @param  name
	 */
    public void setName(String name) {
	this.name = name;
    }

    /**
	 * @return
	 */
    public Status getStatus() {
	return status;
    }

    /**
	 * @param  status
	 */
    public void setStatus(Status status) {
	this.status = status;
    }

    /**
	 * @param  admin
	 */
    public void setAdmin(boolean admin) {
	this.admin = admin;
    }
    
    /**
	 * @return
	 */
    public boolean isAdmin() {
	return admin;
    }

}

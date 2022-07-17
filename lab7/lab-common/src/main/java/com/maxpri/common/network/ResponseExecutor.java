package com.maxpri.common.network;

import com.maxpri.common.controllers.CommandListener;

public class ResponseExecutor {

    private final Response response;
    private final CommandListener commandListener;

    public ResponseExecutor(Response response, CommandListener commandListener) {
        this.response = response;
        this.commandListener = commandListener;
    }

    public void execute() {
        response.showResult();
        if (response.getUser() != null) {
            commandListener.setUser(response.getUser());
        }
    }

}

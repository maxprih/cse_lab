package com.maxpri.common.network;

import com.maxpri.common.controllers.CommandListener;

public record ResponseExecutor(Response response,
                               CommandListener commandListener) {

    public void execute() {
        response.showResult();
        if (response.getUser() != null) {
            commandListener.setUser(response.getUser());
        }
    }

}

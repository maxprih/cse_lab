package com.maxpri.common.commands;


import com.maxpri.common.network.Response;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super( "show", "outputs all collection elements", 0);
    }


    @Override
    public Response execute(Object[] args) {
        return new Response(getCommandManager().getCollectionManager().show(), "All persons provided.", true);
    }

}

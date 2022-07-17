package com.maxpri.server.channels;

public enum ChannelState {
    READY_TO_READ,
    READING,
    READY_TO_WRITE,
    WRITING,
    READY_TO_DIE
}

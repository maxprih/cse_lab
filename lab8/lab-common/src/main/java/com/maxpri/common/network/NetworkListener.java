package com.maxpri.common.network;

import com.maxpri.common.state.PerformanceState;

import java.io.IOException;

public interface NetworkListener {

    Response listen(Request request) throws IOException;
    PerformanceState getPerformanceState();

}

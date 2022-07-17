package com.maxpri.common.io;


import java.io.File;
import java.io.IOException;


public interface CollectionFileWriter<T> {
    void write(File file, T object) throws IOException;
}

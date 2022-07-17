package com.maxpri.common.io;


import java.io.File;
import java.io.IOException;

public interface CollectionFileReader<T> {
    T read(File file) throws IOException;
}

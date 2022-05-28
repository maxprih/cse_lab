package com.maxpri.server.config;

import com.maxpri.server.collection.CollectionManagerImpl;
import com.maxpri.common.io.CollectionFileReader;
import com.maxpri.common.io.CollectionFileWriter;
import com.maxpri.server.io.xml.XmlCollectionFileOperator;

import java.io.File;

public final class IOConfig {

    public static final CollectionFileReader<CollectionManagerImpl> COLLECTION_FILE_READER = new XmlCollectionFileOperator();
    public static final CollectionFileWriter<CollectionManagerImpl> COLLECTION_FILE_WRITER = new XmlCollectionFileOperator();
    private static File inputFile;
    private static File outputFile;

    private IOConfig() {

    }

    public static File getInputFile() {
        return inputFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static boolean configure(File file) {
        inputFile = file;
        outputFile = file;
        System.out.println("Configured successfully!");
        return true;
    }

}

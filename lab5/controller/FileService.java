package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pojo.MusicBand;
import util.Validator;
import util.impl.ValidatorImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class FileService {

    private final File file;
    private final XmlMapper xmlMapper = new XmlMapper();
    private final Validator validator = new ValidatorImpl();

    public FileService(File file) {
        this.file = file;
    }

    /**
     * Checking file existence and rights
     *
     * @throws FileNotFoundException
     */
    private void checkFile() throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File was not found");
        if (!file.canRead() || !file.canWrite()) throw new SecurityException("File doesn't allow you to read and write");
    }

    /**
     * Dividing file to number of XML-type strings to parse them to objects later
     *
     * @return List of XML-type strings
     * @throws FileNotFoundException
     * @throws SecurityException
     */
    private ArrayList<String> scanFileToStringList() throws FileNotFoundException, SecurityException {
        checkFile();
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        String line;
        while (scanner.hasNext()) {
            line = scanner.next();
            if (!line.equals("<bands>") && !line.equals("</bands>")) list.add(line);
        }
        scanner.close();
        return list;
    }

    /**
     * Loading collection from XML-file if succeed
     *
     * @return collection of objects
     * @throws FileNotFoundException
     */
    public Stack<MusicBand> loadFromFile() throws FileNotFoundException {
        ArrayList<String> xmlList = scanFileToStringList();
        Stack<MusicBand> newStack = new Stack<>();
        try {
            for (String xml: xmlList) {
                MusicBand band = xmlMapper.readValue(xml, MusicBand.class);
                newStack.add(band);
            }
            for (MusicBand b: newStack) {
                if (validator.validateMusicBand(b) == null) {
                    System.out.println("Collection from file hasn't passed validation. Empty collection will be loaded");
                    return new Stack<>();
                }
                b.setId();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Cannot parse xml file to collection. Empty collection will be loaded");
            return new Stack<>();
        }
        System.out.println("Collection loaded from file successfully");
        return newStack;
    }

    /**
     * Converting collection to XML-file
     *
     * @throws IOException
     * @throws SecurityException
     */
    public void saveToFile() throws IOException, SecurityException {
        checkFile();
        if (Client.bands.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<bands>\n");
            for (MusicBand b: Client.bands) {
                String xml;
                xml = xmlMapper.writeValueAsString(b);
                sb.append(xml);
                sb.append("\n");
            }
            sb.append("</bands>");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
            System.out.println("Collection saved to file successfully");
        } catch (JsonProcessingException e) {
            System.out.println("Saving collection to file failed (error occurred while parsing)");
        }

    }

}

package com.maxpri.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConnectionHandler {

    private Socket socket;
    private boolean isOpen = false;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String lastAddress;
    private int lastPort = 0;


    public ConnectionHandler() {
    }

    public int getLastPort() {
        return lastPort;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public Socket getSocket() {
        return socket;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void openConnection() {
        openConnection(inputAddress(), inputPort());
    }

    public void openConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            isOpen = true;
            lastAddress = String.valueOf(socket.getRemoteSocketAddress());
            lastPort = socket.getPort();
            System.out.println("Connected.");
        } catch (IOException | IllegalArgumentException e) {
            isOpen = false;
            System.out.println("Invalid host or port");
            openConnection();
        }
    }

    private int inputPort() {
        try {
            System.out.println("Enter port:");
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    private String inputAddress() {
        System.out.println("Enter address:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
            isOpen = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}



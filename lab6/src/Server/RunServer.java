package Server;

import client.Run;
import client.commands.AbstractCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class RunServer {

    public static void main(String[] args) {
        Handler userFileHandler = null;
        try {
            userFileHandler = new FileHandler("ServerLogs.log");
        } catch (IOException e) {
            System.out.println("Logger problems");
        }
        Run.log.setUseParentHandlers(false);
        Run.log.addHandler(userFileHandler);
        Run.log.info("Server has started");
        ServerCollection collection = new ServerCollection();
        try {
            ServerSocket server = new ServerSocket(2289);
            Socket client = server.accept();
            DataInputStream inputStream=new DataInputStream(client.getInputStream());
            DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream=new ObjectInputStream(client.getInputStream());
            System.out.println("Подключился новый пользователь");
            while (!client.isClosed()){
                AbstractCommand command = (AbstractCommand) objectInputStream.readObject();
                if (command.getClass().getName().equals("client.commands.Exit")){
                    outputStream.writeUTF("Disconnected");
                    collection.whenExit();
                    break;
                }
                outputStream.writeUTF(collection.executeCurrentCommand(command));
                outputStream.flush();
                System.out.println("Успешный запрос");


            }
        } catch (IOException e) {
            Run.log.warning("IOException in server");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Run.log.warning("ClassNotFoundException in server");
            e.printStackTrace();
        }

    }
}

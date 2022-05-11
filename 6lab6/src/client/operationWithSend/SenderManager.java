package client.operationWithSend;

import client.tools.Asker;
import client.commands.*;
import data.Color;
import data.Dragon;

import Server.JsonProcessing;
import java.util.Date;
import java.util.Scanner;

public class SenderManager {
    private Asker asker = new Asker(new Scanner(System.in));
    private SendCmd sender = new SendCmd();

    public void createAndSend(String[] finalCommand) {
        try {
            switch (finalCommand[0]) {
                case "show":
                    Show show = new Show();
                    sender.Sender(show);
                    break;
                case "help":
                    Help help = new Help();
                    sender.Sender(help);
                    break;
                case "info":
                    Info info = new Info();
                    sender.Sender(info);
                    break;
                case "clear":
                    Clear clear = new Clear();
                    sender.Sender(clear);
                    break;
                case "history":
                    History history = new History();
                    sender.Sender(history);
                    break;
                case "insert":
                    InsertNull insert = new InsertNull();
                    try {
                        Integer.parseInt(finalCommand[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Key must be a Integer");
                        break;
                    }
                    insert.setDragon(new Dragon(asker.askName(), asker.askCoordinates(), new Date(), asker.askAge(), asker.askColor(), asker.askDragonType(), asker.askDragonCharacter(), asker.askDragonHead()));
                    insert.setKey(Integer.parseInt(finalCommand[1]));
                    sender.Sender(insert);
                    break;
                case "update":
                    UpdateID updateid = new UpdateID();
                    try {
                        Integer.parseInt(finalCommand[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("ID must be a Integer");
                        break;
                    }
                    updateid.setId(Integer.parseInt(finalCommand[1]));
                    updateid.setDragon(new Dragon(asker.askName(), asker.askCoordinates(), new Date(), asker.askAge(), asker.askColor(), asker.askDragonType(), asker.askDragonCharacter(), asker.askDragonHead()));
                    sender.Sender(updateid);
                    break;
                case "remove":
                    RemoveKey remove = new RemoveKey();
                    try {
                        Integer.parseInt(finalCommand[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Key must be a Integer");
                        break;
                    }
                    remove.setKey(Integer.parseInt(finalCommand[1]));
                    sender.Sender(remove);
                    break;
                case "rmv_greater":
                    RemoveGreaterKey removegr = new RemoveGreaterKey();
                    try {
                        Integer.parseInt(finalCommand[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Key must be a Integer");
                        break;
                    }
                    removegr.setKey(Integer.parseInt(finalCommand[1]));
                    sender.Sender(removegr);
                    break;
                case "rmv_lower":
                    RemoveLowerKey removelw = new RemoveLowerKey();
                    try {
                        Integer.parseInt(finalCommand[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Key must be a Integer");
                        break;
                    }
                    removelw.setKey(Integer.parseInt(finalCommand[1]));
                    sender.Sender(removelw);
                    break;
                case "filter_color":
                    FilteredByColor filterbycolor = new FilteredByColor();
                    if (finalCommand[1].equals("BLACK") || finalCommand[1].equals("YELLOW") || finalCommand[1].equals("BLUE")) {
                        filterbycolor.setColor(Color.valueOf(finalCommand[1]));
                        sender.Sender(filterbycolor);
                        break;
                    }
                    else {
                        System.out.println("Color must be (BLACK, YELLOW or BLUE)");
                    }
                    break;
                case "max_date":
                    MaxByCreationDate maxbydate = new MaxByCreationDate();
                    sender.Sender(maxbydate);
                    break;
                case "exit":
                    Exit exit = new Exit();
                    sender.Sender(exit);
                    System.out.println("Exiting program");
                    break;
                case "execute_script":
                    ExecuteScript execute_script = new ExecuteScript();
                    execute_script.setFileName(finalCommand[1]);
                    sender.Sender(execute_script);
                    break;
                case "print_ascending":
                    PrintAscending printAscending = new PrintAscending();
                    sender.Sender(printAscending);
                    break;
                default:
                    System.out.println("Command not found. Enter \"help\". ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JsonProcessing.log.warning("ArrayIndexOutOfBoundsException");
            System.out.println("Need a numeric argument!");

        }

    }
}

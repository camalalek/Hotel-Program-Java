package hotelprogram;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class room {

    private static Scanner sc = new Scanner(System.in);

    private static void loadFile(roomObjects roomRef[], String path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        int i = 0;
        String line = raf.readLine();
        while (line != null) {
            roomRef[i].setRoomName(line);
            line = raf.readLine();
            i++;
        }
        System.out.println("[*] File Loaded");
    }

    private static void saveFile(roomObjects roomRef[], String path) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < roomRef.length; i++) {
                out.println(roomRef[i].getRoomName());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[*] File Saved");
    }

    public static void main(String[] args) throws IOException {
        roomObjects[] tmpRoomArray = new roomObjects[10];
        for (int i = 0; i < 10; i++) {
            tmpRoomArray[i] = new roomObjects();
        }
        String path = "savedData.txt";
        loadFile(tmpRoomArray, path);

        boolean inMenu = true;
        String menuInput;

        System.out.println("Welcome to the Hotel program\n");

        System.out.println("'X' = Exit the program\n");
        System.out.println("'A' = Add a customer");
        System.out.println("'V' = View all rooms");
        System.out.println("'E' = Display all empty rooms");
        System.out.println("'D' = Delete customer from room");
        System.out.println("'F' = Find room from customer name");
        System.out.println("'S' = Store program data to file");
        System.out.println("'L' = Load program data from file");
        System.out.println("'O' = View rooms ordered alphabetically by name\n");

        while (inMenu) {
            System.out.print("> ");
            menuInput = sc.next();

            switch (menuInput.toUpperCase()) {
                case "A":
                    addingCust(tmpRoomArray);
                    break;
                case "V":
                    viewCust(tmpRoomArray);
                    break;

                case "E":
                    emptyRooms(tmpRoomArray);
                    break;

                case "D":
                    deleteCust(tmpRoomArray);
                    break;

                case "F":
                    findCust(tmpRoomArray);
                    break;

                case "O":
                    orderCust(tmpRoomArray);
                    break;

                case "S":
                    saveFile(tmpRoomArray, path);
                    break;

                case "L":
                    loadFile(tmpRoomArray, path);
                    break;
                case "X":
                    System.out.println("[X] Program has been closed!");
                    inMenu = false;
                    break;
                default:
                    System.out.println("[-] Not an option! Try again...");
                    break;
            }
        }
    }

    private static void addingCust(roomObjects roomRef[]) {
        int tmpRoomNo;
        String tmpRoomName;
        System.out.println("'A' = Add a customer\n");
        System.out.print("What room would you like?: ");
        tmpRoomNo = sc.nextInt();
        if (roomRef[tmpRoomNo].roomName.equals("")) {
            System.out.print("Who will this room be assigned to?: ");
            tmpRoomName = sc.next();
            roomRef[tmpRoomNo].setRoomName(tmpRoomName);
            System.out.println("[+] Room: " + tmpRoomNo + " has now been booked for " + tmpRoomName);
        } else {
            System.out.println("[-] Room number " + tmpRoomNo + " is taken!");
        }
    }

    private static void viewCust(roomObjects roomRef[]) {
        System.out.println("'V' = View all rooms\n");
        for (int i = 0; i < roomRef.length; i++) {
            System.out.println(i + 1 + ") Room Number: " + i + " | Booked By: " + roomRef[i].getRoomName());
        }
    }

    private static void emptyRooms(roomObjects roomRef[]) {
        System.out.println("'E' = Display all empty rooms");
        for (int i = 0; i < roomRef.length; i++) {
            if (roomRef[i].getRoomName().equals("")) {
                System.out.println("Room Number: " + i);
            }
        }
    }

    private static void deleteCust(roomObjects roomRef[]) {
        System.out.println("'D' = Delete customer from room");
        int tmpEmptyNo;
        System.out.print("What Room would your like to empty? ");
        tmpEmptyNo = sc.nextInt();
        roomRef[tmpEmptyNo].setRoomName("");
        System.out.println("[X] Room has been emptied");
    }

    private static void findCust(roomObjects roomRef[]) {
        System.out.println("'F' = Find room from customer name");
        String tmpSearcnName;
        System.out.print("Who would you like to find? ");
        tmpSearcnName = sc.next();
        for (int i = 0; i < roomRef.length; i++) {
            if (roomRef[i].getRoomName().equals(tmpSearcnName)) {
                System.out.println(i + 1 + ") Room Number: " + i + " | Booked By: " + tmpSearcnName);
            } else {
                System.out.println(i + 1 + ") Name was not found!");
            }
        }
    }

    private static void orderCust(roomObjects roomRef[]) {
        System.out.println("'O' = View rooms ordered alphabetically by name\n");
        String[] tmpOrderArray = new String[10];
        int roomNo = 0;
        for (int a = 0; a < roomRef.length; a++) {
            tmpOrderArray[a] = roomRef[a].getRoomName();
        }
        Arrays.sort(tmpOrderArray);
        for (int b = 0; b < tmpOrderArray.length; b++) {
            for (int c = 0; c < roomRef.length; c++) {
                if (tmpOrderArray[b].equals(roomRef[c].getRoomName())) {
                    roomNo = c;
                    break;
                }
            }
            if (!tmpOrderArray[b].equals("")) {
                System.out.println("Room Number: " + roomNo + " | Booked By: " + tmpOrderArray[b]);
            }
        }

    }
}

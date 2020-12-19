package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String[][] tasks;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        WelecomeScreen();
        Options();
        String task = scanner.next();

        switch (task) {

            case "add":
                break;
            case "remove":
                break;
            case "list":
                taskList();
                break;
            case "exit":
                System.out.println(ConsoleColors.RED + "Bye, Bye. See you next time :)");
                break;
            default:
                System.out.println(ConsoleColors.CYAN + "Pleas select a correct option.");
        }
    }


    public static void Options() {

        System.out.println(ConsoleColors.BLUE + "Please select an option :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "1." + ConsoleColors.RESET + " add");
        System.out.println(ConsoleColors.YELLOW + "2." + ConsoleColors.RESET + " remove");
        System.out.println(ConsoleColors.YELLOW + "3." + ConsoleColors.RESET + " list");
        System.out.println(ConsoleColors.YELLOW + "4." + ConsoleColors.RESET + " exit");

    }

    public static void WelecomeScreen() {

        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT);
        System.out.println("Welcome in Task Menager v1.00" + ConsoleColors.RESET);

    }

    public static void taskList(){
        File taskList = new File("tasks.csv");
        try {
            int count = 1;
            Scanner loadTask = new Scanner(taskList);
            System.out.println(ConsoleColors.BLUE + "Tasks:" + "\n" + "Name-Date-Importance");
            while(loadTask.hasNextLine()){
                String line = loadTask.nextLine().replace(","," ");
                System.out.println(ConsoleColors.YELLOW + count + ". " + ConsoleColors.RESET + line);
                count++;
            }
        } catch (FileNotFoundException e) {
            try {
                taskList.createNewFile();
                System.out.println("File not found, so it has been created just fot You.");
            } catch (IOException ex) {
                System.out.println("File can not be created. Try do it manually (TaskMenager/tasks.csv).");
            }
        }
    }
}
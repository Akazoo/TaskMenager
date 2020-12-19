package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String[][] tasks;

    public static void main(String[] args) {
        tasks = List();
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
                System.out.println(Arrays.toString(List()));
                break;
            case "exit":
                System.out.println(ConsoleColors.RED + "Bye, Bye. See you next time");
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

    public static String[][] List() {

        Scanner scanner = new Scanner("tasks.csv");
        String[][] new1;
        File taskList = new File("tasks.csv");
        try {
            Scanner loadTask = new Scanner(taskList);
        } catch (FileNotFoundException e) {
            try {
                taskList.createNewFile();
                System.out.println("Plik nie został znaleziony, więc został dla Ciebie utworzony");
            } catch (IOException ex) {
                System.out.println("Nie udało się stworzyć pliku. Spróbuj stworzyć plik o nazwie tasks.csv w katalogu TaskMenager recznie.");
            }
        }


        return new1;
    }
}
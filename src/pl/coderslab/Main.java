package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String[][] tasks;

    public static void main(String[] args) {

        csvToArray();
        Scanner scanner = new Scanner(System.in);

        WelecomeScreen();
        Options();

        System.out.println(ConsoleColors.BLUE + "Please enter \"help\" to show options again.");
        while (scanner.hasNextLine()) {

            String task = scanner.next();

            switch (task) {

                case "add":
                    addTask();
                    break;
                case "remove":
                    deleteTask();
                    break;
                case "list":
                    tasksListArray();
                    break;
                case "exit":
                    System.out.println(ConsoleColors.RED + "Bye, Bye. See you next time :)");
                    save();
                    System.exit(0);
                    break;
                case "help":
                    Options();
                    break;
                default:
                    System.out.println(ConsoleColors.CYAN + "Pleas select a correct option.");
            }
        }
    }

    public static void Options() {

        System.out.println(ConsoleColors.BLUE + "Please select an option :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "1." + ConsoleColors.RESET + " add");
        System.out.println(ConsoleColors.YELLOW + "2." + ConsoleColors.RESET + " remove");
        System.out.println(ConsoleColors.YELLOW + "3." + ConsoleColors.RESET + " list");
        System.out.println(ConsoleColors.YELLOW + "4." + ConsoleColors.RESET + " exit");

    } //ok

    public static void WelecomeScreen() {

        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT);
        System.out.println("Welcome in Task Menager v1.00" + ConsoleColors.RESET);

    } //ok

    public static void taskList() {
        File taskList = new File("tasks.csv");
        try {
            int count = 0;
            Scanner loadTask = new Scanner(taskList);
            System.out.println(ConsoleColors.BLUE + "Tasks:" + "\n" + "Name-Date-Importance");
            while (loadTask.hasNextLine()) {
                String line = loadTask.nextLine().replace(",", " ");
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
    } ///// delete

    public static void addTask() {

        Scanner scanner = new Scanner(System.in);
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        System.out.println(ConsoleColors.BLUE + "Please add task description:");
        String answer1 = scanner.nextLine();
        tasks[tasks.length - 1][0] = answer1;
        System.out.println("Please add task due date: (YYYY-MM-DD)");
        String answer2 = scanner.nextLine();
        tasks[tasks.length - 1][1] =  " " + answer2;
        System.out.println("Is your task important? (true/false)");
        String answer3 = scanner.nextLine();
        tasks[tasks.length - 1][2] = " " + answer3;
        System.out.println("Task successfully added.");

    } // ok

    public static void arrayRefresh() {
        /*String[][] tasks2 = new String[0][];
        File file = new File("tasks.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                tasks2 = Arrays.copyOf(tasks2, tasks2.length + 1);
                tasks2[tasks2.length - 1] = nextLine.split(",");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            tasks = tasks2;*/
        File file = new File("tasks.csv");
        int length1 = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                length1++;
            }
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
                System.out.println("File not found, so it has been created just fot You.");
            } catch (IOException ex) {
                System.out.println("File can not be created. Try do it manually (TaskMenager/tasks.csv).");
            }
        }

        tasks = new String[length1][];
        /*for (int i = 0; i < length1; i++) {
            try {
                Scanner scanner = new Scanner(file);
                tasks[i] = scanner.nextLine().split(",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        try {
            int i = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tasks[i] = scanner.nextLine().split(",");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } ///// delete

    public static void deleteTask() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select Task number to remove.");
        boolean correction = true;
        int number;
        while (correction) {
            try {
                while (!scanner.hasNextInt()) {
                    System.out.println(ConsoleColors.BLUE + "Please enter number. Go ahead and try again.");
                    scanner.nextLine();
                }
                number = scanner.nextInt();
                tasks = ArrayUtils.remove(tasks, number);
                System.out.println(ConsoleColors.BLUE + "Selected Task successfully removed.");
                correction = false;

            } catch (IndexOutOfBoundsException e) {
                System.out.println(ConsoleColors.BLUE + "There is no Task connected to this number. Your task are from 0 to " + (tasks.length - 1) + ". Try again.");
            }

        }
    } // !work

    public static void tasksListArray() {
        System.out.println(ConsoleColors.BLUE + "Tasks:" + "\n" + "Name-Date-Importance");
        int count= 0;
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(ConsoleColors.YELLOW + count + ". " + ConsoleColors.RESET);
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j]);
            }
            count++;
            System.out.println();
        }
    } // ok

    public static void save() {

        Path path1 = Paths.get("tasks.csv");
        List<String> outList = new ArrayList<>();

        for (int i = 0; i < tasks.length; i++) {
            String input = String.join(",", tasks[i]);
            //for (int j = 0; j < tasks[i].length; j++) {
                ////input += tasks[i][j] + ",";
            //}
            outList.add(input);
        }
        try {
            Files.write(path1, outList);
        } catch (IOException ex) {
            System.out.println("Nie można zapisać pliku.");
        }
    } // ok/!

    public static void csvToArray() {
        Path path = Paths.get("tasks.csv");
        int length1 = 0;
        File file = new File("tasks.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                length1++;
            }
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
                System.out.println("File not found, so it has been created just fot You.");
            } catch (IOException ex) {
                System.out.println("File can not be created. Try do it manually (TaskMenager/tasks.csv).");
            }
        }

        tasks = new String[length1][];
        try {
            int k =0;
            for (String line : Files.readAllLines(path)) {
                tasks[k]= line.split(",");
                System.out.println(line);
                k++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //ok
}
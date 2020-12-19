package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String[][] tasks;

    public static void main(String[] args) {

        arrayRefresh();
        Scanner scanner = new Scanner(System.in);

        WelecomeScreen();
        Options();

        String task = scanner.next();

        switch (task) {

            case "add":
                arrayRefresh();
                addTask();
                System.out.println(tasks[3][2]);
                break;
            case "remove":
                deleteTask();
                arrayRefresh();
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

    public static void addTask() {

        Scanner scanner = new Scanner(System.in);
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length-1] = new String[3];
        System.out.println(ConsoleColors.BLUE + "Please add task description:");
        String answer1 = scanner.nextLine();
        tasks[tasks.length-1][0] = answer1;
        System.out.println("Please add task due date:");
        String answer2 = scanner.nextLine();
        tasks[tasks.length-1][1] = answer2;
        System.out.println("Is your task important? (true/false)");
        String answer3 = scanner.nextLine();
        tasks[tasks.length-1][2] = answer3;

    }

    public static void arrayRefresh() {
        // tylko return String[][]
            /*String[][] tasks2 = new String[0][];
            File file = new File("tasks.csv");
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    tasks2 = Arrays.copyOf(tasks2, tasks2.length + 1);
                    tasks2[tasks2.length - 1] = nextLine.split(",");
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            return tasks2;
            */
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
        for (int i = 0; i < length1; i++) {
            try {
                Scanner scanner = new Scanner(file);
                tasks[i] = scanner.nextLine().split(",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteTask(){

        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select Task number to remove.");
        int i = scanner.nextInt();
        while


        ArrayUtils.remove(tasks,entry);
        }
}
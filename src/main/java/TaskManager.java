import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tasks = readFromFile();
        String option = "";

        while(!option.equals("exit")) {
            System.out.println(ConsoleColors.GREEN_BOLD + "\nPlease select an option:" + ConsoleColors.RESET);
            System.out.println("add\n" +
                    "remove\n" +
                    "list\n" +
                    ConsoleColors.BLUE + "exit\n" + ConsoleColors.RESET);

            option = scanner.nextLine();
            switch (option) {
                case "add":
                    add(scanner);
                    break;
                case "remove":
                    remove(scanner);
                    break;
                case "list":
                    list();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD + "Invalid option. " + ConsoleColors.RESET + "Please try again.");
                    break;
            }
        }
    }

    public static String[][] readFromFile(){
        tasks = new String[0][];
        File file = new File("tasks.csv");

        try(Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                tasks = addNewRow(tasks, data);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public static void add(Scanner scanner2){
        String[] newTask = new String[3];
        System.out.println("Please add task description");
        newTask[0] = scanner2.nextLine();
        System.out.println("Please add task due date");
        newTask[1] = scanner2.nextLine();
        System.out.println("Is your task important: " + ConsoleColors.RED + "true" + ConsoleColors.RESET + "/" + ConsoleColors.RED + "false");
        while (!scanner2.hasNextBoolean()) {
            System.out.println(ConsoleColors.RED_BOLD + "Invalid value. " + ConsoleColors.RESET + "Please enter either " + ConsoleColors.RED + "true" + ConsoleColors.RESET + " or " + ConsoleColors.RED + "false" );
            scanner2.nextLine();
        }
        newTask[2] = scanner2.nextLine();
        tasks = addNewRow(tasks,newTask);
    }

    public static void remove(Scanner scanner3){
        System.out.println("Please select number to remove.");
        int number = scanner3.nextInt();
        scanner3.nextLine();
        while(number < 0 || number > tasks.length-1){
            System.out.println(ConsoleColors.RED_BOLD + "Invalid value. " + ConsoleColors.RESET + "Please select number between 0 and " + (tasks.length-1));
            scanner3.nextLine();
            number = scanner3.nextInt();
            scanner3.nextLine();
        }
        String[][] newTasks = new String[0][];
        for(int i=0; i < tasks.length; i++) {
            if(i != number) {
                newTasks = addNewRow(newTasks,tasks[i]);
            }
        }
        tasks = newTasks;
        System.out.println("Value was successfully deleted.");
    }

    public static void list(){
        System.out.println("list");
        if(tasks.length == 0){
            System.out.println("Your to-do list is empty.");
        } else {
            for(int i=0; i < tasks.length; i++){
                System.out.println(i + " : " + tasks[i][0] + "\t" +
                        tasks[i][1] + "\t" + ConsoleColors.RED +
                        tasks[i][2] + ConsoleColors.RESET);
            }
        }
    }

    public static void exit(){
        try(FileWriter fileWriter = new FileWriter("tasks.csv")){
            if(tasks.length==0) {
                fileWriter.append("");
            } else {
                for(int i = 0; i < tasks.length; i++) {
                    fileWriter.append(StringUtils.join(tasks[i],","));
                    fileWriter.append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] addNewRow(String[][] mArray, String[] row){
        mArray = Arrays.copyOf(mArray, mArray.length + 1);
        mArray[mArray.length-1] = row;
        return mArray;
    }

}

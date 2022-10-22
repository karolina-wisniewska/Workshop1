import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;
    private static final String EXIT = "exit";
    private static final String ADD = "add";
    private static final String REMOVE = "remove";
    private static final String LIST = "list";
    private static final String FILENAME = "tasks.csv";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        readFromFile();
        String option = "";

        while(!option.equals(EXIT)) {
            printMenu();
            option = scanner.nextLine();
            switch (option) {
                case ADD:
                    add(scanner);
                    break;
                case REMOVE:
                    remove(scanner);
                    break;
                case LIST:
                    list();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD + "Invalid option. " + ConsoleColors.RESET + "Please try again.");
                    break;
            }
        }
    }

    public static void readFromFile(){
        tasks = new String[0][];
        File file = new File(FILENAME);

        try(Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                tasks = addNewRow(tasks, data);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printMenu(){
        System.out.println(ConsoleColors.GREEN_BOLD + "\nPlease select an option:");
        System.out.print(ConsoleColors.RESET);
        System.out.println(ADD);
        System.out.println(REMOVE);
        System.out.println(LIST);
        System.out.print(ConsoleColors.BLUE);
        System.out.println(EXIT);
        System.out.println(ConsoleColors.RESET);
    }

    public static void add(Scanner scanner){
        String[] newTask = new String[3];
        System.out.println("Please add task description");
        newTask[0] = scanner.nextLine();
        System.out.println("Please add task due date");
        newTask[1] = scanner.nextLine();
        System.out.println("Is your task important: " + ConsoleColors.RED + "true" + ConsoleColors.RESET + "/" + ConsoleColors.RED + "false");
        while (!scanner.hasNextBoolean()) {
            System.out.println(ConsoleColors.RED_BOLD + "Invalid value. " + ConsoleColors.RESET + "Please enter either " + ConsoleColors.RED + "true" + ConsoleColors.RESET + " or " + ConsoleColors.RED + "false" );
            scanner.nextLine();
        }
        newTask[2] = scanner.nextLine();
        tasks = addNewRow(tasks,newTask);
    }

    public static void remove(Scanner scanner){
        System.out.println("Please select number to remove.");
        int number = getNumberFromUser(scanner);
        while(number < 0 || number > tasks.length-1){
            System.out.println(ConsoleColors.RED_BOLD + "Number out of bounds: " + number + ConsoleColors.RESET + " Please select number between 0 and " + (tasks.length-1));
            number = getNumberFromUser(scanner);
        }
        tasks = ArrayUtils.remove(tasks,number);
        System.out.println("Value was successfully deleted.");
    }

    public static int getNumberFromUser(Scanner scanner){
        while(!scanner.hasNextInt()){
            System.out.println(ConsoleColors.RED_BOLD + "Invalid value. " + ConsoleColors.RESET + "Please enter a number.");
            scanner.nextLine();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static void list(){
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
        try(FileWriter fileWriter = new FileWriter(FILENAME)){
            for(int i = 0; i < tasks.length; i++) {
                fileWriter.append(StringUtils.join(tasks[i],","));
                fileWriter.append("\n");
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

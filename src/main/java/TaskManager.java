import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tasks = readFromFile();
        String option = "";

        while(!option.equals("exit")) {
            printMenu();
            option = scanner.nextLine();
            switch (option) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "list":
                    list();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
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
            e.printStackTrace();
        }

        return tasks;
    }
    public static void printMenu() {
        System.out.println(ConsoleColors.GREEN_BOLD + "\nPlease select an option:");
        System.out.println(ConsoleColors.RESET + "add\n" +
                "remove\n" +
                "list\n" +
                "exit\n");
    }



    public static String[][] add(){
        Scanner scanner2 = new Scanner(System.in);
        String[] newTask = new String[3];
        System.out.println("Please add task description");
        newTask[0] = scanner2.nextLine();
        System.out.println("Please add task due date");
        newTask[1] = scanner2.nextLine();
        System.out.println("Is your task important: " + ConsoleColors.RED + "true" + ConsoleColors.RESET + "/" + ConsoleColors.RED + "false");
        while (!scanner2.hasNextBoolean()) {
            System.out.println(ConsoleColors.RED_BOLD + "Invalid value. Please enter boolean: " + ConsoleColors.RED + "true" + ConsoleColors.RESET + "/" + ConsoleColors.RED + "false" );
            scanner2.nextLine();
        }
        newTask[2] = scanner2.nextLine();
        scanner2.close();
        System.out.println(ConsoleColors.RESET);
        tasks = addNewRow(tasks,newTask);
        return tasks;
    }

    public static void remove(){

        System.out.println("Option remove executing...");
    }

    public static void list(){
        System.out.println("list");
        for(int i=0; i < tasks.length; i++){
            System.out.println(i + " : " + tasks[i][0] + "\t" +
                    tasks[i][1] + "\t" + ConsoleColors.RED +
                    tasks[i][2] + ConsoleColors.RESET);
        }

    }

    public static void exit(){
        System.out.println("Option exit executing...");

    }



    public static String[][] addNewRow(String[][] multiarray, String[] row){
        multiarray = Arrays.copyOf(multiarray, multiarray.length + 1);
        multiarray[multiarray.length-1] = row;
        return multiarray;
    }




}

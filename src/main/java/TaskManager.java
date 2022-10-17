import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
    }
    public static void printMenu() {
        System.out.println(ConsoleColors.GREEN_BOLD + "\nPlease select an option:");
        System.out.println(ConsoleColors.RESET + "add\n" +
                "remove\n" +
                "list\n" +
                "exit");
    }

    public static void add(){
        System.out.println("Option add executing...");
    }

    public static void remove(){
        System.out.println("Option remove executing...");
    }

    public static void list(){
        System.out.println("list");
        String[][] tasks = readFromFile();
        for(int i=0; i<tasks.length; i++){
            System.out.println(i + " : " + tasks[i][0] + " " +
                    tasks[i][1] + " " + ConsoleColors.RED +
                    tasks[i][2] + ConsoleColors.RESET);
        }

    }

    public static void exit(){
        System.out.println("Option exit executing...");

    }

    public static String[][] readFromFile(){
        String[][] uploadedTasks = new String[0][];
        File file = new File("tasks.csv");

        try(Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                uploadedTasks = addNewRow(uploadedTasks, data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return uploadedTasks;
    }

    public static String[][] addNewRow(String[][] multiarray, String[] row){
        multiarray = Arrays.copyOf(multiarray, multiarray.length + 1);
        multiarray[multiarray.length-1] = row;
        return multiarray;
    }




}

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
        System.out.println("Option list executing...");
    }

    public static void exit(){
        System.out.println("Option exit executing...");
    }
}

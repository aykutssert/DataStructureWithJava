import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


/**
 * Main class to run the social network analysis program
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in); // Scanner object to read user input
    private static final SocialNetwork network = new SocialNetwork(); // SocialNetwork object to perform operations

    /**
     * Main method to run the social network analysis program
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        
        while (true) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addPerson(); // Add a person to the network
                    break; 
                case "2":
                    removePerson(); // Remove a person from the network
                    break;
                case "3":
                    addFriendship(); // Add a friendship between two people
                    break;
                case "4":
                    removeFriendship(); // Remove a friendship between two people
                    break;
                case "5":
                    findShortestPath(); // Find the shortest path between two people
                    break;
                case "6":
                    suggestFriends(); // Suggest friends for a person
                    break; 
                case "7":
                    countClusters(); // Count clusters in the network
                    break;
                case "8":
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to add a person to the network
     */
    private static void addPerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        int age;
        try{
        System.out.print("Enter age: ");
        age = Integer.parseInt(scanner.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Invalid age. Please try again.");
            return;
        }
        System.out.print("Enter hobbies (comma-separated): ");
        List<String> hobbies = Arrays.asList(scanner.nextLine().trim().split(","));
        network.addPerson(name, age, hobbies);
    }

    /**
     * Method to remove a person from the network
     */
    private static void removePerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter person’s timestamp: ");
        String timestamp1 = scanner.nextLine().trim();
        network.removePerson(name, timestamp1);
    }

    /**
     * Method to add a friendship between two people
     */
    private static void addFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Enter first person’s timestamp: ");
        String timestamp1 = scanner.nextLine().trim();
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine().trim();
        System.out.print("Enter second person’s timestamp: ");
        String timestamp2 = scanner.nextLine().trim();
        network.addFriendship(name1, name2);
    }

    /**
     * Method to remove a friendship between two people
     */
    private static void removeFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Enter first person’s timestamp: ");
        String timestamp1 = scanner.nextLine().trim();
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine().trim();
        System.out.print("Enter second person’s timestamp: ");
        String timestamp2 = scanner.nextLine().trim();
        network.removeFriendship(name1, name2);
    }

    /**
     * Method to find the shortest path between two people
     */
    private static void findShortestPath() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Enter first person’s timestamp: ");
        String timestamp1 = scanner.nextLine().trim();
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine().trim();
        System.out.print("Enter second person’s timestamp: ");
        String timestamp2 = scanner.nextLine().trim();
        network.findShortestPath(name1, name2);
    }

    /**
     * Method to suggest friends for a person
     */
    private static void suggestFriends() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter person’s timestamp: ");
        String timestamp1 = scanner.nextLine().trim();
        System.out.print("Enter maximum number of friends to suggest: ");
        int maxSuggestions = Integer.parseInt(scanner.nextLine().trim());
        network.suggestFriends(name, maxSuggestions);
    }

    /**
     * Method to count clusters in the network
     */
    private static void countClusters() {
        network.countClusters();
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to generate a random command file for the StockDataManager
 */
public class RandomGenerator {

    private static final String[] COMMANDS = {"REMOVE", "UPDATE", "ADD", "SEARCH"}; // List of possible commands
    private static final int MAX_SYMBOL_LENGTH = 5; // Length of the randomly generated symbols
    private static final int MIN_SYMBOL_LENGTH = 3; // Length of the randomly generated symbols
    private static final Random RANDOM = new Random(); // Random number generator
    private static final List<String> symbols = new ArrayList<>(); // List of existing symbols
    private final int commandSize;

    /**
     * Constructor to initialize the command size
     */
    public RandomGenerator() {
        this.commandSize = 100 + RANDOM.nextInt(901);
    }

    /**
     * Generates a random command file with a specified number of commands
     */
    public void Generate(){
        String outputFile = "random.txt";
        int numberOfCommands = commandSize;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < numberOfCommands; i++) {
                String command = generateRandomCommand();
                writer.write(command);
                writer.newLine();
            }
            System.out.println("File generated successfully with " + numberOfCommands + " commands.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Generates a random command based on the predefined command types
     * @return A randomly generated command
     */
    private  String generateRandomCommand() {
        String commandType = COMMANDS[RANDOM.nextInt(COMMANDS.length)];

        switch (commandType) {
            case "REMOVE":
                return generateRemoveCommand();
            case "UPDATE":
                return generateUpdateCommand();
            case "ADD":
                return generateAddCommand();
            case "SEARCH":
                return generateSearchCommand();
            default:
                throw new IllegalArgumentException("Invalid command type");
        }
    }

    /**
     * Generates a random REMOVE command
     * @return  A randomly generated REMOVE command
     */
    private  String generateRemoveCommand() {
        String symbol = getRandomExistingSymbol();
        return "REMOVE " + symbol;
    }

    /**
     * Generates a random UPDATE command
     * @return A randomly generated UPDATE command
     */
    private  String generateUpdateCommand() {
        String symbol = getRandomExistingSymbol();
        String newSymbol = generateRandomSymbol();
        double price = 100 + (RANDOM.nextDouble() * 900);
        long volume = 1000 + (long) (RANDOM.nextDouble() * 999000);
        long timestamp = 1000000 + (long) (RANDOM.nextDouble() * 999900000);
        return String.format("UPDATE %s %s %.2f %d %d", symbol, newSymbol, price, volume, timestamp);
    }

    /**
     * Generates a random ADD command
     * @return A randomly generated ADD command
     */
    private  String generateAddCommand() {
        String symbol = generateRandomSymbol();
        symbols.add(symbol); // Add the new symbol to the list
        double price = 100 + (RANDOM.nextDouble() * 900);
        long volume = 1000 + (long) (RANDOM.nextDouble() * 999000);
        long timestamp = 1000000 + (long) (RANDOM.nextDouble() * 999900000);
        return String.format("ADD %s %.2f %d %d", symbol, price, volume, timestamp);
    }

    /**
     * Generates a random SEARCH command
     * @return A randomly generated SEARCH command
     */
    private  String generateSearchCommand() {
        String symbol = getRandomExistingSymbol();
        return "SEARCH " + symbol;
    }

    /**
     * Returns a random existing symbol from the list of symbols
     * @return A random existing symbol
     */
    private  String getRandomExistingSymbol() {
        if (symbols.isEmpty() || RANDOM.nextDouble() < 0.3) { // 10% chance to generate a new symbol
            return generateRandomSymbol();
        } else {
            return symbols.get(RANDOM.nextInt(symbols.size()));
        }
    }

    /**
     * Generates a random symbol with a random length between MIN_SYMBOL_LENGTH and MAX_SYMBOL_LENGTH
     * @return A randomly generated symbol
     */
    private  String generateRandomSymbol() {
        int symbolLength = MIN_SYMBOL_LENGTH + RANDOM.nextInt(MAX_SYMBOL_LENGTH - MIN_SYMBOL_LENGTH + 1);
        StringBuilder symbol = new StringBuilder(symbolLength);
        for (int i = 0; i < symbolLength; i++) {
            char letter = (char) ('A' + RANDOM.nextInt(26));
            symbol.append(letter);
        }
        return symbol.toString();
    }
}

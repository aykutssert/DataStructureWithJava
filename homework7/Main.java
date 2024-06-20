import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Integer> dataPointsX = new ArrayList<>(); // X-axis data points
    private static List<Long> addTimes = new ArrayList<>(); // Y-axis data points for add operation
    private static List<Long> removeTimes = new ArrayList<>(); // Y-axis data points for remove operation
    private static List<Long> searchTimes = new ArrayList<>(); // Y-axis data points for search operation

    public static void main(String[] args) {
      
        RandomGenerator randomGenerator = new RandomGenerator(); // Create a new instance of RandomGenerator
        randomGenerator.Generate(); // Generate a random command file
        String inputFileString = "random.txt"; // Path to the generated command file
        StockDataManager managerMain = new StockDataManager(); // Create a new instance of StockDataManager
        StockDataManager manager[] = new StockDataManager[10]; // Array of StockDataManager instances

        // Initialize each StockDataManager in the array
        for (int i = 0; i < 10; i++) {
            manager[i] = new StockDataManager();
        }

        // Read the command file and process each command
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileString))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, managerMain);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate data points for the X-axis
        for(int i=1;i<=10;i++){
            dataPointsX.add(i*1000);
        }
        
        // Perform performance analysis for each StockDataManager instance
        for(int i = 1; i <= 10; i++){
            myPerformanceAnalysis(manager[i-1],i*1000,addTimes,removeTimes,searchTimes);
        }
        
        String ploString = "scatter";
        // Display the performance analysis results using a GUI
        GUIVisualization frame = new GUIVisualization(ploString,dataPointsX,addTimes,"ADD");
        frame.setVisible(true);
        GUIVisualization frame2 = new GUIVisualization(ploString,dataPointsX,searchTimes,"SEARCH");
        frame2.setVisible(true);
        GUIVisualization frame3 = new GUIVisualization(ploString,dataPointsX,removeTimes,"REMOVE");
        frame3.setVisible(true);
       
    }
    private static void myPerformanceAnalysis(StockDataManager manager,int size,List<Long> addTimes,List<Long> removeTimes,List<Long> searchTimes){
            if(size == 1000){
                int warmUpSize = size/10 ;

                // Warm-up phase
                for (int i = 0; i < warmUpSize; i++) {
                    manager.addOrUpdateStock("WARM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                }
                manager.clear();
            }

            long startTime, endTime; // Variables to store the start and end time of each operation

            //ADD OPERATION
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
            endTime = System.nanoTime();
            addTimes.add((endTime - startTime) / size); // Calculate the average time taken for each add operation
            //ADD OPERATION


            //SEARCH OPERATION
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.searchStock("SYM" + i);
            }
            endTime = System.nanoTime();
            searchTimes.add((endTime - startTime) / size); // Calculate the average time taken for each search operation
            //SEARCH OPERATION

            //REMOVE OPERATION
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.removeStock("SYM" + i);
            }
            endTime = System.nanoTime();
            removeTimes.add((endTime - startTime) / size); // Calculate the average time taken for each remove operation
            //REMOVE OPERATION

    }
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" "); // Split the line into tokens
        String command = tokens[0]; // Extract the command

        switch (command) {
            case "ADD": // ADD <symbol> <price> <quantity> <volume>
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE": // REMOVE <symbol>
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH": // SEARCH <symbol>
                
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found:" + tokens[1]);
                }
                break;
            case "UPDATE": // UPDATE <symbol> <price> <quantity> <volume>
                // Update the stock with the given symbol
                manager.updateStock(tokens[1],tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command); // Unknown command
                break;
        }
    }
    
}

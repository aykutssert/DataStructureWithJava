import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Class to visualize the performance analysis data using a GUI
 */
public class GUIVisualization extends JFrame {
    private List<Integer> dataPointsX; // List to store x-axis data points
    private List<Long> dataPointsY; // List to store y-axis data points
    private String plotType; // Type of plot ("line" or "scatter")
    private String operation; // Type of operation ("add", "remove", or "search")

    /**
     * Constructor to initialize the GUIVisualization object
     * @param plotType Type of plot ("line" or "scatter")
     * @param dataPointsX List of x-axis data points
     * @param dataPointsY List of y-axis data points
     * @param operation Type of operation ("add", "remove", or "search")
     */
    public GUIVisualization(String plotType, List<Integer> dataPointsX, List<Long> dataPointsY,String operation) {
        this.plotType = plotType; // Set the plot type
        this.dataPointsX = dataPointsX;// Initialize x-axis data points list
        this.dataPointsY = dataPointsY; // Initialize y-axis data points list
        this.operation = operation; // Set the operation type
       
        setTitle(operation); // Set the title of the window
        setSize(800, 600); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        setLocationRelativeTo(null); // Center the window on the screen
    }
    /**
     * Method to paint the graph
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass's paint method
        drawGraph(g); // Draw the graph
    }

    /**
     * Method to draw the graph
     * @param g Graphics object
     */
    private void drawGraph(Graphics g) {
        int width = getWidth(); // Get the width of the window
        int height = getHeight(); // Get the height of the window
        int padding = 50; // Padding around the graph
        int labelPadding = 20; // Padding for labels

        Graphics2D g2 = (Graphics2D) g; // Cast Graphics to Graphics2D for better rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing

        // Draw white background for the graph
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // Create hatch marks and grid lines for y axis.
        int numberYDivisions = 10; // Number of divisions for the y-axis
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = width - padding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding);
            int y1 = y0;
            if (dataPointsY.size() > 0) {
                g2.setColor(Color.LIGHT_GRAY); // Set color for grid lines
                g2.drawLine(padding + labelPadding + 1 + labelPadding, y0, x1, y1); // Draw grid line
                g2.setColor(Color.BLACK); // Set color for labels
                String yLabel = ((int) ((getMaxYValue() * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + ""; // Generate y-axis label
                FontMetrics metrics = g2.getFontMetrics(); // Get font metrics for label width
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3); // Draw y-axis label
            }
        }

        // Create hatch marks and grid lines for x axis.
        for (int i = 0; i < dataPointsX.size(); i++) {
            if (dataPointsX.size() > 1) {
                int x0 = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = height - padding - labelPadding;
                int y1 = y0 - 4;
                if ((i % ((int) ((dataPointsX.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(Color.LIGHT_GRAY); // Set color for grid lines
                    g2.drawLine(x0, height - padding - labelPadding - 1 - labelPadding, x1, padding); // Draw grid line
                    g2.setColor(Color.BLACK); // Set color for labels
                    String xLabel = dataPointsX.get(i) + ""; // Generate x-axis label
                    FontMetrics metrics = g2.getFontMetrics(); // Get font metrics for label width
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3); // Draw x-axis label
                }
                g2.drawLine(x0, y0, x1, y1); // Draw x-axis hatch mark
            }
        }

        // Draw axis lines.
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding); // y-axis
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding); // x-axis

        // Draw the actual graph.
        Stroke oldStroke = g2.getStroke();
        g2.setColor(Color.BLUE); // Set color for the graph
        g2.setStroke(new BasicStroke(2f)); // Set stroke for the graph

        if (plotType.equals("line")) {
            for (int i = 0; i < dataPointsX.size() - 1; i++) {
                int x1 = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y1 = height - padding - labelPadding - (int) ((dataPointsY.get(i) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                int x2 = (i + 1) * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y2 = height - padding - labelPadding - (int) ((dataPointsY.get(i + 1) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                g2.drawLine(x1, y1, x2, y2); // Draw line between data points
            }
        } else if (plotType.equals("scatter")) {
            for (int i = 0; i < dataPointsX.size(); i++) {
                int x = i * (width - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y = height - padding - labelPadding - (int) ((dataPointsY.get(i) * 1.0) / getMaxYValue() * (height - padding * 2 - labelPadding));
                g2.fillOval(x - 3, y - 3, 6, 6); // Draw data point as a small circle
            }
        }

        g2.setStroke(oldStroke); // Restore original stroke
    }

   
    /**
     * Method to get the maximum y value from the data points
     * @return long maximum y value
     */
    private long getMaxYValue() {
        long max = Long.MIN_VALUE; // Initialize max value to minimum possible value
        for (Long y : dataPointsY) {
            max = Math.max(max, y); // Find maximum y value
        }
        return max; // Return maximum y value
    }

    
}

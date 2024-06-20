public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
    	// fill this method
        int n = arr.length; // Array size
        
        boolean swapped; // Flag to check if any element is swapped
        
        for(int i = 0; i < n-1; i++){ // Traverse the array
            swapped = false; // Initialize the flag
            for(int j = 0; j < n-i-1; j++){ // Traverse the array
                comparison_counter++; // Increment the comparison counter
                if(arr[j] > arr[j+1]){ // Compare the elements
                    swap(j, j+1); // Call the swap method (inherited from the parent class)

                    swapped = true; // Set the flag
                }
            }
            if (!swapped) // Eğer hiç eleman yer değiştirmemiş ise döngüden çıkmamız laızm.
                break;
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t"); // Print the algorithm name
    	super.print(); // Call the print method
    }
}

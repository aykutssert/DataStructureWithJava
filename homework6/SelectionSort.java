public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        // fill this method
        int n = arr.length; // Array size
        for(int i = 0; i < n-1; i++){ // Traverse the array
            int minIndex = i; // Initialize the minimum index
            for(int j = i+1; j < n; j++){ // Traverse the array
                if(arr[j] < arr[minIndex]){ // Compare the elements
                    minIndex = j; // Update the minimum index
                    comparison_counter++; // Increment the comparison counter
                }
                else{
                    comparison_counter++; // Increment the comparison counter
                
                }
            }
            swap(minIndex, i); // Call the swap method (inherited from the parent class
           
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t"); // Print the algorithm name
    	super.print(); // Call the print method
    }
}

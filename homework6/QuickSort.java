public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array); // Call the constructor of the parent class
	}
	
    private int partition(int arr[], int low, int high){
        // fill this method
        int pivot = arr[high]; // pivot element
        int i = low-1; // index of smaller element
        for(int j = low; j < high; j++){ // Traverse the array
            if(arr[j] < pivot){ // Compare the elements
                i++; // Increment the index
                swap(i, j); // Call the swap method (inherited from the parent class)
                
            }
            comparison_counter++; // Increment the comparison counter
        } 
        swap(i+1, high); // Call the swap method (inherited from the parent class)
        
        return i+1; // Return the index
    }

    private void sort(int arr[], int low, int high){
        // fill this method
        if(low < high){ // Check if there are more than 1 element
            int pi = partition(arr, low, high); // Find the partitioning index
            sort(arr, low, pi-1); // Sort the left half
            sort(arr, pi+1, high); // Sort the right half
        }
    }

    @Override
    public void sort() {
    	sort(arr, 0, arr.length-1); // Call the sort method
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t"); // Print the algorithm name
     	super.print();// Call the print method
    }
}

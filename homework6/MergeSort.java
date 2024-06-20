public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array); // Call the constructor of the parent class
	}
	
	private void merge(int arr[], int l, int m, int r){
        // fill this method
        int n1 = m - l + 1; // left array size
        int n2 = r - m; // right array size
    
        // temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];
    
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
    
        // Merge the temp arrays
        int i = 0, j = 0;
    
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) { // Merge the temp arrays
            if (L[i] <= R[j]) {  // Compare the elements
                arr[k] = L[i]; // Place the smaller element
                i++; // Increment the index
            } else {
                arr[k] = R[j]; // Place the smaller element
                j++; // Increment the index
            }
            k++; // Increment the index
            comparison_counter++; // Increment the comparison counter
        }
    
        // Copy the remaining elements of L[], if there are any
        while (i < n1) {
            arr[k] = L[i]; // Place the remaining elements
            i++; // Increment the index
            k++; // Increment the index
        }
        while (j < n2) { // Copy the remaining elements of R[], if there are any
            arr[k] = R[j]; // Place the remaining elements
            j++;
            k++;
        }
    }

    private void sort(int arr[], int l, int r){
        // fill this method
        if(l < r){ // Check if there are more than 1 element
            int m = (l+r)/2; // Find the middle element
            sort(arr, l, m); // Sort the left half
            sort(arr, m+1, r); // Sort the right half
            merge(arr, l, m, r); // Merge the sorted halves
        }
    }
    
    @Override
    public void sort() {
    	sort(arr, 0, arr.length-1); // Call the sort method
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t"); // Print the algorithm name
    	super.print(); // Call the print method of the parent class
    }
}

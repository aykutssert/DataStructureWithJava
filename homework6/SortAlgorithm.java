public class SortAlgorithm {
	static int arr[]; // Array to be sorted
	static int comparison_counter; // Comparison counter
	static int swap_counter; // Swap counter

	public SortAlgorithm(int input_array[]) {
		arr = input_array.clone();  // Clone the input array
		comparison_counter = 0; // Initialize the comparison counter
		swap_counter = 0; // Initialize the swap counter
	}
	
	public void sort() {
		// this method should be empty.
	}
	
	protected static void swap(int index_1, int index_2) {
		int temp = arr[index_1]; // Swap the elements
		arr[index_1] = arr[index_2]; // Swap the elements
		arr[index_2] = temp; // Swap the elements
		swap_counter += 1; // Increment the swap counter
	}
	
	public void print() {
		System.out.print("Comparison Counter: " + comparison_counter);
		System.out.print("   \t Swap Counter: " + swap_counter);
		System.out.print("   \t Sorted Array: ");
		for(int e: arr)
			System.out.print(e + " ");
		System.out.println();		
	}
	
	public void sort_and_print() {
		sort();
		print();
	}
}
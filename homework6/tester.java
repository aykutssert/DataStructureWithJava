public class tester {

	public static void main(String[] args) {
		int my_array[] = {1,2,3,4,5,6,7,8}; // this array will be edited by TA during assessment...
		
		System.out.print("Initial Array: ");
		for(int e: my_array)
			System.out.print(e + " ");
		System.out.println("\n");		
		
		SelectionSort ss = new SelectionSort(my_array);
		ss.sort_and_print();
		BubbleSort bs = new BubbleSort(my_array);
		bs.sort_and_print();
		QuickSort qs = new QuickSort(my_array);
		qs.sort_and_print();
		MergeSort ms = new MergeSort(my_array);
		ms.sort_and_print();
	}
}
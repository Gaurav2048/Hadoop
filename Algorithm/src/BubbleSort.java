
public class BubbleSort {
// Bubble sort takes two adjesent element in an array and swaps it if they are not ascendingly arranged. It is continued until there is no swipe happening. 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("=============  Bubble sort ================"); 
		int [] array = new int[] {11, 23,66,32,54,58,23,7,4,3}; 
		
		boolean isSwapHappening = true; 
		
		while(isSwapHappening == true) {
			isSwapHappening = false; 
			for(int i=0;i<array.length-1; i++) {
				if(array[i]>array[i+1]) {
					array = swap(array, i, i+1);
					isSwapHappening  = true; 
				}
			}
		}
		System.out.println(" \n Result of the progeam : ");
		for(int i = 0 ; i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		

	}
	
	public static int [] swap(int[]data_stuff, int swap1, int swap2) {
		int element = data_stuff[swap2]; 
		data_stuff[swap2] = data_stuff[swap1]; 
		data_stuff[swap1] = element; 
		return data_stuff; 
	}
	

}

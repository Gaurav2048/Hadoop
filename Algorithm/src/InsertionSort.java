
public class InsertionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("================= Insertion sort ===================== \n ");
		
		int [] array_data = new int[] {11, 5,66,32,54,58,23,7,4,3}; 
		
		System.out.print("Entered data \n");
		
		for(int i = 0 ; i< array_data.length ; i++) {
			System.out.print(array_data[i]+" ");
		}
		
		System.out.println();
		
	 
		for(int counter = 1; counter <= array_data.length-1; counter ++)
		{
			int to_check_element = array_data[counter]; 
			array_data = swipeTillNeeded(array_data, to_check_element, counter);  
		}
		
		System.out.print("Sorted data \n");
		
		for(int i = 0 ; i< array_data.length ; i++) {
			System.out.print(array_data[i]+" ");
		}
		
	}
	
	
	public static int[] swipeTillNeeded(int [] array , int to_check, int counter) {
		
		if(counter -1 == 0 ) {
		if(array[0] > to_check) {
			int element = array[0]; 
			array[0] = array[1]; 
			array[1] = element; 
		}
		}else {
		 
		for(int i =counter-1; i>=0; i--) {
			if(to_check<array[i]) {
				int element = array[i]; 
				array[i] = array[i+1]; 
				array[i+1] = element; 
			}
		}
		
		}
		return array; 
	}
	
	
	

}

import javax.lang.model.element.Element;

public class MergeSort {

	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] element = new int[] {11, 23,66,32,54,58,23,7,4,3};	
		for(int i = 0 ; i< element.length; i++ ) {
			int [] new_array = subArray(element, i);
		//	System.out.println(new_array.length);
			int position = FindMinPosition(new_array); 
		//	System.out.println("min position "+ position);
			//System.out.println(i +"   "+position);
			element = swapElement(element, i, i+position);
			//System.out.println(element[i]);
		}
		
		for(int i =0 ;i< element.length ; i++) {
			System.out.println(element[i]);
		}
		
	}
	
	public static int[] swapElement (int [] array, int swap1, int swap2) {
		int element1 = array[swap2]; 
		array[swap2] = array[swap1]; 
		array[swap1] = element1; 
		return array; 
	}
	
	
	public static int FindMin(int [] array_data ) {
		
		int min = 1000; 
		
		for(int i =0; i<array_data.length;i++) {
			if(array_data[i]<min) {
				min = array_data[i]; 
			}
		}
		
		return min;
		
	}
	
public static int FindMinPosition(int [] array_data ) {
		
		int min = 1000; 
		int position = 0; 
		
		for(int i =0; i<array_data.length;i++) {
			if(array_data[i]<min) {
				min = array_data[i]; 
				position = i; 
			}
		}
		
		return position;
		
	}
	
	public static int[] subArray(int[] array_data, int begin) {
		
		int[] array_new = new int[array_data.length - begin];
		int count = 0; 
		for(int k =begin ; k<array_data.length;k++) {
			array_new[count] = array_data[k]; 
			count++; 
		}
		
		return array_new;
	}

}

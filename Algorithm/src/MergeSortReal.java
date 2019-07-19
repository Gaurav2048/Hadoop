
public class MergeSortReal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] array_data = new int[] {11, 5,66,32,54,58,23,7,4,3}; 
		
		
	}
	
	public static int[] createSubArray(int []array , int begin , int end ) {
		   
		int[] new_stuff = new int[end-begin+1]; 
		
		int count =0; 
		for(int i = begin ; i<= end ; i ++) {
			new_stuff[count] = array[i]; 
		}		
		return new_stuff; 
	}
	

}

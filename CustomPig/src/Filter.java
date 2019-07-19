import java.io.IOException;

import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;

public class Filter extends FilterFunc {

	@Override
	public Boolean exec(Tuple tup) throws IOException {
		// TODO Auto-generated method stub
		if(tup == null && tup.size() ==0 ) {
			return false; 
		}
		
		try {
			int id = Integer.parseInt(tup.get(0).toString()); 
			String status = tup.get(1).toString(); 
			
			if(status.equals("pass")) {
				return true; 
			}else {
				return false; 
			}
			
		}catch(Exception e){
			return false; 
		}
		
	}

}

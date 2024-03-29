package problem2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class AirlineeroStop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
public static class Map extends Mapper<LongWritable, Text, Text, IntWritable>{
		
		public void map(LongWritable key , Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString(); 
				StringTokenizer tokenizer = new StringTokenizer(line); 
			
			while(tokenizer.hasMoreTokens()) {
				value.set(tokenizer.nextToken()); 
				
				context.write(value	, new IntWritable(1));
				
			}
			
		}
		
	}
}

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class HotAndCold {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		
		Configuration conf = new Configuration(); 
		
		Job job = Job.getInstance(conf, "Hot and cold"); 
		
		job.setJarByClass(HotAndCold.class);
		
		job.setMapOutputKeyClass(Text.class);
		
		job.setMapOutputValueClass(Text.class);
		
		job.setMapperClass(Map.class);
		
		job.setReducerClass(Reduce.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]); 
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		outputPath.getFileSystem(conf).delete(outputPath, true); 
		
		System.exit(job.waitForCompletion(true)? 0:1 );
		
		
		
	}
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text>{
		
		public static final int MISSING = 9999; 
		
		 @Override 
	     public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
			 {
			 String line = value.toString(); 
			 
			 if(!(line.length()==0)) {
				 String data = line.substring(6,14);
				 
				 float max_temp = Float.parseFloat(line.substring(39, 45).trim());
				 
				 float min_temp = Float.parseFloat(line.substring(47, 53).trim());
				 
				 if(max_temp>40.0 && max_temp != MISSING) {
					 context.write(new Text(data), new Text(max_temp+"   "+"Hot day"));
				 }
				 
				 if(min_temp<10.0 && max_temp != -MISSING) {
					 context.write(new Text(data), new Text(min_temp+"   "+"Cold day"));
				 }

		 }
		
	}
		 
	
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		public void reduce (Text key , Iterable<Text> values, Context context) throws IOException, InterruptedException {
			
			for(Text x : values) {
				context.write(key, x);
			}
			
			
		}
	}
	
	
	
	
}

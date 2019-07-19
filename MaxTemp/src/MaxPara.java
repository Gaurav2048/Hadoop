import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MaxPara {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub

		Configuration conf = new Configuration(); 
		Job job = Job.getInstance(conf, "Max_temperature");
		job.setJarByClass(MaxPara.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduced.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]); 
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		outputPath.getFileSystem(conf).delete(outputPath, true); 
		
		System.exit( job.waitForCompletion(true) ? 0 : 1);

	
	}
	
	
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable>{
		public void map(LongWritable key , Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString().trim(); 
			String[] data = line.split(" "); 
			context.write(new Text(data[0]), new IntWritable(Integer.valueOf(data[1])));	
		}
	}
	
public static class Reduced extends Reducer<Text, IntWritable, Text, IntWritable> {
		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException  {
			int sum = 0 ; 
			
			for(IntWritable x : values) {
				if(x.get()>sum) {
					sum = x.get(); 
				}
			}
			context.write(key, new  IntWritable(sum)); 
			
		}
	
		
}

}
	


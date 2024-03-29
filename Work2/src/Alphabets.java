import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

 

public class Alphabets {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
Configuration conf =  new Configuration(); 
		
		Job job = Job.getInstance(conf, "WordCount"); 
		job.setJarByClass(Alphabets.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]); 
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		outputPath.getFileSystem(conf).delete(outputPath, true); 
		
		System.exit( job.waitForCompletion(true) ? 0 : 1);
	}
	

public static class Map extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	
	public void map(LongWritable key , Text value, Context context) throws IOException, InterruptedException {
		
		String line = value.toString(); 
		
		String[] line_array = line.split(" "); 
		
		
		for(int i =0 ; i<line_array.length;i++) {
			String operate = line_array[i].replace(",", "").replace(".", ""); 
			context.write(new IntWritable(operate.length()), new IntWritable(1));
		}
	}
}

public static class Reduce extends Reducer <IntWritable, IntWritable, IntWritable, IntWritable> {
	public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum =0 ; 
		
		for(IntWritable x : values) {
			sum+= x.get(); 
		}
		context.write(key, new  IntWritable(sum)); 
		
	}
}
}

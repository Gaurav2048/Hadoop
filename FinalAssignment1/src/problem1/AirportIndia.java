package problem1;

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



public class AirportIndia {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		
		Configuration conf =  new Configuration(); 
		
		Job job = Job.getInstance(conf, "Airport_in_india"); 
		job.setJarByClass(AirportIndia.class);
		job.setMapperClass(Map.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]); 
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		outputPath.getFileSystem(conf).delete(outputPath, true); 
		
		System.exit( job.waitForCompletion(true) ? 0 : 1);

	}
	
public static class Map extends Mapper<LongWritable, Text, Text, Text>{
	
	Text k = new Text(); 
	Text v = new Text(); 
		
		public void map(LongWritable key , Text value, Context context) throws IOException, InterruptedException  {
			String line = value.toString(); 
			String[] inputs = line.split(","); 
			
			if(inputs[3].toLowerCase().equals("india")) {
				String vaue = "City:- "+inputs[1]+"  IATA/FAA No:- "+inputs[4];
				k.set(inputs[0]);
				v.set(vaue);
				context.write(k,v);
			}
			
		}
		
	}


}

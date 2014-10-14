package sample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.hadoop.fs.FileSystem;

public class WordCount {

	private static final transient Logger LOG = LoggerFactory.getLogger(WordCount.class);

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();		

		LOG.info("HDFS Root Path: {}", conf.get("fs.defaultFS"));
		LOG.info("MR Framework: {}", conf.get("mapreduce.framework.name"));
		/* Set the Input/Output Paths on HDFS */
		//String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	    if (args.length != 2) {
	      System.err.println("Usage: wordcount <in> <out>");
	      System.exit(2);
	    }
	    
	    //String inputPath = "/home/satya/workspace/input.cpp";
		//String outputPath = "output";

		/* FileOutputFormat wants to create the output directory itself.
		 * If it exists, delete it:
		 */
		//deleteFolder(conf,outputPath);
		
		Job job = new Job(conf, "word count");

		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//FileInputFormat.addInputPath(job, new Path(inputPath));
		//FileOutputFormat.setOutputPath(job, new Path(outputPath));
		FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	
	/**
	 * Delete a folder on the HDFS. This is an example of how to interact
	 * with the HDFS using the Java API. You can also interact with it
	 * on the command line, using: hdfs dfs -rm -r /path/to/delete
	 * 
	 * @param conf a Hadoop Configuration object
	 * @param folderPath folder to delete
	 * @throws IOException
	 */
	/*private static void deleteFolder(Configuration conf, String folderPath ) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(folderPath);
		if(fs.exists(path)) {
			fs.delete(path,true);
		}
	}*/
}
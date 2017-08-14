package Tool.Forensics_Log_Analyzer;
/**
*
* @author g_chawla
*/
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Auth_Test 
{
	 public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
	 {
	    private final static IntWritable one = new IntWritable(1);
	    private Text word = new Text();
	    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
	    {
	    	   String date="";
	           String Date="";
	       	   String time="";
	           String CIP="";           
	           String SIP="";         	
	           String port="";
	           String username="";
	           String status="";
	           System.out.println("test here");
	           StringTokenizer itr = new StringTokenizer(value.toString());

	           while (itr.hasMoreTokens())

	           {
	               date=itr.nextToken();
	               System.out.println("date = " +date);
	               time=itr.nextToken();
	               System.out.println("time = " +time);
	               CIP=itr.nextToken();
	               System.out.println("Client IP = " +CIP);
	               SIP=itr.nextToken();   
	               System.out.println("Server IP = " +SIP);
	               port=itr.nextToken();
	               System.out.println("port = " +port);
	               username=itr.nextToken();
	               System.out.println("Port name= " +username);
	               status=itr.nextToken();
	               System.out.println("status = " +status);
	               if(status.equalsIgnoreCase("deny"))
	               {   
	               word.set(CIP);
	   	           context.write(word, one);
	               }
	           }
	      System.out.println("here");
	      System.out.println(context.getCurrentKey().toString());
	    }
	  }

	  public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
	  {
	    private IntWritable result = new IntWritable();
	    public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException 
	    {
	      int sum = 0;
	      for (IntWritable val : values) 
	      {
	    	System.out.println("-----------test-----------------------"); 
	    	System.out.println(key.toString());
	    	sum += val.get();
	      }
	      result.set(sum);
	      context.write(key, result);
	    }
	   }

	  public static void main() throws Exception 
	  {
	    Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "word count");
	    job.setJarByClass(Auth_Test.class);
	    job.setMapperClass(TokenizerMapper.class);
	    job.setCombinerClass(IntSumReducer.class);
	    job.setReducerClass(IntSumReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
//	    FileInputFormat.addInputPath(job, new Path("Auth_Logs_New.txt"));
	    FileInputFormat.addInputPath(job, new Path("newtest.txt"));	    
	    FileOutputFormat.setOutputPath(job, new Path("output"));
//	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	    job.waitForCompletion(true);
	    Auth_Detect ad= new Auth_Detect();
	    ad.setVisible(true);
	  }
	}
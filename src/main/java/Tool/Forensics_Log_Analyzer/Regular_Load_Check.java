package Tool.Forensics_Log_Analyzer;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Regular_Load_Check 
{
    static String dat="";
    static String to="";
    static String from="";
 public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
 {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
    {
    	String date="";
    	String time="";
        String CIP="";           
        String SIP="";         	
        String port="";
        String username="";
        String status="";
      StringTokenizer itr = new StringTokenizer(value.toString());
      System.out.println(itr.countTokens());  
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
          System.out.println("Username= " +username);
          status=itr.nextToken();
          System.out.println("status = " +status);
          int hr=Integer.parseInt(time.substring(0, 2));
          int min=Integer.parseInt(time.substring(3, 5));
          int sec=Integer.parseInt(time.substring(6, 8));
          System.out.println("PARSE---" + hr +" "+ min+ " " +sec); 
          if(status.equalsIgnoreCase("Deny"))
        	   if(date.equalsIgnoreCase(dat))
        		   if(hr>=Integer.parseInt(to) && hr<=Integer.parseInt(from))
						{
							System.out.println("testfire-----------------------------------------");
							word.set(time.substring(0, 2));
							context.write(word, one);
						}        		   
			}
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

  public static void main(String args[]) throws Exception 
  {
    dat=args[0];
    to=args[1];
    from=args[2];
	Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(Peak.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path("newtest.txt"));
    FileOutputFormat.setOutputPath(job, new Path("output"));
    //System.exit(job.waitForCompletion(true) ? 0 : 1);
    job.waitForCompletion(true);
    Detect_Dos la = new Detect_Dos();
    la.setVisible(true);    
  }
}
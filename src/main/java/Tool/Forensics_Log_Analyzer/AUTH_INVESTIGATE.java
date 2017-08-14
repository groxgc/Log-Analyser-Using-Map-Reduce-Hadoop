package Tool.Forensics_Log_Analyzer;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AUTH_INVESTIGATE {
	   public static class WordMapper extends Mapper<Text, Text, Text, Text>
	   {
	       private Text word = new Text();
	       private Text word2 = new Text();
	       public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	       {
	       	String date="";
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
	               
	               System.out.println("date = " +date);
	               time=itr.nextToken();
	               System.out.println("unknown = " +time);
	               CIP=itr.nextToken();
	               System.out.println("Client IP = " +CIP);
	               SIP=itr.nextToken();   
	               System.out.println("Server IP = " +SIP);
	               port=itr.nextToken();
	               System.out.println("time = " +port);
	               username=itr.nextToken();
	               System.out.println("name= " +username);
	               status=itr.nextToken();
	               System.out.println("status = " +status);
	               if(status.equalsIgnoreCase("Deny"))
	               {
	               word.set(username);	               
	   	       	   word2.set(port);   	  
	    	       context.write(word2,word);
	               }
	          }
	       }
	   }

	   public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
	   {
		   private Text test = new Text();
		   public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
	       { 
	           System.out.println("It comes here");
	           for (Text val : values)
	           {
	        	   System.out.println(val);
	           	context.write(key, val);
	           }
	       }
	   }

	   public static void main() throws Exception
	   {
	       Configuration conf = new Configuration();
	       Job job = new Job(conf, "auth_test");
	       job.setJarByClass(Auth_Test2.class);
	       job.setMapperClass(WordMapper.class);
	       job.setReducerClass(AllTranslationsReducer.class);
	       job.setOutputKeyClass(Text.class);
	       job.setOutputValueClass(Text.class);
	       job.setInputFormatClass(KeyValueTextInputFormat.class);
	       FileInputFormat.addInputPath(job, new Path("machine.txt"));	    
		   //FileInputFormat.addInputPath(job, new Path("Auth_Logs_New.txt"));
	       FileOutputFormat.setOutputPath(job, new Path("output3"));
	       job.waitForCompletion(true);
	       AUTH_MAC_RESULT ar=new AUTH_MAC_RESULT();
	       ar.setVisible(true);      
	    
	   }
}

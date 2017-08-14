package Tool.Forensics_Log_Analyzer;
/**
*
* @author g_chawla
*/
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Auth_Test2 {
public static String ip="";
   public static class WordMapper extends Mapper<Text, Text, Text, Text>
   {
       private Text word = new Text();
       private Text word2 = new Text();
       public void map(Text key, Text value, Context context) throws IOException, InterruptedException
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
               if(status.equalsIgnoreCase("Deny"))
               {
            	   word.set(CIP);
               
   	       	   word2.set(username);   	  
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
		   test.set(ip);
           System.out.println("It comes here");
           for (Text val : values)
           {
        	   System.out.println(val);
        	if(val.equals(test))   
           	context.write(key, val);
           }
       }
   }

   public static void main(String[] args) throws Exception
   {
	   ip=args[0];
	   System.out.println(ip);
       Configuration conf = new Configuration();
       Job job = new Job(conf, "auth_test");
       job.setJarByClass(Auth_Test2.class);
       job.setMapperClass(WordMapper.class);
       job.setReducerClass(AllTranslationsReducer.class);
       job.setOutputKeyClass(Text.class);
       job.setOutputValueClass(Text.class);
       job.setInputFormatClass(KeyValueTextInputFormat.class);
       FileInputFormat.addInputPath(job, new Path("newtest.txt"));	    
	   //FileInputFormat.addInputPath(job, new Path("Auth_Logs_New.txt"));
       FileOutputFormat.setOutputPath(job, new Path("output2"));
       job.waitForCompletion(true);
       Auth_Result ar=new Auth_Result();
       ar.setVisible(true);      
    
   }
}

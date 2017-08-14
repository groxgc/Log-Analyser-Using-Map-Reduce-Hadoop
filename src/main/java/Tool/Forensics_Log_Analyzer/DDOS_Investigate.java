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



public class DDOS_Investigate {
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        private Text word2 = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
        	String username="";
            String time="";
            String SIP="";         	
            String CIP="";
            String port="";
            String status="";
            System.out.println("test here");
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens())
            {
                time=itr.nextToken();
                System.out.println("time = " +time);
                SIP=itr.nextToken();   
                System.out.println("Server IP = " +SIP);
                CIP=itr.nextToken();
                System.out.println("Client IP = " +CIP);
                port=itr.nextToken();
                System.out.println("port = " +port);
                username=itr.nextToken();
                System.out.println("username = " +username);                
                status=itr.nextToken();
                System.out.println("status = " +status);           
                int hr=Integer.parseInt(time.substring(0, 2));
                int min=Integer.parseInt(time.substring(3, 5));
                int sec=Integer.parseInt(time.substring(6, 8));
                System.out.println("PARSE---" + hr +" "+ min+ " " +sec); 
                if(status.equalsIgnoreCase("Deny"))
              	      if(hr>=02)
      						{
      							System.out.println("testfire-----------------------------------------");
      			                word.set(username);
      			    	       	word2.set(SIP);   	  
      			    	       	context.write(word,word2);
      						} 
            }
        }
    }

    public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
    {
        private Text result = new Text();
        private Text check = new Text();
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
        {
            System.out.println("It comes here");
            for (Text val : values)
            {
            	context.write(key, val);
            }
        }
    }

    public static void main() throws Exception
    {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "dictionary");
        job.setJarByClass(Dictionary.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("newtest.txt"));
        FileOutputFormat.setOutputPath(job, new Path("output3"));
        job.waitForCompletion(true);
        DDOS_INV_RESULT dosinv = new DDOS_INV_RESULT();
        dosinv.setVisible(true);
    }
}
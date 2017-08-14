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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Dictionary

{
	 static String Val1="";
	 static String Val2="";
	 static String Val3="";
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        private Text word2 = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
        	String date="";
            String time="";
            String SIP="";         	
            String CIP="";
            String port="";
            String Pname="";
            String protocol="";
            String status="";
            String check="";
            String Auth="";
            System.out.println("test here");
            System.out.println(Val1);
            System.out.println(Val2);
            System.out.println(Val3);
            StringTokenizer itr = new StringTokenizer(value.toString());

            while (itr.hasMoreTokens())

            {
                date=itr.nextToken();
                System.out.println("date = " +date);
                time=itr.nextToken();
                System.out.println("time = " +time);
                SIP=itr.nextToken();   
                System.out.println("Server IP = " +SIP);
                CIP=itr.nextToken();
                System.out.println("Client IP = " +CIP);
                port=itr.nextToken();
                System.out.println("port = " +port);
                Pname=itr.nextToken();
                System.out.println("Port name= " +Pname);
                protocol=itr.nextToken();
                System.out.println("Protocol= " +protocol);
                status=itr.nextToken();
                System.out.println("status = " +status);
                check=itr.nextToken();
                System.out.println("check = " +check);
                Auth=itr.nextToken();
                System.out.println("Auth = " +Auth);
                if(Val1.equalsIgnoreCase("Date"))
                {
                	if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(date);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(date);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(date);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(date);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(date);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(date);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(date);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(date);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(date);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
         }
        if(Val1.equalsIgnoreCase("TimeStamp"))
        {    		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(time);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(time);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(time);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(time);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(time);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(time);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(time);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(time);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(time);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Server IP"))
        {
        			if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(SIP);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(SIP);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(SIP);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(SIP);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(SIP);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(SIP);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(SIP);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(SIP);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(SIP);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Client IP"))
        {
        			if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(CIP);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(CIP);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(CIP);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(CIP);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(CIP);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(CIP);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(CIP);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(CIP);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(CIP);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }    		
        if(Val1.equalsIgnoreCase("Port"))    		       
        {
        			if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(port);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(port);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(port);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(port);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(port);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(port);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(port);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(port);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(port);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }    		
        if(Val1.equalsIgnoreCase("Port Name"))
        {    		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(Pname);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(Pname);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(Pname);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(Pname);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(Pname);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(Pname);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(Pname);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(Pname);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(Pname);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Protocol"))
        {    		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(protocol);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(protocol);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(protocol);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(protocol);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(protocol);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(protocol);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(protocol);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(protocol);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(protocol);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Status"))
        {   		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(status);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(status);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(status);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(status);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(status);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(status);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(status);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(status);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(status);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Auth Status"))
        {   		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(check);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(check);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(check);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(check);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(check);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(check);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(check);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(check);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Request Type"))
            		{
            	       	word.set(check);
            	       	word2.set(Auth);   	  
            	       	context.write(word,word2);
            		}
        }
        if(Val1.equalsIgnoreCase("Request Type"))
        {    		if (Val2.equalsIgnoreCase("Date"))
            		{
            	       	word.set(Auth);
            	       	word2.set(date);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("TimeStamp"))
            		{
            	       	word.set(Auth);
            	       	word2.set(time);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Server IP"))
            		{
            	       	word.set(Auth);
            	       	word2.set(SIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Client IP"))
            		{
            	       	word.set(Auth);
            	       	word2.set(CIP);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port"))
            		{
            	       	word.set(Auth);
            	       	word2.set(port);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Port Name"))
            		{
            	       	word.set(Auth);
            	       	word2.set(Pname);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Protocol"))
            		{
            	       	word.set(Auth);
            	       	word2.set(protocol);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Status"))
            		{
            	       	word.set(Auth);
            	       	word2.set(status);   	  
            	       	context.write(word,word2);
            		}
            		if (Val2.equalsIgnoreCase("Auth Status"))
            		{
            	       	word.set(Auth);
            	       	word2.set(check);   	  
            	       	context.write(word,word2);
            		}
        }       
                
//                word.set(time);
//    	       	word2.set(SIP);   	  
//    	       	context.write(word,word2);
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
            check.set(Val3);
            for (Text val : values)
            {
            	if(val.equals(check))
            	context.write(key, val);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Output: " + args[0] + "	" +args[1] + "	" +args[2]);
        Val1=args[0];
        Val2=args[1];
        Val3=args[2];
        Configuration conf = new Configuration();
        Job job = new Job(conf, "dictionary");
        job.setJarByClass(Dictionary.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("test.txt"));
        FileOutputFormat.setOutputPath(job, new Path("output"));
        job.waitForCompletion(true);
        Display dis = new Display();
        dis.main(args);
//        dis.setVisible(true);
    }
}
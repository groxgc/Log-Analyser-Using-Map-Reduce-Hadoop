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


public class Signature_based
{
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        private Text word2 = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
	    	String Rand="";
	        String Ciph="";
	        String IP="";
	        String idk="";         	
	        String Port="";
	        String Pro1="";
	        String Pro2="";
	        String r1="";
	        String r2="";
	        String r3="";
            System.out.println("test here");
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens())
            {
  	          Ciph=itr.nextToken();
  	    	  Rand=itr.nextToken();
  	    	  System.out.println(Ciph);
  	          idk=itr.nextToken();
  	          IP=itr.nextToken();    
  	          System.out.println(IP);
  	          Port=itr.nextToken();  	          
  	          Pro1=itr.nextToken();
  	          Pro2=itr.nextToken();
  	          r1=itr.nextToken();
  	          r2=itr.nextToken();
  	          r3=itr.nextToken();	     
  	          word.set(IP);
	          word2.set(Ciph);   	  
	          context.write(word2,word);
	          System.out.println("Check");
              }
             // System.out.println(context.getCurrentKey());
        }
    }

    public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
    {
        private Text result = new Text();
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
        {
            String translations = "";

            for (Text val : values)

            {

                translations += "|"+val.toString();
                result.set(translations);

                context.write(key, result);
            }


        }
    }

    public static void main() throws Exception
    {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "Signature_based");
        job.setJarByClass(Signature_based.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("conn.log"));
        FileOutputFormat.setOutputPath(job, new Path("output"));
        job.waitForCompletion(true);
        Intrusion_display idis= new Intrusion_display();
        idis.setVisible(true);
    }
}
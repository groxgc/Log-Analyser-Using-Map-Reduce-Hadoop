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

public class Hash_Check

{
	 static String Val1="";
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        private Text word2 = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
        	String number="";
            String hash="";
            System.out.println("test here");
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens())
            {
                number=itr.nextToken();
                System.out.println("number = " +number);
                hash=itr.nextToken();
                System.out.println("hash = " +hash);   
                word.set(number);
    	       	word2.set(hash);   	  
    	       	context.write(word,word2);
            }
        }
    }

    public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
    {
        private Text check = new Text();
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
        {
        	//System.out.println(Val1);
        	check.set(Val1);
        	for (Text val : values)
            {
            	if(val.equals(check))
        		context.write(key, val);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Output: " + args[0] );
        Val1=args[0];
        Configuration conf = new Configuration();
        Job job = new Job(conf, "Hash_Check");
        job.setJarByClass(Hash_Check.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("Hash.txt"));
        FileOutputFormat.setOutputPath(job, new Path("output"));
        job.waitForCompletion(true);
        Hash_Display dis = new Hash_Display();
        dis.setVisible(true);
    }
}
package sample;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private static Set<String>ignorewords = GetUnwantedWords();
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		//StringTokenizer itr = new StringTokenizer(value.toString());
		String str = value.toString().toLowerCase().replaceAll("\\.", " ");
		str = str.replaceAll("[^a-zA-Z ]+", "");
		String []retval = str.split("\\s+");
		//HashSet<String> set = new HashSet<String>();
		
		for (int i=0;i<retval.length-1;i++){
			if(ignorewords.contains(retval[i]) == false)
			{
				for (int j=i+1;j<retval.length;j++)
				{
					if(ignorewords.contains(retval[j]) == false &&
							!retval[i].equals(retval[j]) )
					{
						String bigram = null;
						int res = retval[i].compareToIgnoreCase(retval[j]);
						if(res > 0)
						{
							bigram = retval[i] +" "+retval[j];
						}
						else
						{
							bigram = retval[j] +" "+retval[i];
						}
						//if(set.contains(bigram) == false)
						{
							//set.add(bigram);
							word.set(bigram);
							context.write(word, one);
						}
					}
				}
			}
	      }
		
		/*while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
			context.write(word, one);
		}*/
	}
	
	static private Set<String> GetUnwantedWords()
	{
		return new HashSet<String>(Arrays.asList("&","a","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at",
				"be","because","been","before","being","below","between","both","but","by","can't","cannot","could",
				"couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for",
				"from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's",
				"her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm",
				"i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't",
				"my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","ours","ourselves",
				"out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some",
				"such","than","that","that's","the","their","theirs","them","themselves","then","there","there's",
				"these","they","they'd","they'll","they're","they've","this","those","through","to","too","under",
				"until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what",
				"what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with",
				"won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"));
	}
}
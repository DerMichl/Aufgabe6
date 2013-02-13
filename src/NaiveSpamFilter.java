import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Michel
 * Date: 13.02.13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class NaiveSpamFilter
{
	public static void main(String[] args) throws IOException
	{
		long startTime = System.currentTimeMillis();
		if (args[0].equals("learn"))
		{
			NaiveMailCorpus hamCorpus = new NaiveMailCorpus(new File(args[1]), true, "ham");
			//NaiveMailCorpus spamCorpus = new NaiveMailCorpus(new File(args[1]), true, "spam");

			hamCorpus.writeModel(new File(args[2] + "//..//modelham"));
			//spamCorpus.writeModel(new File(args[2] + "//..//modelspam"));

			//hamCorpus.printWords();
			System.out.println("Ham & Spam eingelesen und Model geschrieben in " + (System.currentTimeMillis() - startTime) + "ms");
		}
		else if (args[0].equals("classify"))
		{
			NaiveMailCorpus hamCorpus = new NaiveMailCorpus(new File(args[1] + "//..//modelham"), false, "ham");
			//NaiveMailCorpus spamCorpus = new NaiveMailCorpus(new File(args[2] + "//..//modelspam"), false, "spam");



			//hamCorpus.printWords();
		}

	}


}

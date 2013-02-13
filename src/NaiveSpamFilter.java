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
			NaiveMailCorpus hamCorpus = new NaiveMailCorpus(new File(args[2]));
			NaiveMailCorpus spamCorpus = new NaiveMailCorpus(new File(args[1]));

			System.out.println("Ham & Spam eingelesen in " + (System.currentTimeMillis() - startTime) + "ms");

			//spamCorpus.printWords();
		}
		else if (args[0].equals("classify"))
		{

		}

	}


}

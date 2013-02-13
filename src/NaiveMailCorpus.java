import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Michel
 * Date: 13.02.13
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class NaiveMailCorpus
{
	HashMap<String, Integer> corpus = new HashMap<String, Integer>();
	//HashMap<String, Integer> spamCorpus = new HashMap<String, Integer>();

	public NaiveMailCorpus(File path, boolean train, String category) throws IOException
	{
		if (train)
		{
			read(path);
		}
		else
		{
			readModel(path);
		}
	}


	private void read(File path) throws IOException
	{
		System.out.println(path);
		for (File file : path.listFiles())
		{
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.length() > 0) {
					String[] words = strLine.split(" ");
					for (int i = 0; i < words.length; i++)
					{
						words[i] = normToken(words[i].toLowerCase());
						// wenn Wort schon in Map, ein hochzaehlen, ansonsten einfuegen
						corpus.put(words[i], corpus.containsKey(words[i]) ?  corpus.get(words[i]) + 1 : 1);
					}
				}
			}
			in.close();

		}
	}

	public static String normToken(String token) {
		return token.replaceAll("\\d+", "*$0*").replaceAll("\\d", "D");
	}

	public void printWords()
	{
		Iterator i = corpus.entrySet().iterator();
		Map.Entry e;
		while (i.hasNext())
		{
			e = (Map.Entry) i.next();
			System.out.println(e.getKey() + "  :  " + corpus.get(e.getKey()));
		}
	}

	public void writeModel(File file) throws IOException
	{
		// Model schreiben
		DataOutputStream outputStream;
		outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		outputStream.writeInt(corpus.size());
		System.out.println(corpus.size());
		int i = 0;
		for (Map.Entry<String, Integer> entry : corpus.entrySet())
		{
			i++;
			System.out.println(i);
			//Helper.writeString(outputStream, "hallo");
			String word = entry.getKey();
			outputStream.writeInt(word.length());
			for (char c : word.toCharArray())
				outputStream.writeChar(c);
			outputStream.writeInt(entry.getValue());
			System.out.println(word + "  :  " + entry.getValue());
		}
	}

	public void readModel(File file) throws IOException
	{
		// Model lesen
		DataInputStream inputStream;
		inputStream = new DataInputStream(new FileInputStream(file));
		int size = inputStream.readInt();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.println(i);
			int strLength = inputStream.readInt();
			char[] chars = new char[strLength];
			for (int j = 0; j < strLength; j++) {
				chars[j] = inputStream.readChar();
			}
			int value = inputStream.readInt();
			corpus.put(String.valueOf(chars), value);
			System.out.println(String.valueOf(chars) + "  :  " + value);
		}
	}
}

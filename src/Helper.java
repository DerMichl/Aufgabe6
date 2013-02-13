
import java.io.*;
import java.text.Bidi;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Arne
 * Date: 24.01.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
	static final char tagDelimiter = '/';

	public static String[] getFileList(String directoryPath) {
		File dir = new File(directoryPath);
		return  dir.list(/*new FilenameFilter() {
			public boolean accept(File d, String name) {
				return !name.toLowerCase().contains(".pos");
			}
		}*/);
	}

	public static String readString(DataInputStream inputStream) throws IOException {
		int strLength = inputStream.readInt();
		char[] chars = new char[strLength];
		for (int j = 0; j < strLength; j++) {
			chars[j] = inputStream.readChar();
		}
		return String.valueOf(chars);
	}

	public static void writeString(DataOutputStream outputStream, String string) throws IOException {
		outputStream.writeInt(string.length());
		for (char c : string.toCharArray()) {
			outputStream.writeChar(c);
		}
	}


}

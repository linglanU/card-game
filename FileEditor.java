import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

public class FileEditor {
	@Test
	public static String getUserDir() {
		return System.getProperty("user.dir");
	}

	public static void main(String[] args) {
		System.out.println(getUserDir());
	}
}

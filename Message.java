public class Message {
	public static void say(String content) {
		System.out.println(content);
	}

	public static void alert(String warning) {
		say(warning);
	}

	public static void transferInfo(MultiThread.Account origin, MultiThread.Account target) {
		say(origin.getName() + " send to " + target.getName());
	}
}

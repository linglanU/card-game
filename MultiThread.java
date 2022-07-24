import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThread {
	// Every process has its own variables
	// However, multi threads owe single set of variables
	// Communication between threads is more effective and easier than the one between processes

	// Process is relatively heavy
	// Thread is relatively thin

	public class Account {
		private String name = null;
		private int remains = 0;

		public Account(String name) {
			this.setName(name);
			this.remains = ThreadLocalRandom.current().nextInt(0, 1000);
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getRemains() {
			return this.remains;
		}

		public void getStatus() {
			System.out.println(this.getName() + ": " + this.getRemains() + " yen");
		}

		public void transferTo(Account target, int amount) {
			this.remains -= amount;
			target.remains += amount;
		}
	}

	@Test
	public void transferTestWithoutMultiThread() {
		Message.say("Init Alice and Usagi");
		Account alice = new Account("Alice");
		Account	usagi = new Account("Usagi");
		alice.getStatus();
		usagi.getStatus();
		int allRemains = alice.remains + usagi.getRemains();
		Message.say("All: " + allRemains + " yen");

		Message.transferInfo(alice, usagi);
		alice.transferTo(usagi, 100);
		alice.getStatus();
		usagi.getStatus();
		allRemains = alice.getRemains() + usagi.getRemains();
		Message.say("All: " + allRemains + " yen");

		Message.transferInfo(usagi, alice);
		usagi.transferTo(alice, 100);
		alice.getStatus();
		usagi.getStatus();
		allRemains = alice.getRemains() + usagi.getRemains();
		Message.say("All: " + allRemains + " yen");
	}

	@Test
	// failed
	public void transferTestWithMultiTread() {
		Message.say("Init Alice and Usagi");
		Account alice = new Account("Alice");
		Account	usagi = new Account("Usagi");
		alice.getStatus();
		usagi.getStatus();
		int allRemains = alice.remains + usagi.getRemains();
		Message.say("All: " + allRemains + " yen");

		Runnable task1 = () -> {
			// Alice transfer to Usagi
			try {
				for (int i = 0; i < 100; i++) {
					int amount = ThreadLocalRandom.current().nextInt(0, 100);
					Message.transferInfo(alice, usagi);
					alice.transferTo(usagi, amount);
					alice.getStatus();
					usagi.getStatus();
					Thread.sleep(ThreadLocalRandom.current().nextInt(0, 100));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable task2 = () -> {
			// Usagi transfer to Alice
			try {
				for (int i = 0; i < 100; i++) {
					int amount = ThreadLocalRandom.current().nextInt(0, 100);
					Message.transferInfo(usagi, alice);
					usagi.transferTo(alice, amount);
					alice.getStatus();
					usagi.getStatus();
					Thread.sleep(ThreadLocalRandom.current().nextInt(0, 100));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		new Thread(task1).start();
		new Thread(task2).start();
	}


	//
	//
	// all below not done
	public class Card {
		private int id = 0;
		private String color = null;
		private String name = null;
		private int value = 0;

		String[] cardColors = {"♥", "♠", "♦", "♣"};
		String[] cardNames = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] cardKings = {"king", "KING"};

		public Card(int id) {
			this.id = id;
			this.name = this.idToColor() + this.idToName();
			this.value = this.idToValue();
		}

		public String getColor() {
			return this.color;
		}

		public String getName() {
			return this.name;
		}

		public String getCardName() {
			return this.getColor() + this.getName();
		}

		public String idToColor() {
			int id = this.id;
			if (id > 52) {
				if (id == 53) { this.color = null; }
				if (id == 54) { this.color = null; }
			} else {
				int colorId = id % 13;
				if (colorId == 0) { this.color = "Heart"; }
				if () {}
			}
		}

		public String idToName() {

		}

		public int idToValue() {

		}
	}

	public class Gamer {}

	public class CardGame {
		int[] cards = new int[54];
		// ArrayList<Card> cards = new ArrayList<>();
		public CardGame() {
			for (int i = 0; i < 54; i++) {
				int id = i + 1;
				cards[i] = id;
			}
			for (int i = 0; i < 54; i++) {
				int targetId = ThreadLocalRandom.current().nextInt(1, 54);
				int temp = cards[i];
				cards[i] = cards[targetId];
				cards[targetId] = temp;
			}
			Message.say("Card game init done");
		}

	}
}

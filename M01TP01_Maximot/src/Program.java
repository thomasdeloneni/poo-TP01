import java.io.*;
import java.util.*;

public class Program {

	public static void main(String[] args) throws IOException {
		var random = new Random();
		var scanner = new Scanner(System.in);

		String[] dico = InitializedData();
		char[] word = tirerMotAleatoire(dico, random);
		// System.out.println(word);
		char[] shuffledLetters = Shuffle(word, random);
		boolean correct;

		var essai = 0;
		do {
			System.out.println("Voila le tirage :" + new String(shuffledLetters));
			System.out.println("Entrez une proposition");

			var proposition = scanner.nextLine();
			correct = bonneslettres(proposition, shuffledLetters);
			if (correct && isIdentique(word, proposition.toCharArray())) {
				System.out.println("GG");
				return;
			} else {
				System.out.println("Echec n" + ++essai);
			}
		} while (essai < 5);
		System.out.println("Game Over");
		System.out.println("le mot était : "+new String(word));
		// System.out.println(shuffledLetters);

	}

	private static boolean isIdentique(char[] word, char[] proposition) {
		return new String(word).equals(new String(proposition));
	}

	private static boolean bonneslettres(String proposition, char[] shuffledLetters) {
		var letters = shuffledLetters.clone();
		for (char c : proposition.toCharArray()) {
			var found = false;
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == c) {
					letters[i] = 0;
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}

	private static char[] Shuffle(char[] word, Random random) {
		var letters = word.clone();
		for (int i = 0; i < letters.length; i++) {
			var temp = letters[i];
			var index = random.nextInt(letters.length);
			letters[i] = letters[index];
			letters[index] = temp;
		}

		return letters;
	}

	private static char[] tirerMotAleatoire(String[] dico, Random random) {
		var indexRandom = random.nextInt(dico.length);
		return dico[indexRandom].toCharArray();
	}

	private static String[] InitializedData() throws FileNotFoundException, IOException {
		var fis = new FileInputStream("./dictionnaire.txt");
		var scanner = new Scanner(fis);
		var count = 0;

		while (scanner.hasNext()) {
			scanner.nextLine();
			count++;
		}

		scanner.close();
		fis.close();

		var dico = new String[count];
		count = 0;

		fis = new FileInputStream("./dictionnaire.txt");
		scanner = new Scanner(fis);
		while (scanner.hasNext()) {
			dico[count++] = scanner.nextLine();
		}
		fis.close();
		scanner.close();
		return dico;
	}

}

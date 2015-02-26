package main;

public class Letter implements Comparable<Object> {
	private int[] bits;
	private char letter;
	private boolean space;

	public Letter() {
		letter = ' ';
		space = false;
		bits = new int[35];
	}

	public Letter(String bitCode) {
		bits = new int[35];
		letter = bitCode.charAt(bitCode.length() - 1);
		setBitCode(bitCode.substring(0, bitCode.length() - 1));
		if (letter == ' ') {
			space = true;
		} else {
			space = false;
		}
	}

	
	public boolean hasSpace() {
		return space;
	}

	
	private void setBitCode(String bitCode) {
		for (int index = 0; index < bits.length; index++) {
			bits[index] = Integer.parseInt(Character.toString(bitCode
					.charAt(index)));
		}
	}
	

	public char getLetter() {
		return letter;
	}
	

	public int[] getBits() {
		return bits;
	}
	

	public void setBits(int[] bits) {
		this.bits = bits;
	}

	/*
	 * public static ArrayList<Letter> getLetters(File inFile) throws
	 * FileNotFoundException { Scanner inScan = new Scanner(inFile);
	 * ArrayList<Letter> letters = new ArrayList<Letter>();
	 * while(inScan.hasNext()) { String bitCode = inScan.nextLine();
	 * letters.add(new Letter(bitCode)); } inScan.close(); return letters; }
	 */

	public int compareTo(Object obj) {
		char character = ((Letter) obj).getLetter();
		if (character > letter) {
			return 1;
		}

		else if (character < letter) {
			return -1;
		}
		return 0;
	}
}
package fhv.q3;

public class Compiler {

	public static void main(String[] args) {
		char[] tape = new char[] { 'z', 'x', 'y', 'z', 'b' };
		boolean result = accept(tape);
		System.out.println(result ? "OK" : "NOK");
	}

	public static String start = "A";
	public static String end = "C";

	public static boolean accept(char[] tape) {
		String currentState = start;

		for (int sign = 0; sign < tape.length; sign++) {
			if (currentState == "A") {
				currentState = acceptA(tape[sign]);
			} else if (currentState == "B") {
				currentState = acceptB(tape[sign]);
			} else if (currentState == "C") {
				// no signs are excpected after state c 
				return false;
			} else if (currentState == "D") {
				currentState = acceptD(tape[sign]);
			} else {
				return false;
			}
		}

		return currentState == end;
	}

	private static String acceptA(char next) {
		if (next == 'z') {
			return "A";
		} else if (next == 'x') {
			return "B";
		} else {
			return null;
		}
	}

	private static String acceptB(char next) {
		if (next == 'z') {
			return "C";
		} else if (next == 'y') {
			return "D";
		} else {
			return null;
		}
	}

	private static String acceptD(char next) {
		if (next == 'z') {
			return "C";
		} else {
			return null;
		}
	}
}

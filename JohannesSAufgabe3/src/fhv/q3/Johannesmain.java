package fhv.q3;

public class Johannesmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Johannesmain m = new Johannesmain();
		char[] tape = new char[] { 'z', 'X', 'z' };
		if (m.accept(tape)) {
			System.out.println("Accept");
		} else {
			System.out.println("Nicht Accept");
		}

	}

	public Boolean accept(char[] tape) {
		tape = new String(tape).toLowerCase().toCharArray();
		char z = 'z';
		char x = 'x';
		char y = 'y';
		State cur = State.A;

		for (char c : tape) {
			switch (cur) {
			case A:
				if (c == z)
					cur = State.A;
				else if (c == x)
					cur = State.B;
				else
					return false;
				break;
			case B:
				if (c == z)
					cur = State.C;
				else if (c == y)
					cur = State.D;
				else
					return false;
				break;
			case C:
				return false;
			case D:
				if (c == z)
					cur = State.C;
				else
					return false;
				break;
			default:
				return false;
			}
		}
		return cur == State.C;
	}

	enum State {
		A, B, C, D
	}
}

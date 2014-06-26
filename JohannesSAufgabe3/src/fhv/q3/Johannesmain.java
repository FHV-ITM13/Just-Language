package fhv.q3;

public class Johannesmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Johannesmain m = new Johannesmain();
		
		if (m.accept2("zzzzfzxz".toCharArray())) {
			System.out.println("Accept");
		} else {
			System.out.println("Nicht Accept");
		}

	}

	public boolean accept2(char[] tape){
		char[][] states = new char[128][128];
		
		states['A']['z'] = 'A';
		states['A']['x'] = 'B';
		states['B']['y'] = 'D';
		states['B']['z'] = 'C';
		states['D']['z'] = 'C';
		
		char cur = 'A';
		
		for(char c:tape){
			cur = states[cur][c];
			if(cur == 0)
				return false;
		}
		return cur =='C';
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

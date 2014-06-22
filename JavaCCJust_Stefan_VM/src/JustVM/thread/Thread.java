package JustVM.thread;

import JustVM.stack.CallStack;

public class Thread {
	private int pc;
	private CallStack callStack; 
	
	public Thread(int pc) {
		this.pc = pc;
		callStack = new CallStack(100);
	}
	
	public void run() {
		
	}
	

	public void setPc(int pc) {
		this.pc = pc;
	}
	
	public int getPc() {
		return pc;
	}
}

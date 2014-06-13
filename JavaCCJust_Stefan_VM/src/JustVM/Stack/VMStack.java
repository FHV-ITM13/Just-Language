package JustVM.Stack;

import java.util.LinkedList;

public class VMStack {
	private LinkedList<StackFrame> stackFrames;
	
	public VMStack() {
		stackFrames = new LinkedList<StackFrame>();
	}
	
	//TODO return type
	public void createFrame(Object pc, int nrOfParams, int maxOpStackSize, int maxLocals) {
		
	}
	
	public void destroyFrame() {
		
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	//TODO return type
	public void pop() {
		
	}
	
	public void push(Object operand) {
		
	}
	
	public void set(int index, Object value) {
		
	}
	
	//TODO return type
	public void get(int index) {
		
	}
}

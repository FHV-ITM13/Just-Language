package JustVM.thread;

import java.util.LinkedList;

import JustVM.VirtualMachine;
import JustVM.clazz.Method;
import JustVM.stack.CallStack;
import JustVM.stack.StackFrame;

public class Thread {
	private int pc;
	private CallStack callStack; 
	private LinkedList<StackFrame> stackFrames;
	private VirtualMachine vm;
	private Method startMethod;
	
	public Thread(int pc, VirtualMachine vm, Method startMethod) {
		this.pc = pc;
		this.vm = vm;
		this.startMethod = startMethod;

		callStack = new CallStack(4, startMethod.getNrLocals() + startMethod.getNrParams());
		stackFrames = new LinkedList<StackFrame>();
		
		stackFrames.add(new StackFrame(0, startMethod.getStackSize(), startMethod.getNrLocals(), startMethod.getNrParams()));
	}
	
	public void run() {
		pc = vm.execute(pc);
	}
	
	public void push(int value) {
		StackFrame last = stackFrames.getLast();
		callStack.put(last.getSp(), value);
		last.increaseSp();
	}
	
	public int pop() {
		StackFrame last = stackFrames.getLast();
		last.decreaseSp();
		int value = callStack.get(last.getSp());
		//clean stack for better debuggung
		callStack.put(last.getSp(), 0);
		return value;
	}
	
	public int getLocalVarValue(int addr) {
		return callStack.getValue(stackFrames.getLast().getLocalStartAddr() + addr);		
	}
	
	public void setLocalVarValue(int addr, int value ) {
		callStack.setValue(stackFrames.getLast().getLocalStartAddr() + addr, value);	
	}
	
	public void invokeMethod(Method m) {
		//overlay two stackframes, so that the parameters must not be copied
		int startAddr = stackFrames.getLast().getStart() + stackFrames.getLast().getSp() - m.getNrParams();
		stackFrames.getLast().setSp(stackFrames.getLast().getSp() - m.getNrParams());
		
		StackFrame sf = new StackFrame(startAddr, m.getStackSize(), m.getNrLocals(), m.getNrParams());
		stackFrames.addLast(sf);
		
		int currentPc = pc;
		int methodLength = m.getStartAddr() + m.getLength();
		pc = m.getStartAddr();
		
		do {
			pc = vm.execute(pc);
			printStack();
		} while(pc != methodLength);
		
		System.out.println("After method call");
		
		pc = currentPc; //restore pc
	}
	
	public boolean isFinished() {
		return pc == startMethod.getStartAddr() + startMethod.getLength() && stackFrames.size() == 0;
	}

	public void destroyStackFrame() {
		stackFrames.removeLast();
	}

	public void printStack() {
		callStack.printStack();
	}	
}

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

		callStack = new CallStack(5);
		stackFrames = new LinkedList<StackFrame>();
		
		stackFrames.add(new StackFrame(0, startMethod.getStackSize(), startMethod.getNrLocals(), startMethod.getNrParams()));
	}
	
	public void run() {
		pc = vm.execute(pc);
	}
	
	public void push(int value) {
		callStack.push(value);
		stackFrames.getLast().increaseSp();
	}
	
	public int pop() {
		stackFrames.getLast().decreaseSp();
		return callStack.pop();
	}
	
	public int getLocalVarValue(int addr) {
		return callStack.getValue(stackFrames.getLast().getLocalStartAddr() + addr);		
	}
	
	public void setLocalVarValue(int addr, int value ) {
		callStack.setValue(stackFrames.getLast().getLocalStartAddr() + addr, value);	
	}
	
	public void invokeMethod(Method m) {
		//overlay two stackframes, so that the parameters must not be copied
		int startAddr = stackFrames.getLast().getStart() + stackFrames.getLast().getCurrSize() - m.getNrParams();
		StackFrame sf = new StackFrame(startAddr, startMethod.getStackSize(), startMethod.getNrLocals(), startMethod.getNrParams());
		stackFrames.add(sf);
	}
	
	public boolean isFinished() {
		return pc == startMethod.getStartAddr() + startMethod.getLength();
	}

	public void destroyStackFrame() {
		stackFrames.removeLast();
	}	
}

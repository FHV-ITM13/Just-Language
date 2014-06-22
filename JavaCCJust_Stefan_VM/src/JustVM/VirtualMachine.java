package JustVM;

import java.util.ArrayList;
import java.util.List;

import JustVM.clazz.Clazz;
import JustVM.thread.Thread;
import JustVM.constantPool.ConstantPool;

public class VirtualMachine {
	private List<Thread> threads;
	private List<Clazz> clazzes;
	private ConstantPool constantPool;
	private Code code;
	
	public VirtualMachine() {
		threads = new ArrayList<>();
		clazzes = new ArrayList<>();
		constantPool = new ConstantPool();
		code = new Code();
	}
	
	public void run() {
		ClazzLoader.load(this);
		
		Thread mainThread = new Thread();
		execute(0);
	}
	
	public int addConstant(String name, byte[] value, Type type) {
		return constantPool.add(name, value, type);
	}
	
	public void addClazz(Clazz clazz) {
		clazzes.add(clazz);
	}
	
	public void addCodeLine(String line) {
		code.appedLine(line);
	}
	
	public int execute(int pc) {
		
		return 1;
	}
	
	
	

} 

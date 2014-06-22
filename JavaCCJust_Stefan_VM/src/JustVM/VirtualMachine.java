package JustVM;

import java.util.ArrayList;
import java.util.List;

import JustVM.clazz.Clazz;
import JustVM.clazz.Method;
import JustVM.clazzLoader.ClazzLoader;
import JustVM.thread.Thread;
import JustVM.type.Type;
import JustVM.code.Code;
import JustVM.constantPool.Constant;
import JustVM.constantPool.ConstantPool;

public class VirtualMachine {
	private List<Thread> threads;
	private List<Clazz> clazzes;
	private ConstantPool constantPool;
	private Code code;
	private Thread currThread;

	public VirtualMachine() {
		threads = new ArrayList<>();
		clazzes = new ArrayList<>();
		constantPool = new ConstantPool();
		code = new Code();
	}

	public void run() {
		ClazzLoader.load(this);

		// to make this work main method must always be the first
		// method in the code
		Thread mainThread = new Thread(0, this, clazzes.get(0).getMethod(0));
		threads.add(mainThread);
		currThread = mainThread;

		while(!mainThread.isFinished()) {
			mainThread.run();
		}
	}

	public int addConstant(String name,int value, Type type) {
		//TODO type of value should be byte[]
		return constantPool.add(name, value, type);
	}

	public void addClazz(Clazz clazz) {
		clazzes.add(clazz);
	}

	public void addCodeLine(String line) {
		code.appedLine(line);
	}

	public String getCodeLine(int line) {
		return code.getLine(line);
	}
	
	public int execute(int pc) {
		// 0 = OpCode 1 = index of var, constant, method,...
		String[] line = getCodeLine(pc).split(" ");

		// TODO add all possible OpCodes
		switch (line[0]) {
		case "LDC_W":
			ldcW(Integer.parseInt(line[1]));
			break;
		case "ILOAD":
			iLoad(Integer.parseInt(line[1]));
			break;
		case "ISTORE":
			iStore(Integer.parseInt(line[1]));
			break;
		case "GETSTATIC":
			getStatic(Integer.parseInt(line[1]));
			break;
		case "PUTSTATIC":
			getStatic(Integer.parseInt(line[1]));
			break;
		case "INVOKESTATIC":
			invokeStatic(clazzes.get(0).getMethod(Integer.parseInt(line[1])));
			break;

		case "GOTO":
			
			break;

		case "IADD":
			getStatic(Integer.parseInt(line[1]));
			break;

		case "IF_ICMPLT":
			getStatic(Integer.parseInt(line[1]));
			break;
		case "IF_ICMPLE":
			getStatic(Integer.parseInt(line[1]));
			break;

		case "RETURN":
			getStatic(Integer.parseInt(line[1]));
			break;
		case "IRETURN":
			getStatic(Integer.parseInt(line[1]));
			break;
		case "NOP":
			getStatic(Integer.parseInt(line[1]));
			break;
		default:
			System.err.println("OpCode not available " + line[0]);
		}

		return pc++;
	}
	
	private void iLoad(int addr) {
		int val = currThread.getLocalVarValue(addr);
		currThread.push(val);
	}
	
	private void iStore(int addr) {
		int val = currThread.pop();
		currThread.setLocalVarValue(addr, val);
	}
	
	private void getStatic(int addr) {
		
	}
	
	private void putStatic(int addr) {
		
	}
	
	private void ldcW(int addr) {
		Constant c = constantPool.getById(addr);
		currThread.push(c.getValue());
	}
	
	private void invokeStatic(Method m) {
		currThread.invokeMethod(m);
	}
	
	private void goTo(int addr) {
		
	}
	
	private void iAdd() {
		
	}
	
	private void ifLess() {
		
	}
	
	private void ifLessEqual() {
		 
	}
	
	private void simpleReturn() {
		
	}

	private void iReturn() {
		
	}
}

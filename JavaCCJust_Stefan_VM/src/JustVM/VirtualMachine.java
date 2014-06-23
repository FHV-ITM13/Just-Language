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
			putStatic(Integer.parseInt(line[1]));
			break;
		case "INVOKESTATIC":
			invokeStatic(clazzes.get(0).getMethod(Integer.parseInt(line[1])));
			break;

		case "GOTO":
			return goTo(line[1]);

		case "IADD":
			iAdd();
			break;
		case "ISUB":
			iSub();
			break;
		case "IMUL":
			iMul();
			break;
		case "IDIV":
			iDiv();
			break;
		case "IAND":
			iAnd();
			break;
		case "IOR":
			iOr();
			break;

		case "IF_ICMPEQ": //compare equal
			return ifEqual(pc, line[1]);
		case "IF_ICMPNE": //compare not equal
			return ifNotEqual(pc, line[1]);
		case "IF_ICMPGE": //value2 is greater than value1
			return ifGreaterEqual(pc, line[1]);
		case "IF_ICMPGT": //value2 is greater than or equal to value1
			return ifGreater(pc, line[1]);
		case "IF_ICMPLE": //value2 is less than value1
			return ifLessEqual(pc, line[1]);
		case "IF_ICMPLT": //value2 is less than or equal to value1
			return ifLess(pc, line[1]);
			
		case "RETURN":
			simpleReturn();
			break;
		case "IRETURN":
			iReturn();
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
		int val = constantPool.getById(addr).getValue();
		currThread.push(val);
	}
	
	private void putStatic(int addr) {
		int val = currThread.pop();
		constantPool.getById(addr).setValue(val);
	}
	
	private void ldcW(int addr) {
		Constant c = constantPool.getById(addr);
		currThread.push(c.getValue());
	}
	
	private void invokeStatic(Method m) {
		currThread.invokeMethod(m);
	}
	
	private int goTo(String label) {
		int newPc = code.getCodeLineNrByLabel(label);
		
		if(newPc == -1) throw new RuntimeException("Invalid label " + label);
		
		return newPc;
	}
	
	private void iAdd() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		currThread.push(val1 + val2);
	}
	
	private void iSub() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();

		currThread.push(val2 - val1);				
	}

	private void iOr() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();

		currThread.push(val1 | val2);			
	}
	
	private void iAnd() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();

		currThread.push(val1 & val2);			
	}

	private void iDiv() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val1 == 0) throw new RuntimeException("Division by zero!");
		
		currThread.push(val2 / val1);		
	}

	private void iMul() {
		int val1 = currThread.pop();
		int val2 = currThread.pop();

		currThread.push(val2 * val1);				
	}
	
	private int ifLess(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 < val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		}
		
		return pc++;
	}
	
	private int ifLessEqual(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 <= val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		} 
		
		return pc++;
	}
	
	private int ifEqual(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 == val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		}

		return pc++;
	}

	private int ifNotEqual(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 != val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		}
		
		return pc++;
	}

	private int ifGreaterEqual(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 >= val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		}

		return pc++;
	}

	private int ifGreater(int pc, String label) {
		int val1 = currThread.pop();
		int val2 = currThread.pop();
		
		if(val2 > val1) {
			int newPc = code.getCodeLineNrByLabel(label);
			
			if(newPc == -1) throw new RuntimeException("Invalid label " + label);
			
			return newPc;
		}
		
		return pc++;
	}
	
	private void simpleReturn() {
		currThread.destroyStackFrame();
	}

	private void iReturn() {
		int val = currThread.pop();
		currThread.destroyStackFrame();
		currThread.push(val);
	}
}

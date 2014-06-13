package JustVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import JustVM.Code.*;
import JustVM.Stack.VMStack;

public class JustVM {

	public static void main(String[] args) {
		JustVM vm = new JustVM();
		vm.run("res/ATest.ojus");
	}

	private VMStack vmStack;

	public JustVM() {
		vmStack = new VMStack();
	}

	public void run(String filename) {
		// 1 parse file
		List<Method> methods = new ArrayList<Method>();
		
		Method main = new Method("main", Type.voidType, 0, 1, Arrays.asList("LDC_W 12", "LDC_W 13", "PUTSTATIC 7", "L1: NOP",
																			"ILOAD 0", "GETSTATIC 7", "IF_ICMPLT L2", "ILOAD 0", 
																			"INVOKESTATIC 17", "ISTORE 0", "GOTO L1", "L2: NOP", "RETURN"));
		
		Method doIt = new Method("main", Type.intType, 1, 0, Arrays.asList("ILOAD 0", "GETSTATIC 7", "IF_ICMPLE L3", "ILOAD 0",
																			"LDC_W 18", "IADD", "ISTORE 0", "GOTO L4", "L3: NOP", 
																			"ILOAD 0", "LDC_W 19", "IADD", "ISTORE 0", "L4: NOP", "ILOAD 0", "IRETURN"));
		
		methods.add(main);
		methods.add(doIt);
		
	}
}

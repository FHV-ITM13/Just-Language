package JustVM;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import JustVM.clazz.Clazz;
import JustVM.clazz.ClazzVar;
import JustVM.clazz.Method;

public class ClazzLoader {

	public static void load(VirtualMachine vm) {
		//TODO should parse clazzfile
		vm.addConstant("0", BigInteger.valueOf(0).toByteArray(), Type.intType);
		vm.addConstant("10", BigInteger.valueOf(10).toByteArray(), Type.intType);
		vm.addConstant("1", BigInteger.valueOf(1).toByteArray(), Type.intType);
		vm.addConstant("2", BigInteger.valueOf(2).toByteArray(), Type.intType);

		loadCode(vm);

		Clazz c = new Clazz("RealTest");
		Method main = new Method(0, 0, 1, 2, 14);
		Method doIt = new Method(14, 1, 0, 2, 16);
		c.addMethod(main);
		c.addMethod(doIt);
		
		c.addClazzVar(new ClazzVar("globalLimit", Type.intType));
		
		vm.addClazz(c);
		
	}
	
	private static void loadCode(VirtualMachine vm) {
		//Constants IDs already adapted to IDs of constants in constant pool
		List<String> codes = Arrays.asList("LDC_W 0", "ISTORE 0", "LDC_W 1", "PUTSTATIC 7", "L1: NOP",
					"ILOAD 0", "GETSTATIC 7", "IF_ICMPLT L2", "ILOAD 0", 
					"INVOKESTATIC 17", "ISTORE 0", "GOTO L1", "L2: NOP", "RETURN",
					"ILOAD 0", "GETSTATIC 7", "IF_ICMPLE L3", "ILOAD 0",
					"LDC_W 2", "IADD", "ISTORE 0", "GOTO L4", "L3: NOP", 
					"ILOAD 0", "LDC_W 3", "IADD", "ISTORE 0", "L4: NOP", "ILOAD 0", "IRETURN");
		
		for (String c : codes) {
			vm.addCodeLine(c);
		}
	}
}

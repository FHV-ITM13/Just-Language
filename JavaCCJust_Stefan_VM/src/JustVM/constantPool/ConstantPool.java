package JustVM.constantPool;

import java.util.HashMap;

import JustVM.type.Type;

public class ConstantPool {
	private HashMap<Integer, Constant> constants;
	
	public ConstantPool() {
		constants = new HashMap<>();
	}
	
	//TODO value should be byte[]
	public int add(int addr, String name, int value, Type type) { 
		Constant c = new Constant(name, addr, value, type);
		
		constants.put(c.getAddr(), c);
		
		return c.getAddr();
	}
	
	public Constant getByName(String name) {
		for (Constant temp : constants.values()) {
			if(temp.getName().equals(name)) {
				return temp;
			}
		}
		
		return null;
	}
	
	public Constant getById(int id) {
		return constants.get(id);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Constant c : constants.values()) {
			sb.append(c.getName()).append(": ").append(c.getValue()).append("\n"); 
		}
		
		return sb.toString();
	}
}

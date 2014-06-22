package JustVM.constantPool;

import java.util.HashMap;

import JustVM.Type;

public class ConstantPool {
	private HashMap<Integer, Constant> constants;
	
	public ConstantPool() {
		constants = new HashMap<>();
	}
	
	public int add(String name, byte[] value, Type type) {
		Constant c = new Constant(name, constants.size(), value, type);
		
		constants.put(c.getId(), c);
		
		return c.getId();
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
}

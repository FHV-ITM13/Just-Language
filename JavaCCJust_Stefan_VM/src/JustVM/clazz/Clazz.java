package JustVM.clazz;

import java.util.HashMap;

public class Clazz {
	private String name;
	private HashMap<Integer, Method> methods;
	private HashMap<String, ClazzVar> clazzVars;
	
	public Clazz(String name) {
		this.name = name;
		methods = new HashMap<Integer, Method>();
		clazzVars = new HashMap<String, ClazzVar>();
	}
	
	public void addMethod(Method method) {
		methods.put(method.getIndex(), method);
	}
	
	public Method getMethod(Integer index) {
		return methods.get(index);
	}
	
	public void addClazzVar(ClazzVar clazzVar) {
		clazzVars.put(clazzVar.getName(), clazzVar);
	}
	
	public ClazzVar getClazzVar(String name) {
		return clazzVars.get(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

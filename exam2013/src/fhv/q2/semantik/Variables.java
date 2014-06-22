package fhv.q2.semantik;

import java.util.HashMap;

public class Variables {
	private static HashMap<String, Integer> vars = new HashMap<String, Integer>();

	public static void setVar(String name, String value) {
		vars.put(name, Integer.parseInt(value));
	}

	public static Integer getVar(String name) {
		return vars.get(name);
	}

	public static void clear() {
		vars.clear();
	}
}

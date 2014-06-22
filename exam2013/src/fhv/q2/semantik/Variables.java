package fhv.q2.semantik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fhv.q2.ParseException;

public class Variables {
	private static List<String> varNames = new ArrayList<>();
	private static HashMap<String, Integer> vars = new HashMap<String, Integer>();
	private static HashMap<String, Boolean> usage = new HashMap<String, Boolean>();

	public static void setVar(String name, String value) throws ParseException {
		if (vars.containsKey(name)) {
			throw new ParseException(String.format(
					"Doppelte Definition von Variable: %s", name));
		}
		varNames.add(name);
		usage.put(name, false);
		vars.put(name, Integer.parseInt(value));
	}

	public static Integer getVar(String name) throws ParseException {
		if (!vars.containsKey(name)) {
			throw new ParseException(String.format(
					"Verwendung einer unedfinierten Variable: %s", name));
		}
		usage.put(name, true);

		return vars.get(name);
	}

	public static boolean check() {
		List<String> unused = new ArrayList<String>();

		for (int i = 0; i < varNames.size(); i++) {
			if (!usage.get(varNames.get(i))) {
				unused.add(varNames.get(i));
			}
		}

		if (!unused.isEmpty()) {
			System.out
					.println(String.format(
							"WARNING: Nicht verwendete Variable: %s",
							unused.toString()));
		}

		return unused.isEmpty();
	}

	public static void clear() {
		vars.clear();
		varNames.clear();
		usage.clear();
	}
}

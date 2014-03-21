package fhv.semantic.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Namelist {
	public static Namelist nameList = new Namelist();

	private Namelist() {
	}

	private HashMap<String, Integer> spixs = new HashMap<>();

	private List<String> names = new ArrayList<>();

	public Integer insert(String name) throws Exception {
		if (names.contains(name)) {
			throw new Exception("Duplicate Name");
		}
		names.add(name);
		return names.size() - 1;
	}

	public Integer lookup(String name) {
		return spixs.get(name);
	}

	public String getNameOf(Integer spix) {
		return names.get(spix);
	}

	public Integer getIndexOf(String name) {
		return spixs.get(name);
	}
}

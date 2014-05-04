package fhv.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NameList {
	public static NameList nameList = new NameList();

	private NameList() {
	}

	private HashMap<String, Integer> spixs = new HashMap<>();

	private List<String> names = new ArrayList<>();

	public Integer insert(String name) {
		if (spixs.containsKey(name)) {
			return spixs.get(name);
		} else {
			names.add(name);
			Integer spix = names.size() - 1;
			spixs.put(name, spix);
			return spix;
		}
	}

	public Integer lookup(String name) {
		if (!spixs.containsKey(name)) {
			insert(name);
		}
		return spixs.get(name);
	}

	public String getNameOf(Integer spix) {
		return names.get(spix);
	}

	public Integer getIndexOf(String name) {
		return spixs.get(name);
	}
}

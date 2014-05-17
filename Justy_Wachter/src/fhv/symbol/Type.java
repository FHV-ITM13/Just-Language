package fhv.symbol;

public class Type {
	public static Type Void = new Type("void", "V");
	public static Type Int = new Type("int", "I");
	public static Type Bool = new Type("bool", "B");

	private String name;
	private String shortName;

	private Type(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}
}

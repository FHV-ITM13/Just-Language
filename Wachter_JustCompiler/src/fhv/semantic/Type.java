package fhv.semantic;


public class Type {
	public enum Kind {
		undefType, voidType, boolType, intType, fieldType
	};

	private Type(String name, Type.Kind kind) {
		try {
			this.spix = NameList.nameList.insert(name);
			this.name = name;
			this.kind = kind;
		} catch (Exception ex) {
		}
	}

	public String name;
	public Type.Kind kind;
	public int spix;

	public static Type intType = new Type("int", Type.Kind.intType);
}

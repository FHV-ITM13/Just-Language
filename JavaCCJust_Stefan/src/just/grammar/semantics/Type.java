package just.grammar.semantics;

public class Type {
	public enum Kind {
		undefKind, voidKind, boolKind, intKind, fieldKind
	};

	public Type(String string, Type intType2) {
	}

	public Kind kind;
	public int spix;
	
	public static Type undefType = new Type("undef", Type.undefType);
	public static Type intType = new Type("int", Type.intType);
	public static Type booleanType = new Type("boolean", Type.booleanType);
	public static Type arrayType = new Type("array", Type.arrayType);
	public static Type voidType = new Type("void", Type.voidType);
}
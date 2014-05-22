package just.grammar.semantics;

public class Type {
	public enum Kind {
		undefType, voidType, boolType, intType
	}
	
	public String name;
	public Type.Kind kind;
	public int spix;    

    private Type(String s, Kind kind) {
		try {
			this.spix = NameList.NameList.insert(s);
	        this.name = s;
			this.kind = kind;
		} catch (Exception ex) {
		}
    }

    public String toString(){
       return name;
    }

	public static Type undefinedType = new Type("undefined", Type.Kind.undefType);
	public static Type voidType = new Type("void", Type.Kind.voidType);
	public static Type boolType = new Type("bool", Type.Kind.boolType);
	public static Type intType = new Type("int", Type.Kind.intType);
}
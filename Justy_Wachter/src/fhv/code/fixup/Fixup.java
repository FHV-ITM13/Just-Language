package fhv.code.fixup;

import fhv.ParseException;

public abstract class Fixup {
	protected Object op;

	public abstract boolean fix() throws ParseException;

	public Object getOp() {
		return op;
	}
	
	@Override
	public String toString() {
		try {
			if (!this.fix()) {
				throw new RuntimeException("Cannot fix marked place");
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot fix marked place");
		}
		return op.toString();
	}
}

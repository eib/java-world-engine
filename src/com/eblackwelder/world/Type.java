package com.eblackwelder.world;

public class Type {
	
	private final String name;

	public Type(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		boolean isEqual = false;
		if (other instanceof Type) {
			Type otherType = (Type) other;
			return otherType != null && this.name.equals(otherType.name);
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return 23 + getClass().hashCode() * 33 + this.name.hashCode() * 17;
	}
	
}

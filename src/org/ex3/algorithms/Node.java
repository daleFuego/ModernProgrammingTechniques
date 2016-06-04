package org.ex3.algorithms;

public class Node {

	public String id;
	public String name;

	public Node(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		if (id == null) {
			if (((Node) obj).id != null) {
				return false;
			}
		} else if (!id.equals(((Node) obj).id)) {
			return false;
		}
		
		return true;
	}

}
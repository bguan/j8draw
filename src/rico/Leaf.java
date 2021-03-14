package rico;

public class Leaf {

	Branch parent;
	LeafColor leaf_color;

	public Leaf(LeafColor lc, Branch b) {

		// Parent
		parent = b;

		leaf_color = lc;
	}

	String myName = "<null-leaf-name>";

	public void set_name(String n) {
		myName = n;
	}

	String get_name() {
		return myName;
	}

	public enum LeafColor {
		NONE, GREEN, YELLOW, RED, BROWN
	}

	public void print_leaf_info() {
		System.out.println("    Leaf: " + myName + " Parent: " + parent.get_name());
	}

}

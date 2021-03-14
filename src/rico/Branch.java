package rico;

import java.util.ArrayList;
import java.util.List;

public class Branch {

	List<Leaf> leaves = new ArrayList<Leaf>();
	Tree parent;

	static String leafnames[] = { "larry", "lou", "laura", "lucky" };

	public Branch(int x, Tree t) {

		// Parent tree
		parent = t;

		for (int i = 0; i < x; i++) {

			Leaf.LeafColor lc;
			switch (i % 4) {
			case 0:
				lc = Leaf.LeafColor.GREEN;
				break;
			case 1:
				lc = Leaf.LeafColor.RED;
				break;
			case 2:
				lc = Leaf.LeafColor.YELLOW;
				break;
			case 3:
				lc = Leaf.LeafColor.BROWN;
				break;
			default:
				lc = Leaf.LeafColor.NONE;
			}

			Leaf L = new Leaf(lc, this);
			leaves.add(L);
		}
	}

	// Class variable
	String myName = "<null-branch-name>";

	public void set_name(String n) {
		myName = n;
	}

	String get_name() {
		return myName;
	}

	public void print_branch_info() {
		System.out.println("  Branch: " + myName + " Parent: " + parent.get_name());
		for (Leaf L : leaves) {
			L.print_leaf_info();
		}
	}

}

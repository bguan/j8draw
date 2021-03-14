package rico;

import java.util.*;

public class Tree {

	List<Branch> branches = new ArrayList<Branch>();

	static String bnames[] = { "bob", "bill", "betty", "barak" };

	public Tree(int x) {

		for (int i = 0; i < x; i++) {

			Branch b = new Branch(4, this);

			// Add to our array
			branches.add(b);
		}
	}

	public void Draw() {
		print_tree_info();
	}

	// Class variable
	String myName = "Thaddeus";

	public void set_name(String n) {
		myName = n;
	}

	String get_name() {
		return myName;
	}

	public void print_tree_info() {
		System.out.println("Tree: " + myName + " Parent: " + null);
		for (Branch b : branches) {
			b.print_branch_info();
		}
	}

}

// 			b.set_name(bnames[i] + "(" + i + ")");

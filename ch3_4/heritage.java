/*
 ID: antonio13
 LANG: JAVA
 PROG: heritage
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author antonio081014
 * @time Jul 24, 2013, 7:33:12 PM
 */
public class heritage {

	public static void main(String[] args) {
		heritage main = new heritage();
		main.run();
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader in = new BufferedReader(
					new FileReader("heritage.in"));
			PrintWriter out = new PrintWriter(new FileWriter("heritage.out"));
			String inorder = in.readLine();
			String preorder = in.readLine();
			Tree tree = new Tree(inorder, preorder);
			out.write(tree.PostOrder() + "\n");
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Tree {
	public Node root;

	public Tree(String inorder, String preorder) {
		root = SubTree(inorder, preorder);
	}

	public Node SubTree(String inorder, String preorder) {
		if (inorder.length() == 0)
			return null;
		if (inorder.length() == 1)
			return new Node(inorder.charAt(0));

		Node node = new Node(preorder.charAt(0));
		int index = inorder.indexOf(preorder.charAt(0));
		String inorderLeft = inorder.substring(0, index);
		String inorderRight = inorder.substring(index + 1);
		String preorderLeft = preorder.substring(1, 1 + inorderLeft.length());
		String preorderRight = preorder.substring(1 + inorderLeft.length());
		// System.out.println("Inorder: " + inorderLeft + ", " + inorderRight);
		// System.out.println("Preorder: " + preorderLeft + ", " + preorderRight);
		node.left = SubTree(inorderLeft, preorderLeft);
		node.right = SubTree(inorderRight, preorderRight);
		return node;
	}

	public String PostOrder() {
		return PostOrder(root);
	}

	public String PostOrder(Node node) {
		String ret = "";
		if (node.left != null)
			ret += PostOrder(node.left);
		if (node.right != null)
			ret += PostOrder(node.right);
		if (node != null) {
			ret += node.data;
		}
		return ret;
	}
}

class Node {
	public char data;
	public Node left;
	public Node right;

	public Node(char d) {
		this.data = d;
		left = null;
		right = null;
	}
}

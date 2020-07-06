package lab7;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BSTree tree = new BSTree();

		BufferedReader br = new BufferedReader(new FileReader("BST_input.txt"));
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");

			if (lines[0].equals("i")) {
				tree.InsertNode(Integer.parseInt(lines[1]));
			} else if (lines[0].equals("d")) {
				tree.DeleteNode(Integer.parseInt(lines[1]));
			} else if (lines[0].equals("f")) {
				if (tree.FindNode(Integer.parseInt(lines[1])))
					System.out.println(lines[1] + " is in the tree");
				else
					System.out.println(lines[1] + " is not in the tree");
			} else if (lines[0].equals("pi")) {
				tree.PrintInorder(tree.root);
				System.out.println();
			} else if (lines[0].equals("pr")) {
				tree.PrintPreorder(tree.root);
				System.out.println();
			} else if (lines[0].equals("po")) {
				tree.PrintPostorder(tree.root);
				System.out.println();
			}
		}

		br.close();
	}
}

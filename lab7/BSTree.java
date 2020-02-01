package lab7;

public class BSTree { // TreeNode �� �̷���� �迭 = Tree
	TreeNode root;

	public boolean InsertNode(int key) {
		if (FindNode(key)) {
			System.out.println("Element " + key + " already exists");
			return false;
		}

		TreeNode nowNode = new TreeNode(key);
		if (root == null) { // ��Ʈ�� �����ϴ� ���
			root = nowNode;
			return true;
		}
		TreeNode place = root;
		TreeNode parent = null;

		while (true) { // ���ϱ��� �ݺ� (����ִ� �ڸ� �°� ã�ư� �� ����)
			parent = place;
			if (place.value < key) { // Ű���� �� ��� ������ ũ�� ���������� ������
				place = place.right;
				if (place == null) { // �������� ������� ��� insert
					place = nowNode;
					parent.right = place;
					return true;
				}
			} else { // Ű ���� �� ��� ������ ���� ��� �������� ������
				place = place.left;
				if (place == null) { // ������ ������� ��� insert
					place = nowNode;
					parent.left = place;
					return true;
				}
			}
		}
	}

	public boolean DeleteNode(int key) {
		if (!FindNode(key)) {
			System.out.println("Element " + key + " not found");
			return false;
		}
		TreeNode delNode = root;
		TreeNode parent = null;
		boolean right = true;

		while (delNode.value != key) { // Key�� ��Ʈ�� �Ǹ� �ߴ�, right�� key�� parent�� ����� �ڽ����� ǥ��
			parent = delNode;
			if (delNode.value < key) { // ������ Ž��
				right = true;
				delNode = delNode.right;
			} else { // ���� Ž��
				right = false;
				delNode = delNode.left;
			}
		}

		if (delNode.left == null && delNode.right == null) { // �ڽ��� ����
			if (delNode == root) // ���� ���� Ʈ�� ��ü�� ��Ʈ
				root = null;
			else { // ��Ʈ�� �ƴ����� �ڽ��� ���� �������
				delNode = null;
				if (right) // delNode�� ������ �ڽ��̶��
					parent.right = null; // �θ����� �������� ����
				else // ���� �ڽ��̶�� �θ����� ������ ����
					parent.left = null;
			}
		}

		else if (delNode.left != null && delNode.right == null) { // ���� �ڽ��� �ִ� ���
			if (root == delNode)
				root = delNode.left; // �������� Ʈ�� ��ü�� ��Ʈ. ������ ���� ���� �����´�.
			else {
				if (right) {// ��Ʈ�� �ƴϰ�, �θ����� ������ ����̸�, ���� �ڽ��� ������ �ִ�.
					parent.right = delNode.left;
					delNode.left = null;
				} else { // ��Ʈ�� �ƴϰ�, �θ����� ������ ����̸�, ������ �ڽ��� ������ �ִ�.
					parent.left = delNode.left;
					delNode.left = null;
				}
			}
		} else if (delNode.left == null && delNode.right != null) { // ������ �ڽ��� �ִ� ���
			if (root == delNode) // �������� Ʈ�� ��ü�� ��Ʈ, ������ ������ ���� �����´�.
				root = delNode.right;
			else {
				if (right) {// ��Ʈ�� �ƴϰ�, �θ����� ������ ����̸�, ������ �ڽ��� ������ �ִ�.
					parent.right = delNode.right;
					delNode.right = null;
				} else {// ��Ʈ�� �ƴϰ�, �θ����� ���� ����̸�, ������ �ڽ��� ������ �ִ�.
					parent.left = delNode.right;
					delNode.right = null;
				}
			}
		} else if (delNode.left != null && delNode.right != null) { // �ڽ��� �� �� �ִ� ���
			if (root == delNode)
				FindMinDelRoot(root);
			else {
				if (right) { // �θ����� ������ ����̸� �ڽ� �Ѵ� �ִ�
					parent.right = FindMinDelRoot(delNode); // ������ ����Ʈ������ �ּҰ� �����ͼ� �θ��� ������ �ڽ����� ����
				} else {
					parent.left = FindMinDelRoot(delNode); // ������ ����Ʈ������ �ּҰ� �����ͼ� �θ��� ���� �ڽ����� ����
				}
			}
		}
		return true;
	}

	public TreeNode FindMinDelRoot(TreeNode node) {
		// �� ��带 �����ϰ� ������ ����Ʈ������ �ּڰ��� ã�� ��Ʈ�� �÷��ִ� ����. �ּҳ�带 ��ȯ�Ѵ�.
		TreeNode childNode = node.right;
		TreeNode minNode = childNode;
		TreeNode minNodeP = null;

		while (childNode != null) { // ������ ����Ʈ���� �ִ� ��쿡
			minNodeP = minNode; // �� ��尡 �θ� �ǰ�
			minNode = childNode; // �����ڽĳ�尡 �� ��尡 �ǰ�
			childNode = childNode.left; // �����ڽĳ��� �ٽ� �������� �� �� �� ������
		}
		// �ݺ����� ������ minNode���� �ּҳ�尡, childNode���� �� �ּ� ����� ���� �ڽĺκ� (null)�� �� �ִ�.
		// ������ ����Ʈ���� ��Ʈ�� �ּҶ�� ������ ����Ʈ���� ��Ʈ�� ��ȯ������
		// ��Ʈ�� �ּҰ� �ƴ� ��� �ڸ��� �ٲ��ִ� �۾� �ʿ�

		if (minNode != node.right) {
			minNodeP.left = minNode.right; // �ּҰ��� �������� (null�̵� �ƴϵ�) �ּҰ� �θ����� ���� �ڽ��� �ȴ� (������ �� ���� ���̹Ƿ�)
			// ���⼭ ���� �� delete�� �̷������ ��
			minNode.right = node.right; // �ּҰ��� �ö󰡼� ��尡 �ȴ�. �� ����� ������ �ڽ��� �ּҰ� ����� ������ �ڽ��� �ȴ�.
		}

		return minNode; // ������ �ڽ��� ���ٸ� �� ��尡 �ּҳ��

	}

	public boolean FindNode(int key) {
		if (root == null)
			return false;
		TreeNode nowNode = root;

		while (nowNode != null) {
			if (nowNode.value == key) // �� ��� ���� Ű�� �����ϸ� ã�� �ͤ� true ��ȯ
				return true;
			else if (nowNode.value < key) // Ű ������ �� ��尪�� ����
				nowNode = nowNode.right; // �� ����� ������ �ڽ� Ž��
			else if (nowNode.value > key) // Ű ������ �� ��尪�� ŭ
				nowNode = nowNode.left; // �� ����� ���� �ڽ� Ž��
		}
		return false;
	}

	public void PrintInorder(TreeNode root) {
		if (root != null) {
			PrintInorder(root.left); // ���ʺ���
			System.out.print(root.value + " "); // ����Ʈ. ���� ����Ʈ �ϰ� ������ root(�θ�) ����Ʈ �ϰ� ��
			PrintInorder(root.right); // �� �� ������ ����Ʈ
		}
	}

	public void PrintPreorder(TreeNode root) {
		if (root != null) {
			System.out.print(root.value + " ");
			PrintInorder(root.left);
			PrintInorder(root.right);
		}
	}

	public void PrintPostorder(TreeNode root) {
		if (root != null) {
			PrintInorder(root.left);
			PrintInorder(root.right);
			System.out.print(root.value + " ");
		}
	}
}

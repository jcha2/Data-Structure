package lab7;

public class BSTree { // TreeNode 로 이루어진 배열 = Tree
	TreeNode root;

	public boolean InsertNode(int key) {
		if (FindNode(key)) {
			System.out.println("Element " + key + " already exists");
			return false;
		}

		TreeNode nowNode = new TreeNode(key);
		if (root == null) { // 루트에 삽입하는 경우
			root = nowNode;
			return true;
		}
		TreeNode place = root;
		TreeNode parent = null;

		while (true) { // 리턴까지 반복 (비어있는 자리 맞게 찾아갈 때 까지)
			parent = place;
			if (place.value < key) { // 키값이 현 노드 값보다 크면 오른쪽으로 내려감
				place = place.right;
				if (place == null) { // 오른쪽이 비어있을 경우 insert
					place = nowNode;
					parent.right = place;
					return true;
				}
			} else { // 키 값이 현 노드 값보다 작은 경우 왼쪽으로 내려감
				place = place.left;
				if (place == null) { // 왼쪽이 비어있을 경우 insert
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

		while (delNode.value != key) { // Key가 루트가 되면 중단, right로 key가 parent의 어느쪽 자식인지 표현
			parent = delNode;
			if (delNode.value < key) { // 오른쪽 탐색
				right = true;
				delNode = delNode.right;
			} else { // 왼쪽 탐색
				right = false;
				delNode = delNode.left;
			}
		}

		if (delNode.left == null && delNode.right == null) { // 자식이 없음
			if (delNode == root) // 삭제 값은 트리 전체의 루트
				root = null;
			else { // 루트는 아니지만 자식이 없는 리프노드
				delNode = null;
				if (right) // delNode가 오른쪽 자식이라면
					parent.right = null; // 부모노드의 오른쪽을 제거
				else // 왼쪽 자식이라면 부모노드의 왼쪽을 제거
					parent.left = null;
			}
		}

		else if (delNode.left != null && delNode.right == null) { // 왼쪽 자식이 있는 경우
			if (root == delNode)
				root = delNode.left; // 삭제값은 트리 전체의 루트. 간단히 왼쪽 값만 가져온다.
			else {
				if (right) {// 루트는 아니고, 부모노드의 오른쪽 노드이며, 왼쪽 자식을 가지고 있다.
					parent.right = delNode.left;
					delNode.left = null;
				} else { // 루트는 아니고, 부모노드의 오른쪽 노드이며, 오른쪽 자식을 가지고 있다.
					parent.left = delNode.left;
					delNode.left = null;
				}
			}
		} else if (delNode.left == null && delNode.right != null) { // 오른쪽 자식이 있는 경우
			if (root == delNode) // 삭제값은 트리 전체의 루트, 간단히 오른쪽 값만 가져온다.
				root = delNode.right;
			else {
				if (right) {// 루트는 아니고, 부모노드의 오른쪽 노드이며, 오른쪽 자식을 가지고 있다.
					parent.right = delNode.right;
					delNode.right = null;
				} else {// 루트는 아니고, 부모노드의 왼쪽 노드이며, 오른쪽 자식을 가지고 있다.
					parent.left = delNode.right;
					delNode.right = null;
				}
			}
		} else if (delNode.left != null && delNode.right != null) { // 자식이 둘 다 있는 경우
			if (root == delNode)
				FindMinDelRoot(root);
			else {
				if (right) { // 부모노드의 오른쪽 노드이며 자식 둘다 있다
					parent.right = FindMinDelRoot(delNode); // 오른쪽 서브트리에서 최소값 가져와서 부모의 오른쪽 자식으로 설정
				} else {
					parent.left = FindMinDelRoot(delNode); // 오른쪽 서브트리에서 최소값 가져와서 부모의 왼쪽 자식으로 설정
				}
			}
		}
		return true;
	}

	public TreeNode FindMinDelRoot(TreeNode node) {
		// 현 노드를 삭제하고 오른쪽 서브트리에서 최솟값을 찾아 루트로 올려주는 연산. 최소노드를 반환한다.
		TreeNode childNode = node.right;
		TreeNode minNode = childNode;
		TreeNode minNodeP = null;

		while (childNode != null) { // 오른쪽 서브트리가 있는 경우에
			minNodeP = minNode; // 현 노드가 부모가 되고
			minNode = childNode; // 왼쪽자식노드가 현 노드가 되고
			childNode = childNode.left; // 왼쪽자식노드는 다시 왼쪽으로 한 층 더 내려감
		}
		// 반복문을 나오면 minNode에는 최소노드가, childNode에는 그 최소 노드의 왼쪽 자식부분 (null)이 들어가 있다.
		// 오른쪽 서브트리의 루트가 최소라면 오른쪽 서브트리의 루트를 반환하지만
		// 루트가 최소가 아닐 경우 자리를 바꿔주는 작업 필요

		if (minNode != node.right) {
			minNodeP.left = minNode.right; // 최소값의 오른쪽이 (null이든 아니든) 최소값 부모노드의 왼쪽 자식이 된다 (무조건 더 적은 값이므로)
			// 여기서 원래 값 delete가 이루어지는 것
			minNode.right = node.right; // 최소값은 올라가서 노드가 된다. 현 노드의 오른쪽 자식이 최소값 노드의 오른쪽 자식이 된다.
		}

		return minNode; // 오른쪽 자식이 없다면 그 노드가 최소노드

	}

	public boolean FindNode(int key) {
		if (root == null)
			return false;
		TreeNode nowNode = root;

		while (nowNode != null) {
			if (nowNode.value == key) // 현 노드 값과 키값 동일하면 찾은 것ㅡ true 반환
				return true;
			else if (nowNode.value < key) // 키 값보다 현 노드값이 작음
				nowNode = nowNode.right; // 현 노드의 오른쪽 자식 탐색
			else if (nowNode.value > key) // 키 값보다 현 노드값이 큼
				nowNode = nowNode.left; // 현 노드의 왼쪽 자식 탐색
		}
		return false;
	}

	public void PrintInorder(TreeNode root) {
		if (root != null) {
			PrintInorder(root.left); // 왼쪽부터
			System.out.print(root.value + " "); // 프린트. 왼쪽 프린트 하고 나서는 root(부모) 프린트 하게 됨
			PrintInorder(root.right); // 그 후 오른쪽 프린트
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

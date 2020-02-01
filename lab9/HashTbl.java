package lab9;

import java.util.*;

public class HashTbl {
	int tableSize;
	double calnum;
	ArrayList<HashNode> list;

	HashTbl(int size, double calnum) {
		this.tableSize = size;
		this.list = new ArrayList<HashNode>();
		for (int i = 0; i < size; i++)
			this.list.add(null); // arraylist 0부터 7까지 자리 잡아주고 일단 null값 넣어 놓기
		this.calnum = calnum;
	}

	public void insert(int value) {
		if (find(value)) { // 이미 있는 값이면 아무 일도 일어나지 않음
		} else {
			HashNode newNode = new HashNode(value);
			int nodenum = Hash(newNode);
			HashNode nextNode = list.get(nodenum); // 이미 있는 값 = 새로운 값의 다음 노드. 값이 없다면 다음 노드는 자연스레 null로 설정

			newNode.next = nextNode;
			list.set(nodenum, newNode); // 그리고 버켓 첫 값을 새로운 값으로 설정해 준다 - 프린트 시 새로운 값부터 출력되기에 이렇게 설정

			System.out.println("inserted : " + newNode.element + " in node " + nodenum);
		}
	}

	public void delete(int value) {
		if (!find(value)) { // 없는 값이라면 아무 일도 일어나지 않음
		} else {
			int nodenum = Hash(new HashNode(value));

			if (list.get(nodenum).element == value) { // 첫 값이 삭제해야 할 값이라면
				HashNode delNode = list.get(nodenum); // delNode는 맨 앞 값이었군
				list.set(nodenum, delNode.next); // 버켓 첫 값을 그 다음 값으로 설정
			} else {
				HashNode prevNode = list.get(nodenum); // 그 앞의 노드
				while (prevNode.next.element != value) {
					prevNode = prevNode.next;
				}
				HashNode delNode = prevNode.next; // 삭제 노드는 이전 노드의 바로 다음 노드
				prevNode.next = delNode.next; // 이전 노드의 다음을 삭제 노드의 다음으로 변경
			}
			System.out.println("deleted : " + value + " in node " + nodenum);
		}
	}

	public boolean find(int value) {
		HashNode temp = new HashNode(value);
		int nodenum = Hash(temp);
		if (list.get(nodenum) != null) { // list에 nodenum에 값이 있는 경우
			HashNode prevNode = list.get(nodenum);
			while (prevNode.next != null) {
				if (prevNode.element == value)// 계속 next 해주며 값 찾아보기
					return true;
				prevNode = prevNode.next;
			} // 근데 이렇게 할 경우 마지막 값에 대해서는 비교를 하지 않기 때문에
			if (prevNode.element == value)// 따로 마지막 값에 대해서도 비교해보기
				return true;
			return false;
		} else
			return false;
	}

	public void print(HashTbl ht) {
		for (int i = 0; i < ht.tableSize; i++) {
			if (ht.list.get(i) != null) {

				HashNode next = ht.list.get(i);
				System.out.print(ht.list.get(i).element + " ");
				while (next.next != null) {
					next = next.next;
					System.out.print(next.element + " ");
				}
				System.out.println();
			} else
				System.out.println("null");
		}
	}

	public int Hash(HashNode node) {
		int value = node.element;
		double result = tableSize * (value * calnum % 1);
		return (int) result;
	}

}

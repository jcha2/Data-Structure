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
			this.list.add(null); // arraylist 0���� 7���� �ڸ� ����ְ� �ϴ� null�� �־� ����
		this.calnum = calnum;
	}

	public void insert(int value) {
		if (find(value)) { // �̹� �ִ� ���̸� �ƹ� �ϵ� �Ͼ�� ����
		} else {
			HashNode newNode = new HashNode(value);
			int nodenum = Hash(newNode);
			HashNode nextNode = list.get(nodenum); // �̹� �ִ� �� = ���ο� ���� ���� ���. ���� ���ٸ� ���� ���� �ڿ����� null�� ����

			newNode.next = nextNode;
			list.set(nodenum, newNode); // �׸��� ���� ù ���� ���ο� ������ ������ �ش� - ����Ʈ �� ���ο� ������ ��µǱ⿡ �̷��� ����

			System.out.println("inserted : " + newNode.element + " in node " + nodenum);
		}
	}

	public void delete(int value) {
		if (!find(value)) { // ���� ���̶�� �ƹ� �ϵ� �Ͼ�� ����
		} else {
			int nodenum = Hash(new HashNode(value));

			if (list.get(nodenum).element == value) { // ù ���� �����ؾ� �� ���̶��
				HashNode delNode = list.get(nodenum); // delNode�� �� �� ���̾���
				list.set(nodenum, delNode.next); // ���� ù ���� �� ���� ������ ����
			} else {
				HashNode prevNode = list.get(nodenum); // �� ���� ���
				while (prevNode.next.element != value) {
					prevNode = prevNode.next;
				}
				HashNode delNode = prevNode.next; // ���� ���� ���� ����� �ٷ� ���� ���
				prevNode.next = delNode.next; // ���� ����� ������ ���� ����� �������� ����
			}
			System.out.println("deleted : " + value + " in node " + nodenum);
		}
	}

	public boolean find(int value) {
		HashNode temp = new HashNode(value);
		int nodenum = Hash(temp);
		if (list.get(nodenum) != null) { // list�� nodenum�� ���� �ִ� ���
			HashNode prevNode = list.get(nodenum);
			while (prevNode.next != null) {
				if (prevNode.element == value)// ��� next ���ָ� �� ã�ƺ���
					return true;
				prevNode = prevNode.next;
			} // �ٵ� �̷��� �� ��� ������ ���� ���ؼ��� �񱳸� ���� �ʱ� ������
			if (prevNode.element == value)// ���� ������ ���� ���ؼ��� ���غ���
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

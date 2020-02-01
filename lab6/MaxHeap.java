package lab6;

public class MaxHeap {
	public int[] elements;
	public int size;
	public int capacity;

	public MaxHeap CreateHeap(int capacity) { // ���ο� capacity�� �� ����
		MaxHeap mh = new MaxHeap();
		mh.capacity = capacity;
		mh.size = 0;
		mh.elements = new int[mh.capacity + 1];
		return mh;
	}

	public void MakeHeap(MaxHeap mh, int index) {
		if (index / 2 > 0) { // �θ� 0���� ũ�� = �θ� ���� ���
			if (mh.elements[index / 2] < mh.elements[index]) { // ���� �θ� �ڽĺ��� ���� ���� ��쿡 ����
				swap(mh, index / 2, index);
			}
			MakeHeap(mh, index / 2); // �ٲ� Ȥ�� �״���� �θ� ���� ���Ͽ� ���. �״���� �θ� ���� ��� �ٷ� ���� ��Ϳ��� �ߴܵ� (�̹� ���ĵ� ��)
		}
	}

	public void swap(MaxHeap mh, int i, int j) {
		int temp = mh.elements[i];
		mh.elements[i] = mh.elements[j];
		mh.elements[j] = temp;
	}

	public void Insert(MaxHeap mh, int element) {
		if (mh.size == mh.capacity) { // heap �� �� �� ����
			System.out.println("Insert : Max Heap is full");
		} else if (mh.size == 0) { // heap �� ����ִ� ����
			mh.size++;
			mh.elements[mh.size] = element;
		} else { // heap�� ���Ұ� �����Ƿ� �ϴ� �� �ڿ� �߰��ϰ�
			mh.size++;
			mh.elements[mh.size] = element;
			MakeHeap(mh, mh.size); // �߰� �� �ش� ���� ���� MakeHeap ����
		}
	}

	public void DeleteMax(MaxHeap mh) {
		if (mh.size == 0) // ����ִ� heap
			System.out.println("Delete : Max Heap is empty");
		else { // �ϴ� �� ���� element�� �� ���� element�� ���� ������ ����
			mh.elements[1] = mh.elements[mh.size];
			mh.size--; // �� �� size�� �ٿ��ָ� �ڿ����� ������ ���� �����
			for (int i = 1; i <= mh.size; i++)
				MakeHeap(mh, i); // ��Ʈ ������ ��� ���� ���Ͽ� ���� ����. ���������� Ʈ���� ����.
		}
	}

	public void PrintHeap(MaxHeap mh) {
		if (mh.size == 0) {
			System.out.println("Print : Max Heap is empty");
		} else {
			for (int i = 1; i <= mh.size; i++) {
				System.out.print(mh.elements[i] + " ");
			}
			System.out.println();
		}
	}
}

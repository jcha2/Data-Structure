package lab6;

public class MaxHeap {
	public int[] elements;
	public int size;
	public int capacity;

	public MaxHeap CreateHeap(int capacity) { // 새로운 capacity의 힙 생성
		MaxHeap mh = new MaxHeap();
		mh.capacity = capacity;
		mh.size = 0;
		mh.elements = new int[mh.capacity + 1];
		return mh;
	}

	public void MakeHeap(MaxHeap mh, int index) {
		if (index / 2 > 0) { // 부모가 0보다 크다 = 부모가 있을 경우
			if (mh.elements[index / 2] < mh.elements[index]) { // 만약 부모가 자식보다 작은 값일 경우에 스왑
				swap(mh, index / 2, index);
			}
			MakeHeap(mh, index / 2); // 바뀐 혹은 그대로인 부모 값에 대하여 재귀. 그대로인 부모 값일 경우 바로 다음 재귀에서 중단됨 (이미 정렬된 힙)
		}
	}

	public void swap(MaxHeap mh, int i, int j) {
		int temp = mh.elements[i];
		mh.elements[i] = mh.elements[j];
		mh.elements[j] = temp;
	}

	public void Insert(MaxHeap mh, int element) {
		if (mh.size == mh.capacity) { // heap 이 꽉 찬 상태
			System.out.println("Insert : Max Heap is full");
		} else if (mh.size == 0) { // heap 이 비어있는 상태
			mh.size++;
			mh.elements[mh.size] = element;
		} else { // heap에 원소가 있으므로 일단 맨 뒤에 추가하고
			mh.size++;
			mh.elements[mh.size] = element;
			MakeHeap(mh, mh.size); // 추가 후 해당 값에 대해 MakeHeap 진행
		}
	}

	public void DeleteMax(MaxHeap mh) {
		if (mh.size == 0) // 비어있는 heap
			System.out.println("Delete : Max Heap is empty");
		else { // 일단 맨 앞의 element를 맨 뒤의 element와 같은 값으로 설정
			mh.elements[1] = mh.elements[mh.size];
			mh.size--; // 그 후 size를 줄여주면 자연스레 마지막 값은 사라짐
			for (int i = 1; i <= mh.size; i++)
				MakeHeap(mh, i); // 루트 값부터 모든 값에 대하여 정렬 시작. 위에서부터 트리를 정렬.
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

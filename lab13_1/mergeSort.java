package lab13_1;

public class mergeSort {

	public void iterativeMergeSort(int[] nums) {
		int left = 0; // left�� ���� �ε���
		int right = 0; // right�� ���� �ε���
		int end = 0; // right�� ������ �ε���

		for (int size = 1; size <= nums.length; size = size * 2) {// �迭�� ����� 1�̸� left�� 0 right�� 1, �迭 ����� 2�� left�� 0
																	// right�� 2
			for (left = 0; left < nums.length; left = end + 1) {
				right = left + size;
				end = right + size - 1; // �� �迭�� ������ ��
				Merge(nums, left, right, end);
			}
		}
	}

	public void recursiveMergeSort(int[] nums, int left, int end) {
		if (left < end) { // ��ʹ� �����ٺ��� ���Ұ� �Ѱ��� �� �� ����
			int size = (end - left) / 2 + 1; // ���� �迭�� ������
			int right = left + size; // ������ �迭�� ù ���� ���� �迭�� ���� ��. (���� ù �ε��� + ���� �迭�� ������)

			recursiveMergeSort(nums, left, right - 1); // ���� �迭 ����
			recursiveMergeSort(nums, right, end); // ������ �迭 ����
			Merge(nums, left, right, end); // �պ�
		}
	}

	public void Merge(int[] nums, int left, int right, int end) {

		if (end >= nums.length) {
			// left�� right�� �迭 ũ�Ⱑ ���� ���� ��
			if (right >= nums.length)
				right = left; // right �迭�� �ƿ� ���� ���
			end = nums.length - 1;
		}

		int temp[] = new int[nums.length];
		int tempindex = left;

		int lindex = left, rindex = right;

		while (lindex < right && rindex <= end) { // ���� ���� ���ʷ� ��� �迭�� �־��ִ� �۾�
			if (nums[lindex] <= nums[rindex])
				temp[tempindex++] = nums[lindex++];
			else
				temp[tempindex++] = nums[rindex++];
		}

		while (tempindex <= end) {
			if (lindex < right) // ���� �迭�� ���� ���� ���� ���
				temp[tempindex++] = nums[lindex++];
			if (rindex <= end) // ������ �迭�� ���� ���� ���� ���
				temp[tempindex++] = nums[rindex++];
		}

		for (int ind = left; ind <= end; ind++) { // �ٲ� �κ� ����, �� �迭�� �־��ֱ�
			nums[ind] = temp[ind];
			System.out.print(nums[ind] + " ");
		}
		System.out.println();

	}

}

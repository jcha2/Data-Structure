package lab13_1;

public class mergeSort {

	public void iterativeMergeSort(int[] nums) {
		int left = 0; // left의 시작 인덱스
		int right = 0; // right의 시작 인덱스
		int end = 0; // right의 마지막 인덱스

		for (int size = 1; size <= nums.length; size = size * 2) {// 배열의 사이즈가 1이면 left는 0 right는 1, 배열 사이즈가 2면 left는 0
																	// right는 2
			for (left = 0; left < nums.length; left = end + 1) {
				right = left + size;
				end = right + size - 1; // 현 배열의 마지막 값
				Merge(nums, left, right, end);
			}
		}
	}

	public void recursiveMergeSort(int[] nums, int left, int end) {
		if (left < end) { // 재귀는 나누다보니 원소가 한개가 될 때 멈춤
			int size = (end - left) / 2 + 1; // 왼쪽 배열의 사이즈
			int right = left + size; // 오른쪽 배열의 첫 값은 왼쪽 배열의 다음 값. (왼쪽 첫 인덱스 + 왼쪽 배열의 사이즈)

			recursiveMergeSort(nums, left, right - 1); // 왼쪽 배열 정렬
			recursiveMergeSort(nums, right, end); // 오른쪽 배열 정렬
			Merge(nums, left, right, end); // 합병
		}
	}

	public void Merge(int[] nums, int left, int right, int end) {

		if (end >= nums.length) {
			// left와 right의 배열 크기가 같지 않을 때
			if (right >= nums.length)
				right = left; // right 배열이 아예 없는 경우
			end = nums.length - 1;
		}

		int temp[] = new int[nums.length];
		int tempindex = left;

		int lindex = left, rindex = right;

		while (lindex < right && rindex <= end) { // 작은 값들 차례로 결과 배열에 넣어주는 작업
			if (nums[lindex] <= nums[rindex])
				temp[tempindex++] = nums[lindex++];
			else
				temp[tempindex++] = nums[rindex++];
		}

		while (tempindex <= end) {
			if (lindex < right) // 왼쪽 배열에 아직 값이 남은 경우
				temp[tempindex++] = nums[lindex++];
			if (rindex <= end) // 오른쪽 배열에 아직 값이 남은 경우
				temp[tempindex++] = nums[rindex++];
		}

		for (int ind = left; ind <= end; ind++) { // 바뀐 부분 복사, 원 배열에 넣어주기
			nums[ind] = temp[ind];
			System.out.print(nums[ind] + " ");
		}
		System.out.println();

	}

}

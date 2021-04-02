package lab13_2;

public class QuickSort {
	public void sort(int[] nums, int start, int end, int howpivot) {
		int lefti = start;
		int righti = end;
		int pivoti = findpivot(nums, start, end, howpivot);

		/****** leftmost ******/
		if (howpivot == 0) {
			while (lefti < righti) {
				while (nums[lefti] <= nums[pivoti] && lefti < end) // 처음부터 보면서 피벗 값보다 큰 값을 찾음
					lefti++;
				while (nums[righti] > nums[pivoti] && righti > start) // 끝부터 보면서 피벗 값보다 작은 값을 찾음
					righti--;

				if (lefti < righti)
					swap(nums, lefti, righti);
			}

			// 왼쪽에 작은 값이 있고 오른쪽에 큰 값이 있는 상태일 경우.
			// 크로스 되어서 righti가 피벗보다 작은 값을 가리키는 상태.

			swap(nums, righti, pivoti); // 작은 값과 피벗 교환
			print(nums, start, end, righti);

			if (start < righti - 1)
				sort(nums, start, righti - 1, howpivot);
			if (righti + 1 < end)
				sort(nums, righti + 1, end, howpivot);
		}

		/****** rightmost ******/
		if (howpivot == 1) {
			while (lefti < righti) {
				while (nums[lefti] < nums[pivoti] && lefti < end) // 처음부터 보면서 피벗 값보다 큰 값을 찾음
					lefti++;
				while (nums[righti] >= nums[pivoti] && righti > start) // 끝부터 보면서 피벗 값보다 작은 값을 찾음
					righti--;

				if (lefti < righti)
					swap(nums, lefti, righti);
			}

			swap(nums, lefti, pivoti); // 큰 값과 피벗 교환
			print(nums, start, end, lefti);

			if (start < lefti - 1)
				sort(nums, start, lefti - 1, howpivot);
			if (lefti + 1 < end)
				sort(nums, lefti + 1, end, howpivot);

		}

		/****** middle ******/
		if (howpivot == 2) {
			while (lefti < pivoti && righti > pivoti) {
				while (nums[lefti] <= nums[pivoti] && lefti < pivoti) // 처음부터 보면서 피벗 값보다 큰 값을 찾음
					lefti++;
				while (nums[righti] >= nums[pivoti] && righti > pivoti) // 끝부터 보면서 피벗 값보다 작은 값을 찾음
					righti--;

				if (lefti < righti && lefti != pivoti && righti != pivoti)
					swap(nums, lefti, righti);
			}
			if (lefti == start && righti == end) { // 이미 정렬된 원소 두개의 배열,.. 예외
				print(nums, start, end, pivoti);

				if (start < pivoti - 1)
					sort(nums, start, pivoti - 1, howpivot);
				if (pivoti + 1 < end)
					sort(nums, pivoti + 1, end, howpivot);

			} else if (lefti == pivoti && righti == pivoti) { // 이미 정렬된 배열
				print(nums, start, end, pivoti);

				if (start < pivoti - 1)
					sort(nums, start, pivoti - 1, howpivot);
				if (pivoti + 1 < end)
					sort(nums, pivoti + 1, end, howpivot);

			} else if (pivoti == righti) {
				swap(nums, lefti, pivoti); // lefti가 아직도 왼쪽에 : 첫 큰 값 가리키고 있는 것
				print(nums, start, end, lefti);

				if (start < lefti - 1)
					sort(nums, start, lefti - 1, howpivot);
				if (lefti + 1 < end)
					sort(nums, lefti + 1, end, howpivot);

			} else if (pivoti == lefti) {
				swap(nums, righti, pivoti); // righti 가 오른쪽에 : 마지막 작은 값 가키고 있는 것
				print(nums, start, end, righti);

				if (start < righti - 1)
					sort(nums, start, righti - 1, howpivot);
				if (righti + 1 < end)
					sort(nums, righti + 1, end, howpivot);
			}
		}

		// System.out.println(nums[lefti] + " = " + lefti + "," + nums[righti] + " = " +
		// righti);
	}

	public void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	public void print(int[] nums, int start, int end, int pivot) {
		System.out.print("< ");
		for (int i = start; i <= end; i++) {
			if (i == pivot)
				System.out.print("> <" + nums[i] + "> < ");
			else
				System.out.print(nums[i] + " ");
		}
		System.out.println(">");

	}

	public int findpivot(int[] nums, int start, int end, int howpivot) {
		if (howpivot == 0)
			return start;
		else if (howpivot == 1)
			return end;
		else if (howpivot == 2)
			return (end + start) / 2;

		return howpivot;
	}
}

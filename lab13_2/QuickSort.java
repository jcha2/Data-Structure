package lab13_2;

public class QuickSort {
	public void sort(int[] nums, int start, int end, int howpivot) {
		int lefti = start;
		int righti = end;
		int pivoti = findpivot(nums, start, end, howpivot);

		/****** leftmost ******/
		if (howpivot == 0) {
			while (lefti < righti) {
				while (nums[lefti] <= nums[pivoti] && lefti < end) // ó������ ���鼭 �ǹ� ������ ū ���� ã��
					lefti++;
				while (nums[righti] > nums[pivoti] && righti > start) // ������ ���鼭 �ǹ� ������ ���� ���� ã��
					righti--;

				if (lefti < righti)
					swap(nums, lefti, righti);
			}

			// ���ʿ� ���� ���� �ְ� �����ʿ� ū ���� �ִ� ������ ���.
			// ũ�ν� �Ǿ righti�� �ǹ����� ���� ���� ����Ű�� ����.

			swap(nums, righti, pivoti); // ���� ���� �ǹ� ��ȯ
			print(nums, start, end, righti);

			if (start < righti - 1)
				sort(nums, start, righti - 1, howpivot);
			if (righti + 1 < end)
				sort(nums, righti + 1, end, howpivot);
		}

		/****** rightmost ******/
		if (howpivot == 1) {
			while (lefti < righti) {
				while (nums[lefti] < nums[pivoti] && lefti < end) // ó������ ���鼭 �ǹ� ������ ū ���� ã��
					lefti++;
				while (nums[righti] >= nums[pivoti] && righti > start) // ������ ���鼭 �ǹ� ������ ���� ���� ã��
					righti--;

				if (lefti < righti)
					swap(nums, lefti, righti);
			}

			swap(nums, lefti, pivoti); // ū ���� �ǹ� ��ȯ
			print(nums, start, end, lefti);

			if (start < lefti - 1)
				sort(nums, start, lefti - 1, howpivot);
			if (lefti + 1 < end)
				sort(nums, lefti + 1, end, howpivot);

		}

		/****** middle ******/
		if (howpivot == 2) {
			while (lefti < pivoti && righti > pivoti) {
				while (nums[lefti] <= nums[pivoti] && lefti < pivoti) // ó������ ���鼭 �ǹ� ������ ū ���� ã��
					lefti++;
				while (nums[righti] >= nums[pivoti] && righti > pivoti) // ������ ���鼭 �ǹ� ������ ���� ���� ã��
					righti--;

				if (lefti < righti && lefti != pivoti && righti != pivoti)
					swap(nums, lefti, righti);
			}
			if (lefti == start && righti == end) { // �̹� ���ĵ� ���� �ΰ��� �迭,.. ����
				print(nums, start, end, pivoti);

				if (start < pivoti - 1)
					sort(nums, start, pivoti - 1, howpivot);
				if (pivoti + 1 < end)
					sort(nums, pivoti + 1, end, howpivot);

			} else if (lefti == pivoti && righti == pivoti) { // �̹� ���ĵ� �迭
				print(nums, start, end, pivoti);

				if (start < pivoti - 1)
					sort(nums, start, pivoti - 1, howpivot);
				if (pivoti + 1 < end)
					sort(nums, pivoti + 1, end, howpivot);

			} else if (pivoti == righti) {
				swap(nums, lefti, pivoti); // lefti�� ������ ���ʿ� : ù ū �� ����Ű�� �ִ� ��
				print(nums, start, end, lefti);

				if (start < lefti - 1)
					sort(nums, start, lefti - 1, howpivot);
				if (lefti + 1 < end)
					sort(nums, lefti + 1, end, howpivot);

			} else if (pivoti == lefti) {
				swap(nums, righti, pivoti); // righti �� �����ʿ� : ������ ���� �� ����Ű�� �ִ� ��
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

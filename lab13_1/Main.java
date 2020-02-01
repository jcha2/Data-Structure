package lab13_1;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		mergeSort ms = new mergeSort();

		BufferedReader br = new BufferedReader(new FileReader("merge_input.txt"));
		String[] numsStr = br.readLine().split(" ");
		int[] nums1 = new int[numsStr.length];
		int[] nums2 = new int[numsStr.length];

		System.out.println("input : ");
		for (int i = 0; i < nums1.length; i++) {
			nums1[i] = Integer.parseInt(numsStr[i]);
			nums2[i] = Integer.parseInt(numsStr[i]);
			System.out.print(nums1[i] + " ");
		}
		System.out.println();

		System.out.println("\niterative : ");
		ms.iterativeMergeSort(nums1);
		System.out.println("\nrecursive : ");
		ms.recursiveMergeSort(nums2, 0, nums2.length - 1);

		br.close();

	}
}

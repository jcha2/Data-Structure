package lab13_2;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		QuickSort qs = new QuickSort();

		BufferedReader br = new BufferedReader(new FileReader("quick_input.txt"));
		String line;

		while ((line = br.readLine()) != null) {
			int howpivot = 0;

			if (line.startsWith("leftmost")) {
				System.out.println("leftmost : ");
				howpivot = 0;
			} else if (line.startsWith("rightmost")) {
				System.out.println("rightmost : ");
				howpivot = 1;
			} else if (line.startsWith("middle")) {
				System.out.println("middle : ");
				howpivot = 2;
			}

			String str[] = line.split(" ");
			int nums[] = new int[str.length - 1];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(str[i + 1]);
			}

			qs.sort(nums, 0, nums.length - 1, howpivot);

			System.out.println("\n result : ");
			for (int i = 0; i < nums.length; i++)
				System.out.print(nums[i] + " ");
			System.out.println("\n");
		}
		br.close();

	}
}

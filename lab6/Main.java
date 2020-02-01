package lab6;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		MaxHeap mh = new MaxHeap();

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");

			if (lines[0].equals("n")) {
				mh = mh.CreateHeap(Integer.parseInt(lines[1]));
			} else if (lines[0].equals("i")) {
				mh.Insert(mh, Integer.parseInt(lines[1]));
			} else if (lines[0].equals("d")) {
				mh.DeleteMax(mh);
			} else if (lines[0].equals("p")) {
				mh.PrintHeap(mh);
			}
		}

		br.close();
	}
}

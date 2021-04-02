package lab9;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hash_input.txt"));
		String line = "";

		int size = Integer.parseInt(br.readLine().trim());
		double cal = Double.parseDouble(br.readLine().trim());
		HashTbl ht = new HashTbl(size, cal); // 새로운 해시 테이블 생성, 사이즈와 해싱 함수 만들기 위한 float 값 설정
		System.out.println("Hash table size : " + size);
		System.out.println("A : " + String.format("%.6f", cal)); //소수점 6번째 자리까지 표현

		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");

			if (lines[0].equals("i")) {
				ht.insert(Integer.parseInt(lines[1]));
			} else if (lines[0].equals("d")) {
				ht.delete(Integer.parseInt(lines[1]));
			} else if (lines[0].equals("f")) {
				if (ht.find(Integer.parseInt(lines[1]))) {
					HashNode temp = new HashNode(Integer.parseInt(lines[1]));
					System.out.println("found " + lines[1] + " : " + ht.Hash(temp));
				} else
					System.out.println("null");
			} else if (lines[0].equals("p")) {
				ht.print(ht);
			} else if (lines[0].equals("q")) {
				System.exit(0);
			}
		}

		br.close();
	}
}

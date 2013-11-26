import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class squareDetector {

	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("src/square_detector_output.txt");
		Scanner in = new Scanner(new FileReader("src/sq_test.txt"));
		int cases = in.nextInt();
		int caseCount = 1;
		while (cases > 0) {
			int n = in.nextInt();

			char[][] grid = new char[n][n];
			for (int i = 0; i < n; i++) {
				String line = in.next();
				for (int j = 0; j < n; j++) {
					grid[i][j] = line.charAt(j);
				}
			}

			boolean hasSquare = isSquare(grid);
			if (hasSquare) {
				writer.println("Case #" + caseCount + ": YES");
			} else {
				writer.println("Case #" + caseCount + ": NO");
			}
			caseCount++;
			cases--;
		}
		writer.close();
	}

	private static boolean isSquare(char[][] grid) {

		int firstRow = -1;
		int firstCol = -1;

		int totalTags = 0;
		int countOfCols = 0;
		int reqCount = 0;

		END: for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '#') {
					firstRow = i;
					firstCol = j;
					break END;
				}
			}
		}

		for (int i = firstCol; i < grid[0].length; i++) {
			if (grid[firstRow][i] == '#') {
				countOfCols++;
			} else {
				break;
			}
		}

		int requiredRows = countOfCols;

		for (int i = firstRow; i < requiredRows + firstRow; i++) {
			for (int j = firstCol; j < countOfCols + firstCol; j++) {
				if (grid[i][j] != '#') {
					return false;
				}
				reqCount++;
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '#') {
					totalTags++;
				}
			}
		}
		if (reqCount != totalTags) {
			return false;
		}
		return true;
	}
}

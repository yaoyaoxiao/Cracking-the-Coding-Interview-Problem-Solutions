/*  8.10 Paint Fill: Implement the "paint fill" function that one might see on many image editing programs.
That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
fill in the surrounding area until the color changes from the original color. */

//suppose we are using positive integers to represent different colors
void paintFill(int[][] screen, Point p, int color){
	int startRow = p.first();
	int startCol = p.second();
	if (screen == null || screen.length() <= startRow || screen[].length() <= startCol){ //invalid input
		return; 
	}
	int startColor = screen[startRow][startCol];
	if (startColor == color) { //no need to fill
		return;
	}
	LinkedList<Point> toBePaint = new LinkedList<Point>();
	toBePaint.addLast(p);
	boolean[][] checked = new boolean[screen.length()][screen[].length()];

	while (!toBePaint.isEmpty()){
		Point cur = toBePaint.removeFirst();
		int r = cur.first();
		int c = cur.second();
		screen[r][c] = color;
		checked[r][c] = true;
		for (int i = r-1; i <= r+1; i++){
			for (int j = c-1, j <= c+1; j++){
				if (i >= 0 && i < screen.length() && j >= 0 && j < screen[].length()){
					Point surroundP = new Point(i,j);
					if (!checked[i][j] && screen[i][j] == startColor){
						toBePaint.addLast(new Point(i,j));
					}
				}
			}
		}
	}
}

public class Point{
	private int first;
	private int second;
	public Point(int r, int c){
		first = r;
		second = c;
	}
	public int first(){return first;}
	public int secont() {return second;}
}

/*
The above is using a iterative way and eight directions. We can also do this recursively. Suppose that we have 5 colors as an Enum and we only do four directions. The recursive way can be implemented as below. 
*/

enum Color {Black, White, Red, Blue, Yellow}
boolean paintFill(Color[][] screen, int row, int col, Color newColor){
	if (screen == null || screen.length() <= row || screen[0].length() <= col || screen[row][col] == newColor){
		return false;
	}
	return paintFill(screen, row, col, newColor, screen[row][col]);
}
boolean paintFill(Color[][] screen, int r, int c, Color newColor, Color oldColor){
	if (r < 0 || r >= screen.length() || c < 0 || c <= screen[0].length()){
		return false;
	}
	if (screen[r][c] == oldColor){
		screen[r][c] = newColor;
		paintFill(screen, r-1, c, newColor, oldColor);
		paintFill(screen, r+1, c, newColor, oldColor);
		paintFill(screen, r, c-1, newColor, oldColor);
		paintFill(screen, r, c+, newColor, oldColor);
	}
	return true;
}
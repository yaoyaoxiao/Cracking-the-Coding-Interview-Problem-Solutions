/* 8.13 Stack of Boxes: You have a stack of n boxes, with widths Wi, heights hi, and depths di. The boxes
cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
larger than the box above it in width, height, and depth. Implement a method to compute the
height of the tallest possible stack. The height of a stack is the sum of the heights of each box. */

int tallestStack(ArrayList<Box> boxes){
	Collection.sort(boxes, new BoxComparator());
	int[] map = new int[boxes.size()]; // use this map to record the height with a specific bottom
	return tallestStack(boxes, null, 0, map);
}

int tallestStack(ArrayList<Box> boxes, Box bottom, int offset, int[] map){
	if (offset >= boxes.size()) { //the base case
		return 0;
	}
	//height with this box at bottom
	int heightWithBoxAtOffset = 0;
	Box cur = boxes.get(offset);
	if (bottom = null || cur.canBeAbove(bottom)){
		if (map[offset] == 0){
			map[offset] = tallestStack(boxes, cur, offset+1, map);
			map[offset] += cur.height;
		}
		heightWithBoxAtOffset = map[offset];
	}
	//without this box at bottom
	int heightWithoutThisBox = tallestStack(boxes, bottom, offset+1, map);

	//return the taller on of these two
	return Math.max(heightWithBoxAtOffset, heightWithoutThisBox);
}

public class Box{
	private int width;
	private int height;
	private int depth;

	public Box(int w, int h, int d){
		width = w;
		height = h;
		depth = d;
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getDepth(){
		return depth;
	}

	public booldean canBeAbove(Box b){
		return (width < b.getWidth() && height < b.getHeight() && depth < b.getDepth());
	}
}

public BoxComparator Implement BoxComparator<Box>{
	@Override
	public int compare(Box b1, Box b2){
		return b1.getHeight() - b2.getHeight;
	}
}
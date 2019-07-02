/* 16.3 Intersection: Given two straight line segments (represented as a start point and an end point)'
compute the point of intersection, if any. */

/*
1. first calculate the slope and the yIntercept for each line
2. If the lines can be extended/infinite, is there any intersection? slopes are different or (same slope and same yIntercept)
3. do the two line segments intersect? if different slopes, the intersection point should be within both line segments; if same slopes, one segment's end point should be between the other segment's start point and end point.
*/

Point findIntersection(Point start1, Point end1, Point start2, Point end2){
	// let the point with smaller x-intercepter be the start point
	if (start1.x > end1.x){
		swapPoint(start1, start2);
	}
	if (start2.x > end2.x){
		swapPoint(start2, end2);
	}

	//let the line segment with smaller starting x be the first
	if (start1.x > start2.x){
		swapPoint(start1,start2);
		swapPoint(end1, end2);
	}

	Line line1 = new Line(start1, end1);
	Line line2 = new Line(start2, end2);

	if (line1.slope == line2.slope){ // same slope, two lines have intersections only if their infinite lines are the same line and if two segments have overlaps
		// no intersection if two lines are parallel; return start2 if they have overlaps
		if (line1.yIntercept == line2.yIntercept && start2.x >= start1.x && start2.x <= end1.x) { // we only need to check the x intercepts
			return start2; 
		}
		return null;
	}
	else{
		//calculate the intersection if infinite lines 
		double x = (line2.yIntercept - line1.yIntercept) / (line1.slope - line2.slope);
		double y = line1.slope * x + line1.yIntercept;

		//check whether the intersection is in both line segments, assume Point (x,y) is on both infinite lines so we just need to check the x intercepts
		if (x >= start1.x && x <= end1.x && x >= start2.x && x <= end2.x){
			Point intersection = new Point(x, y);
			return intersection;
		}
		return null;
	}
}

void swapPoint(Point p1, Point p2){
	double p1x = p1.x;
	double p1y = p1.y;
	p1.setCoordinates(p2.x, p2.y);
	p2.setCoordinates(p1x, p1y);
}

public class Line{
	public double slope;
	public double yIntercept;
	public Line(double s, double y){
		slope = s;
		yIntercept = y;
	}
	public Line(Point start, Point end){
		slope = (end.y - start.y)/(end.x-start.x);
		yIntercept = start.y - slope * start.x; 
	}
}

public class Point{
	public double x;
	public double y;
	public Point(double d1, double d2){
		x = d1;
		y = d2;
	}
	public setCoordinates(double d1, double d2){
		x = d1;
		y = d2;
	}
}



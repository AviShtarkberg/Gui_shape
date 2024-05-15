//my id: 322530080
package geo;

import java.util.ArrayList;



public class Polygon_2D implements GeoShape {
	//will determine new arrayList that will represent the polygon points.
	private ArrayList<Point_2D> polygon;

	/*Polygon_2D: will construct a new polygon with the arrayList.
    method: will use this and new methods for the construction.
     */
	public Polygon_2D() {
		this.polygon = new ArrayList<>();
	}

	/*Polygon_2D: will construct a new polygon that is a copy of a polygon.
    method: will make a copy of the polygon with this and new methods.

     */
	public Polygon_2D(Polygon_2D po) {
		this.polygon = new ArrayList<>(po.polygon);
	}

	/*getAllPoints: the output will be a points array that represent all the points of the polygon.
    method:will run threw the arrayList of the polygon with a for loop
    and will add to the ans array the points of the polygon.
     */
	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[this.polygon.size()];
		for (int i = 0; i < this.polygon.size(); i++) {
			ans[i] = new Point_2D(this.polygon.get(i));
		}
		return ans;
	}

	/*add: will add a point to the polygon array.
    method: will use the add method of the array list class and add a point to the
    array list.
     */
	public void add(Point_2D p) {
		this.polygon.add(p);
	}

	/*toString: will return a string that represent the polygon.
    method: will run a loop threw the array list that represent the polygon and each time will
    add "(" , the point with the to string of the point method, ")" and will add "," each time
    between the points that are not in the end of the string.

     */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Polygon_2D, ");
		if (!this.polygon.isEmpty()) {
			for (int i = 0; i < this.polygon.size(); i++) {
				s.append(this.polygon.get(i).toString());
				if (i != this.polygon.size() - 1) {
					s.append(",");
					s.append(" ");
				}
			}
			return s.toString();
		}
		return null;
	}

    /*contains: will check if a given point is inside or outside the polygon.
    method:
    firstly will check that the polygon is not empty, if its empty will return false.
    if the polygon contain only one point will check if the point that we check is the same one as the polygons if so will
    return true, else will return false.
    after:
    1.will construct a segments array list that will hold all the segments of the polygon.
    2.will fill all the segments of the polygon to the array list with for loop, the last one
    will be filled outside the for loop.(if we have only one segment in the polynom will check if the
    point that we are checking is inside ite segment with contains method from segment class).
     3. will check if the point is on one of the segments with for loop that will iterate threw the array list.
     if so will return false.
     4. will find the max X ,max Y, min X, min Y of the points values of the polygon with 4 for loops.
     6. if the point is outside the "boundary's" of the polygon will return automatically false.
     7. will determine new int named counter that will increase each intersection point of the line that will
     go to the right side from the point.
     8.will run a for loop that will contain some conditions:
     firstly the loop will determine the x and y values of the segments each time defined by i(the int of the loop).
      a)first main condition: if the x values of the segment are not the same:
      will calculate the incline of the segment.
      a1) will check that the incline is not 0, if so:
      will make a straight line that is defined by the 2 points of the segment and will determine the x value of the straight line
      with the y value of the point. will construct a new point that is defined by this 2 values. if this point is inside the
      segment(will be checked with contains method of segment class) and the x value of this point is greater than the x value
      of the checked point (means that its on the right side) than will add 1 to the counter.
      a2) if the incline equals zero: will check that the x value of the point is lesser than the maximum of the
      x values of the segment(means that the segment is on the right side of the point) and will check that the
      y value of the segment is equals to the y value of the point that we check.
      this 2 conditions will check weather or not there will be an intersection point from the right side between the line
      that is determined by the point that we check and the segment from the polygon.
      b) second main condition: if the x values of the segment are same (meaning that the segment is parallel to y line):
       firstly will construct a new point that is determined by the x value  of the segment and the y value of the point that we check.
        then will check that the segment is on the right side of the point and will check that the segment contain the point that is
        mentioned in the line below.
        if so it means that the line that is going to the right side of the point and segment will intersect so will add 1 to the counter.
   9. will do the same algorithm to the left side of the point with a new counter and opposite directions.
   10. now we got our final counters results, and will check if the counters is odd or even,
       if its odd then the point is inside the polygon and will return true.
       if its even then the point is outside the polygon so will return false.

     this algorithm is based on ray casting algorithm as mentioned
     in wikipedia:https://en.wikipedia.org/wiki/Point_in_polygon.
     */

	@Override
	public boolean contains(Point_2D ot) {
		if (this.polygon.isEmpty()) {
			return false;
		}
		if (this.polygon.size() == 1) {
			return this.polygon.get(0).equals(ot);
		}
		ArrayList<Segment_2D> segments = new ArrayList<>();
		for (int i = 0; i < this.polygon.size() - 1; i++) {
			Segment_2D segment = new Segment_2D(this.polygon.get(i), this.polygon.get(i + 1));
			segments.add(segment);
		}
		Segment_2D lastSegment = new Segment_2D(this.polygon.get(this.polygon.size() - 1), this.polygon.get(0));
		segments.add(lastSegment);
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).contains(ot)) {
				return false;
			}
		}
		double yMax = Double.MIN_VALUE;
		for (int i = 0; i < this.polygon.size(); i++) {
			if (this.polygon.get(i).y() > yMax) {
				yMax = this.polygon.get(i).y();
			}
		}
		double xMax = Double.MIN_VALUE;
		for (int i = 0; i < this.polygon.size(); i++) {
			if (this.polygon.get(i).x() > xMax) {
				xMax = this.polygon.get(i).x();
			}
		}
		double xMin = Double.MAX_VALUE;
		for (int i = 0; i < this.polygon.size(); i++) {
			if (this.polygon.get(i).x() < xMin) {
				xMin = this.polygon.get(i).x();
			}
		}
		double yMin = Double.MAX_VALUE;
		for (int i = 0; i < this.polygon.size(); i++) {
			if (this.polygon.get(i).y() < yMin) {
				yMin = this.polygon.get(i).y();
			}
		}
		if (ot.y() <= yMin || ot.x() <= xMin || ot.y() >= yMax || ot.x() >= xMax) {
			return false;
		}
		int counter = 0;
		for (int i = 0; i < segments.size(); i++) {
			double x1 = segments.get(i).get_p1().x();
			double y1 = segments.get(i).get_p1().y();
			double x2 = segments.get(i).get_p2().x();
			double y2 = segments.get(i).get_p2().y();
			if (x2 != x1) {
				double m = (y2 - y1) / (x2 - x1);
				if (m != 0) {
					double intersectionX = (ot.y() - y1 + (m * x1)) / m;
					Point_2D b = new Point_2D(intersectionX, ot.y());
					if (intersectionX > ot.x() && segments.get(i).contains(b)) {
						counter++;
					}
				} else {
					if (ot.y() == y2 && ot.x() < Math.max(x1, x2)) {
						counter++;
					}
				}
			} else {
				Point_2D intersection = new Point_2D(x1, ot.y());
				if (x1 > ot.x() && segments.get(i).contains(intersection)) {
					counter++;
				}
			}
		}
		int counter2 = 0;
		for (int i = 0; i < segments.size(); i++) {
			double x1 = segments.get(i).get_p1().x();
			double y1 = segments.get(i).get_p1().y();
			double x2 = segments.get(i).get_p2().x();
			double y2 = segments.get(i).get_p2().y();
			if (x2 != x1) {
				double m = (y2 - y1) / (x2 - x1);
				if (m != 0) {
					double intersectionX = (ot.y() - y1 + (m * x1)) / m;
					Point_2D b = new Point_2D(intersectionX, ot.y());
					if (intersectionX < ot.x() && segments.get(i).contains(b)) {
						counter2++;
					}
				} else {
					if (ot.y() == y2 && ot.x() > Math.max(x1, x2)) {
						counter2++;
					}
				}
			} else {
				Point_2D intersection = new Point_2D(x1, ot.y());
				if (x1 < ot.x() && segments.get(i).contains(intersection)) {
					counter2++;
				}
			}
		}
		return counter % 2 != 0 && counter2 % 2 != 0;
	}

    /*area: will return the area of a regular polygon.
    method: will run a for loop that will use the form for the area of a polygon as
    written in wikipedia. note that the last segment of the polygon is added outside the for loop.
   link to the wikipedia page for the area: https://en.wikipedia.org/wiki/Polygon .

     */

	@Override
	public double area() {
		double area = 0;
		for (int i = 0; i < this.polygon.size() - 1; i++) {
			area += (this.polygon.get(i).x() * this.polygon.get(i + 1).y()) - (this.polygon.get(i + 1).x() * this.polygon.get(i).y());
		}
		area += (this.polygon.get(this.polygon.size() - 1).x() * this.polygon.get(0).y()) - (this.polygon.get(this.polygon.size() - 1).y() * this.polygon.get(0).x());
		return area * 0.5;

	}
    /*perimeter: will calculate the perimeter of the polygon.
    method: will iterate threw the polygon and will make a distance method
    each time between each to points of the polygon. the sum of all the distances will be the perimeter
    of the polygon.

     */

	@Override
	public double perimeter() {
		double perimeter = 0;
		for (int i = 0; i < this.polygon.size() - 1; i++) {
			perimeter += this.polygon.get(i).distance(this.polygon.get(i + 1));
		}
		perimeter += this.polygon.get(0).distance(this.polygon.get(this.polygon.size() - 1));
		return perimeter;
	}

	/*translate: will translate the polygon.
    method: will run a for loop over all the points of the polygon and each time will
    use the move method of Point2D class with the vector that is given as input to translate the polygon by
    its points.

     */
	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < this.polygon.size(); i++) {
			this.polygon.get(i).move(vec);
		}
	}

	/*will make e copy of the polygon.
    method: will make a copy of the polygon by using this and new methods.

     */
	@Override
	public GeoShape copy() {
		Polygon_2D copy = new Polygon_2D();
		for(Point_2D p : this.getAllPoints()){
			copy.add(p);
		}
		return copy;

	}

	/*scale: will scale the polygon.
    method: will run a loop threw all the points of the polygon and each time will
    make a scale with the scale method from Point2D class fo the point of the polygon.
    the scale method for the point will be the center and the ratio that is given as
    input for the function.
     */
	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < this.polygon.size(); i++) {
			this.polygon.get(i).scale(center, ratio);
		}
	}

	/*rotate: will rotate the polygon.
    method: will run a for loop threw the points of the polygon and each time will
   rotate the point threw the center and angleDegrees that is given as input in the function.
   the function will use the rotate method from Point2D class.
     */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < this.polygon.size(); i++) {
			this.polygon.get(i).rotate(center, angleDegrees);
		}
	}
}

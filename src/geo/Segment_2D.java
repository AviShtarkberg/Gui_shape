//my id: 322530080
package geo;

/**
 * This class represents a 2D segment on the plane,
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Segment_2D implements GeoShape {
	private Point_2D _a, _b;

	/*Segment_2D: this function will construct the segment with 2 given points.
    method: this function will set the 2 points that are given as input from the
    user to be the points that determine the current segment.
     */
	public Segment_2D(Point_2D a, Point_2D b) {
		this._a = new Point_2D(a);
		this._b = new Point_2D(b);
	}

	/*Segment_2D: this function will construct a new segment from a given segment.
    method: will set the 2 points of the segments that is given as input to br the
    points of the constructed segment.
     */
	public Segment_2D(Segment_2D t1) {
		this(t1._a, t1._b);
	}

	/*get_p1: will get the first point of the segment
    method: will return the value as point of the first point of "this" segment.
     */
	public Point_2D get_p1() {
		Point_2D a = this._a;
		return a;
	}

	/*get_p2: will get the second point of the segment.
    method: will return the value as point of the second point of "this" segment.
     */
	public Point_2D get_p2() {
		Point_2D b = this._b;
		return b;
	}

	/* contains: this function will return true if the point is inside the segment.
    method: will check if the sum of the distances of the point that ig given as input
    with the 2 points of the array is same as the distance of the segment. if so will return true
    else will return false.
     */
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D a = this._a;
		Point_2D b = this._b;
		double d1 = a.distance(ot);
		double d2 = ot.distance(b);
		double sum = d1 + d2;
		if (Math.abs(a.distance(b) - sum) <= 0.001) {
			return true;
		}
		return false;
	}

	//will return 0 : the area of a segment.
	@Override
	public double area() {
		return 0;
	}

	//will return the length of the segment multiplied by 2 as determined at the interface.
	@Override
	public double perimeter() {
		return this._a.distance(_b) * 2;
	}

	/*translate: this function will translate the segment.
     method: will make translate to the 2 points of the segment.
      will use the move methode of point2D class with the current given as input vector.
     */
	@Override
	public void translate(Point_2D vec) {
		this._a.move(vec);
		this._b.move(vec);
	}

	/*copy: will make a copy of the segment.
     method: will copy the segment with new and "this" methods.
     */
	@Override
	public GeoShape copy() {
		return new Segment_2D(this);
	}

	/*scale:this function  will make a scale for a segment.
    method: will do a scale to the 2 points of the segment as given at the Point2D class method of
    scale with given as input center and ratio.
     */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._a.scale(center, ratio);
		this._b.scale(center, ratio);
	}

	/*rotate:this function will rotate the segment.
    method: will make  rotate to the points of the segment as in the Point2D class.
    the center and the angleDegrees will be given as input for this function.
     */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._a.rotate(center, angleDegrees);
		this._b.rotate(center, angleDegrees);
	}
	//this function will return the string that is represented by the points of the string.
	@Override
	public String toString(){
		return "Segment_2D,"+" "+ this._a.toString() +","+" "+this._b.toString();
	}
}
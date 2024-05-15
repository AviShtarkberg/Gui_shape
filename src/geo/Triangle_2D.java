//my id: 322530080
package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Triangle_2D implements GeoShape {
	private Point_2D _p1, _p2, _p3;
	private static final double eps = 0.001;

	/*Triangle_2D: the constructor by points of the Triangle.
    method: will set the points of the given triangle as the points of the triangle .
     */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}
    /*Triangle_2D: will construct a new triangle from a given triangle.
    method: the input of this function will be a triangle and the function will set
    a new triangle with the same points that was in the one that was given with "this" method.
     */

	public Triangle_2D(Triangle_2D t1) {
		this(t1._p1, t1._p2, t1._p3);
	}

	/*getAllPoints: will return an array that contains all the points of the given triangle.
    method: will set the first second and third indexes of the array with the p1,p2,p3 points of the
    given triangle.

     */
	public Point_2D[] getAllPoints() {
		Point_2D[] allPoints = new Point_2D[3];
		allPoints[0] = this._p1;
		allPoints[1] = this._p2;
		allPoints[2] = this._p3;
		return allPoints;
	}/*getAllDistances: this function will output all the lengths of the lines of a given triangle.
    method: will use the distance function from the Point2D class and will calculate all the
    distances between the 3 points of the triangle.
    the output will be an array that represent the 3 lines lengths as doubles.
     */

	public double[] getAllDistances() {
		Point_2D a = this._p1;
		Point_2D b = this._p2;
		Point_2D c = this._p3;
		double ab = a.distance(b);
		double ac = a.distance(c);
		double bc = b.distance(c);
		double[] dis = new double[3];
		dis[0] = ab;
		dis[1] = ac;
		dis[2] = bc;
		return dis;
	}

	/*contains: will check if a point is inside the triangle.
    method:will create new 3 triangles from the points of a given triangle and the given as input point.
    will calculate the area of the 3 new triangles and fill check if the sum of the 3 areas is the same as the
    whole given triangle area. if so will return true, else will return false.
    note: the equation will be up to eps.
    the form for calculation of the area of the triangles was taken from wikipedia:
    https://he.wikipedia.org/wiki/%D7%9E%D7%A9%D7%95%D7%9C%D7%A9
     */
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D[] all = this.getAllPoints();
		Triangle_2D a = new Triangle_2D(all[0], all[1], ot);
		Triangle_2D b = new Triangle_2D(all[0], all[2], ot);
		Triangle_2D c = new Triangle_2D(all[1], all[2], ot);
		double f = a.area() + b.area() + c.area();
		return this.area() < f + eps && this.area() > f - eps;
	}

	/*area: this function will return the area of a given triangle.
    method: first will get  all lengths of the triangle with the allDistance function and will
    return the form area calculated with form as written in wikipedia:
    https://he.wikipedia.org/wiki/%D7%9E%D7%A9%D7%95%D7%9C%D7%A9
     */
	@Override
	public double area() {
		double[] d = this.getAllDistances();
		return 0.25 * (Math.sqrt((d[0] + d[1] + d[2]) * (d[1] + d[2] - d[0]) * (d[0] + d[2] - d[1]) * (d[0] + d[1] - d[2])));
	}

	/*perimeter: this method will return the perimeter of a given triangle.
    method: will firstly get all the lengths of the triangle with the getAllDistances method
    and will return the sum of the 3 lengths.
     */
	@Override
	public double perimeter() {
		double[] a = this.getAllDistances();
		return a[0] + a[1] + a[2];
	}

	/*translate: this method will move the triangle from his current place on a 2D "map".
    method: will use the "move" with the given vector
     method of the Point2D class for each of the points of the triangle.
     */
	@Override
	public void translate(Point_2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
		this._p3.move(vec);
	}

	/*copy: will copy the current triangle.
    method: will return a new triangle that is a copy of the given triangle with "this" method.
     */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
	}

	/*scale: will make the triangle bigger or smaller.
    method: will scale all the points of the triangle with the scale method of Point2D class with the given
    as input ratio and center.
     */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}

	/*rotate: will rotate the triangle.
    method: will use the rotate method of Point2D class with the given center and angleDegrees as input
    of rotate Point2D method.
     */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}
	@Override
	public String toString(){
		return "Triangle_2D,"+" " +this._p1.toString() + " " + this._p2.toString()+ " "+ this._p3.toString();

	}
}

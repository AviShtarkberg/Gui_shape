//my id: 322530080
package geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Rect_2D implements GeoShape {
	private static final double eps = 0.001;
	private Point_2D _a, _b, _c, _d;

	/*Rect_2D: will construct a new rectangle that is determined by 2 points and
    another 2 points that is the opposites of the first points by x,y values.
     */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this._a = new Point_2D(p1);
		this._b = new Point_2D(p2);
		double x1 = this._a.x();
		double x2 = this._b.x();
		double y2 = this._b.y();
		double y1 = this._a.y();
		this._c = new Point_2D(x1, y2);
		this._d = new Point_2D(x2, y1);
	}

	/*Rect_2D: this function will construct a copy of a rectangle.
    method: the function will create 4 new points for each point po the given as input rectangle.
     */
	public Rect_2D(Rect_2D t1) {
		this(t1._a, t1._b);
	}

	/*will get the 4 points of the rectangle.
	 */
	public Point_2D get_a() {
		return this._a;
	}

	public Point_2D get_b() {
		return this._b;
	}

	public Point_2D get_c() {
		return this._c;
	}

	public Point_2D get_d() {
		return this._d;
	}

	/*contains will check weather or not a point is inside the rectangle.
    method: will create 4 triangles with the given point and the 4 points of the
    rectangle. if the sum of the 4 triangles area will be the same as the area
    of the rectangle will return true, else, will return false.
     */
	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D a = new Triangle_2D(_a, _c, ot);
		Triangle_2D b = new Triangle_2D(_a, _d, ot);
		Triangle_2D c = new Triangle_2D(_b, _c, ot);
		Triangle_2D d = new Triangle_2D(_b, _d, ot);
		double sumArea = a.area() + b.area() + c.area() + d.area();
		if (this.area() < sumArea + eps && this.area() > sumArea - eps) {
			return true;
		}
		return false;
	}

	/*area: will return the area of the rectangle.
    method: will calculate the area of the rectangle with the form of rectangle
    area. will use the distance method of the Point2D class.

     */
	@Override
	public double area() {
		return (_a.distance(_d)) * (_a.distance(_c));

	}
    /*area: will return the perimeter of the rectangle.
    method: will calculate the perimeter of the rectangle with the form of rectangle
    perimeter. will use the distance method of the Point2D class.
     */

	@Override
	public double perimeter() {
		return ((_a.distance(_d)) * 2) + (_a.distance(_c) * 2);
	}

	/*translate: will translate the rectangle.
    method: will translate the rectangle by translating all the points of the
    rectangle with the move method from Point2D class.
     */
	@Override
	public void translate(Point_2D vec) {
		this._a.move(vec);
		this._b.move(vec);
		this._c.move(vec);
		this._d.move(vec);
	}

	/*copy: will make a copy of the rectangle.
    method: will determine a new rectangle with new method, the rectangle will be
    determined by "this" rectangle.
     */
	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}

	/*scale: will scale the rectangle.
    method: will make a scale to all the 4 points of the rectangle with scale method
    from Point2D class, will get as input the center and the ratio for making the scale.
     */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._a.scale(center, ratio);
		this._b.scale(center, ratio);
		this._c.scale(center, ratio);
		this._d.scale(center, ratio);
	}

	/* rotate: will rotate the rectangle.
    method: will use the rotate method for all the 4 points of the rectangle with rotate method
    from Point2D class with given as input center and angleDegrees.
     */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._a.rotate(center, angleDegrees);
		this._b.rotate(center, angleDegrees);
		this._c.rotate(center, angleDegrees);
		this._d.rotate(center, angleDegrees);
	}

	//this function will return the string that represent the rectangle by its points.
	@Override
	public String toString() {
		return "Rect_2D," + " "+ this._a.toString() +","+ " " + this._b.toString() +","+ " " + this._c.toString()
		+ ","+ " "+ this._d.toString();
	}
}

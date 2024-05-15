//my id: 322530080
package geo;

import java.awt.*;

/**
 * This class represents a 2D circle in the plane.
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 *
 * @author boaz.benmoshe
 */
public class Circle_2D implements geo.GeoShape {
	private Point_2D _center;
	private double _radius;


	/*Circle_2D: the circle constructor.
    method: each new circle will be determined by the radius and the center of the circle.
     */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

    //get radius: this method will get the radius of a specific circle, will use "this" method.
	public double getRadius() {
		return this._radius;
	}

	//getCenter: this method will get the center of the circle
	public Point_2D getCenter() {
		return this._center;
	}

	/*toString:this function will return a string that represent the circle.
    method: the function will return first the radius and then inside a parenthesis the x and the y
    values of the center of the circle.
     */
	@Override
	public String toString() {
		return "Circle_2D,"+ this._center.toString()+ ", " + this._radius ;
	}

	/*contains: will check if a point is inside a circle.
    method: if the distance between the given point and the center of the circle
    is greater than the radius of the circle will return false, else will return true.
    will use the distance method from Point2D class.

     */
	@Override
	public boolean contains(Point_2D ot) {
		if (this.getCenter().distance(ot) <= _radius) {
			return true;
		}
		return false;

	}

	/*area: will return the area of the circle.
    method: will calculate the area with the area form of a circle.
     */
	@Override
	public double area() {
		return Math.PI * Math.pow(this._radius, 2);
	}

	/*area: will return the perimeter of the circle.
      method: will calculate the perimeter with the perimeter form of a circle.
     */
	@Override
	public double perimeter() {
		return 2 * Math.PI * this._radius;
	}

	/*will make  translate to the circle.
    method: will make  translate to the center point of the circle.
    will use the move method from Point2D class.
     */
	@Override
	public void translate(Point_2D vec) {
		this._center.move(vec);
	}

	/*copy: will make a copy of the circle.
    method: will copy the circle with "new" and "this" methods.
    */
	@Override
	public GeoShape copy() {
		return new Circle_2D(this._center, this._radius);
	}

	/* scale: this function will scale the circle.
    method: will make a scale as in Point2D class to the center and will multiply
    the radius by the ratio to make it bigger or smaller.
     */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._center.scale(center, ratio);
		this._radius *= ratio;
	}

	/*rotate: will rotate the circle.
    method: this function will rotate the center of the circle with the given as input to the
    function: center,angleDegrees. will use the Point2D rotate method.
     */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center, angleDegrees);
	}
}

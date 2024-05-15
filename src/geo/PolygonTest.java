//my id: 322530080
package geo;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PolygonTest {
    public final Point_2D a = new Point_2D(1, 2);
    public final Point_2D b = new Point_2D(3, 2);
    public final Point_2D c = new Point_2D(2, 3);
    public final Point_2D d = new Point_2D(4, 4);
    public final Point_2D e = new Point_2D(1, 4);
    private static final double eps = 0.001;

    /*will test the polygon Constructor.
    will check if the constructor of the polygon constructs the right polygon that
    we expect, will check the equation with the to string method, so will test
    the to string method to. will use the add function so will test that method to.
     */
    @Test
    public void polygonConstructorsToStringAddTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        Polygon_2D p1 = new Polygon_2D(p);
        assertEquals(p.toString(), "Polygon_2D, 1.0,2.0, 3.0,2.0, 2.0,3.0, 4.0,4.0, 1.0,4.0");
        assertEquals(p.toString(), p1.toString());
        assertNotSame(p, p1);
    }

    /*will test "getAllPoints" function.
    will check that after adding a points to the array list the represent the
    polygon  we get an array of the right points. will do the equation with "assertArrayEquals".
     */
    @Test
    public void getAllPointsTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        Point_2D[] exp = {a, b, c, d, e};
        assertArrayEquals(exp, p.getAllPoints());
    }

    /*will test the perimeter function.
    will test that we get the area that we expect by summing the segments of the
    polygon.
     */
    @Test
    public void perimeterTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        assertTrue(Math.abs(p.perimeter() - 10.65) < eps);
    }

    /*will test the area of the polygon.
    will test that we get the area that we expect from the form from wikipedia.
    the area will be on  a simple polygon and with a polygon that is simple to
    calculate the area with(by rectangles and triangles).
     */
    @Test
    public void areaTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        assertEquals(p.area(), 3.5);
    }

    /*this test will test the contains function. the test will construct
    a various different polygons and different points to check with.
    there are some cases that we want to check:
    1.if a point is outside the polygon then will return false.
    2.if the point is inside the polygon will return true.
    3.if the point is on one of the segments of the polygon will return false.
    4. if the point is inside and there is a straight line on the right side of the point
    will return true.
    6.if the point is outside and there is a straight line on the right side of the point
    will return false.
    7.if the point is inside and there is a straight line on the left side of the point
    will return true.
    8.if the point is outside and there is a straight line on the left side of the point
    will return false.
     */
    @Test
    public void containsTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        Point_2D p1 = new Point_2D(2, 2.5);
        Point_2D p2 = new Point_2D(0.5, 3.5);
        Point_2D p3 = new Point_2D(0.5, 2);
        assertTrue(p.contains(p1));
        assertFalse(p.contains(p2));
        assertFalse(p.contains(p3));
        assertFalse(p.contains(a));
        Polygon_2D polygon2 = new Polygon_2D();
        Point_2D p4 = new Point_2D(1, 2);
        Point_2D p5 = new Point_2D(4, 2);
        Point_2D p6 = new Point_2D(4, 4);
        Point_2D p7 = new Point_2D(2, 4);
        Point_2D p8 = new Point_2D(2, 3);
        Point_2D p9 = new Point_2D(1, 3);
        polygon2.add(p4);
        polygon2.add(p5);
        polygon2.add(p6);
        polygon2.add(p7);
        polygon2.add(p8);
        polygon2.add(p9);
        Point_2D d = new Point_2D(3, 3);
        Point_2D e = new Point_2D(0, 3);
        assertTrue(polygon2.contains(d));
        assertFalse(polygon2.contains(e));
        Polygon_2D polygon3 = new Polygon_2D();
        Point_2D p10 = new Point_2D(1, 1);
        Point_2D p11 = new Point_2D(4, 1);
        Point_2D p12 = new Point_2D(4, 2);
        Point_2D p13 = new Point_2D(3, 2);
        Point_2D p14 = new Point_2D(3, 5);
        Point_2D p15 = new Point_2D(1, 5);
        Point_2D ot1 = new Point_2D(2, 2);
        Point_2D ot2 = new Point_2D(2, 3);
        Point_2D ot3 = new Point_2D(2, 6);
        polygon3.add(p10);
        polygon3.add(p11);
        polygon3.add(p12);
        polygon3.add(p13);
        polygon3.add(p14);
        polygon3.add(p15);
        assertTrue(polygon3.contains(ot2));
        assertTrue(polygon3.contains(ot1));
        assertFalse(polygon3.contains(p11));
        assertFalse(polygon3.contains(ot3));
        Polygon_2D polygon4 = new Polygon_2D();
        Point_2D p16 = new Point_2D(0, 0);
        Point_2D p17 = new Point_2D(2, 3);
        Point_2D p18 = new Point_2D(0, 6);
        Point_2D p19 = new Point_2D(-2, 3);
        polygon4.add(p16);
        polygon4.add(p17);
        polygon4.add(p18);
        polygon4.add(p19);
        Point_2D h = new Point_2D(0, 4);
        assertTrue(polygon4.contains(h));
        Polygon_2D polygon5 = new Polygon_2D();
        Point_2D p20 = new Point_2D(0, 0);
        Point_2D p21 = new Point_2D(0, 1);
        polygon5.add(p20);
        assertTrue(polygon5.contains(p20));
        assertFalse(polygon5.contains(p21));
    }

    /*this test will test the "translate" method
    will test that after translating the polygon with a vector we get the polygon that we expect.
     */
    @Test
    public void translateTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        Point_2D vec = new Point_2D(5, 4);
        Point_2D a1 = new Point_2D(6, 6);
        Point_2D a2 = new Point_2D(8, 6);
        Point_2D a3 = new Point_2D(7, 7);
        Point_2D a4 = new Point_2D(9, 8);
        Point_2D a5 = new Point_2D(6, 8);
        p.translate(vec);
        Polygon_2D expected = new Polygon_2D();
        expected.add(a1);
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);
        assertEquals(expected.toString(), p.toString());
    }

    /*will test the copy method.
    will test that after using the copy method we get the same points
    polygon but another one.
     */
    @Test
    public void copyTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        GeoShape pCopy = p.copy();
        assertEquals(pCopy.toString(), p.toString());
        assertNotEquals(pCopy, p);
    }

    /*will test the scale function.
    will test that the scale function that scale all the points of the polygon is scaling well
    all the points. the method to find the expected polygon is by calculating the scale
    for each point pf the polygon and checking that the string of the scaled one
    and the one that we expect are the same.
     */
    @Test
    public void scaleTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        p.scale(a, 2);
        Polygon_2D expected = new Polygon_2D();
        Point_2D a1 = new Point_2D(1, 2);
        Point_2D a2 = new Point_2D(5, 2);
        Point_2D a3 = new Point_2D(3, 4);
        Point_2D a4 = new Point_2D(7, 6);
        Point_2D a5 = new Point_2D(1, 6);
        expected.add(a1);
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);
        assertEquals(expected.toString(), p.toString());
    }

    /*will test the rotate function.
    will test that the rotate function has been rotated all the points of the polygon
    correctly with the rotate function from Point2D class. will check up to eps.
     */
    @Test
    public void rotateTest() {
        Polygon_2D p = new Polygon_2D();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        p.rotate(a, 90);
        Polygon_2D expected = new Polygon_2D();
        Point_2D a1 = new Point_2D(1, 2);
        Point_2D a2 = new Point_2D(1, 4);
        Point_2D a3 = new Point_2D(0, 3);
        Point_2D a4 = new Point_2D(-1, 5);
        Point_2D a5 = new Point_2D(-1, 2);
        expected.add(a1);
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);
        assertTrue(Math.abs(p.getAllPoints()[0].x() - a1.x()) < eps
                && Math.abs(p.getAllPoints()[0].y() - a1.y()) < eps);
        assertTrue(Math.abs(p.getAllPoints()[1].x() - a2.x()) < eps
                && Math.abs(p.getAllPoints()[1].y() - a2.y()) < eps);
        assertTrue(Math.abs(p.getAllPoints()[2].x() - a3.x()) < eps
                && Math.abs(p.getAllPoints()[2].y() - a3.y()) < eps);
        assertTrue(Math.abs(p.getAllPoints()[3].x() - a4.x()) < eps
                && Math.abs(p.getAllPoints()[3].y() - a4.y()) < eps);
        assertTrue(Math.abs(p.getAllPoints()[4].x() - a5.x()) < eps
                && Math.abs(p.getAllPoints()[4].y() - a5.y()) < eps);
    }
}

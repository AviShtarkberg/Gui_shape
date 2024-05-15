//my od:322530080
package geo;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    public static final Point_2D p1 = new Point_2D(1, 9);

    /*this test will test the copy constructor of the Point2D class.
    will test tha the x,y values of the copy point is the same as the copied one.
    will also test the x and y methods.
     */
    @Test
    public void copyConstructorTest() {
        Point_2D copy = new Point_2D(p1);
        assertEquals(copy.x(), p1.x());
        assertEquals(copy.y(), p1.y());
        assertNotSame(copy,p1);
    }

    /*will test point2D by string method
    will asserEquals if the string that is given and translated into a point is the same as the same
    as we expect. will also test the toString method in the assertEquals method.
     */
    @Test
    public void stringConstructorTest() {
        Point_2D a = new Point_2D("3,5");
        Point_2D b = new Point_2D(3, 5);
        assertEquals(a.toString(), b.toString());
    }

    /*will test the ix, iy methods.
    will check that the casting from int into double for the x and y
     values of the points have worked well.
     */
    @Test
    public void ixIyTest() {
        Point_2D p1 = new Point_2D(2.222222, 3.333333);
        int x = p1.ix();
        int y = p1.iy();
        assertEquals(x, 2);
        assertEquals(y, 3);
    }

    /*will test the add method for 2 points.
    will check if after adding 2 points we get the point that we expect.
     */
    @Test
    public void addTest() {
        Point_2D a = new Point_2D(1, 2);
        Point_2D b = new Point_2D(3, 4);
        Point_2D c = new Point_2D(4, 6);
        assertEquals(a.add(b).toString(), c.toString());
    }

    /*will test the distance function.
    will test that the distance of the point from the origin of Point2D
    class function output is the one that we expect.
     */
    @Test
    public void distanceTest() {
        Point_2D a = new Point_2D(1, 1);
        assertEquals(a.distance(), Math.sqrt(2));
    }

    /*will test the distance between 2 points.
    will test that the distance of the point from the second point that will be
     the output of the distance function is the one that we expect.
     */
    @Test
    public void distanceType2Test() {
        Point_2D a = new Point_2D(1, 1);
        Point_2D b = new Point_2D(3, 5);
        assertEquals(a.distance(b), Math.sqrt(Math.pow((b.x() - a.x()), 2) + Math.pow((b.y() - a.y()), 2)));
    }

    /*will test the equals,closeEquals methods of Point2D class.
    will be true if two points have the same x and y values for the regular equals method
    and for the close equals method same values up to epsilon.
     */
    @Test
    public void equalsCloseEqualsTest() {
        Point_2D a = new Point_2D(1.00001, 1.00001);
        Point_2D b = new Point_2D(1.00001, 1.00001);
        Point_2D c = new Point_2D(1, 1);
        Point_2D d = new Point_2D(2, 2);
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertTrue(a.close2equals(c, 0.01));
        assertFalse(a.equals(d));
    }

    /*will test the vector method.
    will test that we get the vector that we expect.
     */
    @Test
    public void vectorTest() {
        Point_2D target = new Point_2D(5, 0);
        Point_2D a = new Point_2D(1, 1);
        Point_2D exp = new Point_2D(4, -1);
        assertEquals(a.vector(target).toString(), exp.toString());
    }

    /*will test the move method from Point2D class.
    will check that the point has been moved as we expect.
     */
    @Test
    public void moveTest() {
        Point_2D vec = new Point_2D(1, 4);
        Point_2D a = new Point_2D(1, 1);
        a.move(vec);
        Point_2D exp = new Point_2D(2, 5);
        assertEquals(a.toString(), exp.toString());
    }

    /*will test the scale method from Point2D class.
    will test if the point that we get after scaling is the point that we
    expect to get.
     */
    @Test
    public void scaleTest() {
        Point_2D a = new Point_2D(1, 1);
        Point_2D center = new Point_2D(3, 3);
        a.scale(center, 10);
        Point_2D exp = new Point_2D(-17, -17);
        assertEquals(a.toString(), exp.toString());
    }

    /*will test the rotate method.
    will test that after rotating the point we get the point that we expect.
     */
    @Test
    public void rotateTest() {
        Point_2D a = new Point_2D(1, 1);
        Point_2D center = new Point_2D(3, 3);
        a.rotate(center, 90);
        Point_2D exp = new Point_2D(5, 1);
        assertEquals(a.toString(), exp.toString());
    }
}


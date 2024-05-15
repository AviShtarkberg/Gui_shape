//my id: 322530080
package geo;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {
    public final Circle_2D circle = new Circle_2D(Point_2D.ORIGIN, 1);
    public final Circle_2D circle1 = new Circle_2D(Point_2D.ORIGIN, 2);

    // will test the getRadius method. will test that the method output is the right radius.
    @Test
    public void getRadius() {
        assertEquals(circle.getRadius(), 1);
    }

    // will test the getCenter method. will test that the method output is the right center.
    @Test
    public void getCenterTest() {
        Point_2D a = new Point_2D(0, 0);
        assertEquals(circle.getCenter(), a);
    }

    //will test the to string method. will check that the string
    // that represent the circle is the one that we expect to get.
    @Test
    public void toStringTest() {
        assertEquals("Circle_2D,0.0,0.0, 1.0" ,  circle.toString());
    }

    /*will test the contains method.
    will check that for a point that is inside the circle will return true
    and for a circle that is outside the circle will return false.
     */
    @Test
    public void containsTest() {
        Point_2D p1 = new Point_2D(0.5, 0.5);
        Point_2D p2 = new Point_2D(3, 2);
        assertTrue(circle.contains(p1));
        assertFalse(circle.contains(p2));
    }

    //will test the area method. will test that the area of the circle that is
    // calculated by the method is the one that we expect to get.
    @Test
    public void areaTest() {
        assertEquals(circle.area(), Math.PI);
        assertEquals(circle1.area(), 4 * Math.PI);
    }

    //will test the perimeter method. will test that the perimeter that we
    // get from the perimeter method is the one that we expect to get.
    @Test
    public void perimeter() {
        assertEquals(circle.perimeter(), 2 * Math.PI);
        assertEquals(circle1.perimeter(), 4 * Math.PI);
    }

    /*will test the translate method.
    will test that after translating the circle the radius stays the same and the
    center moves to the vector point.
     */
    @Test
    public void translateTest() {
        Circle_2D circle = new Circle_2D(Point_2D.ORIGIN, 1);
        Point_2D vec = new Point_2D(1, 0);
        circle.translate(vec);
        assertEquals(circle.getCenter().x(), vec.x());
        assertEquals(circle.getRadius(), 1);

    }

    /*will test the copy method of the circle class.
    will check that after copying the circle we get the same center anf the same radius
    but the circles are different in memory storage terms.
     */
    @Test
    public void copyTest() {
        Circle_2D copy = (Circle_2D) circle.copy();
        assertEquals(copy.toString(), circle.toString());
        assertNotSame(copy, circle);
    }

    /*will test the scale method for circle.
    will test that the center of the circle have been scaled correctly and the radius have been
    increased correctly to the ratio.
     */
    @Test
    public void scaleTest() {
        Circle_2D circle3 = new Circle_2D(Point_2D.ORIGIN, 1);
        circle3.scale(circle3.getCenter(), 3);
        assertEquals(circle3.getRadius(), 3);
        assertEquals(circle.getCenter().toString(), Point_2D.ORIGIN.toString());
    }

    /*will test the rotate method.
    will test that the rotate method have been rotated the circle as we expect by testing that the
    circle that we get is the one that we expect.
     */
    @Test
    public void rotateTest() {
        Circle_2D circle3 = new Circle_2D(Point_2D.ORIGIN, 1);
        circle3.rotate(circle3.getCenter(), 90);
        assertEquals(circle3.getCenter(), Point_2D.ORIGIN);
        assertEquals(circle3.getRadius(), 1);
    }
}

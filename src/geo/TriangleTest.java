//my id: 322530080
package geo;

import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.fields.AllIndexesSelector;


import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
    public static final Point_2D a = new Point_2D(3, 0);
    public static final Point_2D b = new Point_2D(0, 4);
    public static final Point_2D c = new Point_2D(0, 0);
    public static final Triangle_2D t = new Triangle_2D(a, b, c);
    private static final double eps = 0.001;

    /*will test the copy constructor and the all points method.
    will check that the array that we get from the copy constructor that will
    copy the triangle t is the same as the t by points but not the same on the
    memory.
     */
    @Test
    public void copyConstructorAndGetAllPointsTest() {
        Triangle_2D copy = new Triangle_2D(t);
        assertArrayEquals(copy.getAllPoints(), t.getAllPoints());
        assertNotEquals(copy, t);
    }

    /*will test the getAllDistances method.
    will check that the array that contain all the distances that we get from the
    function is the one that we expect to get by assertArrayEquals method.
     */
    @Test
    public void getAllDistancesTest() {
        double[] allDis = t.getAllDistances();
        double[] exp = {5, 3, 4};
        assertArrayEquals(allDis, exp);

    }

    //this test will test the area of a triangle function.
    // will check that we get the area that we expect from a triangle.
    @Test
    public void areaTest() {
        double area = t.area();
        assertEquals(area, 6);
    }

    //this test will test the perimeter of a triangle function.
    // will check that we get the perimeter that we expect from a triangle.
    @Test
    public void perimeterTest() {
        double p = t.perimeter();
        assertEquals(12, p);
    }

    //this test will test the contains function for a triangle .
    // will check that the contains function asserts true for a point that is .
    @Test
    public void containsTest() {
        Point_2D h = new Point_2D(1, 1);
        Point_2D m = new Point_2D(4, 3);
        assertTrue(t.contains(h));
        assertFalse(t.contains(m));
    }

    /*will test the translate method for triangle.
    will test that after moving the triangle by vector v all the points
    are the one that we expect. the method will move all the points so will
    check if the array of t translated is the same one that we expect.
     */
    @Test
    public void translateTest() {
        Point_2D a = new Point_2D(3, 0);
        Point_2D b = new Point_2D(0, 4);
        Point_2D c = new Point_2D(0, 0);
        Triangle_2D copyT = new Triangle_2D(a, b, c);
        Point_2D vector = new Point_2D(5, 0);
        copyT.translate(vector);
        Point_2D p1 = new Point_2D(8, 0);
        Point_2D p2 = new Point_2D(5, 4);
        Point_2D p3 = new Point_2D(5, 0);
        Triangle_2D exp = new Triangle_2D(p1, p2, p3);
        assertArrayEquals(exp.getAllPoints(), copyT.getAllPoints());
    }

    /*will test the copy method.
    will test that when we copy a triangle we get the same triangle
    but not on the same place in memory term. will be checked with getAllPoints
    method.
     */
    @Test
    public void CopyTest() {
        Triangle_2D copyT = (Triangle_2D) t.copy();
        assertArrayEquals(copyT.getAllPoints(), t.getAllPoints());
        assertNotEquals(copyT, t);
    }

    /*will test the scale method.
    will test that all the points of the triangle have been scaled, will be checked
    by get all points method and assertArrayEquals method.
     */
    @Test
    public void scaleTest() {
        Point_2D a = new Point_2D(3, 0);
        Point_2D b = new Point_2D(0, 4);
        Point_2D c = new Point_2D(0, 0);
        Triangle_2D copyT = new Triangle_2D(a, b, c);
        copyT.scale(a, 3);
        Point_2D p2 = new Point_2D(-6, 12);
        Point_2D p3 = new Point_2D(-6, 0);
        Triangle_2D exp = new Triangle_2D(a, p2, p3);
        assertArrayEquals(exp.getAllPoints(), copyT.getAllPoints());
    }

    /*will test the rotate method.
    will test that after we rotate the triangle we get the triangle that we expect.
    will be checked by checking all the points of the rotated triangle with getAllPoints
    method with equation to the array that we expect to get.
     */
    @Test
    public void rotateTest() {
        Point_2D a = new Point_2D(3, 0);
        Point_2D b = new Point_2D(0, 4);
        Point_2D c = new Point_2D(0, 0);
        Triangle_2D copyT = new Triangle_2D(a, b, c);
        copyT.rotate(c, 90);
        Point_2D p1 = new Point_2D(0, 3);
        Point_2D p2 = new Point_2D(-4, 0);
        assertTrue(Math.abs(copyT.getAllPoints()[0].x() - p1.x()) < eps
                && Math.abs(copyT.getAllPoints()[0].y() - p1.y()) < eps);
        assertTrue(Math.abs(copyT.getAllPoints()[1].x() - p2.x()) < eps
                && Math.abs(copyT.getAllPoints()[1].y() - p2.y()) < eps);
        assertTrue(Math.abs(copyT.getAllPoints()[2].x() - c.x()) < eps
                && Math.abs(copyT.getAllPoints()[2].y() - c.y()) < eps);
    }

    //this test will test the toString function. will check that we get the string that we expect.
    @Test
    public void toStringTest(){
        Point_2D a = new Point_2D(3, 0);
        Point_2D b = new Point_2D(0, 4);
        Point_2D c = new Point_2D(0, 0);
        Triangle_2D triangle = new Triangle_2D(a, b, c);
        String s = "Triangle_2D, 3.0,0.0 0.0,4.0 0.0,0.0";
        assertEquals(s,triangle.toString());
    }
}

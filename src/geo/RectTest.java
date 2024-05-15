//my id: 322530080
package geo;


import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class RectTest {
    private static final double eps = 0.001;
    public static final Point_2D p1 = new Point_2D(3, 5);
    public static final Point_2D p2 = new Point_2D(0, 0);

    public static final Rect_2D rectangle1 = new Rect_2D(p1, p2);

    /*will test that after coping the rectangle we get another rectangle in memory terms but with
    the same points.
     */
    @Test
    public void copyConstructorTest() {
        Rect_2D copy = new Rect_2D(rectangle1);
       assertNotSame(rectangle1,copy);
        assertEquals(copy.get_a().toString(), rectangle1.get_a().toString());
        assertEquals(copy.get_b().toString(), rectangle1.get_b().toString());
        assertEquals(copy.get_c().toString(), rectangle1.get_c().toString());
        assertEquals(copy.get_d().toString(), rectangle1.get_d().toString());
    }

    // will test the contains method, will test that for a point that is inside
    // the rectangle returns true and for a point that is outside the rectangle returns false.
    @Test
    public void containsTest() {
        Point_2D c = new Point_2D(1, 1);
        Point_2D d = new Point_2D(5, 5);
        assertTrue(rectangle1.contains(c));
        assertFalse(rectangle1.contains(d));
    }

    //will test that the area method calculate the area of the rectangle correctly.
    @Test
    public void areaTest() {
        assertEquals(rectangle1.area(), 15);
    }

    //will test that the perimeter method calculate the perimeter of the rectangle correctly.
    @Test
    public void perimeterTest() {
        assertEquals(rectangle1.perimeter(), 16);
    }

    /*will test the translate method for rectangle.
    will test that after moving the rectangle by vector all the points of the rectangle
    are the one that we expect. will be checked with toString from the Point2D class.
     */
    @Test
    public void translateTest() {
        Point_2D p1 = new Point_2D(3, 5);
        Point_2D p2 = new Point_2D(0, 0);
        Rect_2D rec = new Rect_2D(p1, p2);
        Point_2D vector = new Point_2D(9, 0);
        rec.translate(vector);
        Point_2D p3 = new Point_2D(3, 5);
        Point_2D p4 = new Point_2D(0, 0);
        assertEquals(p1.toString(), p3.toString());
        assertEquals(p2.toString(), p4.toString());
    }

    /*will test the copy method.
    will test that when we copy a rectangle we get the same rectangle
    but not on the same place in memory term. will be checked with toString method from the Point2D class.
     */
    @Test
    public void copyTest() {
        Rect_2D copy = (Rect_2D) rectangle1.copy();
        assertEquals(copy.get_a().toString(), rectangle1.get_a().toString());
        assertEquals(copy.get_b().toString(), rectangle1.get_b().toString());
        assertEquals(copy.get_c().toString(), rectangle1.get_c().toString());
        assertEquals(copy.get_d().toString(), rectangle1.get_d().toString());
        assertNotSame(copy,rectangle1);
    }

    /*will test the scale method.
    will test that all the points of the rectangle have been scaled, will be checked
    by checking if all the points of the rectangle have benn scaled.
    */
    @Test
    public void scaleTest() {
        Point_2D a = new Point_2D(3, 5);
        Point_2D b = new Point_2D(0, 0);
        Rect_2D rectangle = new Rect_2D(a, b);
        rectangle.scale(a, 3);
        Point_2D exp1 = new Point_2D(3, 5);
        Point_2D exp2 = new Point_2D(-6, -10);
        assertEquals(rectangle.get_a().toString(), exp1.toString());
        assertEquals(rectangle.get_b().toString(), exp2.toString());
    }

    /*will test the rotate method.
    will test that after rotating a rectangle all the points have been rotated accordingly to
    the center and the angleDegrees, will be checked by equation of the points
    with points that we expect to get. the equation will be up to epsilon.
     */
    @Test
    public void rotateTest() {
        Point_2D p1 = new Point_2D(3, 5);
        Point_2D p2 = new Point_2D(0, 0);
        Rect_2D rec = new Rect_2D(p1, p2);
        rec.rotate(p1, 90);
        Point_2D p3 = new Point_2D(3, 5);
        Point_2D p4 = new Point_2D(8, 2);
        Point_2D p5 = new Point_2D(8, 5);
        Point_2D p6 = new Point_2D(3, 2);
        assertTrue(Math.abs(rec.get_a().x() - p3.x()) < eps && Math.abs(rec.get_a().y() - p3.y()) < eps);
        assertTrue(Math.abs(rec.get_b().x() - p4.x()) < eps && Math.abs(rec.get_b().y() - p4.y()) < eps);
        assertTrue(Math.abs(rec.get_c().x() - p5.x()) < eps && Math.abs(rec.get_c().y() - p5.y()) < eps);
        assertTrue(Math.abs(rec.get_d().x() - p6.x()) < eps && Math.abs(rec.get_d().y() - p6.y()) < eps);
    }

    //this test will test the toString function. will check that we get the string that we expect.
    @Test
    public void toStringTest(){
        Point_2D p1 = new Point_2D(3, 5);
        Point_2D p2 = new Point_2D(0, 0);
        Rect_2D rec = new Rect_2D(p1, p2);
        String s = "Rect_2D, 3.0,5.0, 0.0,0.0, 3.0,0.0, 0.0,5.0";
        assertEquals(rec.toString(),s);
    }

}

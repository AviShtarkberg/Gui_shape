//my id: 322530080
package geo;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SegmentTest {
    private static final double eps = 0.001;
    public static final Point_2D p1 = new Point_2D(3, 0);
    public static final Point_2D p2 = new Point_2D(0, 0);
    public static final Point_2D p3 = new Point_2D(1, 0);
    public static final Point_2D p4 = new Point_2D(6, 0);
    public static final Point_2D p5 = new Point_2D(6, 12);
    public static final Point_2D p6 = new Point_2D(4, 8);
    public static final Segment_2D segment = new Segment_2D(p1, p2);

    /*will test the copy constructor of the segment class.
    will test that the points of the copied segment are the same
    but the segments are not the same in memory storage terms.
    will aslo test the get p1 and p2 method.
     */
    @Test
    public void copyConstructorTest() {
        Segment_2D copy = new Segment_2D(segment);
        assertEquals(copy.get_p1().x(), segment.get_p1().x());
        assertEquals(copy.get_p1().y(), segment.get_p1().y());
        assertNotEquals(copy, segment);
    }

    /*will test the contains method.
    will test for a point that is inside the segment will return true
    and for a point that is outside the segment will return false as expected.
     */
    @Test
    public void containsTest() {
        Segment_2D b = new Segment_2D(p2, p5);
        assertTrue(segment.contains(p3));
        assertFalse(segment.contains(p4));
        assertTrue(b.contains(p6));
        assertFalse(b.contains(p4));
    }

    /*will test that the area method returns the area of a segment as 0
    and the perimeter as twice of the segment length.

     */
    @Test
    public void areaAndPerimeterTest() {
        assertEquals(segment.area(), 0);
        assertEquals(segment.perimeter(), 6);
    }

    /*will test the translate function for segment.
    will test that after translating the 2 points of the segment translated as we
    expect by the vector point.
     */
    @Test
    public void translateTest() {
        Point_2D p1 = new Point_2D(3, 0);
        Point_2D p2 = new Point_2D(0, 0);
        Segment_2D segment = new Segment_2D(p1, p2);
        Point_2D vector = new Point_2D(3, 5);
        Point_2D exp = new Point_2D(6, 5);
        segment.translate(vector);
        assertEquals(segment.get_p2().toString(), vector.toString());
        assertEquals(segment.get_p1().toString(), exp.toString());
    }

    /* will test the copy method for segment.
    will test that the segment that we get from a copy method is
    the same as we want to copy in the points term but different in the memory storage
    term.
     */
    @Test
    public void copyTest() {
        Segment_2D copy = (Segment_2D) segment.copy();
        assertEquals(copy.get_p1().toString(), segment.get_p1().toString());
        assertEquals(copy.get_p2().toString(), segment.get_p2().toString());
        assertNotEquals(copy, segment);

    }

    /*this test will test the scale function.
    will test that after we scale a segment the 2 points of the segment have been
    scaled correctly as we expect.
     */
    @Test
    public void scaleTest() {
        Point_2D p1 = new Point_2D(3, 0);
        Point_2D p2 = new Point_2D(0, 0);
        Segment_2D segment = new Segment_2D(p1, p2);
        segment.scale(p2, 7);
        Point_2D exp1 = new Point_2D(21, 0);
        assertEquals(segment.get_p1().toString(), exp1.toString());
        assertEquals(segment.get_p2().toString(), p2.toString());
    }
    /*will test the rotate method for segment.
    this test will test that after rotating the segment we got the segment that we expect
    by testing that the 2 points have been rotated as we expect.
     */

    @Test
    public void rotateTest() {
        Point_2D p1 = new Point_2D(3, 0);
        Point_2D p2 = new Point_2D(0, 0);
        Segment_2D segment = new Segment_2D(p1, p2);
        segment.rotate(p2, 90);
        Point_2D p4 = new Point_2D(0, 3);
        assertTrue(Math.abs(segment.get_p1().x() - p4.x()) < eps && Math.abs(segment.get_p1().y() - p4.y()) < eps);
        assertTrue(Math.abs(segment.get_p2().x() - p2.x()) < eps && Math.abs(segment.get_p2().y() - p2.y()) < eps);
    }

    //this test will test the toString function. will check that we get the string that we expect.
    @Test
    public void  toStringTest(){
        Point_2D p1 = new Point_2D(3, 0);
        Point_2D p2 = new Point_2D(0, 0);
        Segment_2D segment = new Segment_2D(p1, p2);
        String exp = "Segment_2D, 3.0,0.0, 0.0,0.0";
        assertEquals(segment.toString(),exp);
    }
}

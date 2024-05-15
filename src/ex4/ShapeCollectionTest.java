package ex4;

import geo.*;
import gui.GUIShape;
import gui.GUI_Shape;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeCollectionTest {
    public static final Point_2D p1= new Point_2D(0,0);
    public static final Point_2D p2= new Point_2D(3,3);
    public static final Point_2D p4= new Point_2D(1,1);
    public static final Point_2D p5= new Point_2D(3,0);
    public static final Point_2D p6= new Point_2D(1.5,3);
    public static final Circle_2D c1 = new Circle_2D(p1,7);
    public static final Rect_2D r1 = new Rect_2D(p1,p2);
    public static final Segment_2D s1 = new Segment_2D(p1,p4);
    public static final Triangle_2D t1 = new Triangle_2D(p1,p6,p5);
    public static final Polygon_2D poly1 = new Polygon_2D();
    public static final  GUI_Shape circle = new GUIShape(c1, false, Color.BLACK, 0);
    public static final  GUI_Shape rect = new GUIShape(r1, false, Color.BLACK, 0);
    public static final  GUI_Shape segment= new GUIShape(s1, false, Color.BLACK, 0);
    public static final  GUI_Shape triangle= new GUIShape(t1, false, Color.BLACK, 0);


    private final ShapeCollection shapes = new ShapeCollection();
    //will test the get method for each shape that is inside the shape collection.
    //will also check that the add function is working well by adding the shapes to the shapes collection.
    @Test
    void get() {
        shapes.add(circle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.add(triangle);
        assertEquals(shapes.get(3).toString(),triangle.toString());
        assertEquals(shapes.get(2).toString(),segment.toString());
        assertEquals(shapes.get(1).toString(),rect.toString());
        assertEquals(shapes.get(0).toString(),circle.toString());
    }
    //will check that the size of the shape collection is the one that we
    // expect to get after filling the array list.
    @Test
    void size() {
        shapes.add(circle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.add(triangle);
        assertEquals(shapes.size(),4);
    }

    //will check that we get the shape collection that we expect after removing a shape at
    //in the shape collection at a specific index.
    @Test
    void removeElementAt() {
        shapes.add(circle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.add(triangle);
        shapes.removeElementAt(2);
        ShapeCollection exp = new ShapeCollection();
        exp.add(circle);
        exp.add(rect);
        exp.add(triangle);
        assertEquals(shapes.toString(),exp.toString());
    }
    //will test the add at function. will test that the function is adding the shape at the
    //index that we expect.
    @Test
    void addAt() {
        shapes.add(circle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.addAt(triangle,0);
        ShapeCollection exp = new ShapeCollection();
        exp.add(triangle);
        exp.add(circle);
        exp.add(rect);
        exp.add(segment);
        assertEquals(exp.toString(), shapes.toString());
    }
    //will test the copy method for the shape collection.
    //will test that we get the same collection in value terms but not the same
    //on the memory terms.
    @Test
    void copy() {
        shapes.add(circle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.addAt(triangle,0);
        GUI_Shape_Collection exp = shapes.copy();
        assertEquals(exp.toString(),shapes.toString());
        assertNotSame(exp,shapes);
    }
    /*will test the sort function for the shape collection. will test with the area comparator
    that the sort is sorting by the right area.
     */
    @Test
    void Sort() {
        shapes.add(triangle);
        shapes.add(rect);
        shapes.add(segment);
        Comparator<GUI_Shape> areaComparator = Comparator.comparingDouble(o -> o.getShape().area());
        shapes.sort(areaComparator);
        ShapeCollection exp = new ShapeCollection();
        exp.add(segment);
        exp.add(triangle);
        exp.add(rect);
        assertEquals(shapes.toString(),exp.toString());
    }

    //will test that after using remove all function the size of the shape collection is 0.
    @Test
    void removeAll() {
        shapes.add(triangle);
        shapes.add(rect);
        shapes.add(segment);
        shapes.removeAll();
        assertEquals(shapes.size(),0);
    }
}
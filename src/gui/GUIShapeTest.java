package gui;

import geo.Circle_2D;
import geo.GeoShape;
import geo.Point_2D;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {

    //will test the get shape method. will test that the get shape return the string that we expect to get.
    @Test
    void getShape() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        String [] s = a.toString().split(",");
        assertEquals(s[0],"GUIShape");
        assertEquals(s[2],"false");
        assertEquals(s[3],"0");
        assertEquals(s[4],"Circle_2D");
        assertEquals(s[5],"3.0");
        assertEquals(s[6],"5.0");
        assertEquals(s[7]," 6.0");

    }
    //will test that if shape have been set to be filled the isFilled method will return true, else will return false.
    @Test
    void isFilled() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
       assertFalse(a.isFilled());
        assertTrue(b.isFilled());
    }
    //will test that using the setFill method is working as we expect.
    @Test
    void setFilled() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        a.setFilled(true);
        assertTrue(a.isFilled());
    }
    //will test that the get color function returns the right color of the shape.
    @Test
    void getColor() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        assertEquals(a.getColor(),Color.BLACK);
    }
    //will test that the set color method is setting the color of the shape as we expect.
    @Test
    void setColor() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        a.setColor(Color.cyan);
        assertEquals(a.getColor(),Color.cyan);
    }
    //will test that the get tag method work as we expect.
    //will also test the set tag method.
    @Test
    void getTag() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        a.setTag(5);
        assertEquals(a.getTag(),5);
    }
    //will test that the copy method is copying the shape in the right way.
    // will test that it's the same shape in value terms but not the same shape in the memory terms.
    @Test
    void copy() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
       GUI_Shape c3 = a.copy();
       assertEquals(c3.toString(),a.toString());
       assertNotSame(c3,a);

    }
    //will test that the is selected method returns true only for selected shapes.
    //will also test the setSelected function.
    @Test
    void isSelected() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
        a.setSelected(true);
        assertTrue(a.isSelected());
        assertFalse(!a.isSelected());
    }
    @Test
    void setShape() {
        Circle_2D c1 = new Circle_2D(new Point_2D(3,5),6);
        GUI_Shape a = new GUIShape(c1,false, Color.BLACK,0);
        GUI_Shape b = new GUIShape(c1,true, Color.BLACK,0);
       GeoShape c =  a.getShape();
       assertEquals(a.toString().substring(27),c.toString());
       assertNotSame(a,c);
    }
}
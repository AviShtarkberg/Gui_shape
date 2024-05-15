package ex4;

import geo.*;
import gui.GUIShape;
import gui.GUI_Shape;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class ShapeCollection implements GUI_Shape_Collection {
    private ArrayList<GUI_Shape> _shapes;

    //will construct a new array list.
    public ShapeCollection() {
        _shapes = new ArrayList<GUI_Shape>();
    }

    //will get the shape at the index that is given as input.
    @Override
    public GUI_Shape get(int i) {
        return _shapes.get(i);
    }

    //will return the size of the array list.
    @Override
    public int size() {
        return _shapes.size();
    }

    /*will allow to remove an element at a specific index inside the array list.

     */
    @Override
    public GUI_Shape removeElementAt(int i) {
        if (i < this._shapes.size() && i >= 0) {
            return this._shapes.remove(i);
        }
        return null;
    }

    //will allow to add a new shape at a specific index inside the array list.
    @Override
    public void addAt(GUI_Shape s, int i) {
        if (i >= 0 && i < this.size() && s != null) {
            this._shapes.add(i, s);
        }
    }

    //will add a new shape to the shape collection.
    @Override
    public void add(GUI_Shape s) {
        if (s != null && s.getShape() != null) {
            _shapes.add(s);
        }
    }

    //will copy the arraylist that represent the shape collection.
    @Override
    public GUI_Shape_Collection copy() {
        ShapeCollection copy = new ShapeCollection();
        copy._shapes.addAll(this._shapes);
        return copy;
    }

    //will sort the array list correspondingly to the comparator the is given as input to the method.
    //will use the sort method from javas methods.
    @Override
    public void sort(Comparator<GUI_Shape> comp) {
        _shapes.sort(comp);
    }

    //will remove all the shapes from the shape collection. will use the removeAll method of the arraylist.
    @Override
    public void removeAll() {
        this._shapes.removeAll(_shapes);
    }

    //will run threw the shape collection and will save the strings that
    //represent the shapes to a file.
    @Override
    public void save(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i <_shapes.size() ; i++) {
                writer.write(_shapes.get(i)+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //will loud the shapes accordingly to the strings that represent them.
    @Override
    public void load(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                GUI_Shape shape = createShapeFromString(line);
                if (shape != null) {
                    _shapes.add(shape);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //this method will construct the GUIShape from the string that is inside the file.
    //will construct accordingly to the shape that is in the current line that the load function will read.
    private GUIShape createShapeFromString(String shapeString){
        String [] shape = shapeString.split(",");
        if(shape[4].equals("Circle_2D")) {
            Color color = Color.decode(shape[1]);
            boolean field = Boolean.parseBoolean(shape[2]);
            int tag = Integer.parseInt(shape[3]);
            double x = Double.parseDouble(shape[5]);
            double y = Double.parseDouble(shape[6]);
            double r = Double.parseDouble(shape[7]);
            Point_2D c = new Point_2D(x, y);
            GeoShape g = new Circle_2D(c, r);
            return new GUIShape(g, field, color, tag);
        }
        if (shape[4].equals("Segment_2D")){
            Color color = Color.decode(shape[1]);
            boolean field = Boolean.parseBoolean(shape[2]);
            int tag = Integer.parseInt(shape[3]);
            double x1 = Double.parseDouble(shape[5]);
            double y1 = Double.parseDouble(shape[6]);
            double x2 = Double.parseDouble(shape[7]);
            double y2 = Double.parseDouble(shape[8]);
            Point_2D a = new Point_2D(x1,y1);
            Point_2D b = new Point_2D(x2,y2);
            GeoShape g = new Segment_2D(a,b);
            return new GUIShape(g, field, color, tag);
        }
        if (shape[4].equals("Rect_2D")){
            Color color = Color.decode(shape[1]);
            boolean field = Boolean.parseBoolean(shape[2]);
            int tag = Integer.parseInt(shape[3]);
            double x1 = Double.parseDouble(shape[5]);
            double y1 = Double.parseDouble(shape[6]);
            double x2 = Double.parseDouble(shape[7]);
            double y2 = Double.parseDouble(shape[8]);
            Point_2D a = new Point_2D(x1,y1);
            Point_2D b = new Point_2D(x2,y2);
            GeoShape g = new Rect_2D(a,b);
            return new GUIShape(g, field, color, tag);
        }
        if (shape[4].equals("Polygon_2D")){
            Color color = Color.decode(shape[1]);
            boolean field = Boolean.parseBoolean(shape[2]);
            int tag = Integer.parseInt(shape[3]);
            Polygon_2D p = new Polygon_2D();
            for (int i = 5; i <shape.length ; i=i+2) {
                Point_2D a = new Point_2D(Double.parseDouble(shape[i]),Double.parseDouble(shape[i+1]));
                p.add(a);
            }
            return new GUIShape(p,field,color,tag);
        }
        if (shape[4].equals("Triangle_2D")){
            Color color = Color.decode(shape[1]);
            boolean field = Boolean.parseBoolean(shape[2]);
            int tag = Integer.parseInt(shape[3]);
            Polygon_2D p = new Polygon_2D();
            for (int i = 5; i <shape.length ; i=i+2) {
                Point_2D a = new Point_2D(Double.parseDouble(shape[i]),Double.parseDouble(shape[i+1]));
                p.add(a);
            }
            return new GUIShape(p,field,color,tag);
        }
        return null;
    }
    /*will convert the shapes inside the array list that represent the shape collection
    to string that represent all the shapes.
     */
    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < size(); i = i + 1) {
            ans += this.get(i) + "\n";
        }
        return ans;
    }


}

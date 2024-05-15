package ex4;

import geo.*;
import gui.Ex4_GUI;
import gui.GUIShape;
import gui.GUI_Shape;
import gui.StdDraw_Ex4;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Objects;
import java.io.File;

/**
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Color _color = Color.blue;
    private boolean _fill = true;
    private String _mode = "";
    private Point_2D _p1;
    private int tag;

    private Polygon_2D polygon;

    private static Ex4 _winEx4 = null;

    private Ex4() {
        init(null);
    }

    public void init(GUI_Shape_Collection s) {
        // //shou,ld be s.copy();}
        _shapes = Objects.requireNonNullElseGet(s, ShapeCollection::new);
        _gs = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    /*this method will run the functions that we want to run by one click at the
      mode that we want to run.
      the modes:
      remove: will use the remove function to remove the selected shape.
      all: will select all the shapes with the all function.
      anti: after pressed will select all the shapes that are not selected and will "unselect" all
      the shapes that are selected.
      none: when this button is pressed will "unselect" all thr shapes that are selected.
      the sorts: when pressed will use the comparators lambda(was done with intellij shortcuts) to
      determine the type of the comparator in which we want to sort the shapes and will sort
      with javas sort algo to sort with the comparator that is chosen.
      info: when pressed will print the string that represent all the shapes.


     */
    public void drawShapes() {
        Comparator<GUI_Shape> areaComparator = Comparator.comparingDouble(o -> o.getShape().area());
        Comparator<GUI_Shape> antiAreaComparator = areaComparator.reversed();
        Comparator<GUI_Shape> perimeterComparator = Comparator.comparingDouble(o -> o.getShape().perimeter());
        Comparator<GUI_Shape> antiPerimeterComparator = perimeterComparator.reversed();
        Comparator<GUI_Shape> toStringComparator = Comparator.comparing(o -> o.getShape().toString());
        Comparator<GUI_Shape> antiToStringCComparator = toStringComparator.reversed();
        Comparator<GUI_Shape> tagComparator = Comparator.comparingInt(GUI_Shape::getTag);
        Comparator<GUI_Shape> antiTagComparator = tagComparator.reversed();
        if (_mode.equals("Remove")) {
            remove();
        }
        if (_mode.equals("All")) {
            all();
        }
        if (_mode.equals("Anti")) {
            anti();
        }
        if (_mode.equals("None")) {
            none();
        }
        if (_mode.equals("ByArea")) {
            _shapes.sort(areaComparator);
        }
        if (_mode.equals("ByAntiArea")) {
            _shapes.sort(antiAreaComparator);
        }
        if (_mode.equals("ByPerimeter")) {
            _shapes.sort(perimeterComparator);
        }
        if (_mode.equals("ByAntiPerimeter")) {
            _shapes.sort(antiPerimeterComparator);
        }
        if (_mode.equals("ByToString")) {
            _shapes.sort(toStringComparator);
        }
        if (_mode.equals("ByAntiToString")) {
            _shapes.sort(antiToStringCComparator);
        }
        if (_mode.equals("ByTag")) {
            _shapes.sort(tagComparator);
        }
        if (_mode.equals("ByAntiTag")) {
            _shapes.sort(antiTagComparator);
        }
        if (_mode.equals("Info")) {
            System.out.println(getInfo());
        }
        if (_mode.equals("Save")) {
            save();
        }
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);
            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    /*this function will determine the way in  which each shape is draws.
    will use the std draw function with each type of shape. for circle the std draw function input will be
    the radius and the center of the circle.
    for segment will use the draw line method with the segments 2 points x,y values.
    for polygon will run a loop to fill 2 array representing the x and y values of the points of the polygon.
    and will use the polygon draw from the std class that expect to get 2 arrays of x and y values.
    for rectangle will use 2 points to determine 2 other points (as in Rect_2D), and will use the draw
    polygon method to draw the shape(more comfortable than with the draw rectangle).
    note that for each shape the function will be split to filled or not filled shape.
     */
    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex4.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment_2D segment) {
            Point_2D a = segment.get_p1();
            Point_2D b = segment.get_p2();
            StdDraw_Ex4.line(a.x(), a.y(), b.x(), b.y());
        }
        if (gs instanceof Polygon_2D) {
            Polygon_2D c = (Polygon_2D) gs;
            Point_2D[] polygonPoints = c.getAllPoints();
            double[] x = new double[polygonPoints.length];
            double[] y = new double[polygonPoints.length];
            for (int i = 0; i <= polygonPoints.length - 1; i++) {
                x[i] = polygonPoints[i].x();
                y[i] = polygonPoints[i].y();
            }
            if (isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            } else {
                StdDraw_Ex4.polygon(x, y);
            }
        }
        if (gs instanceof Rect_2D rectangle) {
            Point_2D[] rectPoints = {rectangle.get_a(), rectangle.get_b(), rectangle.get_c(), rectangle.get_d()};
            double[] xValues = {rectPoints[0].x(), rectPoints[2].x(), rectPoints[1].x(), rectPoints[3].x()};
            double[] yValues = {rectPoints[0].y(), rectPoints[2].y(), rectPoints[1].y(), rectPoints[3].y()};
            if (isFill) {
                StdDraw_Ex4.filledPolygon(xValues, yValues);
            } else {
                StdDraw_Ex4.polygon(xValues, yValues);
            }
        }
    }

    //will set the color of the shape.
    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    //will set the status of the shape to be filled if the button fill is pressed before painting the shape.
    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    //will set determine the color of the shape with the coloros that are valid at this function.
    public void actionPerformed(String p) {
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (_mode.equals("Load")) {
            load();
        }
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        drawShapes();
    }


    /*this function checks the current mode and run the modes that request a mouse click.
    if the mode is "Circle" or "Segment", it either initializes a new shape or adds the current shape
    to the list of shapes.
    if the mode is "Polygon" or "Triangle", it adds the clicked point to the polygon being constructed.
    if the mode is "Rect", it either initializes a new rectangle shape or adds the current rectangle
    shape to the list.
    if the mode is "Move" or "Copy", it handles the movement or duplication of selected shapes.
    if the mode is "Scale_90%" or "Scale_110%", it performs scaling operations on the clicked point.
    if the mode is "Point", it selects shapes that contain the clicked point.
    if the mode is "Rotate", it calculates the rotation angle based on the initial and
    current points and rotates the shapes accordingly.
    At the end, it calls the drawShapes method to update the graphical display.
    note: the rotate method uses a calculation that was taken from chatGpt.
     */
    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                tag++;
                _gs.setTag(tag);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                tag++;
                _gs.setTag(tag);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Polygon")) {
            if (_gs == null) {
                polygon = new Polygon_2D();
                polygon.add(p);
            } else {
                polygon.add(p);
            }
        }
        if (_mode.equals("Triangle")) {
            if (_gs == null) {
                polygon = new Polygon_2D();
                polygon.add(p);
            } else {
                polygon.add(p);
                if (polygon.getAllPoints().length == 3) {
                    _gs = new GUIShape(polygon, false, _color, 0);
                    _gs.setColor(_color);
                    _gs.setFilled(_fill);
                    _shapes.add(_gs);
                    tag++;
                    _gs.setTag(tag);
                    _gs = null;
                    polygon = null;
                }
            }
        }
        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                Rect_2D rect = new Rect_2D(_p1, p);
                _gs = new GUIShape(rect, false, _color, 0);
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                tag++;
                _gs.setTag(tag);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Scale_90%")) {
            _p1 = p;
            scale90P();
        }
        if (_mode.equals("Scale_110%")) {
            _p1 = p;
            scale110P();
        }

        if (_mode.equals("Point")) {
            select(p);
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                double x1 = _p1.x();
                double x2 = p.x();
                double y1 = _p1.y();
                double y2 = p.y();
                double dotProduct = (x1 * x2) + (y1 * y2);
                double magnitudeA = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
                double magnitudeB = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));
                double angle = Math.acos(dotProduct / (magnitudeA * magnitudeB));
                double angleDegrees = Math.toDegrees(angle);
                while (angleDegrees < 0) {
                    angleDegrees += 360;
                }
                rotate(angleDegrees);
                _p1 = null;
            }
        }
        drawShapes();
    }

    //will set the shape that we want to select mode to be selected.
    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    /*will set all the shapes status to be selected.
     will run a loop to determine the selected status of each shape.
     */
    private void all() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(true);
            }
        }
    }

    /*will select the shapes that are selected and will unselect all the shapes that are selected.
     will run a loop to determine the selected status of each shape.
     */
    private void anti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    /*will set all the shapes status to be unselected.
     will run a loop to determine the selected status of each shape.
     */
    private void none() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(false);
            }
        }
    }

    /*will move the shape that is selected that we want to move.
     will run a loop that will check for each shape if its selected and if so will use the translate
     method to move the shape.
     */
    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }

    /* will scale the selected shapes by 90%.
    will run a loop that will check which of the shapes is selected and will
    use the scale method to scale the shape with 0.9 ratio.
     */
    private void scale90P() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(_p1, 0.9);
            }
        }
    }

    /* will scale the selected shapes by 110%.
       will run a loop that will check which of the shapes is selected and will
       use the scale method to scale the shape with 1.1 ratio.
        */
    private void scale110P() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(_p1, 1.1);
            }
        }
    }

    /*this function will remove the shape that is selected.
    will run a loop to check which of the shapes is selected and will use the removeElement
    method from the gui shape class to the remove this shape.
     */
    private void remove() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                _shapes.removeElementAt(i);
            }
        }
    }

    /*will run a loop threw the shapes collection and will rotate the shapes that are selected.
    will use the rotate method from the geoShape interface.
     */
    public void rotate(double degree) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.rotate(_p1, degree);
            }
        }
    }

    /*will run a loop threw the shape collection  array and will copy the shape that are selected
    using the copy method from the geo shape interface.
    will use the translate method to translate the copied shape to the place that we want to copy the
    shape to, will be determined by mouse click.
     */
    private void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                GeoShape copy = g.copy();
                copy.translate(_p1);
                _gs = new GUIShape(copy, s.isFilled(), s.getColor(), tag);
                tag++;
                this._shapes.add(_gs);
                _gs = null;
            }
        }
    }

    /*this method will force the polygon to be "closed".
    will stop letting the user add point to the polygon when the
    user press the right mouse click.
    so when the user press the right click will add the polygon to the
    shape collection and will determine the tag of the polygon.
     */
    public void mouseRightClicked(Point_2D p) {
        System.out.println("right click!");
        if (_mode.equals("Polygon")) {
            if (_gs != null) {
                polygon.add(p);
                _gs = new GUIShape(polygon, _fill, _color, tag);
                _shapes.add(_gs);
                tag++;
                _gs = null;
                polygon = null;
            }
        }
        drawShapes();
    }

    /*the mouseMoved method is triggered when the mouse is moved in the graphical user interface.
    it checks if either _p1 or polygon is not null, indicating that a shape is being constructed.
    it then retrieves the current mouse coordinates (x1 and y1) and creates a new instance of
    GeoShape based on the current mode.
    if the mode is "Circle", it calculates the distance between the initial point _p1 and the current
    mouse position p, and creates a new Circle_2D shape with _p1 as the center and the calculated
    distance as the radius.
    if the mode is "Segment", it creates a new Segment_2D shape with _p1 as one endpoint and the current
    mouse position p as the other endpoint.
    if the mode is "Polygon" or "Triangle", it creates a temporary copy of the existing polygon (temp) and adds
    the current mouse position p to it.
    then, it creates a new Polygon_2D shape based on the temporary polygon.
    if the mode is "Rect", it creates a new Rect_2D shape with _p1 as one corner and the current mouse
    position p as the opposite corner.
    after the shape is finished it assigns the newly created
    GUIShape to _gs, sets its properties (color, filled, and tag),
    and calls drawShapes to update the graphical display.
     */
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null || polygon != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShape gs = null;
            Point_2D p = new Point_2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
            }
            if (_mode.equals("Polygon")) {
                Polygon_2D temp = new Polygon_2D(polygon);
                temp.add(p);
                gs = new Polygon_2D(temp);
            }
            if (_mode.equals("Triangle")) {
                Polygon_2D temp = new Polygon_2D(polygon);
                temp.add(p);
                gs = new Polygon_2D(temp);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
            }
            _gs = new GUIShape(gs, false, Color.pink, tag);
            drawShapes();
        }
    }

    //will return the shapes collection that contain all the shapes.
    @Override
    public GUI_Shape_Collection getShape_Collection() {
        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }

    //will return the string that represent the array of the shapes that are inside the arraylist at the
    //moment, will be used at the info function when the info button is pressed.
    @Override
    public String getInfo() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans.append(s.toString()).append("\n");
        }
        return ans.toString();
    }

    private void save() {
        FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    private void load() {
        _shapes.removeAll();
        FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }
}

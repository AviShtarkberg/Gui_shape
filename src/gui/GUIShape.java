package gui;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */

import geo.GeoShape;

import java.awt.*;



public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	/*the constructor of the gui shape.
	will set the attributes of the shapes - if filled, the color, the tag, and will determine if the
	shape is selected or not.
	 */
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	//the copy constructor of the shape.
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	//will return the shape
	@Override
	public GeoShape getShape() {
		return _g;
	}
	//will return a boolean that represent if the shape is filled or not.
	@Override
	public boolean isFilled() {
		return _fill;
	}
	//will set the fill status of the shape.
	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}
		//will get the color of the shape.
	@Override
	public Color getColor() {
		return _color;
	}
	//will set the color of the shape.
	@Override
	public void setColor(Color cl) {
		_color = cl;
	}
	//will get the tag of the shape.
	@Override
	public int getTag() {
		return _tag;
	}
	//the setter of the tag of the shape.
	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}
	//the function that copy the Guishape. will return a new shape that is not the same shape in the memory
	// terms but the same in the values that represent the shape.
	@Override
	public GUI_Shape copy() {
		return new GUIShape(this);
	}
	//will return the string that represent the shape.
	@Override
	public String toString() {
		String a = this.getShape().toString();
		String [] split = a.split(",");
		return "GUIShape,"+ _color.getRGB()+","+_fill+","+_tag+","+this.getShape();
	}
	private void init(String[] ww) {

	}
	//will check weather or not the shape is selected.
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}

	//will set the selected status of the shape.
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	//will set the shape.
	@Override
	public void setShape(GeoShape g) {
		_g.copy();
	}
}
